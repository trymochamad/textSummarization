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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author mochamadtry
 */
public class PatternRegex {
    public static List<String> isiFile = new ArrayList();
    public static List<String> bagianAwal = new ArrayList();
    public static List<String> bagianIsi = new ArrayList();
    
    
    public PraProses pp;
    public Rule rule;
    public IOFile file; 
    
    public PatternRegex(){
        pp = new PraProses();
        rule = new Rule();
        file = new IOFile(); 
    }
    
    public List patternTahunSidang(List isiFile, boolean ketemu){
        List<String> tahunSidang = new ArrayList();
        int i = 0; 
        //System.out.println(isiFile);
        while(i<isiFile.size() && ketemu == false){
            Pattern pattern = Pattern.compile("(.*)(Tahun Sidang)(.*)");
            String line = isiFile.get(i).toString();
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                ketemu = true; 
//                System.out.println("group 1: " + matcher.group(1));
//                System.out.println("group 2: " + matcher.group(2));
//                System.out.println("group 3: " + matcher.group(3));
//                System.out.println("group 4: " + matcher.group(4));
                if(ketemu)
                    tahunSidang.add(matcher.group(3));
            }
            ++i;
        }  
        if (!ketemu)
            System.out.println("tidak ditemukan");
//        PraProses.deleteSpace(tahunSidang);
//        PraProses.deleteColon(tahunSidang);
        Rule.ruleTahunSidang(tahunSidang);
        System.out.println("tahun sidang : " + tahunSidang);
        return tahunSidang;
    }
    
    public void regexTahunSidang (Notulen not){
        List<String> tahunPersidangan = new ArrayList();
        List<String> daftarPola = Arrays.asList(".*Tahun Sidang(.*)");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getBagianAwal().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getBagianAwal().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        tahunPersidangan.add(matcher.group(1));
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleTahunSidang(tahunPersidangan);
        not.setTahunSidang(tahunPersidangan);
    }
      
    public void regexMasaSidang (Notulen not){
        List<String> masaSidang = new ArrayList();
        List<String> daftarPola = Arrays.asList(".*Masa Persidangan(.*)");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getBagianAwal().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getBagianAwal().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        masaSidang.add(matcher.group(1));
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleMasaPersidangan(masaSidang);
        //System.out.println("masa sidang terbaru : " + masaSidang); 
        not.setMasaSidang(masaSidang);
    }
    
    public void regexUrutanRapat (Notulen not){
        List<String> urutanRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Rapat ke-|Rapat ke|Rapat Ke-|Rapat Ke)(.*)");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getBagianAwal().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getBagianAwal().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        urutanRapat.add(matcher.group(3));
                }
                ++i;
            }  
            j++;
        } 
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleRapatKe(urutanRapat); 
        not.setUrutanRapat(urutanRapat);
    }
     
    public void regexJenisRapat (Notulen not){
        List<String> valueJenisRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Jenis Rapat|Jenis rapat|JENIS RAPAT)(.*)");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getBagianAwal().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getSentences().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        valueJenisRapat.add(matcher.group(3));
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleJenisRapat(valueJenisRapat);
        not.setJenisRapat(valueJenisRapat);
    }
    
    public void regexSifatRapat (Notulen not){
        List<String> valueSifatRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Sifat Rapat)(.*)");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getBagianAwal().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getBagianAwal().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        valueSifatRapat.add(matcher.group(3));
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleSifatRapat(valueSifatRapat);
        not.setSifatRapat(valueSifatRapat);
    }
    
    public void regexHariRapat (Notulen not){
        List<String> valueHariRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Hari, tanggal |Hari, Tanggal|Hari , tanggal|Hari,tanggal|Hari,Tanggal)(.*)(,+\\s)(.*)");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getBagianAwal().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getBagianAwal().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        valueHariRapat.add(matcher.group(3));
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleHariRapat(valueHariRapat);
        not.setHariRapat(valueHariRapat);
    }
    
    public void regexTanggalRapat (Notulen not){
        List<String> valueTanggalRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Hari, tanggal |Hari, Tanggal|Hari , tanggal|Hari,tanggal|Hari,Tanggal)(.*)(,+\\s)(.*)");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getBagianAwal().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getBagianAwal().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        valueTanggalRapat.add(matcher.group(5));
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleTanggalRapat(valueTanggalRapat);
        not.setTanggalRapat(valueTanggalRapat);
    }
    
    public void regexRapatDibuka (Notulen not){
        List<String> valueRapatDibuka = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(RAPAT DIBUKA PUKUL+\\s+:|RAPAT DIBUKA PUKUL+\\s|RAPAT DIBUKA PUKUL:|RAPAT D IBUKA PUKUL)(.*)(\\s+W IB|\\s+WIB|\\s+WI B)(.*)", "(.*)(Waktu|waktu|w a k t u|W a k t u)(.*)(Pukul|pukul)(.*)(WIB|WI B|W I B)(.*)");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1 && isFind == false){
            while(i<not.getBagianIsi().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getBagianIsi().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    System.out.println("masuk sini A");
                    isFind = true; 
                    if(j == 0){
                        valueRapatDibuka.add(matcher.group(3));
                    }
                    else if (j ==1){
                        valueRapatDibuka.add(matcher.group(5));
                    }
                }
                ++i;
            }  
            j++;
            i = 0; 
        }
        
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getBagianAwal().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getBagianAwal().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    System.out.println("masuk sini B");
                    isFind = true; 
                    if(j == 0){
                        valueRapatDibuka.add(matcher.group(3));
                    }
                    else if (j ==1){
                        valueRapatDibuka.add(matcher.group(5));
                    }
                }
                ++i;
            }  
            j++;
            i = 0; 
        }
        if (!isFind) {
            System.out.println("waktu dibuka tidak ditemukan");
        } 
        Rule.rulePukulBuka(valueRapatDibuka);
        not.setRapatDibuka(valueRapatDibuka);
    }
    
    public void regexRapatDitutup (Notulen not){
        List<String> valueRapatDitutup = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(RAPAT DITUTUP PUKUL+\\s+:|RAPAT DITUTUP PUKUL+\\s|RAPAT DITUTUP PUKUL:|RAPAT DiTUTUP’ PUKUL|RAPAT DITUTUP PADA PUKUL\\s+|RAPAT OITUTUP PUKUL)(.*)(\\s+WIB|\\s+WI B)(.*)");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getBagianIsi().size() && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getBagianIsi().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind){
                        valueRapatDitutup.add(matcher.group(3));
                    }
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.rulePukulTutup(valueRapatDitutup);
        not.setRapatDitutup(valueRapatDitutup);
    }
    
    public void regexTempatRapat (Notulen not){
         List<String> valueTempatRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(T e m p a t|Tempat)(.*)", "(.*)(Acara|A c a r a|ACARA)(.*)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getBagianAwal().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternBatas = Pattern.compile(daftarPola.get(1));
                String teks = not.getBagianAwal().get(i).toString();
                String teksBatas = not.getBagianAwal().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherBatas = patternBatas.matcher(teksBatas);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind){
                        valueTempatRapat.add(matcher.group(3));
                    }
                }
                if(matcherBatas.find() && isFind == true) {
                    isBatas = true;
                } else if (isFind == true){
                    valueTempatRapat.add(teksBatas);
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleTempatRapat(valueTempatRapat);
        not.setTempatRapat(valueTempatRapat);
    }
    
    public void regexKotaRapat (Notulen not){
         List<String> valueKotaRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(T e m p a t|Tempat)(.*)", "(.*)(\\–|\\-)(.*)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getBagianAwal().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternBatas = Pattern.compile(daftarPola.get(1));
                String teks = not.getBagianAwal().get(i).toString();
                String teksBatas = not.getBagianAwal().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherBatas = patternBatas.matcher(teksBatas);
                if (matcher.find()) {
                    isFind = true; 
                }
                if(matcherBatas.find() && isFind == true) {
                    isBatas = true;
                    valueKotaRapat.add(matcherBatas.group(3));
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleKotaRapat(valueKotaRapat);
        not.setKotaRapat(valueKotaRapat);
    }
    
    public void regexAcara (Notulen not) throws IOException{
        List<String> valueAcara = new ArrayList();
        List<String> valueAcaraRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(A c a r a|Acara)(.*)", "(.*)(Ketua Rapat)(.*)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getBagianAwal().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternBatas = Pattern.compile(daftarPola.get(1));
                String teks = not.getBagianAwal().get(i).toString();
                String teksBatas = not.getBagianAwal().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherBatas = patternBatas.matcher(teksBatas);
                if (matcher.find()) {
                    //System.out.println("masuk sini haha");
                    isFind = true; 
                    valueAcara.add(matcher.group(3));
                    //System.out.println(valueAcara);
                }
                if(matcherBatas.find() && isFind == true) {
                     isBatas = true;
                } else if (isFind == true){
                    valueAcara.add(teksBatas); 
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        else {
            int k = 0;
            StringBuilder teks = new StringBuilder();
            List<String> daftarAngka = new ArrayList();
            String valueRegex = null; boolean cekAngka = false;
            int iterator = 0; 
            while (k < valueAcara.size()-1){ 
                Pattern patternPotongan = Pattern.compile ("(.*)(\\d{1}\\.|Pertama|pertama|Kedua|kedua|Ketiga|ketiga|Keempat|keempat|Kelima|kelima|Keenam|keenam|Ketujuh|ketujuh|Kedelapan|kedelapan|Kesembilan|kesembilan)(.*)");
                Matcher matcher = patternPotongan.matcher(valueAcara.get(k+1));
                if (matcher.find()){
                    cekAngka = false;
                    valueRegex = matcher.group(2); 
                    iterator = 0; 
                    while (iterator < daftarAngka.size() && !daftarAngka.isEmpty() && cekAngka ==false){
                        String d = daftarAngka.get(iterator);
                        if (daftarAngka.get(iterator).equals(valueRegex)){
                           cekAngka = true; 
                        }
                        iterator++;
                    }
                }
                matcher = patternPotongan.matcher(valueAcara.get(k+1));
                if (matcher.find() && cekAngka == false){
                    String isi = valueAcara.get(k);
                    teks.append(isi + " ");
                    valueAcaraRapat.add(teks.toString());
                    teks.delete(0, teks.length());
                    valueRegex = matcher.group(2);
                    daftarAngka.add(valueRegex);
                    System.out.println("value " + daftarAngka); 
                }
                else{
                   String isi = valueAcara.get(k);
                   teks.append(isi + " "); 
                }
                k++;
            }
            teks.append(valueAcara.get(valueAcara.size()-1));
            valueAcaraRapat.add(teks.toString());
        }
        Rule.ruleAcara(valueAcaraRapat);
        not.setAcara(valueAcaraRapat);
    }
    
    public void regexAcaraRapat (Notulen not) throws IOException{
        List<String> valueAcara = new ArrayList();
        List<String> valueAcaraRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(acara rapat paripurna hari ini|Acara Rapat Paripurna hari ini|acara Rapat Paripurna hari ini adalah|maka acara rapat|maka Acara Rapat|Rapat Paripurna hari ini adalah:)(.*)", "(.*)(apakah acara|Apakah Acara|Apakah acara|disetujui)(.*)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; 
        int init = 0; 
        int med = 0; 
        int finalize = 0; 
        while (i < not.getSentences().size() && isFind== false){
            Pattern pattern = Pattern.compile(daftarPola.get(0));
            String lines = not.getSentences().get(i).toString();
            Matcher matcher = pattern.matcher(lines);
            if (matcher.find()){
                isFind = true; 
            }
            i++;
            med = i; 
        }
        while (init < not.getSentences().size() && isFind && isBatas == false){
            Pattern patternNext = Pattern.compile(daftarPola.get(1));
            String lineNext = not.getSentences().get(init).toString(); 
            Matcher matcherNext = patternNext.matcher(lineNext);
            if (matcherNext.find()){
                isBatas = true; 
            }
            init++; 
            finalize = init;
        }
        if(isFind && isBatas){
            while (med < finalize-1){
                String lines = not.getSentences().get(med-1).toString(); 
                valueAcara.add(lines);
                med++;
            }
            valueAcara.add(not.getSentences().get(med-1).toString());
        }
        System.out.println("acara " + valueAcara);
        if (!isBatas)
            System.out.println("tidak ditemukan");
         else {
            int j = 0;
            StringBuilder teks = new StringBuilder();
            List<String> daftarAngka = new ArrayList();
            String valueRegex = null; boolean cekAngka = false;
            int iterator = 1; 
            while (j < valueAcara.size()-1){ 
                Pattern patternPotongan = Pattern.compile ("(.*)(\\d{1}\\.|Pertama|pertama|Kedua|kedua|Ketiga|ketiga|Keempat|keempat|Kelima|kelima|Keenam|keenam|Ketujuh|ketujuh|Kedelapan|kedelapan|Kesembilan|kesembilan)(.*)");
                Matcher matcher = patternPotongan.matcher(valueAcara.get(j+1));
                if (matcher.find()){
                    cekAngka = false;
                    valueRegex = matcher.group(2); 
                    int strToInt = Rule.strToInt(valueRegex);
                   
                    iterator = 1; 
                    System.out.println("daftar "+daftarAngka);
                    while (iterator <= daftarAngka.size() && !daftarAngka.isEmpty() && cekAngka ==false){
                        //String d = daftarAngka.get(iterator);
                         int strToInteger = Rule.strToInt(daftarAngka.get(iterator-1));
                         strToInteger++;
                         System.out.println("str to int "+strToInt + strToInteger);
                        if (daftarAngka.get(iterator-1).equals(valueRegex)){//|| !(strToInteger == strToInt)){
                            //if (!(strToInteger == strToInt)){
                                cekAngka = true; 
                            //}
                        }
                        iterator++;
                    }
                }
                matcher = patternPotongan.matcher(valueAcara.get(j+1));
                if (matcher.find() && cekAngka == false){
                    String isi = valueAcara.get(j);
                    teks.append(isi + " ");
                    valueAcaraRapat.add(teks.toString());
                    teks.delete(0, teks.length());
                    valueRegex = matcher.group(2);
                    daftarAngka.add(valueRegex);
                    System.out.println("value " + daftarAngka); 
                }
                else{
                   String isi = valueAcara.get(j);
                   teks.append(isi + " "); 
                }
                j++;
            }
            teks.append(valueAcara.get(valueAcara.size()-1));
            valueAcaraRapat.add(teks.toString());
        }
        Rule.ruleAcaraIsi(valueAcaraRapat);
        not.setAcaraRapat(valueAcaraRapat);
    }
    
    public void patternAcaraRapat (Notulen not) throws IOException{
        List<String> agendaRapat = new ArrayList(); //penampung untuk data kotor acara rapat
        List<String> acaraRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Dengan disetujuinya permintaan Komisi X tersebut, maka acara Rapat Paripurna Dewan hari ini|Dengan disetujuinya permintaan Komisi X tersebut, maka acara Rapat Paripurna hari ini)(.*)", "(.*)(acara rapat paripurna hari ini adalah|acara rapat paripurna hari ini|Acara Rapat Paripurna hari ini|acara Rapat Paripurna Dewan hari ini|acara Rapat Paripurna hari ini adalah)(.*)", "(.*)(RAPAT: SETUJU|Sebelum memulai acara perlu kami sampaikan|Sidang Dewan yang terhormat|Surat yang ditandatangani oleh|Selanjutnya surat tersebut|Berkaitan dengan itu|Bapak, Ibu sekalian,|Itulah)(.*)");
        boolean isFind = false; boolean isEnd = false;
        int i = 0; int sentencesFind = 0; int j = 0; 
        while (j<daftarPola.size()-1 && isFind == false){
            i = 0; 
            while (i < not.getBagianIsi().size()-1 && isFind == false){ 
                //System.out.println("masuk 2");
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String line = not.getBagianIsi().get(i).toString();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()){
                    isFind = true; 
                }
                sentencesFind = i; 
                i++; 
            }
            j++;
        }
        System.out.println(sentencesFind);
        
        if (isFind){
            int iterator = 1; 
            while (sentencesFind < not.getBagianIsi().size()-1 && isEnd == false){
                Pattern pattern = Pattern.compile(daftarPola.get(2));
                String teks = not.getBagianIsi().get(sentencesFind+1).toString();
                Matcher matcher = pattern.matcher(teks);
                if (matcher.find()){
                    isEnd = true; 
                    if (iterator ==1){
                        agendaRapat.add(teks); 
                    }
                }
                else {
                   agendaRapat.add(teks); 
                   iterator++;
                }
                
                sentencesFind++;
            }
            
        }
        
        
        //parse dan masukan ke acara Rapat
        if (agendaRapat.isEmpty())
            System.out.println("agenda rapat tidak ditemukan");
        
        posProses.posAcaraRapat(agendaRapat);
        System.out.println("rapat coy: " + agendaRapat);
//        Rule.restructuredAcaraRapat(agendaRapat);
//        Rule.ruleAcaraRapat(agendaRapat);
//        not.setAcaraRapat(agendaRapat);
    }
    
    public void regexKetuaRapat (Notulen not){
        List<String> valueKetuaRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Ketua Rapat)(.*)", "(.*)(Didampingi|didampingi)(.*)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getBagianAwal().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternBatas = Pattern.compile(daftarPola.get(1));
                String teks = not.getBagianAwal().get(i).toString();
                String teksBatas = not.getBagianAwal().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherBatas = patternBatas.matcher(teksBatas);
                if (matcher.find()) {
                    isFind = true; 
                    if(isFind){
                        valueKetuaRapat.add(matcher.group(3));
                    }
                }
                if(matcherBatas.find() && isFind == true) {
                    isBatas = true;
                } else if (isFind == true){
                    valueKetuaRapat.add(teksBatas);
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleKetuaRapat(valueKetuaRapat);
        not.setKetuaRapat(valueKetuaRapat);
    }
    
    public void regexPendampingKetua (Notulen not){
        List<String> valuePendamping = new ArrayList();
        List<String> valuePendampingKetua = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Didampingi)(.*)", "(.*)(Sekretaris Rapat)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getBagianAwal().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternBatas = Pattern.compile(daftarPola.get(1));
                String teks = not.getBagianAwal().get(i).toString();
                String teksBatas = not.getBagianAwal().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherBatas = patternBatas.matcher(teksBatas);
                while (matcher.find()) {
                    //System.out.println("masuk 1");
                    isFind = true; 
                    if(isFind){
                        valuePendamping.add(matcher.group(3));
                    }
                }
                if(matcherBatas.find() && isFind == true) {
                    //System.out.println("masuk 2");
                    isBatas = true;
                } else if (isFind == true){
                    //System.out.println("masuk 3");
                    valuePendamping.add(teksBatas);
                }
                ++i;
            }  
            j++;
        }
        System.out.println(valuePendamping);
        if (!isFind) {
            System.out.println("tidak ditemukan");
        }
        else {
            int k = 0;
            StringBuilder teks = new StringBuilder();
            while (k < valuePendamping.size()-1){ 
                Pattern patternPotongan = Pattern.compile ("(.*)(\\d+)(\\.)(.*)");
                Matcher matcher = patternPotongan.matcher(valuePendamping.get(k+1));
                if (!matcher.find()){
                   String isi = valuePendamping.get(k);
                   teks.append(isi + " : " + valuePendamping.get(k+1));
                }
                else{
                    //System.out.println ("masuk kesini");
                    valuePendampingKetua.add(teks.toString());
                    teks.delete(0, teks.length());
                   
                }
                k++;
            }
            valuePendampingKetua.add(teks.toString());
            
            
        }
        Rule.rulePendampingKetua(valuePendamping);
        not.setPendampingKetua(valuePendamping);
    }
    
    public void regexSekretarisRapat (Notulen not) throws IOException{
        List<String> valueSekretarisRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Sekretaris Rapat)(\\s+:+\\s|:|\\s+:)(.*)", "(.*)(H  a  d  i  r|H a d i r|Hadir|H a d I r)(.*)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getBagianAwal().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternBatas = Pattern.compile(daftarPola.get(1));
                String teks = not.getBagianAwal().get(i).toString();
                String teksBatas = not.getBagianAwal().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherBatas = patternBatas.matcher(teksBatas);
                while (matcher.find()) {
                    //System.out.println("masuk 1");
                    isFind = true; 
                    if(isFind){
                        valueSekretarisRapat.add(matcher.group(4));
                    }
                }
                if(matcherBatas.find() && isFind == true) {
                    //System.out.println("masuk 2");
                    isBatas = true;
                } else if (isFind == true){
                    //System.out.println("masuk 3");
                    valueSekretarisRapat.add(teksBatas);
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleSekretarisRapat(valueSekretarisRapat);
        not.setSekretarisRapat(valueSekretarisRapat);
    }
    
    public void regexPendampingSekretaris (Notulen not){
        List<String> valuePendamping = new ArrayList();
        List<String> valuePendampingSekretaris = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(SEKRETARIAT JENDERAL DPR RI)(.*)", "(.*)(DAFTAR HADIR ANGGOTA DPR RI|DAFTAR HADIR ANGGOTA DPRRI|DAFTAR HADIR ANGGOTA DPR Rl|DAFTAR HADIR ANGGOTA DPR Ri)(.*)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getBagianAwal().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternBatas = Pattern.compile(daftarPola.get(1));
                String teks = not.getBagianAwal().get(i).toString();
                String teksBatas = not.getBagianAwal().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherBatas = patternBatas.matcher(teksBatas);
                while (matcher.find()) {
                    isFind = true; 
                }
                if(matcherBatas.find() && isFind == true) {
                    isBatas = true;
                } else if (isFind == true){
                    valuePendampingSekretaris.add(teksBatas);
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        }
//        else {
//            int k = 0;
//            StringBuilder teks = new StringBuilder();
//            while (k < valuePendamping.size()-1){ 
//                Pattern patternPotongan = Pattern.compile ("(.*)(\\d+)(\\.)(.*)");
//                Matcher matcher = patternPotongan.matcher(valuePendamping.get(k+1));
//                if (!matcher.find()){
//                   String isi = valuePendamping.get(k);
//                   teks.append(isi + " : " + valuePendamping.get(k+1));
//                }
//                else{
//                    System.out.println ("masuk kesini");
//                    valuePendampingSekretaris.add(teks.toString());
//                    teks.delete(0, teks.length());
//                   
//                }
//                k++;
//            }
//            valuePendampingSekretaris.add(teks.toString());
//            
//            
//        }
        Rule.rulePendampingSekretaris(valuePendampingSekretaris);
        not.setPendampingSekretaris(valuePendampingSekretaris);
    }
    
    public void regexAnggotaHadir (Notulen not){
        List<String> valueAnggotaHadir = new ArrayList();
        List<String> daftarPola = Arrays.asList("((.*)(H  a  d  i  r|H a d i r|Hadir|H a d I r)(\\s+:+\\s)(ANGGOTA DPR RI)(.*))|((.*)(H  a  d  i  r|H a d i r|Hadir)(\\s+:+\\s)(ANGGOTA DPR RI)(.*)(dari |DARI |d a r i ))", "(\\s+)(.*)(dari |DARI |d a r i )(.*)(orang)(.*)", "(.*)(dari |DARI |d a r i )(.*)(orang)(.*)");
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getBagianAwal().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternNext = Pattern.compile(daftarPola.get(1));
                Pattern patternBatas = Pattern.compile(daftarPola.get(2));
                String teks = not.getBagianAwal().get(i).toString();
                String teksBatas = not.getBagianAwal().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherNext = patternNext.matcher(teks);
                Matcher matcherBatas = patternBatas.matcher(teksBatas);
                while (matcher.find()) {
                    //System.out.println("masuk 1");
                    isFind = true; 
                }
                if(matcherNext.find() && isFind == true) {
                    //System.out.println("masuk 2");
                    isBatas = true;
                    valueAnggotaHadir.add(matcher.group(5));
                    valueAnggotaHadir.add(matcherNext.group(3));
                } else if (matcherBatas.find() && isFind == true){
                    isBatas = true;
                    //System.out.println("masuk 3");
                    valueAnggotaHadir.add(matcherBatas.group(1)); 
                    valueAnggotaHadir.add(matcherBatas.group(3)); 
                }
                ++i;
            }  
            j++;
        }
        if (!isFind && !isBatas) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleAnggotaHadir(valueAnggotaHadir);
        not.setAnggotaHadir(valueAnggotaHadir);
    }
    
    public void regexPucukSuratMasuk (Notulen not){
        List<String> valueSurat = new ArrayList();
        List<String> valueSuratMasuk = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(surat masuk yaitu|pucuk surat yaitu|sepucuk surat|pucuk surat|\\d+\\s+surat dari Presiden|menerima\\s+\\d+\\s+pucuk surat)(.*)", "(.*)(1|pertama|Pertama|yaitu)(.*)", "(.*)(Sidang dewan yang kami hormati|Sidang Dewan yang kami hormati|Dewan yang kami hormati|Yang terhormat|Interupsi Pimpinan|marilah kita memasuki acara Rapat Paripurna|Hadirin Sidang yang terhormat|Marilah kita memasuki)(.*)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getSentences().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternNext = Pattern.compile(daftarPola.get(1));
                Pattern patternBatas = Pattern.compile(daftarPola.get(2));
                String teks = not.getSentences().get(i).toString();
                String teksBatas = not.getSentences().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherNext = patternNext.matcher(teksBatas);
                Matcher matcherBatas = patternBatas.matcher(teks);
                if (matcher.find() && matcherNext.find()) {
                    isFind = true;
                }
                if(isFind) {
                    //System.out.println("masuk 2");
                    valueSurat.add(teksBatas);
                } 
                if (matcherBatas.find() && isFind){
                    //System.out.println("masuk 3");
                    isBatas = true;
                }
                ++i;
            }  
            j++;
        }
        System.out.println(valueSurat);
        if (!isFind) {
            System.out.println("tidak ditemukan");
        }
        else {
            int k = 0;
            StringBuilder teks = new StringBuilder();
            while (k < valueSurat.size()-1){ 
                Pattern patternPotongan = Pattern.compile ("(.*)(\\d+\\.|Pertama|pertama|Kedua|kedua|Ketiga|ketiga|Keempat|keempat|Kelima|kelima|Keenam|keenam|Ketujuh|ketujuh|Kedelapan|kedelapan|Kesembilan|kesembilan)(.*)");
                Matcher matcher = patternPotongan.matcher(valueSurat.get(k+1));
                if (matcher.find()){
                   String isi = valueSurat.get(k);
                   teks.append(isi + " ");
                   valueSuratMasuk.add(teks.toString());
                   teks.delete(0, teks.length());
                }
                else{
                    String isi = valueSurat.get(k);
                    teks.append(isi + " "); 
                   
                }
                k++;
            }
            valueSuratMasuk.add(teks.toString());
            
            
        }
        Rule.rulePucukSuratMasuk(valueSuratMasuk);
        not.setPucukSuratMasuk(valueSuratMasuk);
    }
    
    public void patternPucukSuratMasuk (Notulen not){
        List<String> pucukSurat = new ArrayList(); //penampung untuk data kotor acara rapat
        List<String> daftarPola = Arrays.asList("(.*)(surat masuk yaitu|pucuk surat yaitu|sepucuk surat|pucuk surat|\\d+\\s+surat dari Presiden|menerima\\s+\\d+\\s+pucuk surat)(.*)", "(.*)(Sidang dewan yang kami hormati|Sidang Dewan yang kami hormati|Dewan yang kami hormati)(.*)" );
        boolean isFind = false; boolean isEnd = false;
        int i = 0; int sentencesFind = 0; int j = 0; 
        while (j<daftarPola.size()-1 && isFind == false){
            i = 0; 
            while (i < not.getBagianIsi().size()-1 && isFind == false){ 
                //System.out.println("masuk 2");
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String line = not.getBagianIsi().get(i).toString();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()){
                    isFind = true; 
                }
                sentencesFind = i; 
                i++; 
            }
            j++;
        }
        System.out.println(sentencesFind);
        
        if (isFind){
            int iterator = 1; 
            while (sentencesFind < not.getBagianIsi().size()-1 && isEnd == false){
                Pattern pattern = Pattern.compile(daftarPola.get(1));
                String teks = not.getBagianIsi().get(sentencesFind+1).toString();
                Matcher matcher = pattern.matcher(teks);
                if (matcher.find()){
                    isEnd = true; 
                    if (iterator ==1){
                        pucukSurat.add(teks); 
                    }
                }
                else {
                   pucukSurat.add(teks); 
                   iterator++;
                }
                
                sentencesFind++;
            }
            
        }
        
        
        //parse dan masukan ke acara Rapat
        if (pucukSurat.isEmpty())
            System.out.println("pucuk surat tidak ditemukan");
        
        posProses.posSuratMasuk(pucukSurat);
        System.out.println("pucuk surat: " + pucukSurat);
    }
        
    public void regexSepucukSuratMasuk (Notulen not){
        List<String> valueSurat = new ArrayList();
        List<String> valueSuratMasuk = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(sepucuk surat)(.*)", "(.*)(1|pertama|)(.*)", "(.*)(Sidang dewan yang kami hormati|Sidang Dewan yang kami hormati|Yang terhormat|Interupsi Pimpinan)(.*)" );
        boolean isFind = false; boolean isBatas = false;
        int i = 0; int j = 0; 
        while (j<daftarPola.size()-1){
            while(i<not.getSentences().size() - 1 && isBatas == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                Pattern patternNext = Pattern.compile(daftarPola.get(1));
                Pattern patternBatas = Pattern.compile(daftarPola.get(2));
                String teks = not.getSentences().get(i).toString();
                String teksBatas = not.getSentences().get(i+1).toString();
                Matcher matcher = pattern.matcher(teks);
                Matcher matcherNext = patternNext.matcher(teksBatas);
                Matcher matcherBatas = patternBatas.matcher(teks);
                if (matcher.find() && matcherNext.find()) {
                   // System.out.println("masuk 1");
                    isFind = true;
                    valueSurat.add(teks);
                }
                if(isFind) {
                    //System.out.println("masuk 2");
                    valueSurat.add(teksBatas);
                } 
                if (matcherBatas.find() && isFind){
                    //System.out.println("masuk 3");
                    isBatas = true;
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        }
        else {
            int k = 0;
            StringBuilder teks = new StringBuilder();
            while (k < valueSurat.size()-1){ 
                Pattern patternPotongan = Pattern.compile ("(.*)(\\d+\\.|Pertama|pertama|Kedua|kedua|Ketiga|ketiga|Keempat|keempat|Kelima|kelima|Keenam|keenam|Ketujuh|ketujuh|Kedelapan|kedelapan|Kesembilan|kesembilan)(.*)");
                Matcher matcher = patternPotongan.matcher(valueSurat.get(k+1));
                if (!matcher.find()){
                   String isi = valueSurat.get(k);
                   teks.append(isi + " ");
                   valueSuratMasuk.add(teks.toString());
                   teks.delete(0, teks.length());
                }
                else{
                    String isi = valueSurat.get(k);
                    teks.append(isi + " "); 
                   
                }
                k++;
            }
            valueSuratMasuk.add(teks.toString());
            
            
        }
        //Rule.rulePucukSuratMasuk(valueSuratMasuk);
        not.setSepucukSuratMasuk(valueSuratMasuk);
    }
    
    public void regexPenggantiAntarWaktu(Notulen not){
        List<String> valueAntarWaktu = new ArrayList();
        List<String> valuePenggantiAntarWaktu = new ArrayList();
        boolean isFind = false;
        boolean isTanda = false;
        int initPattern = 0; 
        boolean isBatas = false;
        List<String> daftarPola = Arrays.asList("(.*)(melantik Anggota|Peresmian Pergantian|melaksanakan pelantikan Anggota|Peresmian Anggota Dewan|Peresmian Penggantian|upacara pelantikan Anggota Pengganti)(.*)", "(.*)(yaitu:|yaitu|Saudara)(.*)", "(.*)(Selanjutnya|Kami persilakan)(.*)");
        Pattern pattern = Pattern.compile(daftarPola.get(0));
        Pattern patternPotongan = Pattern.compile (daftarPola.get(1));
        Pattern patternBatas = Pattern.compile (daftarPola.get(2));
        int i = 0; 
        while(i<not.getSentences().size() - 1 && isFind == false){
            String lines = not.getSentences().get(i).toString();
            Matcher matcher = pattern.matcher(lines);
            if (matcher.find()) {
                isFind = true; 
            }
            ++i;
            initPattern = i; 
        } 
        //System.out.println(initPattern);
        while (initPattern < not.getSentences().size() && isBatas == false && isFind == true ){
            String lineNext = not.getSentences().get(initPattern).toString();
            Matcher matcherNext = patternPotongan.matcher(lineNext);
            if (matcherNext.find()){
                isTanda = true; 
                initPattern++;
                valueAntarWaktu.add(lineNext);
                //System.out.println(initPattern);
            }
            while(initPattern < not.getSentences().size() && isTanda == true && isBatas == false){
                String lineAdd = not.getSentences().get(initPattern).toString();
                Matcher matcherBatas = patternBatas.matcher(lineAdd);
                if (matcherBatas.find()){
                    isBatas = true; 
                    valueAntarWaktu.add(lineAdd);
                }else {
                    valueAntarWaktu.add(lineAdd);
                }
                initPattern++;
            }
            initPattern++;
        }
        if (!isFind)
            System.out.println("tidak ditemukan");
        else {
            int j = 0;
            StringBuilder teks = new StringBuilder();
            while (j < valueAntarWaktu.size()-1){ 
                Pattern patternProses = Pattern.compile ("(.*)(\\d+\\.|\\d+\\s+\\.)(.*)");
                Matcher matcher = patternProses.matcher(valueAntarWaktu.get(j+1));
                if (matcher.find()){
                    String isi = valueAntarWaktu.get(j);
                    teks.append(isi + " ");
                   
                    valuePenggantiAntarWaktu.add(teks.toString());
                    teks.delete(0, teks.length());
                }
                else{
                   String isi = valueAntarWaktu.get(j);
                   teks.append(isi + " "); 
                }
                j++;
            }
            teks.append(valueAntarWaktu.get(valueAntarWaktu.size()-1));
            valuePenggantiAntarWaktu.add(teks.toString());
        }
        Rule.rulePenggantiAntarWaktu(valuePenggantiAntarWaktu);
        not.setPenggantiAntarWaktu(valuePenggantiAntarWaktu);
    }
    
    public void patternPenggantiAntarWaktu (Notulen not){
        List<String> penggantiAntarWaktu = new ArrayList(); //penampung untuk data kotor acara rapat
        List<String> daftarPola = Arrays.asList("(.*)(melantik Anggota Pengganti Antar Waktu|Peresmian Pergantian Anggota Antar Waktu|melaksanakan pelantikan Anggota|Peresmian Anggota Dewan|Peresmian Penggantian Anggota Antar Waktu|upacara pelantikan Anggota Pengganti Antar Waktu)(.*)", "(.*)(Selanjutnya|Kami persilakan)(.*)");
        boolean isFind = false; boolean isEnd = false;
        int i = 0; int sentencesFind = 0; int j = 0; 
        while (j<daftarPola.size()-1 && isFind == false){
            i = 0; 
            while (i < not.getBagianIsi().size()-1 && isFind == false){ 
                //System.out.println("masuk 2");
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String line = not.getBagianIsi().get(i).toString();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()){
                    isFind = true; 
                }
                sentencesFind = i; 
                i++; 
            }
            j++;
        }
        System.out.println(sentencesFind);
        
        if (isFind){
            int iterator = 1; 
            while (sentencesFind < not.getBagianIsi().size()-1 && isEnd == false){
                Pattern pattern = Pattern.compile(daftarPola.get(1));
                String teks = not.getBagianIsi().get(sentencesFind+1).toString();
                Matcher matcher = pattern.matcher(teks);
                if (matcher.find()){
                    isEnd = true; 
                    penggantiAntarWaktu.add(teks);
                }
                else {
                   penggantiAntarWaktu.add(teks); 
                   iterator++;
                }
                
                sentencesFind++;
            }
            
        }
        
        
        //parse dan masukan ke acara Rapat
        if (penggantiAntarWaktu.isEmpty())
            System.out.println("pengganti antar waktu tidak ditemukan");
        
        posProses.posPenggantiAntarWaktu(penggantiAntarWaktu);
        System.out.println("pengganti antar waktu: " + penggantiAntarWaktu);
    }
    
    public void regexPenggantiAntarWaktuTambahan(Notulen not){
        // List yang akan berisi jumlah yang hadir saat rapat dan jumlah keseluruhan anggota yang seharusnya hadir
        List<String> valueAntarWaktu = new ArrayList();
        List<String> valuePenggantiAntarWaktu = new ArrayList();
        boolean isFind = false;
        boolean isTanda = false;
        int initPattern = 0; 
        boolean isBatas = false;
        List<String> daftarPola = Arrays.asList("(.*)(pengucapan sumpah/janji meresmikan)(.*)", "(.*)(pengangkatan antar waktu)(.*)", "(.*)(dan seterusnya)(.*)");
        Pattern pattern = Pattern.compile(daftarPola.get(0));
        Pattern patternPotongan = Pattern.compile (daftarPola.get(1));
        Pattern patternBatas = Pattern.compile (daftarPola.get(2));
        int i = 0; 
        while (i<not.getSentences().size()){
            while(i<not.getSentences().size() && isFind == false){
                String lines = not.getSentences().get(i).toString();
                Matcher matcher = pattern.matcher(lines);
                if (matcher.find()) {
                    isFind = true; 
                    //System.out.println("masukDua");
                }
                ++i;
                initPattern = i; 
            } 
            //System.out.println(initPattern);
            while (initPattern < not.getSentences().size() && isBatas == false && isFind == true ){
                //System.out.println("masukTiga");
                String lineNext = not.getSentences().get(initPattern).toString();
                Matcher matcherNext = patternPotongan.matcher(lineNext);
                if (matcherNext.find()){
                    //System.out.println("masukEmpat");
                    isTanda = true; 
                    initPattern++;
                    valueAntarWaktu.add(lineNext);
                    //System.out.println(initPattern);
                }
                while(initPattern < not.getSentences().size() && isTanda == true && isBatas == false){
                    //System.out.println("masukLima");
                    String lineAdd = not.getSentences().get(initPattern).toString();
                    Matcher matcherBatas = patternBatas.matcher(lineAdd);
                    if (matcherBatas.find()){
                        //System.out.println("masukEnam");
                        isBatas = true; 
                        valueAntarWaktu.add(lineAdd);
                    }else {
                        //System.out.println("masukTujuh");
                        valueAntarWaktu.add(lineAdd);
                    }
                    initPattern++;
                }
                initPattern++;
            }
            isFind = false; isTanda = false; isBatas = false;
            i = initPattern;
        }
        if (!valueAntarWaktu.isEmpty())
            isFind = true;
        if (!isFind)
            System.out.println("lala");
        else {
            int j = 0;
            StringBuilder teks = new StringBuilder();
            while (j < valueAntarWaktu.size()-1){ 
                Pattern patternProses = Pattern.compile ("(.*)(pengangkatan antar waktu)(.*)");
                Matcher matcher = patternProses.matcher(valueAntarWaktu.get(j));
                if (matcher.find()){
                    String isi = valueAntarWaktu.get(j);
                    //System.out.println("tidak");
                    teks.append(isi + " ");
                    valuePenggantiAntarWaktu.add(teks.toString());
                    teks.delete(0, teks.length());
                }
                else{
                   String isi = valueAntarWaktu.get(j);
                   teks.append(isi + " "); 
                }
                j++;
            }
            teks.append(valueAntarWaktu.get(valueAntarWaktu.size()-1));
            valuePenggantiAntarWaktu.add(teks.toString());
        }
        Rule.rulePenggantiAntarWaktu(valuePenggantiAntarWaktu);
        not.setPenggantiAntarWaktu(valuePenggantiAntarWaktu);
    }
    
    public void regexKesimpulan (Notulen not){
        List<String> valueKesimpulan = new ArrayList(); //antarWkatu diganti valueKesimpulan
        List<String> valueKesimpulanRapat = new ArrayList(); //pengganti diganti valueKesimpulanrAPAT
        List<String> kesimpulan = new ArrayList();
        boolean isFind = false;
        boolean isTanda = false;
        int initPattern = 0; 
        boolean isBatas = false;
        Pattern pattern = Pattern.compile("(.*)(memformulasikan kesepakatan)");//|telah disepakati)(.*)");
        Pattern patternPotongan = Pattern.compile ("(.*)(pertama)(.*)");
        Pattern patternBatas = Pattern.compile ("(.*)(formulasikan|Setuju)(.*)");
        int i = 0; 
        for (int c = 0; c < not.getAcaraRapat().size(); c++){
              kesimpulan.add(not.getAcaraRapat().get(c));
        }
        while(i<not.getSentences().size() && isFind == false){
            String lines = not.getSentences().get(i).toString();
            Matcher matcher = pattern.matcher(lines);
            if (matcher.find()) {
                isFind = true;
                CreatePDF.isKesimpulan = true;
            }
            ++i;
            initPattern = i; 
        } 
        while (initPattern < not.getSentences().size() && isBatas == false && isFind == true ){
            String lineNext = not.getSentences().get(initPattern).toString();
            Matcher matcherNext = patternPotongan.matcher(lineNext);
            if (matcherNext.find()){
                isTanda = true; 
                valueKesimpulan.add(lineNext);
                initPattern++;
            }
            while(initPattern < not.getSentences().size() && isTanda == true && isBatas == false){
                String lineAdd = not.getSentences().get(initPattern).toString();
                Matcher matcherBatas = patternBatas.matcher(lineAdd);
                if (matcherBatas.find()){
                    isBatas = true; 
                }else {
                    valueKesimpulan.add(lineAdd);
                }
                initPattern++;
            }
            initPattern++;
        }
        if (!isFind && !not.getAcaraRapat().isEmpty()){
            System.out.println("Formulasi kesimpulan tidak ditemukan, generate pake template");
            
            int number = 1; 
            if (!not.getPenggantiAntarWaktu().isEmpty()){
                String add = (" Pelaksana Tugas Ketua DPR RI, melantik Anggota Pengganti Antar Waktu DPR RI Sisa Masa Tahun 2014-2019 yaitu: ");
                number++;
                valueKesimpulan.add(add);
//                for (int j = 0; j < not.getPenggantiAntarWaktu().size(); j++){
//                    valueKesimpulan.add(not.getPenggantiAntarWaktu().get(j));
//                }
            }
            //PraProses.deleteNumber(not.getAcaraRapat());
            Rule.deleteNoNeedKesimpulan(kesimpulan);
            for(int k = 0 ; k < not.getAcaraRapat().size(); k++){
                String teks = kesimpulan.get(k);
                boolean membacakan = TemplateGenerator.cekKesimpulanMembacakan(teks);
                boolean menyetujui = TemplateGenerator.cekKesimpulanMenyetujui(teks);
                boolean namaPengganti = TemplateGenerator.cekPenggantiKetua(teks, not);
                if (membacakan){
                    valueKesimpulan.add("Pelaksana Tugas Ketua DPR RI membacakan " + teks);
                    number++;
                }
                else if(menyetujui){
                    if (namaPengganti){
                        
                        valueKesimpulan.add(" Rapat Paripurna menyetujui " + teks + " " + not.getDaftarAnggota().get(0));
                    }
                    else{
                        valueKesimpulan.add(" Rapat Paripurna menyetujui " + teks);
                    }
                    number++;
                }
                else{
                    valueKesimpulan.add(teks);
                }
                System.out.println ("pengganti : " + namaPengganti);
            }
            System.out.println ("b : " + valueKesimpulan.size());
            int x = 0;
            StringBuilder teks = new StringBuilder();
            while (x < valueKesimpulan.size()){ 
                Pattern patternProses = Pattern.compile ("(.*)(\\d+\\.)(.*)");
                Matcher matcher = patternProses.matcher(valueKesimpulan.get(x));
                if (matcher.find()){
                    String isi = valueKesimpulan.get(x);
                    teks.append(isi + " ");
                    
                }
                else{
                   valueKesimpulanRapat.add(teks.toString());
                   teks.delete(0, teks.length());
                   String isi = valueKesimpulan.get(x);
                   teks.append(isi + " "); 
                }
                x++;
            }
            valueKesimpulanRapat.add(teks.toString());
//            PraProses.deleteNotWord(valueKesimpulanRapat);
//            PraProses.deleteNoNeed(valueKesimpulanRapat);
            //System.out.println (namaPengganti);
            
        }  
        else if(isFind) {
            int j = 0;
            StringBuilder teks = new StringBuilder();
            while (j < valueKesimpulan.size()-1){ 
                Pattern patternProses = Pattern.compile ("(.*)(pertama|kedua|ketiga|keempat)(.*)");
                Matcher matcher = patternProses.matcher(valueKesimpulan.get(j+1));
                if (matcher.find()){
                    String isi = valueKesimpulan.get(j);
                    teks.append(isi + " ");
                   
                    valueKesimpulanRapat.add(teks.toString());
                    teks.delete(0, teks.length());
                }
                else{
                   String isi = valueKesimpulan.get(j);
                   teks.append(isi + " "); 
                }
                j++;
            }
            teks.append(valueKesimpulan.get(valueKesimpulan.size()-1));
            valueKesimpulanRapat.add(teks.toString());
        }
        if (not.getAcaraRapat().isEmpty()){
            PraProses.deleteNumber(not.getAcara());
            for(int k = 0 ; k < not.getAcara().size(); k++){
                String teks = not.getAcara().get(k);
                boolean membacakan = TemplateGenerator.cekKesimpulanMembacakan(teks);
                boolean menyetujui = TemplateGenerator.cekKesimpulanMenyetujui(teks);
                if (membacakan){
                    valueKesimpulan.add("Pelaksana Tugas Ketua DPR RI membacakan" + teks);
                    //number++;
                }
                else if(menyetujui){
                    valueKesimpulan.add(" Rapat Paripurna menyetujui " + teks);
                    //number++;
                }
                else{
                    valueKesimpulan.add(teks);
                }
            }
            int x = 0;
            StringBuilder teks = new StringBuilder();
            while (x < valueKesimpulan.size()){ 
                Pattern patternProses = Pattern.compile ("(.*)(\\d+\\.)(.*)");
                Matcher matcher = patternProses.matcher(valueKesimpulan.get(x));
                if (matcher.find()){
                    String isi = valueKesimpulan.get(x);
                    teks.append(isi + " ");;
                    
                }
                else{
                   valueKesimpulanRapat.add(teks.toString());
                   teks.delete(0, teks.length());
                   String isi = valueKesimpulan.get(x);
                   teks.append(isi + " "); 
                }
                x++;
            }
            valueKesimpulanRapat.add(teks.toString());
            PraProses.deleteNotWord(valueKesimpulanRapat);
            PraProses.deleteNoNeed(valueKesimpulanRapat);
        }
        Rule.ruleKesimpulan(valueKesimpulanRapat);
        not.setKesimpulan(valueKesimpulanRapat);
    }
    
    public void regexBiroPersidangan (Notulen not){
        List<String> valueBiroPersidangan = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Kepala Biro Persidangan I|Kepala Biro Persidangan|Lakhar Kepala Biro Persidangan)(.*)", "(.*)(//.)(.*)");
        boolean isFind = false; 
        int i = 1; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            while(i<not.getSentences().size() && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getSentences().get(i).toString();
                String teksBatas = not.getSentences().get(i-1).toString();
                Matcher matcher = pattern.matcher(teks);
                if (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        valueBiroPersidangan.add(teksBatas);
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleBiroPersidangan(valueBiroPersidangan);
        not.setBiroPersidangan(valueBiroPersidangan);
    }
    
    public void regexBagianAcara (Notulen not){
        int i = 0;
        boolean ketemu = false;
        boolean matcherTeks = false;
        boolean matcherTeksAkhir = false;
        List<List<String>> daftarBagianAcara = new ArrayList<List<String>>();
        List<String> bagianAcaraSatu = new ArrayList();
        List<String> bagianAcaraDua = new ArrayList();
        List<String> bagianAcaraTiga = new ArrayList();
        List<String> bagianAcaraEmpat = new ArrayList();
        List<String> bagianAcaraLima = new ArrayList();
        //Bagian acara pembuka 
        while (i<not.getSentences().size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(marilah kita memasuki acara pertama|Marilah kita masuki acara|agenda sidang pertama)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara kedua|dengan demikian acara pertama)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = not.getSentences().get(i).toString();
            String teksNext = not.getSentences().get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 1");
            }
            if (matcherTeks == true){
                bagianAcaraSatu.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 1");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            i++;
        }
        matcherTeks = false; ketemu = false;
        //Bagian acara kedua
        while (i<not.getSentences().size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(acara kedua|dengan demikian acara pertama)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara ketiga|dengan demikian acara kedua|Dengan demikian selesailah acara kedua)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = not.getSentences().get(i).toString();
            String teksNext = not.getSentences().get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 2");
            }
            if (matcherTeks == true){
                bagianAcaraDua.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 2");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            i++;
        }
        matcherTeks = false; ketemu = false;
        //Bagian Acara Ketiga 
        while (i<not.getSentences().size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(acara ketiga|dengan demikian acara pertama|Marilah kita memasuki acara ketiga)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara ke empat|acara keempat|dengan demikian acara ketiga|Dengan demikian selesailah acara ketiga|Dengan demikian selesailah)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = not.getSentences().get(i).toString();
            String teksNext = not.getSentences().get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 3");
            }
            if (matcherTeks == true){
                bagianAcaraTiga.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 3");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            
            i++;
        }
        matcherTeks = false; ketemu = false;
        //Bagian Acara Keempat 
        while (i<not.getSentences().size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(acara ke empat|acara keempat|dengan demikian acara ketiga|Marilah kita memasuki acara ke empat|Marilah kita memasuki acara keempat)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara yang kelima|acara kelima|acara ke lima|dengan demikian acara keempat|Dengan demikian selesailah acara keempat|Dengan demikian selesailah)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = not.getSentences().get(i).toString();
            String teksNext = not.getSentences().get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 4");
            }
            if (matcherTeks == true){
                bagianAcaraEmpat.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 4");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            i++;
        }
        matcherTeks = false; ketemu = false;
        //Bagian Acara Kelima
        while (i<not.getSentences().size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(acara yang kelima|acara kelima|dengan demikian acara ke empat|Marilah kita memasuki acara ke lima|Marilah kita memasuki acara kelima)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara yang keenam|acara keenam|acara ke enam|dengan demikian acara kelima|Dengan demikian selesailah acara kelima)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = not.getSentences().get(i).toString();
            String teksNext = not.getSentences().get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 5");
            }
            if (matcherTeks == true){
                bagianAcaraEmpat.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 5");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            i++;
        }
        daftarBagianAcara.add(bagianAcaraSatu);
        daftarBagianAcara.add(bagianAcaraDua);
        daftarBagianAcara.add(bagianAcaraTiga);
        daftarBagianAcara.add(bagianAcaraEmpat);
        daftarBagianAcara.add(bagianAcaraLima);
        System.out.println(daftarBagianAcara.get(1));
    }
    
    public void regexInterupsi (Notulen not){
        boolean ketemu = false;
        List<List<String>> daftarInterupsi = new ArrayList<>();
        ArrayList<String> namaInterupsi = new ArrayList();
        ArrayList<String> isiInterupsi = new ArrayList();
        ArrayList<String> tanggapanKetua = new ArrayList();
        boolean isTanda = false;
        boolean isBatas = false;
        boolean isBatasAkhir = false;
        Pattern pattern = Pattern.compile("(.*)(F-)(.*)(\\()(.*)(\\))");
        Pattern patternTanda = Pattern.compile("(.*)(Interupsi.|bicara.)(.*)");
        Pattern patternPotongan = Pattern.compile ("(.*)(KETUA RAPAT)(.*)");
        Pattern patternBatas = Pattern.compile ("(.*)(Wassalamu'alaikum|RAPAT : SETUJU)(.*)"); //ketemu
        int i = 0; 
        
        while(i<not.getBagianIsi().size() && isBatasAkhir == false){
            String line = not.getBagianIsi().get(i).toString();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                ketemu = true;
                isBatas = false;
                isTanda = false;
                namaInterupsi.add(matcher.group(5));
            }
            ++i;
            while(isTanda == false && i<not.getBagianIsi().size() && ketemu){
                String lineNext = not.getBagianIsi().get(i).toString();
                Matcher matcherPotongan = patternPotongan.matcher(lineNext);
                if (matcherPotongan.find()){
                    isTanda = true;
                    i++;
                }else {
                    isiInterupsi.add(not.getBagianIsi().get(i).toString());
                    i++;
                }   
            }
            while (isTanda && i<not.getBagianIsi().size() && isBatas == false && isBatasAkhir == false){
                String lineGo = not.getBagianIsi().get(i).toString(); 
                Matcher matcherBatas = patternBatas.matcher(lineGo);
                Matcher matcherLagi = pattern.matcher(lineGo);
                if (matcherBatas.find()){
                    isBatasAkhir = true;
                }else if (matcherLagi.find()){
                    isBatas = true;
                }
                else {
                    tanggapanKetua.add(not.getBagianIsi().get(i).toString());
                    i++;
                }
            }
        }
        daftarInterupsi.add(isiInterupsi);
        daftarInterupsi.add(tanggapanKetua);
        if (!ketemu)
            System.out.println("interupsi tidak ditemukan");
        int j = 0;
        for (List<String> daftarInterupsi1 : daftarInterupsi) {
            j++;
        }
        System.out.println("interupsi : " +daftarInterupsi.get(1));
        System.out.println("nama interupsi : " +namaInterupsi);
    }
    
    public Map partSegmentasi (Notulen not){ //segmentasi bagian di risalah rapat 
        Map<String, Integer> map = new LinkedHashMap<>();
        //List<String> daftarInterupsi = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(marilah kita memasuki acara pertama|Marilah kita masuki acara|agenda sidang pertama|marilah kita memasuki acara pertama|Selanjutnya untuk mempersingkat waktu marilah kita masuki acara)(.*)", "(.*)(Marilah kita memasuki acara kedua|Dengan demikian selesailah acara pertama Rapat Paripurna|Dengan demikian selesailah acara pertama|kita memasuki acara selanjutnya)(.*)", "(.*)(kita memasuki acara terakhir Rapat Paripurna|selesailah seluruh acara)(.*)", "(.*)(Segera saya akhiri persidangan ini|Wassalamu’alaikum Warahmatullahi Wabarakaatuh)(.*)");
        boolean isFind = false;
        int i = 0; int sentencesFind = 0; int j = 0; 
        if (!not.getAcaraRapat().isEmpty()){
            while (j<not.getAcaraRapat().size()){
                isFind = false;
                while (i < not.getSentences().size()-1 && isFind == false){ 
                    Pattern pattern = Pattern.compile(daftarPola.get(j));
                    String line = not.getSentences().get(i).toString();
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()){
                        //System.out.println("masuk 2");
                        isFind = true; 
                        map.put(not.getAcaraRapat().get(j), i); //acara x, awalnya kalimat ke-
                    }
                    sentencesFind = i; 
                    i++; 
                }
                j++;
            }
        }
        //System.out.println((int)not.getSentencesLine().size());
        map.put("end", (int)not.getSentences().size());
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//		System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
//                
//	}
        //System.out.println(not.getSentences().get(960));
        not.setInterupsi(map);
        return map;
    }
    
    public Map regexIsiInterupsi (Notulen not){
        List<List<String>> daftarBreakInterupsi = new ArrayList();
        //List<String> daftarInterupsi = new ArrayList();
        List<String> daftarOrang = new ArrayList();
        List findSentences = new ArrayList(); //menyimpan batas kalimat
        Map<Integer, List<String>> mapKalimat = new LinkedHashMap<>(); //simpan kalimat yang akan diklasifikasikan 
        Map<String, List<String>> map = new LinkedHashMap<>();
        //int findSentences = 0; //menyimpan kalimat keberapa 
        List<String> daftarPola = Arrays.asList(".*(KETUA RAPAT:).*", ".*(F-[A-Z\\s]+\\(.*\\).*\\:).*", ".*(KETUA.*\\(.*\\)\\:).*");// "[A-Z\\s]+\\((.*)\\)");
        boolean isFind; boolean isEnd = false; String key = null;
        int i = 0; int sentencesToFind; int j = 0; int k = 0;
        while (j<not.getInterupsi().keySet().size()){ // iterasi sampe jumlah agenda
            
            isFind = false;
            if (not.getInterupsi().keySet().toArray()[j] != "end"){ // cek apakah key sudah "end" atau belum
                
                sentencesToFind = (int) not.getInterupsi().get(not.getAcaraRapat().get(j));// mulai kalimat agenda pertama
                while (sentencesToFind < (int)not.getInterupsi().get(not.getInterupsi().keySet().toArray()[j+1]) -1 && isFind == false){ 
                    List<String> daftarInterupsi = new ArrayList();
                    while (i < daftarPola.size() && isFind == false){
                        Pattern pattern = Pattern.compile(daftarPola.get(i));
                        String line = not.getSentences().get(sentencesToFind).toString();
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()){
                            key = line;
                            daftarOrang.add(matcher.group(1));
                            isFind = true; 
                            sentencesToFind++;
                        }
                        i++;
                    }
                    if (isFind){ //masukan kalimat yang termasuk bahasan orang tersebut dan cari batas yang lain 
                        isEnd = false;
                        //System.out.println("kalimat ke: " + sentencesToFind);
                        findSentences.add(sentencesToFind);
                        while (sentencesToFind < (int)not.getInterupsi().get(not.getInterupsi().keySet().toArray()[j+1]) - 1 && isEnd == false){

                            String line = not.getSentences().get(sentencesToFind).toString();
                            while (k < daftarPola.size() && isEnd == false){
                                Pattern pattern = Pattern.compile(daftarPola.get(k));
                                Matcher matcher = pattern.matcher(line); 
                                if (matcher.find()){
                                    isEnd = true; 
                                    key = line;
                                }
                                k++;
                            }
                            if (!isEnd){
                                daftarInterupsi.add(line);
                            }
                            else {
                                isFind=false;
                                sentencesToFind--;
                            }
                            sentencesToFind++;
                            k = 0;
                            
                        }
                        sentencesToFind--;
                        daftarInterupsi.add(not.getAcaraRapat().get(j));
                        //System.out.println("ini interupsi" + daftarInterupsi + "ini interupsi");
                        daftarBreakInterupsi.add(daftarInterupsi);
                        
                        //System.out.println("ini break interupsi" + daftarBreakInterupsi + "ini interupsi");
                       
                        //daftarInterupsi.clear();
                    }
                    sentencesToFind++;
                    i = 0;
                }           
                
            }
            else {
                System.out.println("masuk end");
            }
            j++;
        }
        for (int a = 0; a < daftarBreakInterupsi.size(); a++){
            map.put(findSentences.get(a).toString(), daftarBreakInterupsi.get(a));
        }
        
//        map.entrySet().stream().forEach((entry) -> {
//            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
//        });
//        System.out.println(map);
        //Rule.rulePembicara(daftarOrang);
        not.setSegmentasiAcara(map);
        not.setPembicara(daftarOrang);
        not.setIsiInterupsi(daftarBreakInterupsi);
        return map;
    }
    
    public void regexLampiran (Notulen not){
        List<List<String>> daftarBreakInterupsi = new ArrayList();
        //List<String> daftarInterupsi = new ArrayList();
        List<String> daftarOrang = new ArrayList();
        List findSentences = new ArrayList(); //menyimpan batas kalimat
        Map<Integer, List<String>> mapKalimat = new LinkedHashMap<>(); //simpan kalimat yang akan diklasifikasikan 
        Map<String, List<String>> map = new LinkedHashMap<>();
        //int findSentences = 0; //menyimpan kalimat keberapa 
        List<String> daftarPola = Arrays.asList(".*(F-.*[A-Z].*\\(.*\\)).*",".*(KETUA RAPAT:).*", ".*(F\\s-\\s.*[A-Z].*\\(.*\\)).*",".*(F-[A-Z\\s]+\\(.*\\).*\\:).*", ".*(PIMPINAN PANSUS.*\\(.*\\)\\:).*", ".*(F-[A-Z\\s]+\\(.*\\).*\\;).*" , ".*(F\\s.*[A-Z].*\\(.*\\):).*", ".*(PIMPINAN BALEG.*\\(.*\\)\\:).*", ".*(PIMPINAN KOMISI.*\\(.*\\)\\:).*",".*(MENTERI.*\\(.*\\)\\:).*" , ".*(KETUA RAPAT:).*", ".*(MENTERI.*\\:).*");// "[A-Z\\s]+\\((.*)\\)");
        boolean isFind; boolean isEnd = false; String key = null;
        int i = 0; int sentencesToFind; int j = 0; int k = 0;
//        while (j<not.getInterupsi().keySet().size()){ // iterasi sampe jumlah agenda
            
            isFind = false;
//            if (not.getInterupsi().keySet().toArray()[j] != "end"){ // cek apakah key sudah "end" atau belum
                
                sentencesToFind = 0;// mulai kalimat agenda pertama
                while (sentencesToFind < not.getBagianIsi().size()-1 && isFind == false){ 
                    List<String> daftarInterupsi = new ArrayList();
                    while (i < daftarPola.size() && isFind == false){
                        Pattern pattern = Pattern.compile(daftarPola.get(i));
                        String line = not.getBagianIsi().get(sentencesToFind).toString();
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()){
                            key = line;
                            daftarOrang.add(matcher.group(1));
                            isFind = true; 
                            sentencesToFind++;
                        }
                        i++;
                    }
                    if (isFind){ //masukan kalimat yang termasuk bahasan orang tersebut dan cari batas yang lain 
                        isEnd = false;
                        //System.out.println("kalimat ke: " + sentencesToFind);
                        findSentences.add(sentencesToFind);
                        while (sentencesToFind < not.getBagianIsi().size()-1 && isEnd == false){

                            String line = not.getBagianIsi().get(sentencesToFind).toString();
                            while (k < daftarPola.size() && isEnd == false){
                                Pattern pattern = Pattern.compile(daftarPola.get(k));
                                Matcher matcher = pattern.matcher(line); 
                                if (matcher.find()){
                                    isEnd = true; 
                                    key = line;
                                }
                                k++;
                            }
                            if (!isEnd){
                                daftarInterupsi.add(line);
                            }
                            else {
                                isFind=false;
                                sentencesToFind--;
                            }
                            sentencesToFind++;
                            k = 0;
                            
                        }
                        sentencesToFind--;
//                        daftarInterupsi.add(not.getAcaraRapat().get(j));
                        //System.out.println("ini interupsi" + daftarInterupsi + "ini interupsi");
                        daftarBreakInterupsi.add(daftarInterupsi);
                        
                        //System.out.println("ini break interupsi" + daftarBreakInterupsi + "ini interupsi");
                       
                        //daftarInterupsi.clear();
                    }
                    sentencesToFind++;
                    i = 0;
                }           
                
//            }
//            else {
//                System.out.println("masuk end");
//            }
            j++;
//        }
        for (int a = 0; a < daftarBreakInterupsi.size(); a++){
            map.put(findSentences.get(a).toString(), daftarBreakInterupsi.get(a));
        }
        
//        map.entrySet().stream().forEach((entry) -> {
//            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
//        });
//        System.out.println(map);
        //Rule.rulePembicara(daftarOrang);
        not.setSegmentasiAcara(map);
        not.setPembicara(daftarOrang);
        not.setIsiInterupsi(daftarBreakInterupsi);
    }
    
    public List patternIndonesiaRaya (List isiFile, boolean ketemu){
        List<String> indonesiaRaya = new ArrayList(); 
        boolean batas = false;
        int i = 0; 
        while(i<isiFile.size() && batas == false){
            Pattern pattern = Pattern.compile("(.*)(RAPAT DIBUKA)(.*)");
            Pattern patternPotongan = Pattern.compile ("(.*)(MENYANYIKAN LAGU)(.*)");
            String line = isiFile.get(i).toString();
            String lineNext = isiFile.get(i+1).toString();
            Matcher matcher = pattern.matcher(line);
            Matcher matcherNext = patternPotongan.matcher(lineNext);
            while (matcher.find()) {
                ketemu = true;
            }
            if(matcherNext.find() && ketemu == true) {
                batas = true;
            } else if (ketemu == true)
                indonesiaRaya.add(lineNext); 
            ++i;
        } 
        if (!ketemu)
            System.out.println("tidak ditemukan");
        System.out.println("indonesia raya: " +indonesiaRaya);
        return indonesiaRaya; 
    }
        
    public List patternBagianAcara (List isiFile, boolean ketemu){
        int i = 0;
        boolean matcherTeks = false;
        boolean matcherTeksAkhir = false;
        List<List<String>> daftarBagianAcara = new ArrayList<List<String>>();
        List<String> bagianAcaraSatu = new ArrayList();
        List<String> bagianAcaraDua = new ArrayList();
        List<String> bagianAcaraTiga = new ArrayList();
        List<String> bagianAcaraEmpat = new ArrayList();
        List<String> bagianAcaraLima = new ArrayList();
        //Bagian acara pembuka 
        while (i<isiFile.size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(marilah kita memasuki acara pertama|Marilah kita masuki acara|agenda sidang pertama)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara kedua|dengan demikian acara pertama)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = isiFile.get(i).toString();
            String teksNext = isiFile.get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 1");
            }
            if (matcherTeks == true){
                bagianAcaraSatu.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 1");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            i++;
        }
        matcherTeks = false; ketemu = false;
        //Bagian acara kedua
        while (i<isiFile.size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(acara kedua|dengan demikian acara pertama)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara ketiga|dengan demikian acara kedua|Dengan demikian selesailah acara kedua)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = isiFile.get(i).toString();
            String teksNext = isiFile.get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 2");
            }
            if (matcherTeks == true){
                bagianAcaraDua.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 2");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            i++;
        }
        matcherTeks = false; ketemu = false;
        //Bagian Acara Ketiga 
        while (i<isiFile.size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(acara ketiga|dengan demikian acara pertama|Marilah kita memasuki acara ketiga)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara ke empat|acara keempat|dengan demikian acara ketiga|Dengan demikian selesailah acara ketiga|Dengan demikian selesailah)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = isiFile.get(i).toString();
            String teksNext = isiFile.get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 3");
            }
            if (matcherTeks == true){
                bagianAcaraTiga.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 3");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            
            i++;
        }
        matcherTeks = false; ketemu = false;
        //Bagian Acara Keempat 
        while (i<isiFile.size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(acara ke empat|acara keempat|dengan demikian acara ketiga|Marilah kita memasuki acara ke empat|Marilah kita memasuki acara keempat)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara yang kelima|acara kelima|acara ke lima|dengan demikian acara keempat|Dengan demikian selesailah acara keempat|Dengan demikian selesailah)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = isiFile.get(i).toString();
            String teksNext = isiFile.get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 4");
            }
            if (matcherTeks == true){
                bagianAcaraEmpat.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 4");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            i++;
        }
        matcherTeks = false; ketemu = false;
        //Bagian Acara Kelima
        while (i<isiFile.size()-1 && (ketemu == false||matcherTeksAkhir == false)){
            Pattern pattern = Pattern.compile("(.*)(acara yang kelima|acara kelima|dengan demikian acara ke empat|Marilah kita memasuki acara ke lima|Marilah kita memasuki acara kelima)(.*)");
            Pattern patternBatas = Pattern.compile("(.*)(acara yang keenam|acara keenam|acara ke enam|dengan demikian acara kelima|Dengan demikian selesailah acara kelima)(.*)");
            Pattern patternAkhir = Pattern.compile("(.*)(dengan demikian selesailah acara rapat paripurna)(.*)");
            String teks = isiFile.get(i).toString();
            String teksNext = isiFile.get(i+1).toString(); 
            Matcher matcher = pattern.matcher(teks);
            Matcher matcherBatas = patternBatas.matcher(teksNext);
            Matcher matcherAkhir = patternAkhir.matcher(teksNext);
            if (matcher.find()){
                System.out.println(i);
                matcherTeks = true; 
                System.out.println("ketemu 5");
            }
            if (matcherTeks == true){
                bagianAcaraEmpat.add(teks);
            }
            if(matcherBatas.find()){
                System.out.println("ketemu batas 5");
                ketemu = true;
                System.out.println(i);
            }
            if(matcherAkhir.find()){
               System.out.println("ketemu batas akhir");
               matcherTeksAkhir = true;
               System.out.println(i);
            }
            i++;
        }
        daftarBagianAcara.add(bagianAcaraSatu);
        daftarBagianAcara.add(bagianAcaraDua);
        daftarBagianAcara.add(bagianAcaraTiga);
        daftarBagianAcara.add(bagianAcaraEmpat);
        daftarBagianAcara.add(bagianAcaraLima);
        System.out.println(daftarBagianAcara);
        return daftarBagianAcara; 
    }
     //interupsi 
    public List patternInterupsi (List isiFile, boolean ketemu){
        List<List<String>> daftarInterupsi = new ArrayList<List<String>>();
        ArrayList<String> namaInterupsi = new ArrayList();
        ArrayList<String> isiInterupsi = new ArrayList();
        ArrayList<String> tanggapanKetua = new ArrayList();
        boolean isTanda = false;
        boolean isBatas = false;
        boolean isBatasAkhir = false;
        Pattern pattern = Pattern.compile("(.*)(F-)(.*)(\\()(.*)(\\))");
        Pattern patternTanda = Pattern.compile("(.*)(Interupsi.|bicara.)(.*)");
        Pattern patternPotongan = Pattern.compile ("(.*)(KETUA RAPAT)(.*)");
        Pattern patternBatas = Pattern.compile ("(.*)(Wassalamu'alaikum|RAPAT : SETUJU)(.*)"); //ketemu
        int i = 0; 
        
        while(i<isiFile.size() && isBatasAkhir == false){
            String line = isiFile.get(i).toString();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                ketemu = true;
                isBatas = false;
                isTanda = false;
                namaInterupsi.add(matcher.group(5));
            }
            ++i;
            while(isTanda == false && i<isiFile.size() && ketemu){
                String lineNext = isiFile.get(i).toString();
                Matcher matcherPotongan = patternPotongan.matcher(lineNext);
                if (matcherPotongan.find()){
                    isTanda = true;
                    i++;
                }else {
                    isiInterupsi.add(isiFile.get(i).toString());
                    i++;
                }   
            }
            while (isTanda && i<isiFile.size() && isBatas == false && isBatasAkhir == false){
                String lineGo = isiFile.get(i).toString(); 
                Matcher matcherBatas = patternBatas.matcher(lineGo);
                Matcher matcherLagi = pattern.matcher(lineGo);
                if (matcherBatas.find()){
                    isBatasAkhir = true;
                }else if (matcherLagi.find()){
                    isBatas = true;
                }
                else {
                    tanggapanKetua.add(isiFile.get(i).toString());
                    i++;
                }
            }
        }
        daftarInterupsi.add(isiInterupsi);
        daftarInterupsi.add(tanggapanKetua);
        if (!ketemu)
            System.out.println("tidak ditemukan");
        int j = 0;
        for (List<String> daftarInterupsi1 : daftarInterupsi) {
            j++;
        }
        System.out.println("interupsi : " +daftarInterupsi);
        return daftarInterupsi; 
    }
    
    public void fill(Notulen not) throws IOException {
        // TODO code application logic here
        file.loadFile(not);
        file.segmentasiTeks(not);
        Map<String, Integer> map = new LinkedHashMap<>();
        Map<String, Integer> mapIsi = new LinkedHashMap<>();
        regexTahunSidang(not);
        regexMasaSidang(not);
        regexUrutanRapat(not);
        regexJenisRapat(not);
        regexSifatRapat(not);
        regexHariRapat(not);
        regexTanggalRapat(not);
        regexRapatDibuka(not);
        regexRapatDitutup(not);
        regexTempatRapat(not);
        regexKotaRapat(not);
        regexKetuaRapat(not);
        regexPendampingKetua(not);
        regexSekretarisRapat(not);
        regexPendampingSekretaris(not);
        regexAnggotaHadir(not);
        regexSepucukSuratMasuk(not);
        regexPucukSuratMasuk(not);
        regexPenggantiAntarWaktu(not);
        regexBiroPersidangan(not);
        regexAcara(not);
        regexAcaraRapat(not);
        regexKesimpulan(not);
        regexBagianAcara(not);
        regexInterupsi(not);
        map = partSegmentasi(not);
//        mapIsi = regexIsiInterupsi(not);
        regexLampiran(not);
        patternAcaraRapat(not);
        patternPucukSuratMasuk(not);
        patternPenggantiAntarWaktu(not);
        //boolean namaPengganti = TemplateGenerator.cekPenggantiKetua(isiTeks, not)
        System.out.println("a" + not.getTahunSidang());
        System.out.println("b" + not.getMasaSidang());
        System.out.println("c" + not.getUrutanRapat());
        System.out.println("d" + not.getJenisRapat());
        System.out.println("e" + not.getSifatRapat());
        System.out.println("f" + not.getHariRapat());
        System.out.println("g" + not.getTanggalRapat());
        System.out.println("h" + not.getRapatDibuka());
        System.out.println("i" + not.getRapatDitutup());
        System.out.println("j" + not.getTempatRapat());
        System.out.println("k" + not.getKotaRapat());
        System.out.println("l" + not.getKetuaRapat());
        System.out.println("m" + not.getPendampingKetua());
        System.out.println("n" + not.getSekretarisRapat());
        System.out.println("o" + not.getPendampingSekretaris());
        System.out.println("p" + not.getAnggotaHadir());
        System.out.println("q" + not.getPucukSuratMasuk().size());
        System.out.println("q1" + not.getSepucukSuratMasuk());
        System.out.println("r" + not.getPenggantiAntarWaktu());
        System.out.println("r1" + not.getPenggantiAntarWaktuTambahan());
        System.out.println("s" + not.getBiroPersidangan());
        System.out.println("t"+ not.getAcara().size());
        System.out.println("u"+ not.getAcaraRapat().size());
        System.out.println("w"+ not.getKesimpulan());
        System.out.println("x" + not.getBagianAwal());
        System.out.println("y" + not.getBagianIsi());
        System.out.println("y" + not.getInterupsi());
        System.out.println("y" + not.getPembicara());
        System.out.println("y" + not.getIsiInterupsi());
        System.out.println("y" + not.getSegmentasiAcara().size());
        System.out.println("y" + not.getPembicara().size());
        System.out.println("y" + not.getIsiInterupsi().size());
        System.out.println("y" + not.getSegmentasiAcara().size());
        System.out.println("z" + not.getDaftarAnggota());
        
        
        file.writeResultSystem(not);    
        
        
    }
    
}
