/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
public class RegexGenerator {
    
    
    //pembangkit regex informasi umum 
    public RegexGenerator(){
        
    }
    
    public void regexTanggalRapat (Notulen not){
        List<String> valueHariRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList(".*tanggal(.*\\d{1,2}.*\\d{4}\\,\\s.*)dan.*",".*pada hari(.*\\d{1,2}.*\\d{4}).*", ".*pada hari(.*\\,.*\\d{1,2}.*\\d{4}).*",".*hari ini(.*\\,.*\\d{1,2}.*\\d{4}).*", ".*hari(.*\\,.*\\d{1,2}.*\\d{4}).*",".*pada hari(.*tanggal.*\\d{1,2}\\sApril\\s\\d{4}).*");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while(i<not.getSentencesLine().size() - 1 && isFind == false){
            j = 0; 
            while (j<daftarPola.size() && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getSentencesLine().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        valueHariRapat.add(matcher.group(1));
                }
                ++j;
            }  
            i++;
        }
        if (!isFind) {
            System.out.println("tanggal rapat tidak ditemukan");
        } 
        Rule.ruleTanggalRapat(valueHariRapat);
        not.setTanggalRapat(valueHariRapat);
    }//dibalik algoritma cari tanggalnya
    
    public void regexTahunSidang (Notulen not){
        List<String> valueTahunSidang = new ArrayList();
        List<String> daftarPola = Arrays.asList(".*Tahun Sidang(\\s{1,2}\\d{4}\\-\\d{4}).*", ".*Tahun Sidang(\\s{1,2}\\d{4}\\-\\s\\d{4}).*");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            i = 0; 
            while(i<not.getSentencesLine().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getSentencesLine().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        valueTahunSidang.add(matcher.group(1));
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleTahunSidang(valueTahunSidang);
        not.setTahunSidang(valueTahunSidang);
    }
    
    public void regexMasaPersidangan (Notulen not){
        List<String> valueMasaPersidangan = new ArrayList();
        List<String> daftarPola = Arrays.asList(".*Masa Persidangan(\\s{1,2}IV|\\s{1,2}III|\\s{1,2}II|\\s{1,2}I).*", ".*Masa Persidangan ke(\\s{1,2}IV|\\s{1,2}III|\\s{1,2}II|\\s{1,2}I).*", ".*Masa Persidangan Ke-(IV|III|II|I|lll).*", ".*Masa Persidangan(\\-{1,2}IV|\\-{1,2}III|\\-{1,2}II|\\-{1,2}I).*");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            i = 0; 
            while(i<not.getSentencesLine().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getSentencesLine().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        valueMasaPersidangan.add(matcher.group(1));
                }
                ++i;
            }  
            j++;
        }
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        Rule.ruleMasaSidang(valueMasaPersidangan);
        not.setMasaPersidangan(valueMasaPersidangan);
    }
    
    public void regexUrutanRapat (Notulen not){
        List<String> valueUrutanRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList(".*Rapat Paripurna Dewan ke-(\\s{1,2}\\d{1,2}|\\d{1,2}).*", ".*Rapat Paripurna yang ke-(\\s{1,2}\\d{1,2}|\\d{1,2}).*", ".*Rapat Paripurna DPR.*yang ke-(\\s{1,2}\\d{1,2}|\\d{1,2}).*", ".*rapat paripurna yang ke-(\\d{1,2}).*, .*Rapat Paripurna DPR.* ke-(\\s{1,2}\\d{1,2}|\\d{1,2}).*");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while (j<daftarPola.size() && isFind == false){
            i = 0; 
            while(i<not.getSentencesLine().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getSentencesLine().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true; 
                    if(isFind)
                        valueUrutanRapat.add(matcher.group(1));
                }
                ++i;
            }  
            j++;
        }
        System.out.println("masuk ke else " + valueUrutanRapat);
        if (!isFind) {
            valueUrutanRapat.add("null");
            not.setUrutanRapat(valueUrutanRapat);
        } 
//        else {
//            System.out.println("masuk ke else " + valueUrutanRapat);
            Rule.ruleUrutanSidang(valueUrutanRapat);
            not.setUrutanRapat(valueUrutanRapat);
//        }
    }
    
    public void regexDaftarHadirAnggota (Notulen not){
        List<String> valueDaftarHadirAnggota = new ArrayList();
        List<String> valueJumlahHadirAnggota = new ArrayList();
        List<String> daftarPola = Arrays.asList(".*telah ditandatangani oleh.*(\\d{2,4}).*orang Anggota dengan perincian.*",".*sejumlah.*(\\d{3}).*Anggota",".*sebanyak.*(\\d{3}).*Anggota",".*telah ditandadatangani oleh.*(\\d{3}).*",".*telah ditandatangani oleh.*(\\d{3}).*",".* telah hadir.*(\\d{3}).*orang",".*Dengan demikian kuorum telah tercapai.*|.*Dengan demikian maka kuorum telah tercapai.*|.*Dengan demikian kuorum telah mencapai.*|.*Dengan demikian korum telah tercapai.*|.*Dengan demikian telah tercapai kuorum.*");
        boolean isFind = false; boolean isMatch = false;
        int i = 0; int j = 0; int k = 0;  
        while (j<daftarPola.size()-1 && isFind == false){
            i = 0; 
            while(i<not.getKalimat().size() - 1 && isFind == false){
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String teks = not.getKalimat().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                while (matcher.find()) {
                    isFind = true;

                     valueJumlahHadirAnggota.add(matcher.group(1));
                     System.out.println("value : " + valueJumlahHadirAnggota);
                    not.setJumlahHadirAnggota(valueJumlahHadirAnggota);
                }
                ++i;
            }  
            j++;
        }
        System.out.println("value i : " + not.getKalimat().size());
        if (isFind){
            while (i < not.getKalimat().size() && isMatch ==false ){
                Pattern pattern = Pattern.compile(daftarPola.get(6));
                String teks = not.getKalimat().get(i).toString();
                Matcher matcher = pattern.matcher(teks);
                if(matcher.find()) {
                    isMatch = true;
                } 
                else {
                    valueDaftarHadirAnggota.add(teks);
                }
                i++;
            }
        }
        if (!isFind) {
            System.out.println("kehadiran anggota tidak ditemukan");
        } 
        Rule.ruleDaftarHadir(valueDaftarHadirAnggota);
        not.setDaftarHadirAnggota(valueDaftarHadirAnggota);
    }
    
    public List regexAcara (Notulen not) throws IOException{ //cari agenda rapat
        List<String> agendaRapat = new ArrayList(); //penampung untuk data kotor acara rapat
        List<String> acaraRapat = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(Dengan disetujuinya permintaan Komisi X tersebut, maka acara Rapat Paripurna Dewan hari ini|Dengan disetujuinya permintaan Komisi X tersebut, maka acara Rapat Paripurna hari ini)(.*)", "(.*)(Pada pagi hari ini kita.*punya.*agenda|acara rapat paripurna hari ini|Acara Rapat Paripurna hari ini|acara Rapat Paripurna Dewan hari ini|acara Rapat Paripurna hari ini adalah)(.*)", "(.*)(Sekarang kami akan menanyakan|RAPAT: SETUJU|Sebelum memulai acara perlu kami sampaikan|Sidang Dewan yang terhormat|Surat yang ditandatangani oleh|Selanjutnya surat tersebut|Berkaitan dengan itu|Bapak, Ibu sekalian,|Itulah)(.*)");
        boolean isFind = false; boolean isEnd = false;
        int i = 0; int sentencesFind = 0; int j = 0; 
        while (j<daftarPola.size()-1 && isFind == false){
            i = 0; 
            while (i < not.getSentencesLine().size()-1 && isFind == false){ 
                //System.out.println("masuk 2");
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String line = not.getSentencesLine().get(i).toString();
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
            System.out.println("masuk isFind");
            int iterator = 1; 
            while (sentencesFind < not.getSentencesLine().size()-1 && isEnd == false){
                Pattern pattern = Pattern.compile(daftarPola.get(2));
                String teks = not.getSentencesLine().get(sentencesFind+1).toString();
                Matcher matcher = pattern.matcher(teks);
                if (matcher.find()){
                    System.out.println("masuk sini");
                    isEnd = true; 
                    if (iterator ==1){
                        agendaRapat.add(teks); 
                    }
                }
                else {
                    System.out.println("masuk isFind lagi");
                   agendaRapat.add(teks); 
                   iterator++;
                }
                
                sentencesFind++;
            }
            
        }
        System.out.println("rapat coy: " + agendaRapat.size());
        
        //parse dan masukan ke acara Rapat
        if (agendaRapat.isEmpty())
            System.out.println("agenda rapat tidak ditemukan");
//        else {
//            int k = 0;
//            StringBuilder teks = new StringBuilder();
//            while (k < agendaRapat.size()-1){ 
//                Pattern patternParse = Pattern.compile ("(.*)(\\d+\\.|Pertama|pertama|Kedua|kedua|Ketiga|ketiga|Keempat|keempat|Kelima|kelima|Keenam|keenam|Ketujuh|ketujuh|Kedelapan|kedelapan|Kesembilan|kesembilan)(.*)");
//                Matcher matcher = patternParse.matcher(agendaRapat.get(k+1));
//                if (matcher.find()){
//                    String isi = agendaRapat.get(k);
//                    teks.append(isi + " ");
//                    acaraRapat.add(teks.toString());
//                    teks.delete(0, teks.length());
//                }
//                else{
//                   String isi = agendaRapat.get(k);
//                   teks.append(isi + " "); 
//                }
//                k++;
//            }
//            k = 0; 
//            teks.append(agendaRapat.get(agendaRapat.size()-1));
//            acaraRapat.add(teks.toString());
//            while (k < acaraRapat.size()) {
//                Pattern patternParse = Pattern.compile ("(.*)(\\d+\\.|Pertama|pertama|Kedua|kedua|Ketiga|ketiga|Keempat|keempat|Kelima|kelima|Keenam|keenam|Ketujuh|ketujuh|Kedelapan|kedelapan|Kesembilan|kesembilan)(.*)");
//                Matcher matcher = patternParse.matcher(acaraRapat.get(k));
//                if (!matcher.find()){
//                    acaraRapat.remove(acaraRapat.get(k));
//                }
//                k++;
//            }
//                
//            
//        }
        //Rule.ruleAcaraIsi(acara);
//        System.out.println("acara rapat : " +acaraRapat);
//        System.out.println("acara rapat : " +acaraRapat.size());
//        for (int k = 0; k < agendaRapat.size(); k++){
//            System.out.println(agendaRapat.get(k));
//        }
        System.out.println(agendaRapat.size());
//        Rule.restructuredAcaraRapat(agendaRapat);
        System.out.println(agendaRapat.size());
//        agendaRapat.remove(0);
//        if (agendaRapat.size() > 1){
//            agendaRapat.remove(0);
//        }
//        else if (agendaRapat.size() == 1){
//            //aturan untuk mengambil bagian acara saja
//        }
//        Rule.ruleAcaraRapat(agendaRapat);
        not.setAcaraRapat(agendaRapat);
        return agendaRapat; 
    }
        
    public Map partSegmentasi (Notulen not){ //segmentasi bagian di risalah rapat 
        Map<String, Integer> map = new LinkedHashMap<>();
        //List<String> daftarInterupsi = new ArrayList();
        List<String> daftarPola = Arrays.asList("(.*)(agenda sidang pertama|marilah kita memasuki acara pertama|Marilah kita memasuki acara pertama|Untuk mempersingkat waktu marilah kita memasuki acara Rapat Paripurna kita hari ini, yaitu|mari kita masuki acara pertama|Marilah kita memulai acara yang pertama|Marilah kita memasuki acara Rapat Paripurna Dewan hari ini|Selanjutnya untuk mempersingkat waktu saya persilakan)(.*)", "(.*)(sehingga dengan demikian acara pertama dapat kita selesaikan|Dengan demikian agenda kita yang pertama telah selesai|Dengan demikian selesailah acara pertama|Marilah kita memasuki acara kedua|kita memasuki acara selanjutnya|sekarang menginjak agenda kita yang kedua|marilah kita memasuki acara kedua Rapat Paripurna hari ini|mari kita masuki acara kedua|menginjak agenda kita yang kedua)(.*)", "(.*)(Marilah kita memasuki acara ke-3|kita memasuki acara terakhir Rapat Paripurna)(.*)", "(.*)(Marilah kita memasuki acara terakhir|Segera saya akhiri persidangan ini|Wassalamu’alaikum Warahmatullahi Wabarakaatuh)(.*)");
        boolean isFind = false;
        int i = 0; int sentencesFind = 0; int j = 0; 
        while (j<not.getAcaraRapat().size()){
            isFind = false;
            while (i < not.getSentencesLine().size()-1 && isFind == false){ 
                Pattern pattern = Pattern.compile(daftarPola.get(j));
                String line = not.getSentencesLine().get(i).toString();
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
        //System.out.println((int)not.getSentencesLine().size());
        map.put("end", (int)not.getSentencesLine().size());
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//		System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
//                
//	}
        
        not.setInterupsi(map);
        not.setJumlahInterupsi(map.size()-1);
        System.out.println("jumlah segementasi interupsi " + not.getJumlahInterupsi());
        return map;
    }
    
    public Map regexInterupsi (Notulen not){
        List<List<String>> daftarBreakInterupsi = new ArrayList();
        List<String> daftarOrang = new ArrayList();
        List findSentences = new ArrayList(); //menyimpan batas kalimat
        Map<Integer, List<String>> mapKalimat = new LinkedHashMap<>(); //simpan kalimat yang akan diklasifikasikan 
        Map<String, List<String>> map = new LinkedHashMap<>();
        //int findSentences = 0; //menyimpan kalimat keberapa 
        List<String> daftarPola = Arrays.asList(".*(KETUA RAPAT:).*", ".*(F\\s-\\s.*[A-Z].*\\(.*\\)).*",".*(F-[A-Z\\s]+\\(.*\\).*\\:).*", ".*(KETUA.*\\(.*\\)\\:).*", ".*(PIMPINAN PANSUS.*\\(.*\\)\\:).*", ".*(F-[A-Z\\s]+\\(.*\\).*\\;).*" , ".*(F\\s.*[A-Z].*\\(.*\\):).*", ".*(PIMPINAN BALEG.*\\(.*\\)\\:).*", ".*(PIMPINAN KOMISI.*\\(.*\\)\\:).*",".*(MENTERI.*\\(.*\\)\\:).*" , ".*(KETUA RAP AT:).*", ".*(MENTERI.*\\:).*");// "[A-Z\\s]+\\((.*)\\)");
        boolean isFind; boolean isEnd = false; String key = null;
        int i = 0; int sentencesToFind; int j = 0; int k = 0;
        while (j< not.getInterupsi().keySet().size()){ // iterasi sampe jumlah agenda
            
            isFind = false;
            if (not.getInterupsi().keySet().toArray()[j] != "end"){ // cek apakah key sudah "end" atau belum
                
                sentencesToFind = (int) not.getInterupsi().get(not.getAcaraRapat().get(j));// mulai kalimat agenda pertama
                System.out.println(sentencesToFind);
                while (sentencesToFind < (int)not.getInterupsi().get(not.getInterupsi().keySet().toArray()[j+1]) && isFind == false){ 
                    List<String> daftarInterupsi = new ArrayList();
                   //i = 0; //isFind = false;
                    while (i < daftarPola.size() && isFind == false){
                        Pattern pattern = Pattern.compile(daftarPola.get(i));
                        String line = not.getSentencesLine().get(sentencesToFind).toString();
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()){
                            key = line;
                            daftarOrang.add(matcher.group(1));
                            isFind = true; 
                            sentencesToFind++;
                            System.out.println(matcher.group(1));
                        }
                        i++;
                    }
                    //sentencesToFind--;
                    if (isFind){ //masukan kalimat yang termasuk bahasan orang tersebut dan cari batas yang lain 
                        isEnd = false;
                        //System.out.println("kalimat ke: " + sentencesToFind);
                        findSentences.add(sentencesToFind);
                        while (sentencesToFind < (int)not.getInterupsi().get(not.getInterupsi().keySet().toArray()[j+1]) && isEnd == false){
                            //k = 0; 
                            String line = not.getSentencesLine().get(sentencesToFind).toString();
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
        //System.out.println("kalimat ke- " + not.getSentencesLine().get(410));
        Rule.rulePembicara(daftarOrang);
        not.setSegmentasiAcara(map);
        not.setPembicara(daftarOrang);
        not.setIsiInterupsi(daftarBreakInterupsi);
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\a.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                
                for (int y = 0; y < not.getSegmentasiAcara().size(); y++){
                    w.write( not.getSegmentasiAcara().get(not.getSegmentasiAcara().keySet().toArray()[y]).toString());
                    w.newLine();
                }
            }
                    
                    
        } catch (IOException e) {
                System.err.println("Problem writing to the file statsTest.txt");
        }
        return map;
        
    }
    
    
}
