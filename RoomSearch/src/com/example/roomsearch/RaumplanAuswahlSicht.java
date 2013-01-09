package com.example.roomsearch;

import Datenbank.ActivityRegistry;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RaumplanAuswahlSicht extends Activity implements OnClickListener {

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raumplan_auswahl);
        ActivityRegistry.finishAll();
        ActivityRegistry.register(this);
        
        Button raum = (Button) findViewById(R.id.button_raumbearbeiten);
        raum.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent in = new Intent(RaumplanAuswahlSicht.this, RaumPlanBearbeitenSicht.class);
		startActivity(in);
	}
}
