package com.example.expensetrackerupdate;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ProductListActivity extends Activity{
	
	Spinner productspinner;
	ListView productlist;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list);
		productspinner = (Spinner)findViewById(R.id.SpinnerProduct);
		productlist = (ListView)findViewById(R.id.productList);
		productlist.setAdapter(new CustomizeList());
	}
	public class CustomizeList extends BaseAdapter{

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
		public View getView(int position, View arg1, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view;
			LayoutInflater inflater = getLayoutInflater();
			view = inflater.inflate(R.layout.product_item_list_row, parent,false);
			ImageView img;
			TextView frm,sbjct,descrptn;
			img = (ImageView)view.findViewById(R.id.icon);
			frm = (TextView)view.findViewById(R.id.From);
			sbjct = (TextView)view.findViewById(R.id.subject);
			descrptn = (TextView)view.findViewById(R.id.description);
			return view;
		}
		
	}
}
