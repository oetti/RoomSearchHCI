package com.example.roomsearch;


import Datenbank.ActivityRegistry;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Intent;

public class RoomSearch extends Activity {
	private ImageView lupe;
	private Animation animAlpha;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_search);
        ActivityRegistry.register(this);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.jump);
        //lupe = (ImageView)findViewById(R.id.image_lupe);
        //lupe.startAnimation(animAlpha);;
        final Intent in = new Intent (RoomSearch.this, LoginSicht.class);
			startActivity(in);
        /*Handler handler=new Handler();
      	Runnable r = new Runnable() {
      		public void run() {
      			final Intent in = new Intent (RoomSearch.this, LoginSicht.class);
      			startActivity(in);     			                    
		    }
		}; 
		handler.postDelayed(r, 1);*/
    }
}
