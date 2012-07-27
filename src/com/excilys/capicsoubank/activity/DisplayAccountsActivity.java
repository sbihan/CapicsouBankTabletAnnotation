package com.excilys.capicsoubank.activity;

import java.text.NumberFormat;

import com.excilys.capicsoubanktabletannotation.R;
import com.excilys.capicsoubank.dto.AccountDTO;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.googlecode.androidannotations.annotations.ViewById;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@EActivity(R.layout.activity_display_accounts)
@OptionsMenu(R.menu.menu)
public class DisplayAccountsActivity extends AccountGetterActivity {

	@ViewById(R.id.tableAccounts)
	TableLayout table;
	
	@AfterViews
	public void getAccounts() {
		taskGetAccounts.execute(username, password);
	}

	public void fillAccounts(AccountDTO[] accounts) {

		for (AccountDTO account : accounts) {

			TableRow row = new TableRow(this);

			TextView id = new TextView(this);
			id.setText(account.getAccountId().toString());

			TextView label = new TextView(this);
			label.setText(account.getLabel());

			TextView balance = new TextView(this);
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			balance.setText(nf.format(account.getBalance() / 100.0));

			row.addView(id);
			row.addView(label);
			row.addView(balance);

			table.addView(row);
		}
	}
}
