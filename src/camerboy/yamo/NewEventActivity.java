package camerboy.yamo;


import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class NewEventActivity extends Activity implements View.OnClickListener,DialogInterface.OnClickListener {
	final static String API = "http://www.cs.lewisu.edu/~kerseycy/yamo/webservice.php";
	
	ImageView join;
	Spinner spinner;
	private static int LOAD_IMAGE_RESULTS=1;
	EditText name;
	EditText location;
	EditText time;
	EditText date;
	TextView t;
	
    ImageView picture;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_event);
		t=(TextView)findViewById(R.id.people);
		name = (EditText) findViewById(R.id.editText1);
		location = (EditText) findViewById(R.id.editText2);
		time = (EditText) findViewById(R.id.editText3);
		date=(EditText) findViewById(R.id.editText4);
		picture = (ImageView)findViewById(R.id.imageView3);
		join = (ImageView)findViewById(R.id.imageView2);
		join.setOnClickListener(this);
		
		spinner = (Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this, R.array.categoriess, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
public void add(View view){
	Intent i = new Intent(
			Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			 
			startActivityForResult(i, LOAD_IMAGE_RESULTS);
}


@Override
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
         
        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
     
    }
 
 

}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_event, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AlertDialog ad = new AlertDialog.Builder(this)
		.setMessage("ARE YOU SURE YOU WOULD LIKE TO CREATE THIS NEW EVENT?")
		.setIcon(R.drawable.ic_launcher)
		.setTitle("Terms of Service")
		.setPositiveButton("Yes", this)
		.setNegativeButton("No", this)
		.setNeutralButton("Cancel", this)
		.setCancelable(false)
		.create();
 
		ad.show();
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		switch(which){
		case DialogInterface.BUTTON_POSITIVE: // yes
			String sendname =
			name.getText().toString();
			
			String sendlocation =
			location.getText().toString();
			
			String sendtime=
			time.getText().toString();
			
			String sendchoice = 
					spinner.getSelectedItem().toString();
			String senddate=
					date.getText().toString();
			
			Intent confirm = new Intent(this, Confirmation.class);
			confirm.putExtra("name", sendname);
			confirm.putExtra("location", sendlocation);
			confirm.putExtra("time", sendtime);
			confirm.putExtra("choice", sendchoice);
			confirm.putExtra("date",senddate );
			
			startActivity(confirm);
			
				
			break;
		case DialogInterface.BUTTON_NEGATIVE: // no
			t.setText("create New Event");
			break;
		case DialogInterface.BUTTON_NEUTRAL: // neutral
			t.setText("Please select yes or no");
			break;
		default:
			// nothing
			break;
		
	}

}

}