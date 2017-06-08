package de.tuberlin.snet.prog2.ue08.streams;

import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

/**
 * You can use this given file or your own solution from exercise 6.
 * 
 * @author prog2-team
 *
 * @param <T>
 */
public class StreamArrayList<T> extends ArrayList<T> {

	public StreamArrayList(){
		super();
	}
	
	public StreamArrayList(ArrayList<T> dataset){
		super(dataset);
	}
	

	public StreamArrayList<T> filter(Predicate<T> p) {
		StreamArrayList<T> result = new StreamArrayList<T>();
		for (T data : this) {
			if (p.test(data)) {
				result.add(data);
			}
		}
		return result;

	}
	
	public StreamArrayList<Double> mapToDouble(ToDoubleFunction<T> fct){		
		StreamArrayList<Double> resultArr= new StreamArrayList<Double>();
		for(T data: this){
			resultArr.add(fct.applyAsDouble(data));
		}
		return resultArr;
	}
	
	public Optional<T> reduce(BinaryOperator<T> bfct){
	     boolean foundAny = false;
	     T result = null;
	     for (T element : this) {
	         if (!foundAny) {
	             foundAny = true;
	             result = element;
	         }
	         else
	             result = bfct.apply(result, element);
	     }
	     return foundAny ? Optional.of(result) : 
	      Optional.empty();
	}
	
	public StreamArrayList<T> limit(long max) {
		StreamArrayList<T> results = new StreamArrayList<T>();
		for (int i = 0; i <= max - 1; i++) {
			results.add(this.get(i));
		}
		return results;
	}
	
	public long count(){
		return this.size();
	}

}

