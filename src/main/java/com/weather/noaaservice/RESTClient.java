package com.weather.noaaservice;

import java.text.MessageFormat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class RESTClient {
	private final static String REST_URI = "https://api.weather.gov/points/{0},{1}";

  private Client client = ClientBuilder.newClient();

  public String getJsonPoints(String lat, String lng) {
	  String endpoint = new MessageFormat(REST_URI).format(new Object[] { lat, lng});
      return client
        .target(endpoint)
        .request(MediaType.APPLICATION_JSON)
        .get(String.class);
  }
  
  public String getJsonForecast(String endpoint) {
	  return client
		        .target(endpoint)
		        .request(MediaType.APPLICATION_JSON)
		        .get(String.class);
  }
}
