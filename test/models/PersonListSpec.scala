package models

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global

class PersonListSpec extends Specification with Mockito {
  isolated
  val dbConfigProvider: DatabaseConfigProvider = mock[DatabaseConfigProvider]
  val personList = new PersonList(dbConfigProvider)

  "PersonList" should {

    "add" in {
      val result = personList.add(new Person(1, "name", "test@email.com", "A1", "Scala", "https://www.any-url.test/cv"))
      Await.result(result, 10.seconds) === "replaceMeWithExpectedResult"
    }

    "update" in {
      val result = personList.update(new Person(1, "name", "test@email.com", "A1", "Scala", "https://www.any-url.test/cv"))
      Await.result(result, 10.seconds) === 1
    }

    "delete" in {
      val result = personList.delete(1)
      Await.result(result, 10.seconds) === 1
    }

    "get" in {
      val result = personList.get(1)
      Await.result(result, 10.seconds) === Some(new Person(1, "name", "test@email.com", "A1", "Scala", "https://www.any-url.test/cv"))
    }

    "list All" in {
      val result = personList.listAll
      Await.result(result, 10.seconds) === null
    }
  }
}