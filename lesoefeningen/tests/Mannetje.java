package tests;

public class Mannetje {
	private String naam;
	private int leeftijd;
	
	public Mannetje(String nm, int lf) {
		naam = nm;
		leeftijd = lf;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public int getLeeftijd() {
		return leeftijd;
	}
	
	public void setNaam(String nm) {
		naam = nm;
	}
	
	public void setLeeftijd(int lf) {
		leeftijd =lf;
	}
	
	public String toString() {
		return "naam: " + naam;
	}
}
