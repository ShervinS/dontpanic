package categories;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JTextField;

import panic.ThemeLoader;

//syntax isn't exactly right
public class TextFieldWithPlaceholder extends JTextField{

	private String placeHolderString;
	private ThemeLoader tl;
	
  @Override
  protected void paintComponent(java.awt.Graphics g) {
      super.paintComponent(g);
      tl = new ThemeLoader();

      if(getText().isEmpty() && ! (FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)){
          Graphics2D g2 = (Graphics2D)g.create();
          g2.setBackground(tl.getColor("lightGray"));
          g2.setFont(getFont().deriveFont(Font.ITALIC));
          g2.setColor(tl.getColor("gray"));
          g2.drawString(this.placeHolderString, 6, 18); //figure out x, y from font's FontMetrics and size of component.
          g2.dispose();
      }
    }
  
  public TextFieldWithPlaceholder(String placeHolderString) {
	    this.placeHolderString = placeHolderString;
	    }
  
  public void changePlaceholderTextTo(String placeHolderText){
	  this.placeHolderString = placeHolderText;
	  this.repaint();
  }
}