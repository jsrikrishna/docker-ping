package rest

import akka.actor.ActorSystem
import akka.actor._
import spray.routing.{ Directive0, ExceptionHandler, HttpService }
import akka.actor.ActorRefFactory
import scala.concurrent.ExecutionContextExecutor
import akka.io.IO
import spray.can.Http

/**
 * @author sjalipar
 */

class restActor extends Actor with HttpService with RestRoutes {
  
  override def actorRefFactory: ActorRefFactory = context

  implicit def ec: ExecutionContextExecutor = context.dispatcher
  
  override def receive: Receive =  runRoute(route)
}


object restMain {
  def main(args: Array[String]) : Unit = {
    implicit val actorSystem = ActorSystem("Rest-Server-Actor")
    
    val restService = actorSystem.actorOf(Props[restActor], "rest-serve-actor")
    
    IO(Http) ! Http.Bind(restService, "0.0.0.0", 8081)
  }
}