package models

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

case class Person(id: Int, name: String, email: String, englishLevel: String, technicalKnows: String, linkCV: String)

case class PersonFormData(id: Int, name: String, email: String, englishLevel: String, technicalKnows: String, linkCV: String)


object PersonForm {
  val form = Form(
    mapping(
      "id" -> number,
      "name" -> text,
      "email" -> text,
      "englishLevel" -> text,
      "technicalKnows" -> text,
      "linkCV" -> text
    )(PersonFormData.apply)(PersonFormData.unapply)
  )
}

class PersonTableDef(tag: Tag) extends Table[Person](tag, "person") {
  def id = column[Int]("id", O.PrimaryKey)

  def name = column[String]("name")

  def email = column[String]("email")

  def englishLevel = column[String]("englishLevel")

  def technicalKnows = column[String]("technicalKnows")

  def linkCV = column[String]("linkCV")

  override def * = (id, name, email, englishLevel, technicalKnows, linkCV) <> (Person.tupled, Person.unapply)
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
        .map(person => (person.name, person.technicalKnows, person.englishLevel, person.linkCV))
        .update(personItem.name, personItem.technicalKnows, personItem.englishLevel, personItem.linkCV)
      )
  }

  def get(id: Int): Future[Option[Person]] = {
    dbConfig.db.run(personList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Person]] = {
    dbConfig.db.run(personList.result)
  }
}