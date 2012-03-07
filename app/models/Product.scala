package models

import play.api.libs.json._


case class Product(id: Long, name: String) {
  implicit object ProductFormat extends Format[Product] {
    def reads(json: JsValue) = Product((json\"id").as[Long], (json\"name").as[String])
    def writes(p: Product) = JsObject(List("id"->JsInteger(p.id), "name"->JsString(p.name)))
  }
}

object ProductFormat extends Format[Product] {
  def reads(json: JsValue) = Product((json\"id").as[Long], (json\"name").as[String])
  def writes(p: Product) = JsObject(List("id"->JsInteger(p.id), "name"->JsString(p.name)))
}

object Product {
  def all() = ProductStore.store.map(_._2)
}

object ProductStore {
  import scala.collection.mutable.{Map => MMap}
  val store: MMap[Long, Product] = MMap()
  
  def init() {
    store(1) = Product(1, "Olive Oil")
    store(2) = Product(2, "Extra Vierge")
  }
}