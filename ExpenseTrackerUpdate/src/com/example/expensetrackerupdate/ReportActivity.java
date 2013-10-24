package com.example.expensetrackerupdate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ReportActivity extends Activity{
	ListView reportlist;
	String[] reports = {"Residential Report","Expense Report"};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_list_activity);
		reportlist = (ListView)findViewById(R.id.reportList);
		reportlist.setAdapter(new Customizereport(reports));
		
		reportlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				if(position == 0){
					Intent graphintent = new Intent(ReportActivity.this,ResidentialGraphActivity.class);
					startActivity(graphintent);
				}
				if(position == 1){
					Intent graphintent = new Intent(ReportActivity.this,ExpenseGraphActivity.class);
					startActivity(graphintent);
				}
			}
		});
	}
public class Customizereport extends BaseAdapter{
	String[] allreports;
	Customizereport(){
		allreports = null;
	}
	 public Customizereport(String[] reportsget){
		 allreports = reportsget;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return allreports.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi;
		LayoutInflater inflater = getLayoutInflater();
		vi = inflater.inflate(R.layout.report_item_row, parent,false);
		TextView reporttext;
		reporttext = (TextView)vi.findViewById(R.id.reportTextView);
		reporttext.setText(allreports[position]);
		
		
		
		return vi;
	}
	
}
}
