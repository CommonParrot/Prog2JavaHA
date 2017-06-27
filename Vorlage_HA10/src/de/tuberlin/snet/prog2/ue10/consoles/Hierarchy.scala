package de.tuberlin.snet.prog2.ue10.consoles

class Human(val name: String) extends Colleague with Friend{
  
}

trait Colleague{
  val name: String
  def isColleague()=println(s"Your colleague is $name")
}

trait Friend{
  val name: String
  def isFriend()=println(s"Your friend is $name")
}

class Man extends Human{
  
}

class Woman extends Human{
  
}

abstract class Animal{
  def makeNoise: String;
}

class Dog(val name: String) extends Animal with Friend{
}

class Cat(val name: String) extends Animal{
}

trait Flying extends Animal{
  def fly()
}

trait Swimming extends Animal{
  def swim()
}
abstract class Bird extends Animal{
  val name: String
}
class Frigatebird extends Bird with Flying{
  
}
class Duck extends Bird with Flying with Swimming{
  
}
class Ostrich extends Bird{
  
}
class Penguin extends Bird with Swimming{
  
}

