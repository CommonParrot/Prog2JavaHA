package de.tuberlin.snet.prog2.ue06.fittingInterfaces;

import java.time.LocalDateTime;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.ToDoubleBiFunction;

public class LambdaExp {
	
	//1
	static BiPredicate<String, String> bipredi = (String s1, String s2) -> s1.length() < s2.length() ? true : false;
	
	//2
	final static double d = 10;
	static DoubleSupplier dbo = () -> d*d;
	
	 //3
	static ToDoubleBiFunction<Integer,Integer> intbi =(Integer i1, Integer i2)->Math.PI*i1*i2;
	
	//4
	static TwoIntToDouble tid = (int i1, int i2) -> Math.PI * i1 * i2;
	
	//5
	static Consumer<String> cons = c -> {String str = c.toString();
	 String[] strarr = str.split(".");
	 System.out.println(strarr.length);};
	
	//6
	static TripleConsumer<String, String, String> tc = (a, b, c) -> System.out.println(a.toString() + b.toString() + c.toString());
	 
	//7
	static BooleanSupplier bs = () -> {LocalDateTime time = LocalDateTime.now(); 
		int minute = time.getMinute(); 
		if (minute%2 == 1) return true; else return false;};
		
	public static void main(String[] args) {
		
		System.out.println(bipredi.test("too","hot"));
		
		System.out.println(dbo.getAsDouble());
		
		System.out.println(intbi.applyAsDouble(2,3));
		
		System.out.println(tid.intToDouble(3, 4));
		
		cons.accept("L.UL3D.Kapp.a");
		
		tc.consume("a", "b","c");
		
		System.out.println(bs.getAsBoolean());
		
	}
		
}
