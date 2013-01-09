package storage;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Saver {

	
	public static String speichernASKonten(HashMap<String, Object[]> daten) {
		String out = null;
	    if (daten != null) {
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ObjectOutputStream oos = new ObjectOutputStream(baos);
	            oos.writeObject(daten);
	            out = new String("SpracheAuswahlKonto");
	        } catch (IOException e) {
	            return null;
	        }
	    }
	    return out;
	}
	
	public static byte[] serializeObject(Object o) { 
	    ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	 
	    try { 
	      ObjectOutput out = new ObjectOutputStream(bos); 
	      out.writeObject(o); 
	      out.close(); 
	 
	      // Get the bytes of the serialized object 
	      byte[] buf = bos.toByteArray(); 
	 
	      return buf; 
	    } catch(IOException ioe) { 
	      Log.e("serializeObject", "error", ioe); 
	 
	      return null; 
	    } 
	  }
	
	 public static Object deserializeObject(byte[] b) { 
		    try { 
		      ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(b)); 
		      Object object = in.readObject(); 
		      in.close(); 
		 
		      return object; 
		    } catch(ClassNotFoundException cnfe) { 
		      Log.e("deserializeObject", "class not found error", cnfe); 
		 
		      return null; 
		    } catch(IOException ioe) { 
		      Log.e("deserializeObject", "io error", ioe); 
		 
		      return null; 
		    } 
		  }

	public static HashMap<String, Object[]> loaded(String s) {
		/*Object out = null;
	    if (s != null) {
	        try {
	            ByteArrayInputStream bios = new ByteArrayInputStream;
	            ObjectInputStream ois = new ObjectInputStream(bios);
	            out = ois.readObject();
	        } catch (IOException e) {
	            return null;
	        }catch (ClassNotFoundException e) {
	            return null;
	        }
	    }
	    return out;*/
		return null;
	} 
}
