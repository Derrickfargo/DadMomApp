package com.example.expensetrackerupdate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

public class ChildListActivity extends Activity{
	ListView childlist;
	Spinner spinnerSort;
	ImageView back,edit;
	TextView headerset;
	private String[] type = {"Select","Expense date","Amount"};
	public void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.expenselist);
		back = (ImageView)findViewById(R.id.logOut);
		back.setImageResource(R.drawable.arrow);
		edit = (ImageView)findViewById(R.id.editProfile);
		headerset = (TextView)findViewById(R.id.Text);
		spinnerSort = (Spinner)findViewById(R.id.SpinnerCategory);
		childlist = (ListView)findViewById(R.id.child_expenseList);
		
		
		childlist.setAdapter(new Customizelist());
		childlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long id) {
				// TODO Auto-generated method stub
				Intent intentexpnse = new Intent(ChildListActivity.this,ChildExpense_edit.class);
				startActivity(intentexpnse);
			}
		});
		headerset.setText("Expense List");
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,type);
		adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinnerSort.setAdapter(adapter_state);
		spinnerSort.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				spinnerSort.setSelection(position);
				
				if(position == 1){
//					list.setAdapter(new Customizelist());
				}
				if(position == 2){
//					list.setAdapter(new Customizelist());
				}
				
			}
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent childint = new Intent(ChildListActivity.this,ChildExpenseActivity.class);
				startActivity(childint);
			}
		});
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChildListActivity.this.finish();
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
		public View getView(int position, View v, ViewGroup parent) {
			// TODO Auto-generated method stub
			View vie;
			LayoutInflater inflater = getLayoutInflater();
			vie = inflater.inflate(R.layout.expense_list_item_row, parent,false);
			return vie;
		}
		
	}

}
