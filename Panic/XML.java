package Panic;

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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


//todo, inv�nta en f�rdig categoryklass, �ndra s� att kategorielementen i xmlfilen i sin tur h�ller element om f�rg osv,
//och g�ra samma �ndring i taskobjektet.. som nu endast h�ller i en str�ng som beskriver kategorin...
//d�refter fixa getCategorys()

public class XML {

	public XML() {
		if (!new File("./file.xml").isFile()) 
			makeEmptyXML();
	}




	public ArrayList<Category> getCategorys() { //TODO read the categorys from the XML-database
		/*ArrayList<Category> r = new ArrayList<Category>();
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

					String id = eElement.getAttribute("id");
					String category= eElement.getElementsByTagName("category").item(0).getTextContent();
					

					Category c = new Category()
					t.setId(Integer.parseInt(id));
					r.add(t);
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;*/
		ArrayList<Category> cats = new ArrayList<Category>();
		cats.add(new Category("Skola",new Color(100,100,100)));
		return cats;
	}

	public void addCategory(Category c){//TODO add category to XML-database
		
	}


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
				System.out.println((""+ itemID));

				System.out.println(node.getAttributes().getNamedItem("id"));


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