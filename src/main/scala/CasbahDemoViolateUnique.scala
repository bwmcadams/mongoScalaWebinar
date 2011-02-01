package com.mongodb.demo;
package webinar;
package scala;

// Imports Commons, Core, and the Query DSL
import com.mongodb.casbah.Imports._

// Tools to let us use Joda instead of JDK Dates,
import com.mongodb.casbah.commons.conversions.scala._
import org.scala_tools.time.Imports._

object CasbahDemoViolateUnique extends Application {
  import CasbahDemo._

  // Do it with a WriteConcern instead.

  mongo.insert(mongoChina, WriteConcern.Safe)

}



// vim: set ts=2 sw=2 sts=2 et:
