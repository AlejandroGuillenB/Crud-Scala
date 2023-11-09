package services

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import models.{Account, AccountList}

import scala.concurrent.duration._
import scala.concurrent.Await

class AccountServiceSpec extends Specification with Mockito {
  isolated
  val accounts: AccountList = mock[AccountList]
  val accountService = new AccountService(accounts)

  "AccountService" should {

    "get Item" in {
      accounts.get(anyInt) returns scala.concurrent.Future.successful(Some(new Account(1, "nameaccount", "nameclient", "request", 1)))
      val result = accountService.getItem(1)
      Await.result(result, 10.seconds) === Some(new Account(1, "nameaccount", "nameclient", "request", 1))
    }

    "update Item" in {
      accounts.update(any) returns scala.concurrent.Future.successful(1)
      val result = accountService.updateItem(new Account(1, "nameaccount", "nameclient", "request", 1))
      Await.result(result, 10.seconds) === 1
    }

    "list All Items" in {
      accounts.listAll returns scala.concurrent.Future.successful(null)
      val result = accountService.listAllItems
      Await.result(result, 10.seconds) === null
    }

    "add Item" in {
      accounts.add(any) returns scala.concurrent.Future.successful("addResponse")
      val result = accountService.addItem(new Account(1, "nameaccount", "nameclient", "request", 1))
      Await.result(result, 10.seconds) === "replaceMeWithExpectedResult"
    }

    "delete Item" in {
      accounts.delete(anyInt) returns scala.concurrent.Future.successful(1)
      val result = accountService.deleteItem(1)
      Await.result(result, 10.seconds) === 1
    }
  }
}