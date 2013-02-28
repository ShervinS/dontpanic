package categories;

import java.awt.Color;


import java.awt.Color;

/** A class intended to hold the name and color of the catagorys for the todo application.
 * Each separate catogory is inteded to have an uniqe color.
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
	
    /**
     *
     * @param color The color representated as a 6 digit hex number
     * @param name The name of the category
     */
  /*  public Category(String color,String name){
        this.color = color;
        this.name  = name;
    }
    /**
     *
     * @param color A color object witch will be used as a representation for the category
     * @param name The name of the category
     */
    /*public Category(Color color,String name){
        this.color = Integer.toHexString( color.getRGB() );
        this.name = name;
    }*/
    /**
     *
     * @return An awt color object.
     */
    /*public Color getColorAwt(){
        int intValue = Integer.parseInt(color,16);
        Color aColor = new Color(intValue);
        return aColor;
    }*/
    /**
     *
     * @return A 6 digit string represantation of the color
     */
   /* public String getColorString(){
       // return color;
    }*/
    /**
     *
     * @return The name of the category
     */
            
}
