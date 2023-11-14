package utils

import io.jsonwebtoken.{Claims, Jws, Jwts, SignatureAlgorithm}
import scala.collection.JavaConverters._
import java.time.Instant
import java.util.{Date, UUID}

object Jwt {
  val Ttl: Int = 3600
  val Secret: String = "DtAMS3o8zUOWOZplLzSzS6jjCBEzymvXu2UJYLDnUFxC64WBNYiMDVkqYJTPjBVK"

  def apply(claims: Map[String, Any]): String = {
    val jwt = Jwts.builder()
      .setId(UUID.randomUUID().toString)
      .setIssuedAt(Date.from(Instant.now()))
      .setExpiration(Date.from(Instant.now().plusSeconds(Ttl)))
      .signWith(SignatureAlgorithm.HS512, Secret.getBytes("UTF-8"))

    claims.foreach{ case (name, value) =>
      jwt.claim(name, value)
    }
    jwt.compact()
  }

  def unapply(jwt: String): Option[Map[String, Any]] =
    try {
      val claims: Jws[Claims] = Jwts.parser()
        .setSigningKey(Secret.getBytes("UTF-8"))
        .parseClaimsJws(jwt)
      Option(claims.getBody.asScala.toMap)
    } catch {
      case _: Exception => None
    }
}
