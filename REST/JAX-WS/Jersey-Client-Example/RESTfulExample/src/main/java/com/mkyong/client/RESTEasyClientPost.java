package com.mkyong.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;

public class RESTEasyClientPost {

	public static void main(String[] args) {/*

		try {

			ClientRequest request = new ClientRequest(
					"http://localhost:8080/RestJerseyJsonClient/rest/json/metallica/post");
			request.accept("application/json");

			String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

			request.body("application/json", input);

			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity()
					.getBytes())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

	*/}

}
