package controllers

import org.specs2.mutable.Specification
import play.api.test.Helpers._
import play.mvc.Http.Status
import play.api.test.FakeRequest
import play.api.libs.json.Json

class ApplicationSpec extends Specification {

  "Application" should {
    "return some products" in {
      val response = controllers.Application.products()(FakeRequest())

      status(response) must be equalTo(Status.OK)
      contentAsString(response) must contain("{\"products\":[")
    }
  }
}
