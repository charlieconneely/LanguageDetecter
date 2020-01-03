package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

public class Parser {
	
	private BlockingQueue<LanguageEntry> q = null;
	
	public Parser (BlockingQueue<LanguageEntry> q) {
		this.q = q;
	}
	
	public void run () throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader
				(new FileInputStream("./wili-2018-Small-11750-Edited")));
		
		String line = null;
		
		while ((line=br.readLine()) != null) {
			String[] record = line.toUpperCase().trim().split("e");
			
			processLine(record[0], record[1]);
		}
	}
	
	public void processLine(String text, String lang) {
		//Language l = Language.getLanguageName(lang);
		
		for (int i = 0; i < text.length(); i+=3) {
			//db.add(l.getKmer(), lang);
		}
	}

}
