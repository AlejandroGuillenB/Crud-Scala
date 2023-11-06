package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Person, PersonForm}
import services.PersonService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PersonController @Inject()(
                                  cc: ControllerComponents,
                                  personService: PersonService
                                )
  extends AbstractController(cc) {

  implicit val personFormat: OFormat[Person] = Json.format[Person]

  def getAll() = Action.async {
    implicit request: Request[AnyContent] =>
      personService.listAllItems map { items =>
        Ok(Json.toJson(items))
      }
  }

  def getById(id: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      personService.getItem(id) map { item =>
        Ok(Json.toJson(item))
      }
  }

  def add() = Action.async {
    implicit request: Request[AnyContent] =>
      PersonForm.form.bindFromRequest().fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val newPersonItem = Person(data.id, data.name, data.email, data.englishlevel, data.technicalknows, data.linkcv)
          personService.addItem(newPersonItem).map(_ => Redirect(routes.PersonController.getAll()))
        }
      )
  }

  def update(id: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      PersonForm.form.bindFromRequest().fold(
        errorsForm => {
          errorsForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val personItem = Person(id, data.name, data.email, data.englishlevel, data.technicalknows, data.linkcv)
          personService.updateItem(personItem).map(_ => Redirect(routes.PersonController.getAll()))
        }
      )
  }

  def delete(id: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      personService.deleteItem(id) map { res =>
        Redirect(routes.PersonController.getAll())
      }
  }
}