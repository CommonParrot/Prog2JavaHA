package de.tuberlin.snet.prog2.ue03.berlinsEinwohner;

public class Berliner {
	
	String bezirk;
	String bezName;
	String ortsteil;
	String ortsname;
	String geschl;
	String staatsange;
	String altersgr;
	String häufigkeit;
	
	public String getBezirk() {
		return bezirk;
	}
	public String getBezName() {
		return bezName;
	}
	public String getOrtsteil() {
		return ortsteil;
	}
	public String getOrtsname() {
		return ortsname;
	}
	public String getGeschl() {
		return geschl;
	}
	public String getStaatsangeh() {
		return staatsange;
	}
	public String getAltersgr() {
		return altersgr;
	}
	public String getHäufigkeit() {
		return häufigkeit;
	}
	
	Berliner (String bezi, String bezN, String ortst, String ortsn, String ges, String staatsan, String alters, String häuf){
		
		bezirk = bezi;
		bezName = bezN;
		ortsteil = ortst;
		ortsname = ortsn;
		geschl = ges;
		staatsange = staatsan;
		altersgr = alters;
		häufigkeit = häuf;
		
	}
	
}
