package org.unbapp;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Events extends ListActivity {
	
	public class Event {
		private String name;
		private EventType type;
		private String location;
		private String date;
		
		public Event(String name, EventType type, String location, String date) {
			this.name = name;
			this.type = type;
			this.location = location;
			this.date = date;
		}
		
		public String getName() {
			return name;
		}
		
		public EventType getType() {
			return type;
		}
		
		public String getLocation() {
			return location;
		}
		
		public String getDate() {
			return date;
		}
	}
	
	enum EventType {
		HAPPY_HOUR, PALESTRA, FORMATURA, TCC, ASSEMBLEIA
	}
	
	String[] events = new String[] {
			"Happy Hour da Florestal",
			"Formatura da Eng. Mecatr™nica",
			"Assembleia Geral do DCE"
	};
	
	EventType[] types = new EventType[] {
			EventType.HAPPY_HOUR,
			EventType.FORMATURA,
			EventType.ASSEMBLEIA
	};
	
	String[] locations = new String[] {
			"FT",
			"Centro Comunit‡rio",
			"Ceubinho"
	};
	
	String[] dates = new String[] {
			"29/02 - 18:00",
			"30/02 - 20:00",
			"31/02 - 12:00"
	};
	
	final int NUM_OF_EVENTS = events.length;
	
	private List<Event> myEvents = new ArrayList<Event>();
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setTitle(R.string.events_activity);
	    setContentView(R.layout.events);
	    
	    populateEventList();		// Fill myEvents
	    populateListView();			// Fill the XML
	    //registerClickCallback();	// Create listener
	}
	
	private void populateEventList() {
		for(int i = 0; i < NUM_OF_EVENTS; i++) {
			myEvents.add(new Event(events[i], types[i], locations[i], dates[i]));
		}
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
			
			if (currentEvent.getType() == EventType.FORMATURA) {
				View imageView = itemView.findViewById(R.id.event_image_id);
				ImageView image = (ImageView) imageView;
				Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.event_graduation);
				image.setImageBitmap(bm);
			}
			
			return itemView;
		}	
	}
}
