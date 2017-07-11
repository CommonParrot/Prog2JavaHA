package de.tuberlin.snet.prog2.ue12.akka

import akka.actor.ActorRef
import akka.actor.Actor

class User extends Actor {
  def receive = {
    case StartQuery(a: ActorRef) => {
      /**
       * TODO
       */
    }
    case Response(text: String, 200) => println("mmm, that's interesting: \n  " + text)
    case Response(text: String, 404) => println("mmm, that must be wrong: \n  " + text)
    case Response(text: String, 401) => println(s"Response: $text")
    case _                           => println("I'm an Internet User")
  }
  
  
}