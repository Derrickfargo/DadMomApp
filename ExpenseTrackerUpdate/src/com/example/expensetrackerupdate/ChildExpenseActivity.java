package com.example.expensetrackerupdate;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ChildExpenseActivity extends Activity{

	ImageView expensedateclick,location,pictureclick,recordaudio,recordvideo,expensediscripclick;
	TextView expensedateset,locationset,videoset,imageset,audioset,expensediscripset;
	Spinner categoey_type,payment_mode;
	EditText amount;
	String[] categorylist = {"","Breakfast","Lunch","Dinner","Clothes","Toys","Other"};
	String[] paymentlist = {"","Visa Card","Master Card","Cash"};
	final int Date_Dialog_ID=0;
	File mediaFile,imgfile,audofile;
	String success = "";
	int cDay,cMonth,cYear; // this is the instances of the current date
	Calendar cDate;
	Uri videoUri,galleryUri,audioUri ;
	Bitmap photo;
	Double Latitude,Longitude;
	Button saveexpense;
	int a,b,c,audioCnt,vdoCnt,imgCntcam;
	String vdoname,camimgname,audofilenme,getnotes;
	private static final int PICK_FROM_CAMERA = 1;
	private static final int PICK_FROM_FILE = 2;
	private static final int VIDEO_CAPTURE = 3;
	private static final int RQS_RECORDING = 4;
	int sDay,sMonth,sYear;
	String categoryget,paymentmodeget,dollar;
	public void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.childexpense);
		expensedateclick = (ImageView)findViewById(R.id.expenseDateImage);
		expensedateset = (TextView)findViewById(R.id.expenseDateText);
		categoey_type = (Spinner)findViewById(R.id.SpinnerCategory);
		payment_mode = (Spinner)findViewById(R.id.spinnerPayment);
		location = (ImageView)findViewById(R.id.expenseLocation);
		locationset = (TextView)findViewById(R.id.expenseLocationText);
		pictureclick = (ImageView)findViewById(R.id.picture);

		recordvideo = (ImageView)findViewById(R.id.video);
		videoset = (TextView)findViewById(R.id.videoText);
		imageset = (TextView)findViewById(R.id.pictureText);
		recordaudio = (ImageView)findViewById(R.id.audio);
		audioset = (TextView)findViewById(R.id.audioText);
		saveexpense = (Button)findViewById(R.id.save);
		amount = (EditText)findViewById(R.id.dollar_money);
		expensediscripclick = (ImageView)findViewById(R.id.expenseDescriptionImage);
		expensediscripset = (TextView)findViewById(R.id.expenseDescriptionText);

		Intent mapget = getIntent();
		Latitude = mapget.getDoubleExtra("LAT",0);
		Longitude = mapget.getDoubleExtra("LON",0);
		if(Latitude !=0 && Longitude !=0){
		
			locationset.setText("Latitude:"+Latitude+"\n"+"Longitude:"+Longitude);
		}
		expensediscripclick.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder ar = new AlertDialog.Builder(ChildExpenseActivity.this);
				ar.setTitle("Edit Description");
				final EditText input = new EditText(ChildExpenseActivity.this);
				ar.setView(input);
				ar.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						c = input.getEditableText().toString().length();
						Log.v("cccccc",""+c);
						getnotes = input.getEditableText().toString();
						expensediscripset.setText(getnotes);
						//							notesset.setText(getnotes);
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

		location.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mapintent = new Intent(ChildExpenseActivity.this,MapActivity.class);
				startActivity(mapintent);
			}
		});


		saveexpense.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dollar = amount.getText().toString();
				if(!expensedateset.equals("")){
					if(!dollar.equals("")){
						if(a > 0){
							if(b > 0){

								if(c  > 0){
									//									add condition for location
									if(audioCnt == 1){
										if(vdoCnt == 1 ){
											if(imgCntcam == 1){
												//												condition for barcode
												//												new RegisterDevice2().execute();
											}
											else{
												Toast.makeText(ChildExpenseActivity.this, "Add Image", 2000).show();
											}
										}
										else{
											Toast.makeText(ChildExpenseActivity.this, "Add Video", 2000).show();
										}
									}else{
										Toast.makeText(ChildExpenseActivity.this, "Record Audio", 2000).show();
									}
								}
								else{
									Toast.makeText(ChildExpenseActivity.this, "Add description", 2000).show();
								}

							}
							else{
								Toast.makeText(ChildExpenseActivity.this, "Select payment mode", 2000).show();
							}
						}
						else{
							Toast.makeText(ChildExpenseActivity.this, "Select category", 2000).show();
						}
					}
					else{
						Toast.makeText(ChildExpenseActivity.this, "Add amount", 2000).show();
					}

				}
				else{
					Toast.makeText(ChildExpenseActivity.this, "Select date", 2000).show();
				}

			}
		});

		recordaudio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				audofilenme = "myaudio"+System.currentTimeMillis()+".3gpp";
				audofile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+audofilenme);
				Intent intent =new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
				audioUri = Uri.fromFile(audofile);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, audioUri);
				startActivityForResult(intent, RQS_RECORDING);

			}
		});





		recordvideo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				vdoname ="myvideo"+System.currentTimeMillis()+".mp4";
				mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+vdoname);

				Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

				videoUri = Uri.fromFile(mediaFile);

				intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
				startActivityForResult(intent, VIDEO_CAPTURE);




			}
		});

		expensedateclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(Date_Dialog_ID);
			}
		});
		pictureclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				dialogshow();

			}
		});
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categorylist);
		adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		categoey_type.setAdapter(adapter_state);
		categoey_type.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				categoey_type.setSelection(position);
				a = position;
				categoryget = categorylist[position];
				Log.v("categoryget", ""+categoryget);
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		ArrayAdapter<String> adapter_state1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paymentlist);
		adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		payment_mode.setAdapter(adapter_state1);
		payment_mode.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				payment_mode.setSelection(position);
				b = position;
				paymentmodeget = paymentlist[position];

				Log.v("paymentmodeget", ""+paymentmodeget);
			}
			public void onNothingSelected(AdapterView<?> parent) {
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


		expensedateset.setText(date+"-"+(month+1)+"-"+year);




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
				camimgname ="myimage"+System.currentTimeMillis()+".png";
				imgfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+camimgname);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				videoUri = Uri.fromFile(imgfile);
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
					double bytes = imgfile.length();
					double kbytes = (bytes/1024);
					double mbytes = (kbytes/1024);
					if(mbytes > 5.000000){
						Toast.makeText(ChildExpenseActivity.this,"File size greater than 5 MB", 2000).show();
					}
					else{
						imageset.setText(camimgname);
						imgCntcam = 1;

					}
					//					photo = extras.getParcelable("data");
					//					Drawable d = new BitmapDrawable(getResources(), photo);
					//					captureImage.setBackgroundDrawable(d);
				}
			}	

			if(requestCode == RQS_RECORDING){
				double bytes = audofile.length();
				double kbytes = (bytes/1024);
				double mbytes = (kbytes/1024);
				if(mbytes > 5.000000){
					Toast.makeText(ChildExpenseActivity.this,"File size greater than 5 MB", 2000).show();
				}
				else{

					audioset.setText(audofilenme);
					audioCnt = 1;
				}
			}
			if (requestCode == VIDEO_CAPTURE) {
				if (resultCode == RESULT_OK) {
					Log.v("videoUri",""+videoUri);
					double bytes = mediaFile.length();
					double kbytes = (bytes/1024);
					double mbytes = (kbytes/1024);
					if(mbytes > 10.000000){
						Toast.makeText(ChildExpenseActivity.this,"File size greater than 10 MB", 2000).show();
					}
					else{
						videoset.setText(vdoname);
						vdoCnt = 1;
					}

				} else if (resultCode == RESULT_CANCELED) {
					Toast.makeText(this, "Video recording cancelled.", 
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(this, "Failed to record video", 
							Toast.LENGTH_LONG).show();
				}
			}
			if (requestCode == PICK_FROM_FILE) {
				galleryUri = data.getData();
				Bundle extras2 = data.getExtras();
				if (extras2 != null) {
					//					 double bytes = mediaFile.length();
					//						double kbytes = (bytes/1024);
					//						double mbytes = (kbytes/1024);
					//						if(mbytes > 5){
					//							Toast.makeText(ChildExpenseActivity.this,"File size greater than 5 MB", 2000).show();
					//						}
					//						else{
					String galleryfilename = galleryUri.getPath();
					File galryfile = new File(galleryfilename);
					if(galryfile.isDirectory()){
						galryfile.list();
					}
					else{
						String galler = galryfile.getName();

						imageset.setText(galler);
						imgCntcam = 1;
					}
					//						}
					photo = extras2.getParcelable("data");
					Drawable d = new BitmapDrawable(getResources(), photo);
					//					captureImage.setBackgroundDrawable(d);

				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private class RegisterDevice2 extends AsyncTask<Void, Void, Boolean> {
		private ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(ChildExpenseActivity.this, "","Loading, please wait...", true);
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
				Toast.makeText(ChildExpenseActivity.this, "Saved Successfully", 3000)
				.show();
				Intent simple = new Intent(ChildExpenseActivity.this,SimpleListActivity.class);
				startActivity(simple);
			} else {
				Toast.makeText(ChildExpenseActivity.this, " Failed", 3000)
				.show();
			}

		}
	}


}