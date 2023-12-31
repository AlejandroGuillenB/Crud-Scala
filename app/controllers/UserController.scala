package controllers

import com.google.inject.Inject
import models.{User, UserForm}
import play.api.libs.json._
import play.api.mvc._
import services.UserService
import utils.Jwt
import play.api.http.HeaderNames.AUTHORIZATION
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserController @Inject()(
                                cc: ControllerComponents,
                                userService: UserService
                              ) extends AbstractController(cc) {
  implicit val userFormat: OFormat[User] = Json.format[User]

  def getAll() = Action.async { implicit request: Request[AnyContent] =>
    userService.listAllItems map { users =>
      Ok(Json.toJson(users))
    }
  }

  def getById(id: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      request.headers.get(AUTHORIZATION).getOrElse(throw new Exception("Token not found"))
      userService.getItem(id) map { user =>
        Ok(Json.toJson(user))
      }
  }

  def add() = Action.async {
    implicit request: Request[AnyContent] =>
      val token = request.headers.get(AUTHORIZATION).getOrElse(throw new Exception("Token not found"))
      UserForm.form.bindFromRequest().fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val newUserItem = User(data.id, data.username, data.password, data.rol)
          println("=====>", token)
          userService.addItem(newUserItem).map(_ => Redirect(routes.UserController.getAll()))
        }
      )
  }

  def update(id: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      UserForm.form.bindFromRequest().fold(
        errorsForm => {
          errorsForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val userItem = User(id, data.username, data.password, data.rol)
          userService.updateItem(userItem).map(_ => Redirect(routes.UserController.getAll()))
        }
      )
  }

  def delete(id: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      userService.deleteItem(id) map { res =>
        Redirect(routes.UserController.getAll())
      }
  }

  def login(userName: String, password: String) = Action.async {
    implicit request: Request[AnyContent] =>
      userService.login(userName, password) map { user =>
        val jwt = Jwt(Map(
          "id" -> user.get.id,
          "username" -> user.get.username,
          "rol" -> user.get.rol
        ))
        Ok(Json.toJson(user)).withHeaders(AUTHORIZATION -> jwt)
      }
  }
}
