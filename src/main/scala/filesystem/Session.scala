package filesystem

import filesystem.Errors.NuIncepeCuP

sealed trait Operation{
  def execute(s: Session): Session
}

object PrintWorkingDir extends Operation {
  override def execute(s: Session): Session = {
    if (s.workingDir == Root) println("/")
    else println(s.workingDir.getPath())
    s
  }
}

case class MkDir(dirName: String, path: Option[String] = None) extends Operation {
  def execute(s: Session): Session = {
    // if path is not empty, take the parent from @path
    // if not, the parent is s.workingDir
    path match {
      case None =>
        val dirToCreate = new ChildDirectory(dirName, s.workingDir)
        val newSess = s.workingDir.addItem(dirToCreate)
        Session(newSess)
      case Some(p) =>
        // path-ul should exist
        // if not, throw smth
        // else create @dirName
        if(!p.startsWith("/")) throw NuIncepeCuP

    }
  }
}

case class Session(workingDir: Directory = Root) {

}

object Errors {
  object NuIncepeCuP extends IllegalArgumentException {
    override val getMessage = "Nu incepe cu /"
  }
}