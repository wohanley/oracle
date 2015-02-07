package oracle

import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import com.twitter.finagle.http.Response
import java.net.InetSocketAddress
import java.util.Timer
import java.util.TimerTask
import org.jboss.netty.handler.codec.http._
import util.Properties
import oauth.signpost.OAuth
import oauth.signpost.OAuthConsumer
import oauth.signpost.OAuthProvider
import oauth.signpost.basic.DefaultOAuthConsumer
import oauth.signpost.basic.DefaultOAuthProvider

object Animator {
  def main(args: Array[String]) {
    val port = Properties.envOrElse("PORT", "8080").toInt
    val server = Http.serve(":" + port, new HelloService)
    Await.ready(server)

    tweetRegularly()
  }

  def tweetRegularly() =
    new Timer().schedule(TweetTask, 0, 15 * 60 * 1000)
}

object TweetTask extends TimerTask {

  val wordMap = oracle.buildWordMap(List("fortunes", "wisdom", "goedel"))

  /** Tweet a fortune */
  override def run = {
    val oauthConsumer = new DefaultOAuthConsumer(
      "9JqqFKjWiA435oCGU9yuuM1MY",
      Properties.envOrElse("API_SECRET", ""))
    oauthConsumer.setTokenWithSecret(
      "3012148274-YO67BWOFQHyQT0ff7DXPrsLtaemFsxSCfF8hmci",
      Properties.envOrElse("ACCESS_TOKEN_SECRET", "")
    )
    
  }
}

class HelloService extends Service[HttpRequest, HttpResponse] {
  def apply(request: HttpRequest) = {
    val response = Response()
    response.setStatusCode(200)
    response.setContentString("Hi there!")
    Future(response)
  }
}
