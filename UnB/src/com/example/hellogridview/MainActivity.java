package com.example.hellogridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        View mapsButton = findViewById(R.id.maps_button);
        mapsButton.setOnClickListener(this);
        
        View eventsButton = findViewById(R.id.events_button);
        eventsButton.setOnClickListener(this);
        
        View emergencyButton = findViewById(R.id.emergency_button);
        emergencyButton.setOnClickListener(this);
    }
    
    public void openMaps(View view)
    {
    	Intent intent = new Intent(this, Maps.class);
    	startActivity(intent);
    }

	public void onClick(View v)
	{
		Intent i;
		
		switch (v.getId())
		{
		case R.id.maps_button:
			i = new Intent(this, Maps.class);
			startActivity(i);
			break;
		case R.id.events_button:
			i = new Intent(this, Events.class);
			startActivity(i);
			break;
		case R.id.emergency_button:
			i = new Intent(this, Emergency.class);
			startActivity(i);
			break;
		// More buttons go here (if any) ...
		}
	}
}