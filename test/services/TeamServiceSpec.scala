package services

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import models.{Team, TeamList}

import scala.concurrent.duration._
import scala.concurrent.Await

class TeamServiceSpec extends Specification with Mockito {
  isolated
  val teams: TeamList = mock[TeamList]
  val teamService = new TeamService(teams)

  "TeamService" should {

    "get Item By Person" in {
      teams.getByPerson(anyInt) returns scala.concurrent.Future.successful(Some(new Team(1, 1, null, null)))
      val result = teamService.getItemByPerson(1)
      Await.result(result, 10.seconds) === Some(new Team(1, 1, null, null))
    }

    "update Item" in {
      teams.update(any) returns scala.concurrent.Future.successful(1)
      val result = teamService.updateItem(new Team(1, 1, null, null))
      Await.result(result, 10.seconds) === 1
    }

    "list All Items" in {
      teams.listAll returns scala.concurrent.Future.successful(null)
      val result = teamService.listAllItems
      Await.result(result, 10.seconds) === null
    }

    "add Item" in {
      teams.add(any) returns scala.concurrent.Future.successful("addResponse")
      val result = teamService.addItem(new Team(1, 1, null, null))
      Await.result(result, 10.seconds) === "replaceMeWithExpectedResult"
    }

    "delete Item" in {
      teams.delete(anyInt, anyInt) returns scala.concurrent.Future.successful(1)
      val result = teamService.deleteItem(1, 1)
      Await.result(result, 10.seconds) === 1
    }

    "get Item By Account" in {
      teams.getByAccount(anyInt) returns scala.concurrent.Future.successful(Some(new Team(1, 1, null, null)))
      val result = teamService.getItemByAccount(1)
      Await.result(result, 10.seconds) === Some(new Team(1, 1, null, null))
    }
  }
}