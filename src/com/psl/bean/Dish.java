package com.psl.bean;

import java.util.List;
import java.util.Set;

public class Dish {

	private int dishId;
	private String dishName;
	private double cost;
	private double timeToCook;
	private Set<Location> set;
	
	public int getDishId() {
		return dishId;
	}
	public void setDishId(int dishId) {
		this.dishId = dishId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getTimeToCook() {
		return timeToCook;
	}
	public void setTimeToCook(double timeToCook) {
		this.timeToCook = timeToCook;
	}
	public Set<Location> getSet() {
		return set;
	}
	public void setSet(Set<Location> set) {
		this.set = set;
	}
	
	
	
	
}
