package de.tuberlin.snet.prog2.ue05.lambda;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;

public class LambdaUebung {
	
	//runnable that is created with a lambda expression that overrides the run method
	Runnable runnableOutput = () -> System.out.println("This Thread is running with Runnable Interface in JDK7 format");
	
	Comparator<String> compareByStringLength = (String o1, String o2) -> {return Integer.compare(o1.length(),o2.length());};
	
}
