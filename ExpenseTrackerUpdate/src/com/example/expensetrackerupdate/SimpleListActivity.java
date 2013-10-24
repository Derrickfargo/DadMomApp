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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleListActivity extends Activity{
	ListView list;
	Spinner spinnerSort;
	String success="";
	String url;
	ImageView edit_profile,baCk;
	TextView headertext;
	private String[] Datetype = {"Select","Pick up date","Captured date"};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.simplelistactivity);
		edit_profile = (ImageView)findViewById(R.id.editProfile);
		headertext = (TextView)findViewById(R.id.Text);
		baCk = (ImageView)findViewById(R.id.logOut);
		baCk.setImageResource(R.drawable.arrow);
		
//		new sendData().execute();
		list = (ListView)findViewById(R.id.MessageList);
		spinnerSort = (Spinner)findViewById(R.id.SpinnerCategory);
		
		
		
		edit_profile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intentbk = new Intent(SimpleListActivity.this,ResidentialCameraActivity.class);
				startActivity(intentbk);
			}
		});
			
		baCk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleListActivity.this.finish();
			}
		});
		
		headertext.setText("Residential List");
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Datetype);
		adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinnerSort.setAdapter(adapter_state);
		spinnerSort.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				spinnerSort.setSelection(position);
				
				if(position == 1){
					list.setAdapter(new Customizelist());
				}
				if(position == 2){
					list.setAdapter(new Customizelist());
				}
				
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) {
				// TODO Auto-generated method stub
				Intent resicam = new Intent(SimpleListActivity.this,ResidentialEditActivity.class);
				startActivity(resicam);
			}
		});
		
	}
	public class Customizelist extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int posoition, View v, ViewGroup parent) {
			// TODO Auto-generated method stub
			View vie;
			LayoutInflater inflater = getLayoutInflater();
			vie = inflater.inflate(R.layout.simpleactivity_list_row, parent,false);
		TextView date,time;
		date = (TextView)findViewById(R.id.text_date);
		time = (TextView)findViewById(R.id.text_time);
		
			return vie;
		}
		
	}
	private class sendData extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(SimpleListActivity.this, "","Loading, please wait...", true);
		}

		@SuppressWarnings("unused")
		protected Boolean doInBackground(Void... unused) {
			JSONObject json = new JSONObject();
			JSONArray jArray;
			try {

				HttpClient httpclient = new DefaultHttpClient();
HttpPost httppost = new HttpPost("http://10.0.1.11/dadmom/webservices/get_residentials.php?");

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
//				Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
//				startActivity(intent);
//				finish();
			} else {
				Toast.makeText(SimpleListActivity.this, "Access denied", 3000).show();
			}
		};
		

	}
	
}
