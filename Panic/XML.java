package Panic;

import java.io.File;
import java.io.IOException;

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
import org.xml.sax.SAXException;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML {

	public XML() {
		//if there is an existing XML-file, read it, else make one
		//System.out.println(new File("./file.xml").isFile());
		if (new File("./file.xml").isFile()) {
			readXML();
		}
		else {
			makeEmptyXML();
		}
	}


	public Task[] readXML() {
		System.out.println("readXML");
		return null;
	}




	public void makeEmptyXML() {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("wrapper");
			doc.appendChild(rootElement);

			// staff elements
			Element tasklist = doc.createElement("tasklist");
			rootElement.appendChild(tasklist);

			Element categorys = doc.createElement("categorys");
			rootElement.appendChild(categorys);



			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File("./file.xml"));


			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

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
			Document doc = docBuilder.parse(filepath);

			int id = item.getId();

			//Node wrapper = doc.getFirstChild();



			Node tasklist = doc.getElementsByTagName("tasklist").item(0);

			NodeList list = tasklist.getChildNodes();
			
			
			for (int i = 0; i < list.getLength(); i++) {
				 
                Node node = list.item(i);

                node.getAttribute
                
                
                
		   // get the salary element, and update the value
		   if ("salary".equals(node.getNodeName())) {
			node.setTextContent("2000000");
		   }

                //remove firstname
		   if ("firstname".equals(node.getNodeName())) {
			staff.removeChild(node);
		   }

		}
			
			
			
			
			//System.out.println("" + tasks.getLength());

			Element task = doc.createElement("task");
			//task.appendChild(doc.createTextNode("" + id));
			tasklist.appendChild(task);
			
			
			Element ide = doc.createElement("id");
			ide.appendChild(doc.createTextNode("" + id));
			task.appendChild(ide);
			
			
			
			

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Done");



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


	public void removeTask(TaskItem item) {

	}

}