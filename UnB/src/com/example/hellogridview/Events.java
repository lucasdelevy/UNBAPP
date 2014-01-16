package com.example.hellogridview;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Events extends ListActivity {
	/* Based on these examples:
	 * http://www.mkyong.com/android/android-listview-example/
	 * http://www.vogella.com/tutorials/AndroidListView/article.html
	 */
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// add strings to the list view
	    String[] values = new String[] { "Event1", "Event2", "Event3" };
	    
	    // using adapter defined in the XML file
	    setListAdapter(new ArrayAdapter<String>(this, R.layout.events,
	    		R.id.event_name, values));
	    
	    ListView listView = getListView();
		listView.setTextFilterEnabled(true);
	}
}
