package camerboy.yamo;

import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SoccerActivity extends Activity  {
Button button;
ImageView join;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soccer);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B2E7FC")));
		
		join = (ImageView)findViewById(R.id.joinevent);
		
		
		
		
		
		
	}
	public void desc(View view){
		Intent intent = new Intent( this,DescriptionActivity.class );
		startActivity(intent);
		
	}
	
	public void google(View view){
		Intent myintent = new Intent( Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/uic+rec+center/@41.637244,-88.0994225,10z/data=!3m1!4b1") );
		startActivity(myintent);
		
	}
	
	public void finish(View view){
		finish();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.soccer, menu);
		return true;
	}
	
	

}
