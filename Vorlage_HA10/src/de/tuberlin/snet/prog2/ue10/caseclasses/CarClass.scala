package de.tuberlin.snet.prog2.ue10.caseclasses

class CarClass private (val owner: String, val seatCount: Int = 1, val hu: Boolean)

object CarClass {
  def apply(owner: String, seatCount: Int, hu: Boolean): CarClass = {
    if (seatCount < 1)
      new CarClass(owner = owner, hu = hu)
    else
      new CarClass(owner, seatCount, hu)
  }
  
  def unapply(car: CarClass): Option[(String, Int, Boolean)] = {
    if (car == null) None
    else Some((car.owner, car.seatCount, car.hu))
  }
  
  def main(args: Array[String]): Unit = {
    val car1 = CarClass("Johannes", 7, true)
    println(CarClass.unapply(car1))
  }
}