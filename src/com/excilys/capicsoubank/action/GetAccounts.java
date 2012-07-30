package com.excilys.capicsoubank.action;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.excilys.capicsoubank.activity.AccountGetterActivity;
import com.excilys.capicsoubank.dto.AccountDTO;
import com.excilys.capicsoubanktabletannotation.R;

import android.os.AsyncTask;
import android.view.View;

public class GetAccounts extends AsyncTask<String, Integer, AccountDTO[]> {

	private static final String URL_ACCOUNTS = "http://192.168.11.12:8080/capicsou-bank-webservices-rest/user/accounts";

	private AccountGetterActivity activity;

	public GetAccounts(AccountGetterActivity activity) {
		this.activity = activity;
	}

	@Override
	protected AccountDTO[] doInBackground(String... params) {

		String username = params[0];
		String password = params[1];

		HttpAuthentication authHeader = new HttpBasicAuthentication(username,
				password);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAuthorization(authHeader);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

		ResponseEntity<AccountDTO[]> response = restTemplate
				.exchange(URL_ACCOUNTS, HttpMethod.GET, requestEntity,
						AccountDTO[].class);

		return response.getBody();
	}

	@Override
	protected void onPreExecute() {
		activity.findViewById(R.id.progressBarAccounts).setVisibility(
				View.VISIBLE);
	}

	@Override
	protected void onPostExecute(AccountDTO[] result) {
		activity.findViewById(R.id.progressBarAccounts).setVisibility(
				View.GONE);
		activity.fillAccounts(result);
	}
}
