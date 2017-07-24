package de.tuberlin.snet.prog2.uexx.nyc

import  scala.collection.mutable.MutableList

object CrazyTaxi {
  
  def main(args:Array[String]): Unit={
    
     val bufferedSource = io.Source.fromFile("C:\\GitHub\\workspace java 2\\yellow_tripdata_2016-12.csv").getLines().drop(1).take(1)
     
     for (line:String <-bufferedSource){
       
       println(line.split(",")(2))
       
     }
  
//     val taxicsvs =  for (line <- bufferedSource) yield {new NYCTaxiData(line)}
//    
//      println(taxicsvs.map(f => f.total).sum)
//      
//      println(taxicsvs.map(f => f.total).max)
//      
//      println(taxicsvs.map(f=> f.tip).max)
//      
//      println(taxicsvs.map(f=> f.distance).max)
//      
//      println("cash" + taxicsvs.map(f=> f.payment).count(p => p==2) +"credit" + taxicsvs.map(f=> f.payment).count(p => p==1))
//      
//      println("cash" + taxicsvs.map(f=> f.payment).count(p => p==4))
          
          
      
       
  }
  
}

case class NYCTaxiData(){
  
  var passengers: Int = _
  var distance: Double = _
  var payment: Int = _
  var tip: Double = _
  var amount: Double = _
  var total = tip + amount
  
  def this(csvline: String){
    this()
    var important = csvline.split(",")
    this.passengers = important(3).toInt
    this.distance = important(4).toDouble
    this.payment = important(13).toInt
    this.amount = important(20).toDouble
    this.tip = important(18).toDouble
  }
}