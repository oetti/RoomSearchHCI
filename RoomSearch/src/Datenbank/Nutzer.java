package Datenbank;

import java.util.ArrayList;
import java.util.HashMap;

public class Nutzer {
	private String name;
	private String account;
	private int identität;
	private static HashMap<String,ArrayList<String>> nutzer;
	
	public Nutzer() {
		if(nutzer == null) {
			nutzer = new HashMap<String, ArrayList<String>>();
			fill();
		}	
	}
	
	public void hinzufuegen (String account, ArrayList<String> daten) {
		if(nutzer.containsKey(account)) {
			for(int i = 0; i < daten.size(); i++) {
				nutzer.get(account).add(daten.get(i));
			}
		} else {
			
		}
	}
	public boolean accountVorhanden (String account) {
		return nutzer.containsKey(account);
	}
	public void setProfil(String[] daten) {
		//  account; passwort; vorname; nachname; mail; D,S,M;
		ArrayList<String> benutzerDaten = new ArrayList<String>();
		String[] test = daten;
		String id = test[0];
		for(int i = 0; i < test.length; i++) {
			benutzerDaten.add(daten[i]);
		}
		nutzer.put(id, benutzerDaten);
	}
	
	public boolean check(String[] daten) {
		return ch(daten);
	}
	
	private boolean ch(String[] daten) {
		boolean status = false;
		try {
			String nutzerArt = (String) nutzer.get(daten[0]).get(2);
				String control = (String) nutzer.get(daten[0]).get(1);
				if(control.equals(daten[1])) {
					setName((String) nutzer.get(daten[0]).get(3));
					setAccount((String) nutzer.get(daten[0]).get(0));
					if(nutzerArt.equals("d")) {
						setIden(0);
					}
					if(nutzerArt.startsWith("s")) {
						setIden(1);
					}
					if(nutzerArt.startsWith("master")) {
						setIden(3);
					}
					status = true;
				}
		} catch (Exception e){
			System.out.println(e);
		}
		return status;
	}
	
	public int getIdentität() {
		return this.identität;
	}
	
	public String getName() {
		return this.name;
	}
	
	private void setAccount(String account) {
		this.account=account;
	}
	
	public String getAccount() {
		return this.account;
	}
	
	private void setIden(int id) {
		this.identität = id;
	}
	private void setName(String name) {
		this.name = name;
	}
	
	public void editProfil(String[] daten) {
		
	}
	
	private void fill() {
		String[] nutzerStudent = {"s12345", "a", "s", "Nadine", "Chaumar", "n.chaumar@freenet.de"};
		String[] nutzerStudent2 = {"s54321", "a", "s", "Jochen", "Peters", "j.peters@gmx.net"};
		String[] nutzerDozent = {"e.bommel61", "1234", "d", "Erwin", "Bommel", "bommel@beuth.de"};
		setProfil(nutzerStudent);
		setProfil(nutzerStudent2);
		setProfil(nutzerDozent);
	}
}
