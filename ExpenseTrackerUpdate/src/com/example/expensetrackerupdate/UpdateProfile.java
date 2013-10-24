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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class UpdateProfile extends Activity{
ArrayList<String>updateArray;
Button submit;
EditText Name,Email,Phone,Address,PinCode;
CheckBox Newsletter;
ImageView dateofbirth;
TextView setdateofbirth,headerset;
String name,email,phone,address,pincode,dateOb;
String NAME,EMAIL,PHONE,ADDRESS,PINCODE,SUBSCRIPTION,DOB;
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
	public void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.updateprofile);
		submit = (Button)findViewById(R.id.signup_button_signup);
		
		Name = (EditText)findViewById(R.id.signup_editText_name);
		Email = (EditText)findViewById(R.id.signup_editText_email);
		Phone = (EditText)findViewById(R.id.signup_editText_phone);
		Address = (EditText)findViewById(R.id.signup_editText_address);
		PinCode = (EditText)findViewById(R.id.signup_editText_pincode);
		Newsletter = (CheckBox)findViewById(R.id.chkBox);
		dateofbirth = (ImageView)findViewById(R.id.pickupdateimage);
		setdateofbirth = (TextView)findViewById(R.id.pickuddatetext);
		headerset = (TextView)findViewById(R.id.Text);
		headerset.setText("SignUp");
		
		
		
		updateArray = new ArrayList<String>();
		Intent loginintent = getIntent();
		updateArray = loginintent.getStringArrayListExtra("arrayEdit");
		Log.v("updateArray",""+updateArray);
		
//		Get Value From ArrayList to Set.........
		
		NAME = updateArray.get(2);
		DOB = updateArray.get(3);
		EMAIL = updateArray.get(4);
		PHONE = updateArray.get(5);
		ADDRESS = updateArray.get(6);
		PINCODE = updateArray.get(7);
		SUBSCRIPTION = updateArray.get(8);
		
//Set The edittext......
		Name.setText(NAME);
		setdateofbirth.setText(DOB);
		Email.setText(EMAIL);
		Phone.setText(PHONE);
		Address.setText(ADDRESS);
		PinCode.setText(PINCODE);
		if(SUBSCRIPTION.equals("0")){
			Newsletter.setChecked(true);
		}
		
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
				
				name = Name.getText().toString();
				email = Email.getText().toString();
				phone = Phone.getText().toString();
				address = Address.getText().toString();
				pincode = PinCode.getText().toString();
				if(!name.equals("")){
					if(!email.equals("")){
						if (checkEmail(email)) {
							if(!phone.equals("")){
								if(!address.equals("")){
									if(!pincode.equals("")){
										Intent intent = new Intent(UpdateProfile.this, HomeActivity.class);
										startActivity(intent);
									}
									else{
										Toast.makeText(UpdateProfile.this, "Enter pin code", 2000).show();
									}
								}
								else{
									Toast.makeText(UpdateProfile.this, "Enter address", 2000).show();
								}
							}
							else{
								Toast.makeText(UpdateProfile.this, "Enter phone number", 2000).show();
							}
						}
						else{
							Toast.makeText(UpdateProfile.this, "Enter valid Email", 2000).show();
						}
					}
					else{
						Toast.makeText(UpdateProfile.this, "Enter Email", 2000).show();
					}
				}
				else{
					Toast.makeText(UpdateProfile.this, "Enter Name", 2000).show();
				}
			}
		});
	}
	private boolean checkEmail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
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
		private class UpdateUser extends AsyncTask<Void, Void, Boolean> {
			private ProgressDialog dialog;

			protected void onPreExecute() {
				dialog = ProgressDialog.show(UpdateProfile.this, "","Loading, please wait...", true);
			}

			@SuppressWarnings("unused")
			protected Boolean doInBackground(Void... unused) {
				JSONObject json;
				JSONObject json2;
				try {
					  url= ExpenseTrackerConstant.webServiceUrl+ExpenseTrackerConstant.registerUser+"edit";
			        	HttpClient httpclient = new DefaultHttpClient();
			        	HttpPost httppost = new HttpPost(url);
					List<NameValuePair> params = new ArrayList<NameValuePair>();
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
					Intent intent = new Intent(UpdateProfile.this, LoginScreen.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(UpdateProfile.this, failure, 3000).show();
				}
			};
			

		}
}
