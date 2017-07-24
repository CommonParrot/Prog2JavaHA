//package de.tuberlin.snet.prog2.ue10.consoles
//
//class Human(val name: String) extends Colleague with Friend{
//  
//}
//
//trait Colleague{
//  val name: String
//  def isColleague()=println(s"Your colleague is $name")
//}
//
//trait Friend{
//  val name: String
//  def isFriend()=println(s"Your friend is $name")
//}
//
//class Man(n:String) extends Human(n){
//  
//}
//
//class Woman(n:String) extends Human(n){
//  
//}
//
//abstract class Animal{
//  def makeNoise(){
//    println("kappa")
//  }
//}
//
//class Dog(val name: String) extends Animal with Friend{
//    
//}
//
//class Cat(val name: String) extends Animal{
//}
//
//trait Flying extends Animal{
//  val flyMessage: String
//  def fly()
//  override def makeNoise(){
//    super.makeNoise()
//    println("flap")
//  }
//}
//
//trait Swimming extends Animal{
//  val swimMessage: String
//  def swim()
//    override def makeNoise(){
//    super.makeNoise()
//    println("splash")
//  }
//}
//abstract class Bird(n:String) extends Animal{
//  val name = n
//  override def makeNoise(){
//    super.makeNoise()
//    println("cheep")
//  }
//}
//class Frigatebird(n:String) extends Bird(n) with Flying{
//  
//}
//class Duck(n:String) extends Bird(n) with Flying with Swimming{
//  override def makeNoise(){
//    super.makeNoise()
//    println("quak")
//  }
//}
//class Ostrich(n:String) extends Bird(n){
//  
//}
//class Penguin(n:String) extends Bird(n) with Swimming{
//  
//}
//
//object doSomething{
//  
//  def main(args: Array[String]): Unit = {
//    
//    
//    
//  }
//  
//}

