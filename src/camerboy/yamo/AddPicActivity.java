package camerboy.yamo;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddPicActivity extends Activity {
ImageButton ajoutpicc;
private static int LOAD_IMAGE_RESULTS=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_pic);
		ajoutpicc = (ImageButton)findViewById(R.id.addphoto1);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B2E7FC")));
	}
public void ajoutClick(View v){
	Intent i = new Intent(
			Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			 
			startActivityForResult(i, LOAD_IMAGE_RESULTS);	
	
}

public void next(View v){
	Intent choices =  new Intent (AddPicActivity.this,ChoicesActivity.class );
	startActivity(choices);
}



protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
     
    if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && null != data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
         
        ImageView imageView = (ImageView) findViewById(R.id.logotext);
        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
     
    }
}
}
