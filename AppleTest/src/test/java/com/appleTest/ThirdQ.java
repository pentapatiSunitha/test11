package com.appleTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

public class ThirdQ {

	// Extract response as string and return response
	public String returnResponseString() {
		String res = given().relaxedHTTPSValidation().when().get("https://Cars.com/cars").then().extract().response()
				.asString();
		return res;
	}//returnResponseString() function ended

	// Calculate highest revenue car
	@Test
	public void highestRevenueCar() {
		//store return response in a string			
		String res = returnResponseString();
		
		//call reusable method discountPrice and store it in a list
		List<Float> listOfPriceWithDiscount = discountPrice(res);
		
		//convert response string to json
		JsonPath jsRes=new JsonPath(res);
		
		//get list of price and store it in listOfPrice
		List<Float> listOfPrice= jsRes.get(car.perdayrent.Price);
		
		//get list of discount and store it in listOfDiscount
		List<Float> listOfDiscount= jsRes.get(car.perdayrent.Discount);
		List<Float> listOfPriceWithDiscount1 = new ArrayList();
		
		//get list of rental count year to date
		List<Integer> listOfRentalCount= jsRes.get(car. metrics.rentalcount.yeartodate);
		List<Float> listOfRevenue=new ArrayList();

		//add values to listOfPriceWithDiscount after applying price-(price*discount)/100

		for(int i=0; i<listOfPrice.size();i++){
			listOfPriceWithDiscount1.add(listOfPrice.get(i)-         
					(listOfPrice.get(i)*listOfPriceWithDiscount1.get(i))/100)
		}//for loop ended
		
		//add values to listOfRevenue after applying pricewithdiscount *rentalcount
		for(int i=0; i<listOfPrice.size();i++){
			listOfRevenue .add(listOfPriceWithDiscount1.get(i)* listOfRentalCount.get(i));
		}//for loop ended
		
		//find highest revenue car index
		Integer index = listOfRevenue.indexOf(Collections.max(listOfRevenue));
		
		//print highest car revenue
		System.out.println(jsRes.get(Car[index]));
	}//highestRevenueCar() function ended
	

	// Calculate highest profit car
	private List<Float> discountPrice(String res) {
		
		return null;
	}//discountPrice() function ended

	@Test
	public void highestProfitCar() {
		//store return response in a string			
		String res = returnResponseString();
		
		//convert response string to json
		JsonPath jsRes=new JsonPath(res);
	
		//get list of price and store it in listOfPrice
		List<Float> listOfPrice= jsRes.get(car.perdayrent.Price);
		List<Float> listOfDiscount= jsRes.get(car.perdayrent.Discount);
		List<Float> listOfMaintanceCost= jsRes.get(car. metrics.yoymaintenancecost);
		List<Float> listOfDepreciation= jsRes.get(car.perdayrent. metrics. depreciation);
		List<Float> listOfPriceWithDiscount=new ArrayList();
	
		//get list of rental count year to date
		List<Integer> listOfRentalCount= jsRes.get(car. metrics.rentalcount.yeartodate);
		List<Float> listOfProfit=new ArrayList();

		//add values to listOfPriceWithDiscount after applying price-(price*discount)/100
		for(int i=0; i<listOfPrice.size();i++){
			listOfPriceWithDiscount.add(listOfPrice.get(i)-         
					(listOfPrice.get(i)*listOfPriceWithDiscount.get(i))/100)
		}//for loop ended
		
		//add values to listOfProfit after applying (pricewithdiscount *rentalcount)-(maintanceCost + depreciation)
		for(int i=0; i<listOfPrice.size();i++){
			listOfProfit .add((listOfPriceWithDiscount.get(i)* listOfRentalCount.get(i))-(
					listOfMaintanceCost.get(i)+ listOfDepreciation.get(i)));
		}//for loop ended
		
		//find highest revenue car index
		Integer index = listOfProfit.indexOf(Collections.max(listOfProfit));
	
		//print highest car revenue
		System.out.println(jsRes.get(Car[index]));
	}//highestProfitCar() function ended

}//ThirdQ class ended
