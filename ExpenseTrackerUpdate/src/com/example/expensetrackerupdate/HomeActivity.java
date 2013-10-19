package com.example.expensetrackerupdate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class HomeActivity extends Activity
{
	String[] data = {"a","2","3","4","5","6","7"};

	GridView grid_main;

	public void onBackPressed()
	{
		new AlertDialog.Builder(this).setTitle("Logout").setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface paramDialogInterface, int paramInt)
			{
				HomeActivity.this.finish();
			}
		}).setNegativeButton("No", null).show();
	}

	public void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.homeactivity);
		grid_main = (GridView)findViewById(R.id.grid_view);
		grid_main.setAdapter(new CustomizeGrid(data));

		grid_main.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) {
				// TODO Auto-generated method stub
				switch(position){
					case 1:
//						Intent intentSignup12 = new Intent(HomeActivity.this,MedicalNotes.class);
//				startActivity(intentSignup12);
				}
			}
		});
	}
	class CustomizeGrid extends BaseAdapter{
		String[] dataget;
		CustomizeGrid(){
			dataget = null;
		}
		public CustomizeGrid(String[] datapass){
			dataget = datapass;

		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dataget.length;
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
			v = inflater.inflate(R.layout.griddispaly, parent,false);
			//			ImageView gridimg = (ImageView)v.findViewById(R.id.icon_image);
			//			gridimg.setImageResource(dataget[position]);
			TextView gridtxt = (TextView)v.findViewById(R.id.icon_text);
			gridtxt.setText(dataget[position]);

			return v;
		}

	}
}