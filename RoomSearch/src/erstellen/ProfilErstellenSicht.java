package erstellen;

import com.example.roomsearch.LoginSicht;
import com.example.roomsearch.R;
import Datenbank.ActivityRegistry;
import Datenbank.KontoControl;
import Datenbank.Nutzer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilErstellenSicht extends Activity implements OnClickListener, TextWatcher {
	private Button speichern;
	private Spinner nutzertyp;
	private String typ;
	private Nutzer n = new Nutzer();
	private ImageView pflicht1, pflicht2, pflicht3, pflicht4, pflicht5;
	private EditText account, vorname, nachname, mail, passwort, paWdh;
	private static boolean accB = false;
	private static boolean vorB = false;
	private static boolean maiB = false;
	private static boolean pasB = false;
	private static boolean pwdB = false;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_erstellen);
        ActivityRegistry.finishAll();
        ActivityRegistry.register(this);
        
        String[] nutzertypen = getNutzertypen(KontoControl.getSPRACHE());
        nutzertyp = (Spinner) findViewById(R.id.spinner_nutzertyp);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nutzertypen); 
        nutzertyp.setBackgroundColor(Color.WHITE);
        nutzertyp.setAdapter(spinnerAdapter);
        
        speichern = (Button) findViewById(R.id.button_profil_speichern);
        speichern.setOnClickListener(this); 
        
        account = (EditText) findViewById(R.id.edit_account);
		vorname = (EditText) findViewById(R.id.edit_vorname);
		nachname = (EditText) findViewById(R.id.edit_nachname);
		mail = (EditText) findViewById(R.id.edit_mail);
		passwort = (EditText) findViewById(R.id.edit_passwort);
		paWdh = (EditText) findViewById(R.id.edit_passwort_wdh);
		
		account.addTextChangedListener(this);
		vorname.addTextChangedListener(this);
		mail.addTextChangedListener(this);
		passwort.addTextChangedListener(this);
		paWdh.addTextChangedListener(this);
		pflicht1 = (ImageView) findViewById(R.id.pe_pflicht1);
		if(accB) {
			pflicht1.setBackgroundResource(R.drawable.stern_plicht_ok);
		} else {
			pflicht1.setBackgroundResource(R.drawable.stern_plicht);
		}
		
		pflicht2 = (ImageView) findViewById(R.id.pe_pflicht2);
		pflicht2.setBackgroundResource(R.drawable.stern_plicht);
		pflicht3 = (ImageView) findViewById(R.id.pe_pflicht3);
		pflicht3.setBackgroundResource(R.drawable.stern_plicht);
		pflicht4 = (ImageView) findViewById(R.id.pe_pflicht4);
		pflicht4.setBackgroundResource(R.drawable.stern_plicht);
		pflicht5 = (ImageView) findViewById(R.id.pe_pflicht5);
		pflicht5.setBackgroundResource(R.drawable.stern_plicht);
        
        if(KontoControl.getSPRACHE().equals("english")) {
        	englishChange();
        }  
	}
	
	private String[] getNutzertypen(String sprache) {
		if(sprache.equals("english")) {
			String[] nutzertypen = {"Student", "Lecturer", "Administrator", "Other"};
			return nutzertypen;
		} else {
			String[] nutzertypen = {"Student", "Dozent", "Administrator", "Anderes"};
			return nutzertypen;
		}
	}

	private void englishChange() {
		TextView main = (TextView)findViewById(R.id.p_profil);
		main.setText("create profile");
		TextView acc = (TextView)findViewById(R.id.p_acc);
		acc.setText("Enter account name");
		TextView vor = (TextView)findViewById(R.id.p_vor);
		vor.setText("Enter name");
		TextView nac = (TextView)findViewById(R.id.p_nac);
		nac.setText("Enter surname");
		TextView mail = (TextView)findViewById(R.id.p_mail);
		mail.setText("Enter email address");
		TextView pas = (TextView)findViewById(R.id.p_pas);
		pas.setText("Enter password");
		TextView pwd = (TextView)findViewById(R.id.p_pwd);
		pwd.setText("Repeat password");
		speichern.setText("Save");
	}

	private void setTyp (String nutzer) {
		this.typ = "a";
		
		if (nutzer.equals("Student")) {
			this.typ = "s";
		}
		
		if (nutzer.equals("Dozent")||nutzer.equals("Lecturer")) {
			this.typ = "d";
		}
		
		if (nutzer.equals("Administrator")) {
			this.typ = "master";
		}
	}

	public void onClick(View arg0) {
		if(passwort.getText().toString().equals(paWdh.getText().toString())) {
			if(account.getText().toString().length()!=0 && passwort.getText().toString().length() != 0 && mail.getText().toString().length() != 0
					&& vorname.getText().toString().length() != 0) {
			
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
				if(KontoControl.getSPRACHE().equals("english")) {
						myAlertDialog.setTitle("Create profile");
						myAlertDialog.setMessage("Do you want to create this profile?");
						myAlertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							setTyp(nutzertyp.getSelectedItem().toString());
							String[] daten = {account.getText().toString(), passwort.getText().toString(), typ, vorname.getText().toString(), nachname.getText().toString(), mail.getText().toString() };
							
								n.setProfil(daten);
								Intent intent = new Intent(ProfilErstellenSicht.this, LoginSicht.class);
								startActivity(intent);
							
						}});
						myAlertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							// tue nicht
						}});
				} else {
					myAlertDialog.setTitle("Profil erstellen");
					myAlertDialog.setMessage("Willst du dieses Profil erstellen?");
					myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						setTyp(nutzertyp.getSelectedItem().toString());
						String[] daten = {account.getText().toString(), passwort.getText().toString(), typ, vorname.getText().toString(), nachname.getText().toString(), mail.getText().toString() };
						
							n.setProfil(daten);
							Intent intent = new Intent(ProfilErstellenSicht.this, LoginSicht.class);
							startActivity(intent);
						
					}});
					myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						// tue nicht
					}});
				}
				
			 myAlertDialog.show();
			} else {
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
				 if(KontoControl.getSPRACHE().equals("english")) {
					 	myAlertDialog.setTitle("Not entered correctly!");
						myAlertDialog.setMessage("Please fill in mandatory fields. They are marked with a star.");
				 } else {
					 myAlertDialog.setTitle("Nicht korrekt ausgefüllt!");
					 myAlertDialog.setMessage("Bitte die Pflichtfelder ausfüllen. Du erkennst sie an den Sternchen.");
				 }
				myAlertDialog.setNeutralButton("ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						// tue nicht
					}});
				myAlertDialog.show();
			}
		} else {
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
			if(KontoControl.getSPRACHE().equals("english")) {
				myAlertDialog.setTitle("Error");
				myAlertDialog.setMessage("Your password and your repeated password don't match.");
			} else {
				myAlertDialog.setTitle("Fehler");
				myAlertDialog.setMessage("Dein Passwort stimmt mit der Wiederholung nicht überein.");
			}
			myAlertDialog.setNeutralButton("ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					// tue nicht
				}});
			myAlertDialog.show();
		}
	}
	
	private void bildPflichtChange(ImageView bild, boolean ok) {
		if(ok) {
			bild.setBackgroundResource(R.drawable.stern_plicht_ok);
		} else {
			bild.setBackgroundResource(R.drawable.stern_plicht);
		}
	}

	public void afterTextChanged(Editable arg0) {
	}

	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {	
	}

	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		if(arg0.length() > 0 && account.isInputMethodTarget()) {
			bildPflichtChange(pflicht1, true);
			accB = true;
			if(n.accountVorhanden(arg0.toString())) {
			pflicht1.setBackgroundResource(R.drawable.err_vorhanden);
			}
		} else {
			bildPflichtChange(pflicht1, false);
			accB = false;
		}
		if(arg0.length() > 0 && vorname.isInputMethodTarget()) {
			bildPflichtChange(pflicht2, true);
			vorB = true;
		} else {
			bildPflichtChange(pflicht2, false);
			vorB = true;
		}
		if(arg0.length() > 0 && mail.isActivated()) {
			bildPflichtChange(pflicht3, true);
			maiB = true;
			
		} else {
			bildPflichtChange(pflicht3, false);
			maiB = false;
		}
		if(arg0.length() > 0 && passwort.isActivated()) {
			bildPflichtChange(pflicht4, true);
			pasB = true;
			
		} else {
			bildPflichtChange(pflicht4, false);
			pasB = false;
		}
		if(arg0.length() > 0 && paWdh.isActivated()) {
			bildPflichtChange(pflicht5, true);
			pwdB = true;
			if(paWdh.length() == passwort.length()) {
				if(!arg0.equals(passwort.getText().toString())) {
					pflicht1.setBackgroundResource(R.drawable.err_vorhanden);
					Toast toast = Toast.makeText(paWdh.getContext(), "Bitte im Stundenplan eine Vorlesung auswählen!", Toast.LENGTH_SHORT);
					toast.setMargin(passwort.getX(), passwort.getY());
					toast.show(); 
				}
			}
		} else {
			bildPflichtChange(pflicht5, false);
			pwdB = false;
		}
	}
}
