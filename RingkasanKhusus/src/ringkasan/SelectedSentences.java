/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan;

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
    public void rankedSentencesMMR (Notulen not){
        MMR mmr = new MMR();
        //List<String> sentences = new ArrayList();
        List<String> penampung = new ArrayList();
        
        int k = 0; int jumlahKata = 0; int kata = 0; int sumKata = 0;
            ArrayList<String> arr = new ArrayList<String>();
            ArrayList<String> terpilih = new ArrayList<String>();
            //ArrayList<String> selection = new ArrayList<String>();
            for (int j = 0; j < not.getFilterKlasifikasi().size(); j++){
                String a = not.getFilterKlasifikasi().get(j).toString();
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
//            penampung.add(terpilih);
            not.setFilterKlasifikasi(terpilih);
        
//        MMR mmr = new MMR();
//        //List<String> sentences = new ArrayList();
//        List<List<String>> penampung = new ArrayList();
//        
//        int k = 0; int jumlahKata = 0; int kata = 0; int sumKata = 0;
//        for (int i = 0; i < not.getProsesInterupsi().size(); i++){
//            jumlahKata = 0; 
//            sumKata = 0; 
//            k=0;
//            ArrayList<String> arr = new ArrayList<String>();
//            ArrayList<String> terpilih = new ArrayList<String>();
//            //ArrayList<String> selection = new ArrayList<String>();
//            for (int j = 0; j < not.getProsesInterupsi().get(i).size()-1; j++){
//                String a = not.getProsesInterupsi().get(i).get(j);
//                arr.add(a);
//            }
//            ArrayList<String> selection = mmr.selectSentences(arr,arr.size());
//            System.out.println("print arr " + selection);
//            while (k < selection.size() && jumlahKata <= 100){
//                String sentence = selection.get(k);
//                kata = selection.get(k).split(" ").length;
//                sumKata = jumlahKata + kata; 
//                System.out.println("jumlah kata " + sumKata + " " + jumlahKata + " " + kata);
//                if (sumKata <= 100){
//                    terpilih.add(sentence);
//                    jumlahKata += kata; 
//                }
//                k++; 
//                
//            }
//            System.out.println("print xxx " + terpilih);
//            penampung.add(terpilih);
//        }
//        not.setProsesInterupsi(penampung);
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
    
    public void calcCS(Notulen not){
        List<List<Double>> valueCS = new ArrayList();
        List<List<Double>> valueCSKom = new ArrayList(); 
        List<Double> penampung = new ArrayList();
        
        for (int i = 0; i < not.getSentencesLine().size(); i++){
            String str = (String) not.getSentencesLine().stream().collect(Collectors.joining());
            System.out.println("str " + str);
            double valueCosine = cs.Cosine_Similarity_Score(str, not.getSentencesLine().get(i).toString());
            if (Double.isNaN(valueCosine)){
                valueCosine = 0.0; 
            }
            penampung.add(valueCosine);
        }
        valueCS.add(penampung);
        not.setValueCosine(valueCS);
        
//        penampung = new ArrayList();
//        for (int j = 0; j < not.getSentencesLine().size() - 1; j++){
//            for (int k = j+1; k < not.getSentencesLine().size(); k++){
//                
//                double valueCosine = cs.Cosine_Similarity_Score(not.getSentencesLine().get(j).toString(), not.getSentencesLine().get(k).toString());
//                if (Double.isNaN(valueCosine)){
//                    valueCosine = 0.0; 
//                }
//                penampung.add(valueCosine);
//                }
//            }
//        valueCSKom.add(penampung);
//        not.setValueCosineKom(valueCSKom);
        
        
//        for (int i = 0; i < not.getProsesInterupsi().size(); i++){
//            
//            for (int j = 0; j < not.getProsesInterupsi().get(i).size(); j++){
//                String str = not.getProsesInterupsi().get(i).stream().collect(Collectors.joining());
//                double valueCosine = cs.Cosine_Similarity_Score(str, not.getProsesInterupsi().get(i).get(j));
//                if (Double.isNaN(valueCosine)){
//                    valueCosine = 0.0; 
//                }
//                penampung.add(valueCosine);
//            }
//            valueCS.add(penampung);
//            penampung = new ArrayList();
//        }
//        not.setValueCosine(valueCS);
//        penampung = new ArrayList();
//        for (int i = 0; i < not.getProsesInterupsi().size(); i++){
//            
//            for (int j = 0; j < not.getProsesInterupsi().get(i).size() - 1; j++){
//                for (int k = j+1; k < not.getProsesInterupsi().get(i).size(); k++){
//                
//                double valueCosine = cs.Cosine_Similarity_Score(not.getProsesInterupsi().get(i).get(j), not.getProsesInterupsi().get(i).get(k));
//                if (Double.isNaN(valueCosine)){
//                    valueCosine = 0.0; 
//                }
//                penampung.add(valueCosine);
//                }
//            }
//            valueCSKom.add(penampung);
//            penampung = new ArrayList();
//        }
//        not.setValueCosineKom(valueCSKom);
        
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\valueCS.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int k = 0; k < penampung.size(); k++){
                    w.write(penampung.get(k).toString());
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
