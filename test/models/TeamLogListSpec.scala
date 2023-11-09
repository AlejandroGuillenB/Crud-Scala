package models

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global

class TeamLogListSpec extends Specification with Mockito {
  isolated
  val dbConfigProvider: DatabaseConfigProvider = mock[DatabaseConfigProvider]
  val teamLogList = new TeamLogList(dbConfigProvider)

  "TeamLogList" should {

    "get By Person" in {
      val result = teamLogList.getByPerson("person")
      Await.result(result, 10.seconds) === null
    }

    "get By Date Init" in {
      val result = teamLogList.getByDateInit(null)
      Await.result(result, 10.seconds) === null
    }

    "get By Date End" in {
      val result = teamLogList.getByDateEnd(null)
      Await.result(result, 10.seconds) === null
    }

    "get By Account" in {
      val result = teamLogList.getByAccount("account")
      Await.result(result, 10.seconds) === null
    }

    "list All" in {
      val result = teamLogList.listAll
      Await.result(result, 10.seconds) === null
    }
  }
}