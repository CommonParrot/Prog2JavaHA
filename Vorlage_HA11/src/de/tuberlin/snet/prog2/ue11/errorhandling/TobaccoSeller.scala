package de.tuberlin.snet.prog2.ue11.errorhandling

/**
 * TobaccoSeller represents the shop that sells the tobacco
 * cigaretteCount:Int
 */
object TobaccoSeller extends App {
  /**
   * Person represents the customer, buying a cigarette
   * name: String
   * age: Int
   */
  class Person(val age: Int, val name: String)

  /** Cigarette */
  case class Cigarette()

  /** Number of cigarettes in the shop, that's fairly little... */
  var cigaretteCount = 2;

  /**
   * Buy cigarettes from the tobacco seller.
   * Receive cigarette if there are still some
   * of it and if the person is older than 18.
   */
  def buyCigarette(pers: Person): Option[Cigarette] = {
    
    if (pers.age < 18)
//      throw underageException("You aint get no ciggis from me bro")
      None
    
    else if (cigaretteCount <= 0)
//            throw outOfStockException("Ciggis are out try again later")
      None
      
    else {
      cigaretteCount -= 1
      Some(new Cigarette)
//      return new Cigarette
    }
  }

  val child = new Person(age = 12, "Child")
  val teenager = new Person(age = 16, "Teenager")
  val adult = new Person(age = 25, "Adult")

  tryToBuyCigarettes(child)
  tryToBuyCigarettes(teenager)
  tryToBuyCigarettes(adult)
  tryToBuyCigarettes(adult)
  tryToBuyCigarettes(adult)

  case class underageException(error: String) extends Exception(error)
  case class outOfStockException(error: String) extends Exception(error)

  def tryToBuyCigarettes(p: Person) = {
    
    println(s"${p.name} tries to buy cigarettes.")
    
    val pro: Option[Cigarette] = buyCigarette(p)
    
    pro match{
      case Some(Cigarette()) => println(s"${p.name} got ciggis.")
      case None => println(s"${p.name} got no ciggis.")
    }

//        try {
//          buyCigarette(p)
//          println(s"${p.name} got ciggis.")
//        } catch {
//          case x: underageException => {
//            println(x.getMessage)
//            println(s"${p.name} got no ciggis.")
//          }
//          case x: outOfStockException => {
//            println(x.getMessage)
//            println(s"${p.name} got no ciggis.")
//          }
//    
//        }

  }

}