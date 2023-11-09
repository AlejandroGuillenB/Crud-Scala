package controllers

import models.Person
import play.api.mvc.ControllerComponents
import services.PersonService

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class PersonControllerSpec extends Specification with Mockito {
  isolated
  val cc: ControllerComponents = mock[ControllerComponents]
  val personService: PersonService = mock[PersonService]
  val personController = new PersonController(cc, personService)

  "PersonController" should {

    "add" in {
      personService.addItem(any) returns scala.concurrent.Future.successful("addItemResponse")
      val result = personController.add()
      result === null
    }

    "update" in {
      personService.updateItem(any) returns scala.concurrent.Future.successful(1)
      val result = personController.update(1)
      result === null
    }

    "get By Id" in {
      personService.getItem(anyInt) returns scala.concurrent.Future.successful(Some(new Person(1, "name", "email", "englishlevel", "technicalknows", "linkcv")))
      val result = personController.getById(1)
      result === null
    }

    "delete" in {
      personService.deleteItem(anyInt) returns scala.concurrent.Future.successful(1)
      val result = personController.delete(1)
      result === null
    }

    "get All" in {
      personService.listAllItems returns scala.concurrent.Future.successful(null)
      val result = personController.getAll()
      result === null
    }
  }
}