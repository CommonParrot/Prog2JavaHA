package de.tuberlin.snet.prog2.uexx.funktionale;

import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;

public class Fuktonen {
	
	public static double quadrat(Double x, DoubleFunction<Double> quadrieren){return quadrieren.apply(x);};
	
	public static double irgendwas(Double x, Double y,BiFunction<Double, Double, Double> iwas ){return iwas.apply(x, y);}
	
	public static int irgendwas2(int x, int y, BiFunction<Integer,Integer,Integer>iwas2){return iwas2.apply(x, y);};
	
	public static int ok(int x, IntFunction<Integer> oke){return oke.apply(x);};
	
	static Double k = quadrat(5.0,x -> x*x);
	
	static Double g = irgendwas(5.0,5.0,(x,y)->x*y+y);
	
	static double h = irgendwas2(5,5,(x,y)->ok(x,c->2*c)*y);
	
	public static void main(String[] args){
		
		System.out.println(h);
		
	}
	
}

public interface equality<T>{
	public Boolean equals(T x1, T x2);
}

public interface parsing{
	public int parseInt(String x1);
}