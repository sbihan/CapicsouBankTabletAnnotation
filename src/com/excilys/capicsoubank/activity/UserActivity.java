package com.excilys.capicsoubank.activity;

import com.excilys.capicsoubanktabletannotation.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public abstract class UserActivity extends Activity {

	protected String username;
	protected String password;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		username = getIntent().getStringExtra("username");
		password = getIntent().getStringExtra("password");
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
	    switch (item.getItemId()) {
	    	case R.id.menu_accounts:
	            intent = new Intent(this, DisplayAccountsActivity_.class);
	            intent.putExtra("username", username);
				intent.putExtra("password", password);
	            startActivity(intent);
	            return true;
	        case R.id.menu_transfer:
	            intent = new Intent(this, DisplayTransferActivity_.class);
	            intent.putExtra("username", username);
				intent.putExtra("password", password);
	            startActivity(intent);
	            return true;
            case R.id.menu_deconnection:
	            intent = new Intent(this, MainActivity_.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
