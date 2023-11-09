package services

import io.swagger.annotations.{Api, ApiOperation}

import javax.inject.Inject
import models.{Person, PersonList}

import scala.concurrent.Future

@Api(value = "/account")
class PersonService @Inject() (persons: PersonList){

  @ApiOperation(
    value = "Add person",
    notes = "Add a new person",
    response = classOf[models.Person],
    httpMethod = "POST"
  )
  def addItem(person: Person): Future[String] = {
    persons.add(person)
  }

  @ApiOperation(
    value = "Delete person",
    notes = "Delete a person by id",
    response = classOf[models.Person],
    httpMethod = "DELETE"
  )
  def deleteItem(id: Int): Future[Int] = {
    persons.delete(id)
  }

  @ApiOperation(
    value = "Update person",
    notes = "Update a person",
    response = classOf[models.Person],
    httpMethod = "PUT"
  )
  def updateItem(person: Person): Future[Int] = {
    persons.update(person)
  }

  @ApiOperation(
    value = "Get person by id",
    notes = "Return a person by id",
    response = classOf[models.Person],
    httpMethod = "GET"
  )
  def getItem(id: Int): Future[Option[Person]] = {
    persons.get(id)
  }

  @ApiOperation(
    value = "List all person",
    notes = "Return a list with all person",
    response = classOf[models.Person],
    httpMethod = "GET"
  )
  def listAllItems: Future[Seq[Person]] = {
    persons.listAll
  }
}
