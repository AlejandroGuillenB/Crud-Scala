package services

import models.{Account, AccountList}
import org.mockito.Mockito.{doReturn, mock, when}
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._



class AccountServiceSpec extends PlaySpec {

  "AccountService" should {
    "Add new account" in {
      val data = Account(id = 0,
        nameaccount = "Test",
        nameclient = "Cidenet",
        request = "any",
        liable = 234567
      )
      val mockAccountList = mock[AccountList]
      val accountService: AccountService = new AccountService(mockAccountList)
      doReturn(200).when(accountService).addItem(data)

      val result = accountService.addItem(data)

      result.mustBe(OK)
    }
  }
}
