package services

import com.google.inject.Inject
import models.{Account, AccountList}

import scala.concurrent.Future

class AccountService @Inject() (items: AccountList) {

  def addItem(item: Account): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Int): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Account): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Int): Future[Option[Account]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Account]] = {
    items.listAll
  }
}
