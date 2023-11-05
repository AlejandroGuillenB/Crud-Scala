package models

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

case class User(id: Int, username: String, password: String, rol: Int)

case class UserFormData(id: Int, username: String, password: String, rol: Int)

object UserForm {
  val form = Form(
    mapping(
      "id" -> number,
      "username" -> text,
      "password" -> text,
      "rol" -> number
    )(UserFormData.apply)(UserFormData.unapply)
  )
}

class UserTableDef(tag: Tag) extends Table[User](tag, "user") {

  def id = column[Int]("id", O.PrimaryKey)

  def username = column[String]("username")

  def password = column[String]("password")

  def rol = column[Int]("rol")

  override def * = (id, username, password, rol) <> (User.tupled, User.unapply)
}

class UserList @Inject()(
                          protected val dbConfigProvider: DatabaseConfigProvider
                        )(
                          implicit executionContext: ExecutionContext
                        ) extends HasDatabaseConfigProvider[JdbcProfile] {
  val userList = TableQuery[UserTableDef]

  def add(userItem: User): Future[String] = {
    dbConfig.db
      .run(userList += userItem)
      .map(res => "User successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage)
          ex.getMessage
        }
      }
  }

  def delete(id: Int): Future[Int] = {
    dbConfig.db.run(userList.filter(_.id === id).delete)
  }

  def update(userItem: User): Future[Int] = {
    dbConfig.db
      .run(userList.filter(_.id === userItem.id)
        .map(user => (user.username, user.password, user.rol))
        .update(userItem.username, userItem.password, userItem.rol)
      )
  }

  def get(id: Int): Future[Option[User]] = {
    dbConfig.db.run(userList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[User]] = {
    dbConfig.db.run(userList.result)
  }
}
