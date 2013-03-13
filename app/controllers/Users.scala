package controllers

import play.api.data._
import play.api.data.Forms._
import play.api.templates._
import models._
import views._
import play.api.mvc._
import play.api.mvc.Results._
import jp.t2v.lab.play2.auth._
import play.api.Play._
import play.api.cache.Cache
import reflect.classTag
import jp.t2v.lab.play2.stackc.{RequestWithAttributes, RequestAttributeKey, StackableController}

object Users extends Controller with AuthElement with AuthConfigImpl {

  def index = Action {
    Ok(views.html.users.index("Nytsyn - Users", User.findAll))
  }

  def show(id: Int) = Action {
    Ok(views.html.users.show("Nytsyn - Users", User.findById(id)))
  }
}