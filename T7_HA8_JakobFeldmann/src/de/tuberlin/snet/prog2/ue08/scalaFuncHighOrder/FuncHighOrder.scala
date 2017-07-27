package de.tuberlin.snet.prog2.ue08.scalaFuncHighOrder

object FuncHighOrder {

  
  val biggerNum = (m: Int, n: Int) => Integer.max(m, n)
  
  def biggerNums = (m:Int,n:Int)=>Integer.max(_,_)
  
  val higher: (Int, Int, (Int, Int) => (Int)) => (Int) = (a, b, biggerNum) => biggerNum(a, b)

  var i = util.Random.nextInt

  var j = util.Random.nextInt

  
  
  val prod= (a: Int) => ((n: Int) => a * n)

  val produkt = prod(4)

  
  // Funktion   Funktionstyp           Funktionsliteral
  val square: (Double) => (Double) = (x) => x * x

  
  
  val sum: (Int, Int) => (Int) = (x, y) => x + y
  val platzhaltersum: (Int,Int) => (Int) = _+_
  
  
  def main(args: Array[String]): Unit = {
    println(higher(i, j, biggerNum))
    println(produkt(5))
    println(square(5))
    println(sum(5, 5))
  }

  
}