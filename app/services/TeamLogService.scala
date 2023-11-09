package services

import io.swagger.annotations.{Api, ApiOperation}
import javax.inject.Inject
import models.{TeamLog, TeamLogList}
import java.sql.Date
import scala.concurrent.Future

@Api(value = "/teamlog")
class TeamLogService @Inject()(teamLogs: TeamLogList) {

  @ApiOperation(
    value = "Get log by account",
    notes = "Return logs by account",
    response = classOf[models.TeamLog],
    httpMethod = "GET"
  )
  def getItemByAccount(account: String): Future[Seq[TeamLog]] = {
    teamLogs.getByAccount(account)
  }

  @ApiOperation(
    value = "Get log by person",
    notes = "Return logs by person",
    response = classOf[models.TeamLog],
    httpMethod = "GET"
  )
  def getItemByPerson(person: String): Future[Seq[TeamLog]] = {
    teamLogs.getByPerson(person)
  }

  @ApiOperation(
    value = "Get log by date init",
    notes = "Return logs by date init",
    response = classOf[models.TeamLog],
    httpMethod = "GET"
  )
  def getItemByDateInit(dateInit: Date): Future[Seq[TeamLog]] = {
    teamLogs.getByDateInit(dateInit)
  }

  @ApiOperation(
    value = "Get logs by date end",
    notes = "Return logs by date end",
    response = classOf[models.TeamLog],
    httpMethod = "GET"
  )
  def getItemByDateEnd(dateEnd: Date): Future[Seq[TeamLog]] = {
    teamLogs.getByDateEnd(dateEnd)
  }

  @ApiOperation(
    value = "List all logs",
    notes = "Return a list with all logs",
    response = classOf[models.TeamLog],
    httpMethod = "GET"
  )
  def listAllItems: Future[Seq[TeamLog]] = {
    teamLogs.listAll
  }
}
