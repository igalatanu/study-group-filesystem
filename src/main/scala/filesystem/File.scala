package filesystem

class File(val name: String, val content: String, val parentDir: Directory) extends Item {
  override def hasParent(): Boolean = true
  override val parent = Some(parentDir)

  override def getPath(): String = parent.get.getPath() + "/" + name
}

