/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum;
import java.util.ArrayList;
import java.util.List;
import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianSentenceTokenizer;
import IndonesianNLP.IndonesianStemmer;
import IndonesianNLP.IndonesianSentenceDetector;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author mochamadtry
 */
public class Praproses {
    public IndonesianSentenceFormalization formalizer; 
    public IndonesianSentenceTokenizer tokenizer; 
    public IndonesianStemmer stemmer;
    public IndonesianSentenceDetector detector; 
    
    public Praproses(){
        formalizer = new IndonesianSentenceFormalization(); 
        tokenizer = new IndonesianSentenceTokenizer();
        stemmer = new IndonesianStemmer();
        detector = new IndonesianSentenceDetector(); 
        
        formalizer.initStopword();  
        
    }
    public List sentencesSpliter (String text){
        List<String> sentencesSplit = new ArrayList(); 
        
        sentencesSplit = detector.splitSentence(text);
        for (int i = 0 ; i < sentencesSplit.size(); i++){
            //System.out.println("kalimat ke- " + i + " " + sentencesSplit.get(i)); 
        }
        
        
        return sentencesSplit; 
    }
    
    public String praprocessSentences (String text){
        String processed = new String(); 
        
        //Formalisasi 
        processed = formalizer.normalizeSentence(text);

        //Hapus semua yang tidak diperlukan 
        //processed = prosesNoNeed(text.toLowerCase());

        //Tokenisasi 
        ArrayList<String> words = tokenizer.tokenizeSentence(processed);

        //Stemming 
        StringBuilder result = new StringBuilder(); 
        for (String word : words){
            if (word.length() > 1){
                //word = stemmer.stem(word);
                word = formalizer.formalizeWord(word);
                result.append(word + " ");
            }
        }
        //Hapus Stop word 
        //String finalize = formalizer.deleteStopword(result.toString());
        //processed = finalize;       
        return processed; 
    }
    
    public static void processPembicara (List text){
        int j = 0; boolean isFind = false; 
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            j = 0; isFind = false; 
            String sentence = text.get(i).toString();
            boolean match;
            List<String> daftarPola = Arrays.asList(".*(KETUA RAPAT:).*", ".*(F-[A-Z\\s]+\\(.*\\).*\\:).*", ".*(KETUA.*\\(.*\\)\\:).*",".*(PIMPINAN PANSUS.*\\(.*\\)\\:).*", ".*(F\\s-\\s.*\\..*[A-Z].*\\(.*\\)).*", ".*(F-[A-Z\\s]+\\(.*\\).*\\;).*", ".*(F-.*\\..*[A-Z].*\\(.*\\)).*", ".*(F.*\\..*[A-Z].*\\(.*\\)).*", ".*(PIMPINAN BALEG.*\\(.*\\)\\:).*", ".*(PIMPINAN KOMISI.*\\(.*\\)\\:).*", ".*(MENTERI.*\\(.*\\)\\:).*");
            while (j < daftarPola.size() && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Matcher matcher = pattern.matcher(sentence);
                if (matcher.find()) {
                    sentence = sentence.replaceAll("(\\.)", "");
                    isFind = true;
                }
                j++;
            }
            processed.add(sentence);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void addSpaceSentences (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\.)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\.)", ". ");
                //sentence = sentence.replaceAll("(\\;)", "");
                //sentence = sentence.trim();
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteDot (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches(".*\\d+(\\.)\\d+.*")) 
            {
                System.out.println("masuk hilang dot");
                sentence = sentence.replaceAll("\\.", "");
                sentence = sentence.trim();
            }
            processed.add(sentence);
            
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deletePageNumber (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches(".*\\d+[A-Z]+.*")) 
            {
                System.out.println("masuk hilang page number");
                sentence = sentence.replaceAll("(\\d+)", "");
                sentence = sentence.trim();
            }
            processed.add(sentence);
            
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
}
