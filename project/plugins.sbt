// Note: This is an example plugins.sbt for a Play project

//// Comment to get more information during initialization
//logLevel := Level.Warn

//// The Typesafe repository
//resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

//// Use the Play sbt plugin for Play projects
//addSbtPlugin("play" % "sbt-plugin" % "2.1.1")

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.4.0")

addSbtPlugin("com.github.scct" % "sbt-scct" % "0.2")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.3.1")

