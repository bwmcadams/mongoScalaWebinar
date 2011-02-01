package com.mongodb.demo;
package webinar;
package scala;

// Imports Commons, Core, and the Query DSL
import com.mongodb.casbah.Imports._

// Tools to let us use Joda instead of JDK Dates,
import com.mongodb.casbah.commons.conversions.scala._

// Lift Stuff
import net.liftweb.common._
import net.liftweb.mongodb._
import net.liftweb.mongodb.record._
import net.liftweb.mongodb.record.field._
import net.liftweb.json.DefaultFormats
import net.liftweb.record.field._
import net.liftweb.json.ext.EnumSerializer
import net.liftweb.record.field.{EnumField, OptionalEnumField}
import net.liftweb.util.TimeHelpers._
import net.liftweb.util.Helpers._

// JSON DSL
import net.liftweb.json.JsonDSL._
import net.liftweb.json.JsonAST.JObject

// Rogue 
import com.foursquare.rogue.Rogue._

import org.scala_tools.time.Imports._

object RogueDemo extends Application {
  import LiftRecordDemo._
  /**
   * I don't believe Lift plays nice with Joda, so dereg it
   */
  DeregisterJodaTimeConversionHelpers()
  
  // Tell Lift about our DB
  val mongoAddr = MongoAddress(MongoHost("127.0.0.1", 27017), "scalaWebinar")

  MongoDB.defineDb(DefaultMongoIdentifier, mongoAddr)

  // Rogue gives us a saner approach, although still hobbled by some
  // of Lift-MongoDB-Record's limits on embedded docs

  val q = MongoEvent where (_.eventType eqs EventType.Webinar)

  println("Rogue created a Query '%s'\n\n".format(q))

  for (x <- MongoEvent where (_.eventType eqs EventType.Webinar)) {
    println("Name: %s Presenter: %s\n".format(x.name, x.presenter))
  }

  // Rogue can also do sorting for you, which is useful

  println("\n\n\n")

  for (x <- MongoEvent where (_.eventType eqs EventType.Conference) 
                       orderAsc(_.language) andDesc(_.name)) { 
    println("Name: %s Language: %s\n".format(x.name, x.language))
  }
  val start = new DateTime(2011, 2, 1, 0, 0, 0, 0)
  val end = new DateTime(2011, 3, 1, 0, 0, 0, 0)

    /** The following would be nice but unfortunately, 
      doesn't work because of lift's current embedded doc
      implementation
    */
  //val dateQ = MongoEvent where (_.date.start after start) 
                           //and (_.date.end before end)


  System.exit(0)

}



// vim: set ts=2 sw=2 sts=2 et:
