package com.psl.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.psl.bean.Dish;
import com.psl.bean.Location;
import com.psl.bean.Order;
import com.psl.util.DominozPizzaDelivery;
import com.psl.util.DominozPizzaDeliveryImpl;

public class Client {

	public static void main(String[] args) {
		List<Dish> dish = new ArrayList();
		Set<Location> location = new HashSet();
		List<Order> orderList = new ArrayList();
		DominozPizzaDeliveryImpl dp= new DominozPizzaDeliveryImpl();
		dp.populateData("dish.txt", "location.txt", dish, location);
		System.out.println("DISH INFO");
		for(Dish di:dish)
		{
			System.out.print(di.getDishId()+" ");
			System.out.print(di.getDishName()+" ");
			System.out.print(di.getCost()+" ");
			System.out.print(di.getTimeToCook()+"\n");
			
		}
		System.out.println("LOCATION INFO");
		for(Location lo:location)
		{
			System.out.print(lo.getLocationCode()+" ");
			System.out.print(lo.getLocationDistance()+" ");
			System.out.print(lo.getLocationTime()+"\n");
			
		}
		
		dp.calculateLocationForDistance(dish, location);
		System.out.println("Possible Location Info INFO");
		for(Dish di:dish)
		{
			System.out.print(di.getDishId()+" ");
			System.out.print(di.getDishName()+" ");
			System.out.print(di.getCost()+" ");
			System.out.print(di.getTimeToCook()+" ");
			Set<Location> temp =di.getSet();
			for(Location lo:temp)
			{
				System.out.print(lo.getLocationCode()+" ");	
			}
			System.out.println();
			
		}
		System.out.println("ORDER LIST");
		orderList=dp.calculateOrder("order.txt", dish, location);
		for(Order o:orderList)
		{
			System.out.print(o.getDishId()+" ");
			System.out.print(o.getLocationCode()+" ");
			System.out.print(o.getTotalCost()+"\n");
		}
		System.out.println("After FREE HOME DELIVERY");
		dp.freeDelivery(orderList, dish, location);
		for(Order o:orderList)
		{
			System.out.print(o.getDishId()+" ");
			System.out.print(o.getLocationCode()+" ");
			System.out.print(o.getTotalCost()+"\n");
		}
		
	}

}
