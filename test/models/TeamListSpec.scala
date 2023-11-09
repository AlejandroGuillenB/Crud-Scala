package models

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import play.api.db.slick.DatabaseConfigProvider

import java.sql.Date
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global

class TeamListSpec extends Specification with Mockito {
  isolated
  val dbConfigProvider: DatabaseConfigProvider = mock[DatabaseConfigProvider]
  val teamList = new TeamList(dbConfigProvider)

  "TeamList" should {
    val dateInit = new Date(1699568343)
    val dateEnd = new Date(1731190743)
    "add" in {
      val result = teamList.add(new Team(1, 1, dateInit, dateEnd))
      Await.result(result, 10.seconds) === "replaceMeWithExpectedResult"
    }

    "get By Person" in {
      val result = teamList.getByPerson(1)
      Await.result(result, 10.seconds) === Some(new Team(1, 1, dateInit, dateEnd))
    }

    "update" in {
      val result = teamList.update(new Team(1, 1, dateInit, dateEnd))
      Await.result(result, 10.seconds) === 1
    }

    "delete" in {
      val result = teamList.delete(1, 1)
      Await.result(result, 10.seconds) === 1
    }

    "get By Account" in {
      val result = teamList.getByAccount(1)
      Await.result(result, 10.seconds) === Some(new Team(1, 1, dateInit, dateEnd))
    }

    "list All" in {
      val result = teamList.listAll
      Await.result(result, 10.seconds) === null
    }
  }
}