package com.example.demo.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeApiDAO {

	public String getEmployeeApi(String url) throws IOException {
		URL urlRequest = new URL(url);
		HttpURLConnection getConnection = (HttpURLConnection) urlRequest.openConnection();
		getConnection.setRequestMethod("GET");
		int responseCode = getConnection.getResponseCode();

		String readLine = null;
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
			StringBuffer responseEmployeeApi = new StringBuffer();
			while ((readLine = in.readLine()) != null) {
				responseEmployeeApi.append(readLine);
			}
			in.close();
			return String.valueOf(responseEmployeeApi);
		} else {
			return "HTTP CONNECTION FAIL";
		}
	}

	public ResponseEntity<StringBuilder> addEmployeeApi(String json) throws IOException, ParseException {
		String url = "http://localhost:8088/api/v1/employees/add";
		URL urlRequest = new URL(url);
		HttpURLConnection postConnection = (HttpURLConnection) urlRequest.openConnection();
		postConnection.setRequestMethod("POST");
		postConnection.setRequestProperty("Content-Type", "application/json");

		postConnection.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(postConnection.getOutputStream())) {
			wr.writeBytes(json);
			wr.flush();
			wr.close();
		}

		int responseCode = postConnection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			StringBuilder response;
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			String inputLine;
			response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return ResponseEntity.ok().body(response);
		}
		return null;
	}

	public ResponseEntity<StringBuilder> deleteEmployeeApi(String id) throws IOException, ParseException {
		String url = String.format("http://localhost:8088/api/v1/employees/%s/delete", id);
		URL urlRequest = new URL(url);
		HttpURLConnection postConnection = (HttpURLConnection) urlRequest.openConnection();
		postConnection.setRequestMethod("DELETE");
		postConnection.setRequestProperty("Content-Type", "application/json");

		postConnection.setDoOutput(true);

		try (DataOutputStream wr = new DataOutputStream(postConnection.getOutputStream())) {
			wr.writeBytes(id);
			wr.flush();
			wr.close();
		}

		int responseCode = postConnection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			StringBuilder response;
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
			String inputLine;
			response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return ResponseEntity.ok().body(response);
		}
		return null;
	}

	public ResponseEntity<StringBuilder> editEmployeeApi(String id, String json) throws IOException {
		String charset = "UTF-8";
		String url = String.format("http://localhost:8088/api/v1/employees/%s/edit", URLEncoder.encode(id, charset));
		URL urlRequest = new URL(url);
		HttpURLConnection postConnection = (HttpURLConnection) urlRequest.openConnection();
		postConnection.setRequestMethod("PUT");
		postConnection.setRequestProperty("Content-Type", "application/json");

		postConnection.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(postConnection.getOutputStream())) {
			wr.writeBytes(json);
			wr.flush();
			wr.close();
		}

		int responseCode = postConnection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			StringBuilder response;
			BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream(), Charset.forName(charset)));
			String inputLine;
			response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return ResponseEntity.ok().body(response);
		}
		return null;
	}
}
