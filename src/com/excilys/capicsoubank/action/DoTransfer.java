package com.excilys.capicsoubank.action;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.excilys.capicsoubank.activity.DisplayTransferActivity;
import com.excilys.capicsoubank.dto.Transfer;
import com.excilys.capicsoubanktabletannotation.R;

import android.os.AsyncTask;
import android.view.View;

public class DoTransfer extends AsyncTask<String, Integer, Boolean> {

	private static final String URL_TRANSFER = "http://192.168.11.12:8080/capicsou-bank-webservices-rest/transfer";

	private DisplayTransferActivity activity;

	public DoTransfer(DisplayTransferActivity activity) {
		this.activity = activity;
	}

	@Override
	protected Boolean doInBackground(String... params) {

		Integer sourceAccountId = Integer.valueOf(params[2]);
		Integer destinationAccountId = Integer.valueOf(params[3]);

		double amountTmp = Double.parseDouble(params[4]);
		long amount = (long) ((amountTmp / 100) * 100 * 100);

		Transfer transfer = new Transfer.Builder()
				.accountDestinationId(destinationAccountId)
				.accountSourceId(sourceAccountId).amount(amount).build();

		String username = params[0];
		String password = params[1];

		HttpAuthentication authHeader = new HttpBasicAuthentication(username,
				password);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAuthorization(authHeader);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(transfer,
				requestHeaders);

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

		try {
			restTemplate.exchange(URL_TRANSFER, HttpMethod.POST, requestEntity,
					null);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	protected void onPreExecute() {
		activity.findViewById(R.id.transferError).setVisibility(View.GONE);
		activity.findViewById(R.id.transferFinished).setVisibility(View.GONE);
		activity.findViewById(R.id.buttonTransfer).setEnabled(false);
		activity.findViewById(R.id.buttonTransfer).setVisibility(View.GONE);
		activity.findViewById(R.id.progressBarTransfer).setVisibility(
				View.VISIBLE);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		activity.findViewById(R.id.buttonTransfer).setEnabled(true);
		activity.findViewById(R.id.buttonTransfer).setVisibility(View.VISIBLE);
		activity.findViewById(R.id.progressBarTransfer)
				.setVisibility(View.GONE);
		activity.finalizeTransfer(result);
	}
}
