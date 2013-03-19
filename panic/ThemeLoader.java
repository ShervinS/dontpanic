package panic;

import java.awt.Color;
import java.io.InputStream;

import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
* Loads the color theme (Based on the XML-manager by Erik Samuelsson)
* @author Carl Ekman
*/

public class ThemeLoader {

	/**
	* Loads all the colors from colors.xml
	*/
	public void loadColors() {
		try {

			InputStream fXmlFile = this.getClass().getClassLoader().getResource("resources/colors.xml").openStream();
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
		//System.out.println("getting color");
		try {

			InputStream fXmlFile = this.getClass().getClassLoader().getResource("resources/colors.xml").openStream();
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
					
					//System.out.println("key: "+key);
					//System.out.println("value: "+value);

					if (key.equals(k)) {
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
