package models

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{ExecutionContext, Future}
import java.sql.{Timestamp, Date}

case class TeamLog(idlog: Int, actionlog: String, datelog: Timestamp, idperson: Int, nameperson: String, idaccount: Int, nameaccount: String, initteam: Date, endteam: Date)

case class TeamLogFormData(idlog: Int, actionlog: String, datelog: Timestamp, idperson: Int, nameperson: String, idaccount: Int, nameaccount: String, initteam: Date, endteam: Date)

object TeamLogForm {
  val form = Form(
    mapping(
      "idlog" -> number,
      "actionlog" -> text,
      "datelog" -> sqlTimestamp,
      "idperson" -> number,
      "nameperson" -> text,
      "idaccount" -> number,
      "nameaccount" -> text,
      "initteam" -> sqlDate,
      "endteam" -> sqlDate
    )(TeamLogFormData.apply)(TeamLogFormData.unapply)
  )
}

class TeamLogTableDef(tag: Tag) extends Table[TeamLog](tag, "teamlog") {

  def idlog = column[Int]("idlog", O.PrimaryKey)

  def actionlog = column[String]("actionlog")

  def datelog = column[Timestamp]("datelog")

  def idperson = column[Int]("idperson")

  def nameperson = column[String]("nameperson")

  def idaccount = column[Int]("idaccount")

  def nameaccount = column[String]("nameaccount")

  def initteam = column[Date]("initteam")

  def endteam = column[Date]("endteam")

  override def * = (idlog, actionlog, datelog, idperson, nameperson, idaccount, nameaccount, initteam, endteam) <> (TeamLog.tupled, TeamLog.unapply)
}

class TeamLogList @Inject()(
                          protected val dbConfigProvider: DatabaseConfigProvider
                        )(
                          implicit executionContext: ExecutionContext
                        ) extends HasDatabaseConfigProvider[JdbcProfile] {
  val teamLogList = TableQuery[TeamLogTableDef]

  def getByAccount(account: String): Future[Seq[TeamLog]] = {
    dbConfig.db.run(teamLogList.filter(_.nameaccount.toUpperCase === account.toUpperCase()).result)
  }

  def getByPerson(person: String): Future[Seq[TeamLog]] = {
    dbConfig.db.run(teamLogList.filter(_.nameperson.toUpperCase === person.toUpperCase()).result)
  }

  def getByDateInit(dateInit: Date): Future[Seq[TeamLog]] = {
    dbConfig.db.run(teamLogList.filter(_.initteam === dateInit).result)
  }

  def getByDateEnd(dateEnd: Date): Future[Seq[TeamLog]] = {
    dbConfig.db.run(teamLogList.filter(_.endteam === dateEnd).result)
  }

  def listAll: Future[Seq[TeamLog]] = {
    dbConfig.db.run(teamLogList.result)
  }
}