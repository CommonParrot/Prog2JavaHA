package de.tuberlin.snet.prog2.ue08.scalaFuncHighOrder

class FuncHighOrder extends App {

  val biggerNum: (Int, Int) => (Int) = (m: Int, n: Int) => Integer.max(m, n)

  val higher: (Int, Int, (Int, Int) => (Int)) => (Int) = (a: Int, b: Int, biggerNum) => biggerNum(a, b)

  var i = util.Random.nextInt

  var j = util.Random.nextInt

  val prod: (Int) => ((Int) => (Int)) = (a: Int) => ((n: Int) => a * n)

  val produkt = prod(5)

  val square: (Double) => (Double) = (x: Double) => x * x

  val sum: (Int, Int) => (Int) = (x: Int, y: Int) => x + y

  higher(i, j, biggerNum)
  produkt(5)
  square(5)
  sum(5, 5)

}