package filesystem

abstract class Directory extends Item {
  val name: String
  val content: List[Item]

  def addItem(item: Item): Directory

  def ls: Unit = content.foreach(i => println(i.name))
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

  /* 1.
  root + currentDir

  cd
  currenDir = root.getDir(path)

  mkDir => root = root.mkdir(path)

  List(1,2,3,4).foldRight(z: A)((a: Int, z: A) => A)


   */

  override def addItem(item: Item): ChildDirectory = {
    item match {
      case c: ChildDirectory =>
        parentDir.replace(path, newChild)
        ChildDirectory(name, parentDir, item :: this.content)
      case _ => ???
    }
  }

  def getRoot(): Root = ???

  def mkDir(name: String): ChildDirectory = {
    addItem(ChildDirectory(name, this))
  }

  /*def checkIfDirectoryHierarchyExists(List[ChildDirectory]) {

  }*/
}