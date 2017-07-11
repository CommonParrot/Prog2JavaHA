package de.tuberlin.snet.prog2.ue12.akka

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef

object AkkaServer extends App {

/**
 * TODO
 */
}

trait Request
case class WebRequest(url: String) extends Request
case class SupportRequest(question: String) extends Request

case class Response(text: String, code: Int)
case class StartQuery(actor: ActorRef)







