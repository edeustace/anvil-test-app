package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {

    import play.api.Logger
    import play.api.libs.json._
    import play.api.libs.functional.syntax._

    implicit val personFormat : Format[Person] = Json.format[Person]

    println(personFormat)
     val json = """
      {
        "name" : "Ed",
        "age" : 100,
        "lovesChocolate" : false,
        "phone" :
          { "number" : "1", "phoneType" : "home"}

      }
     """

    //this format will be implicitly used by the following from/toJson functions
    val person:JsResult[Person] = Json.fromJson(Json.parse(json)) //JsResult is like JsSucces/JsError

     Logger.info(person.toString)
    person match {
      case JsSuccess(p,_) => {
        Logger.info(p.toString)
        Ok(Json.toJson(p))
      }
      case _ => Ok("")
    }
  }

}