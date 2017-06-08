object ScalaRecursion extends App{
  
   def reoccurs(k: Int, n: Double, j: Int = 1): Double = if(j <= k) ((n-k+j)/j)*reoccurs(k, n, j+1) else 1
   
   def stackfriendly(k: Int, n: Double, s: Double, j: Int = 1): Double = if(j <= k) (stackfriendly(k, n, s*((n-k+j)/j), j+1)) else s
   
   println(reoccurs(5,7))
       
}