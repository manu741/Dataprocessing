package p1DAO;

import java.sql.Date;

public class Reiziger {
	private int id;
	private String naam;
	private String gbdatum;
	
	public Reiziger(int i, String n, String gbd) {
		id = i;
		naam = n;
		gbdatum = gbd;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int i) {
		id = i;
	}
	
	public void setNaam(String n) {
		n = naam;
	}
	
	public String getGBdatum() {
		return gbdatum;
	}
	
	public void setGBdatum(String gbd) {
		gbdatum = gbd;
	}
	
	public boolean equals(Object obj) {
		boolean gelijkeObjecten = false;
		if (obj instanceof Reiziger) {
			Reiziger andereReiziger = (Reiziger) obj;
			if (this.id == andereReiziger.id) {
				gelijkeObjecten = true;
			}
		}
		return gelijkeObjecten;
	}
	public String toString() {
		return "ID: " + id + " heeft naam " + naam + " geboren op: " + gbdatum;
	}
}
