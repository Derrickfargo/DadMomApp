package com.example.expensetrackerupdate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class ReminderActivity extends Activity{

	ImageView reminderdateclick,remindertimeclick,notes;
	TextView reminderdateset,remindertimeset,notesset,remind;
	Spinner reminderspin;
	String[] reminderList =  { "","One time", "Daily", "1st of every month" };
	String reminderget;
	private boolean dateFlag = false;
	private boolean timeFlag = false;
	static final int DATE_DIALOG_ID = 1;
	static final int TIME_DIALOG_ID = 0;
	int sDay,sMonth,sYear;
	public static int notificationCount;
	String date1,time,notesget,getnotes;
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;
	private String time1;
	private Calendar c; 
	private Context mContext; 
	private String contentText; 
	Button saveclk,cancelclk;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reminder);    
		mContext = this;
		reminderspin = (Spinner)findViewById(R.id.reminderType);
		reminderdateclick = (ImageView)findViewById(R.id.reminder_dateimage);
		reminderdateset = (TextView)findViewById(R.id.reminder_datetext);
		remindertimeclick = (ImageView)findViewById(R.id.reminder_timeimage);
		remindertimeset = (TextView)findViewById(R.id.reminder_timetext);
		notes = (ImageView)findViewById(R.id.notes_image);
		notesset = (TextView)findViewById(R.id.notes_text);
		saveclk = (Button)findViewById(R.id.save);
		cancelclk = (Button)findViewById(R.id.cancel);
		remind = (TextView)findViewById(R.id.TextView1);
		remind.setText("Add Reminder");
		c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);  
		
		
		saveclk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onReminderClicked(v);
			}
		});
		
		cancelclk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ReminderActivity.this.finish();
			}
		});
	
		notes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				AlertDialog.Builder ar = new AlertDialog.Builder(ReminderActivity.this);
				ar.setTitle("Edit your notes");
				final EditText input = new EditText(ReminderActivity.this);
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
		

	
		
		
		
		reminderdateclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);    
				
			}
		});
		
		remindertimeclick.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID);
			
			}
		});
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, reminderList);
		adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		reminderspin.setAdapter(adapter_state);
		reminderspin.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				reminderspin.setSelection(position);
				reminderget = reminderList[position];
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}


	public void onReminderClicked(View view){        
		if(dateFlag & timeFlag == true){
			notificationCount  = notificationCount+1;
			dateFlag = false;
			timeFlag = false;
			time = mYear+"-"+mMonth+"-"+mDay+" "+mHour+"-"+mMinute;                               
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm");  
			java.util.Date dt = null;
			try {
				try {
					dt = df.parse(time);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                                        
			long when = dt.getTime();                
			
			  
			AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
			Intent notificationIntent = new Intent(mContext, ReminderAlarm.class);
			notificationIntent.putExtra("Description",getnotes );
			notificationIntent.putExtra("NotifyCount",notificationCount );
			PendingIntent pi = PendingIntent.getBroadcast(mContext, notificationCount, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			mgr.set(AlarmManager.RTC_WAKEUP,when, pi);
			Toast.makeText(mContext, "Your Reminder Activated", Toast.LENGTH_LONG).show();
		
			contentText = "";
			 
		}
		else if(dateFlag == false | timeFlag == false){
			Toast.makeText(mContext, "Please choose Date & Time", Toast.LENGTH_SHORT).show();
		}
	}
	public void onTimeClicked(View view){    
		showDialog(TIME_DIALOG_ID);
	}
	public void onDateClicked(View view){    
		showDialog(DATE_DIALOG_ID);    
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this,
					mTimeSetListener, mHour, mMinute, false);
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					mDateSetListener,
					mYear, mMonth, mDay);
		}
		return super.onCreateDialog(id);
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener =
			new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear+1;
			mDay = dayOfMonth;   
			reminderdateset.setText(""+mDay+"/"+mMonth+"/"+mYear);
			dateFlag = true;
		}
	};

	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
			new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			remindertimeset.setText(""+mHour+":"+mMinute);
			timeFlag = true;
		}
	};
}
