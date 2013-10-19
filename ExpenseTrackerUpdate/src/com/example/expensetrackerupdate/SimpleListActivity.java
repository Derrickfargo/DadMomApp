package com.example.expensetrackerupdate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class SimpleListActivity extends Activity{
	ListView list;
	Spinner spinnerSort;
	
	private String[] Datetype = {"Pick up date","Captured date"};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplelistactivity);
		list = (ListView)findViewById(R.id.MessageList);
		spinnerSort = (Spinner)findViewById(R.id.SpinnerCategory);
		
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Datetype);
		adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinnerSort.setAdapter(adapter_state);
		spinnerSort.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				spinnerSort.setSelection(position);
				
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		list.setAdapter(new Customizelist());
		
		
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
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
}
