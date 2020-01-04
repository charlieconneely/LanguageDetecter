package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;

public class Parser implements Runnable {
	
	// private BlockingQueue<LanguageEntry> q = null;
	private Database db = null;
	private String file;
	private int k;
	
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
		
		for (int i = 0; i <= text.length() - k; i+=3) {
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
		
		String s  = "Chuaigh me go dti an siopa mar is maith liom ispini ach thosaigh me ag rith go dti an oifig an phoist";
		
		p.analyseQuery(s);
		
	}
	
	public void analyseQuery(String s) 
	{
		
		int frequency = 1;
		CharSequence kmer;
		int kmerH;
		
		Map<Integer, LanguageEntry> q = new HashMap<>();
		
		for (int i = 0; i <= s.length() - k; i+=3) {	
			kmer = s.substring(i, i+k);
			kmerH = kmer.hashCode();
			
			if (q.containsKey(kmerH)) {
				frequency += q.get(kmerH).getFrequency();
			}
			LanguageEntry lang = new LanguageEntry(kmerH, frequency);
			q.put(frequency, lang);
		}
		
		Language language = db.getLanguage(q);
		System.out.println("appears to be written in " + language);
		
	}

}
