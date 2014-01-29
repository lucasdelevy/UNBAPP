/* Referência: https://www.youtube.com/watch?v=WRANgDgM2Zg*/

package org.unbapp;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Emergency extends Activity {
	String[] titles = new String[] {
    		"UnB Geral",
    		"Achados e Perdidos",
    		"Emergência Médica (de 7h às 19h)",
    		"Posto Policial PMDF",
    		"Segurança na UnB",
    		"Diretoria de Desenvolvimento Social (DDS)",
    		"Diretório Central de Estudantes (DCE)",
    		"Faculdade UnB Planaltina",
    		"Faculdade UnB Ceilândia",
    		"Faculdade UnB Gama" };
    
    String[] numbers = new String[] {
    		"3107-3300",
    		"3107-5855", 
			"3307-2110",
			"3107-5851 | 3107-5852",
			"3107-5851",
			"3107-0558 | 3107-0559",
			"3107-6302",
			"3107-8002 | 3107-8003 | 3107-8012",
			"3107-8416 | 3107-8417 | 3107-8418",
			"3107-8901 | 3107-8904" };
    
    final int NUM_OF_EMERGENCY_CONTACTS = titles.length;
    
	private List<Contact> myContacts = new ArrayList<Contact>();
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setTitle(R.string.emergency_activity);
	    setContentView(R.layout.emergency);
	    
	    populateContactList();		// Fill myContacts
	    populateListView();			// Fill the xml
	    registerClickCallback();	// Create listener
	}
	
	private void populateContactList() {
		for(int i = 0; i < NUM_OF_EMERGENCY_CONTACTS; i++)
		{
			myContacts.add(new Contact(titles[i], numbers[i]));
		}
	}
	
	private void populateListView() {
		ArrayAdapter<Contact> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.emergency_contacts_list);
		list.setAdapter(adapter);
	}
	
	private void registerClickCallback() {
		ListView list = (ListView) findViewById(R.id.emergency_contacts_list);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
				Contact clickedContact = myContacts.get(position);
				String[] currentNumbers = clickedContact.getNumbersArray();
				
				// In case there's only one number to call, dial it
				if(clickedContact.getNumOfNumbers() == 1) {
					Intent call = new Intent (Intent.ACTION_DIAL);
					call.setData(Uri.parse("tel:"+currentNumbers[0]));
		            startActivity(call);
				}
				// Otherwise, pop-up submenu with other numbers
				else {
					PopupMenu submenu = new PopupMenu(Emergency.this, viewClicked);

					Menu popup = submenu.getMenu();
					
					for (int i = 0; i < clickedContact.getNumOfNumbers(); i++)
						popup.add(0, i+1, i+1, currentNumbers[i]);
					
					submenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					     public boolean onMenuItemClick(MenuItem item) {
							Intent call = new Intent (Intent.ACTION_DIAL);
							call.setData(Uri.parse("tel:"+item.getTitle()));
				            startActivity(call);
					    	return true;
					     }
					});

					submenu.show();
				}
			}
		});
	}

	private class MyListAdapter extends ArrayAdapter<Contact> {
		public MyListAdapter() {
			super(Emergency.this, R.layout.item_view, myContacts);	
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (may have been given null)
			View itemView = convertView;
			if(itemView == null)
				itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
			
			// Find the Contact
			Contact currentContact = myContacts.get(position);
			
			// Fill the View
			TextView title = (TextView) itemView.findViewById(R.id.emergency_title_id);
			title.setText(currentContact.getTitle());

			TextView subtitle = (TextView) itemView.findViewById(R.id.emergency_subtitle_id);
			subtitle.setText(currentContact.getNumbers());
			
			return itemView;
		}	
	}
}