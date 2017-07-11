package de.tuberlin.snet.prog2.ue12.deciderdirective

import scala.io.Source
import scala.concurrent.duration._
import java.io.FileNotFoundException

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import akka.actor.Terminated
import akka.event.Logging



object Master extends App {
  val filename = "resources/like_mich"
  val system = ActorSystem("AkkaDeciderDemo")
  /* Log-Nachrichten unterdrücken: */
  system.eventStream.setLogLevel(Logging.LogLevel(0))
  val supervisor = system.actorOf(Props[SupervisorActor], "Supervisor")
  supervisor ! Start
  Thread.sleep(100)
  supervisor ! Close
  system.terminate()
}

case object Start
case object Close

/**
 * Actor that supervises the child.
 * Demonstrates the different strategies.
 */
class SupervisorActor extends Actor {

  println("SupervisorActor ist started")
  //  context.self ! Start

  val child = context.actorOf(Props[Child], "child")
  context.watch(child) // -> erhält Terminated(child)-Nachricht, wenn child terminiert

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: ArithmeticException      => Resume
      case _: NullPointerException     => Restart
      case _: IllegalArgumentException => Stop
      case _: Exception                => Escalate
    }

  def receive = {
    // create actor with configuration p and send requests
    case Start =>
      child ! "get"
      child ! 42
      child ! "get"
      child ! new ArithmeticException(""""Normal Exception" -> Resume""") // crash it
      child ! "get"
      child ! new NullPointerException(""""Null Exception" -> Restart""") // crash it harder
      child ! "get"
      child ! new IllegalArgumentException(""""Wrong Input Exception" -> Stop""") // break it
//      child ! new Exception("CRASH -> Escalate") // escalate failure, SupervisorActor is restarted
      child ! "get"
    case Close =>
      context.stop(child)
      context.stop(self)
    case state: Int        => println(s"state: $state")
    case Terminated(child) => println("Where is my child?")
  }
}

/**
 * Actor that is supervised by the SupervisorActor
 */
class Child extends Actor {

	println("entered the Child constructor")
  var state = 0

  def receive = {
    case ex: Exception => throw ex
    case x: Int        => state = x
    case "get"         => sender() ! state
  }


}