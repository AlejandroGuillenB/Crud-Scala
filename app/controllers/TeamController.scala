package controllers

import models.{Team, TeamForm}
import play.api.libs.json.{Json, OFormat}
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import services.TeamService

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TeamController @Inject()(
                               cc: ControllerComponents,
                               teamService: TeamService
                             ) extends AbstractController(cc) {
  implicit val teamFormat: OFormat[Team] = Json.format[Team]

  def getAll() = Action.async {
    implicit request: Request[AnyContent] =>
    teamService.listAllItems map { items =>
      Ok(Json.toJson(items))
    }
  }

  def getByAccount(account: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      teamService.getItemByAccount(account) map { item =>
        Ok(Json.toJson(item))
      }
  }

  def getByPerson(person: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      teamService.getItemByPerson(person) map { item =>
        Ok(Json.toJson(item))
      }
  }

  def add() = Action.async {
    implicit request: Request[AnyContent] =>
      TeamForm.form.bindFromRequest().fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val newTeamItem = Team(data.account, data.person, data.init, data.end)
          teamService.addItem(newTeamItem).map(_ => Redirect(routes.TeamController.getAll()))
        }
      )
  }

  def update(account: Int, person: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      TeamForm.form.bindFromRequest().fold(
        errorsForm => {
          errorsForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val teamItem = Team(account, person, data.init, data.end)
          teamService.updateItem(teamItem).map(_ => Redirect(routes.TeamController.getAll()))
        }
      )
  }

  def delete(account: Int, person: Int) = Action.async {
    implicit request: Request[AnyContent] =>
      teamService.deleteItem(account, person) map { res =>
        Redirect(routes.TeamController.getAll())
      }
  }
}
