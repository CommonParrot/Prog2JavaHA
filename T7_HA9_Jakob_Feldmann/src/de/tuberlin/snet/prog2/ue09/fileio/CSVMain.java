package de.tuberlin.snet.prog2.ue09.fileio;


import java.io.IOException;
import java.util.ArrayList;

import de.tuberlin.snet.prog2.ue09.fileio.CSVDataEntry;


/**
 * class containing the main method that runs CSVManager
 *
 */
public class CSVMain {

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CSVManager csvManager = new CSVManager("resources/EWR_Ortsteile_Berlin_2015.csv", "Windows-1252");
//		CSVManager csvManager = new CSVManager("resources/yellow_tripdata_2016-12.csv", "UTF-8");
		
		
		ArrayList<CSVDataEntry> csvData = new ArrayList<CSVDataEntry>();
		try {
			csvData = csvManager.readCSV();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
//		Double resulta = csvData.stream()
//                .mapToDouble(d -> d.hauefigkeit)
//                .reduce(Double::sum)
//                .getAsDouble();
//		
//		System.out.println("Number people in berlin(stream out of arraylist): "+resulta);
//		
//		
//		Double resultb = csvManager.streamCSV()
//                .mapToDouble(d -> d.hauefigkeit)
//                .reduce(Double::sum)
//                .getAsDouble();
//		
//		System.out.println("Number people in berlin(Stream out of file): "+resultb);
		
		
//		csvManager.taxiStream()
//				  //.skip(1)
//				  .limit(11)
//				  .forEach(b -> System.out.println(b));     
		
		
		sequentialStreaming(csvData);
		parallelStreaming(csvData);
		
		
	}
	
	/**
	 * Performs sequential stream operations 
	 * @param sds
	 */
	public static void sequentialStreaming(ArrayList<CSVDataEntry> sds){
		
		long startTime = System.currentTimeMillis();
		Double resulta = sds.stream()
				
									.filter(b -> { 
								  			try {
								  					Thread.sleep(1);
							  					} 
							  				  catch (InterruptedException e) {
							  					  e.printStackTrace();
							  				  	} 
							  				return b.geschlecht==2;})
									
									  .map(d -> d.hauefigkeit)
									  .reduce(Double::sum)
									  .get();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;

		System.out.println("Number women in berlin(sequential): " + resulta);
		System.out.println("Executionime in ms: " + elapsedTime);
		
	}
	
	/**
	 * Performs parallel stream operations 
	 * @param sds
	 */
	public static void parallelStreaming(ArrayList<CSVDataEntry> sds){
		
		long startTime = System.currentTimeMillis();
		Double resultb = sds.parallelStream()
				
				  					  .filter(b -> { 
				  						  			try {
				  						  					Thread.sleep(1);
				  					  					} 
				  					  				  catch (InterruptedException e) {
				  					  					  e.printStackTrace();
				  					  				  	} 
				  					  				return b.geschlecht==2;})
				  					  
				  					  .mapToDouble(d -> d.hauefigkeit)
				  					  .reduce(Double::sum)
				  					  .getAsDouble();

		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    
	    System.out.println("Number women in berlin(parallel): " + resultb);
	    System.out.println("Executionime in ms: " + elapsedTime);
	    
	}
}
