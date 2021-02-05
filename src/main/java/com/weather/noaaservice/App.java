package com.weather.noaaservice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
	private static RESTClient client = new RESTClient();

    public static void main( String[] args ) throws JsonProcessingException, IOException
    {
    	try {
    		System.out.println("Latitude\tLongitude\tPLACE");
    		System.out.println("40.745255\t-74.034775\tHoboken, NJ");
    		System.out.println("34.155834\t-119.20278\tPort Hueneme, CA");
    		System.out.println("42.933334\t-76.566666\tAuburn, NY");
    		System.out.println("42.095554\t-79.238609\tJamestown, NY");
    		System.out.println("38.846668\t-91.948059\tFulton, MO");
    		System.out.println("41.392502\t-81.534447\tBedford, OH");
    		System.out.println("27.192223\t-80.243057\tStuart, FL");
    		System.out.println("31.442778\t-100.45027\tSan Angelo, TX");
    		System.out.println("40.560001\t-74.290001\tWoodbridge, NJ");
    		System.out.println("33.193611\t-117.24111\tVista, CA");
    		System.out.println("41.676388\t-86.250275\tSouth Bend, IN");
    		System.out.println("41.543056\t-90.590836\tDavenport, IA");
    		System.out.println("");
        	System.out.println("________________________________________________________________");
        	
    		Scanner sc= new Scanner(System.in);
        	System.out.print("Enter a Latitude: ");
        	String lat = sc.nextLine();
        	System.out.print("Enter a Longitude: ");
        	String lng = sc.nextLine();
        	System.out.println("");
        	System.out.println("________________________________________________________________");
        	
        	ObjectMapper mapper = new ObjectMapper();
        	JsonNode node = mapper.readTree(client.getJsonPoints(lat,lng));
        	String forecastURI = node.path("properties").path("forecast").asText();
        	
        	if (forecastURI != null) {
        		node = mapper.readTree(client.getJsonForecast(forecastURI));
            	Properties props = mapper.readValue(node.path("properties").toString(), Properties.class);
            	LocalDate days5 = LocalDate.now().plusDays(5);
            	Date enddate = java.sql.Date.valueOf(days5);
            	for(Period period: props.periods) {        		
            		if(period.startTime.before(enddate)) {
        	    		System.out.println(period.startTime.toString() + " TO " + period.endTime.toString());
        	    		System.out.println("Temperature: " + period.temperature + period.temperatureUnit);
        	    		System.out.println("Forecast: " + period.detailedForecast);
        	    		System.out.println("________________________________________________________________");
        	    		System.out.println();
            		}
            	}
        		
        	}    		
    	} catch (Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	    	
    }
}
