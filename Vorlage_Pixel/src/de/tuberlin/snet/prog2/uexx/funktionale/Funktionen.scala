package de.tuberlin.snet.prog2.uexx.funktionale

object Funktionen {
  def main(args: Array[String]):Unit ={
    
    val quad = (x:Double) => x*x
    
    println(quad(5.0))
    
    val dumb = (x:Double,y:Double)=>x*y+y
    
    println(dumb(40.0,40.0))
    
    val g = (x:Double)=>2*x
    
    val misch = (x:Double,y:Double)=>g(x)*y
    
    println(misch(8.9,3.1))
    
    
    val equals : (Any,Any) => Boolean = _.equals(_)
    
    val parseInt: (String) => Integer = Integer.parseInt(_)
    
    val mat = new Admin("Mathias",300.0)
    
    val pet = new Student("Peter",2346234)
    
    val pao = new Stuff("Paola",300.0)
    
    val ber = new HiWi("Berken",4252341,400.0)
    
    val jes = new HiWi("Jessica",3412344,5240958.0) with AdminProperties
  }
  
  
}

trait AdminProperties{
  val masterPassword = "DeineMomIsCool"
}

trait StuffProperties{
  val gehalt:Double
}

trait StudentProperties{
  val matrikelnr:Int
}

abstract class User(val name: String)

class Admin(name:String ,val gehalt: Double) extends User(name) with AdminProperties with StuffProperties

class Student(name: String ,val matrikelnr:Int) extends User(name) with StudentProperties

class Stuff(name: String ,val gehalt:Double) extends User(name) with StuffProperties

class HiWi(name:String, matrikelnr:Int, val gehalt:Double) extends Student(name,matrikelnr) with StuffProperties

//Class ist eine Schablone, die einen Konstuktor erhelt um neue Objekte zu erstellen class Hallo(val msg:String) zb. ein objekt ist ein schon erstelltes Objekt, dass nicht mehr konstruiert werden kann: object Hallo{val msg = "Hallo"}. Eine Case