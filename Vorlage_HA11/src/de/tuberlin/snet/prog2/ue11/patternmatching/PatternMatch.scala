package de.tuberlin.snet.prog2.ue11.patternmatching

object PatternMatch {
  
  
  case class Car(hersteller: String, typ: String, baujahr: Int, motorart: Motorart, hu: Boolean, leistungInKW: Int)
  
  sealed trait Motorart
  //statt class Motorart
  
  case object Diesel extends Motorart
  case object Benzin extends Motorart
  case object Elektro extends Motorart
  case object Gas extends Motorart
  
  def matchCar(x: Any){
    
    x match{
      //
      //VOM SPEZIELLEN IMMER INS ALLGEMEINE!11!!!
      //
      case Car(hersteller @ "VW",_,_,Diesel,_,_) => println(hersteller)//       @ Um auf Variblen in unapply zuzugreifen
      case Car("Porsche",_,_,Benzin,_,300) => println(x)
      case Car(_,"S",_,Elektro,_,_) => println(x)
      case Car(_,_,1992,_,false,_) => println(x)
      case Car(_,_,1985,_,_,_) => println(x)
      case Car(_,_,_,_,false,_) => println(x)
      case _=> println("Something isn't right")
    }
    
  }
  
  def main(args: Array[String]): Unit = {

    val one = Car("VW", "A1", 1994, Diesel, true, 99)
    val two = Car("Porsche", "B2", 1994, Benzin, true, 300)
    val three = Car("Porsche", "S", 1994, Elektro, true, 300)
    val four = Car("Porsche", "S", 1994, Elektro, false, 300)
    val five = Car("VW", "B", 2001, Elektro, true, 4000)
    val six = Car("Porsche", "S", 1992, Elektro, false, 300)
    val seven = Car("BMW", "S", 1985, Elektro, false, 300)

    val eight = Car("VW", "S", 2001, Elektro, true, 4000)
    val nine = Car("Porsche", "S", 2001, Elektro, false, 300)
    val ten = Car("Honda", "S", 2017, Elektro, false, 300)
    
    matchCar(one)
    matchCar(two)
    matchCar(three)
    matchCar(four)
    matchCar(five)
    matchCar(six)
    matchCar(seven)
    
    
  }
}