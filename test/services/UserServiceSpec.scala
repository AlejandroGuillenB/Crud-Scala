package services

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import models.{User, UserList}

import scala.concurrent.duration._
import scala.concurrent.Await

class UserServiceSpec extends Specification with Mockito {
  isolated
  val users: UserList = mock[UserList]
  val userService = new UserService(users)

  "UserService" should {

    "get Item" in {
      users.get(anyInt) returns scala.concurrent.Future.successful(Some(new User(1, "username", "password", 1)))
      val result = userService.getItem(1)
      Await.result(result, 10.seconds) === Some(new User(1, "username", "password", 1))
    }

    "update Item" in {
      users.update(any) returns scala.concurrent.Future.successful(1)
      val result = userService.updateItem(new User(1, "username", "password", 1))
      Await.result(result, 10.seconds) === 1
    }

    "login" in {
      users.login(anyString, anyString) returns scala.concurrent.Future.successful(Some(new User(1, "username", "password", 1)))
      val result = userService.login("userName", "password")
      Await.result(result, 10.seconds) === Some(new User(1, "username", "password", 1))
    }

    "list All Items" in {
      users.listAll returns scala.concurrent.Future.successful(null)
      val result = userService.listAllItems
      Await.result(result, 10.seconds) === null
    }

    "add Item" in {
      users.add(any) returns scala.concurrent.Future.successful("addResponse")
      val result = userService.addItem(new User(1, "username", "password", 1))
      Await.result(result, 10.seconds) === "replaceMeWithExpectedResult"
    }

    "delete Item" in {
      users.delete(anyInt) returns scala.concurrent.Future.successful(1)
      val result = userService.deleteItem(1)
      Await.result(result, 10.seconds) === 1
    }
  }
}