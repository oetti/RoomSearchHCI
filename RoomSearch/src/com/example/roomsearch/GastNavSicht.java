package com.example.roomsearch;

import imports.IntentIntegrator;
import imports.IntentResult;
import erstellen.DialogCreater;
import Datenbank.ActivityRegistry;
import Datenbank.KontoControl;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
//
/**
 * 
 * 
 * @author Andreas Oettinger
 *
 */
public class GastNavSicht extends Activity implements OnClickListener, OnMenuItemClickListener, OnCheckedChangeListener {
	private Button beuth, stras, gauss, bauwesen, qr;
	private CheckBox meldung;
	private DialogCreater dc = new DialogCreater();
	
	/**
	 * In der Ansicht wird der Campusplan f¸r den Besucher angezeigt 
	 */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gast_nav_sicht);
        ActivityRegistry.finishAll();
        ActivityRegistry.register(this);
        beuth = (Button) findViewById(R.id.beuth_button);
        beuth.setOnClickListener(this);
        stras = (Button) findViewById(R.id.grashof_button);
        stras.setOnClickListener(this);
        gauss = (Button) findViewById(R.id.gauss_button);
        gauss.setOnClickListener(this);
        bauwesen = (Button) findViewById(R.id.bauwesen_button);
        bauwesen.setOnClickListener(this);
        meldung = (CheckBox) findViewById(R.id.check_gast);
        //meldung.setChecked(KontoControl.getAUSWAHL());
        meldung.setOnCheckedChangeListener(this);
        TextView position = (TextView) findViewById(R.id.text_position);
        position.setText("Haus Gauﬂ\n3. Etage\nRaum B341");
        qr = (Button) findViewById(R.id.button_qr);
        qr.setOnClickListener(this);
        
        if(KontoControl.getSPRACHE().equals("english")) {
        	englishChange();
        }
	}

	private void englishChange() {
		TextView whaus = (TextView)findViewById(R.id.text_wHaus);
		whaus.setText("To which building you want to go?");
		TextView standort = (TextView)findViewById(R.id.text_position);
		standort.setText("Determining \nposition...");
		meldung.setText("Do not show this dialog again");
	}

	public void onClick(View v) {
		final Intent in = new Intent(GastNavSicht.this, GastFrageSicht.class);
		if(beuth == v) {
			if (KontoControl.getAUSWAHL()) {
				in.putExtra("Haus", "Gauﬂ");
				startActivity(in);
			} else {
				if (KontoControl.getSPRACHE().equals("english")) {
					AlertDialog.Builder myAlertDialog = dc.getHausauswahl(this, "Beuth", KontoControl.getSPRACHE());
					myAlertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							in.putExtra("Haus", "Gauﬂ");
							startActivity(in);
						}});
					myAlertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
						}});
					myAlertDialog.show();
				} else {
					AlertDialog.Builder myAlertDialog = dc.getHausauswahl(this, "Beuth", KontoControl.getSPRACHE());
					myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							in.putExtra("Haus", "Gauﬂ");
							startActivity(in);
						}});
					myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
						}});
					myAlertDialog.show();
				}
			}
		}
		
		if(stras == v) {
			if (KontoControl.getAUSWAHL()) {
				in.putExtra("Haus", "Gauﬂ");
				startActivity(in);
			} else {
				if (KontoControl.getSPRACHE().equals("english")) {
					AlertDialog.Builder myAlertDialog = dc.getHausauswahl(this, "Grashof", KontoControl.getSPRACHE());
					myAlertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							in.putExtra("Haus", "Gauﬂ");
							startActivity(in);
						}});
					myAlertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
						}});
					myAlertDialog.show();
				} else {
					AlertDialog.Builder myAlertDialog = dc.getHausauswahl(this, "Grashof", KontoControl.getSPRACHE());
					myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						in.putExtra("Haus", "Gauﬂ");
						startActivity(in);
					}});
					myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
					}});
					myAlertDialog.show();
				}
			}
		}
		 
		if(gauss == v) {
			if (KontoControl.getAUSWAHL()) {
				in.putExtra("Haus", "Gauﬂ");
				startActivity(in);
			} else {
				if (KontoControl.getSPRACHE().equals("english")) {
					AlertDialog.Builder myAlertDialog = dc.getHausauswahl(this, "Gauﬂ", KontoControl.getSPRACHE());
					myAlertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							in.putExtra("Haus", "Gauﬂ");
							startActivity(in);
						}});
					myAlertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
						}});
					myAlertDialog.show();
				} else {
					AlertDialog.Builder myAlertDialog = dc.getHausauswahl(this, "Gauﬂ", KontoControl.getSPRACHE());
					myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						in.putExtra("Haus", "Gauﬂ");
						startActivity(in);
					}});
					myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
					}});
					myAlertDialog.show();
				}
			}
		}
		
		if(bauwesen == v) {
			if (KontoControl.getAUSWAHL()) {
				in.putExtra("Haus", "Bauwesen");
				startActivity(in);
				} else {
					if (KontoControl.getSPRACHE().equals("english")) {
						AlertDialog.Builder myAlertDialog = dc.getHausauswahl(this, "Bauwesen", KontoControl.getSPRACHE());
						myAlertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
								in.putExtra("Haus", "Bauwesen");
								startActivity(in);
							}});
						myAlertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
							}});
						myAlertDialog.show();
					} else {
						AlertDialog.Builder myAlertDialog = dc.getHausauswahl(this, "Bauwesen", KontoControl.getSPRACHE());
						myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							in.putExtra("Haus", "Bauwesen");
							startActivity(in);
						}});
						myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
						}});
						myAlertDialog.show();
					}
			}
		}
		
		if(v == qr) {
		      IntentIntegrator integrator = new IntentIntegrator(this);
		      integrator.initiateScan();
		    }
	}
	
	/**
	 * Wenn der zur¸ck Button auf Handy gedr¸ckt wird soll eine bestimmte
	 * Ansicht gesetzt werden.
	 */
	@Override
	public void onBackPressed() {
		// TODO Automatisch generierter Methodenstub
		super.onBackPressed();
		final Intent in = new Intent (GastNavSicht.this, LoginSicht.class);
			startActivity(in);  
	}
	
	/**
	 * Menu Optionen
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_room_search, menu);
        menu.getItem(3).setVisible(false);
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

	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		KontoControl.setAUSWAHL(arg1);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	      IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
	      final Intent in = new Intent(GastNavSicht.this, GastFrageSicht.class);
	      if (scanResult != null) {
	        String gebaeude = scanResult.getContents();
	        if(scanResult.getContents().equals("Gauss")) {
	        	gebaeude = "Gauﬂ";
	        }
	         AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
	         myAlertDialog.setTitle("Haus " + gebaeude);
	         myAlertDialog.setMessage("Willst du zum Geb‰ude "+scanResult.getContents()+"?\n\nWeitere Informationen zu dem Geb‰ude kommen hier.");
	         myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface arg0, int arg1) {
	          in.putExtra("Haus", "Gauﬂ");
	          startActivity(in);
	         }});
	         myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface arg0, int arg1) {
	         }});
	         
	         myAlertDialog.show();
	        //}
	      }
	    }
}
