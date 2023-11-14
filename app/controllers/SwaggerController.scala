package controllers

/*
import controllers.SwaggerBaseApiController
import io.swagger.models.auth.{ApiKeyAuthDefinition, In}
import play.api.mvc.{Action, AnyContent, Request, RequestHeader}

class SwaggerController (configuration: play.api.Configuration)
  extends SwaggerBaseApiController {
  def getResources = Action {
    request =>
      implicit val requestHeader: RequestHeader = request
      //Added to support host config [Useful for making api calls with Swagger]
      val host = request.queryString("host").head
    val resourceListing = getResourceListing(host)
    //Added for bearer Authorization
    resourceListing.addSecurityDefinition("bearer", new ApiKeyAuthDefinition("Authorization", In.HEADER))
    val responseStr = returnXml(request) match {
      case true => toXmlString(resourceListing)
      case false => toJsonString(resourceListing)
    }
    returnValue(request, responseStr)
  }
}*/
