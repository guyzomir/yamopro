package camerboy.yamo;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ProfilePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B2E7FC")));
	}

	public void eventOpen(View v){
		Intent eventss = new Intent(ProfilePage.this, MainActivity.class);
		startActivity(eventss);
		
	}

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
		
		MenuItem mnu2 = menu.add(0,1,1,"home");
		{
			mnu2.setIcon(R.drawable.home);
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
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
		
		return true;
		
		
		}
		return false;
	}
	
}
