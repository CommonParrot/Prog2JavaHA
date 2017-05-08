package de.tuberlin.snet.prog2.ue03.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csv {

	public static void main(String[] args) {
		String csvpath = "EWR_Ortsteile_Berlin_2015.csv";
		BufferedReader br = null;
		String readline = "";
		String csvSplitter = ";";
		csvlisting listing;
		List<csvlisting> list = new ArrayList<csvlisting>();

		try {

			br = new BufferedReader(new FileReader(csvpath));
			// Discard first line
			readline = br.readLine();
			while ((readline = br.readLine()) != null) {
				String[] buffer = readline.split(csvSplitter);
				buffer[7] = buffer[7].replace(",00", "");
				listing = new csvlisting(buffer[0], buffer[1], buffer[2], buffer[3], buffer[4], buffer[5], buffer[6],
						buffer[7]);
				list.add(listing);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String Bezirk = "Steglitz-Zehlendorf";
		char Staatsangehoerigkeit = 'D';
		int Geschlecht = 2;
		String Alter = "20_25";
		System.out.println(list.size());
		System.out.println("Die Anzahl an Frauen im Altersbereich: " + Alter + " mit deutscher SA in: " + Bezirk
				+ " betraegt: " + GetAnzahl(list, Bezirk, Staatsangehoerigkeit, Geschlecht, Alter));

	}

	/**
	 * Calculates the Number of People in Berlin based on the Parameters
	 * 
	 * @param list
	 *            List in which the csv is divided
	 * @param Bezirk
	 *            Bezirk in which you search for your love
	 * @param Staatsangehoerigkeit
	 *            SA
	 * @param Geschlecht
	 *            Gender
	 * @param Alter
	 *            Age
	 * @return Amount of Women (not all single)
	 */
	private static int GetAnzahl(List<csvlisting> list, String Bezirk, char Staatsangehoerigkeit, int Geschlecht,
			String Alter) {
		int Frauenzahl = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getGeschlecht() == 2 && list.get(i).getStaatsangehoerigkeit() == 'D'
					&& list.get(i).getBezirk().equals(Bezirk) && list.get(i).getAlter().equals(Alter)) {
				Frauenzahl = Frauenzahl + list.get(i).getHaeufigkeit();

			}
		}
		return Frauenzahl;
	}

}
