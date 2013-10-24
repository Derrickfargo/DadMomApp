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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Medical_ListActivity extends Activity{
	ListView medicallist;
	ImageView back,edit;
	TextView headerset;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medicalhistory_list);
		back = (ImageView)findViewById(R.id.logOut);
		edit = (ImageView)findViewById(R.id.editProfile);
		headerset = (TextView)findViewById(R.id.Text);
		
		medicallist = (ListView)findViewById(R.id.medicalList);
		medicallist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent editintent = new Intent(Medical_ListActivity.this,MedicalHistory_Edit.class);
				startActivity(editintent);
			}
		});
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Medical_ListActivity.this.finish();
			}
		});
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent medicalintent = new Intent(Medical_ListActivity.this,MedicalHistory.class);
				startActivity(medicalintent);
			}
		});
		medicallist.setAdapter(new CustomizeMedical());
}
	public class CustomizeMedical extends BaseAdapter{

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
		public View getView(int arg0, View v, ViewGroup parent) {
			// TODO Auto-generated method stub
			View vie;
			LayoutInflater inflater = getLayoutInflater();
		    vie = inflater.inflate(R.layout.medical_item_list_row, parent,false);
		    ImageView img;
		    TextView frm,subj,descsptn;
		    img = (ImageView)vie.findViewById(R.id.icon);
		    frm = (TextView)vie.findViewById(R.id.From);
		    subj = (TextView)vie.findViewById(R.id.subject);
		    descsptn = (TextView)vie.findViewById(R.id.description);
			return vie;
		}
		
	}
}
