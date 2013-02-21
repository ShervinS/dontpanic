package Panic;

import java.io.IOException;

public class XMLTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		XML xml = new XML();
		Task task = new Task("Plugga","träslöjdläxa: bygga en magnifik barkbåt","skola",1,"24/7", false);
		Task task2 = new Task("Skor","jag måste köpa kläder o skor","Shopping",3,"24/7", true);
		xml.addTask(task);
		xml.addTask(task2);
		xml.addTask(task2);
		xml.getTasks();
		xml.removeTask(task);
	}

}