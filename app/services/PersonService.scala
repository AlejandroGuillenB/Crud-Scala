package services

import com.google.inject.Inject
import models.{Person, PersonList}

import scala.concurrent.Future

class PersonService @Inject() (items: PersonList){

  def addItem(item: Person): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Int): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Person): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Int): Future[Option[Person]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Person]] = {
    items.listAll
  }
}
