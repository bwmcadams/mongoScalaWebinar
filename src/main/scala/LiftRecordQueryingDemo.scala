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

import java.util.Date

object LiftRecordQueryingDemo extends Application {
  import LiftRecordDemo._
  /**
   * I don't believe Lift plays nice with Joda, so dereg it
   */
  DeregisterJodaTimeConversionHelpers()
  
  // Tell Lift about our DB
  val mongoAddr = MongoAddress(MongoHost("127.0.0.1", 27017), "scalaWebinar")

  MongoDB.defineDb(DefaultMongoIdentifier, mongoAddr)


  // Fetch all events
  println("Pulling out all events from '%s'\n\n".format(MongoEvent))
  
  for (event <- MongoEvent.findAll) println("Mongo Event: %s\n\n".format(event)) 

  // Lift has no builtin querying for Mongo, must use Mongo queries

  // Unfortunately, limited demo of querying due to Lift's poor
  // embedded document support

  println("All events with a hashtag of #mongophilly\n\n")
  for (x <- MongoEvent.findAll(
        MongoEvent.hashtag.name -> "#mongophilly"
  )) println(x)

  // Rogue gives us a better approach: Type safety and a cleaner API


}

// vim: set ts=2 sw=2 sts=2 et:
