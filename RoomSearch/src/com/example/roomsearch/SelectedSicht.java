package com.example.roomsearch;

import erstellen.DialogCreater;
import erstellen.StundenplanErstellen;
import Datenbank.ActivityRegistry;
import Datenbank.Listfiller;
import Datenbank.KontoControl;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectedSicht extends Activity implements OnClickListener, OnMenuItemClickListener {
	private Button ms;
	private String account;
	private int id;
	private Listfiller l = new Listfiller();
	private DialogCreater dc = new DialogCreater();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        account = intent.getExtras().getString("Account");
        id = intent.getExtras().getInt("ID");
        ActivityRegistry.finishAll();
        setContentView(R.layout.selected_sicht);
        ActivityRegistry.register(this);
       
        KontoControl.KONTO(account);
        
        ms = (Button) findViewById(R.id.Button_ms);
     
        if(!l.hasStundenplan(account)) {
        	ms.setEnabled(false);
        	ms.setTextColor(Color.DKGRAY);
        }
        ms.setOnClickListener(this);
	}

	/**
	 * Menu Optionen
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        //menu.getItem(3).setEnabled(false);
        for(int i = 0; i < menu.size(); i++) {
        	menu.getItem(i).setOnMenuItemClickListener(this);
        	if(KontoControl.getSPRACHE(account).equals("english")){
        		
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
        				if(menu.getItem(i).getSubMenu().getItem(k).getTitle().equals("Stundenplan erstellen")) {
        					if(!l.hasStundenplan(account)) {
        						menu.getItem(i).getSubMenu().getItem(k).setEnabled(false);
        					}
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

	public void onClick(View v) {
		if(v == ms) {
				Intent intent = new Intent(SelectedSicht.this, NavSicht.class);
				intent.putExtra("Account", account);
				intent.putExtra("ID", id);
				startActivity(intent);
		}
	}

	public boolean onMenuItemClick(MenuItem item) {
		if(item.getTitle().equals("Beenden") || item.getTitle().equals("close")){
			AlertDialog.Builder dialog = dc.getBeendenBuilder(this, KontoControl.getSPRACHE(account));
			dialog.show();
		}
		
		if(item.getTitle().equals("english")) {
			KontoControl.setSPRACHE(account, "english");
			startActivity(getIntent());
		}
		if(item.getTitle().equals("deutsch")) {
			KontoControl.setSPRACHE(account, "deutsch");
			startActivity(getIntent());
		}
		if(item.getTitle().equals("Stundenplan erstellen")) {
			Intent in = new Intent(SelectedSicht.this, StundenplanErstellen.class);
			in.putExtra("Account", account);
			startActivity(in);
		}
		if(item.getTitle().equals("Stundenplan entfernen")) {
			l.deleteStundenplan(account);
		}
		return false;
	}
	
	/**
	 * Beenden
	 */
	@Override
	public void onBackPressed() {
		AlertDialog.Builder dialog = dc.getBeendenBuilder(this, KontoControl.getSPRACHE(account));
		dialog.show();
		//super.onBackPressed();
		
	}
}
