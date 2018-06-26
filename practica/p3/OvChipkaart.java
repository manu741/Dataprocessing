package p3;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OvChipkaart {
	private int kaartNr;
	private Date geldigTot;
	private int klasse;
	private double saldo;
	private int id;
	private Reiziger eigenaar;
	private List<Product> producten = new ArrayList<Product>();

	public OvChipkaart(int nr, Date tot, int klasse, double saldo, int id) {
		kaartNr = nr;
		geldigTot = tot;
		this.klasse = klasse;
		this.saldo = saldo;
		this.id = id;
		
	}
	
	public int getKaartNr() {
		return kaartNr;
	}
	
	public void setKaartNr(int nr) {
		kaartNr = nr;
	}
	
	public Reiziger getEigenaar() {
		return eigenaar;
	}
	
	public void setEigenaar(Reiziger eigenaar) {
		this.eigenaar = eigenaar;
	}
	
	public Date getGeldigTot() {
		return geldigTot;
	}
	
	public void setGeldigTot(int tot) {
		kaartNr = tot;
	}
	
	public int getKlasse() {
		return klasse;
	}
	
	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Product> getProducten() {
		return producten;
	}

	public void setProducten(List<Product> producten) {
		this.producten = producten;
	}
	
	public String toString() {
		return "Kaartnr: " + kaartNr + " geldig tot: " + geldigTot + " klasse: " + klasse + " saldo: " + saldo + " van eigenaar: " + eigenaar.toString1() + " heeft product(en) " + producten.toString(); 
	}
}
