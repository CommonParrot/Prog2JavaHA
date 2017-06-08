package de.tuberlin.snet.prog2.ue08.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import de.tuberlin.snet.prog2.ue08.streams.StreamArrayList;
public class CSVManager {

	File csvFile;
	ArrayList<CSVDataEntry> dataset;

	public CSVManager(String path) {
		csvFile = new File(path);
		dataset = new ArrayList<CSVDataEntry>();
	}

	public ArrayList<CSVDataEntry> readCSV() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		String line;
		reader.readLine(); // remove the headerline
		while ((line = reader.readLine()) != null) {
			dataset.add(new CSVDataEntry(line));
		}
		reader.close();
		return dataset;
	}

	public void writeDataToCSV(ArrayList<CSVDataEntry> csvData, String path) throws IOException {
		File writeFile = new File(path);
		FileWriter writer = new FileWriter(writeFile);

		for (CSVDataEntry data : csvData) {

			writer.append(data.toCSVFormat(";"));
			writer.append('\n');

		}

		writer.flush();
		writer.close();
	}

	public ArrayList<CSVDataEntry> getDatasetArrayList() {
		return !dataset.isEmpty() ? dataset : null;
	}

	public StreamArrayList<CSVDataEntry> getDatasetStream() {
		return !dataset.isEmpty() ? new StreamArrayList<CSVDataEntry>(dataset) : null;
	}

}
