package erstellen;

import java.util.ArrayList;

import com.example.roomsearch.R;
import com.example.roomsearch.SelectedSicht;

import Datenbank.ActivityRegistry;
import Datenbank.Listfiller;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StundenplanErstellen extends Activity implements OnItemClickListener, OnClickListener{
	private ListView mo, di, mi, don, fr;
	private ArrayList<String> moST, diST, miST, doST, frST;
	private ArrayAdapter<String> moAdapter, diAdapter, miAdapter, doAdapter, frAdapter;
	private Button vorl1,vorl2, vorl3, vorl4, vorl5, erstellen;
	private Listfiller f = new Listfiller();
	private String account;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stundenplan_erstellen);
        Intent intent = getIntent();
        account = intent.getExtras().getString("Account");
        ActivityRegistry.register(this);
        
        // Info
        TextView info = (TextView)findViewById(R.id.pl_text_info);
        info.setText("Bei einem Freiblock einen leeren Vorlesungsblock erstellen");
        
        // Montag
        moST = new ArrayList<String>();
        mo = (ListView) findViewById(R.id.ListView_mo);
        moAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, moST);
        mo.setOnItemClickListener(this);
        mo.setAdapter(moAdapter);
        vorl1 = (Button) findViewById(R.id.button_vorl1);
        vorl1.setOnClickListener(this);
        // Dienstag
        diST = new ArrayList<String>();
        di = (ListView) findViewById(R.id.listView_di);
        diAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, diST);
        di.setAdapter(diAdapter);
        di.setOnItemClickListener(this);
        vorl2 = (Button) findViewById(R.id.button_vorl2);
        vorl2.setOnClickListener(this);
        // Mittwoch
        miST = new ArrayList<String>();
        mi = (ListView) findViewById(R.id.ListView_mi);
        miAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, miST);
        mi.setAdapter(miAdapter);
        mi.setOnItemClickListener(this);
        vorl3 = (Button) findViewById(R.id.Button_vorl3);
        vorl3.setOnClickListener(this);
        // Donnerstag
        doST = new ArrayList<String>();
        don = (ListView) findViewById(R.id.ListView_do);
        doAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doST);
        don.setAdapter(doAdapter);
        don.setOnItemClickListener(this);
        vorl4 = (Button) findViewById(R.id.Button_vorl4);
        vorl4.setOnClickListener(this);
        // Freitag
        frST = new ArrayList<String>();
        fr = (ListView) findViewById(R.id.ListView_fr);
        frAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, frST);
        fr.setAdapter(frAdapter);
        fr.setOnItemClickListener(this);
        vorl5 = (Button) findViewById(R.id.Button_vorl5);
        vorl5.setOnClickListener(this);
        
        erstellen = (Button) findViewById(R.id.button_erstellen_SP);
        erstellen.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v == erstellen) {
			ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			daten.add(moST);
			daten.add(diST);
			daten.add(miST);
			daten.add(doST);
			daten.add(frST);
			f.createStundenplan(account, daten);
			Intent intent = new Intent(StundenplanErstellen.this, SelectedSicht.class);
			intent.putExtra("Account", account);
			startActivity(intent);
		}
		
		if(v == vorl1) {
			if(moAdapter.getCount() == 12) {
				Toast toast = Toast.makeText(v.getContext(), "Limit erreicht!", Toast.LENGTH_SHORT);
				toast.show();
			} else {
				Builder builder = new AlertDialog.Builder(this);
			
	        builder.setMessage("z.B.: B 342 Vorlesung");
		    final EditText vorl = new EditText(this);
		    vorl.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
	        builder.setView(vorl);
	        builder.setPositiveButton("hinzufügen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					moAdapter.add(vorl.getText().toString());
				 }});
			builder.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					  // tue nicht
				 }});
		    AlertDialog dialog = builder.create();
		    dialog.show();
		   }
		}
			
		
		if(v == vorl2) {
			
			Builder builder = new AlertDialog.Builder(this);
			
	        builder.setMessage("z.B.: B 342 Vorlesung");
		    final EditText vorl = new EditText(this);
		    vorl.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
	        builder.setView(vorl);
	        builder.setPositiveButton("hinzufügen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					diAdapter.add(vorl.getText().toString());
				 }});
			builder.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					  // tue nicht
				 }});
		    AlertDialog dialog = builder.create();
		    dialog.show();
		   }
		
		if(v == vorl3) {
			Builder builder = new AlertDialog.Builder(this);
			
	        builder.setMessage("z.B.: B 342 Vorlesung");
		    final EditText vorl = new EditText(this);
		    vorl.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
	        builder.setView(vorl);
	        builder.setPositiveButton("hinzufügen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					miAdapter.add(vorl.getText().toString());
				 }});
			builder.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					  // tue nicht
				 }});
		    AlertDialog dialog = builder.create();
		    dialog.show();
		   }
		
		if(v == vorl4) {
			Builder builder = new AlertDialog.Builder(this);
			
	        builder.setMessage("z.B.: B 342 Vorlesung");
		    final EditText vorl = new EditText(this);
		    vorl.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
	        builder.setView(vorl);
	        builder.setPositiveButton("hinzufügen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					doAdapter.add(vorl.getText().toString());
				 }});
			builder.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					  // tue nicht
				 }});
		    AlertDialog dialog = builder.create();
		    dialog.show();
		   }
		
		if(v == vorl5) {
			/*Builder builder = new AlertDialog.Builder(this);
			
	        builder.setMessage("z.B.: B 342 Vorlesung");*/
			final AlertDialog.Builder input = new AlertDialog.Builder(this);
			//Dialog input = new Dialog(getBaseContext());
			LinearLayout layout = new LinearLayout(getBaseContext());
			
			LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 200);
			layout.setLayoutParams(layoutParams);
			layout.setOrientation(1);
			// text einfügen
			final EditText raum = new EditText(getBaseContext());
		    raum.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
		    
		    final EditText vorlesung = new EditText(getBaseContext());
		    vorlesung.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
			layout.addView(raum);
			layout.addView(vorlesung);
			input.setView(layout);
	        input.setPositiveButton("hinzufügen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					String vorl = raum.getText().toString() + " " + vorlesung.getText().toString(); 
					frAdapter.add(vorl);
				 }});
			input.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					  // tue nicht
				 }});
		    input.show();
		   }
	}

	public void onItemClick(final AdapterView<?> adapter, final View v, final int index, long arg3) {
		Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Bitte treffe eine Auswahl");
        builder.setPositiveButton("Vorlesung bearbeiten", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				 /*	
				 Builder builder = new AlertDialog.Builder(getBaseContext());
				 builder.setMessage("z.B.: B 342 Vorlesung");
				 	LinearLayout layout = new LinearLayout(getBaseContext());
				 	//layout.setLayoutParams(params);
				    final EditText change = new EditText(getBaseContext());
				    change.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
				    final EditText change2 = new EditText(getBaseContext());
				    change2.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
				    layout.addView(change,0);
				    layout.addView(change2,1);
			        builder.setView(layout);
			        builder.setPositiveButton("ändern", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
						
						 }});
					builder.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
						 public void onClick(DialogInterface arg0, int arg1) {
							  // tue nicht
						 }});
				    AlertDialog dialog = builder.create();
				    dialog.show();*/
			 }});
        // bearbeiten funktioniert noch nicht
		builder.setNegativeButton("Vorlesung entfernen", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				 ArrayList<String> liste = new ArrayList<String>();
				 ArrayAdapter<String> test = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, liste);
				 for(int i = 0; i < adapter.getAdapter().getCount(); i++) {
					 if(!(i == index)) {
						test.add((String) adapter.getAdapter().getItem(i)); 
					 }
					 
				 }
				System.out.println(v.toString());
					
			 }});
		builder.setNeutralButton("Abbrechen", new DialogInterface.OnClickListener() {
			 public void onClick(DialogInterface arg0, int arg1) {
				
			 }});
	    AlertDialog dialog = builder.create();
	    dialog.show();
	}
}
