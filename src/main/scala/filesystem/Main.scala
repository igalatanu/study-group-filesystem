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

  val newSession = MkDir("aMkdiredDirectory", None).execute(defaultSession)
  println(newSession.workingDir.content)

  val newSession = MkDir("aMkdiredDirectory", Some()).execute(defaultSession)
  println(newSession.workingDir.content)
}
