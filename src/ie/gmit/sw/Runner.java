package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Runner {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader
				(new FileInputStream("./wili-2018-Small-11750-Edited.txt")));
		
		Scanner s = new Scanner(new File("./wili-2018-Small-11750-Edited.txt"));
		
		Map<String[], String> db = new TreeMap<String[], String>();
		
		String line = null;
		//int i = 0;
		String lang = null;
		while ((line=br.readLine()) != null) {
			String[] record = line.toUpperCase().trim().split("@");	
				
			//db.put(record[0], record[1]);
			//i++;
		}
		
		System.out.println(db);

	}
	
	public static void processLine(String r, String l)
	{
		
	}

}
