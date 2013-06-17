package com.typesafe.activator.extras.tutorial

import scala.tools.nsc.Settings
import scala.tools.nsc.reporters.ConsoleReporter

trait Tutorial {
  import scala.tools.nsc.Global

  val settings = new Settings()
  val reporter = new ConsoleReporter(settings)
  val compiler = new Global(settings, reporter)

  val src = "package foo; case class X();"
  val x = compiler.newUnitParser(src)
  val y = x.parse()

  println(y)

}