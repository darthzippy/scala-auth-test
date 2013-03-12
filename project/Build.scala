import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "AuthTest"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "jp.t2v" %% "play2.auth"      % "0.9",
    "jp.t2v" %% "play2.auth.test" % "0.9" % "test",
    "org.mindrot"          % "jbcrypt"                    % "0.3m",
    "com.github.seratch"  %% "scalikejdbc"                % "[1.4,)",
    "com.github.seratch"  %% "scalikejdbc-test"           % "[1.4,)",
    "com.github.seratch"  %% "scalikejdbc-play-plugin"    % "[1.4,)",
    "com.github.seratch"  %% "scalikejdbc-interpolation"  % "[1.4,)",
    "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test",
    "com.typesafe.slick" %% "slick" % "1.0.0",
    "postgresql" % "postgresql" % "9.1-901.jdbc4",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.typesafe" % "config" % "1.0.0"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
  )

}
