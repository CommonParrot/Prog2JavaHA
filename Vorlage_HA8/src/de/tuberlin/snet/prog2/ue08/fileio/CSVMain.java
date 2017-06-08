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
		// TODO Use your implemented filter, map, reduce, limit and count functions
		/*Double result = streamDataset.filter(...)
					 .mapToDouble(...)
					 .reduce(...)
					 .get();
		  System.out.println("Number of German men between 20-25 in populated areas: "+result);
		*/
		
		
		
	}
}
