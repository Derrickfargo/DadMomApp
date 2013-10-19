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
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends Activity{
	EditText username,password;
	Button login,signup;
	TextView forgetpassword;
	String usernameget,passwordget;
	String success = "";
	private Context context = this;
	String PREFS_NAME = "Expense_Tracker";
	SharedPreferences AppID;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginscreen);
		setTitle("DadMom");
		username = (EditText)findViewById(R.id.login_username);
		password = (EditText)findViewById(R.id.login_password);
		login = (Button)findViewById(R.id.bntton_login_login);
		signup = (Button)findViewById(R.id.bntton_login_signup);
		forgetpassword = (TextView)findViewById(R.id.textView_forgetpasssword);


		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				usernameget = username.getText().toString();
				passwordget = password.getText().toString();
				if(usernameget.equals("") && passwordget.equals("")){
					Toast.makeText(LoginScreen.this, "Enter User name & Password", 2000).show();
				}
				if(!usernameget.equals("") && passwordget.equals("")){
					Toast.makeText(LoginScreen.this, "Enter Password", 2000).show();
				}
				if(usernameget.equals("") && !passwordget.equals("")){
					Toast.makeText(LoginScreen.this, "Enter User name", 2000).show();
				}
				if(!usernameget.equals("") && !passwordget.equals("")){
					new Login().execute();
				}
			}
		});

		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intentSignup = new Intent(LoginScreen.this,SignUpScreen.class);
				startActivity(intentSignup);



			}
		});
		forgetpassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intentSignup = new Intent(LoginScreen.this,ForgetPassword.class);
				startActivity(intentSignup);


			}
		});
	}
	private class Login extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(LoginScreen.this, "","Loading, please wait...", true);
		}

		@SuppressWarnings("unused")
		protected Boolean doInBackground(Void... unused) {
			JSONObject json = new JSONObject();
			JSONArray jArray;
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://10.0.1.11/dadmom/webservices/login_user.php?user_name="+ usernameget + "&password=" + passwordget);

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
				SharedPreferences.Editor prefsEditor = AppID.edit();
				prefsEditor.putString("UserName", usernameget);
				prefsEditor.putString("Password", passwordget);
				prefsEditor.commit();
				Toast.makeText(LoginScreen.this, "Loged In Successfully", 3000).show();
				Intent intent = new Intent(LoginScreen.this, HomeActivity.class);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(LoginScreen.this, "Access denied", 3000).show();
			}
		};


	}
}
