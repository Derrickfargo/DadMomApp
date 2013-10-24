package com.example.expensetrackerupdate;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends Activity{

	String[] Details = { "Child Residential", "Child Expense", "Child Medical History", "Blogs", "Reports", "Calendar", "Twilo", "Products" };

	Integer[] ChildImages = {R.drawable.home_child_residential,R.drawable.home_child_expens,R.drawable.home_child_medical,R.drawable.home_blogs
			,R.drawable.home_reports,R.drawable.home_calander,R.drawable.home_twilo,R.drawable.home_product};
	GridView grid_main;
ImageView edit,logout;
TextView headerset;
String PREFS_NAME = "Expense_Tracker";
SharedPreferences AppID;
ArrayList<String>aRRAYlIST;

	public void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.homeactivity);
		grid_main = (GridView)findViewById(R.id.grid_view);
		headerset = (TextView)findViewById(R.id.Text);
		edit = (ImageView)findViewById(R.id.editProfile);
		logout = (ImageView)findViewById(R.id.logOut);
		headerset.setText("Main Menu");
		
		aRRAYlIST = new ArrayList<String>();
		 Intent intenget = getIntent();
		 aRRAYlIST = intenget.getStringArrayListExtra("ARRAYLIST");
		 Log.v("aRRAYlIST","  home......."+aRRAYlIST);
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calllogout();
			}
		});
		grid_main.setAdapter(new CustomizeGrid(Details,ChildImages));

		grid_main.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) {
				// TODO Auto-generated method stub
				switch(position){
				case 0:
					Intent resi = new Intent(HomeActivity.this,SimpleListActivity.class);
					startActivity(resi);
					break;
				case 1:
				Intent expsns = new Intent(HomeActivity.this,ChildListActivity.class);
				startActivity(expsns);
				break;
				case 2:
				Intent medical = new Intent(HomeActivity.this,Medical_ListActivity.class);
				startActivity(medical);
				break;
				case 3:
				Intent blog = new Intent(HomeActivity.this,BlogActivity.class);
				startActivity(blog);
				break;
				case 4:
				Intent reprt = new Intent(HomeActivity.this,ReportActivity.class);
				startActivity(reprt);
				break;
				case 5:
				Intent calendar = new Intent(HomeActivity.this,ReminderActivity.class);
				startActivity(calendar);
				break;
				case 6:
				Intent twilo = new Intent(HomeActivity.this,TwiloActivity.class);
				startActivity(twilo);
				break;
				case 7:
				Intent product = new Intent(HomeActivity.this,ProductListActivity.class);
				startActivity(product);
				break;
				}
			}
		});
		
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stu
				profileUpdate();
			}
		});
	}
	class CustomizeGrid extends BaseAdapter{
		String[] childDetails;
		Integer[] childPictures;
		CustomizeGrid(){
			childDetails = null;
			childPictures = null;
		}
		public CustomizeGrid(String[] detail,Integer[] images){
			childDetails = detail;
			childPictures = images;

		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return childDetails.length;
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
		public View getView(int position, View vi, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v = null;
			LayoutInflater inflater = getLayoutInflater();
			v = inflater.inflate(R.layout.homeactivity_griddispaly, parent,false);
			ImageView gridimg = (ImageView)v.findViewById(R.id.icon_image);
			gridimg.setImageResource(childPictures[position]);
			TextView gridtxt = (TextView)v.findViewById(R.id.icon_text);
			gridtxt.setText(childDetails[position]);

			return v;
		}

	}
	
	public void calllogout(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Logout");
		alert.setMessage("Are yo sure");
		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				AppID = getApplication().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
				SharedPreferences.Editor prefsEditor = AppID.edit();
				prefsEditor.putString("UserName", "");
				prefsEditor.putString("Password", "");
				prefsEditor.commit();
				HomeActivity.this.finish();
				
			}
		});
		alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});alert.show();
	}
	public void profileUpdate(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Update profile");
		
		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent editprfl = new Intent(HomeActivity.this,UpdateProfile.class);
				editprfl.putExtra("arrayEdit", aRRAYlIST);
				startActivity(editprfl);
				
			}
		});
		alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});alert.show();
	}
}