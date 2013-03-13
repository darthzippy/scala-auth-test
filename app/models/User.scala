package models

import org.mindrot.jbcrypt.BCrypt
import scalikejdbc._
import scalikejdbc.SQLInterpolation._

case class User(
                id:Int,
                login:String,
                email:String,
                password:String,
                first_name:String,
                last_name:String,
                organization:String,
                permission:Permission)

object User {
  Class.forName("org.postgresql.Driver")
  ConnectionPool.singleton("jdbc:postgresql://stage.db.wieck/nytsyn", "wieck", "")

  val * = { rs: WrappedResultSet =>
    User(
      id            = rs.int("id"),
      login         = rs.string("login"),
      email         = rs.string("email"),
      password      = rs.string("password"),
      first_name    = rs.string("first_name"),
      last_name     = rs.string("last_name"),
      organization  = rs.string("organization"),
      permission    = Permission.valueOf("Administrator")
    )
  }

  def findAll: Seq[User] = {
    DB localTx { implicit s =>
      sql"SELECT * FROM users".map(*).list.apply()
    }
  }

  def authenticate(login: String, password: String): Option[User] = {
    findByLogin(login).filter { user => BCrypt.checkpw(password, BCrypt.hashpw(user.password, BCrypt.gensalt())) }
  }

  def findByLogin(login: String): Option[User] = {
    DB localTx { implicit s =>
      sql"SELECT id, login, email, password, first_name, last_name, organization FROM users WHERE login = ${login}".map(*).single.apply()
    }
  }

  def findById(id: Int): Option[User] = {
    DB localTx { implicit s =>
      sql"SELECT * FROM users WHERE id = ${id}".map(*).single.apply()
    }
  }
}
