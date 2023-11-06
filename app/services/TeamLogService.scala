package services

import com.google.inject.Inject
import models.{TeamLog, TeamLogList}

import java.sql.Date
import scala.concurrent.Future

class TeamLogService @Inject()(items: TeamLogList) {

  def getItemByAccount(account: String): Future[Seq[TeamLog]] = {
    items.getByAccount(account)
  }

  def getItemByPerson(person: String): Future[Seq[TeamLog]] = {
    items.getByPerson(person)
  }

  def getItemByDateInit(dateInit: Date): Future[Seq[TeamLog]] = {
    items.getByDateInit(dateInit)
  }

  def getItemByDateEnd(dateEnd: Date): Future[Seq[TeamLog]] = {
    items.getByDateEnd(dateEnd)
  }

  def listAllItems: Future[Seq[TeamLog]] = {
    items.listAll
  }
}
