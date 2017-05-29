package de.tuberlin.snet.prog2.ue06.streams;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class StreamArrayList<T> extends ArrayList<T> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2872989399717930307L;

	
	/**
	 * Constructs an empty list with an initial capacity of ten (check
	 * constructor of super class)
	 */
	public StreamArrayList() {
		super();
	}
	

	/**
	 * Constructs a list containing the elements of the specified dataset
	 *
	 * @param dataset  which elements are placed into this list
	 * @throws NullPointerException if the specified collection is null
	 */
	public StreamArrayList(ArrayList<T> dataset) {
		super(dataset);
	}
	

	/**
	 * filters the StreamArrayList according to the Predicate
	 * 
	 * @param functional interface: predicate
	 * @return new StreamArrayList after filtering
	 */
	public StreamArrayList<T> filter(Predicate<T> p) {
		StreamArrayList<T> neue = new StreamArrayList<>();
		
		for(T i : this){
			if(p.test(i)){
				neue.add(i);
			}
		}
		return neue;
	}
	

	/**
	 * maps each single element of the StreamArrayList to a double.
	 * StreamArrayList
	 * 
	 * @param dFunct functional interface: function
	 * @return new StreamArrayList after mapping
	 */
	public StreamArrayList<Double> mapToDouble(ToDoubleFunction<T> dFunct) {
		StreamArrayList<Double> neue = new StreamArrayList<>();
		for(T i : this){
			neue.add(dFunct.applyAsDouble(i));
		}
		return neue;
	}
	

	/**
	 * reduces the StremArrayList according to the BinaryOperator bo
	 * 
	 * @param bo functional interface: BinaryOperator
	 * @return Optional: null or value with datatype T
	 */
	public Optional<T> reduce(BinaryOperator<T> bo) {
		
		T result = null;
		boolean stimmt = false;
		
		for(T i : this){
		  
		  if(!stimmt){
		   stimmt = true;
		   result = i;
		  }
		  else{
		   result = bo.apply(result, i);
		  }
		  
		}
		
		return stimmt ? Optional.of(result) : Optional.empty();
	}
	

	/**
	 * limits the StreamArrayList
	 * 
	 * @param max
	 * @return shortened StreamArrayList
	 */
	public StreamArrayList<T> limit(long max) {
		StreamArrayList<T> neue = new StreamArrayList<>();
		
		for(int i = 0; i<max; i++){
			neue.add(this.get(i));
		}
		
		return neue;
	}
	

	/**
	 * counts the StreamArrayList
	 * 
	 * @return long counter
	 */
	public long count() {
		return (long) this.size();
	}

}
