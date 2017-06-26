package de.tuberlin.snet.prog2.ue10.dataanalyst;


import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.tuberlin.snet.prog2.ue10.dataanalyst.CSVDataEntry;


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

		Stream<CSVDataEntry> streamDataset = csvManager.getDatasetStream();
		
		String bestArea = streamDataset.filter(d -> d.getAltersgruppe().equals("5_10")||d.getAltersgruppe().equals("10_15")||d.getAltersgruppe().equals("15_20")||!d.getStaatsangeh().equals("D"))
				.collect(Collectors.groupingBy(CSVDataEntry::getOrtsname, Collectors.summarizingDouble(CSVDataEntry::getHauefigkeit))).entrySet().stream()
				.max((e1,e2) -> e1.getValue().getSum() > e2.getValue().getSum()? 1 : -1).get().getKey();
		
		System.out.println(bestArea);
		
	}
}
