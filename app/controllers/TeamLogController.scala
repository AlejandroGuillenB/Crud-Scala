package controllers

import javax.inject.Inject
import models.TeamLog
import play.api.libs.json._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import services.TeamLogService

import java.sql.{Date, Timestamp}
import scala.concurrent.ExecutionContext.Implicits.global

class TeamLogController @Inject()(
                                   cc: ControllerComponents,
                                   teamLogService: TeamLogService
                                 ) extends AbstractController(cc) {
  implicit val timestampReads: Reads[Timestamp] = {
    implicitly[Reads[Long]].map(new Timestamp(_))
  }

  implicit val timestampWrites: Writes[Timestamp] = {
    implicitly[Writes[Long]].contramap(_.getTime)
  }

  implicit val teamLogFormat: OFormat[TeamLog] = Json.format[TeamLog]

  def getAll() = Action.async { implicit request: Request[AnyContent] =>
    teamLogService.listAllItems map { items =>
      Ok(Json.toJson(items))
    }
  }

  def getByAccount(account: String) = Action.async {
    implicit request: Request[AnyContent] =>
      teamLogService.getItemByAccount(account) map { item =>
        Ok(Json.toJson(item))
      }
  }

  def getByPerson(person: String) = Action.async {
    implicit request: Request[AnyContent] =>
      teamLogService.getItemByPerson(person) map { item =>
        Ok(Json.toJson(item))
      }
  }

  def getByDateInit(dateInit: Long) = Action.async {
    implicit request: Request[AnyContent] =>
      teamLogService.getItemByDateInit(new Date(dateInit)) map { item =>
        Ok(Json.toJson(item))
      }
  }

  def getByDateEnd(dateEnd: Long) = Action.async {
    implicit request: Request[AnyContent] =>
      teamLogService.getItemByDateEnd(new Date(dateEnd)) map { item =>
        Ok(Json.toJson(item))
      }
  }

}
