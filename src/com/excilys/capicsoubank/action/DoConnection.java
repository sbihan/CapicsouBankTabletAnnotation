package com.excilys.capicsoubank.action;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.excilys.capicsoubanktabletannotation.R;
import com.excilys.capicsoubank.activity.MainActivity;
import com.excilys.capicsoubank.dto.MessageAuthentication;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class DoConnection extends AsyncTask<String, Integer, Boolean> {

	private static final String URL_CONNECTION = "http://192.168.10.128:8080/capicsou-bank-webservices-rest/login";

	private MainActivity activity;

	public DoConnection(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	protected Boolean doInBackground(String... params) {

		String username = params[0];
		String password = params[1];

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

		MessageAuthentication message = new MessageAuthentication();
		message.setUsername(username);
		message.setPassword(password);

		Boolean response = false;

		try {
			response = restTemplate.postForObject(URL_CONNECTION, message,
					Boolean.class);
		} catch (HttpClientErrorException e) {
			Log.e(getClass().getSimpleName(), e.getLocalizedMessage(), e);
		}

		return response;
	}

	@Override
	protected void onPreExecute() {
		activity.findViewById(R.id.connectionButton).setEnabled(false);
		activity.findViewById(R.id.connectionButton).setVisibility(View.GONE);
		activity.findViewById(R.id.progressBarConnection).setVisibility(
				View.VISIBLE);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		if(!result)
		{
			activity.findViewById(R.id.connectionButton).setEnabled(true);
			activity.findViewById(R.id.connectionButton).setVisibility(View.VISIBLE);
		}
		activity.findViewById(R.id.progressBarConnection).setVisibility(
				View.GONE);
		activity.connection(result);
	}
}
