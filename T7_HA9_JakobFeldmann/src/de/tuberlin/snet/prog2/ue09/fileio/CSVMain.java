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
		CSVManager csvManager = new CSVManager("resources/EWR_Ortsteile_Berlin_2015.csv");
		ArrayList<CSVDataEntry> csvData = new ArrayList<CSVDataEntry>();
		try {
			csvData = csvManager.readCSV();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		ArrayList<CSVDataEntry> streamDataset = csvManager.getDatasetStream();

		
		
	}
}
