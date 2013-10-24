package com.example.expensetrackerupdate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends Activity{
	Button submitreset;
	EditText userName;
	String usernameget;
	String success = "",url,failure="";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword);
		userName = (EditText)findViewById(R.id.forget_username);
		submitreset = (Button)findViewById(R.id.bntton_submit);


		submitreset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				usernameget = userName.getText().toString();
				if(usernameget.equals("")){
					Toast.makeText(ForgetPassword.this,"Enter User Name", 3000);
				}
				else{
					
					new Forget().execute();
				}}
		});

	}
	
	private class Forget extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(ForgetPassword.this, "","Loading, please wait...", true);
		}

		@SuppressWarnings("unused")
		protected Boolean doInBackground(Void... unused) {
			JSONObject json = new JSONObject();
			JSONObject json2;
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(ExpenseTrackerConstant.webServiceUrl+
						ExpenseTrackerConstant.forgetPassword+"user_name="+ usernameget );
						
				HttpResponse response = httpclient.execute(httppost);
				
				HttpEntity entity = response.getEntity();
			if (entity != null) {
					
						InputStream instream = entity.getContent();
						String result = Refrence.convertStreamToString(instream);
						json = new JSONObject(result);
						json2 = json.getJSONObject("response");
						success = json2.getString("code");
						failure = json2.getString("msg");
						Log.d("code", "code" + success);

				}
			} catch (Exception e) {

			}
			return null;
		}

		protected void onPostExecute(Boolean unused) {
			super.onPostExecute(unused);
			dialog.dismiss();
			if (success.equalsIgnoreCase("200")) {
				Toast.makeText(ForgetPassword.this, "password sent to your mail", 2000).show();
				Intent intent = new Intent(ForgetPassword.this, LoginScreen.class);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(ForgetPassword.this, failure, 3000).show();
			}
		};
		

	}
}