package categories;

import java.awt.Color;

/** A class intended to hold the name and color of the categories.
 * Each separate category is intended to have an unique color.
 *
 * @author Johannes Henriksson
 * @author Shervin
 */

public class Category {

	private String name;
	private Color color;
	
	public Category(String categoryName, Color color){
		this.name = categoryName;
		this.color = color;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Color getColor(){
		return this.color;
	}
           
	public String toString() {
		return this.name + this.color;
	}
}
