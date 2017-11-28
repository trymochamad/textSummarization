/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.analysis.id.IndonesianStemmer;
import ringkasanumum.klasifikasiretorik.AksesDatabase;
import weka.core.stopwords.WordsFromFile;

/**
 *
 * @author mochamadtry
 */
public class PraProses {
     public static List numID = new ArrayList();
    public static List<String> teks = new ArrayList(); 
    Map<List<String>, List<String>> map = new LinkedHashMap<>();
	public String Stopword(String text, String basicpath) {
		WordsFromFile wo = new WordsFromFile();
		File inputFile = new File(basicpath + "stopword.txt");
		wo.setStopwords(inputFile);

		String[] words = text.split("\\s+");

		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w]", "");

			if (wo.isStopword(words[i])) {
				words[i] = "";
			}
		}
                //System.out.println("basic: " + basicpath);
		String sentence = Arrays.deepToString(words).replaceAll("[^\\w\\s]", "").trim();
		System.out.println("debug: " + sentence);

		text = sentence;

		words = text.split("\\s+");

		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w]", "");
		}

		sentence = Arrays.deepToString(words).replaceAll("[^\\w\\s]", "").trim();
		return sentence;
	}

	public String Stemming(String text) {
		IndonesianStemmer stemmer = new IndonesianStemmer();

		String[] words = text.split("\\s+");

		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w]", "");

			char[] chars = words[i].toCharArray();
			int len = stemmer.stem(chars, chars.length, true);

			words[i] = new String(chars, 0, len);
		}

		String sentence = Arrays.deepToString(words).replaceAll("[^\\w\\s]", "");
		return sentence;
	}

	public int StopwordFromFile(String text, WordsFromFile wo) {
		String[] words = text.split("\\s+");

		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^\\w]", " ");
                        
			if (wo.isStopword(words[i])) {
				return 1;
			}
		}
		return 0;

	}

	public void PreprocessText(AksesDatabase da, String basicpath, String id) throws SQLException, FileNotFoundException {
		ResultSet rs = da.selectquery("SELECT * FROM kalimat WHERE id_notulen = " + id);
                 int numberSentences = 0; 

		while (rs.next()) {
			String result = rs.getString("rawtext").replaceAll("[^\\w\\s]", " ").replaceAll("[\\t\\n\\r]", " ")
					.toLowerCase();
                        
                        teks.add(result);
			result = Stemming(Stopword(result, basicpath));

			da.updatequery("INSERT INTO preprocessedkalimat " + "VALUES (" + rs.getString("id") + ", "
					+ rs.getString("id") + ", '" + result + "')");

			//System.out.println("lalal" + rs.getString("id"));
                        numID.add(numberSentences); 
                        numberSentences++; 
                        
                        
		}
                System.out.println ("jumlah teks : " + teks.size());
                //map.put(numID, teks);
                System.out.println ("jumlah ID : " + numID.size());
                try {
                    //Whatever the file path is.
                    File statText = new File("G:\\Kuliah\\8th semester\\KlasifikasiNotulen\\new.txt");
                    FileOutputStream is = new FileOutputStream(statText);
                    OutputStreamWriter osw = new OutputStreamWriter(is);    
                    BufferedWriter w = new BufferedWriter(osw);
                        for (int i = 0; i < numID.size(); i++){
                            w.write(numID.get(i).toString()+ " ");
                            w.write(teks.get(i));
                            w.newLine();
                        }
                        w.close();
                    
                    
                } catch (IOException e) {
                    System.err.println("Problem writing to the file statsTest.txt");
                }
                
	}
    
}
