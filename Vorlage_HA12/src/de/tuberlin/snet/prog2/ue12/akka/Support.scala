package de.tuberlin.snet.prog2.ue12.akka

import scala.io.StdIn
import akka.actor.ActorRef
import akka.actor.Actor
import akka.actor.Props

class Support extends Actor {
  val faq = List("email", "wlan", "product")
  
  def receive = ???
  /**
   * TODO
   */
  
}

private case class FAQRequest(question: String) extends Request
private case class ChatRequest(question: String) extends Request
trait MessageHandler {
  def handleMessage(msg: String)
  //def handleMessage(f: => String,msg: String)
}

class FAQ extends Actor with MessageHandler {
  def handleMessage(msg: String) = {
    println("Hi, here is a list of our FAQ")
    println("******")
  }
  def receive = ???
  /**
   * TODO
   */
}

class Chat extends Actor with MessageHandler {
  def handleMessage(msg: String) = {
    ask(msg)
  }
  def receive = ???
  /**
   * TODO
   */

  
  def ask(input: String): Unit = {
    val hallo = """(H|h)allo.*""".r
    val gutenTag = """(G|g)uten Tag.*""".r
    val danke = """(D|d)anke.*""".r
    val bye = """(B|b)ye.*""".r
    val pcProblem = """.*Problem.*(PC|pc|Pc).*""".r
    val aufWiedersehen = """Auf Wiedersehen.*""".r
    val service = """.*(S|s)ervice.*""".r

    print("Chatbot > ")
    input match {
        case hallo(_)          => println("Ebenfalls hallo.")
        case gutenTag(_)       => println("Guten Tag. Wie kann ich Ihnen helfen?")
        case danke(_)          => println("Gern geschehen. Kann ich Ihnen sonst noch irgendwie helfen?")
        case bye(_)            => {println("Vielen Dank für Ihre Anfrage. Auf Wiedersehen."); return;}
        case service(_)        => println("Wir bemühen uns stetig unseren Kundenservice zu optimieren.")
        case pcProblem(_)      => println("Ihre Problembeschreibung deutet auf ein Hardwareproblem hin. Bitte wenden Sie sich an den Hardwarehersteller.")
        case aufWiedersehen(_) => println("Auf Wiedersehen.")
        case _                 => println("Ich weiß nicht was Sie meinen, bitte formulieren Sie Ihre Frage anders.")

      }
    ask(StdIn.readLine("Sie > "))
  }
}