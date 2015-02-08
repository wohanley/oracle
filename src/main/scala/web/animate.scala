package oracle.web

import com.twitter.finagle.http.RequestBuilder
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

object HerokuWeb {
  def main(args: Array[String]) {
    tweetRegularly()
  }

  val wordMap = oracle.buildWordMap(List("fortunes", "wisdom", "goedel"))

  private def tweetRegularly() = {
    while (true) {
      web.tweet(wordMap)
      Thread.sleep(0, 15 * 60 * 1000)
    }
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
