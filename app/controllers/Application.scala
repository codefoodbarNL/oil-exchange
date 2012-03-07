package controllers

import play.api.mvc._
import models.{ProductFormat, ProductStore, Product}
import play.api.libs.json.Json

object Application extends Controller {
  ProductStore.init()

  def index = Action {
    Ok(views.html.index())
  }
  
  def products = Action {
    import play.api.libs.json._
    val json: Seq[JsValue] = Product.all().map(x => Json.toJson(x)(ProductFormat)).toSeq
    Ok(Json.toJson(JsObject(List("products"->JsArray(json)))))
  }

  def newProduct = Action { request =>
    print(request.body.asText)
    val product = Json.fromJson(request.body.asJson.get)(ProductFormat)
    Product.put(product)
    Redirect(routes.Application.products)
  }
}