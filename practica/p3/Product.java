package p3;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private int productNr;
	private String productNaam;
	private String beschrijving;
	private double prijs;
	private List<OvChipkaart> kaartList = new ArrayList<OvChipkaart>();
	
	public Product(int productNr, String productNaam, String beschrijving, double prijs) {
		super();
		this.productNr = productNr;
		this.productNaam = productNaam;
		this.beschrijving = beschrijving;
		this.prijs = prijs;
	}
	
	public List<OvChipkaart> getKaartList() {
		return kaartList;
	}

	public void setKaartList(List<OvChipkaart> kaart) {
		this.kaartList = kaart;
	}

	public int getProductNr() {
		return productNr;
	}
	public void setProductNr(int productNr) {
		this.productNr = productNr;
	}
	public String getProductNaam() {
		return productNaam;
	}
	public void setProductNaam(String productNaam) {
		this.productNaam = productNaam;
	}
	public String getBeschrijving() {
		return beschrijving;
	}
	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}
	public double getPrijs() {
		return prijs;
	}
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
	
	public String toString() {
		return "productnr: " + productNr + " productNaam: " + productNaam + " beschrijving: " 
				+ beschrijving + " prijs: " + prijs + " staat op ov-chipkaarten: " + kaartList.toString();
	}
}
