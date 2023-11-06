package models

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{ExecutionContext, Future}
import java.sql.Date

case class Team(account: Int, person: Int, init: Date, end: Date)

case class TeamFormData(account: Int, person: Int, init: Date, end: Date)

object TeamForm {
  val form = Form(
    mapping(
      "account" -> number,
      "person" -> number,
      "init" -> sqlDate,
      "end" -> sqlDate
    )(TeamFormData.apply)(TeamFormData.unapply)
  )
}

class TeamTableDef(tag: Tag) extends Table[Team](tag, "team") {

  def account = column[Int]("account", O.PrimaryKey)

  def person = column[Int]("person", O.PrimaryKey)

  def init = column[Date]("init")

  def end = column[Date]("end")

  override def * = (account, person, init, end) <> (Team.tupled, Team.unapply)
}

class TeamList @Inject()(
                          protected val dbConfigProvider: DatabaseConfigProvider
                        )(
                          implicit executionContext: ExecutionContext
                        ) extends HasDatabaseConfigProvider[JdbcProfile] {
  val teamList = TableQuery[TeamTableDef]

  def add(teamItem: Team): Future[String] = {
    dbConfig.db
      .run(teamList += teamItem)
      .map(res => "Team successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage)
          ex.getMessage
        }
      }
  }

  def delete(account: Int, person: Int): Future[Int] = {
    dbConfig.db.run(teamList.filter(team => team.account === account && team.person === person).delete)
  }

  def update(teamItem: Team): Future[Int] = {
    dbConfig.db
      .run(teamList.filter(team => team.account === teamItem.account && team.person === teamItem.person)
        .map(team => (team.account, team.person, team.init, team.end))
        .update(teamItem.account, teamItem.person, teamItem.init, teamItem.end)
      )
  }

  def getByAccount(account: Int): Future[Option[Team]] = {
    dbConfig.db.run(teamList.filter(_.account === account).result.headOption)
  }

  def getByPerson(person: Int): Future[Option[Team]] = {
    dbConfig.db.run(teamList.filter(_.person === person).result.headOption)
  }

  def listAll: Future[Seq[Team]] = {
    dbConfig.db.run(teamList.result)
  }
}