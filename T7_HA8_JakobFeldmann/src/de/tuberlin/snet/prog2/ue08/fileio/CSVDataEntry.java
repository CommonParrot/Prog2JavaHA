package de.tuberlin.snet.prog2.ue08.fileio;

import java.util.Comparator;

import de.tuberlin.snet.prog2.ue08.fileio.CSVDataEntry;

public class CSVDataEntry {

	int bezirknr;
	String bezirksname;
	int ortsteil;
	String ortsname;
	int geschlecht;
	String staatsangeh;
	String altersgruppe;
	double hauefigkeit;

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

	public String toCSVFormat(String delimiterType) {
		return bezirksname + delimiterType + bezirksname + delimiterType + ortsteil + delimiterType + ortsname
				+ delimiterType + geschlecht + delimiterType + staatsangeh + delimiterType + altersgruppe
				+ delimiterType + hauefigkeit;
	}

	@Override
	public String toString() {
		return "CSVDataEntry [bezirknr=" + bezirknr + ", bezirksname=" + bezirksname + ", ortsteil=" + ortsteil
				+ ", ortsname=" + ortsname + ", geschlecht=" + geschlecht + ", staatsangeh=" + staatsangeh
				+ ", altersgruppe=" + altersgruppe + ", hauefigkeit=" + hauefigkeit + "]";
	}
	
	public static Comparator<CSVDataEntry> getAttributeHauefigkeit(){
		return new Comparator<CSVDataEntry>() {
			
			@Override
			public int compare(CSVDataEntry o1, CSVDataEntry o2) {
				return new Double(o1.getHauefigkeit()).compareTo(o2.getHauefigkeit());
			}
		};
	}

}
