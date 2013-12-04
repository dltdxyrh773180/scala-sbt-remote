
import akka.actor._
import akka.routing.FromConfig
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory 

import akka.pattern.ask

//import scala.concurrent.Await
import akka.dispatch.Await

class GreetingActor extends Actor with ActorLogging { 
  def receive = {
    case who:String => {
      log.info("Welcome " + who)
      sender ! ("Welcome reply " + who)
    }
    case _ => log.info("undefined message")
  }
}

object Remote extends App {
  val config = ConfigFactory.load()
  val system = ActorSystem("sample", config)
  
  val ref = system.actorOf(Props[GreetingActor].withDeploy(Deploy(config = config)), "echo")
}
