import sbt._
import Keys._
import org.scalastyle.sbt._

// to sync this project with IntelliJ, run the sbt-idea plugin with: sbt gen-idea
//
// to set user-specific local properties, just create "~/.sbt/my-settings.sbt", e.g.
// javaOptions += "some cool stuff"
//
// This project allows a local.conf on the classpath (e.g. domain/src/main/resources) to override settings, e.g.
//
// test.db.mongo.hosts { "Sampo.home": 27017 }
// test.db.cassandra.hosts { "Sampo.home": 9160 }
// main.db.mongo.hosts = ${test.db.mongo.hosts}
// main.db.cassandra.hosts = ${test.db.cassandra.hosts}
//
// mkdir -p {domain,core,api,main,test}/src/{main,test}/{java,scala,resources}/org/eigengo/akkapatterns
//
// the following were useful for writing this file
// http://www.scala-sbt.org/release/docs/Getting-Started/Multi-Project.html
// https://github.com/sbt/sbt/blob/0.12.2/main/Build.scala
// https://github.com/akka/akka/blob/master/project/AkkaBuild.scala
object ActivatorExtrasBuild extends Build {

  override val settings = super.settings ++ Seq(
    organization := "com.typesafe.activator",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.10.2"
  )

  lazy val defaultSettings = Defaults.defaultSettings ++ Seq(
    scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.6", "-deprecation", "-unchecked", "-Ywarn-dead-code"),
    javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6", "-Xlint:unchecked", "-Xlint:deprecation", "-Xlint:-options"),
    // https://github.com/sbt/sbt/issues/702
    javaOptions += "-Djava.util.logging.config.file=logging.properties",
    javaOptions += "-Xmx2G",
    outputStrategy := Some(StdoutOutput),
    fork := true,
    maxErrors := 1,
    resolvers ++= Seq(
      Resolver.mavenLocal,
      Resolver.sonatypeRepo("releases"),
      Resolver.typesafeRepo("releases"),
      Resolver.typesafeRepo("snapshots"),
      Resolver.sonatypeRepo("snapshots")
    ),
    parallelExecution in Test := false
  ) ++ ScctPlugin.instrumentSettings ++ ScalastylePlugin.Settings

  def module(dir: String) = Project(id = dir, base = file(dir), settings = defaultSettings)
  import Dependencies._

  lazy val core = module("core") settings(
    libraryDependencies += scala_tools,
    libraryDependencies += specs2 % "test"
  )

  lazy val cli = module("cli") dependsOn (core) settings (
  )

  lazy val root = Project(id = "parent", base = file("."), settings = defaultSettings) settings (
    ScctPlugin.mergeReportSettings: _*
  ) aggregate (
    core, cli
  ) dependsOn (cli) // yuck
}

object Dependencies {
  // to help resolve transitive problems, type:
  //   `sbt dependency-graph`
  //   `sbt test:dependency-tree`
  val bad = Seq(
    ExclusionRule(name = "log4j"),
    ExclusionRule(name = "commons-logging"),
    ExclusionRule(name = "commons-collections"),
    ExclusionRule(organization = "org.slf4j")
  )

  val specs2      = "org.specs2"         %% "specs2"           % "1.14"
  val scala_tools = "org.scala-lang"      % "scala-compiler"   % "2.10.2"
}
