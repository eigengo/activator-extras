name := "activator-extras"

version := "1.0"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  "org.specs2"         %% "specs2"           % "1.14"      % "test"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)
