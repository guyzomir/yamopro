package camerboy.yamo;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignUpActivity extends Activity {
	ImageButton createAcc;
	EditText mailsubmit;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		createAcc = (ImageButton)findViewById(R.id.create);
		mailsubmit=(EditText)findViewById(R.id.emailaddress);
	}

	public void alertSubmit(View v){AlertDialog.Builder submit = new AlertDialog.Builder(this);
	String mail= mailsubmit.getText().toString();
	
	submit.setMessage("An email confirmation has been sent to"+"\n"+mail );

	submit.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
	Intent startB=new Intent (SignUpActivity.this,EditProfileActivity.class);
	startActivity(startB);
	}
	});

			
	submit.show();

}
}
