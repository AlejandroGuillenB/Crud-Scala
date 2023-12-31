package models

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import utils.ValidationData

import scala.concurrent.{ExecutionContext, Future}

case class Person(id: Int, name: String, email: String, englishlevel: String, technicalknows: String, linkcv: String)

case class PersonFormData(id: Int, name: String, email: String, englishlevel: String, technicalknows: String, linkcv: String)


object PersonForm {
  val vd = new ValidationData()
  val form = Form(
    mapping(
      "id" -> number,
      "name" -> text.verifying(vd.message, name => vd.validateName(name)),
      "email" -> text.verifying(vd.message, email => vd.validateEmail(email)),
      "englishlevel" -> text.verifying(vd.message, englishlevel => vd.validateEnglishLevel(englishlevel)),
      "technicalknows" -> text,
      "linkcv" -> text
    )(PersonFormData.apply)(PersonFormData.unapply)
  )
}

class PersonTableDef(tag: Tag) extends Table[Person](tag, "person") {
  def id = column[Int]("id", O.PrimaryKey)

  def name = column[String]("name")

  def email = column[String]("email")

  def englishlevel = column[String]("englishlevel")

  def technicalknows = column[String]("technicalknows")

  def linkcv = column[String]("linkcv")

  override def * = (id, name, email, englishlevel, technicalknows, linkcv) <> (Person.tupled, Person.unapply)
}

class PersonList @Inject()(
                            protected val dbConfigProvider: DatabaseConfigProvider
                          )(
                            implicit executionContext: ExecutionContext
                          ) extends HasDatabaseConfigProvider[JdbcProfile] {
  val personList = TableQuery[PersonTableDef]

  def add(personItem: Person): Future[String] = {
    dbConfig.db
      .run(personList += personItem)
      .map(res => "Person successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage)
          ex.getMessage
        }
      }
  }

  def delete(id: Int): Future[Int] = {
    dbConfig.db.run(personList.filter(_.id === id).delete)
  }

  def update(personItem: Person): Future[Int] = {
    dbConfig.db
      .run(personList.filter(_.id === personItem.id)
        .map(person => (person.name, person.technicalknows, person.englishlevel, person.linkcv))
        .update(personItem.name, personItem.technicalknows, personItem.englishlevel, personItem.linkcv)
      )
  }

  def get(id: Int): Future[Option[Person]] = {
    dbConfig.db.run(personList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Person]] = {
    dbConfig.db.run(personList.result)
  }
}