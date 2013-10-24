package com.example.expensetrackerupdate;

import java.io.File;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResidentialCameraActivity extends Activity{
	ImageView settings,back,imgselect;
	TextView headerset;
	Bitmap photo;
	Button btn_detail,btn_save,btn_cancel;
	private static final int PICK_FROM_CAMERA = 1;
	private static final int PICK_FROM_FILE = 2;
	int cDay,cMonth,cYear; 
	Calendar cDate;
	String captureddate,capturetime,camimgname;
	Calendar mcurrentTime;
	int hour,minute;
//	File imgfile;
//	Uri uri;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.residential_camera);
		settings = (ImageView)findViewById(R.id.editProfile);
		back = (ImageView)findViewById(R.id.logOut);
		headerset = (TextView)findViewById(R.id.Text);
		imgselect = (ImageView)findViewById(R.id.residentialCamera);
		btn_detail = (Button)findViewById(R.id.detail);
		btn_save = (Button)findViewById(R.id.save);
		btn_cancel = (Button)findViewById(R.id.cancel);
		
		settings.setImageResource(R.drawable.setting);
		back.setImageResource(R.drawable.arrow);
		headerset.setText("Add Residential");
		settings.setVisibility(View.INVISIBLE);
		back.setVisibility(View.INVISIBLE);
		Toast.makeText(ResidentialCameraActivity.this,"Tap here for select image", 2000).show();
		imgselect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dialogshow();
				cDate=Calendar.getInstance();
				cDay=cDate.get(Calendar.DAY_OF_MONTH);
				cMonth=cDate.get(Calendar.MONTH);
				cYear=cDate.get(Calendar.YEAR);
				captureddate = cDay+"-"+(cMonth+1)+"-"+cYear;
				 mcurrentTime = Calendar.getInstance();
				 hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
			 minute = mcurrentTime.get(Calendar.MINUTE);
			 capturetime = ""+hour+":"+"minute";
			}
		});
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ResidentialCameraActivity.this.finish();
			}
		});
		
		btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				call webservise;
				Toast.makeText(ResidentialCameraActivity.this, "Saved successfully", 2000).show();
				ResidentialCameraActivity.this.finish();
			}
		});
		
		btn_detail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(photo!=null){
				Intent intdetail = new Intent(ResidentialCameraActivity.this,ResidentialActivity.class);
				intdetail.putExtra("CAPTUREDATE", captureddate);
				intdetail.putExtra("CAPTURETIME", capturetime);
				intdetail.putExtra("PICTURE", photo);
				startActivity(intdetail);
			}
				else{
					Toast.makeText(ResidentialCameraActivity.this, "Select image", 2000).show();
				}
				}
		});
	}
	public void dialogshow(){
		new AlertDialog.Builder(this).setTitle("Select Image").setMessage("Using").setPositiveButton("Camera", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface paramDialogInterface, int paramInt)
			{
//				camimgname ="myimage"+System.currentTimeMillis()+".png";
//				imgfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+camimgname);
//				uri = Uri.fromFile(imgfile);
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
					imgselect.setBackgroundDrawable(d);
				}
			}
			if (requestCode == PICK_FROM_FILE) {
				Bundle extras2 = data.getExtras();
				if (extras2 != null) {

					photo = extras2.getParcelable("data");
					Drawable d = new BitmapDrawable(getResources(), photo);
					imgselect.setBackgroundDrawable(d);

				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
