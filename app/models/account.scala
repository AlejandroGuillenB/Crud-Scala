package models

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

case class Account(id: Int, nameaccount: String, nameclient: String, request: String, liable: Int)

case class AccountFormData(nameaccount: String, nameclient: String, request: String, liable: Int)

object AccountForm {
  val form = Form(
    mapping(
      "nameaccount" -> text,
      "nameclient" -> text,
      "request" -> text,
      "liable" -> number
    )(AccountFormData.apply)(AccountFormData.unapply)
  )
}

class AccountTableDef(tag: Tag) extends Table[Account](tag, "account") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def nameaccount = column[String]("nameaccount")

  def nameclient = column[String]("nameclient")

  def request = column[String]("request")

  def liable = column[Int]("liable")

  override def * = (id, nameaccount, nameclient, request, liable) <> (Account.tupled, Account.unapply)
}

class AccountList @Inject()(
                             protected val dbConfigProvider: DatabaseConfigProvider
                           )(
                             implicit executionContext: ExecutionContext
                           ) extends HasDatabaseConfigProvider[JdbcProfile] {
  val accountList = TableQuery[AccountTableDef]

  def add(accountItem: Account): Future[String] = {
    dbConfig.db
      .run(accountList += accountItem)
      .map(res => "Account successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage)
          ex.getMessage
        }
      }
  }

  def delete(id: Int): Future[Int] = {
    dbConfig.db.run(accountList.filter(_.id === id).delete)
  }

  def update(accountItem: Account): Future[Int] = {
    dbConfig.db
      .run(accountList.filter(_.id === accountItem.id)
        .map(account => (account.nameaccount, account.nameclient, account.request, account.liable))
        .update(accountItem.nameaccount, accountItem.nameclient, accountItem.request, accountItem.liable)
      )
  }

  def get(id: Int): Future[Option[Account]] = {
    dbConfig.db.run(accountList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Account]] = {
    dbConfig.db.run(accountList.result)
  }
}
