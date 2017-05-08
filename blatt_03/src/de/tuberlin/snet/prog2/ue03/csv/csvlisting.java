package de.tuberlin.snet.prog2.ue03.csv;

/**
 * Entrys for the population
 * 
 * @author bartosch
 *
 */
public class csvlisting {
	private int Bezirknr;
	private String Bezirk;
	private int Ortsteil;
	private String Ortsteilname;
	private int Geschlecht;
	private char Staatsangehoerigkeit;
	private String Alter;
	// private float Haeufigkeit;
	private int Haeufigkeit;

	/**
	 * Constructor
	 * 
	 * @param Bezirknr
	 *            Bezirknummer
	 * @param Bezirk
	 *            Bezirk
	 * @param Ortsteil
	 *            Ortsteil
	 * @param Ortsteilname
	 *            OTName
	 * @param Geschlecht
	 *            Geschlaescht
	 * @param Staatsangehoerigkeit
	 *            SA
	 * @param Alter
	 *            Altersspanne
	 * @param Haeufigkeit
	 *            Anzahl an Menschen mit diesen Kriterien
	 */
	public csvlisting(String Bezirknr, String Bezirk, String Ortsteil, String Ortsteilname, String Geschlecht,
			String Staatsangehoerigkeit, String Alter, String Haeufigkeit) {
		this.Bezirknr = Integer.parseInt(Bezirknr);
		this.Bezirk = Bezirk;
		this.Ortsteil = Integer.parseInt(Ortsteil);
		this.Ortsteilname = Ortsteilname;
		this.Geschlecht = Integer.parseInt(Geschlecht);
		this.Staatsangehoerigkeit = Staatsangehoerigkeit.charAt(0);
		this.Alter = Alter;
		// this.Haeufigkeit = Float.parseFloat(Haeufigkeit);
		this.Haeufigkeit = Integer.parseInt(Haeufigkeit);
	}

	/**
	 * getter
	 * 
	 * @return Bezirknr
	 */
	public int getBezirknr() {
		return Bezirknr;
	}

	/**
	 * getter
	 * 
	 * @return Bezirk
	 */
	public String getBezirk() {
		return Bezirk;
	}

	/**
	 * getter
	 * 
	 * @return Ortsteil
	 */
	public int getOrtsteil() {
		return Ortsteil;
	}

	/**
	 * getter
	 * 
	 * @return OTName
	 */
	public String getOrtsteilname() {
		return Ortsteilname;
	}

	/**
	 * getter
	 * 
	 * @return Gender
	 */
	public int getGeschlecht() {
		return Geschlecht;
	}

	/**
	 * getter
	 * 
	 * @return SA
	 */
	public char getStaatsangehoerigkeit() {
		return Staatsangehoerigkeit;
	}

	/**
	 * getter
	 * 
	 * @return Alter
	 */
	public String getAlter() {
		return Alter;
	}

	/**
	 * getter
	 * 
	 * @return Haeufigkeit
	 */
	public int getHaeufigkeit() {
		return Haeufigkeit;
	}

	/**
	 * getter
	 * 
	 * @return Attributes as String
	 */
	public String toString() {
		String sex;
		String SA;
		if (this.Geschlecht == 1) {
			sex = "Male";
		} else if (this.Geschlecht == 2) {
			sex = "Female";
		} else {
			sex = "Unknown for some reason";
		}
		if (this.Staatsangehoerigkeit == 'D') {
			SA = "Deutsch";
		} else if (this.Staatsangehoerigkeit == 'A') {
			SA = "Auslaendisch";
		} else {
			SA = "Unknown for some reason";
		}
		String ausgabe = ("[Bezirk: " + this.Bezirknr + ",Bez.Name: " + Bezirk + ",Ortsteil: " + this.Ortsteil
				+ ",Ortst.Name: " + this.Ortsteilname + ", Geschlecht: " + sex + ",Staatsangehoerigkeit: " + SA
				+ ",Altersgruppe: " + this.Alter + ", Haeufigkeit: " + this.Haeufigkeit + "]");
		return ausgabe;

	}
}
