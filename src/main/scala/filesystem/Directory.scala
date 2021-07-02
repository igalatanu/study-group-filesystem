package filesystem

abstract class Directory extends Item {
  val name: String
  val content: List[Item]

  def addItem(item: Item): Directory
}

case class Root(val content: List[Item] = List.empty) extends Directory {
  override def hasParent(): Boolean = false

  override val name: String = ""
  override val parent: Option[Directory] = None

  override def getPath(): String = name

  override def addItem(item: Item): Root = {
    item match {
      case _: ChildDirectory => new Root(item :: content)
      case _ => ???
    }
  }
}

object Root extends Root(List.empty)

case class ChildDirectory(val name: String, val parentDir: Directory, val content: List[Item] = List.empty) extends Directory {
  override def hasParent(): Boolean = true

  override val parent: Option[Directory] = Some(parentDir)

  override def getPath(): String = parent.get.getPath() + "/" + name

  override def addItem(item: Item): ChildDirectory = {
    item match {
      case c: ChildDirectory => new ChildDirectory(name, parentDir, item :: this.content)
      case _ => ???
    }
  }

  /*def checkIfDirectoryHierarchyExists(List[ChildDirectory]) {

  }*/
}