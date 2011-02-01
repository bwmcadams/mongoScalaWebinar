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


import org.scala_tools.time.Imports._

object LiftRecordDemo extends Application {
  // We'll use enums for Type and Subtype
  object EventType extends Enumeration {
    type EventType = Value
    val Conference, Webinar = Value
  }

  object EventSubType extends Enumeration {
    type EventSubType = Value
    val FullDay = Value("Full Day")
    val HalfDay = Value("Half Day")
  }

  

  class MongoEvent extends MongoRecord[MongoEvent] with MongoId[MongoEvent] {
    def meta = MongoEvent

    object name extends StringField(this, 255)
    object eventType extends EnumField(this, EventType) 
    object eventSubType extends OptionalEnumField(this, EventSubType)
    object location extends JsonObjectField[MongoEvent, EventLocation](this, EventLocation)  {
      def defaultValue = EventLocation(None, None, None, None, None, None, None)
    }

    object hashtag extends OptionalStringField(this, 32)
    object language extends OptionalStringField(this, 32)
    object date extends JsonObjectField[MongoEvent, EventDate](this, EventDate) {
      def defaultValue = EventDate(new DateTime, None)
    }

    object url extends OptionalStringField(this, 255)
    object presenter extends OptionalStringField(this, 255)

  }

  object MongoEvent extends MongoEvent with MongoMetaRecord[MongoEvent] {
    override def collectionName = "mongoEvents"
    override def formats = super.formats + new EnumSerializer(EventType) + new EnumSerializer(EventSubType)
  }


  case class EventLocation(val venueName: Option[String], val url: Option[String], val address: Option[String], val city: Option[String], val state: Option[String], val zip: Option[String], val country: Option[String]) extends JsonObject[EventLocation] {
    def meta = EventLocation
  }

  object EventLocation extends JsonObjectMeta[EventLocation]

  case class EventDate(start: DateTime, end: Option[DateTime]) extends JsonObject[EventDate] {
    def meta = EventDate
  }

  object EventDate extends JsonObjectMeta[EventDate]
}

// vim: set ts=2 sw=2 sts=2 et:
