package com.excilys.capicsoubank.activity;

import android.os.AsyncTask;

import com.excilys.capicsoubank.action.GetAccounts;
import com.excilys.capicsoubank.dto.AccountDTO;

public abstract class AccountGetterActivity extends UserActivity {

	AsyncTask<String, Integer, AccountDTO[]> taskGetAccounts = new GetAccounts(this);
	
	public abstract void fillAccounts(AccountDTO[] accounts);

	@Override
	protected void onDestroy() {
		super.onDestroy();
		taskGetAccounts.cancel(true);
	}
}
