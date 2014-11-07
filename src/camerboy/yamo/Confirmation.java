package camerboy.yamo;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

public class Confirmation extends Activity implements OnClickListener {
TextView namesent;
TextView locationsent;
TextView timesent;
TextView choicesent;
TextView datesent;
CheckBox privat;
TextView s;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmation);
		privat = (CheckBox)findViewById(R.id.checkBox2);
		privat.setOnClickListener(this);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B2E7FC")));
		
	namesent = (TextView)findViewById(R.id.textView1);
	locationsent = (TextView)findViewById(R.id.textView3);
	timesent = (TextView)findViewById(R.id.textView4);
	choicesent= (TextView)findViewById(R.id.textView5);
	datesent= (TextView)findViewById(R.id.textView11);
	
	Intent sender= getIntent();
	String sendname = sender.getStringExtra("name");
	if(sendname!=null && sendname!=""){namesent.setText(sendname);}
	
	String sendlocation = sender.getStringExtra("location");
	if(sendlocation!=null && sendlocation!=""){locationsent.setText(sendlocation);}
	
	String sendtime = sender.getStringExtra("time");
	if(sendtime!=null && sendtime!=""){timesent.setText(sendtime);}
	
	String sendchoice = sender.getStringExtra("choice");
	if(sendchoice!=null && sendchoice!=""){choicesent.setText(sendchoice);}
	
	String senddate = sender.getStringExtra("date");
	if(senddate!=null && senddate!=""){datesent.setText(senddate);}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirmation, menu);
		return true;
	}

	public void onClick(View v) {
	
	}
	
	


		
	

}
