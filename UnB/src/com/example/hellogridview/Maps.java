package com.example.hellogridview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class Maps extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(null);
		
		// Create image view with zoom
		ImageViewZoom touch = new ImageViewZoom(this);
		
		// Set image
		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.unb_map);
		touch.setImageBitmap(bm);
		touch.setMaxZoom(4f); //change the max level of zoom, default is 3f
		
		// Set content (not using XML file)
		setContentView(touch);
	}
}
