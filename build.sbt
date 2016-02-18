name := """gap-year-arg"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtWeb)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "com.typesafe.play" %% "play-mailer" % "2.4.0"
)

includeFilter in (Assets, LessKeys.less) := "*.less"

LessKeys.compress in Assets := true
