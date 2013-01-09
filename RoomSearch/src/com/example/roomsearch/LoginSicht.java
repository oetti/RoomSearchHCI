package com.example.roomsearch;

import erstellen.DialogCreater;
import erstellen.ProfilErstellenSicht;
import Datenbank.ActivityRegistry;
import Datenbank.Nutzer;
import Datenbank.KontoControl;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * In dieser Ansicht kann jemand der ein Profil hat sich einloggen und damit
 * auf seine Daten zugreifen.
 * 
 * Als Gast kann man einfach den Button drücken um weiter zukommen.
 * 
 * @author Andreas Oettinger
 *
 */
public class LoginSicht extends Activity implements OnClickListener, OnMenuItemClickListener {
	private Button login, gast, erstellen;
	// Die Klasse überprüft den Accountnamen und das dazu gehörige Passwort
	private Nutzer ss = new Nutzer();
	private DialogCreater dc = new DialogCreater();
	
	/**
	 * Ansicht setzen
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sicht);
        ActivityRegistry.finishAll();
        ActivityRegistry.register(this);
        
        if(KontoControl.SETTINGS()) {
        	KontoControl.setSPRACHE("deutsch");
        }
        
        // Sende-Button
        login = (Button) findViewById(R.id.ein_button);
        login.setOnClickListener(this);
        
        // Gast-Button
        gast = (Button) findViewById(R.id.gast_button);
        gast.setOnClickListener(this);
        
        // Profil erstellen
        erstellen = (Button) findViewById(R.id.profil_erstellen);
        erstellen.setOnClickListener(this);
        
        if(KontoControl.getSPRACHE().equals("english")) {
        	englishChange();
        }
	}

	/**
	 * Klick-Event
	 */
	public void onClick(final View v) {
		
		//v.startAnimation(animAlpha);
		if(v == erstellen) {
			Intent intent = new Intent(LoginSicht.this, ProfilErstellenSicht.class);
			startActivity(intent);
		}
		
		 Handler handler=new Handler();
	      	Runnable r = new Runnable() {
	      		public void run() {
		// Wird Senden-Button gedrückt dann mache das
		if(v == login) {
			// HRZ-Kennung und Passwort
			TextView name = (TextView) findViewById(R.id.matText);
			TextView pw = (TextView) findViewById(R.id.pwText);
			
			name.setText("s12345");
			pw.setText("a");
			// Daten ins Array packen
			String [] daten = {name.getText().toString(),pw.getText().toString()};
			
			// Kontrolliere die Daten
			if(ss.check(daten)) {
					// Sind die Daten korrekt wechsel die Ansicht
					Intent in = new Intent(LoginSicht.this, UebergangSicht.class);
					// 
					in.putExtra("Account", ss.getAccount());
					// übernehme den Vornamen des Users
					in.putExtra("Vorname", ss.getName());
					// übernehme die Identität (Student oder Dozent)
					in.putExtra("ID", ss.getIdentität());
					// starte die neue Activity
					startActivity(in);
			
			} else {
				Toast toast = Toast.makeText(v.getContext(), "HRZ-Kennung oder Passwort sind nicht korrekt!", Toast.LENGTH_SHORT);
				toast.show(); 
			}
		}
		
		if(v == gast) {
			Intent in = new Intent(LoginSicht.this, UebergangSicht.class);
			// übernehme den Vornamen des Users
			if(KontoControl.getSPRACHE().equals("english")) {
				in.putExtra("Vorname", "visitor");	
			} else {
				in.putExtra("Vorname", "Besucher");	
			}
			
			// übernehme die Identität (Student oder Dozent)
			in.putExtra("ID", 2);
			// starte die neue Activity
			startActivity(in);
		}
	      		}
	      	}; 
			handler.postDelayed(r, 20);
	}
	
	/**
	 * Menu Optionen
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        menu.getItem(3).setEnabled(false);
        for(int i = 0; i < menu.size(); i++) {
        	menu.getItem(i).setOnMenuItemClickListener(this);
        	if(KontoControl.getSPRACHE().equals("english")){
        		
        		if(menu.getItem(i).toString().equals("Sprache")) {
        			menu.getItem(i).setTitle("language");
        		}
        		if(menu.getItem(i).toString().equals("Hilfe")) {
        			menu.getItem(i).setTitle("help");
        		}
        		if(menu.getItem(i).toString().equals("Einstellungen")) {
        			menu.getItem(i).setTitle("settings");
        		}
        		if(menu.getItem(i).toString().equals("Profil")) {
        			menu.getItem(i).setTitle("profile");
        		}
        		if(menu.getItem(i).toString().equals("Beenden")) {
        			menu.getItem(i).setTitle("close");
        		}
        		
        		if(menu.getItem(i).hasSubMenu()) {
        			for(int k = 0; k < menu.getItem(i).getSubMenu().size(); k++) {
        				menu.getItem(i).getSubMenu().getItem(k).setOnMenuItemClickListener(this);
        				if(menu.getItem(i).getSubMenu().getItem(k).getTitle().equals("english")) {
        					menu.getItem(i).getSubMenu().getItem(k).setTitle("englisch");
        					menu.getItem(i).getSubMenu().getItem(k).setEnabled(false);
        				}
        				if(menu.getItem(i).getSubMenu().getItem(k).getTitle().equals("german")) {
        					menu.getItem(i).getSubMenu().getItem(k).setTitle("deutsch");
        				}
        				if(menu.getItem(i).getSubMenu().getItem(k).getTitle().equals("Helligkeit")) {
        					menu.getItem(i).getSubMenu().getItem(k).setTitle("brightness");
        				}
        				if(menu.getItem(i).getSubMenu().getItem(k).getTitle().equals("Profil bearbeiten")) {
        					menu.getItem(i).getSubMenu().getItem(k).setTitle("edit profile");
        				}
        			}
        		}
        	} else {
        		if(menu.getItem(i).hasSubMenu()) {
        			for(int k = 0; k < menu.getItem(i).getSubMenu().size(); k++) {
        				menu.getItem(i).getSubMenu().getItem(k).setOnMenuItemClickListener(this);
        				if(menu.getItem(i).getSubMenu().getItem(k).getTitle().equals("german")) {
        					menu.getItem(i).getSubMenu().getItem(k).setEnabled(false);
        				}
        			}	
        		}
        	}
        }
        
        return true;
    }

	public boolean onMenuItemClick(MenuItem item) {
		if(item.getTitle().equals("Beenden") || item.getTitle().equals("close")){
			AlertDialog.Builder dialog = dc.getBeendenBuilder(this, KontoControl.getSPRACHE());
			dialog.show();
		}
		
		if(item.getTitle().equals("english")) {
			KontoControl.setSPRACHE("english");
			startActivity(getIntent());
		}
		if(item.getTitle().equals("deutsch")) {
			KontoControl.setSPRACHE("deutsch");
			startActivity(getIntent());
		}
		return false;
	}
	
	/**
	 * Zurück Ansicht
	 */
	@Override
	public void onBackPressed() {
		// TODO Automatisch generierter Methodenstub
		AlertDialog.Builder dialog = dc.getBeendenBuilder(this, KontoControl.getSPRACHE());
		dialog.show();
	}
	
	private void englishChange() {
		TextView welcome = (TextView)findViewById(R.id.welcome_login);
    	welcome.setText("Welcome at  Room Search");
    	TextView info1 = (TextView)findViewById(R.id.info_login);
    	info1.setText("If you have a profile, please log in.");
    	TextView info2 = (TextView)findViewById(R.id.Text_login_info2);
    	info2.setText("If you are a visitor, press the \"guess\" button.");
    	TextView info3 = (TextView)findViewById(R.id.Text_login_info3);
    	info3.setText("To create a profile, press \"create profile\".");
    	TextView acc = (TextView)findViewById(R.id.hrz_text);
    	acc.setText("Account name");
    	TextView ps = (TextView)findViewById(R.id.pw_text);
    	ps.setText("Password");
    	erstellen.setText("Create profile");
    	gast.setText("Guest");
	}
}
