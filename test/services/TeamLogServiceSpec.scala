package services

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import models.TeamLogList

import scala.concurrent.duration._
import scala.concurrent.Await

class TeamLogServiceSpec extends Specification with Mockito {
  isolated
  val teamLogs: TeamLogList = mock[TeamLogList]
  val teamLogService = new TeamLogService(teamLogs)

  "TeamLogService" should {

    "get Item By Person" in {
      teamLogs.getByPerson(anyString) returns scala.concurrent.Future.successful(null)
      val result = teamLogService.getItemByPerson("person")
      Await.result(result, 10.seconds) === null
    }

    "get Item By Date Init" in {
      teamLogs.getByDateInit(any) returns scala.concurrent.Future.successful(null)
      val result = teamLogService.getItemByDateInit(null)
      Await.result(result, 10.seconds) === null
    }

    "get Item By Date End" in {
      teamLogs.getByDateEnd(any) returns scala.concurrent.Future.successful(null)
      val result = teamLogService.getItemByDateEnd(null)
      Await.result(result, 10.seconds) === null
    }

    "list All Items" in {
      teamLogs.listAll returns scala.concurrent.Future.successful(null)
      val result = teamLogService.listAllItems
      Await.result(result, 10.seconds) === null
    }

    "get Item By Account" in {
      teamLogs.getByAccount(anyString) returns scala.concurrent.Future.successful(null)
      val result = teamLogService.getItemByAccount("account")
      Await.result(result, 10.seconds) === null
    }
  }
}