package com.example.expensetrackerupdate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpScreen extends Activity{
	Button submit;
	EditText UserName,Password,RetypePassword,Name,Email,Phone,Address,PinCode;
	CheckBox Newsletter;
	ImageView dateofbirth;
	TextView setdateofbirth,headerset;
	String dateOb;
	String username,password,retypepassword,name,email,phone,address,pincode;
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	String success = "";
	final int Date_Dialog_ID=0;
	int cDay,cMonth,cYear; // this is the instances of the current date
	Calendar cDate;
	int sDay,sMonth,sYear;
	String url;
	String subscription = "0";
	String failure="";
    private static final int DATE_PICKER_ID = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.signupscreen);
		submit = (Button)findViewById(R.id.submit_button);
		UserName = (EditText)findViewById(R.id.signup_editText_username);
		Password = (EditText)findViewById(R.id.signup_editText_password);
		RetypePassword = (EditText)findViewById(R.id.signup_editText_confirmpassword);
		Name = (EditText)findViewById(R.id.signup_editText_name);
		Email = (EditText)findViewById(R.id.signup_editText_email);
		Phone = (EditText)findViewById(R.id.signup_editText_phone);
		Address = (EditText)findViewById(R.id.signup_editText_address);
		PinCode = (EditText)findViewById(R.id.signup_editText_pincode);
		Newsletter = (CheckBox)findViewById(R.id.chkBox);
		dateofbirth = (ImageView)findViewById(R.id.Date_birth);
		setdateofbirth = (TextView)findViewById(R.id.date_birth_text);
		headerset = (TextView)findViewById(R.id.Text);
		headerset.setText("SignUp");
		
		Newsletter.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				subscription = "1";
				
			}
		});
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				username = UserName.getText().toString();
				password = Password.getText().toString();
				retypepassword = RetypePassword.getText().toString();
				name = Name.getText().toString();
				email = Email.getText().toString();
				phone = Phone.getText().toString();
				address = Address.getText().toString();
				pincode = PinCode.getText().toString();
				if(username.equals("") && password.equals("") && retypepassword.equals("") && name.equals("") &&  email.equals("") &&
						phone.equals("") && address.equals("") && pincode.equals("")){
					Toast.makeText(SignUpScreen.this,"Enter All Fields !", 3000).show();
					
				}
				if(!username.equals("") ){
					if(!password.equals("")){
						if(! retypepassword.equals("")){
							if(retypepassword.equals(password)){
							if(!name.equals("")){
								if(!dateOb.equals("")){
								if(!email.equals("")){
									if (checkEmail(email)) {
									if(!phone.equals("")){
										if(!address.equals("")){
											if(! pincode.equals("")){
												
												Log.d("dateOb",""+dateOb);
												Log.d("subscription",""+subscription);
												new Signup().execute();
												
												
												
												
												
											}
											else{
												Toast.makeText(SignUpScreen.this,"Enter Pin Code", 3000).show();
											}
										}
										else{
											Toast.makeText(SignUpScreen.this,"Enter Address", 3000).show();
										}
									}
									
									else{
										Toast.makeText(SignUpScreen.this,"Please enter correct format of Email", 3000).show();
									}
									}
									else{
										Toast.makeText(SignUpScreen.this,"Enter Phone Number", 3000).show();
									}
								}
								else{
									Toast.makeText(SignUpScreen.this,"Enter Email", 3000).show();
								}
							}
							else{
								Toast.makeText(SignUpScreen.this,"Enter Date of birth", 3000).show();
							}
							}
							else{
								Toast.makeText(SignUpScreen.this,"Enter Name", 3000).show();
							}
							
						}
						
						else{
							Toast.makeText(SignUpScreen.this,"Confirm Password not match", 3000).show();
						}
						}
						else{
							Toast.makeText(SignUpScreen.this,"Confirm Password", 3000).show();
						}
						
					}
					else{
						Toast.makeText(SignUpScreen.this,"Enter Password", 3000).show();
					}
					
					
				}
				else{
					Toast.makeText(SignUpScreen.this,"Enter User Name", 3000).show();
				}
				
				
			}
		});
		
		//getting current date
		cDate=Calendar.getInstance();
		cDay=cDate.get(Calendar.DAY_OF_MONTH);
		cMonth=cDate.get(Calendar.MONTH);
		cYear=cDate.get(Calendar.YEAR);
		dateofbirth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(Date_Dialog_ID);
			        
			}
		});
}
	// Email Validation
		private boolean checkEmail(String email) {
			return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
		}
		private class Signup extends AsyncTask<Void, Void, Boolean> {
			private ProgressDialog dialog;

			protected void onPreExecute() {
				dialog = ProgressDialog.show(SignUpScreen.this, "","Loading, please wait...", true);
			}

			@SuppressWarnings("unused")
			protected Boolean doInBackground(Void... unused) {
				JSONObject json;
				JSONObject json2;
				try {
					  url= ExpenseTrackerConstant.webServiceUrl+ExpenseTrackerConstant.registerUser+"add";
			        	HttpClient httpclient = new DefaultHttpClient();
			        	HttpPost httppost = new HttpPost(url);
					List<NameValuePair> params = new ArrayList<NameValuePair>();


				     params.add(new BasicNameValuePair("user_name", username));
				     params.add(new BasicNameValuePair("password", password));
				     params.add(new BasicNameValuePair("person_name", name));
				     params.add(new BasicNameValuePair("dob", dateOb));
				     params.add(new BasicNameValuePair("email", email));
				     params.add(new BasicNameValuePair("phone", phone));
				     params.add(new BasicNameValuePair("address", address));
				     params.add(new BasicNameValuePair("pincode", pincode));
				     params.add(new BasicNameValuePair("sub", subscription));
				 	Log.d("BasicNameValuePair", "params"+params);
				     
//			            String paramString = URLEncodedUtils.format(params, "utf-8");
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
							
						
	

					}
				} catch (Exception e) {

				}
				return null;
			}

			protected void onPostExecute(Boolean unused) {
				super.onPostExecute(unused);
				dialog.dismiss();
				if (success.equalsIgnoreCase("200")) {
					Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(SignUpScreen.this, failure, 3000).show();
				}
			};
			

		}
		protected Dialog onCreateDialog(int id) {

			switch (id) {
			case Date_Dialog_ID:
			return new DatePickerDialog(this, onDateSet, cYear, cMonth,cDay);
			}
			return null;
			}

			private void updateDateDisplay(int year,int month,int date) {
			// TODO Auto-generated method stub
				setdateofbirth.setText(date+"/"+(month+1)+"/"+year);
				dateOb = date+"/"+(month+1)+"/"+year;
				
			}
			private OnDateSetListener onDateSet=new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
			// TODO Auto-generated method stub
			System.out.println("2");
			sYear=year;
			sMonth=monthOfYear;
			sDay=dayOfMonth;
			updateDateDisplay(sYear,sMonth,sDay);
			}
			};
}
