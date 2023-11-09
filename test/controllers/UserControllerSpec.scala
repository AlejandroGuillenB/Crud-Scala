package controllers

import models.User
import play.api.mvc.ControllerComponents
import services.UserService

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito

class UserControllerSpec extends Specification with Mockito {
  isolated
  val cc: ControllerComponents = mock[ControllerComponents]
  val userService: UserService = mock[UserService]
  val userController = new UserController(cc, userService)

  "UserController" should {

    "add" in {
      userService.addItem(any) returns scala.concurrent.Future.successful("addItemResponse")
      val result = userController.add()
      result === null
    }

    "update" in {
      userService.updateItem(any) returns scala.concurrent.Future.successful(1)
      val result = userController.update(1)
      result === null
    }

    "get By Id" in {
      userService.getItem(anyInt) returns scala.concurrent.Future.successful(Some(new User(1, "username", "password", 1)))
      val result = userController.getById(1)
      result === null
    }

    "login" in {
      userService.login(anyString, anyString) returns scala.concurrent.Future.successful(Some(new User(1, "username", "password", 1)))
      val result = userController.login("userName", "password")
      result === null
    }

    "delete" in {
      userService.deleteItem(anyInt) returns scala.concurrent.Future.successful(1)
      val result = userController.delete(1)
      result === null
    }

    "get All" in {
      userService.listAllItems returns scala.concurrent.Future.successful(null)
      val result = userController.getAll()
      result === null
    }
  }
}