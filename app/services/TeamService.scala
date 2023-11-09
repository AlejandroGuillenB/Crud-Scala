package services

import io.swagger.annotations.{Api, ApiOperation}

import javax.inject.Inject
import models.{Team, TeamList}

import scala.concurrent.Future

@Api(value = "/team")
class TeamService @Inject()(teams: TeamList) {

  @ApiOperation(
    value = "Add team",
    notes = "Add a new team",
    response = classOf[models.Team],
    httpMethod = "POST"
  )
  def addItem(team: Team): Future[String] = {
    teams.add(team)
  }

  @ApiOperation(
    value = "Delete team",
    notes = "Delete a team by account and person",
    response = classOf[models.Team],
    httpMethod = "DELETE"
  )
  def deleteItem(account: Int, person: Int): Future[Int] = {
    teams.delete(account, person)
  }

  @ApiOperation(
    value = "Update team",
    notes = "Update a team",
    response = classOf[models.Team],
    httpMethod = "PUT"
  )
  def updateItem(team: Team): Future[Int] = {
    teams.update(team)
  }

  @ApiOperation(
    value = "Get team by account",
    notes = "Return a team by account",
    response = classOf[models.Team],
    httpMethod = "GET"
  )
  def getItemByAccount(account: Int): Future[Option[Team]] = {
    teams.getByAccount(account)
  }

  @ApiOperation(
    value = "Get team by account",
    notes = "Return a team by person",
    response = classOf[models.Team],
    httpMethod = "GET"
  )
  def getItemByPerson(person: Int): Future[Option[Team]] = {
    teams.getByPerson(person)
  }

  @ApiOperation(
    value = "List all teams",
    notes = "Return a list with all teams",
    response = classOf[models.Team],
    httpMethod = "GET"
  )
  def listAllItems: Future[Seq[Team]] = {
    teams.listAll
  }
}