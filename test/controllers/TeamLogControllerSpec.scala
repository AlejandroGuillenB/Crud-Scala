package controllers

import play.api.mvc.ControllerComponents
import services.TeamLogService

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class TeamLogControllerSpec extends Specification with Mockito {
  isolated
  val cc: ControllerComponents = mock[ControllerComponents]
  val teamLogService: TeamLogService = mock[TeamLogService]
  val teamLogController = new TeamLogController(cc, teamLogService)

  "TeamLogController" should {

    "get By Date Init" in {
      teamLogService.getItemByDateInit(any) returns scala.concurrent.Future.successful(null)
      val result = teamLogController.getByDateInit(12345l)
      result === null
    }

    "get By Date End" in {
      teamLogService.getItemByDateEnd(any) returns scala.concurrent.Future.successful(null)
      val result = teamLogController.getByDateEnd(12345l)
      result === null
    }

    "get By Person" in {
      teamLogService.getItemByPerson(anyString) returns scala.concurrent.Future.successful(null)
      val result = teamLogController.getByPerson("person")
      result === null
    }

    "get By Account" in {
      teamLogService.getItemByAccount(anyString) returns scala.concurrent.Future.successful(null)
      val result = teamLogController.getByAccount("account")
      result === null
    }

    "get All" in {
      teamLogService.listAllItems returns scala.concurrent.Future.successful(null)
      val result = teamLogController.getAll()
      result === null
    }
  }
}