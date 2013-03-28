package com.example.locus;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.locus.core.CoreFacade;
import com.example.locus.core.ICore;
import com.example.locus.core.IObserver;
import com.example.locus.entity.User;

public class Demo extends Activity implements IObserver{

	 double latitude = 0;
	 double longitude = 0;
	 String username;
	 private ListView listView;
	 private TextView latituteField;
	 private TextView longitudeField;
	 ICore core;
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_users);
		Intent intent = getIntent();
		
		//create Icore instance
		core = CoreFacade.getInstance();
		//core.addObserver(this);
		username = intent.getStringExtra("userName");
		latitude = Double.parseDouble(intent.getStringExtra("latitude"));
		longitude = Double.parseDouble(intent.getStringExtra("longitude"));
		latituteField = (TextView) findViewById(R.id.textView1);
	    longitudeField = (TextView) findViewById(R.id.textView2);
	    latituteField.setText(String.valueOf(latitude));
	    longitudeField.setText(String.valueOf(longitude));
	    //----------------------------- FOR LIST VIEW ---------------------------------------------------------
	    List<User> data = core.getUsersNearby();
	    /*ListDetails data[] = new ListDetails[]{
	    		
	    		new ListDetails(R.drawable.a, "Car1"),
	    		new ListDetails(R.drawable.b, "Car2"),
	    		new ListDetails(R.drawable.c, "Car3"),
	    		new ListDetails(R.drawable.d, "Car4"),
	    		new ListDetails(R.drawable.d, "Car5"),
	    		new ListDetails(R.drawable.d, "Car6"),
	    		new ListDetails(R.drawable.d, "Car7"),
	    		new ListDetails(R.drawable.d, "Car8"),
	    		new ListDetails(R.drawable.a, "Car9"),
	    		new ListDetails(R.drawable.b, "Car10"),
	    		new ListDetails(R.drawable.c, "Car11"),
	    		new ListDetails(R.drawable.d, "Car12"),
	    		new ListDetails(R.drawable.e, "Car13"),
	    		new ListDetails(R.drawable.a, "Car14"),
	    		new ListDetails(R.drawable.b, "Car15"),
	    		new ListDetails(R.drawable.c, "Car15"),
	    		
	    		new ListDetails(R.drawable.e, "Car5")
	    };*/
	    AdapterList adapter = new AdapterList (this, R.layout.activity_list_adapter, data);
	    
	    listView = (ListView)findViewById(R.id.listView);
	    listView.setAdapter(adapter);
	    
	    /*listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
                                           int position, long id) {
				// user clicked a list item, make it "selected"
				
				Toast.makeText(getApplicationContext(),listView.getItemAtPosition(position).toString()+" selected", Toast.LENGTH_SHORT).show();
			}
        });*/
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	        @Override
	        public void onItemClick(AdapterView<?> adapter, View view, int position,
	                long id) {
	            // TODO Auto-generated method stub
	            User o = (User)adapter.getItemAtPosition(position);
	            String str_text = o.getName();
	            Toast.makeText(getApplicationContext(),str_text+" SelecteD ", Toast.LENGTH_SHORT).show();
	        }

	    });  
	 }
	 
	 //------------------------------------------------------------------------------------------------------------------------
	 /* Request updates at startup */
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_users, menu);
		return true;
	}

	@Override
	public void onReceiveMessage(User src, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReceiveUserProfile(User user) {
		// TODO Auto-generated method stub
		
	}

}
