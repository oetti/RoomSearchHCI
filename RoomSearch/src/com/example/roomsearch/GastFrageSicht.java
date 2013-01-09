package com.example.roomsearch;

import erstellen.DialogCreater;
import Datenbank.ActivityRegistry;
import Datenbank.Hausverwaltung;
import Datenbank.KontoControl;
import Datenbank.Listfiller;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

/**
 * In dieser Klasse wird das Gebäude angezeigt was der Gast ausgewählt hat.
 * Nun wird der Gast hier gefragt welchen Raum er denn sucht.
 * 
 * @author Andreas Oettinger
 *
 */
public class GastFrageSicht extends Activity implements OnClickListener, OnMenuItemClickListener, OnItemSelectedListener {
	private Button suchen;
	// In dieser Klasser wird die eingebene Raumnummer überprüft.
	private Hausverwaltung h = new Hausverwaltung();
	private Listfiller f = new Listfiller();
	private Spinner spinZeit, spinVorlesung, spinBeRaeume;
	private ArrayAdapter<String> zeitAdapter, vorAdapter, hausAdapter;
	private ViewSwitcher hausSwitcher;
	private int index = 0;
	private Button r1,r2,r3;
	private EditText text;
	private int choice;
	private DialogCreater dc = new DialogCreater();
	private String haus;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // übernimmt und speichert Daten von Activities
        Intent intent = getIntent();
        // uebergibt Daten weiter uebermittelte Daten der vorigen Activity
        // von Gast_Nav_Sicht
        if(KontoControl.getSPRACHE().equals("english")) {
        	haus ="You chose " + intent.getExtras().getString("Haus") + ".";
        } else {
        	haus ="Du hast das Haus " + intent.getExtras().getString("Haus") + " ausgewählt.";
        }
        
        // 
        if(intent.getExtras().getString("Haus").equals("Gauß")) {
        	setContentView(R.layout.gast_frage_sicht);
        	spinBeRaeume = (Spinner)findViewById(R.id.spinner_besondere);
        	hausAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, f.vorlesungenListe(9));
            spinBeRaeume.setAdapter(hausAdapter);
        }
        //
        if(intent.getExtras().getString("Haus").equals("Bauwesen")) {
        	setContentView(R.layout.gast_frage_sicht_bauwesen);
        	spinBeRaeume = (Spinner)findViewById(R.id.spinner_besondere);
        	hausAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, f.bauwesen());
            spinBeRaeume.setAdapter(hausAdapter);
        }
        ActivityRegistry.register(this);
        // Überschrift welches Haus ausgewählt wurde
        TextView hausname = (TextView) findViewById(R.id.text_info_haus);
        // Gebäudename z.B. Gauss
        hausname.setText(haus);
        
        // Vorlesungen wechseln
        spinZeit = (Spinner) findViewById(R.id.spinner_zeit);
        zeitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, f.zeit());
        spinZeit.setAdapter(zeitAdapter);
        spinZeit.setOnItemSelectedListener(this);
        
        // info wechsel
        hausSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher_haus_info);
        hausSwitcher.setOnClickListener(this);
        
        // eingegebene Raumnummer
        text = (EditText) findViewById(R.id.editText1);
        
        // Suche-Button
        suchen = (Button) findViewById(R.id.suchen_button);
        suchen.setOnClickListener(this);
        
        // RadioButtons
        r1 = (Button) findViewById(R.id.button_radio1);
        r2 = (Button) findViewById(R.id.button_radio2);
        r3 = (Button) findViewById(R.id.button_radio3);
        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        
        if(KontoControl.getSPRACHE().equals("english")) {
        	englishChange();
        }
	}
	
	private void englishChange() {
		TextView info = (TextView)findViewById(R.id.text_gast_info);
		info.setText("Please enter just the number of the room without the letter. The searched room will be shown in your chosen building, if you press send.");
		TextView number = (TextView)findViewById(R.id.text_raumnummer);
		number.setText("Room number");
		TextView time = (TextView)findViewById(R.id.text_zeitraum);
		time.setText("Time");
		TextView vorl = (TextView)findViewById(R.id.text_vorlesung_gast);
		vorl.setText("Lecture");
		TextView sroom = (TextView)findViewById(R.id.text_besondere_raeume_);
		sroom.setText("Special rooms");
		suchen.setText("Search");
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
	 *  Wird der Button suchen gedrückt wird ein Event ausgelöst der eine neue
	 *  Activity startet
	 */
	public void onClick(View arg0) {
		if(arg0 == suchen) {
			Intent in = new Intent(GastFrageSicht.this, CamNavSicht.class);
			if(choice == 1) {
				try {
					// kontrolliere ob die Nummer auch stimmt, wenn ja gib sie weiter an
					// die nächste Activity und starte sie
					if(h.beuthCheck(text.getText().toString())) {
						in.putExtra("Nummer", text.getText().toString());
						startActivity(in);
					} else {
						// Dialog Fehler
						if(KontoControl.getSPRACHE().equals("english")) {
							Toast toast = Toast.makeText(this, "Sorry, this room does not exist!", Toast.LENGTH_SHORT);
							toast.show(); 
						} else {
							Toast toast = Toast.makeText(this, "Tut mir leid. Diesen Raum gibt es nicht!", Toast.LENGTH_SHORT);
							toast.show(); 
						}
					}
				} catch (StringIndexOutOfBoundsException e) {
					if(KontoControl.getSPRACHE().equals("english")) {
						Toast toast = Toast.makeText(this, "No room number was entered.", Toast.LENGTH_SHORT);
						toast.show(); 
					} else {
						Toast toast = Toast.makeText(this, "Es wurde keine Raumnummer eingegeben.", Toast.LENGTH_SHORT);
						toast.show(); 
					}
				}
			} 

			if(choice == 2) {
				in.putExtra("Nummer", spinVorlesung.getSelectedItem().toString());
				startActivity(in);
			}
			
			if(choice == 3) {
				if(!spinBeRaeume.getSelectedItem().toString().equals("Keine")) {
					in.putExtra("Nummer", spinBeRaeume.getSelectedItem().toString());
					startActivity(in);
				} else {
					// Dialog Fehler
					if(KontoControl.getSPRACHE().equals("english")) {
						Toast toast = Toast.makeText(this, "There are no special rooms.", Toast.LENGTH_SHORT);
						toast.show(); 
					} else {
						Toast toast = Toast.makeText(this, "Keine besonderen Räume vorhanden.", Toast.LENGTH_SHORT);
						toast.show(); 
					}
				}
			}
		}
		
		if(arg0 == hausSwitcher) {
			if(index == 1) {
				hausSwitcher.setDisplayedChild(1);
				index = 0;
			} else {
				hausSwitcher.setDisplayedChild(0);
				index = 1;
			}	
		}
		
		if(arg0 == r1) {
			r1.setBackgroundResource(R.drawable.radio_button_an);
			text.setBackgroundColor(Color.WHITE);
			spinZeit.setEnabled(false);
			spinVorlesung.setEnabled(false);
			spinBeRaeume.setEnabled(false);
			r2.setBackgroundResource(R.drawable.radio_button_aus);
			r3.setBackgroundResource(R.drawable.radio_button_aus);
			choice = 1;
		}
		
		if(arg0 == r2) {
			r2.setBackgroundResource(R.drawable.radio_button_an);
			text.setBackgroundColor(Color.DKGRAY);
			spinZeit.setEnabled(true);
			spinVorlesung.setEnabled(true);
			spinBeRaeume.setEnabled(false);
			r1.setBackgroundResource(R.drawable.radio_button_aus);
			r3.setBackgroundResource(R.drawable.radio_button_aus);
			choice = 2;
		}
		
		if(arg0 == r3) {
			r3.setBackgroundResource(R.drawable.radio_button_an);
			text.setBackgroundColor(Color.DKGRAY);
			spinZeit.setEnabled(false);
			spinVorlesung.setEnabled(false);
			spinBeRaeume.setEnabled(true);
			r1.setBackgroundResource(R.drawable.radio_button_aus);
			r2.setBackgroundResource(R.drawable.radio_button_aus);
			choice = 3;
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
			spinVorlesung = (Spinner) findViewById(R.id.spinner_vorlesungen);
			vorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, f.vorlesungenListe(arg2+1));
	        spinVorlesung.setAdapter(vorAdapter);
	}

	public void onNothingSelected(AdapterView<?> arg0) {
	}
}
