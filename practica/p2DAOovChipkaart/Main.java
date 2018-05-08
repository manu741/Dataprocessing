package p2DAOovChipkaart;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.sql.Date;

public class Main {
	public static void main(String[] args) throws ParseException, SQLException {
		ReizigerOracleDaoImpl rDAO = new ReizigerOracleDaoImpl();
		Reiziger r1 = new Reiziger(10, "tony", "Van", "Montana", Date.valueOf("1980-01-01"));
		Reiziger r2 = new Reiziger(8, "pablo", "el", "Escobar", Date.valueOf("1999-01-01"));
		Reiziger r3 = new Reiziger(11, "G", "El", "Chapo", Date.valueOf("1999-01-01"));
		Reiziger r4 = new Reiziger(9, "Don", "", "corleone", Date.valueOf("1999-01-01"));
		
/*------alle records van de tabel ophalen-------*/
		List<Reiziger> test = rDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		System.out.println();
		
/*------reiziger object opslaan in de database------*/
		System.out.println("reizigers toevoegen aan database: ");
		rDAO.save(r1);
		rDAO.save(r2);
		rDAO.save(r3);
		rDAO.save(r4);
		test = rDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		System.out.println();
		
/*------delete functie met reiziger object testen.------*/
		System.out.println("reiziger pablo escobar verwijderen uit database: ");
		rDAO.delete(r2);
		test = rDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		System.out.println();
		
/*------update functie met reiziger object testen.------*/
		System.out.println("reiziger met id 6 updaten: ");
		rDAO.update(r4);
		test = rDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		System.out.println();
		
		System.out.println("met geboortdedatum vinden:");
		List<Reiziger> gbTest = rDAO.findByGBdatum(r3.getGBdatum());
		for(int i=0; i < gbTest.size(); i++) {
			System.out.println(gbTest.get(i));
		}
		
		System.out.println();
		
		System.out.println("vinden met id 2: ");

/*-----findbyID-----*/		
		System.out.println(rDAO.findById(2));
		
		rDAO.delete(r1);
		rDAO.delete(r2);
		rDAO.delete(r3);
		rDAO.delete(r4);
	}
}
