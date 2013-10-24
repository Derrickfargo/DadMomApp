package com.example.expensetrackerupdate;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ImageView;


public class ResidentialActivity extends Activity{
	ImageView captureImage,pickupdate,pickuptime,pickupLocationmap,dropdate,calanDer,dropofftime,dropofLocationMap;
	TextView captureddate,pickupdateset,pickupTimeText,picklocation,dropdateset,dropoftimeset,selectimg,droplocation,notesset;
	final int Date_Dialog_ID=0;
	int cDay,cMonth,cYear; // this is the instances of the current date
	Calendar cDate;
	int sDay,sMonth,sYear;
	
	Bitmap photo;
	Button save;
	String success = "",getnotes,captureDateGet="",captureTimeGet="";
	int pictureGet=0;
	ImageView notesclicked;
	int i=0,j=0,k=0,l=0;
	ImageView logoutclk,editdata;
	TextView headerset;


	Double latget=0.00,longget=0.00;
	private static final int DATE_PICKER_ID = 0;
	private static final int PICK_FROM_CAMERA = 1;
	private static final int PICK_FROM_FILE = 2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.residential);
		captureImage = (ImageView)findViewById(R.id.capturedImage);
		pickupdate = (ImageView)findViewById(R.id.pickupdateimage);
		captureddate = (TextView)findViewById(R.id.capturedDate);
		pickupTimeText = (TextView)findViewById(R.id.pickuptimetext);
		pickupdateset = (TextView)findViewById(R.id.pickuddatetext);
		picklocation = (TextView)findViewById(R.id.pickupLocationText);
		pickuptime = (ImageView)findViewById(R.id.pickuptimeimage);
		pickupLocationmap = (ImageView)findViewById(R.id.pickupLocation);
		dropofLocationMap = (ImageView)findViewById(R.id.dropoffLocation);
		dropdate = (ImageView)findViewById(R.id.dropoffdateimage);
		dropdateset = (TextView)findViewById(R.id.dropoffdatetext);
		calanDer = (ImageView)findViewById(R.id.calendar);
		dropofftime = (ImageView)findViewById(R.id.dropofftimeimage);
		dropoftimeset = (TextView)findViewById(R.id.dropofftimetext);
		selectimg = (TextView)findViewById(R.id.img_tab);
		droplocation = (TextView)findViewById(R.id.dropoffLocationText);
		notesclicked = (ImageView)findViewById(R.id.notes);
		save = (Button)findViewById(R.id.resi_save);
		logoutclk = (ImageView)findViewById(R.id.logOut);
		editdata = (ImageView)findViewById(R.id.editProfile);
		headerset = (TextView)findViewById(R.id.Text);
		notesset = (TextView)findViewById(R.id.notesid);
		editdata.setImageResource(R.drawable.setting);
		headerset.setText("Add Residential");
		logoutclk.setImageResource(R.drawable.arrow);
		logoutclk.setVisibility(View.INVISIBLE);
		editdata.setVisibility(View.INVISIBLE);
		
		
		
		Intent intentget = getIntent();
		intentget.getStringExtra("CAPTUREDATE");
		intentget.getStringExtra("CAPTURETIME");
		intentget.getIntExtra("PICTURE", 0);
		
		
		editdata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent resiedit = new Intent(ResidentialActivity.this,ResidentialEditActivity.class);
//				startActivity(resiedit);
			}
		});
		
		logoutclk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
AlertDialog.Builder alert = new AlertDialog.Builder(ResidentialActivity.this);
alert.setTitle("Logout");
alert.setMessage("Are you sure");
alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		Intent res = new Intent(ResidentialActivity.this,LoginScreen.class);
		startActivity(res);
		ResidentialActivity.this.finish();
	}
	
});
alert.setNegativeButton("No", null).show();
			}
		});

		notesclicked.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder ar = new AlertDialog.Builder(ResidentialActivity.this);
				ar.setTitle("Edit your notes");
				final EditText input = new EditText(ResidentialActivity.this);
				ar.setView(input);
				ar.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
					
							getnotes = input.getEditableText().toString();
							notesset.setText(getnotes);
					}
				});
				ar.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					} 
				});
				ar.show();
			}
		});



		Intent posget = getIntent();
		latget = posget.getDoubleExtra("LATI", 0);
		longget = posget.getDoubleExtra("LONGI", 0);
		picklocation.setText("Lat="+latget+"  Long="+longget);


		dropofftime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				l=1;
				timePicker();
			}
		});
		calanDer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent caLander = new Intent(ResidentialActivity.this,MyCalendarActivity.class);
//				startActivity(caLander);
			}
		});


		pickuptime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				k=1;
				timePicker();
			}
		});
		pickupdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				j=1;
				showDialog(Date_Dialog_ID);


			}
		});
		dropdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=1;
				showDialog(Date_Dialog_ID);

			}
		});
		selectimg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//getting current date
				dialogshow();
				cDate=Calendar.getInstance();
				cDay=cDate.get(Calendar.DAY_OF_MONTH);
				cMonth=cDate.get(Calendar.MONTH);
				cYear=cDate.get(Calendar.YEAR);
				captureddate.setText(cDay+"-"+(cMonth+1)+"-"+cYear);
			}
		});


		pickupLocationmap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent mapintent = new Intent(ResidentialActivity.this,MapActivity.class);
//				startActivity(mapintent);

			}
		});


		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(photo!=null){
					if(!pickupdateset.equals("")){
						if(!pickupTimeText.equals("")){
							if(!picklocation.equals("")){
								if(!dropdateset.equals("")){
									if(!dropoftimeset.equals("")){
										if(!droplocation.equals("")){

											Toast.makeText(ResidentialActivity.this, "Please Wait uloading", 2000).show();
//											new RegisterDevice().execute();
											Intent simple = new Intent(ResidentialActivity.this,SimpleListActivity.class);
											startActivity(simple);


										}
										else{
											Toast.makeText(ResidentialActivity.this, "Select Drop Off Location", 2000).show();
										}
									}
									else{
										Toast.makeText(ResidentialActivity.this, "Select Drop Off Time", 2000).show();
									}
								}
								else{
									Toast.makeText(ResidentialActivity.this, "Select Drop Off Date", 2000).show();
								}
							}
							else{
								Toast.makeText(ResidentialActivity.this, "Select pick Up Location", 2000).show();
							}
						}

						else{
							Toast.makeText(ResidentialActivity.this, "Select pick Up Time", 2000).show();
						}
					}
					else{
						Toast.makeText(ResidentialActivity.this, "Select pick Up Date", 2000).show();
					}


				}
				else{
					Toast.makeText(ResidentialActivity.this, "Select Image", 2000).show();
				}

			}
		});


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
		if(i==1){

			dropdateset.setText(date+"-"+(month+1)+"-"+year);
			i=0;
		}

		if(j==1){
			pickupdateset.setText(date+"-"+(month+1)+"-"+year);
			j=0;
		}

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
	public void dialogshow(){
		new AlertDialog.Builder(this).setTitle("Select Image").setMessage("Using").setPositiveButton("Camera", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface paramDialogInterface, int paramInt)
			{
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
				try {
					intent.putExtra("return-data", true);
					startActivityForResult(intent, PICK_FROM_CAMERA);
				} catch (ActivityNotFoundException e) {
					// Do nothing for now
				}
			}
		}).setNegativeButton("Gallery",new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface paramDialogInterface, int paramInt)
			{
				Intent intent = new Intent();
				// call android default gallery
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				try {
					intent.putExtra("return-data", true);

					startActivityForResult(Intent.createChooser(intent,
							"Complete action using"), PICK_FROM_FILE);

				} catch (ActivityNotFoundException e) {
					// Do nothing for now
				}
			}
		}).show();
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try{
			if (requestCode == PICK_FROM_CAMERA) {
				Bundle extras = data.getExtras();
				if (extras != null) {
					photo = extras.getParcelable("data");
					Drawable d = new BitmapDrawable(getResources(), photo);
					captureImage.setBackgroundDrawable(d);
				}
			}
			if (requestCode == PICK_FROM_FILE) {
				Bundle extras2 = data.getExtras();
				if (extras2 != null) {

					photo = extras2.getParcelable("data");
					Drawable d = new BitmapDrawable(getResources(), photo);
					captureImage.setBackgroundDrawable(d);

				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void timePicker(){
		Calendar mcurrentTime = Calendar.getInstance();
		int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
		int minute = mcurrentTime.get(Calendar.MINUTE);
		TimePickerDialog mTimePicker;
		mTimePicker = new TimePickerDialog(ResidentialActivity.this, new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
				if(k==1){
					pickupTimeText.setText( selectedHour + ":" + selectedMinute);
					k=0;
				}
				if(l==1){
					dropoftimeset.setText( selectedHour + ":" + selectedMinute);
					l=0;
				}
			}
		}, hour, minute, true);//Yes 24 hour time
		mTimePicker.setTitle("Select Time");
		mTimePicker.show();
	}
	private class RegisterDevice extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(ResidentialActivity.this, "","Loading, please wait...", true);
		}

		protected Boolean doInBackground(Void... unused) {
			JSONObject json = new JSONObject();
			JSONArray jArray;
			ByteArrayBody bab;
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("Webservise");
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);
				//				reqEntity.addPart("name", new StringBody(name));
				//				reqEntity.addPart("email", new StringBody(email));
				//				reqEntity.addPart("referred_by", new StringBody(ReferalEmail));
				if (!photo.equals("") || !photo.equals(null)) {

					ByteArrayOutputStream bao1 = new ByteArrayOutputStream();
					photo.compress(Bitmap.CompressFormat.PNG, 90, bao1);
					byte[] imagearray1 = bao1.toByteArray();
					Log.d("INIF" + imagearray1, "INSIDE");
					bab = new ByteArrayBody(imagearray1, "Image"
							+ System.currentTimeMillis() + ".png");

					reqEntity.addPart("photo_url", bab);
				}
				httppost.setEntity(reqEntity);
				Log.d("fdsf", "sdf" + reqEntity.toString());
				HttpResponse response = httpclient.execute(httppost);
				System.out.println("Gurdev_Register*****response*****"
						+ response);
				HttpEntity entity = response.getEntity();
				System.out.println("Gurdev_Register*****entity*****" + entity);
				Log.d("bbb", "bbb" + entity);
				// If the response does not enclose an entity, there is no need
				if (entity != null) {
					Log.d("Response", "messageLogin" + success);
					InputStream instream = entity.getContent();
					String result = Refrence.convertStreamToString(instream);
					json = new JSONObject(result);

					success = json.getString("invitation_msg");
					Log.d("Response", "messageLogin" + success);
					jArray = json.getJSONArray("responsedata");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Boolean unused) {
			super.onPostExecute(unused);
			dialog.dismiss();
			if (success.equalsIgnoreCase("Success")) {
				Toast.makeText(ResidentialActivity.this, "Saved Successfully", 3000)
				.show();
				Intent simple = new Intent(ResidentialActivity.this,SimpleListActivity.class);
				startActivity(simple);
			} else {
				Toast.makeText(ResidentialActivity.this, " Failed", 3000)
				.show();
			}

		}
	}



}
