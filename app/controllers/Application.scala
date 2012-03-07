package controllers

import play.api.mvc._
import models.{ProductFormat, ProductStore, Product}

object Application extends Controller {
  ProductStore.init()

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  def products = Action {
    import play.api.libs.json._
    val json: Seq[JsValue] = Product.all().map(Json.toJson(_)(ProductFormat)).toSeq
    Ok(Json.toJson(JsObject(List("products"->JsArray(json)))))
  }

  def newProduct = TODO
}