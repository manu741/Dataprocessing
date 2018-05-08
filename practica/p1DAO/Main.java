package p1DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

public class Main {
	public static void main(String[] args) throws ParseException {
		
		//nieuwe reiziger objecten aanmaken
		Reiziger r1 = new Reiziger(1, "manu", "04-05-1996");
		Reiziger r2 = new Reiziger(1, "manu", "04-05-1996");
		Reiziger r3 = new Reiziger(2, "jan", "04-05-1996");
		Reiziger r4 = new Reiziger(3, "bart", "04-06-1996");
		Reiziger r5 = new Reiziger(4, "henk", "04-05-1998");
		Reiziger r6 = new Reiziger(4, "jos", "04-07-1998");
		
		//ReizigerOracleDaoImpl definieren
		ReizigerDao rDAO = new ReizigerOracleDaoImpl();
		
		//reiziger objecten toevoegen aan de arrayList
		rDAO.save(r1);
		rDAO.save(r3);
		rDAO.save(r4);
		rDAO.save(r5);
		
		/*System.out.println(rDAO.findByGBdatum("04-05-1996"));*/
		
		//alle objecten in de ArrayList vinden door een nieuwe list te definieren en daarin te loopen met een for loop
		List<Reiziger>test = rDAO.findAll();
		
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		System.out.println("");
		
		//deleten van een object, ook als het een andere object is met dezelfde waardes
		boolean delres = rDAO.delete(r2);
		System.out.println(delres + "\n");
		
		//check of het object verwijderd is.
		test = rDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		System.out.println("");
		
		//ArrayList updaten
		rDAO.update(r6);
		
		test = rDAO.findAll();
		for(int i=0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		//een object zoeken met het ingevoerde (geboorte)datum
		System.out.println("");
		String datum = "04-06-1996";
		System.out.println("vinden met geboortedatum: " + datum);
		
		if(rDAO.findByGBdatum(datum).isEmpty()) {
			System.out.println("geen record gevonden met geboortedatum: " + datum);
		}else {
			for(Reiziger item : rDAO.findByGBdatum(datum)) {
				System.out.println(item);
			}
		}
		
	}
}
