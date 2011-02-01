package com.mongodb.demo;
package webinar;
package casbah;

// Imports Commons, Core, and the Query DSL
import com.mongodb.casbah.Imports._

// Tools to let us use Joda instead of JDK Dates,
import com.mongodb.casbah.commons.conversions.scala._
import org.scala_tools.time.Imports._

object CasbahQuerying extends Application {
  import CasbahDemo._

  // What about querying?  Lets find all the non-US events

  for (x <- mongo.find(MongoDBObject("location.country" -> 
                        MongoDBObject("$ne" -> "USA")))) println(x)

  /* There's a problem here: We got back the Webinars too because 
     They don't have a country at all, so they aren't "USA"
    */
  println("\n\nTesting for existence of Location.Country:")

  for (x <- mongo.find(MongoDBObject("location.country" -> MongoDBObject(
                       "$ne" -> "USA",
                       "$exists" -> true 
                      )))) println(x)
  
  // This is getting a bit unwieldy.  Thankfully, Casbah offers a DSL
  val q = "location.country" $exists true $ne "USA"

  println("\n Created a DBObject: %s".format(q))
  
  println("\n Querying using DSL Object...")

  for (x <- mongo.find(q)) println(x)

  // It's possible to construct more complex queries too.

  // Lets find everything in February

  println("\n February Events...")
  val start = new DateTime(2011, 2, 1, 0, 0, 0, 0)
  val end = new DateTime(2011, 3, 1, 0, 0, 0, 0)
  val dateQ = "date.start" $gte start $lt end 

  println("\n Date Query: %s".format(dateQ))

  for (x <- mongo.find(dateQ, MongoDBObject("name" -> true, "date" -> true))) println(x)

}

// vim: set ts=2 sw=2 sts=2 et:
