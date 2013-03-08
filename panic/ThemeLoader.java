package panic;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tasks.Task;

import categories.Category;

/**
* Loads the color theme (Based on the XML-manager by Erik Samuelsson)
* @version 1.0
* @author Carl Ekman
*/

public class ThemeLoader {

	/**
	* Loads all the colors from colors.xml
	*/
	public void loadColors() {
		try {

			File fXmlFile = new File("./resources/colors.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("color"); 

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String key= eElement.getElementsByTagName("key").item(0).getTextContent();
					String value = eElement.getElementsByTagName("value").item(0).getTextContent();

					Color c = Color.decode(value);
					UIManager.put(key, c);
					
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Gets all the colors from colors.xml
	*/
	public Color getColor(String k) {
		try {

			File fXmlFile = new File("./resources/colors.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("color"); 

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String key= eElement.getElementsByTagName("key").item(0).getTextContent();
					String value = eElement.getElementsByTagName("value").item(0).getTextContent();

					if (key == k) {
						Color c = Color.decode(value);
						return c;
					}
					
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		Color c = new Color(255,0,0);
		return c;
	}
	
}