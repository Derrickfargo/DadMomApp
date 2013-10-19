package com.example.expensetrackerupdate;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Residential extends Activity{
	ImageView captureImage,pickupdate,pickuptime,pickupLocationmap,dropdate,calanDer,dropofftime;
	TextView captureddate,pickupdateset,pickupTimeText,picklocation,dropdateset,dropoftimeset,selectimg;
	final int Date_Dialog_ID=0;
	int cDay,cMonth,cYear; // this is the instances of the current date
	Calendar cDate;
	int sDay,sMonth,sYear;
	Bitmap photo;
	Button save;
	int i=0,j=0,k=0,l=0;
	//	Boolean isClickable = false;

	Double latget=0.00,longget=0.00;
	private static final int DATE_PICKER_ID = 0;
	private static final int PICK_FROM_CAMERA = 1;
	private static final int PICK_FROM_FILE = 2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.residential);
		captureImage = (ImageView)findViewById(R.id.capturedImage);
		pickupdate = (ImageView)findViewById(R.id.pickupdateimage);
		//		pickupdate = (ImageView)findViewById(R.id.pickupdateimage);
		captureddate = (TextView)findViewById(R.id.capturedDate);
		pickupTimeText = (TextView)findViewById(R.id.pickuptimetext);
		pickupdateset = (TextView)findViewById(R.id.pickuddatetext);
		picklocation = (TextView)findViewById(R.id.pickupLocationText);
		pickuptime = (ImageView)findViewById(R.id.pickuptimeimage);
		pickupLocationmap = (ImageView)findViewById(R.id.pickupLocation);
		dropdate = (ImageView)findViewById(R.id.dropoffdateimage);
		dropdateset = (TextView)findViewById(R.id.dropoffdatetext);
		calanDer = (ImageView)findViewById(R.id.calendar);
		dropofftime = (ImageView)findViewById(R.id.dropofftimeimage);
		dropoftimeset = (TextView)findViewById(R.id.dropofftimetext);
		selectimg = (TextView)findViewById(R.id.img_tab);
		save = (Button)findViewById(R.id.resi_save);
		
		//getting current date
		cDate=Calendar.getInstance();
		cDay=cDate.get(Calendar.DAY_OF_MONTH);
		cMonth=cDate.get(Calendar.MONTH);
		cYear=cDate.get(Calendar.YEAR);
		captureddate.setText(cDay+"-"+(cMonth+1)+"-"+cYear);


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
				Intent caLander = new Intent(Residential.this,MyCalendarActivity.class);
				startActivity(caLander);
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
				dialogshow();
			}
		});


		pickupLocationmap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent mapintent = new Intent(Residential.this,MapForLocation.class);
				startActivity(mapintent);

			}
		});
		
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!photo.equals("") || !photo.equals(null)){
//					Check all here
//					call Web service
					Toast.makeText(Residential.this, "Please Wait uloading", 2000);
					Intent mapintent = new Intent(Residential.this,SimpleListActivity.class);
					startActivity(mapintent);
					
				}
				else{
					Toast.makeText(Residential.this, "Select Image", 2000);
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
		mTimePicker = new TimePickerDialog(Residential.this, new TimePickerDialog.OnTimeSetListener() {
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
}
