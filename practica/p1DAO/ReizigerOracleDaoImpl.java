package p1DAO;

import java.util.*;
public class ReizigerOracleDaoImpl implements ReizigerDao{
	ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();
	
	//alle objecten in de ArrayList reizigers returnen
	public List<Reiziger> findAll(){	//interface list<Reiziger> gebruiken, omdat interfaces zo veel mogelijk gebruikt moeten worden in methodes
		return reizigers;
	}
	
	//een reiziger object toevoegen aan de arrayList reizigers
	public Reiziger save(Reiziger reiziger){
		reizigers.add(reiziger);
		return reiziger;
	}
	
	//reiziger object verwijderen uit de arrayList, door de equals methode in klasse Reiziger aan te roepen met remove
	public boolean delete(Reiziger reiziger){
		return reizigers.remove(reiziger);
	}
	
	//de gegevens van een Reiziger object veranderen in de ArrayList reiziger
	public Reiziger update(Reiziger reiziger){
		int i = reizigers.indexOf(reiziger);		//de index van de te wijzigen reiziger opslaan in i
		if(i < 0) {									//als de reiziger niet gevonden wordt, return null
			return null;
		}
		reizigers.set(i, reiziger);					//het object veranderen op index i met de nieuwe reiziger die meegegeven wordt als een argument.
		return reiziger;
	}
	
	//een reiziger object vinden door op de datum te zoeken
	public List<Reiziger> findByGBdatum(String GBdatum){
		ArrayList<Reiziger> gbselection = new ArrayList<Reiziger>(); 	//nieuwe arrayList aanmaken, omdat er maar 1 object terug gegeven moet worden
		for(Reiziger item : reizigers) {								//loopen door de ArrayList reiziger
			if (item.getGBdatum().equals(GBdatum)) {					//het ingevoerde datum vergelijken met het geboortedatum uit de arrayList 
				gbselection.add(item); 									//als het gevonden wordt, dat wordt het hele object toegvoegd aan de aangemaakte arraylist gbselection
			}
		}
		return gbselection;
	}
	
}
