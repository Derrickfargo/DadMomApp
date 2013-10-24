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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends Activity{
	EditText username,password;
	Button login,signup;
	TextView forgetpassword,headettext;
	String usernameget,passwordget;
	String success = "",failure="";
	String url;
	String userName,passWord,personName,doB,emaiL,phonE,addresS,pinCode,suB;
	private Context context = this;
	String PREFS_NAME = "Expense_Tracker";
	SharedPreferences AppID;
	ArrayList<String> arryList;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loginscreen);
		username = (EditText)findViewById(R.id.login_username);
		password = (EditText)findViewById(R.id.login_password);
		login = (Button)findViewById(R.id.bntton_login_login);
		signup = (Button)findViewById(R.id.bntton_login_signup);
		forgetpassword = (TextView)findViewById(R.id.textView_forgetpasssword);
		headettext = (TextView)findViewById(R.id.Text);
		headettext.setText("DadMom");
		
		arryList = new ArrayList<String>();
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
				JSONObject json2;
				try {
					  url= ExpenseTrackerConstant.webServiceUrl+ExpenseTrackerConstant.loginuser;
			        	HttpClient httpclient = new DefaultHttpClient();
			        	HttpPost httppost = new HttpPost(url);
					List<NameValuePair> params = new ArrayList<NameValuePair>();


				     params.add(new BasicNameValuePair("user_name", usernameget));
				     params.add(new BasicNameValuePair("password", passwordget));
				     
			            httppost.setEntity(new UrlEncodedFormEntity(params));
			         
			            HttpResponse httpResponse = httpclient.execute(httppost);
			        
			            HttpEntity httpEntity = httpResponse.getEntity();
			            if (httpEntity != null) {
							Log.d("Response", "messageLogin");
							InputStream instream = httpEntity.getContent();
							String result = Refrence.convertStreamToString(instream);
							json = new JSONObject(result);
							Log.d("re" + result, "msg");
//							
							json2 = json.getJSONObject("response");
							success = json2.getString("code");
							
							failure = json2.getString("msg");
							Log.d("code", "code" + success);
							if(success.equals("200")){
							userName = json2.getString("user_name");
					
							passWord = json2.getString("password");
							personName = json2.getString("person_name");
							doB = json2.getString("dob");
							emaiL = json2.getString("email");
							phonE = json2.getString("phone");
							addresS = json2.getString("address");
							
							pinCode = json2.getString("pin_code");
							suB = json2.getString("is_subscribed");
							
							arryList.add(userName);
							arryList.add(passWord);
							arryList.add(personName);
							arryList.add(doB);
							arryList.add(emaiL);
							arryList.add(phonE);
							arryList.add(addresS);
							arryList.add(pinCode);
							arryList.add(suB);
							Log.d("arryList", "vvvv"+arryList);
							}
					
				}
			} catch (Exception e) {

			}
			return null;
		}

		protected void onPostExecute(Boolean unused) {
			super.onPostExecute(unused);
			dialog.dismiss();
			if (success.equalsIgnoreCase("200")) {
				AppID = getApplication().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
				SharedPreferences.Editor prefsEditor = AppID.edit();
				prefsEditor.putString("UserName", usernameget);
				prefsEditor.putString("Password", passwordget);
				prefsEditor.putString("personname", personName);
				prefsEditor.putString("dateob", doB);
				prefsEditor.putString("EMail", emaiL);
				prefsEditor.putString("Phone", phonE);
				prefsEditor.putString("Address", addresS);
				prefsEditor.putString("Pincode", pinCode);
				prefsEditor.putString("Subccribe", suB);
				prefsEditor.commit();
				Toast.makeText(LoginScreen.this, "Loged In Successfully", 3000).show();
				Intent intent = new Intent(LoginScreen.this, HomeActivity.class);
				intent.putExtra("ARRAYLIST", arryList);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(LoginScreen.this, failure, 3000).show();
			}
		};


	}
}
