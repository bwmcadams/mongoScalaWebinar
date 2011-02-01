package com.mongodb.demo;
package webinar;
package casbah;

// Imports Commons, Core, and the Query DSL
import com.mongodb.casbah.Imports._

// Tools to let us use Joda instead of JDK Dates,
import com.mongodb.casbah.commons.conversions.scala._
import org.scala_tools.time.Imports._

object CasbahDemo extends Application {
  /**
   * By default we serialize/deserialize JDK Dates, this switches to using JodaTime
   */
  RegisterJodaTimeConversionHelpers()

  val conn = MongoConnection() // Connect to default host, default port (localhost:27017)

  val db = conn("scalaWebinar") // Connect to the "scalaWebinar" database, using apply()

  db.dropDatabase() // Drop the database so that we are sure we are starting clean
  // Because of lazy creation, no need to 'create' the db or collection... 

  val mongo = db("mongoEvents") // Fetch the "mongoEvents" collection using apply()

  /**
   * Lets build some Documents and insert them to the db 
   */

  // We provide a Scala 2.8 collections builder using "Pimp My Library"
  val atlBuilder = MongoDBObject.newBuilder
  atlBuilder += "name" -> "Mongo Atlanta"
  atlBuilder += "type" -> "Conference"
  atlBuilder += "subtype" -> "Full Day"
  atlBuilder += "location" -> MongoDBObject( // Create a subdocument using factory constructor
    "venueName" -> "GTRI Conference Center",
    "url" -> "http://www.gtri.gatech.edu/atlanta/conference-center",
    "address" -> "250 14th Street, NW",
    "city" -> "Atlanta",
    "state" -> "GA",
    "zip" -> "30318",
    "country" -> "USA"
  )
  atlBuilder += "hashtag" -> "#MongoATL"
  atlBuilder += "language" -> "English"
  atlBuilder += "date" -> MongoDBObject(
    "start" -> new DateTime(2011, 2, 9, 9, 0, 0, 0),
    "end"   -> new DateTime(2011, 2, 9, 18, 0, 0, 0)
  )

  val mongoAtl = atlBuilder.result // Fetch result from builder, whih returns a DBObject
  
  println("MongoATL: %s".format(mongoAtl)) // ToString of a DBObject is the JSON repr.

  mongo.insert(mongoAtl)

  val scalaWebinar = MongoDBObject(
    "name" -> "MongoDB and Scala",
    "type" -> "Webinar",
    "date" -> MongoDBObject(
      "start" -> new DateTime(2011, 2, 1, 13, 0, 0, 0)
    ),
    "presenter" -> "Brendan McAdams",
    "url" -> "https://10genevents.webex.com/10genevents/onstage/g.php?t=a&d=669724319"
  )

  mongo.insert(scalaWebinar) 

  val shardingWebCast = MongoDBObject(
    "name" -> "How Sharding Works",
    "type" -> "Webinar",
    "date" -> MongoDBObject(
      "start" -> new DateTime(2011, 2, 4, 13, 0, 0, 0)
    ),
    "presenter" -> "Kristina Chodorow",
    "url" -> "http://post.oreilly.com/form/oreilly/viewhtml/9z1zv8aae7cf9etmismqt53al4n63uirqpgrmvim5l0"
  )

  mongo.insert(shardingWebCast)

  val austBuilder = MongoDBObject.newBuilder
  austBuilder += "name" -> "Mongo Austin"
  austBuilder += "type" -> "Conference"
  austBuilder += "subtype" -> "Full Day"
  austBuilder += "location" -> MongoDBObject(
    "venueName" -> "Sheraton Austin",
    "url" -> "http://www.sheratonaustin.com/",
    "address" -> "701 East 11th Street",
    "city" -> "Austin",
    "state" -> "TX",
    "country" -> "USA"
  )
  austBuilder += "hashtag" -> "#mongoaustin"
  austBuilder += "language" -> "English"
  austBuilder += "date" -> MongoDBObject(
    "start" -> new DateTime(2011, 2, 15, 9, 0, 0, 0),
    "end" -> new DateTime(2011, 2, 15, 18, 0, 0, 0)
  )

  val mongoAustin = austBuilder.result

  mongo.insert(mongoAustin)


  val jpBuilder = MongoDBObject.newBuilder
  jpBuilder += "name" -> "Mongo Tokyo"
  jpBuilder += "type" -> "Conference"
  jpBuilder += "subtype" -> "Full Day"
  jpBuilder += "location" -> MongoDBObject(
    "venueName" -> "Shinagawa Seaside Rakuten Tower",
    "address" -> "4-12-3 Higashishinagawa, Shinagawa-ku",
    "city" -> "Tokyo",
    "country" -> "Japan"
  )
  jpBuilder += "hashtag" -> "#mongotokyo"
  jpBuilder += "language" -> "Japanese"
  jpBuilder += "date" -> MongoDBObject(
    "start" -> new DateTime(2011, 3, 1, 12, 0, 0, 0),
    "end" -> new DateTime(2011, 3, 1, 20, 0, 0, 0)
  )

  val mongoJapan = jpBuilder.result 
  
  mongo.insert(mongoJapan)

  val phlBuilder = MongoDBObject.newBuilder
  phlBuilder += "name" -> "Mongo Philly"
  phlBuilder += "type" -> "Conference"
  phlBuilder += "subtype" -> "Full Day"
  phlBuilder += "location" -> MongoDBObject(
    "venueName" -> "Sheraton Society Hill",
    "url" -> "http://www.starwoodhotels.com/sheraton/search/hotel_detail.html?propertyID=166",
    "address" -> "One Dock Street (2nd and Walnut Streets)",
    "city" -> "Philadelphia",
    "state" -> "PA",
    "zip" -> "19106",
    "country" -> "USA"
  )
  phlBuilder += "hashtag" -> "#mongophilly"
  phlBuilder += "language" -> "USA"
  phlBuilder += "date" -> MongoDBObject(
    "start" -> new DateTime(2011, 4, 26, 9, 0, 0, 0),
    "end" -> new DateTime(2011, 4, 26, 18, 0, 0, 0)
  )
  phlBuilder += "cheesesteaks" -> true

  val mongoPhilly = phlBuilder.result 
  
  mongo.insert(mongoPhilly)

  val cnBuilder = MongoDBObject.newBuilder
  cnBuilder += "name" -> "Mongo Beijing"
  cnBuilder += "type" -> "Conference"
  cnBuilder += "subtype" -> "Half Day"
  cnBuilder += "location" -> MongoDBObject(
    "venueName" -> "Park Plaza Beijing Science Park",
    "address" -> "25 Zichun Road, Haidan District ",
    "city" -> "Beijing",
    "country" -> "China"
  )
  cnBuilder += "language" -> "Chinese"
  cnBuilder += "date" -> MongoDBObject(
    "start" -> new DateTime(2011, 3, 3, 13, 0, 0, 0),
    "end" -> new DateTime(2011, 3, 3, 17, 15, 0, 0)
  )

  val mongoChina = cnBuilder.result 
  
  mongo.insert(mongoChina)


  // We now have a good base of objects with differing data and schemas
  // Lets create a unique index to look at Write Concerns (and indexes)

  mongo.ensureIndex(keys=MongoDBObject("name" -> true),
                    name="conferenceName", 
                    unique=true)

  // Now, inserting China again should fail...

  mongo.insert(mongoChina)

  println("If you're reading this, no exception was thrown on index violation....")

  // Wait ... Why didn't MongoDB Fail?!?

  // Do it with a WriteConcern instead.

  mongo.insert(mongoChina, WriteConcern.Safe)

}


// vim: set ts=2 sw=2 sts=2 et:
