package oracle

import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import com.twitter.finagle.http.Response
import java.net.InetSocketAddress
import org.jboss.netty.handler.codec.http._
import util.Properties

object Animator {
  def main(args: Array[String]) {
    val port = Properties.envOrElse("PORT", "8080").toInt
    val server = Http.serve(":" + port, new HelloService)
    Await.ready(server)
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
