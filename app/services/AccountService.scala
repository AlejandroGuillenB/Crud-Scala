package services

import com.google.inject.Inject
import io.swagger.annotations.{Api, ApiOperation}
import models.{Account, AccountList}

import scala.concurrent.Future

@Api(value = "/account")
class AccountService @Inject()(accounts: AccountList) {
  @ApiOperation(
    value = "Add account",
    notes = "Add a new account",
    response = classOf[models.Account],
    httpMethod = "POST"
  )
  def addItem(account: Account): Future[String] = {
    accounts.add(account)
  }

  @ApiOperation(
    value = "Delete account",
    notes = "Delete a account by id",
    response = classOf[models.Account],
    httpMethod = "DELETE"
  )
  def deleteItem(id: Int): Future[Int] = {
    accounts.delete(id)
  }

  @ApiOperation(
    value = "Update account",
    notes = "Update a account",
    response = classOf[models.Account],
    httpMethod = "PUT"
  )
  def updateItem(account: Account): Future[Int] = {
    accounts.update(account)
  }

  @ApiOperation(
    value = "Get account by id",
    notes = "Return a account by id",
    response = classOf[models.Account],
    httpMethod = "GET"
  )
  def getItem(id: Int): Future[Option[Account]] = {
    accounts.get(id)
  }

  @ApiOperation(
    value = "List all accounts",
    notes = "Return a list with all accounts",
    response = classOf[models.Account],
    httpMethod = "GET"
  )
  def listAllItems: Future[Seq[Account]] = {
    accounts.listAll
  }
}
