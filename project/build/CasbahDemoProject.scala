import sbt._

class TestProject(info: ProjectInfo) extends DefaultProject(info) {
  val casbah = "com.mongodb.casbah" %% "casbah" % "2.0.3"
}

// vim: set ts=2 sw=2 sts=2 et:
