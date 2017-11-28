/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasankhusus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 *
 * @author mochamadtry
 */
public class posProses {
    
    public static void posAcaraRapat (List text){
        deleteNoNeedAcaraRapat(text);
        deleteNumber(text);
    }
    
    public static void posSuratMasuk (List text){
        deleteNumber(text);
    }
    
    public static void posPenggantiAntarWaktu (List text){
        deleteNoNeedPAW(text);
        deleteNumber(text);
    }
    
    public static void deleteNoNeedAcaraRapat (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(Sekarang kami akan menanyakan|Rapat Paripurna hari ini adalah)(.*)")) 
            {
                sentence = sentence.replaceAll("Sekarang kami akan menanyakan.*", "");
                sentence = sentence.replaceAll(".*Rapat Paripurna hari ini adalah", "");
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
    
    public static void deleteNoNeedPAW (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(Selanjutnya)(.*)")) 
            {
                sentence = sentence.replaceAll("Selanjutnya.*", "");
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
    
    public static void deleteNumber (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\d{1}\\s\\.||\\;)(.*)")) 
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
}
