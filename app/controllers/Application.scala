package controllers

import play.mvc.Result
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.Play.current
import views._
import com.redis._

object Application extends Controller {
  //define AWS URL and Redis port
  val url = "ec2-52-36-178-239.us-west-2.compute.amazonaws.com";
  val red_port = 6379
  //create connection to redis 
  val red = new RedisClient(url,red_port);
  /**
   * Describes the hello form.
   */

  val helloForm = Form(
    tuple(
      "url" -> nonEmptyText,
      "key" -> nonEmptyText
    )
  )

  /**
   * Home page
   */
  def index = Action {
    Ok(html.index(helloForm));
  }

  def func (n: String, m: String)= {
    //m is key mapping to url n
    red.set(m,n)
    Ok("This is ur new longened URL: " + "ec2-52-36-178-239.us-west-2.compute.amazonaws.com:9000/" + m);
  }
  def convString(n: String) = n

  /**
   * Handles the form submission.
   */
  def sayHello = Action { implicit request =>
    helloForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.index(formWithErrors)),
      {case (url,key) => func(url,key)}
    )
  }

  def redirect(path: String) = Action {
    // val url = red.get(path) map 
    red.get(path) match{
      case None => Ok("not found")
      case x => Redirect(red.get(path).getOrElse("Nothing"))
    }
  }

}