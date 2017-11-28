/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasankhusus;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianSentenceTokenizer;
import IndonesianNLP.IndonesianStemmer;
import IndonesianNLP.IndonesianSentenceDetector;
import java.util.Arrays;

/**
 *
 * @author mochamadtry
 */
public class PraProses {
    public IndonesianSentenceFormalization formalizer; 
    public IndonesianSentenceTokenizer tokenizer; 
    public IndonesianStemmer stemmer;
    public IndonesianSentenceDetector detector;
    
    public PraProses(){
        formalizer = new IndonesianSentenceFormalization(); 
        tokenizer = new IndonesianSentenceTokenizer();
        stemmer = new IndonesianStemmer();
        detector = new IndonesianSentenceDetector();
        
        formalizer.initStopword();  
        
    }
    
    public List sentencesSpliter (String text){
        List<String> sentencesSplit = new ArrayList(); 
        
        sentencesSplit = detector.splitSentence(text);
        
        return sentencesSplit; 
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
    
    public void segmentasiTeks (List isiFile, List bagianAwal, List bagianIsi){
        int i = 0; 
        int init = 0; 
        boolean ketemu = false;
        while (i < isiFile.size()&& ketemu == false){
            String line = isiFile.get(i).toString(); 
            String lineNext = isiFile.get(i+1).toString(); 
            Pattern pattern = Pattern.compile("(.*)(KETUA RAPAT)(.*)");
            Pattern patternPlus = Pattern.compile("(.*)(Bissmillahirahmanirahim)(.*)");
            Matcher matcher = pattern.matcher(line);
            Matcher matcherPlus = patternPlus.matcher(lineNext); 
            if (matcher.find() && matcherPlus.find()){
                ketemu = true; 
            }
            bagianAwal.add(line);
            i++;
            init = i;
        }
        while (init < isiFile.size()&&ketemu == true){
            bagianIsi.add(isiFile.get(init).toString());
            init++;
        }
    }
    
    public String preprocessTeksIsi (String text){
        String processed = new String(); 
        
        //Formalisasi 
        processed = formalizer.normalizeSentence(text);

        //Hapus semua yang tidak diperlukan 
        //processed = prosesNoNeed(text.toLowerCase());

        //Tokenisasi 
        ArrayList<String> words = tokenizer.tokenizeSentence(processed);
        //System.out.println(words);

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
    
    public String preprocessTeksAwal (String text){
        String processed = new String(); 
        
        //Formalisasi 
        processed = formalizer.normalizeSentence(text);

        //Hapus semua yang tidak diperlukan 
        //processed = prosesNoNeed(text.toLowerCase());

        //Tokenisasi 
        ArrayList<String> words = tokenizer.tokenizeSentence(processed);
        //System.out.println(words);

        //Stemming 
        StringBuilder result = new StringBuilder(); 
        for (String word : words){
            if (word.length() > 1){
                //word = stemmer.stem(word);
                word = formalizer.formalizeWord(word);
                result.append(word + " ");
            }
        }     
        return processed; 
    }
    
    public List prosesPucukSurat (List text){
        List<String> processed = new ArrayList(); 
        int i = 0; 
        boolean isBatas = false;
        while(i < text.size()&& isBatas ==false){
            String thisText = new String();
            thisText = text.get(0).toString();
            //thisText = formalizer.normalizeSentence(thisText);
            Pattern pattern = Pattern.compile("(.*)(\\d+)(.*)");
        }
        return processed; 
    }
    
    public static void toInt (List text){
       List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(pertama|kedua|ketiga|Pertama|Kedua|Ketiga|Keempat|Kelima)(.*)")) 
            {
                System.out.println("alala");
                sentence = sentence.replaceAll("(pertama|kedua|ketiga|Pertama|Kedua|Ketiga|Keempat|Kelima)", " ");
            }
            System.out.println(sentence);
            processed.add(sentence);
            System.out.println(processed);
        }
        text.clear();
        for (int j = 0; j < processed.size(); j++){
            String sentence = processed.get(j).toString();
            text.add(sentence);
        }
        System.out.println(text); 
    }
    
    public static void deleteSpace (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\s+)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\s+)", "");
            }
            processed.add(sentence);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteNotWord (List text){
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            if (sentence.length() < 1 | sentence.isEmpty())
                text.remove(i);
        }
    }
    
    public static void deleteColon (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\:)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\:)", "");
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
            if (match = sentence.matches("(.*)(\\-+\\s+\\d+\\s+\\-|\\-(.*)\\-)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\-+\\s+\\d+\\s+\\-|\\-+\\d+\\-(.*))", "");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteNumber (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\d+\\.|\\d(.*)\\.)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\d+\\.|\\d(.*)\\.)", "");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteNoNeedPAW (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(yaitu|yaitu:|Selanjutnya|selanjutnya)(.*)")) 
            {
                sentence = sentence.replaceAll("(.*)(yaitu|yaitu:)", "");
                sentence = sentence.replaceAll("(selanjutnya|Selanjutnya)(.*)", "");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteNoNeedPucukSurat (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(yaitu|yaitu:|Selanjutnya|selanjutnya)(.*)")) 
            {
                sentence = sentence.replaceAll("(.*)(yaitu|yaitu:)", "");
                sentence = sentence.replaceAll("(selanjutnya|Selanjutnya)(.*)", "");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteNoNeed (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(didahului|Antar Waktu|antar waktu|Rekaman Terputus|rekaman terputus)(.*)")) 
            {
                sentence = sentence.replaceAll("(yang didahului)(.*)", "");
                sentence = sentence.replaceAll("(\\(+rekaman terputus+\\))", "");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteParenthesis (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\(|\\))(.*)")) 
            {
                sentence = sentence.replaceAll("(\\()", "");
                sentence = sentence.replaceAll("(\\))", "");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteBidang (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(bidang|Bidang|Sidang dewan|sidang dewan)(.*)")) 
            {
                sentence = sentence.replaceAll("(bidang|Bidang|Sidang dewan|sidang dewan)(.*)", "");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    
    public static void replaceWithDPR (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(Dewan Perwakilan Rakyat)(.*)")) 
            {
                sentence = sentence.replaceAll("(Dewan Perwakilan Rakyat)", "DPR");
                sentence = sentence.replaceAll("(Republik Indonesia)", "RI");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void replaceWithSekjen (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(Sekretaris Jenderal)(.*)")) 
            {
                sentence = sentence.replaceAll("(Sekretaris Jenderal)", "Sekjen");
                sentence = sentence.replaceAll("(Dewan Perwakilan Rakyat)", "DPR");
                sentence = sentence.replaceAll("(Republik Indonesia)", "RI");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void replaceKeputusanPresiden (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(Keputusan Presiden|Keputusan presiden|keputusan presiden)(.*)")) 
            {
                sentence = sentence.replaceAll("(Keputusan Presiden|Keputusan presiden|keputusan presiden)(.*)(yaitu |yaitu:|yaitu :)", "");
                sentence = sentence.replaceAll("(Keputusan Presiden|Keputusan presiden|keputusan presiden)(.*)(\\d+\\-+\\d+)", "");
                sentence = sentence.replaceAll("(.*)(yaitu)", "");
                sentence = sentence.replaceAll("(\\d+\\.|\\d+\\s+\\.)", "");
                
                
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
}