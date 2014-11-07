package camerboy.yamo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;






import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class MainActivity extends Activity {
	final static String API = "http://www.cs.lewisu.edu/~kerseycy/yamo/webservice.php";
	private List<Event> myCars = new ArrayList<Event>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B2E7FC")));
		populateCarList();
		populateListView();
		registerClickCallback();
		new DownloadSchedule().execute();
	}
	private void populateCarList() {
		myCars.add(new Event("Sport", 19, R.drawable.soccer, "Soccer pick up game"));
		myCars.add(new Event("Sport", 12 , R.drawable.stade, "Flag Football"));
		myCars.add(new Event("Art", 9, R.drawable.davinci, "Da Vinci Exposition"));
		myCars.add(new Event("Education", 2, R.drawable.french, "French Learning group"));
		myCars.add(new Event("Community Service", 28, R.drawable.help, "Morning Mission Chicago"));
		myCars.add(new Event("Sport", 10, R.drawable.guy, "Fresbie"));
		myCars.add(new Event("Social", 7, R.drawable.guy, "Working Moms Walk"));
		myCars.add(new Event("Social",5, R.drawable.mtn, "Say Hi to Paddy"));
		myCars.add(new Event("Jeep", 200, R.drawable.bulls, "Awesome"));
		myCars.add(new Event("Rust-Bucket", 2010, R.drawable.eto, "Not *very* good"));
		myCars.add(new Event("Moon Lander", 1971, R.drawable.guy, "Out of this world"));myCars.add(new Event("Porche", 2005, R.drawable.mtn, "Fast!"));
		myCars.add(new Event("Jeep", 200, R.drawable.guy, "Awesome"));
		myCars.add(new Event("Rust-Bucket", 2010, R.drawable.mtn, "Not *very* good"));
		myCars.add(new Event("Moon Lander", 1971, R.drawable.guy, "Out of this world"));
	}
	
	private void populateListView() {
		ArrayAdapter<Event> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.eventlistView);
		list.setAdapter(adapter);
	}
	
	private void registerClickCallback() {
		ListView list = (ListView) findViewById(R.id.eventlistView);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(MainActivity.this,SoccerActivity.class);
                startActivity(intent);
				
			}
		});
	}
	
	private class MyListAdapter extends ArrayAdapter<Event> {
		public MyListAdapter() {
			super(MainActivity.this, R.layout.item_view, myCars);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (may have been given null)
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
			}
			
			// Find the car to work with.
			Event currentCar = myCars.get(position);
			
			// Fill the view
			ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
			imageView.setImageResource(currentCar.getIconID());
			
			// Make:
			TextView makeText = (TextView) itemView.findViewById(R.id.item_txtMake);
			makeText.setText(currentCar.getMake());

			// Year:
			TextView yearText = (TextView) itemView.findViewById(R.id.item_txtYear);
			yearText.setText("" + currentCar.getYear());
			
			// Condition:
			TextView condionText = (TextView) itemView.findViewById(R.id.item_txtCondition);
			condionText.setText(currentCar.getCondition());

			return itemView;
		}				
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		 super.onCreateOptionsMenu(menu);
		 CreateMenu(menu);
		 return true;
	}
	private void CreateMenu(Menu menu) {
		MenuItem mnu1 = menu.add(0,0,0,"new Event");
		{
			mnu1.setIcon(R.drawable.plus);
			mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		MenuItem mnu2 = menu.add(0,1,1,"profile");
		{
			mnu2.setIcon(R.drawable.prosmall);
			mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
	}
	@Override
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		return MenuChoice(item);
	}
	private boolean MenuChoice(MenuItem item) {
		switch (item.getItemId()){
		case 0:
			startActivity(new Intent(getApplicationContext(), NewEventActivity.class));
		
		return true;
		
		case 1:
			startActivity(new Intent(getApplicationContext(), ProfilePage.class));
		
		return true;
		}
		return false;
	}
	
	private class DownloadSchedule extends 
    AsyncTask<Void, Void,ArrayList< HashMap<String,String> > >{
		private ProgressDialog dialog;
		
		@Override
		protected void onPostExecute(ArrayList<HashMap<String, String>> result) {
			ListAdapter adapter = new SimpleAdapter(
					MainActivity.this, result, R.layout.item_view, 
					new String[] {"schedule_row", "_id"}, 
					   new int[] {R.id.item_txtMake, R.id.item_txtCondition });
			MainActivity.this.setListAdapter(adapter);
			if (dialog.isShowing()) {
		          dialog.dismiss();
		   }
		}

		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(MainActivity.this, "", 
				   	"Downloading Games. Please wait...", true, false);
		}

		@Override
		protected ArrayList<HashMap<String, String>> doInBackground(Void... arg0) {
			//set post values
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		    nameValuePairs.add(new BasicNameValuePair("schedule_list",null));
			nameValuePairs.add(new BasicNameValuePair("start","2014-08-01"));
			nameValuePairs.add(new BasicNameValuePair("end","2014-09-30"));
			
			
			StringBuilder builder = new StringBuilder();
			
			 try {
					HttpClient client = new DefaultHttpClient();
					
					HttpPost httppost = new      
					        HttpPost(API);
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = client.execute(httppost);
				    HttpEntity entity = response.getEntity();
				    if (entity != null) {
		            // get entity contents and convert it to string
				    	InputStream content = entity.getContent();
				    	BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				    	String line;
    						while ((line = reader.readLine()) != null) {
    							builder.append(line);
    					} 
				    }else{
				    	Log.e("schedule", "Failed to download file");
				    }	

					} catch (Exception e) {
		    			Log.e("schedule", e.getMessage());
					}
			
			ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();        	
			HashMap<String, String> scheduleList =  new HashMap<String, String>(); 
			String event1;
			String event2;
			String id;
			String location;
			String people;
			String scheduleStr;

			try{
				JSONArray jArray = new JSONArray(builder.toString());	
				
				for(int i=0; i<jArray.length(); i++){
	    			JSONObject jsonObject = jArray.getJSONObject(i); 
	    			id = jsonObject.getString("_id"); 
	    			event1=jsonObject.getString("Test Event 1");    			
	    			event2 = jsonObject.getString("Cinquo de mayo");
	    			location =jsonObject.getString("Old Event");  
	    			people =jsonObject.getString("Another Test Event"); 
	    			scheduleStr = event1 + " vs. " + event2 + "  (" + location + "-" + people +")";
	    			
	    			
	    			scheduleList = new HashMap<String, String>();
	    			scheduleList.put("_id", id);
	    			scheduleList.put("schedule_row", scheduleStr);
	    			results.add(scheduleList);
				}
	    		return results;
			} catch (Exception e) {
    			Log.e("schedule", e.getMessage());
			}

			return null;
		}
		
	}



	public void setListAdapter(ListAdapter adapter) {
		// TODO Auto-generated method stub
		
	}
}
