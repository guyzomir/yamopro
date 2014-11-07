package camerboy.yamo;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class YamoFirstActivity extends Activity {
ImageButton Signup1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yamo_first);
		Signup1=(ImageButton)findViewById(R.id.signupbutton);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B2E7FC")));
	}

	public void signing (View v){ 
		Intent signup = new Intent (YamoFirstActivity.this,SignUpActivity.class);
		startActivity(signup);
		
		
	}
	public void login (View v){ 
		Intent login1 = new Intent (YamoFirstActivity.this,MainActivity.class);
		startActivity(login1);
	
	
}

}