package camerboy.yamo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class EditProfileActivity extends Activity {
	ImageButton addpicture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		addpicture=(ImageButton)findViewById(R.id.startbutt);
	}

	public void photoadd(View v){
		Intent addpic=new Intent(EditProfileActivity.this,AddPicActivity.class);
		startActivity(addpic);
	}

}
