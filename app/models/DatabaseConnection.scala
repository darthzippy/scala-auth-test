package models.slick

import com.typesafe.config.ConfigFactory

object DatabaseConnection {
  import scala.slick.driver.PostgresDriver.simple._

  implicit lazy val database = ConfigFactory.load("conf/properties.conf").getConfig("db.default") match { case config =>
    Database.forURL(
      config.getString("url"),
      config.getString("user"),
      config.getString("password"),
      driver = config.getString("driver")
    )
  }
}