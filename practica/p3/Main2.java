package p3;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.sql.Date;

public class Main2 {
	public static void main(String[] args) throws ParseException, SQLException {
		OvChipkaartDaoImpl ovDAO = new OvChipkaartDaoImpl();
		OvChipkaart ov1 = new OvChipkaart(11111, Date.valueOf("1999-01-01"), 1, 30.00, 6);
		OvChipkaart ov2 = new OvChipkaart(22222, Date.valueOf("1999-01-01"), 1, 30.50, 6);
		OvChipkaart ov3 = new OvChipkaart(33333, Date.valueOf("1999-01-03"), 2, 31.00, 7);
		OvChipkaart ov4 = new OvChipkaart(44444, Date.valueOf("1999-01-04"), 2, 31.50, 7);
		OvChipkaart ov5 = new OvChipkaart(55555, Date.valueOf("1999-01-04"), 2, 32.00, 7);
		
		Reiziger r1 = new Reiziger(2, "Don", "", "corleone", Date.valueOf("1999-01-01"));
		ReizigerDao rDAO = new ReizigerOracleDaoImpl();
/*------alle records van de tabel ophalen-------*/
		List<OvChipkaart> test = ovDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		System.out.println();
		
/*------ovChipkaart opslaan in de database------*/
		System.out.println("kaarten toevoegen aan database:");
		ovDAO.save(ov1);
		ovDAO.save(ov2);
		ovDAO.save(ov3);
		ovDAO.save(ov4);
		ovDAO.save(ov5);
		test = ovDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		System.out.println();
		
/*------delete functie met ovChipkaart object testen.------*/
		System.out.println("3 laatste kaarten verwijderen uit database: ");
		ovDAO.delete(ov5);
		ovDAO.delete(ov4);
		ovDAO.delete(ov3);
		test = ovDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		System.out.println();
		
/*------update functie met OvChipkaart object testen.------*/
		System.out.println("kaart 22222 updaten naar saldo 30.55");
		ov2.setSaldo(30.55);
		ovDAO.update(ov2);
		test = ovDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		System.out.println();
		
/*------vinden met geldig tot datum-----*/		
		System.out.println("met geldig tot datum vinden:");
		List<OvChipkaart> gtTest = ovDAO.findByGeldigTot(ov2.getGeldigTot());
		for(int i=0; i < gtTest.size(); i++) {
			System.out.println(gtTest.get(i));
		}
		
		/*------reset de test:-----*/
		ovDAO.delete(ov5);
		ovDAO.delete(ov4);
		ovDAO.delete(ov3);
		ovDAO.delete(ov2);
		ovDAO.delete(ov1);
		
		//ovDAO.findByOwner(2);
		System.out.println("vind ov met id owner 2");
		test = ovDAO.findByOwner(r1);
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
	}
}
