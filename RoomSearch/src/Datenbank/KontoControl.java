package Datenbank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import storage.Saver;


public class KontoControl implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String SPRACHE;
	private static boolean AUSWAHL;
	//private static String KEY = "kontoKontrolle1234";
	//private static String FILENAME = "as";
	
	private static HashMap<String, Object[]> NUTZERKONTOS;
	//private static byte[] kontenBytes;
	
	public static boolean SETTINGS () {
		return (SPRACHE==null);
	}
	
	public static void KONTO(String account) {
		if(NUTZERKONTOS == null) {
			NUTZERKONTOS = new HashMap<String, Object[]>();
			Object[] daten = new Object[2];
			NUTZERKONTOS.put(account, daten);
			if(SPRACHE != null) {
				NUTZERKONTOS.get(account)[0] = getSPRACHE();/*
			NUTZERKONTOS = load();
			System.out.println(NUTZERKONTOS.get(account)[0]);
			NUTZERKONTOS.get(account)[0] = getSPRACHE();*/
			}
		}
		
		if (!NUTZERKONTOS.containsKey(account)){
			Object[] daten = new Object[2];
			NUTZERKONTOS.put(account, daten);
			if(SPRACHE != null) {
				NUTZERKONTOS.get(account)[0] = getSPRACHE();
			}
		}
	}
	
	public static void setSPRACHE(String sprache) {
		SPRACHE = sprache;
	}
	
	public static void setSPRACHE(String account, String sprache) {
		NUTZERKONTOS.get(account)[0] = sprache;
	}
	
	public static String getSPRACHE() {
		return SPRACHE;
	}
	
	public static String getSPRACHE(String account) {
		//System.out.println("Nutzerkonto"+NUTZERKONTOS.get(account)[0]);
		return (String) NUTZERKONTOS.get(account)[0];
		
	}
	
	public static void setAUSWAHL(String account, boolean auswahl) {
		NUTZERKONTOS.get(account)[1] = auswahl;
		//AUSWAHL = auswahl;
	}
	
	public static boolean getAUSWAHL(String account) {
		return (Boolean) NUTZERKONTOS.get(account)[1];
	}
	
	public static void setAUSWAHL(boolean auswahl) {
		AUSWAHL = auswahl;
	}
	
	public static boolean getAUSWAHL() {
		return AUSWAHL;
	}
	
	public void save(Context context) {
		/*try {
			String s = Saver.speichernASKonten(NUTZERKONTOS);
	        if(s != null && s.length() > 0){
	                Editor edit = context.getSharedPreferences(FILENAME, 0).edit();
	                edit.putString(KEY, s);
	                edit.commit();
	        }
	    }catch (Exception e){
	        System.out.println(e);
	    }
		*/
		//kontenBytes = Saver.serializeObject(NUTZERKONTOS);
		//System.out.println(kontenBytes.toString());
	}
	
	@SuppressWarnings("unchecked")
	private static HashMap<String, Object[]> load() {
		/*if(settings.contains("as")){
	        String s =  getSharedPreferences(FILENAME, 0).getString(KEY, "");
	        if(s.length() > 0){
	            try{
	                return  Saver.loaded(s);
	            }
	            catch (Exception e){
	                return null;
	            }
	        }
	    //}
	    return null;
		
		//return (HashMap<String, Object[]>) Saver.deserializeObject(kontenBytes);*/
	return null;
	}
}
