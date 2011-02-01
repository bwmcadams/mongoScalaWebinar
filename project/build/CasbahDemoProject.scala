import sbt._

class WebinarProject(info: ProjectInfo) extends DefaultProject(info) {
  // Casbah Deps
  val casbah = "com.mongodb.casbah" %% "casbah" % "2.0.3"

  // Lift Deps
  /* Lift-Record is the ORM */
  val lift_mongo_record = "net.liftweb" %% "lift-mongodb-record" % "2.2"

  /* Foursquare's "rogue" */
  val rogue = "com.foursquare" % "rogue_2.8.0" % "1.0.3"
  
}

// vim: set ts=2 sw=2 sts=2 et:
