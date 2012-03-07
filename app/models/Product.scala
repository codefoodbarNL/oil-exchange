package models

import play.api.libs.json._


case class Product(name: String, description: String) {
  implicit object ProductFormat extends Format[Product] {
    def reads(json: JsValue) = Product((json\"name").as[String], (json\"description").as[String])
    def writes(p: Product) = JsObject(List("name"->JsString(p.name), "description"->JsString(p.description)))
  }
}

object ProductFormat extends Format[Product] {
  def reads(json: JsValue) = Product((json\"name").as[String], (json\"description").as[String])
  def writes(p: Product) = JsObject(List("name"->JsString(p.name), "description"->JsString(p.description)))
}

object Product {
  def all() = ProductStore.store.map(_._2)

  def put(p: Product, amount: Int) {
    ProductStore.store(p.name) = (p, amount)
  }
}

object ProductStore {
  import scala.collection.mutable.{Map => MMap}
  val store: MMap[String, (Product, Int)] = MMap()
  
  def init() {
    store("Olive Oil") = (Product("Olive Oil", "Standard product"), 10)
    store("Extra Vierge") = (Product("Extra Vierge", "Nice taste, goes well with red meat"), 2)
  }
}