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

import java.util.Date

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

  for (x <- Event where (_.eventType eqs EventType.Webinar)) println(x)



}



// vim: set ts=2 sw=2 sts=2 et:
