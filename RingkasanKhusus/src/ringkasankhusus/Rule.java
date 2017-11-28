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
public class Rule {
    
    
    public static void ruleTahunSidang (List text){
        deleteSpace(text);
        deleteColon(text);
    }
    
    public static void ruleMasaPersidangan (List text) {
        deleteSpace(text);
        deleteColon(text);
    }
    
    public static void ruleRapatKe (List text){
        deleteSpace(text);
        deleteColon(text);
    }
    
    public static void ruleJenisRapat (List text){
        deleteNoNeedJenisRapat(text);
        deleteColon(text);
        String a = text.get(0).toString().trim();
        text.clear();
        text.add(a); 
    }
    
    public static void ruleSifatRapat (List text){
        deleteSpace(text);
        deleteColon(text);
    }
    
    public static void ruleHariRapat (List text){
        deleteSpace(text);
        deleteColon(text);
    }
    
    public static void ruleTanggalRapat (List text){
        
    }
    
    public static void rulePukulBuka (List text){
        deleteSpace(text);
        deleteColon(text);
        deletePageNumber(text);
    }
    
    public static void rulePukulTutup (List text){
        deleteSpace(text);
        deleteColon(text);
        deletePageNumber(text);
    }
    
    public static void ruleTempatRapat (List text){
        List<String> text_ = new ArrayList();
        deleteColon(text);
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
        //text.add(a);
    }
    
    public static void ruleKotaRapat (List text){
        String a = text.get(0).toString().trim();
        text.clear();
        text.add(a);
    }
    
    public static void ruleAcara (List text) throws IOException{
        List<String> text_ = new ArrayList();
        deleteColon(text);
        replaceTypo(text);
        deleteParenthesis(text);
        replacePAW(text);
        deleteNumber(text);
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void ruleAcaraIsi (List text) throws IOException{
        List<String> text_ = new ArrayList();
        replaceWithDPR(text);
        replaceTypo(text);
        deleteColon(text);
        deleteNoNeedAcaraRapat(text);
        deleteNotWord(text);
        deleteQuotation(text);
        deleteNumber(text);
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void ruleKetuaRapat (List text){
        List<String> text_ = new ArrayList();
        deleteColon(text);
        deleteParenthesis(text);
        deleteBidang(text);
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void rulePendampingKetua (List text){
        List<String> text_ = new ArrayList();
        deleteParenthesis(text);
        deleteBidang(text);
        deleteNotWord(text);
        deletePageNumber(text);
        deleteNumber(text);
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void ruleSekretarisRapat (List text) throws IOException{
        List<String> text_ = new ArrayList();
        deleteParenthesis(text);
        //replaceWithSekjen(text); //pindahin ke kamus aja
        replaceTypo(text);
        deletePageNumber(text);
        
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void rulePendampingSekretaris (List text){
        List<String> text_ = new ArrayList();
        deleteParenthesis(text);
        deleteNotWord(text);        
        deletePageNumber(text);
        deleteNumber(text);
        deleteNumber1(text);
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void ruleAnggotaHadir (List text){
        List<String> text_ = new ArrayList();
        
        deletePageNumber(text);
        
        
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void rulePucukSuratMasuk (List text){
        List<String> text_ = new ArrayList();
        //deleteBidang(text);
        deleteNoNeed(text);
        deleteNotWord(text);
        deleteNoNeedPucukSurat(text);
        deletePageNumber(text);
        deleteNumber(text);
        posProsesPucukSurat(text);
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void rulePenggantiAntarWaktu (List text){
        List<String> text_ = new ArrayList();
        deleteNoNeed(text);
        deleteColon(text);
        deleteNoNeedPAW(text);
        deleteNotWord(text);
        deletePageNumber(text);
        replaceKeputusanPresiden(text);
        deleteNumber(text);
        //deleteNumber(text);
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void ruleBiroPersidangan (List text){
        List<String> text_ = new ArrayList();
        
        deletePageNumber(text);
        deleteNumber(text);
        
        for (int i = 0; i < text.size(); i++){
            String a = text.get(i).toString().trim();
            text_.add(a);
        }
        text.clear();
        for (int i = 0; i < text_.size(); i++){
            text.add(text_.get(i));
        }
    }
    
    public static void ruleKesimpulan (List text){
        deleteNotWord(text);
    }
    
    public static String intToString (int num){
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "satu");
        map.put(2, "dua");
        map.put(3, "tiga");
        map.put(4, "empat");
        map.put(5, "lima");
        map.put(6, "enam");
        map.put(7, "tujuh"); 
        map.put(8, "delapan");
        map.put(9, "sembilan");
        map.put(10, "sepuluh");
        map.put(11, "sebelas");
        map.put(12, "dua belas");
        map.put(13, "tiga belas");
        map.put(14, "empat belas");
        map.put(15, "lima belas");
        return map.get(num);
    }
    
    public static String intToInt(int num){
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(5, "5");
        map.put(6, "6");
        map.put(7, "7"); 
        map.put(8, "8");
        map.put(9, "9");
        map.put(10, "10");
        map.put(11, "11");
        map.put(12, "12");
        map.put(13, "13");
        map.put(14, "14");
        map.put(15, "15");
        return map.get(num);
    }
    
    public static Integer strToInt (String str){
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("1.", 1);
        map.put("2.", 2);
        map.put("3.", 3);
        map.put("4.", 4);
        map.put("5.", 5);
        map.put("6.", 6);
        map.put("7.", 7); 
        map.put("8.", 8);
        map.put("9.", 9);
        map.put("10.", 10);
        map.put("Pertama", 1);
        map.put("Kedua", 2); 
        map.put("Ketiga", 3);
        map.put("pertama", 1);
        map.put("kedua", 2); 
        map.put("ketiga", 3);
        return map.get(str);
    }
  
    public static void deleteSpace (List text){ //Menghapus spasi didalam satu suku kata
        //System.out.println("debug "+ text);
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
        //System.out.println("debug proses"+ processed);
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void processPembicara (List text){
        int j = 0; boolean isFind = false; 
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            j = 0; isFind = false; 
            String sentence = text.get(i).toString();
            boolean match;
            List<String> daftarPola = Arrays.asList(".*(KETUA RAPAT:).*", ".*(F-[A-Z\\s]+\\(.*\\).*\\:).*", ".*(KETUA.*\\(.*\\)\\:).*");
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
    
    public static void deleteColon (List text){ //menghapus titik dua 
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
    
    public static void deleteTandaBaca (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\;)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\d{1,2}\\.)", "");
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
            if (match = sentence.matches("(.*)(\\d{1}\\.|\\d{1}\\))(.*)")) 
            {
                sentence = sentence.replaceAll("(\\d{1}\\.|\\d{1}\\))", "");
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
    
    public static void deleteNumber1 (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\d{1})(.*)")) 
            {
                sentence = sentence.replaceAll("(\\d{1})", "");
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
    
    public static void deleteQuotation (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(\\“|\\”)(.*)")) 
            {
                sentence = sentence.replaceAll("(\\“)", "");
                sentence = sentence.replaceAll("(\\”)", "");
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
            if (match = sentence.matches("(.*)(bidang|Bidang|BIdang|Sidang dewan|sidang dewan)(.*)")) 
            {
                sentence = sentence.replaceAll("(bidang|Bidang|BIdang|Sidang dewan|sidang dewan)(.*)", "");
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
    
    public static void deleteNoNeedJenisRapat (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(DPR RI)(.*)")) 
            {
                sentence = sentence.replaceAll("(DPR RI)", "");
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
                //sentence = sentence.replaceAll("(selanjutnya|Selanjutnya)(.*)", "");
                sentence = sentence.replaceAll("(Yang terhormat)(.*)", "");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void deleteNoNeedAcaraRapat (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(acara Rapat Paripurna hari ini|acara rapat paripurna hari ini|Acara Rapat Paripurna hari ini|acara rapat Paripurna hari ini|maka Acara Rapat  Paripurna hari ini adalah|Rapat Paripurna hari ini adalah)(.*)")) 
            {
                sentence = sentence.replaceAll("(.*)(acara Rapat Paripurna hari ini adalah|acara rapat paripurna hari ini adalah|Acara Rapat Paripurna hari ini|acara rapat Paripurna hari ini|maka Acara Rapat  Paripurna hari ini adalah|Rapat Paripurna hari ini adalah)", "");
                //sentence = sentence.replaceAll("(selanjutnya|Selanjutnya)(.*)", "");
            }
            if (match = sentence.matches("(.*)(Sekarang kami akan menanyakan|Sidang Dewan yang terhormat)(.*)")) 
            {
                sentence = sentence.replaceAll("(Sekarang kami akan menanyakan|Sidang Dewan yang terhormat)(.*)", "");
                //sentence = sentence.replaceAll("(selanjutnya|Selanjutnya)(.*)", "");
            }
            
            if (match = sentence.matches("(.*)(Pertama|Yang Pertama|yang pertama|pertama|Kedua|kedua|Yang kedua|ketiga|Ketiga|Yang ketiga|yang ketiga|Sidang Dewan yang terhormat)(.*)")) 
            {
                sentence = sentence.replaceAll("(.*)(Pertama|Yang Pertama|yang pertama|pertama|Kedua|kedua|Yang kedua|ketiga|Ketiga|Yang ketiga|yang ketiga)", "");
                //sentence = sentence.replaceAll("(selanjutnya|Selanjutnya)(.*)", "");
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
    
    public static void deleteNoNeedKesimpulan (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(yang didahului)(.*)")) 
            {
                sentence = sentence.replaceAll("yang didahului.*", "");
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
    
    public static void posProsesPucukSurat (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            String str = null; 
            boolean match;
            if (match = sentence.matches("(.*)(Untuk surat-surat)(.*)")) 
            {
                str = sentence.replaceAll("(.*)(Untuk surat-surat)", "Untuk surat-surat");
                sentence = sentence.replaceAll("(Untuk surat-surat)(.*)", "");
            }
            processed.add(sentence);
            if(match){
                processed.add(str);
            }
            System.out.println("str : " + str);
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
    
    public static void replacePAW (List text){
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            boolean match;
            if (match = sentence.matches("(.*)(PAW)(.*)")) 
            {
                sentence = sentence.replaceAll("(PAW)", "Pengganti Antar Waktu");
                //sentence = sentence.replaceAll("(Republik Indonesia)", "RI");
            }
            processed.add(sentence);
            //System.out.println(processed);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
          text.add(processed.get(i));
        }
    }
    
    public static void replaceTypo (List text) throws IOException{
        IOFile ref = new IOFile("referensi.txt");
        IOFile system = new IOFile("data.txt");
        List isiFileRef = ref.loadFile();
        List isiFileSys = system.loadFile();
        List<String> processed = new ArrayList(); 
        for (int i = 0; i < text.size(); i++ ){
            String sentence = text.get(i).toString();
            for (int j = 0; j < isiFileRef.size(); j++){
                String sys = isiFileSys.get(j).toString();
                //System.out.println(sys);
                //System.out.println(sentence);
                boolean match; 
                if (match = sentence.matches("(.*)"+ Pattern.quote(sys)+"(.*)")){
                    System.out.println("masuksini yang typo" + Pattern.quote(sys));
                    System.out.println(sys);
                    sentence = sentence.replaceAll(Pattern.quote(sys), isiFileRef.get(j).toString());
                }
            }
            processed.add(sentence);
        }
        text.clear();
        for (int i = 0; i < processed.size(); i++){
            text.add(processed.get(i));
        }
    }
}
