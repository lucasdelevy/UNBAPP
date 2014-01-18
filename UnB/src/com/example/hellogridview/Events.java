package com.example.hellogridview;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Events extends ListActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.events);
		
		// get list view
		// final ListView listview = (ListView) findViewById(R.id.events_listview);
		
		// add strings to the list view
		
		setTitle(R.string.events_activity);
		
	    String[] values = new String[] { "Event1", "Event2", "Event3" };
	    
	    //final ArrayList<String> list = new ArrayList<String>();
	    //for (int i = 0; i < values.length; ++i) {
	    //  list.add(values[i]);
	    //}
	    
	    setListAdapter(new ArrayAdapter<String>(this, R.layout.events, values));
	    //final StableArrayAdapter adapter = new StableArrayAdapter(this,
	    //		android.R.layout.events, list);
	    //listview.setAdapter(adapter);
	    
	    ListView listView = getListView();
		listView.setTextFilterEnabled(true);
	}
}
