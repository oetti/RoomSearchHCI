package com.example.roomsearch;

import java.util.ArrayList;

import Datenbank.ActivityRegistry;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RaumPlanBearbeitenSicht extends Activity{
	private ListView mo;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raumplan_bearbeiten_sicht);
        ActivityRegistry.register(this);
      
        String[] liste = {"", "2", "6"};
        mo = (ListView) findViewById(R.id.list_raumMo); 
        ArrayAdapter<String> sAmon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, liste);
        //mo.setBackgroundColor(Color.WHITE);
        //mo.add
        ListView mon = new ListView(getBaseContext());
        ArrayAdapter<String> smon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, liste);
        ArrayList<View> list = new ArrayList<View>();
        list.add(mon);
        mon.setAdapter(smon);
        mo.addFocusables(list, 0);
        mo.addFocusables(list, 1);
        mo.setAdapter(sAmon);
        
       
        
        for (int a = 0; a < mo.getChildCount(); a++) {
        	if(sAmon.getItem(a).length() == 0) {
        		mo.getChildAt(a).setEnabled(false);
        	}
        }
	}
}
