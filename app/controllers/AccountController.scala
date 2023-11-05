package controllers

import com.google.inject.Inject
import models.{Account, AccountForm}
import play.api.libs.json._
import play.api.mvc._
import services.AccountService


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
class AccountController @Inject()(
                                  cc: ControllerComponents,
                                  accountService: AccountService
                                ) extends AbstractController(cc) {
  implicit val accountFormat = Json.format[Account]

  def getAll() = Action.async { implicit request: Request[AnyContent] =>
    accountService.listAllItems map { items =>
      Ok(Json.toJson(items))
    }
  }

  def getById(id: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      accountService.getItem(id) map { item =>
        Ok(Json.toJson(item))
      }
  }

  def add() = Action.async {
    implicit request: Request[AnyContent] =>
      AccountForm.form.bindFromRequest.fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val newAccountItem = Account(0,data.nameaccount, data.nameclient, data.request, data.liable)
          accountService.addItem(newAccountItem).map(_ => Redirect(routes.AccountController.getAll))
        }
      )
  }

  def update(id: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      AccountForm.form.bindFromRequest.fold(
        errorsForm => {
          errorsForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val accountItem = Account(id, data.nameaccount, data.nameclient, data.request, data.liable)
          accountService.updateItem(accountItem).map(_ => Redirect(routes.AccountController.getAll))
        }
      )
  }

  def delete(id: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      accountService.deleteItem(id) map { res =>
        Redirect(routes.AccountController.getAll)
      }
  }
}