package services

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import models.{Person, PersonList}

import scala.concurrent.duration._
import scala.concurrent.Await

class PersonServiceSpec extends Specification with Mockito {
  isolated
  val persons: PersonList = mock[PersonList]
  val personService = new PersonService(persons)

  "PersonService" should {

    "get Item" in {
      persons.get(anyInt) returns scala.concurrent.Future.successful(Some(new Person(1, "name", "email", "englishlevel", "technicalknows", "linkcv")))
      val result = personService.getItem(1)
      Await.result(result, 10.seconds) === Some(new Person(1, "name", "email", "englishlevel", "technicalknows", "linkcv"))
    }

    "update Item" in {
      persons.update(any) returns scala.concurrent.Future.successful(1)
      val result = personService.updateItem(new Person(1, "name", "email", "englishlevel", "technicalknows", "linkcv"))
      Await.result(result, 10.seconds) === 1
    }

    "list All Items" in {
      persons.listAll returns scala.concurrent.Future.successful(null)
      val result = personService.listAllItems
      Await.result(result, 10.seconds) === null
    }

    "add Item" in {
      persons.add(any) returns scala.concurrent.Future.successful("addResponse")
      val result = personService.addItem(new Person(1, "name", "email", "englishlevel", "technicalknows", "linkcv"))
      Await.result(result, 10.seconds) === "replaceMeWithExpectedResult"
    }

    "delete Item" in {
      persons.delete(anyInt) returns scala.concurrent.Future.successful(1)
      val result = personService.deleteItem(1)
      Await.result(result, 10.seconds) === 1
    }
  }
}