package panic;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tasks.Task;
import categories.Category;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
* Class for management of XML
* @version 1.0
* @author Erik Samuelsson
*/

public class XML {

	/**
	* Constuctor that check if there is a file.xml exists, if not it will create one using makeEmptyXML()
	*/	
	
	public XML() {
		if (!new File("./file.xml").isFile()) 
			makeEmptyXML();
	}

	/**
	* Makes file.xml with wrapper as root element
	* tasklist, categorys, idCounter are appended to the root element
	*/
	public void makeEmptyXML() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("wrapper");
			doc.appendChild(rootElement);

			Element tasklist = doc.createElement("tasklist");
			rootElement.appendChild(tasklist);

			Element categorys = doc.createElement("categorys");
			rootElement.appendChild(categorys);

			Element idCounter = doc.createElement("idCounter");
			idCounter.setAttribute("id", "1");
			rootElement.appendChild(idCounter);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("./file.xml"));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}	
	
	/**
	* Gets all the categorys from file.xml
	* @return an arraylist containing all categorys in file.xml
	*/
	
	public ArrayList<Category> getCategorys() {
		ArrayList<Category> r = new ArrayList<Category>();
		try {
			File fXmlFile = new File("./file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("category"); 

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String name = eElement.getElementsByTagName("name").item(0).getTextContent();
					String red = eElement.getElementsByTagName("red").item(0).getTextContent();
					String green = eElement.getElementsByTagName("green").item(0).getTextContent();
					String blue = eElement.getElementsByTagName("blue").item(0).getTextContent();

					Color c = new Color(Integer.parseInt(red),Integer.parseInt(green),Integer.parseInt(blue));
					Category t = new Category(name,c);
					r.add(t);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	* Adds the content of c to the file.xml, if an other category with the same name already was in file.txt, that category will get updated content according to c'
	*/
	
	public void addCategory(Category c){
		try {
			String filepath = "./file.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath); //

			Node categorys = doc.getElementsByTagName("categorys").item(0);
			NodeList categoryList = doc.getElementsByTagName("category");


			for(int i = 0; i < categoryList.getLength(); i++) {
				String n = ((Element)categoryList.item(i)).getAttribute("name");
				if (n.equals(c.getName())) {
					NamedNodeMap attr = categoryList.item(i).getAttributes();
					Node nodeRed = attr.getNamedItem("red");
					nodeRed.setTextContent("" + c.getColor().getRed());;
					Node nodeGreen = attr.getNamedItem("green");
					nodeGreen.setTextContent("" + c.getColor().getGreen());;
					Node nodeBlue = attr.getNamedItem("blue");
					nodeBlue.setTextContent("" + c.getColor().getBlue());;
				}
			}

			Element category = doc.createElement("category");
			categorys.appendChild(category);

			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(c.getName()));
			category.appendChild(name);

			Element red = doc.createElement("red");
			String x = "" + c.getColor().getRed();
			red.appendChild(doc.createTextNode(x));
			category.appendChild(red);

			Element green = doc.createElement("green");
			String y = "" + c.getColor().getGreen();
			green.appendChild(doc.createTextNode(y));
			category.appendChild(green);

			Element blue = doc.createElement("blue");
			String z = "" + c.getColor().getBlue();
			blue.appendChild(doc.createTextNode(z));
			category.appendChild(blue);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result); //

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	/**
	* Removes all categorys in file.xml that has same name as t
	*/
	public void removeCategory(Category t) {

		try {
			String filepath = "./file.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			Node categorys = doc.getElementsByTagName("categorys").item(0);
			NodeList categoryList = doc.getElementsByTagName("category");

			for (int i = 0; i < categoryList.getLength(); i++) {

				Node node = categoryList.item(i);

				String nodeName = ((Element)node).getAttribute("name");


				if (nodeName.equals(t.getName())) {
					categorys.removeChild(node);
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	/**
	* Gets all the categorys from file.xml
	* @return an arraylist containing all task in file.xml
	*/
	public ArrayList<Task> getTasks() {
		ArrayList<Task> r = new ArrayList<Task>();
		try {

			File fXmlFile = new File("./file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("task"); 

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);


				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String id = eElement.getAttribute("id");
					String category= eElement.getElementsByTagName("category").item(0).getTextContent();
					String title = eElement.getElementsByTagName("title").item(0).getTextContent();
					String description = eElement.getElementsByTagName("description").item(0).getTextContent();
					String addate = eElement.getElementsByTagName("addate").item(0).getTextContent();
					String duedate = eElement.getElementsByTagName("duedate").item(0).getTextContent();
					String priority = eElement.getElementsByTagName("priority").item(0).getTextContent();
					String check = eElement.getElementsByTagName("check").item(0).getTextContent();

					Task t = new Task(title, description, category,Integer.parseInt(priority),addate ,duedate, Boolean.parseBoolean(check));
					t.setId(Integer.parseInt(id));
					r.add(t);
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	/**
	* Adds the content of item to the file.xml, if an other item with the same id already was in file.txt, that item will get updated content according to items'
	*/
	public void addTask(Task item) {

		try {
			String filepath = "./file.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath); //

			Node tasklist = doc.getElementsByTagName("tasklist").item(0);

			int itemID = item.getId();

			if (itemID == 0) {
				Node idCounter = doc.getElementsByTagName("idCounter").item(0);
				String id = ((Element) idCounter).getAttribute("id");
				item.setId(Integer.parseInt(id));
				String newId = "" + (Integer.parseInt(id) + 1);

				((Element) idCounter).setAttribute("id",newId);

				Element newTask = doc.createElement("task");
				newTask.setAttribute("id", id);
				tasklist.appendChild(newTask);

				Element category = doc.createElement("category");
				category.appendChild(doc.createTextNode(item.getCategory().getName()));
				newTask.appendChild(category);

				Element title = doc.createElement("title");
				title.appendChild(doc.createTextNode(item.getTitle()));
				newTask.appendChild(title);

				Element description = doc.createElement("description");
				description.appendChild(doc.createTextNode(item.getDescription()));
				newTask.appendChild(description);

				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String dateATM = dateFormat.format(date);
				Element addate = doc.createElement("addate");
				addate.appendChild(doc.createTextNode(dateATM));
				newTask.appendChild(addate);

				Element duedate = doc.createElement("duedate");
				duedate.appendChild(doc.createTextNode(item.getDueDate()));
				newTask.appendChild(duedate);

				Element priority = doc.createElement("priority");
				priority.appendChild(doc.createTextNode(""+item.getPriority()));
				newTask.appendChild(priority);

				Element check = doc.createElement("check");
				check.appendChild(doc.createTextNode(new Boolean(item.isCheck()).toString()));
				newTask.appendChild(check); }

			else {
				NodeList list = tasklist.getChildNodes();

				for (int i = 0; i < list.getLength(); i++) {

					Node node = list.item(i);

					if ((""+itemID).equals(((Element) node).getAttribute("id"))) {
						Node categoryNodeToUpdate = ((Element) node).getElementsByTagName("category").item(0);
						categoryNodeToUpdate.setTextContent(item.getCategory().getName());

						Node titleNodeToUpdate = ((Element) node).getElementsByTagName("title").item(0);
						titleNodeToUpdate.setTextContent(item.getTitle());

						Node descriptionNodeToUpdate = ((Element) node).getElementsByTagName("description").item(0);
						descriptionNodeToUpdate.setTextContent(item.getDescription());

						Node addateNodeToUpdate = ((Element) node).getElementsByTagName("addate").item(0);
						addateNodeToUpdate.setTextContent(item.getAdDate());

						Node duedateNodeToUpdate = ((Element) node).getElementsByTagName("duedate").item(0);
						duedateNodeToUpdate.setTextContent(item.getDueDate());

						Node priorityNodeToUpdate = ((Element) node).getElementsByTagName("priority").item(0);
						priorityNodeToUpdate.setTextContent("" + item.getPriority());

						Node checkNodeToUpdate = ((Element) node).getElementsByTagName("check").item(0);
						checkNodeToUpdate.setTextContent(new Boolean(item.isCheck()).toString());
					}
				}

			}



			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();


			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result); //



		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	/**
	* Removes all tasks in file.xml that has same id as t
	*/

	public void removeTask(Task t) {

		try {
			String filepath = "./file.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			int itemID = t.getId();
			Node taskList = doc.getElementsByTagName("tasklist").item(0);
			NodeList list = doc.getElementsByTagName("task");

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);

				if ((""+ itemID).equals(((Element) node).getAttribute("id"))) {
					taskList.removeChild(node);
				}

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);


		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
}