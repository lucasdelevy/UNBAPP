package org.unbapp;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Events extends ListActivity {
	// Logging
	private static final String TAG = "Events"; 
	
	// Types of events
	private static final int EVENT_TYPE_HAPPY_HOUR = 0;
	private static final int EVENT_TYPE_PARTY = 1;
	private static final int EVENT_TYPE_GRADUATION = 2;
	private static final int EVENT_TYPE_GENERAL_MEETING = 3;
	
	public class Event {
		private String name;
		private int type;
		private String location;
		private String date;
		
		public Event(String name, int type, String location, String date) {
			this.name = name;
			this.type = type;
			this.location = location;
			this.date = date;
		}
		
		public String getName() {
			return name;
		}
		
		public int getType() {
			return type;
		}
		
		public String getLocation() {
			return location;
		}
		
		public String getDate() {
			return date;
		}
	}
	
	private List<Event> myEvents = new ArrayList<Event>();
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setTitle(R.string.events_activity);
	    setContentView(R.layout.events);
	    
	    populateEventsFromXml();	// Fill myEvents
	    populateListView();			// Fill the XML
	    //registerClickCallback();	// Create listener
	}
	
	private void populateEventsFromXml() {
		Resources res = getResources();
		String[] eventList = res.getStringArray(R.array.event_list);
		String[] eventDetails;
		int type;
		String delimiter = "\\|";
		
		for (String event : eventList) {
			eventDetails = event.split(delimiter);
			type = processEventType(eventDetails[1]);
			myEvents.add(new Event(eventDetails[0], type, eventDetails[2], eventDetails[3]));
		}
	}
	
	private int processEventType(String eventType) {
		if(eventType.equals("Happy Hour"))
			return EVENT_TYPE_HAPPY_HOUR;
		else if(eventType.equals("Party"))
			return EVENT_TYPE_PARTY;
		else if(eventType.equals("Graduation"))
			return EVENT_TYPE_GRADUATION;
		else if(eventType.equals("General Meeting"))
			return EVENT_TYPE_GENERAL_MEETING;
		
		Log.w(TAG, String.format("Unknown event type %s", eventType));
		return -1;
	}
		
	private void populateListView() {
		ArrayAdapter<Event> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.events_contacts_list);
		list.setAdapter(adapter);
	}
	
	private class MyListAdapter extends ArrayAdapter<Event> {
		public MyListAdapter() {
			super(Events.this, R.layout.events_item, myEvents);	
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (may have been given null)
			View itemView = convertView;
			if(itemView == null)
				itemView = getLayoutInflater().inflate(R.layout.events_item, parent, false);
			
			// Find the Event
			Event currentEvent = myEvents.get(position);
			
			// Fill the View
			TextView title = (TextView) itemView.findViewById(R.id.event_name_id);
			title.setText(currentEvent.getName());

			TextView date = (TextView) itemView.findViewById(R.id.event_date_id);
			date.setText(currentEvent.getDate());
			
			TextView location = (TextView) itemView.findViewById(R.id.event_location_id);
			location.setText(currentEvent.getLocation());
			
			View imageView = itemView.findViewById(R.id.event_image_id);
			ImageView image = (ImageView) imageView;
			int drawable = getEventTypeDrawable(currentEvent.getType());
			Bitmap bm = BitmapFactory.decodeResource(getResources(), drawable);
			image.setImageBitmap(bm);
			
			return itemView;
		}
		
		private int getEventTypeDrawable(int type) {
			switch (type) {
			case EVENT_TYPE_HAPPY_HOUR:
				return R.drawable.ic_events_hh_new;
			case EVENT_TYPE_PARTY:
				return R.drawable.ic_events_festa_new;
			default:
				Log.w(TAG, String.format("No icon defined for event type %d", type));
				return R.drawable.ic_events_new;
			}
		}
	}
}
