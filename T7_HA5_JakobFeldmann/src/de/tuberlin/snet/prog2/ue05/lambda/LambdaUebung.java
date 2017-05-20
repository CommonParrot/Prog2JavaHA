package de.tuberlin.snet.prog2.ue05.lambda;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;

public class LambdaUebung {
	
	Runnable runnableOutput = () -> System.out.println("This Thread is running with Runnable Interface in JDK7 format");
	
	Comparator<String> compareByStringLength = (String o1, String o2) -> Integer.compare(o1.length(), o2.length());
	
	DoubleFunction<Double> malzwei = (x) -> x*2;
	DoubleFunction<Double> quadrat = (x) -> x*x;
	BiFunction<Double, Double, Double> xplusy = (x,y) -> x+y;
	BiFunction<Double, Double, Double> fun = (x,y) -> Math.sqrt(Math.cbrt(x-y));
	
}
