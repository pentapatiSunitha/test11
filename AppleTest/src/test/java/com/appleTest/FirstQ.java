package com.appleTest;

import java.io.IOException;
import org.testng.annotations.Test;
import io.restassured.*;
import io.restassured.path.json.JsonPath;

public class FirstQ {

	// first we need to get the response as string
	// then send this response as return output.
	public static String returnResponseString() {
		String res = RestAssured.given().relaxedHTTPSValidation().when().get("https://Cars.com/cars").then().extract()
				.response().asString();

		return res;
	}//returnResponseString() function ended

	// Print all the blue Teslas received in the web service response. Also print
	// the notes

	@Test
	public void printBlueTesla() throws IOException {
		//here we store return response in a string			
		String res = returnResponseString();
		
		//first create a jsonPath object from the response
		JsonPath jsRes=new JsonPath(res);
		
		//get count of cars from the response
		int count = jsRes.get(Car.size());
		
		//traverse through the loop and check model = tesla and color = blue and print model and notes
		for(int i=0; i< count;i++){
			If((jsRes.get(“Car[“+i+”].make).equals(“Tesla”))&&(jsRes.get(“Car[“+i+”].metadata.Color).equals(“Blue”))) 
			{
				System.out.println(“Car Model: ”+jsRes.get(“Car[“+i+”].make));
				System.out.println(“Car Color: ”+jsRes.get(“Car[“+i+”].metadata.Notes));
			}//if statement ended
		}//for statement ended
	}//printBlueTesla() function ended
	
}//FirstQ class ended

