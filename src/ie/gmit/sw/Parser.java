package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;

public class Parser implements Runnable {
	
	// private BlockingQueue<LanguageEntry> q = null;
	private Database db = null;
	private String file;
	private int k = 5;
	
//	public Parser (BlockingQueue<LanguageEntry> q) {
//		this.q = q;
//	}
	
	public Parser(String file, int k) {
		this.file = file;
		this.k = k;
	}
	
	public void setDb(Database db) {
		this.db = db;
	}
	
	@Override 
	public void run () {
		
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader
					(new FileInputStream(file)));
			
			String line = null;
			
			while ((line=br.readLine()) != null) {
				String[] record = line.trim().split("@");
				if(record.length != 2) continue;
				parse(record[0], record[1]);
			}
			
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Map<String[], String> db = new TreeMap<String[], String>();

	}
	
	public void parse(String text, String lang, int... ks) {
		Language language = Language.valueOf(lang);
		
		for (int i = 0; i <= text.length() - k; i+=2) {
			CharSequence kmer = text.substring(i, i + k);
			db.add(kmer, language);
		}
	}
	
	public static void main(String[] args) throws Throwable {
		Parser p = new Parser("wili-2018-Small-11750-Edited.txt", 4);
		
		Database db = new Database();
		p.setDb(db);
		Thread t = new Thread(p);
		t.start();
		t.join();
		
		db.resize(300);
		
		String queryFile  = "irish.txt";
		
		p.analyseQuery(queryFile);
		
	}
	
	public void analyseQuery(String f) throws IOException 
	{
		
		int frequency = 1;
		String kmer = "";
		int kmerH=0;

		//Map<Integer, CharSequence> query = new HashMap<Integer, CharSequence>();
		Map<Integer, LanguageEntry> q = new TreeMap<>();
		
		BufferedReader br1;
		br1 = new BufferedReader(new InputStreamReader
					(new FileInputStream(f)));
		String line = null;
		
		while ((line=br1.readLine())!=null) {	
			
			for (int i = 0; i <= line.length() - k; i+=2) {	
				kmer = line.trim().substring(i, i+k);
				kmerH = kmer.hashCode();
				System.out.println(kmer + "\n");
			}
			
			if (q.containsKey(kmerH)) {
				frequency += q.get(kmerH).getFrequency();
			}
			LanguageEntry lang = new LanguageEntry(kmerH, frequency);
			q.put(frequency, lang);
			//query.put(frequency, kmer);
			//System.out.println(Collections.singletonList(q));
		}
		
		Language language = db.getLanguage(q);
		System.out.println("The language appears to be " + language);
		br1.close();
		
	}

	
	public void compareToFile(Map<Integer, CharSequence> q) {
		
	}
	
}
		

