package com.example.expensetrackerupdate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements
OnInfoWindowClickListener, OnClickListener,  OnMapClickListener {
	private GoogleMap googleMap;
	Button dis;
	String result;
	Location locationA,locationB;
	String distances;
	Double plata,plonga,platb,plongb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maplayout);
		Toast.makeText(MapActivity.this, "Tap Marker To Lock Your Location ", 2000).show();
		if (googleMap == null) {
			googleMap 		   = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			//30.723436,76.847841 current location
			googleMap.setMyLocationEnabled(true);

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),"Sorry! unable to create map", Toast.LENGTH_SHORT).show();
			}
//			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(30.7500, 76.7800), 10, 30, 0)));
			
			googleMap.setOnMapLongClickListener(new OnMapLongClickListener() {

				@Override
				public void onMapLongClick(LatLng point) {
					// TODO Auto-generated method stub
					locationA = new Location("A");
					plata=point.latitude;
					plonga=point.longitude;
					AlertDialog.Builder alert = new AlertDialog.Builder(MapActivity.this);
					alert.setTitle("Do you want to set this position as your current position");
					alert.setMessage("lat="+plata+"   long="+plonga);
					alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intentbck = new Intent(MapActivity.this,ChildExpenseActivity.class);
							intentbck.putExtra("LAT", plata);
							intentbck.putExtra("LON", plonga);
							startActivity(intentbck);
						}
					
					}); 
					alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							MapActivity.this.finish();
						}
					});
					alert.show();
						
				}
			});
				
				
		

		
		}


	}

	@Override
	public void onMapClick(LatLng point) {
		// TODO Auto-generated method stub
		googleMap.animateCamera(CameraUpdateFactory.newLatLng(point));
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onInfoWindowClick(Marker marker) {
		// TODO Auto-generated method stub
		Toast.makeText(MapActivity.this, marker.getTitle(), Toast.LENGTH_LONG)
		.show();
	}



}
