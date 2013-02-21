package Panic;

import java.io.IOException;

public class XMLTest {

	/**
	 * DEPRECATED
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		XML xml = new XML();
		Task task = new Task("Plugga","tr�sl�jdl�xa: bygga en magnifik barkb�t","skola",1,"24/7", false);
		Task task2 = new Task("Skor","jag m�ste k�pa kl�der o skor","Shopping",3,"24/7", true);
		xml.addTask(task);
		xml.addTask(task2);
		xml.addTask(task2);
		xml.getTasks();
		xml.removeTask(task);
	}

}