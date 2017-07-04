package de.tuberlin.snet.prog2.ue11.patternmatching

object OptionTask {

 sealed abstract class myOption[+T] {
    
    def get: T
    
    def isDefined= isInstanceOf[mySome[T]]
  
    
    final def getOrElse[A>:T](default: => A): A= if(isDefined) get else default
    
  }
  
  object myOption{
     def apply(that: Any): myOption[Any] = {
      return mySome(that);
    }
   
    def apply(): myOption[Nothing] = {
      return myNone
    }
  }

  final case class mySome[+T](value: T) extends myOption[T] {
     def get: T= value
  }

  object myNone extends myOption[Nothing] {
    def get: Nothing = throw new NoSuchElementException("Value is nothing")
  }
  
  
  def main(args: Array[String]): Unit = {
    val testmy = myOption("hello")
    val ntestmy = myOption()
    
    println(testmy.isDefined)
    println(testmy.get)
    println(testmy.getOrElse("Hallo"))
    
    println(ntestmy.isDefined)
//    println(ntestmy.get)
    println(ntestmy.getOrElse("Hallo"))
    
    val test: Option[String] = Option("hello")
    val ntest: Option[String] = Option(null)
      
    println(ntest.isDefined)
//    println(ntest.get)
    println(ntest.getOrElse("Hallo"))
     
    println(test.isDefined)
    
    println(test.get)
    
    println(test.getOrElse("Hallo"))
    }
  
  
}