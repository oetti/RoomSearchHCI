package erstellen;

import Datenbank.ActivityRegistry;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogCreater {

	
	public AlertDialog.Builder getBeendenBuilder(Context context, String sprache) {
		if (sprache.equals("english")) {
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(context);
			 myAlertDialog.setTitle("exit");
			 myAlertDialog.setMessage("Do you want to close Room Search?");
			 myAlertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					 ActivityRegistry.finishAll();
				 }});
			 myAlertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					 // tue nicht
				 }});
			 return myAlertDialog;
		} else {
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(context);
			 myAlertDialog.setTitle("Beenden");
			 myAlertDialog.setMessage("Willst du Room Search wirklich beenden?");
			 myAlertDialog.setPositiveButton("ja", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					 ActivityRegistry.finishAll();
				 }});
			 myAlertDialog.setNegativeButton("nein", new DialogInterface.OnClickListener() {
				 public void onClick(DialogInterface arg0, int arg1) {
					 // tue nicht
				 }});
			 return myAlertDialog;
		}
	}

	public AlertDialog.Builder getHausauswahl(Context context, String haus, String sprache) {
		if (sprache.equals("english")) {
			AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(context);
			 myAlertDialog.setTitle("Do you want to go to the " + haus+ " building?");
			 myAlertDialog.setMessage("There is going to be additional information to the building.");
			 myAlertDialog.setCancelable(false);
			 return myAlertDialog;
		} else {
			 AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(context);
			 myAlertDialog.setTitle("Willst du zum Gebäude " + haus+ "?");
			 myAlertDialog.setMessage("Weitere Informationen zu dem Gebäude kommen hier.");
			 myAlertDialog.setCancelable(false);
			 return myAlertDialog;
		}
	}
}

