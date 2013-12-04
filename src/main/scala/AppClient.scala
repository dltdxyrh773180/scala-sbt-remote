
import akka.actor._
import akka.routing.FromConfig
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory 

import akka.pattern.ask

//import scala.concurrent.Await
import akka.dispatch.Await

object Client extends App {
  val logger = LoggerFactory.getLogger(this.getClass())
  val system = ActorSystem("sample", ConfigFactory.load("client"))
  implicit val timeout = Timeout(5000)
  
  val ref = system.actorFor("akka://sample@localhost:2553/user/echo")
  
  logger.info("start")
  
  val remotes = List(
    "alpha", "bravo", "cherie", "delta"
    ).map(s => {
      ref ? s
      })
      logger.info("end")
      
      remotes.foreach(f => {
        val result = Await.result(f, timeout.duration).asInstanceOf[String]
        logger.info(result)
      })
      
      system.shutdown()
}
