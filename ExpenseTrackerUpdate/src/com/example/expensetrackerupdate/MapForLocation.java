package com.example.expensetrackerupdate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapForLocation extends FragmentActivity implements OnInfoWindowClickListener, OnClickListener,  OnMapClickListener {
	private GoogleMap googleMap;

	Location locationA;
	Double lati,longi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maplayout);
		if(lati == 0 && longi == 0){
			Toast.makeText(getApplicationContext(),"Select Your Posttion", Toast.LENGTH_SHORT).show();
		}
		else{
			Intent mapback = new Intent(MapForLocation.this,Residential.class);
			mapback.putExtra("LATI", lati);
			mapback.putExtra("LONGI", longi);
			startActivity(mapback);
			}
	if (googleMap == null) {
			googleMap 		   = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			//30.723436,76.847841 current location
//			googleMap.setMyLocationEnabled(true);

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),"Sorry! unable to create map", Toast.LENGTH_SHORT).show();
			}
//			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(30.7333148, 76.7794179), 14, 30, 0)));
//			    googleMap.addMarker(new MarkerOptions().position(new LatLng(30.7333148, 76.7794179)).title("location").icon(null));
			//    googleMap.addMarker(new MarkerOptions().position(new LatLng(30.7333148,76.7794179)).title("Hotel Aroma").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));



			googleMap.setOnMapLongClickListener(new OnMapLongClickListener() {

				@Override
				public void onMapLongClick(LatLng point) {
					// TODO Auto-generated method stub
					locationA = new Location("A");
lati = point.latitude;
longi = point.longitude;
					locationA.setLatitude(point.latitude);
					locationA.setLongitude(point.longitude);
					Toast.makeText(MapForLocation.this, "locationA is"+ lati  +"  &&  "+longi, 3000).show();
				}
			});
			
			
			// dis.setOnClickListener(new OnClickListener() {
			//	
			//	@Override
			//	public void onClick(View v) {
			//		// TODO Auto-generated method stub
			//		 distance = locationA.distanceTo(locationB);
			//		Log.d("distance", ""+distance);
			//	}
			//});
		


		}


		//icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow))
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
		Toast.makeText(MapForLocation.this, marker.getTitle(), Toast.LENGTH_LONG)
		.show();
	}



}
