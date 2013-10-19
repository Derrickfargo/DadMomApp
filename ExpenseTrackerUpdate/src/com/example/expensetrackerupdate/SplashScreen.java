package com.example.expensetrackerupdate;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		  final Handler handler = new Handler();
	         Runnable runnable = new Runnable() {
	      public void run() {
	            	
	              
	                Intent intent = new Intent(SplashScreen.this,LoginScreen.class);
	                startActivity(intent);
	            }

	        };
	        handler.postDelayed(runnable, 1000); //for initial delay..
	}
	

}
