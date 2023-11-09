package models

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class AccountListSpec extends Specification with Mockito {
  isolated
  val dbConfigProvider: DatabaseConfigProvider = mock[DatabaseConfigProvider]
  val accountList = new AccountList(dbConfigProvider)

  "AccountList" should {

    "add" in {
      val result = accountList.add(new Account(1, "nameaccount", "nameclient", "request", 1))
      Await.result(result, 10.seconds) === "replaceMeWithExpectedResult"
    }

    "update" in {
      val result = accountList.update(new Account(1, "nameaccount", "nameclient", "request", 1))
      Await.result(result, 10.seconds) === 1
    }

    "delete" in {
      val result = accountList.delete(1)
      Await.result(result, 10.seconds) === 1
    }

    "get" in {
      val result = accountList.get(1)
      Await.result(result, 10.seconds) === Some(new Account(1, "nameaccount", "nameclient", "request", 1))
    }

    "list All" in {
      val result = accountList.listAll
      Await.result(result, 10.seconds) === null
    }
  }
}