package com.example.roomsearch;

import Datenbank.ActivityRegistry;
import Datenbank.Datenbank;
import Datenbank.Listfiller;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NavSicht extends Activity implements OnClickListener, OnMenuItemClickListener, OnItemLongClickListener, OnItemClickListener, OnTouchListener {
	private Listfiller filler = new Listfiller();
	private Button finden;
	private Animation animAlpha;
	// Liste von Vorlesungen
	private ListView mo, di, mi, don, fr; 
	private View selectedItem = null;
	private String selectedItemString = "";
	// View für den Standort
	public TextView standort;
	// Dateninhalte
	private Datenbank dt;
	boolean wifiWasEnabled;
	private String account;
	@SuppressWarnings("unused")
	private int id;
	HorizontalScrollView h_scroll;
	ImageView img;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_sicht);
        Intent intent = getIntent();
        account = intent.getExtras().getString("Account");
        id = intent.getExtras().getInt("ID");
        ActivityRegistry.finishAll();
        ActivityRegistry.register(this);
        
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        
        img = (ImageView)findViewById(R.id.image_slider_right);
        /*
         * Stundenlan
         */
        // Vorlesungen Montag
        
        mo = (ListView) findViewById(R.id.ListMo); 
        ArrayAdapter<String> sAmon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.getStundenplan(account, 0));
        mo.setBackgroundColor(Color.WHITE);
        mo.setAdapter(sAmon);
        for (int a = 0; a < mo.getChildCount(); a++) {
        	if(sAmon.getItem(a).length() == 0) {
        		mo.getChildAt(a).setEnabled(false);
        	}
        }
        mo.setOnItemLongClickListener(this);
        mo.setOnItemClickListener(this);
        // Vorlesungen Dienstag
        di = (ListView) findViewById(R.id.ListDi);
        ArrayAdapter<String> sAdie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.getStundenplan(account, 1)); 
        di.setBackgroundColor(Color.WHITE);
        di.setAdapter(sAdie);
        for (int a = 0; a < di.getChildCount(); a++) {
        	if(sAdie.getItem(a).length() == 0) {
        		di.getChildAt(a).setEnabled(false);
        	}
        }
        di.setOnItemLongClickListener(this);
        di.setOnItemClickListener(this);
        // Vorlesungen Mittwoch
        mi = (ListView) findViewById(R.id.ListMi);
        ArrayAdapter<String> sAmit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.getStundenplan(account, 2)); 
        mi.setBackgroundColor(Color.WHITE);
        mi.setAdapter(sAmit);
        for (int a = 0; a < mi.getChildCount(); a++) {
        	if(sAmit.getItem(a).length() == 0) {
        		mi.getChildAt(a).setEnabled(false);
        	}
        }
        mi.setOnItemLongClickListener(this);
        mi.setOnItemClickListener(this);
        // Vorlesungen Donnerstag
        don = (ListView) findViewById(R.id.ListDo);
        ArrayAdapter<String> sAdon = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.getStundenplan(account, 3)); 
        don.setBackgroundColor(Color.WHITE);
        don.setAdapter(sAdon);
        for (int a = 0; a < don.getChildCount(); a++) {
        	if(sAdon.getItem(a).length() == 0) {
        		don.getChildAt(a).setEnabled(false);
        	}
        }
        don.setOnItemLongClickListener(this);
        don.setOnItemClickListener(this);
        // Vorlesungen Freitag
        fr = (ListView) findViewById(R.id.ListFr);
        ArrayAdapter<String> sAfre = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filler.getStundenplan(account, 4)); 
        fr.setBackgroundColor(Color.WHITE);
        fr.setAdapter(sAfre);
        for (int a = 0; a < fr.getChildCount(); a++) {
        	if(sAfre.getItem(a).length() == 0) {
        		fr.getChildAt(a).setEnabled(false);
        	}
        }
        fr.setOnItemLongClickListener(this);
        fr.setOnItemClickListener(this);
        
        // Button Raum finden
        finden = (Button) findViewById(R.id.button_finden);
        finden.setOnClickListener(this);
        
        h_scroll = (HorizontalScrollView)findViewById(R.id.horizontalScrollView1);
        h_scroll.setOnTouchListener(this);
	}
	
	/**
	 * Menu Optionen
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        for(int i = 0; i < menu.size(); i++) {
        	menu.getItem(i).setOnMenuItemClickListener(this);
        }
        
        return true;
    }

	public boolean onMenuItemClick(MenuItem item) {
		if(item.getTitle().equals("Beenden")){
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
			 myAlertDialog.setTitle("Beenden");
			 myAlertDialog.setMessage("Willst du Room Search wirklich beenden?");
			 myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				 //KontoControl.save(getBaseContext());
				 ActivityRegistry.finishAll();
			 }});
			 myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				  // tue nicht
			 }});
			 
			 myAlertDialog.show();
		}
		return false;
	}

	public void onClick(final View v) {
		v.startAnimation(animAlpha);
		Handler handler=new Handler();
      	Runnable r = new Runnable() {
      	public void run() {
        if (v == finden) {
			// von NavSicht zu CamNavSicht
			final Intent in = new Intent (NavSicht.this, CamNavSicht.class);
			
			try {
				if(selectedItemString.length() == 0) {
					Toast toast = Toast.makeText(v.getContext(), "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
					toast.show(); 
				} else {
					dt = new Datenbank(selectedItemString);
					in.putExtra("Vorlesung", dt.getVorlesung());
					startActivity(in);
				}
			} catch (NullPointerException e) {
				Toast toast = Toast.makeText(v.getContext(), "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
				toast.show(); 
			}	
		}
      	  }
      			}; 
      			handler.postDelayed(r, 20);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Automatisch generierter Methodenstub
		super.onBackPressed();
		final Intent in = new Intent (NavSicht.this, SelectedSicht.class);
			in.putExtra("Account", account);
			startActivity(in);  
	}

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		if(arg0.getId() == mo.getId()) {
			for(int k = 0; k < mo.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} 
				}
			}
		}
			
		if(arg0.getId() == di.getId()) {
			for(int k = 0; k < di.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					}
				}
			}
		}
		
		if(arg0.getId() == mi.getId()) {
			for(int k = 0; k < mi.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					}
				}
			}
		}
			
		if(arg0.getId() == don.getId()) {
			for(int k = 0; k < don.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					} 
				}
			}
		}
		
		if(arg0.getId() == fr.getId()) {
			for(int k = 0; k < fr.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
					 myAlertDialog.setTitle("Vorlesungszeit");
					 myAlertDialog.setMessage(filler.zeit().get(k));
					 myAlertDialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						 }});
					 myAlertDialog.show();
					}
				}
			}
		}
		return false;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if(selectedItem != null) {
			selectedItem.setBackgroundColor(Color.WHITE);
		}
		
		if(arg0.getId() == mo.getId()) {
			for(int k = 0; k < mo.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
					}
				}
			}
		}
			
		if(arg0.getId() == di.getId()) {
			for(int k = 0; k < di.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
					}
				}
			}
		}
		
		if(arg0.getId() == mi.getId()) {
			for(int k = 0; k < mi.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
					} 
				}
			}
		}
			
		if(arg0.getId() == don.getId()) {
			for(int k = 0; k < don.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
					}
				}
			}
		}
		
		if(arg0.getId() == fr.getId()) {
			for(int k = 0; k < fr.getCount(); k++) {
				if(arg2 == k) {
					if(!arg0.getAdapter().getItem(arg2).equals("")) {
					arg1.setBackgroundColor(Color.RED);
					selectedItem = arg1;
					selectedItemString = (String) arg0.getAdapter().getItem(arg2);
					}
				}
			}
		}
	}

	public boolean onTouch(View arg0, MotionEvent arg1) {
		if(arg0 == h_scroll) {
			if(arg0.getScrollX() == 0) {
				img.setRotation(0);
			}
			
			if(arg0.getScrollX() == 2800) {
				img.setRotation(180);
			}
		}
		return false;
	}
}
