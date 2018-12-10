package ie.gmit.sw.ds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.stream.Collectors;

import ie.gmit.sw.model.RentalOrder;

/*
 * This is a short example which demonstrates some of the functionality which a desktop client for the project would need to have.
 * 
 * If you want to develop a fully-featured desktop client for the project, you should check out the following:
 * Oracle tutorial on working with URLs (https://docs.oracle.com/javase/tutorial/networking/urls/index.html)
 * HttpURLConnection API docs (https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html)
 */

public class DesktopHTTPClient {

	public static void main(String[] args) {
		String resourceBaseURL = "http://localhost:8080/dsAssignment/webapi/bookings/read";
		String requestedOrder = "";
		URL url;		
		HttpURLConnection con;
		String resultInXml = "";
		String resultInJson = "";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to the car rental website. Pick an option.");
		System.out.println("Enter '1' to view all bookings.");
		System.out.println("Enter '2' to add new booking.");
		System.out.println("Enter '3' to update existing booking.");
		System.out.println("Enter '4' to delete a booking.");
		System.out.println("Enter '-1' to quit.");
		int choice = sc.nextInt();

			

					try {
						url = new URL(resourceBaseURL + requestedOrder);
						con = (HttpURLConnection) url.openConnection();
						con.setRequestMethod("GET");
						con.setRequestProperty("Accept", "application/xml");
						InputStream in = con.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						resultInXml = br.lines().collect(Collectors.joining());
						con.disconnect();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// try to create a connection and request JSON format
					try {
						url = new URL(resourceBaseURL + requestedOrder);
						con = (HttpURLConnection) url.openConnection();
						con.setRequestMethod("GET");
						con.setRequestProperty("Accept", "application/json");
						InputStream in = con.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						resultInJson = br.lines().collect(Collectors.joining());
						con.disconnect();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// print out the results of the requests
					System.out.println("****** Result in XML *****");
					System.out.println(resultInXml);
					System.out.println();
					
					System.out.println("****** Result in JSON *****");
					System.out.println(resultInJson);
					System.out.println();
					

					
			}
		



	

}
