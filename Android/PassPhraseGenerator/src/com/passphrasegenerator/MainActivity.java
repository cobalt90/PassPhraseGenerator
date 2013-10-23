/*
 * NOTES:
 * input for service could have a drop down menu with a list of services with logos for common services like
 * Gmail, Hotmail, FaceBook etc etc etc.
 */

package com.passphrasegenerator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onBtnClicked(View v){
	    if(v.getId() == R.id.but_genPass){
	    	
	    	EditText inputPass = (EditText) findViewById(R.id.passField);
	    	EditText inputServ = (EditText) findViewById(R.id.serviceField);
	    	
	    	if (inputPass.getText().equals("") || inputServ.getText().equals(""))
	    		Toast.makeText(this, "You have not entered a Master password or service", Toast.LENGTH_SHORT).show();
	    	else
	    	{
	    		TextView genPassPhrase = (TextView) findViewById(R.id.generatedPass);
	    		genPassPhrase.setText(PassEncryptor.GenPass(PassEncryptor.TokenizeHash(PassEncryptor.GetHash(inputPass.getText() + "" + inputServ.getText()), 8)));
	    		inputPass.setText("");
	    		genPassPhrase.setVisibility(View.VISIBLE);
	    	}
	    }
	}
}
