package de.tuberlin.snet.prog2.ue08.fileio;

import java.io.IOException;
import java.util.ArrayList;

import de.tuberlin.snet.prog2.ue08.streams.StreamArrayList;

public class CSVMain {

	public static void main(String[] args) {
		CSVManager csvManager = new CSVManager("resources/EWR_Ortsteile_Berlin_2015.csv");
		ArrayList<CSVDataEntry> csvData = new ArrayList<CSVDataEntry>();
		try {
			csvData = csvManager.readCSV();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		StreamArrayList<CSVDataEntry> streamDataset = csvManager.getDatasetStream();
		
		
		Double resulta = streamDataset.filter(d -> d.geschlecht == 1 && d.altersgruppe.equals("20_25") && d.staatsangeh.equals("D"))
				                     .mapToDouble(d -> d.hauefigkeit)
				                     .reduce(Double::sum)
				                     .get();
		  System.out.println("Number of German men between 20-25 in populated areas: "+resulta);
		 
		  
		 
		  
		 long resultb =  streamDataset.filter(d -> d.geschlecht == 1 && d.altersgruppe.equals("20_25") && d.staatsangeh.equals("D"))
				 					   .mapToDouble(d -> d.hauefigkeit)
				 					   .count();
		
		 System.out.println("Number of populated Areas with men between 20-25: " + resultb);
		
		 
		 
		 
		 StreamArrayList<Double> resultc = new StreamArrayList<>();
		 
		 streamDataset.filter(d -> d.geschlecht == 1 && d.altersgruppe.equals("20_25") && d.staatsangeh.equals("D"))
		 			  .mapToDouble(d -> d.hauefigkeit)
		 			  .limit(5)
		 			  .forEach(b -> resultc.add(b));
		 
		 System.out.println("Ich kann limit benutzen :D : " + resultc.count());
		 
	}
}
