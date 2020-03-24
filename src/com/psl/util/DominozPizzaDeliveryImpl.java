package com.psl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.psl.bean.Dish;
import com.psl.bean.Location;
import com.psl.bean.Order;


public class DominozPizzaDeliveryImpl implements DominozPizzaDelivery {

	@Override
	public void populateData(String dishFile, String locationFile, List<Dish> dishs, Set<Location> locations) {
		// TODO Auto-generated method stub
		try  
		{  
		//the file to be opened for reading  
		FileInputStream fis=new FileInputStream(dishFile);       
		Scanner sc=new Scanner(fis);    //file to be scanned  
		//returns true if there is another line to read  
		while(sc.hasNextLine())  
		{
			String line=sc.nextLine();
			Dish di = new Dish(); 
	        String[] arrOfStr = line.split(",", 4); 
	  
	      	di.setDishId(Integer.parseInt(arrOfStr[0]));
	       	di.setDishName(arrOfStr[1]);
	       	di.setCost(Double.parseDouble(arrOfStr[2]));
	       	di.setTimeToCook(Double.parseDouble(arrOfStr[3]));
			 
	       	dishs.add(di);
		}  
		sc.close();     //closes the scanner  
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}
		
		try  
		{  
		//the file to be opened for reading  
		FileInputStream fis=new FileInputStream(locationFile);       
		Scanner sc=new Scanner(fis);    //file to be scanned  
		//returns true if there is another line to read  
		while(sc.hasNextLine())  
		{
			String line=sc.nextLine();
			Location lo = new Location(); 
	        String[] arrOfStr = line.split(",", 3); 
	  
	      	lo.setLocationCode(Integer.parseInt(arrOfStr[0]));
	      	lo.setLocationDistance(Integer.parseInt(arrOfStr[1]));
	       	lo.setLocationTime(Double.parseDouble(arrOfStr[2]));
	      	locations.add(lo);
		}  
		sc.close();     //closes the scanner  
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}
	}

	@Override
	public void calculateLocationForDistance(List<Dish> dishs, Set<Location> locations) {
		// TODO Auto-generated method stub
		for(Dish di: dishs)
		{
			Set<Location> possibleLocation= new HashSet();
			double time=di.getTimeToCook();
			for(Location lo: locations)
			{
				time=time+lo.getLocationTime();
				if(time<=30)
				{
					possibleLocation.add(lo);
					
				}
				time=time-lo.getLocationTime();
			}
			di.setSet(possibleLocation);
		}
	}

	@Override
	public List<Order> calculateOrder(String orderFile, List<Dish> dishs, Set<Location> locations) {
		
		List<Order> orderList= new ArrayList();
		double cost=0;
		double time=0;
		try  
		{  
		//the file to be opened for reading  
		FileInputStream fis=new FileInputStream(orderFile);       
		Scanner sc=new Scanner(fis);    //file to be scanned  
		//returns true if there is another line to read  
		while(sc.hasNextLine())  
		{
			String line=sc.nextLine();
			Order or = new Order(); 
	        String[] arrOfStr = line.split(",", 2); 
	        or.setDishId(Integer.parseInt(arrOfStr[0]));
	       	or.setLocationCode(Integer.parseInt(arrOfStr[1]));
	       	for(Dish di:dishs)
	       	{
	       		if(di.getDishId()==or.getDishId()) {
	       			cost=di.getCost();
	       			time=di.getTimeToCook();
	       		}
	       	}
	       	for(Location lo:locations)
	       	{
	       		if(lo.getLocationCode()==or.getLocationCode()) {
	       			cost=cost+lo.getLocationDistance();
	       			time=time+lo.getLocationTime();
	       		}
	       	}
	       	or.setTotalCost(cost);
	       	if(time<=30)
	       	{
	       		orderList.add(or);
	       	}
	       	time=0;
	       	cost=0;
		}  
		sc.close();     //closes the scanner  
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}
		
		
		return orderList;
	}

	@Override
	public void freeDelivery(List<Order> orders, List<Dish> dishs, Set<Location> locations) {
		
		double cost=0;
		double distance=0;
		for(Order or: orders)
		{
			for(Dish di: dishs)
			{
				if(or.getDishId()==di.getDishId())
				{
					cost=di.getCost();
				}
			}
			for(Location lo:locations)
			{
				if(or.getLocationCode()==lo.getLocationCode())
				{
					distance = lo.getLocationDistance();
				}
			}
			if(cost>200&&distance<=10)
			{
				or.setTotalCost(cost);
			}
		}	
	}	
}