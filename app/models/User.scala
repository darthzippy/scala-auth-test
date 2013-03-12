package models

import org.mindrot.jbcrypt.BCrypt
import scalikejdbc._
import scalikejdbc.SQLInterpolation._

case class User(
                id:Int,
                email:String,
                password:String,
                first_name:String,
                last_name:String,
                organization:String)

object User {
  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton("jdbc:postgresql://stage.db.wieck/nytsyn", "wieck", "")

  val * = { rs: WrappedResultSet =>
    User(
      id            = rs.int("id"),
      email         = rs.string("email"),
      password      = rs.string("password"),
      first_name     = rs.string("first_name"),
      last_name      = rs.string("last_name"),
      organization  = rs.string("organization")
    )
  }

  def findAll: Seq[User] = {
    DB localTx { implicit s =>
      sql"SELECT * FROM users".map(*).list.apply()
    }
  }
}
