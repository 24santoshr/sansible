val scalaV = "2.11.8"

val monocleV = "1.1.0"

scalaVersion := scalaV

lazy val root =
  (project in file(".")).
    aggregate(macros, ansible)

lazy val commonSettings = Seq(
  scalaVersion := scalaV,
  scalacOptions += "-deprecation",
  organization := "com.citycontext",
  libraryDependencies ++= Seq(
    "io.argonaut" %% "argonaut" % "6.1",
    "com.github.julien-truffaut"  %%  "monocle-core" %  monocleV,
    "com.github.julien-truffaut"  %%  "monocle-macro" %  monocleV,
    "org.scalatest" %% "scalatest" % "2.2.6" % "test"
  ),
  addCompilerPlugin(
    "org.scalamacros" % "paradise_2.11.7" % "2.1.0"
  ),
  releasePublishArtifactsAction := PgpKeys.publishSigned.value
//  , publishArtifact in (Compile, packageDoc) := false,
//  publishArtifact in packageDoc := false,
//  sources in (Compile,doc) := Seq.empty,
)

lazy val macros = (project in file("macros")).
  settings(commonSettings: _*)

lazy val ansible = (project in file("ansible")).
  settings(commonSettings: _*).
  dependsOn(macros)
