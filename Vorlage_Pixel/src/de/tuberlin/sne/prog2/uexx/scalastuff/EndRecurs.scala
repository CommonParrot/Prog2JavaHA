package de.tuberlin.sne.prog2.uexx.scalastuff


object EndRecurs {
  
  def main(args: Array[String]): Unit = {
    
    println(faculty(4,1))
    
    println(scalarProduct(List(5.0,1.0,3.0),List(5.0,1.0,3.0),0.0))
    
  }
  
  def faculty(n: Int, i: Int): Int = { 
    if (n >= 1) {
        faculty(n-1, i*n)
     } 
    else i
    
  }
  
  def scalarProduct(a: List[Double], b: List[Double], i: Double): Double = { 
    if (a.length>0 && b.length>0) 
      scalarProduct(a.tail, b.tail, i+a.head * b.head) 
   
     else i}
  
}