package de.tuberlin.snet.prog2.ue03.einwohner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EWR {

	//Objektvariablen
	private String bezID;
	private String bezName;
	private String ortstID;
	private String ortstName;
	private int geschlecht;
	private String staatsang;
	private String alter;
	private int haeufigkeit;
	
	//Getter und Setter der Klasse
	public String getBezID() {
		return bezID;
	}

	public void setBezID(String bezID) {
		this.bezID = bezID;
	}

	public String getBezName() {
		return bezName;
	}

	public void setBezName(String bezName) {
		this.bezName = bezName;
	}

	public String getOrtstID() {
		return ortstID;
	}

	public void setOrtstID(String ortstID) {
		this.ortstID = ortstID;
	}

	public String getOrtstName() {
		return ortstName;
	}

	public void setOrtstName(String ortstName) {
		this.ortstName = ortstName;
	}

	public int getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(int geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getStaatsang() {
		return staatsang;
	}

	public void setStaatsang(String staatsang) {
		this.staatsang = staatsang;
	}

	public String getAlter() {
		return alter;
	}

	public void setAlter(String alter) {
		this.alter = alter;
	}

	public int getHaeufigkeit() {
		return haeufigkeit;
	}

	public void setHaeufigkeit(int haeufigkeit) {
		this.haeufigkeit = haeufigkeit;
	}

	/**
	 * Konstruktor des EWR Objektes
	 * @param bezID BezirksID
	 * @param bezName Bezirksname
	 * @param ortstID OrtsteilID
	 * @param ortstName Ortsteilname
	 * @param geschlecht Geschlecht
	 * @param staatsang Staatsangehörigkeit
	 * @param alter Altersgruppe
	 * @param haeufigkeit Häufigkeit
	 */
	public EWR(String bezID, String bezName, String ortstID, String ortstName, int geschlecht, String staatsang,
			String alter, int haeufigkeit) {
		this.bezID = bezID;
		this.bezName = bezName;
		this.ortstID = ortstID;
		this.ortstName = ortstName;
		this.geschlecht = geschlecht;
		this.staatsang = staatsang;
		this.alter = alter;
		this.haeufigkeit = haeufigkeit;
	}

	/**
	 * Funktion in der eine Datei eingelesen wird und aus dieser eine
	 * ArrayList an EWR Objekten erstellt wird
	 * @param source Quelldatei
	 * @return Array Liste bestehend aus EWR Objekten
	 */
	public static ArrayList<EWR> readIn(File source) {
		ArrayList<EWR> ewrList = new ArrayList<EWR>();
		FileReader fr = null;
		try {
			fr = new FileReader(source);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		String line;
		EWR temp;
		try {
			line = br.readLine();
			System.out.println(line);
			while ((line = br.readLine()) != null) {
				temp = createEWRFromLine(line);
				ewrList.add(temp);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println("Error while Reading file");
		}
		return ewrList;
	}

	/**
	 * Funktion um ein EWR Objekt aus einem String zu
	 * erstellen
	 * @param line String aus dem das Objekt erstellt wird
	 * @return EWR Objekt
	 */
	public static EWR createEWRFromLine(String line) {
		String[] splitLine = line.split(";");
		String bezID = splitLine[0];
		String bezName = splitLine[1];
		String ortstID = splitLine[2];
		String ortstName = splitLine[3];
		int geschlecht = Integer.parseInt(splitLine[4]);
		String staatsang = splitLine[5];
		String alter = splitLine[6];
		int haeufigkeit = Integer.parseInt(splitLine[7].split(",")[0]);
		return new EWR(bezID, bezName, ortstID, ortstName, geschlecht, staatsang, alter, haeufigkeit);
	}

	/**
	 * Funktion die eine EWR ArrayList mit den folgenden Parametern durchsucht,
	 * um eine Menge an Personen zu bestimmen
	 * @param ewrList Liste die durchsucht wird
	 * @param bezName Name des Bezirks
	 * @param geschlecht Geschlecht der Personengruppe
	 * @param staatsang Staatsangehörigkeit der Personengruppe
	 * @param alter Altersgruppe der Personengruppe
	 * @return Menge an personen, die die Parameter erfüllen
	 */
	public static int getNumberOf(ArrayList<EWR> ewrList, String bezName, int geschlecht, String staatsang,
			String alter) {
		int menge = 0;
		for (EWR ewr : ewrList) {
			if (ewr.bezName.equals(bezName) && ewr.geschlecht == geschlecht && ewr.staatsang.equals(staatsang)
					&& ewr.alter.equals(alter)) {
				menge += ewr.haeufigkeit;
				System.out.println("Found " + ewr.haeufigkeit + " Matches!");
			}
		}
		return menge;
	}

	/**
	 * Methode um die Attribute des EWR Objektes in das Format
	 * der Quelldatei in  einem String zu speichern
	 */
	public String toString() {
		String toString = this.bezID + ";";
		toString += this.bezName + ";";
		toString += this.ortstID + ";";
		toString += this.ortstName + ";";
		toString += this.geschlecht + ";";
		toString += this.staatsang + ";";
		toString += this.alter + ";";
		toString += this.haeufigkeit + ",00";
		return toString;
	}

	/**
	 * Funktion zur ausgabe der Array Liste im Format der Quelldatei
	 * @param ewrList Auszugebene Array Liste
	 */
	public static void printEWRList(ArrayList<EWR> ewrList) {
		for (EWR ewr : ewrList) {
			System.out.println(ewr.toString());
		}
	}

	public static void main(String args[]) {
		File sourceFile = new File("src/EWR_Ortsteile_Berlin_2015.csv");
		ArrayList<EWR> ewrList = readIn(sourceFile);
		printEWRList(ewrList);
		int menge = getNumberOf(ewrList, "Marzahn-Hellersdorf", 2, "D", "20_25");
		System.out.println("Es gibt im Bezirk Marzahn-Hellersdorf " + menge + " deutsche Frauen im Alter von 20 bis 25.");
	}
}
