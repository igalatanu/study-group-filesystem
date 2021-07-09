package filesystem

object Main extends App{
  val root = new Root()
  val aDirectory = new ChildDirectory("beautiful", root)
  val simpleFile = new File("virus.txt", "sszx", aDirectory)
  println(simpleFile.getPath())
  println(aDirectory.getPath())

  val defaultSession = Session(aDirectory)
  val currentWorkingDir = PrintWorkingDir.execute(defaultSession)
  println(currentWorkingDir)

  val newSession1 = MkDir("aMkdiredDirectory", None).execute(defaultSession)
  println(newSession1.workingDir.content)

//  val newSession2 = MkDir("aMkdiredDirectory", Some("")).execute(defaultSession)
//  println(newSession2.workingDir.content)

/*
/beatiful <-
  /newDirectory
  /anotherNewDirectory
  */
 val aDirectory2 = aDirectory.mkDir("newDirectory")
  val aDirectory3 = aDirectory.mkDir("newDirectory").mkDir("anotherNewDirectory")

  aDirectory2.ls
  println("""-----------""")
  aDirectory3.ls
  println("""-----------""")
  val anoterNewDirectory = aDirectory3.content.filter(_.name == "anotherNewDirectory").head
  anoterNewDirectory.parent.get.ls

  println(aDirectory
    .mkDir("newDirectory")
    .mkDir("anotherNewDirectory"))
}
