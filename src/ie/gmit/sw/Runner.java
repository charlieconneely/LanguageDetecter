package ie.gmit.sw;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Runner {
	
	private static String wiliFileName;
	private static String queryFileName;
	
	private static Scanner console = new Scanner(System.in);

	public static void main(String[] args) throws IOException, InterruptedException {
		
		displayHeader();
		
		wiliFileName = getWiLIFileName();
		System.out.println("Building subject database... please wait...");
		Parser p = new Parser(wiliFileName, 4);

		Database db = new Database();
		p.setDb(db);
		Thread t = new Thread(p);
		t.start();
		t.join();
			
		db.resize(300);
		
		queryFileName  = getQueryFileName();
		
		p.analyseQuery(queryFileName);
	}
	
	public static void displayHeader()
	{
		System.out.println("Charlie Conneely - G00348887");
		System.out.println("------------------------------");
	}
	
	public static String getWiLIFileName()
	{
		String inputFile;	
		String file;
		
		do
		{
			System.out.println("Enter WiLI Data Location>");
			inputFile = console.next();
		} while(new File(inputFile).isFile() == false);
	
		file = inputFile;
		
		return file;
	}

	public static String getQueryFileName()
	{
		String inputFile;	
		String file;
		
		do
		{
			System.out.println("Enter Query File Location>");
			inputFile = console.next();
		} while(new File(inputFile).isFile() == false);
	
		file = inputFile;
		
		return file;
	}
}
