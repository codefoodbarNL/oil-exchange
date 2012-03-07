package models

import play.api.libs.json._


case class Product(name: String, description: String, email: String) {
  implicit object ProductFormat extends Format[Product] {
    def reads(json: JsValue) = Product((json\"name").as[String], (json\"description").as[String], (json\"email").as[String])
    def writes(p: Product) = JsObject(List("name"->JsString(p.name), "description"->JsString(p.description), "email"->JsString(p.email)))
  }
}

object ProductFormat extends Format[Product] {
  def reads(json: JsValue) = Product((json\"name").as[String], (json\"description").as[String], (json\"email").as[String])
  def writes(p: Product) = JsObject(List("name"->JsString(p.name), "description"->JsString(p.description), "email"->JsString(p.email)))
}

object Product {
  def all() = ProductStore.store.map(_._2)

  def put(p: Product) {
    ProductStore.store(p.name) = p
  }
}

object ProductStore {
  import scala.collection.mutable.{Map => MMap}
  val store: MMap[String, Product] = MMap()
  
  def init() {
    store("Olive Oil") = Product("Olive Oil", "Standard product", "john@doe.org")
    store("Extra Vierge") = Product("Extra Vierge", "Nice taste, goes well with red meat", "mario@pizza.nl")
  }
}