package Panic;

import java.awt.Color;

public class Category {

	protected String name;
	protected Color color;
	
	public Category(String categoryName, Color color){
		this.name = categoryName;
		this.color = color;
	}
	
	public String name(){
		return this.name;
	}
	
	public Color color(){
		return this.color;
	}
}
