package de.tuberlin.snet.prog2.ue09.fileio;

import java.util.Comparator;

import de.tuberlin.snet.prog2.ue09.fileio.CSVDataEntry;

/**
 * each instance of CSVDataEntry represents a line of 
 * the file "EWR_Ortsteile_Berlin.csv" file
 * 
 *
 */
public class CSVDataEntry {

	/** Bezirknr. */
	int bezirknr;
	/** Bezirkname */
	String bezirksname;
	/** Ortsteil */
	int ortsteil;
	/** Ortsname */
	String ortsname;
	/** Geschlecht (männlich/weiblich) */
	int geschlecht;
	/** Staatsangehoerigkeit */
	String staatsangeh;
	/** Altersgruppe */
	String altersgruppe;
	/** Häufigkeit */
	double hauefigkeit;

	
	/**
	 * Constructor for a single entry which splits a string that represents one line of the .csv file
	 *  
	 * @param dataEntryLine string that represents one line of the .csv file
	 */
	public CSVDataEntry(String dataEntryLine) {
		String[] splitUpArr = dataEntryLine.split(";");
		bezirknr = Integer.parseInt(splitUpArr[0]);
		bezirksname = splitUpArr[1];
		ortsteil = Integer.parseInt(splitUpArr[2]);
		ortsname = splitUpArr[3];
		geschlecht = Integer.parseInt(splitUpArr[4]);
		staatsangeh = splitUpArr[5];
		altersgruppe = splitUpArr[6];
		hauefigkeit = Double.parseDouble(splitUpArr[7].replace(',', '.'));
	}

	/**
	 * Constructor with only three specific details of one line of the .csv file
	 * 
	 * 
	 * @param bezirknr
	 * @param bezirksname
	 * @param hauefigkeit
	 */
	public CSVDataEntry(int bezirknr, String bezirksname, double hauefigkeit) {
		this.bezirknr = bezirknr;
		this.bezirksname = bezirksname;
		this.hauefigkeit = hauefigkeit;
	}

	public int getBezirknr() {
		return bezirknr;
	}

	public void setBezirknr(int bezirknr) {
		this.bezirknr = bezirknr;
	}

	public String getBezirksname() {
		return bezirksname;
	}

	public void setBezirksname(String bezirksname) {
		this.bezirksname = bezirksname;
	}

	public int getOrtsteil() {
		return ortsteil;
	}

	public void setOrtsteil(int ortsteil) {
		this.ortsteil = ortsteil;
	}

	public String getOrtsname() {
		return ortsname;
	}

	public void setOrtsname(String ortsname) {
		this.ortsname = ortsname;
	}

	public int getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(int geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getStaatsangeh() {
		return staatsangeh;
	}

	public void setStaatsangeh(String staatsangeh) {
		this.staatsangeh = staatsangeh;
	}

	public String getAltersgruppe() {
		return altersgruppe;
	}

	public void setAltersgruppe(String altersgruppe) {
		this.altersgruppe = altersgruppe;
	}

	public double getHauefigkeit() {
		return hauefigkeit;
	}

	public void setHauefigkeit(double hauefigkeit) {
		this.hauefigkeit = hauefigkeit;
	}

	/**
	 * returns the instance to a CSVFormat with a specific given delimiter
	 * 
	 * @param delimiterType
	 * @return a string that represents an instance in a CSV Format
	 */
	public String toCSVFormat(String delimiterType) {
		return bezirksname + delimiterType + bezirksname + delimiterType + ortsteil + delimiterType + ortsname
				+ delimiterType + geschlecht + delimiterType + staatsangeh + delimiterType + altersgruppe
				+ delimiterType + hauefigkeit;
	}

	/**
	 * toString method
	 * 
	 */
	@Override
	public String toString() {
		return "CSVDataEntry [bezirknr=" + bezirknr + ", bezirksname=" + bezirksname + ", ortsteil=" + ortsteil
				+ ", ortsname=" + ortsname + ", geschlecht=" + geschlecht + ", staatsangeh=" + staatsangeh
				+ ", altersgruppe=" + altersgruppe + ", hauefigkeit=" + hauefigkeit + "]";
	}
	
	/**
	 * method that returns a Comparator that compares 2 CSVDataEntrys 
	 * by their field hauefigkeit
	 * 
	 * @return
	 */
	public static Comparator<CSVDataEntry> getAttributeHauefigkeit(){
		return new Comparator<CSVDataEntry>() {
			
			@Override
			public int compare(CSVDataEntry o1, CSVDataEntry o2) {
				return new Double(o1.getHauefigkeit()).compareTo(o2.getHauefigkeit());
			}
		};
	}

}
