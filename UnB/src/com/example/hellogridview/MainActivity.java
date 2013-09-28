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
    }
    
    public void openMaps(View view)
    {
    	Intent intent = new Intent(this, Maps.class);
    	startActivity(intent);
    }

	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.maps_button:
			Intent i = new Intent(this, Maps.class);
			startActivity(i);
			break;
			// More buttons go here (if any) ...
		}
	}
}