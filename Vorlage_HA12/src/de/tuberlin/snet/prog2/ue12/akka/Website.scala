package de.tuberlin.snet.prog2.ue12.akka

import akka.actor.ActorRef
import akka.actor.Actor

class Website extends Actor {
  
  val urlList = Map(
      "home" -> "Please choose: Shop, Offers, Pricing",
      "shop" -> "Take time to browse our shop.",
      "offers" -> "Today's Top Deals: Unlogitech Webcam, Sport-Umbrella",
      "pricing" -> "You only pay the shipping costs of 100,-EUR. The products are ALL FOR FREE!")
   
   def receive = ???
   /**
   * TODO
   */
}