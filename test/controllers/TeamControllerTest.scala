package controllers

import models.Team
import play.api.mvc.ControllerComponents
import services.TeamService

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class TeamControllerSpec extends Specification with Mockito {
  isolated
  val cc: ControllerComponents = mock[ControllerComponents]
  val teamService: TeamService = mock[TeamService]
  val teamController = new TeamController(cc, teamService)

  "TeamController" should {

    "add" in {
      teamService.addItem(any) returns scala.concurrent.Future.successful("addItemResponse")
      val result = teamController.add()
      result === null
    }

    "update" in {
      teamService.updateItem(any) returns scala.concurrent.Future.successful(1)
      val result = teamController.update(1, 1)
      result === null
    }

    "get By Person" in {
      teamService.getItemByPerson(anyInt) returns scala.concurrent.Future.successful(Some(new Team(1, 1, null, null)))
      val result = teamController.getByPerson(1)
      result === null
    }

    "get By Account" in {
      teamService.getItemByAccount(anyInt) returns scala.concurrent.Future.successful(Some(new Team(1, 1, null, null)))
      val result = teamController.getByAccount(1)
      result === null
    }

    "delete" in {
      teamService.deleteItem(anyInt, anyInt) returns scala.concurrent.Future.successful(1)
      val result = teamController.delete(1, 1)
      result === null
    }

    "get All" in {
      teamService.listAllItems returns scala.concurrent.Future.successful(null)
      val result = teamController.getAll()
      result === null
    }
  }
}