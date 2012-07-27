package com.excilys.capicsoubank.activity;

import com.excilys.capicsoubanktabletannotation.R;
import com.excilys.capicsoubank.action.DoConnection;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	private String username;
	private String password;
	
	@ViewById(R.id.username)
	EditText usernameInput;
	
	@ViewById(R.id.password)
	EditText passwordInput;
    
	@Click
    public void connectionButton() {
    	username = usernameInput.getText().toString();
    	password = passwordInput.getText().toString();

    	new DoConnection(this).execute(username, password);
    }
    
    public void connection(boolean isConnected) {
    	if(isConnected)
		{
			findViewById(R.id.connectionError).setVisibility(View.GONE);
			Intent intent = new Intent(this, DisplayAccountsActivity_.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			intent.putExtra("username", username);
			intent.putExtra("password", password);
			startActivity(intent);
		}
		else
			findViewById(R.id.connectionError).setVisibility(View.VISIBLE);
    }
}

