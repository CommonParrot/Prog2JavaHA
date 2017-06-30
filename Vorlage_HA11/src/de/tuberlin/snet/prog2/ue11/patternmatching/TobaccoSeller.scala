package de.tuberlin.snet.prog2.ue11.patternmatching

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
  def buyCigarette(pers: Person): Cigarette = {
    if (pers.age < 18)
      return null
    else if (cigaretteCount <= 0)
      return null
    else {
      cigaretteCount -= 1
      return new Cigarette
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

  def tryToBuyCigarettes(p: Person) = {
		  println(s"${p.name} tries to buy cigarettes.")
    /**
     * TODO: Try to buy cigarettes and make an appropriate output on the console
     */
		  
  }

}