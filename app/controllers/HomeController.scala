package controllers

import play.api.mvc._

import javax.inject._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Redirect("/swagger-ui/")
  }
}