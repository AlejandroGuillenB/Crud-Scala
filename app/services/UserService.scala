package services

import com.google.inject.Inject
import models.{User, UserList}

import scala.concurrent.Future

class UserService @Inject() (items: UserList){

  def addItem(item: User): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Int): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: User): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Int): Future[Option[User]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[User]] = {
    items.listAll
  }
}
