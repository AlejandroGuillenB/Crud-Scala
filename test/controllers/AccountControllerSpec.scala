package controllers

import models.Account
import play.api.mvc.ControllerComponents
import services.AccountService

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class AccountControllerSpec extends Specification with Mockito {
  isolated
  val cc: ControllerComponents = mock[ControllerComponents]
  val accountService: AccountService = mock[AccountService]
  val accountController = new AccountController(cc, accountService)

  "AccountController" should {

    "add" in {
      accountService.addItem(any) returns scala.concurrent.Future.successful("addItemResponse")
      val result = accountController.add()
      result === null
    }

    "update" in {
      accountService.updateItem(any) returns scala.concurrent.Future.successful(1)
      val result = accountController.update(1)
      result === null
    }

    "get By Id" in {
      accountService.getItem(anyInt) returns scala.concurrent.Future.successful(Some(new Account(1, "nameaccount", "nameclient", "request", 1)))
      val result = accountController.getById(1)
      result === null
    }

    "delete" in {
      accountService.deleteItem(anyInt) returns scala.concurrent.Future.successful(1)
      val result = accountController.delete(1)
      result === null
    }

    "get All" in {
      accountService.listAllItems returns scala.concurrent.Future.successful(null)
      val result = accountController.getAll()
      result === null
    }
  }
}
