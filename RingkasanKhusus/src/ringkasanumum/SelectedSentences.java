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
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mochamadtry
 */
public class SelectedSentences {
    public CosineSimilarity cs;
    public Mip ilp;
    public ILP ilpJava;
    
    public SelectedSentences(){
        cs = new CosineSimilarity(); 
        ilp = new Mip();
        ilpJava = new ILP(); 
    }    
    
    public void sentencesJoin (Notulen not){
        List<String> penampungAcara = new ArrayList();
        List<String> penampungPembicara = new ArrayList(); 
        List<List<String>> penampungInterupsi = new ArrayList(); 
        List<String> acaraFix = new ArrayList(); 
        List<String> pembicaraFix = new ArrayList();
        List<List<String>> interupsiFix = new ArrayList(); 
        int j = 0; //untuk pengulangan sampai n acara
        int i = 0;
        boolean isFind = false; 
        boolean isIterasi = false; 
        
        while (j < not.getInterupsi().size()-1){
            isFind = false; 
            //System.out.println("masuk 1");
            String acara = not.getInterupsi().keySet().toArray()[j].toString();
            System.out.println("masuk 1 " + acara );
            while (i < not.getPembicara().size() && isFind== false){ //sampai semua pembicara telah ditelusuri
                isIterasi = false;
                System.out.println("masuk 2");
                System.out.println("masuk 2 " + " " + not.getPembicara().get(i).toString()+ not.getInterupsiKlasifikasi().get(i) + " ");
                List<String> penampung = new ArrayList();
                penampungAcara.add(acara);
                penampungPembicara.add(not.getPembicara().get(i).toString());
                penampungInterupsi.add(not.getInterupsiKlasifikasi().get(i));
                int k = i + 1 ; 
                while (k < not.getPembicara().size() && isIterasi == false ){ //sampai semua pembicara yang sama dimasukan 
                    System.out.println ("masuk 3 " + not.getInterupsi().keySet().toArray()[j] + " " + not.getInterupsiKlasifikasi().get(k).get(not.getInterupsiKlasifikasi().get(k).size()-1));
                    if (not.getInterupsi().keySet().toArray()[j] == not.getInterupsiKlasifikasi().get(k).get(not.getInterupsiKlasifikasi().get(k).size()-1) && penampungPembicara.get(i).equals(not.getPembicara().get(k).toString())){
                        
                        System.out.println ("masuk if " );//+ not.getInterupsi().keySet().toArray()[j] + " " + not.getInterupsiKlasifikasi().get(k).get(not.getInterupsiKlasifikasi().get(k).size()-1));
                        for (int y = 0; y < not.getInterupsiKlasifikasi().get(k).size()-1 ; y++){
                            String line = not.getInterupsiKlasifikasi().get(k).get(y);
                            penampung.add(line);
                        }
                        penampungInterupsi.set(i, penampung);
                    }
                    else if (not.getInterupsi().keySet().toArray()[j] != not.getInterupsiKlasifikasi().get(k).get(not.getInterupsiKlasifikasi().get(k).size()-1)){
                        System.out.println ("masuk else if");
                        isIterasi = true;
                    }
                    k++;
                }
                if (not.getInterupsiKlasifikasi().get(i).get(not.getInterupsiKlasifikasi().get(i).size()-1).equals(not.getInterupsi().keySet().toArray()[j+1])){
                    isFind = true;
                    i--;
                }
                i++;
            }
            j++;
        }
        System.out.println("penampung pembicara: " + penampungPembicara.size());
        int x = 0; int z = 0; 
        //System.out.println ("1 " + penampungAcara);
        //System.out.println ("2 " + penampungPembicara.size());
        while (x < penampungAcara.size()){
            int y = 0; int a = 0;
            boolean match = false; boolean find = false; 
            if (acaraFix.isEmpty()){
                System.out.println("masuk baru");
                acaraFix.add(penampungAcara.get(x));
                pembicaraFix.add(penampungPembicara.get(x));
                interupsiFix.add(penampungInterupsi.get(x));
            }
            else{
                while (a < acaraFix.size() && find == false){
                    if (acaraFix.get(a).equals(penampungAcara.get(x))){
                        y = a; 
                        find = true; 
                    }
                    else {
                        y = y + 1;
                    }
                    a++;
                }
                   
                while (y < acaraFix.size() && match == false){
                    if(pembicaraFix.get(y).equals(penampungPembicara.get(x))){// !(acaraFix.get(y).equals(penampungAcara.get(x)))){
                        //if (!(acaraFix.get(y).equals(penampungAcara.get(x))))
                            match = true; 
                        System.out.println("masuk sini");
                    }
                    y++;
                }
                    
                
                if (!match){
                    acaraFix.add(penampungAcara.get(x));
                    pembicaraFix.add(penampungPembicara.get(x));
                    interupsiFix.add(penampungInterupsi.get(x));
                }
            }
            x++;
        }
        Rule.ruleInterupsi(interupsiFix);
        x = 0; int c = 0; boolean find = false; 
        while (x < not.getJumlahInterupsi()-1){
            System.out.println("masuk sini 1");
            find = false;
            if (not.getAcaraRapat().size() > 1){
                while (c < pembicaraFix.size()&&find == false){
                    if (not.getAcaraRapat().get(x).equals(acaraFix.get(c))){
                        System.out.println("masuk sini 3");
                       c++; 
                    }
                    else {
                        System.out.println("masuk sini 2");
                        find = true;
                       x++;  
                       interupsiFix.remove(c-1);
                       pembicaraFix.remove(c-1);
                       acaraFix.remove(c-1);
                    }
                }
            }
        }
        
        
        System.out.println ("3 " + interupsiFix);
        System.out.println ("4 " + pembicaraFix);
        not.setProsesPembicara(pembicaraFix);
        not.setProsesInterupsi(interupsiFix);
        not.setProsesAcara(acaraFix);
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\fixMMR.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int k = 0; k < not.getProsesPembicara().size(); k++){
                    w.write(not.getProsesPembicara().get(k).toString()+ not.getProsesInterupsi().get(k).size() + not.getProsesInterupsi().get(k).toString());
                    w.newLine();
                }
            }
                    
                    
        } catch (IOException e) {
                System.err.println("Problem writing to the file statsTest.txt");
        }
//        not.setPembicara(pembicaraFix);
//        not.setIsiInterupsi(interupsiFix);
        
    }
    
    public void rankedSentencesMMR (Notulen not){
        MMR mmr = new MMR();
        //List<String> sentences = new ArrayList();
        List<List<String>> penampung = new ArrayList();
        
        int k = 0; int jumlahKata = 0; int kata = 0; int sumKata = 0;
        for (int i = 0; i < not.getProsesInterupsi().size(); i++){
            jumlahKata = 0; 
            sumKata = 0; 
            k=0;
            ArrayList<String> arr = new ArrayList<String>();
            ArrayList<String> terpilih = new ArrayList<String>();
            //ArrayList<String> selection = new ArrayList<String>();
            for (int j = 0; j < not.getProsesInterupsi().get(i).size()-1; j++){
                String a = not.getProsesInterupsi().get(i).get(j);
                arr.add(a);
            }
            ArrayList<String> selection = mmr.selectSentences(arr,arr.size());
            System.out.println("print arr " + selection);
            while (k < selection.size() && jumlahKata <= 100){
                String sentence = selection.get(k);
                kata = selection.get(k).split(" ").length;
                sumKata = jumlahKata + kata; 
                System.out.println("jumlah kata " + sumKata + " " + jumlahKata + " " + kata);
                if (sumKata <= 100){
                    terpilih.add(sentence);
                    jumlahKata += kata; 
                }
                k++; 
                
            }
            System.out.println("print xxx " + terpilih);
            penampung.add(terpilih);
        }
        not.setProsesInterupsi(penampung);
    }
    
    public void rankedSentencesILP (Notulen not, List<Integer> list){
        List<String> string = new ArrayList();
        List<List<String>> listString = new ArrayList();
        for (int i = 0 ; i < not.getProsesInterupsi().size(); i++){
            list = new ArrayList();
            string = new ArrayList(); 
            not.getProsesInterupsi().get(i).remove(not.getProsesInterupsi().get(i).size()-1);
            Mip.selectedSentencesILP(not, i, list);
            if (!list.isEmpty()){
                for (int j = 0; j < list.size(); j++){
                    System.out.println("hehe string: " + list);
                    string.add(not.getProsesInterupsi().get(i).get(list.get(j)-1));
                }
                listString.add(string);
            }
        }
        for (int i = 0 ; i < listString.size(); i++){
            System.out.println("string terpilih : " + listString.get(i));
        }
        System.out.println("this string " + string);
        
    }
    
    public void rankedJavaILP (Notulen not, List<Integer> list){
        List<String> string = new ArrayList();
        List<String> nullString = new ArrayList();
        List<List<String>> listString = new ArrayList();
        for (int i = 0 ; i < not.getProsesInterupsi().size(); i++){
            list = new ArrayList();
            string = new ArrayList(); 
            if (nullString.isEmpty()){
               nullString.add("null"); 
            }
            
            String lastIndex = not.getProsesInterupsi().get(i).get(not.getProsesInterupsi().get(i).size()-1);
            not.getProsesInterupsi().get(i).remove(not.getProsesInterupsi().get(i).size()-1);
            if (not.getProsesInterupsi().get(i).size() >= 1){
                ilpJava.selectedWithILP(not,i, list);
            }
            
            if (!list.isEmpty()){
                for (int j = 0; j < list.size(); j++){
                    System.out.println("hehe string: " + list);
                    string.add(not.getProsesInterupsi().get(i).get(list.get(j)));
                    
                }
                //string.add(lastIndex);
                listString.add(string);
            }
            else{
                listString.add(nullString);
            }
        }
        for (int i = 0 ; i < listString.size(); i++){
            System.out.println("string terpilih : " + listString.get(i));
        }
        System.out.println("this string " + string);
         not.setProsesInterupsi(listString);
        
    }
    
    public void rankedSentencesCosine (Notulen not, int maxSentences){
        List<List<String>> penampung = new ArrayList();
        int sumSentences = 0; int iterator = 0; 
        while (iterator < not.getProsesInterupsi().size()){
            if (not.getProsesInterupsi().get(iterator).size()-1 <= maxSentences && !not.getProsesInterupsi().get(iterator).isEmpty()){
                //System.out.println("2 kalimat");
                ArrayList<String> arr = new ArrayList<>();
                for (int i = 0; i < not.getProsesInterupsi().get(iterator).size() - 1; i++){
                    String a = not.getProsesInterupsi().get(iterator).get(i);
                    arr.add(a);
                }
                penampung.add(arr);
            }
            else if (not.getProsesInterupsi().get(iterator).size()-1 > maxSentences && !not.getProsesInterupsi().get(iterator).isEmpty()) {
                //System.out.println("lebih dari 2 kalimat");
                sumSentences = 0; 
                ArrayList<String> arr = new ArrayList<>();
                int jumlahKata = 0; int kata = 0; 
                while (sumSentences < maxSentences && iterator < not.getProsesInterupsi().size() ){
                    //int iterate = 0; 
                    double penampungNilai = 0.0; 
                    String penampungKalimat = ""; int x = 0;
                    
                    for (int i = 0; i < not.getProsesInterupsi().get(iterator).size()-1; i++){
                        double a = not.getValueCosine().get(iterator).get(i);
                        //String pilih = not.getProsesInterupsi().get(iterator).get(i);
                        if (a > penampungNilai){
                            penampungKalimat = not.getProsesInterupsi().get(iterator).get(i);
                            penampungNilai = a; 
                            
                            x = i;
                        }
                         
                    }
                    kata = penampungKalimat.split(" ").length + jumlahKata;
                    
                    if (jumlahKata <= 100 && kata <= 100 ){
                        arr.add(penampungKalimat);
                        jumlahKata += penampungKalimat.split(" ").length;
                        not.getProsesInterupsi().get(iterator).remove(x);
                        sumSentences++;
                    }
                    else {
                        not.getProsesInterupsi().get(iterator).remove(x);
                    }
                }
                penampung.add(arr);
            }
            iterator++;
        }
        //System.out.println("hehe penampung "+ penampung.get(3));
        //System.out.println(penampung.get(1));
        not.setProsesInterupsi(penampung);
    }
    
    public void calcCosineSim (Notulen not){
        List<List<Double>> valueCS = new ArrayList();
        List<List<Double>> valueCSKom = new ArrayList(); 
        List<Double> penampung = new ArrayList();
        
        for (int i = 0; i < not.getProsesInterupsi().size(); i++){
            
            for (int j = 0; j < not.getProsesInterupsi().get(i).size(); j++){
                
                double valueCosine = cs.Cosine_Similarity_Score(not.getProsesAcara().get(i).toString(), not.getProsesInterupsi().get(i).get(j));
                if (Double.isNaN(valueCosine)){
                    valueCosine = 0.0; 
                }
                penampung.add(valueCosine);
            }
            valueCS.add(penampung);
            penampung = new ArrayList();
        }
        not.setValueCosine(valueCS);
        penampung = new ArrayList();
        for (int i = 0; i < not.getProsesInterupsi().size(); i++){
            
            for (int j = 0; j < not.getProsesInterupsi().get(i).size() - 1; j++){
                for (int k = j+1; k < not.getProsesInterupsi().get(i).size(); k++){
                
                double valueCosine = cs.Cosine_Similarity_Score(not.getProsesInterupsi().get(i).get(j), not.getProsesInterupsi().get(i).get(k));
                if (Double.isNaN(valueCosine)){
                    valueCosine = 0.0; 
                }
                penampung.add(valueCosine);
                }
            }
            valueCSKom.add(penampung);
            penampung = new ArrayList();
        }
        not.setValueCosineKom(valueCSKom);
        
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\valueCS.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int k = 0; k < valueCS.size(); k++){
                    w.write(valueCS.get(k).toString());
                    w.newLine();
                    //System.out.println(valueCS.get(k).size());
                }
//                w.write("kombinasi");
//                for (int k = 0; k < valueCSKom.size(); k++){
//                    w.write(valueCSKom.get(k).toString());
//                    w.newLine();
//                    //System.out.println(valueCSKom.get(k).size());
//                }
                System.out.println("normal" + valueCS.get(0).size());
                //System.out.println("kombinasi" +valueCSKom.get(0).size());
            }
                    
                    
        } catch (IOException e) {
                System.err.println("Problem writing to the file statsTest.txt");
        }
        
    }
    
    public void calcCS(Notulen not){
        List<List<Double>> valueCS = new ArrayList();
        List<List<Double>> valueCSKom = new ArrayList(); 
        List<Double> penampung = new ArrayList();
        
        for (int i = 0; i < not.getProsesInterupsi().size(); i++){
            
            for (int j = 0; j < not.getProsesInterupsi().get(i).size(); j++){
                String str = not.getProsesInterupsi().get(i).stream().collect(Collectors.joining());
                double valueCosine = cs.Cosine_Similarity_Score(str, not.getProsesInterupsi().get(i).get(j));
                if (Double.isNaN(valueCosine)){
                    valueCosine = 0.0; 
                }
                penampung.add(valueCosine);
            }
            valueCS.add(penampung);
            penampung = new ArrayList();
        }
        not.setValueCosine(valueCS);
        penampung = new ArrayList();
        for (int i = 0; i < not.getProsesInterupsi().size(); i++){
            
            for (int j = 0; j < not.getProsesInterupsi().get(i).size() - 1; j++){
                for (int k = j+1; k < not.getProsesInterupsi().get(i).size(); k++){
                
                double valueCosine = cs.Cosine_Similarity_Score(not.getProsesInterupsi().get(i).get(j), not.getProsesInterupsi().get(i).get(k));
                if (Double.isNaN(valueCosine)){
                    valueCosine = 0.0; 
                }
                penampung.add(valueCosine);
                }
            }
            valueCSKom.add(penampung);
            penampung = new ArrayList();
        }
        not.setValueCosineKom(valueCSKom);
        
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\valueCS.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int k = 0; k < valueCS.size(); k++){
                    w.write(valueCS.get(k).toString());
                    w.newLine();
                    //System.out.println(valueCS.get(k).size());
                }
//                w.write("kombinasi");
//                for (int k = 0; k < valueCSKom.size(); k++){
//                    w.write(valueCSKom.get(k).toString());
//                    w.newLine();
//                    //System.out.println(valueCSKom.get(k).size());
//                }
                //System.out.println("normal" + valueCS.get(0).size());
                //System.out.println("kombinasi" +valueCSKom.get(0).size());
            }
                    
                    
        } catch (IOException e) {
                System.err.println("Problem writing to the file statsTest.txt");
        }
        
    }
}
