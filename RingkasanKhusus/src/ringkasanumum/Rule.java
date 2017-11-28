/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mochamadtry
 */
public class Rule {
    
    public static void ruleTanggalRapat(List text){
        trimSentences(text);
        deleteNoNeedTanggalRapat(text);
        
    }
    
    public static void ruleTahunSidang(List text){
        trimSentences(text);
    }
    
    public static void ruleMasaSidang(List text){
        trimSentences(text);
    }
    
    public static void ruleUrutanSidang(List text){
        trimSentences(text);
    }
    
    public static void ruleDaftarHadir(List text){
        trimSentences(text);
        deleteNotWord(text);
        deleteMinus(text);
        deleteNoNeedDaftarHadir(text);
    }
    
    public static void ruleAcaraRapat (List text){
        deleteNoNeedAcaraRapat(text);
        deleteNumber(text);
    }
    
    public static void rulePembicara(List text){
        deleteNoNeed(text);
        replacePembicara(text);
        deleteParenthesis(text);
        
    }
    
    public static void ruleInterupsi(List<List<String>> text){
        
        addNull(text);
        deletePageNumber(text);
        deleteNoNeedInterupsi(text);
        deleteNotWords(text);
    }
    
    public static void restructuredAcaraRapat (List<String> text){
        int j = 0;
        StringBuilder teks = new StringBuilder();
        List<String> value = new ArrayList();
        int iterator = 1; 
        //System.out.println("banya size " + text.size());
        while (j < text.size()){ 
            Pattern patternPotongan = Pattern.compile (".*(\\,.*[a-z]\\s\\.|\\:.*[a-z]\\s\\.).*");
            Matcher matcher;// = patternPotongan.matcher(text.get(j));

            matcher = patternPotongan.matcher(text.get(j));
            if (matcher.find()){
                String isi = text.get(j);
                teks.append(isi + " ");
            }
            else{
               if (!teks.toString().isEmpty()){
                   String isi = text.get(j);
                   teks.append(isi+" ");
                   value.add(teks.toString());
                   //value.add(isi);
                   teks.delete(0, teks.length());
                   
                   
               }
               else { //teks kosong 
                    String isi = text.get(j);
                    value.add(isi); 
               }
            }
            j++;
        }
        
        text.clear();
        for (int k = 0; k < value.size(); k++){
            text.add(value.get(k));
        }
        
    }
    
    public static void replacePembicara (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\:)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\:)(.*)", "");
                //sentence = sentence.replaceAll("(selanjutnya|Selanjutnya)(.*)", "");
                sentence = sentence.trim();
            }
            else {
                sentence = sentence.trim();
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
            if (match = sentence.matches("(.*)(INTERUPSI|Interupsi|interupsi)(.*)")) 
            {
                sentence = sentence.replaceAll("(INTERUPSI|Interupsi|interupsi)", "");
                //sentence = sentence.replaceAll("(selanjutnya|Selanjutnya)(.*)", "");
                sentence = sentence.trim();
            }
            else {
                sentence = sentence.trim();
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteNoNeedInterupsi (List<List<String>> text){
        List<List<String>> processed = new ArrayList(); 
        List<String> penampung = new ArrayList();
        for (int i = 0; i < text.size(); i++ ){
            penampung = new ArrayList();
            for (int j = 0; j < text.get(i).size(); j++){
                 
                String sentence = text.get(i).get(j);
                boolean match;
                if (match = sentence.matches("(.*)(KETUA RAPAT|Saya persilakan sekarang|Silakan)(.*)")) 
                {
                    sentence = sentence.replaceAll("(KETUA RAPAT|Saya persilakan sekarang|Silakan).*", "");
                    sentence = sentence.trim();
                }
                else {
                    sentence = sentence.trim();
                }
                penampung.add(sentence);
            }
            processed.add(penampung);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
        
    }
    
    public static void deleteNoNeedTanggalRapat (List text){
        List<String> processed = new ArrayList(); 
        
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(tanggal)(.*)|(.*)(dan)(.*)|.*ini hari.*")) 
            {
                sentence = sentence.replaceAll("tanggal", "");
                sentence = sentence.replaceAll("dan.*", "");
                sentence = sentence.replaceAll(".*ini hari", "");
                sentence = sentence.trim();
            }
            else {
                sentence = sentence.trim();
            }
            
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteNoNeedDaftarHadir (List text){
        List<String> processed = new ArrayList(); 
        List<String> result = new ArrayList();
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(Dengan demikian kuorum)(.*)|(.*)(Dengan demikian maka kuorum)(.*)|.*dihadiri oleh.*|.*sebagai berikut.*|\\d{2}")) 
            {
                sentence = sentence.replaceAll("Dengan demikian kuorum.*", "");
                sentence = sentence.replaceAll("Dengan demikian maka kuorum.*", "");
                sentence = sentence.replaceAll(".*dihadiri oleh.*", "");
                sentence = sentence.replaceAll(".*sebagai berikut.*", "");
                sentence = sentence.replaceAll("\\d{2}", "");
                sentence = sentence.trim();
            }
            else {
                sentence = sentence.trim();
            }
            if (sentence.split(" ").length > 1){
               processed.add(sentence); 
            }
            
            
            //System.out.println(processed);
        }
        System.out.println("tolong " + processed.size());
        if (processed.size() >= 10 && processed.size() % 2 == 0){
            int mulai = processed.size()/2;
            int k = mulai; 
            for (int i = 0; i < mulai; i++){
                String sentences = processed.get(i);
                sentences = sentences.concat(" "+processed.get(k));
                result.add(sentences);
                k++; 
            }
        }
        text.clear();
        for (int i = 0; i < result.size(); i++){
          text.add(result.get(i));
        }
        
    }
    
    public static void deleteNoNeedAcaraRapat (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(Untuk itu|Rapat Paripurna hari ini adalah|Sekarang kami akan menanyakan)(.*)")) 
            {
                sentence = sentence.replaceAll("Untuk itu.*", "");
                sentence = sentence.replaceAll(".*Rapat Paripurna hari ini adalah", "");
                sentence = sentence.replaceAll("Sekarang kami akan menanyakan.*", "");
                //sentence = sentence.replaceAll("Mudah-mudahan.*", "");
                //sentence = sentence.replaceAll("Dengan demikian maka kuorum.*", "");
                sentence = sentence.trim();
            }
            else if (match = sentence.matches("(.*)(Mudah-mudahan)(.*)")) 
            {
                text.remove(i);
            }
            else {
                sentence = sentence.trim();
            }
            
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deletePageNumber (List<List<String>> text){
        List<List<String>> processed = new ArrayList(); 
        List<String> penampung = new ArrayList();
        for (int i = 0; i < text.size(); i++ ){
            penampung = new ArrayList();
            for (int j = 0; j < text.get(i).size(); j++){
                 
                String sentence = text.get(i).get(j);
                boolean match;
                if (match = sentence.matches("(.*)(\\-+\\s+\\d+\\s+\\-|\\-(.*)\\-)(.*)")) 
                {
                    sentence = sentence.replaceAll("(\\-+\\s+\\d+\\s+\\-|\\-+\\d+\\-(.*))", "");
                }
                penampung.add(sentence);
            }
            processed.add(penampung);
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
            if (match = sentence.matches("(.*)(\\(||\\))(.*)")) 
            {
                sentence = sentence.replaceAll("(\\()", "");
                sentence = sentence.replaceAll("(\\))", "");
                sentence = sentence.trim();
            }
            else {
                sentence = sentence.trim();
            }
            
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteMinus (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\-)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\-)", "");
                sentence = sentence.trim();
            }
            else {
                sentence = sentence.trim();
            }
            
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteNotWord (List text){ // menghapus kata yang <= 2 huruf
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            if (!sentence.matches(".*[a-zA-Z]+.*")|sentence.length() < 2 | sentence.isEmpty())
                text.remove(i);
        }
    }
    
    public static void deleteNotWords (List<List<String>> text){ // menghapus kata yang <= 2 huruf
        List<List<String>> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            for (int j = 0; j < text.get(i).size(); j++){
                String sentence = text.get(i).get(j);
                if (!sentence.matches(".*[a-zA-Z]+.*")||sentence.length() < 2 ||sentence.isEmpty())
                    
                    text.get(i).remove(j);
            }
        }
    }
    
    public static void deleteNumber (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\d{1}\\s\\.|\\d{1}\\s\\)|\\;|\\d{1}\\.)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\d{1}\\s\\.|\\d{1}\\s\\)|\\d{1}\\.)", "");
                sentence = sentence.replaceAll("(\\;)", "");
                sentence = sentence.trim();
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void trimSentences (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            
            sentence = sentence.trim();
            
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void addNull (List<List<String>> text){
        List<List<String>> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            List<String> sentence = Arrays.asList("null");
            if (text.get(i).isEmpty()){
                text.set(i, sentence);
            }
        }
    }
    
    
}
