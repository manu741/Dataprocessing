package tests;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Mannetje> lijst  = new ArrayList<Mannetje>();
		
		Mannetje manu = new Mannetje("manu", 21);
		Mannetje husso = new Mannetje("husso", 22);
		lijst.add(manu);
		lijst.add(husso);
		
		System.out.println("test");
		for(Mannetje item: lijst) {
			if(item.getLeeftijd() == manu.getLeeftijd()) {
				System.out.println(manu.getLeeftijd());
			}
		}
	}

}
