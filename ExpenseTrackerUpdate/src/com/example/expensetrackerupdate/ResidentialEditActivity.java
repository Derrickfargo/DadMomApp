package com.example.expensetrackerupdate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ResidentialEditActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.residential_edit);
	}

}
