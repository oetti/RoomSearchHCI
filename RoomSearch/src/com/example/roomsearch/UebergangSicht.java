package com.example.roomsearch;

import Datenbank.ActivityRegistry;
import Datenbank.KontoControl;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class UebergangSicht extends Activity {
	private Handler handler;
	private String account, name;
	private TextView text;
	private int id;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        // Account
        account = intent.getExtras().getString("Account");
        // Vorname
        name = intent.getExtras().getString("Vorname");
        // Identität
        id = intent.getExtras().getInt("ID");
        setContentView(R.layout.uebergang_sicht);
        ActivityRegistry.register(this);
        
        // Begruessung beim laden
        text = (TextView) findViewById(R.id.begruessung_text);
        if(id == 2) {
        	text.setText("Hallo " + name + ".\n\nDein Standort wird ermittelt, das kann etwas dauern." );
        } else {
        	text.setText("Hallo " + name + ".\n\nDeine Daten werden geladen, das kann etwas dauern." );
        }
       
        if(KontoControl.getSPRACHE().equals("english")) {
        	englishChange();
        }
      	
        // thread zum Background laden
        handler=new Handler();
      	Runnable r = new Runnable() {
      		public void run() {
      			// Gastansicht
      			if(id == 2) {
      				final Intent in = new Intent (UebergangSicht.this, GastNavSicht.class);
      				startActivity(in); 
      			} else {
      				// angemeldete Ansichten
      				final Intent in = new Intent (UebergangSicht.this, SelectedSicht.class);
      				in.putExtra("Account", account);
      				in.putExtra("ID", id);
      				startActivity(in);   
      			}
		    }
		}; 
		handler.postDelayed(r, 2000);
	}

	private void englishChange() {
		TextView load = (TextView)findViewById(R.id.laden);
		load.setText("loaded...");
		if(id == 2) {
        	text.setText("Hello " + name + ".\n\nYour data is being loacted. This take a while.");
        } else {
        	 text.setText("Hallo " + name + ".\n\nYour position is being loaded. This take a while.");
        }
	}
       
}
