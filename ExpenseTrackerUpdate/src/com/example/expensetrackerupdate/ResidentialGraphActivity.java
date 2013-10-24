package com.example.expensetrackerupdate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ResidentialGraphActivity extends Activity{
Spinner reportType,selectYear,selectMonth;
ImageView back,edit;
TextView headerset;
	public void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		setContentView(R.layout.residential_graph);
		reportType = (Spinner)findViewById(R.id.report_Type);
		selectYear = (Spinner)findViewById(R.id.select_Year);
		selectMonth = (Spinner)findViewById(R.id.select_Month);
		back = (ImageView)findViewById(R.id.logOut);
		edit = (ImageView)findViewById(R.id.editProfile);
		headerset = (TextView)findViewById(R.id.Text);
		edit.setVisibility(View.INVISIBLE);
		headerset.setText("Report List");
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ResidentialGraphActivity.this.finish();
			}
		});
	}

}
