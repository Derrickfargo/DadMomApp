package com.example.expensetrackerupdate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

public class SplashScreen extends Activity {
	String PREFS_NAME = "Expense_Tracker";
	SharedPreferences AppID;
	String ussername,passworD;
	String Code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashscreen);
		
		  final Handler handler = new Handler();
	         Runnable runnable = new Runnable() {
	      public void run() {
	    	try{
	    	  logincheck();
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}    
	            }

	        };
	        handler.postDelayed(runnable, 1000); //for initial delay..
	}
	public void logincheck(){
		  AppID = getApplication().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
	  		ussername = AppID.getString("UserName", "");
	  		passworD = AppID.getString("Password", "");
	  		Log.d("ussername",""+ussername);
	  		Log.d("passworD",""+passworD);
	    	  if (!ussername.equals("") && !passworD.equals("")) {
					Intent i = new Intent(SplashScreen.this,HomeActivity.class);
					startActivity(i);
					finish();
				} else {
					 Intent intent = new Intent(SplashScreen.this,LoginScreen.class);
		                startActivity(intent);
		                finish();
				}
	}
	

}
