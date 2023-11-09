package models

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global

class UserListSpec extends Specification with Mockito {
  isolated
  val dbConfigProvider: DatabaseConfigProvider = mock[DatabaseConfigProvider]
  val userList = new UserList(dbConfigProvider)

  "UserList" should {

    "add" in {
      val result = userList.add(new User(1, "username", "password", 1))
      Await.result(result, 10.seconds) === "replaceMeWithExpectedResult"
    }

    "update" in {
      val result = userList.update(new User(1, "username", "password", 1))
      Await.result(result, 10.seconds) === 1
    }

    "login" in {
      val result = userList.login("userName", "password")
      Await.result(result, 10.seconds) === Some(new User(1, "username", "password", 1))
    }

    "delete" in {
      val result = userList.delete(1)
      Await.result(result, 10.seconds) === 1
    }

    "get" in {
      val result = userList.get(1)
      Await.result(result, 10.seconds) === Some(new User(1, "username", "password", 1))
    }

    "list All" in {
      val result = userList.listAll
      Await.result(result, 10.seconds) === null
    }
  }
}