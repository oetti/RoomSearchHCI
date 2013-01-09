package Datenbank;

import java.util.ArrayList;
import java.util.HashMap;

public class Listfiller {
	private static HashMap<String, ArrayList<ArrayList<String>>> NUTZERPLAN;
	private ArrayList<String> mo = new ArrayList<String>();
	private ArrayList<String> di = new ArrayList<String>();
	private ArrayList<String> mi = new ArrayList<String>();
	private ArrayList<String> don = new ArrayList<String>();
	private ArrayList<String> fr = new ArrayList<String>();
	private ArrayList<String> haus = new ArrayList<String>();
	private ArrayList<String> zeiten = new ArrayList<String>();
	private ArrayList<String> vorlesungen1 = new ArrayList<String>();
	private ArrayList<String> vorlesungen2 = new ArrayList<String>();
	private ArrayList<String> vorlesungen3 = new ArrayList<String>();
	private ArrayList<String> vorlesungen4 = new ArrayList<String>();
	private ArrayList<String> vorlesungen5 = new ArrayList<String>();
	private ArrayList<String> vorlesungen6 = new ArrayList<String>();
	private ArrayList<String> vorlesungen7 = new ArrayList<String>();
	private ArrayList<String> bauwesen = new ArrayList<String>();
	
	
	public Listfiller() {
		if(NUTZERPLAN == null) {
			NUTZERPLAN = new HashMap<String, ArrayList<ArrayList<String>>>();
			nutzerPlan();
		}
		fuellen();
	}
	
	private void fuellen() {
		
		
		zeiten.add(" 8:00 - 9:30");
		zeiten.add("10:00 - 11:30");
		zeiten.add("12:15 - 13:45");
		zeiten.add("14:15 - 15:45");
		zeiten.add("16:00 - 17:30");
		zeiten.add("17:45 - 19:15");
		zeiten.add("19:30 - 21:00");
		
		// 8:00 - 9:30
		vorlesungen1.add("B 323 Computergrafik II");
		vorlesungen1.add("B 542 Mobiles Web");
		vorlesungen1.add("B 343 Computergrafik II ‹bg.");
		vorlesungen1.add("D 114 Algorithmen ‹bg.");
		vorlesungen1.add("B 323 Algorithmen");
		// 10:00 - 11:30
		vorlesungen2.add("B 323 Algorithmen");
		vorlesungen2.add("B 507 Grundlagen wissen. Arbeitens");
		vorlesungen2.add("D 114 Human Computer Interaction ‹bg.");
		vorlesungen2.add("B 325 Human Computer Interaction");
		// 12:15 - 13:45
		vorlesungen3.add("B 341 Medienprojekt I");
		vorlesungen3.add("D E16a L Software-Engineering II ‹bg.");
		vorlesungen3.add("D 209 Software-Engineering II");
		// 14:15 - 15:45
		vorlesungen4.add("B 323 Computergrafik I");
		vorlesungen4.add("B 542 Mobiles Web");
		vorlesungen4.add("B 343 Computergrafik I ‹bg.");
		vorlesungen4.add("A 114 Mathematik I");
		vorlesungen4.add("A 223 Mathematik II");
		vorlesungen4.add("A 123 Mathematik II");
		vorlesungen4.add("B 507 Software-Engineering I");
		vorlesungen4.add("D 114 Human Computer Interaction ‹bg.");
		// 16:00 - 17:30
		vorlesungen5.add("D E16a L Software-Engineering I ‹bg.");
		vorlesungen5.add("D 209 Software-Engineering I");
		vorlesungen5.add("B 323 Medienprojekt II");
		vorlesungen5.add("B 542 Mobiles Web");
		vorlesungen5.add("B 343 Computergrafik I ‹bg.");
		vorlesungen5.add("A 114 Mathematik I");
		// 17:45 -
		vorlesungen6.add("Keine");
		vorlesungen7.add("Keine");
		
		// Geb‰ude
		haus.add("Beuth (A)");
		haus.add("Gauﬂ (B)");
		haus.add("Grashof (C)");
		haus.add("Bauwesen (D)");
		
		// Besondere R‰ume
		bauwesen.add("Ausw‰hlen");
		bauwesen.add("Mensa");
		bauwesen.add("Biblothek");
	}
	
	private void nutzerPlan() {
		 // Stundenplan
		mo.add("B 323 Computergrafik II");
		mo.add("B 542 Mobiles Web");
		mo.add("B 343 Computergrafik II ‹bg.");
		
		di.add("");
		di.add("D 114 Algorithmen ‹bg.");
		di.add("B 323 Algorithmen");
		di.add("B 323 Algorithmen");
		di.add("B 507 Grundlagen wissen. Arbeitens");
		
		mi.add("D 114 Human Computer Interaction ‹bg.");
		mi.add("B 325 Human Computer Interaction");
		
		don.add("B 341 Medienprojekt I");
		
		fr.add("D E16a L Software-Engineering II ‹bg.");
		fr.add("D 209 Software-Engineering II");
		
		ArrayList<ArrayList<String>> stundenplan = new ArrayList<ArrayList<String>>();
		stundenplan.add(mo);
		stundenplan.add(di);
		stundenplan.add(mi);
		stundenplan.add(don);
		stundenplan.add(fr);
		
		NUTZERPLAN.put("s12345", stundenplan);
	}
	
	public ArrayList<String> getStundenplan(String account, int index) {
		return NUTZERPLAN.get(account).get(index);
	}
	
	public boolean hasStundenplan(String account) {
		return NUTZERPLAN.containsKey(account);
	}
	
	public void deleteStundenplan(String account) {
		NUTZERPLAN.remove(account);
	}
	
	public void createStundenplan(String account, ArrayList<ArrayList<String>> daten) {
		NUTZERPLAN.put(account, daten);
	}
	
	public ArrayList<String> haus() {
		return this.haus;
	}
	
	public ArrayList<String> zeit() {
		return this.zeiten;
	}
	
	public ArrayList<String> bauwesen() {
		return this.bauwesen;
	}
	
	public ArrayList<String> vorlesungenListe(int vorlesungen) {
		ArrayList<String> liste = vorlesungen7;
		
		if(vorlesungen == 1) {
			liste = vorlesungen1;
		}
		if(vorlesungen == 2) {
			liste = vorlesungen2;
		}
		if(vorlesungen == 3) {
			liste = vorlesungen3;
		}
		if(vorlesungen == 4) {
			liste = vorlesungen4;
		}
		if(vorlesungen == 5) {
			liste = vorlesungen5;
		}
		if(vorlesungen == 6) {
			liste = vorlesungen6;
		}
		
		return liste;
	}
}
