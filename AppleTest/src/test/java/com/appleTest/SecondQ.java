package com.appleTest;

import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class SecondQ {
	
	//lets get the price only here
	@Test
	public void priceOnly() {
		//store return response in a string			
		String res = FirstQ.returnResponseString();
		
		//convert response string to json
		JsonPath jsRes=new JsonPath(res);
		
		//get car which has min price
		int minPrice = jsRes.get("car.perdayrent.price.min()");
		
		//print car details which has min price
		System.out.println(“Details of Car whose rent is lowest : ”+ jsRes.get(“car.findAll{it->    
   		it.perdayrent.price == “+minPrice+”}));
	}//priceOnly() function ended
	   	

	//Reusable method
	Public List discountPrice(JsonPath jsRes) {
		
		//get list of price and store it in listOfPrice
		List<float> listOfPrice= jsRes.get(car.perdayrent.Price);
	
		//get list of discount and store it in listOfDiscount
		List<float> listOfDiscount= jsRes.get(car.perdayrent.Discount);
	
		//create array list to store price with discount
		List<float> listOfPriceWithDiscount=new ArrayList();

		//find the min value after applying price-(price*discount)/100
		for(int i=0; i<listOfPrice.size();i++) {
			listOfPriceWithDiscount.add(listOfPrice.get(i)-         
					(listOfPrice.get(i)*listOfPriceWithDiscount.get(i))/100);
		}//for loop ended
		
	   return listOfPriceWithDiscount;
	}//discountPrice function ended
	
	
	//price after discount
	@Test
	public void priceAfterDiscount(){
		//store return response in a string			
		String res = returnResponseString();
		
		//call reusable method discountPrice and store it in a list
		//convert response string to json
		JsonPath jsRes=new JsonPath(res);
		List<float> listOfPriceWithDiscount = discountPrice(res);
		
		//index of min price with discount
		int minPriceIndex=listOfPriceWithDiscount.indexOf(Collections.min(listOfPriceWithDiscount));
		
		//print car details with minimum price with discount
		System.out.println (jsRes.get(Car[“minPriceIndex”]));
	}//priceAfterDiscount() function ended

}//SecondQ class ended
