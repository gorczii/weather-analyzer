name := "WeatherAnalyzer"

version := "0.1"

scalaVersion := "2.12.8"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "2.4.1",
  "io.spray" %% "spray-json" % "1.3.5",
  "org.projectlombok" % "lombok" % "1.16.18" % "provided",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.mockito" % "mockito-core" % "2.7.19" % Test
)