package com.excilys.capicsoubank.activity;

import java.util.ArrayList;
import java.util.List;

import com.excilys.capicsoubank.action.DoTransfer;
import com.excilys.capicsoubank.dto.AccountDTO;
import com.excilys.capicsoubanktabletannotation.R;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

@EActivity(R.layout.activity_display_transfer)
@OptionsMenu(R.menu.menu)
public class DisplayTransferActivity extends AccountGetterActivity {

	@ViewById(R.id.sourceAccount)
	Spinner sourceAccount;

	@ViewById(R.id.destinationAccount)
	Spinner destinationAccount;

	@ViewById(R.id.amount)
	EditText amountEdit;

	@ViewById(R.id.transferError)
	View transferError;

	@ViewById(R.id.transferFinished)
	View transferFinished;

	@ViewById(R.id.transferForm)
	View transferForm;
	
	int sourceAccountSelected = 0;
	int destinationAccountSelected = 0;

	@AfterViews
	public void getAccounts() {
		taskGetAccounts.execute(username, password);
	}

	public void fillAccounts(AccountDTO[] accounts) {

		List<String> accountsList = new ArrayList<String>();

		for (AccountDTO account : accounts)
			accountsList.add(account.getAccountId() + " - "
					+ account.getLabel());

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, accountsList);

		sourceAccount.setAdapter(dataAdapter);
		destinationAccount.setAdapter(dataAdapter);

		transferForm.setVisibility(View.VISIBLE);
		
		sourceAccount.setSelection(sourceAccountSelected);
		destinationAccount.setSelection(destinationAccountSelected);
	}

	@Click
	public void buttonTransfer() {

		String sourceAccountId = ((TextView) sourceAccount.getSelectedView())
				.getText().toString().split(" - ")[0];
		String destinationAccountId = ((TextView) destinationAccount
				.getSelectedView()).getText().toString().split(" - ")[0];

		String amount = amountEdit.getText().toString();

		new DoTransfer(this).execute(username, password, sourceAccountId,
				destinationAccountId, amount);
	}

	public void finalizeTransfer(Boolean result) {
		if (!result) {
			transferError.setVisibility(View.VISIBLE);
			transferFinished.setVisibility(View.GONE);
		} else {
			transferFinished.setVisibility(View.VISIBLE);
			transferError.setVisibility(View.GONE);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);

		savedInstanceState.putInt("sourceAccount",
				sourceAccount.getSelectedItemPosition());
		savedInstanceState.putInt("destinationAccount",
				destinationAccount.getSelectedItemPosition());
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		sourceAccountSelected = savedInstanceState.getInt("sourceAccount");
		destinationAccountSelected = savedInstanceState
				.getInt("destinationAccount");
	}
}
