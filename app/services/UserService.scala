package services

import com.google.inject.Inject
import io.swagger.annotations.{Api, ApiOperation}
import models.{User, UserList}

import scala.concurrent.Future

@Api(value = "/user")
class UserService @Inject() (users: UserList){
  @ApiOperation(
    value = "Add user",
    notes = "Add a new user",
    response = classOf[models.User],
    httpMethod = "POST"
  )
  def addItem(user: User): Future[String] = {
    users.add(user)
  }

  @ApiOperation(
    value = "Delete user",
    notes = "Delete a user by id",
    response = classOf[models.User],
    httpMethod = "DELETE"
  )
  def deleteItem(id: Int): Future[Int] = {
    users.delete(id)
  }

  @ApiOperation(
    value = "Update user",
    notes = "Update a user",
    response = classOf[models.User],
    httpMethod = "PUT"
  )
  def updateItem(user: User): Future[Int] = {
    users.update(user)
  }

  @ApiOperation(
    value = "Get user by id",
    notes = "Return a user by id",
    response = classOf[models.User],
    httpMethod = "GET"
  )
  def getItem(id: Int): Future[Option[User]] = {
    users.get(id)
  }

  @ApiOperation(
    value = "List all users",
    notes = "Return a list with all users",
    response = classOf[models.User],
    httpMethod = "GET"
  )
  def listAllItems: Future[Seq[User]] = {
    users.listAll
  }

  @ApiOperation(
    value = "Login",
    notes = "Return a authenticate token",
    response = classOf[models.User],
    httpMethod = "GET"
  )
  def login(userName: String, password: String): Future[Option[User]] = {
    users.login(userName, password)
  }
}
