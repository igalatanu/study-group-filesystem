package filesystem


abstract class Item {
  val name: String
  val parent: Option[Directory]

  def hasParent(): Boolean
  def getPath(): String
}


//class Child(override val name: String, override val parent: Option[Directory]) extends Item {
//  override def hasParent(): Boolean = true
//}