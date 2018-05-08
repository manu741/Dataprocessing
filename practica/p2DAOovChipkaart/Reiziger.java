package p2DAOovChipkaart;

import java.sql.Date;
import java.util.ArrayList;

public class Reiziger {
	private int id;
	private String naam;
	private Date gbdatum;
	private String voorletter;
	private String tussenvoegsel;
	private ArrayList<OvChipkaart> ovList = new ArrayList<OvChipkaart>();
	
	public Reiziger(int i, String v, String t, String n, Date gbd) {
		id = i;
		voorletter = v;
		tussenvoegsel = t;
		naam = n;
		gbdatum = gbd;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public void setNaam(String n) {
		n = naam;
	}
	
	public Date getGbdatum() {
		return gbdatum;
	}

	public void setGbdatum(Date gbdatum) {
		this.gbdatum = gbdatum;
	}

	public ArrayList<OvChipkaart> getOvList() {
		return ovList;
	}

	public void setOvList(ArrayList<OvChipkaart> ovList) {
		this.ovList = ovList;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int i) {
		id = i;
	}
	
	public Date getGBdatum() {
		return gbdatum;
	}
	
	public void setGBdatum(Date gbd) {
		gbdatum = gbd;
	}
	
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}
	
	public void setTussenvoegsel(String tussen) {
		tussenvoegsel = tussen;
	}
	
	public String getVoorletter() {
		return voorletter;
	}
	
	public void setVoorletter(String letter) {
		voorletter = letter;
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
		String s = "ID: " + id + " Naam: " + voorletter + ". ";
		if( tussenvoegsel == null) {
			s +=  naam + " geboortedatum: " + gbdatum;
		}else {
			s +=  tussenvoegsel + " " + naam + " geboortedatum: " + gbdatum;
		}
		return s;
	}
}
