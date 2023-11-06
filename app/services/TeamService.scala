package services

import com.google.inject.Inject
import models.{Team, TeamList}

import scala.concurrent.Future

class TeamService@Inject() (items: TeamList) {

  def addItem(item: Team): Future[String] = {
    items.add(item)
  }

  def deleteItem(account: Int, person: Int): Future[Int] = {
    items.delete(account, person)
  }

  def updateItem(item: Team): Future[Int] = {
    items.update(item)
  }

  def getItemByAccount(account: Int): Future[Option[Team]] = {
    items.getByAccount(account)
  }

  def getItemByPerson(person: Int): Future[Option[Team]] = {
    items.getByPerson(person)
  }

  def listAllItems: Future[Seq[Team]] = {
    items.listAll
  }
}