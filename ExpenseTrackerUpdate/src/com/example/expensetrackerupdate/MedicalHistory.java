package com.example.expensetrackerupdate;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MedicalHistory extends Activity{
	EditText notes;
	String notesget;
	Button btn_save,btn_cancel;
	String success = "";
	ImageView logoutbtn;
	public void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.medicalhistory);
		notes = (EditText)findViewById(R.id.medical_notes);
		btn_save = (Button)findViewById(R.id.save);
		btn_cancel = (Button)findViewById(R.id.cancel);
		logoutbtn = (ImageView)findViewById(R.id.logOut);
		btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				notesget = notes.getText().toString();	
				if(!notesget.equals("")){
//					new Medical().execute();
					Toast.makeText(MedicalHistory.this, "Saved successfully", 3000).show();
					Intent inte = new Intent(MedicalHistory.this,HomeActivity.class);
					startActivity(inte);
				}
				else{
					Toast.makeText(MedicalHistory.this, "Add notes", 3000).show();
				}
			}
		});

		
		logoutbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MedicalHistory.this.finish();
			}
		});
}
	private class Medical extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(MedicalHistory.this, "","Loading, please wait...", true);
		}

		@SuppressWarnings("unused")
		protected Boolean doInBackground(Void... unused) {
			JSONObject json = new JSONObject();
			JSONArray jArray;
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://10.0.1.11/dadmom/webservices/login_user.php?notes="+ notesget);

				HttpResponse response = httpclient.execute(httppost);
				System.out.println("Gurdev_Register*****response*****"
						+ response);
				HttpEntity entity = response.getEntity();
				System.out.println("Gurdev_Register*****entity*****" + entity);
				Log.d("bbb", "bbb" + entity);
				// If the response does not enclose an entity, there is no need
				if (entity != null) {
					Log.d("Response", "messageLogin");
					InputStream instream = entity.getContent();
					String result = Refrence.convertStreamToString(instream);
					json = new JSONObject(result);
					Log.d("re" + result, "msg");
					success = json.getString("login");
					Log.d("Response", "messageLogin" + success);
					jArray = json.getJSONArray("responsedata");
					
					
					
					
					

				}
			} catch (Exception e) {

			}
			return null;
		}

		protected void onPostExecute(Boolean unused) {
			super.onPostExecute(unused);
			dialog.dismiss();
			if (success.equalsIgnoreCase("Success")) {
				
				Toast.makeText(MedicalHistory.this, "Saved Successfully", 3000).show();
//				Intent intent = new Intent(LoginScreen.this, HomeActivity.class);
//				startActivity(intent);
//				finish();
			} else {
				Toast.makeText(MedicalHistory.this, "Access denied", 3000).show();
			}
		};


	}
}