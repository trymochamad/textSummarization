/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan;

import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author mochamadtry
 */
public class MMR {
    double MRStc[]; //range nilai -1 sd 1
    
    TreeMap<String,Integer> tmCentroid;
    double pjgCentroid;

    //data per sentences
    ArrayList<Double> panjangTokenStc;              //diisi saat inisialisasi
    ArrayList<TreeMap<String,Integer>> arrTokenStc; //diisi saat inisialisasi

    ArrayList<Integer> processing,selected;              //diisi saat inisialisasi

    public MMR() {
        tmCentroid=new TreeMap<String,Integer>();
        panjangTokenStc=new ArrayList<Double>();
        arrTokenStc=new ArrayList<TreeMap<String, Integer>>();
        processing=new ArrayList<Integer>();
        selected=new ArrayList<Integer>();
    }

    public ArrayList<String> selectSentences(ArrayList<String> source, int number) {
        init(source);
        ArrayList<String> result=new ArrayList<String>();
        for (int i=0;i<number;i++) {
            System.out.println("Iterasi-"+i);
            calcCentroid();
            calcMRScore();
            //kata = penampungKalimat.split(" ").length + jumlahKata;
            int idxMax=idxMaxArray(MRStc);
            if (idxMax!=-1) {//ada yang terpilih
                System.out.println("max(MR)="+MRStc[idxMax]+"("+idxMax+")");
                Integer idxStc=processing.remove(idxMax);
                selected.add(idxStc);
                result.add(source.get(idxStc));
            }
        }
        return result;
    }

    public ArrayList<Integer> selectIdSentences() {
        //hanya dapat dipanggil setelah selectSentences diproses
        return selected;
    }
    
    int idxMaxArray(double[] arr) {
    //return idx array with maximum value; return -1 if not found
        if (arr.length>0) {
            int idMax=0;
            for (int j=1;j<arr.length;j++) {
                if (arr[j]>arr[idMax]) {
                    idMax=j;
                }
                //System.out.println("MR-"+j+"="+MR[j]);
            }
            return idMax;
        }
        else return -1;
    }

    void init(ArrayList<String> source) {
        panjangTokenStc.clear();
        arrTokenStc.clear();
        processing.clear();
        selected.clear();
        for (int i=0;i<source.size();i++) {
            String str=source.get(i);
            //buang tanda baca .,
            str=str.replaceAll("[\\.,]","");
            TreeMap<String,Integer> tm=Tokenizer.tokenize(str);
            //System.out.println(tm.toString());
            arrTokenStc.add(tm);
            panjangTokenStc.add(new Double(Tokenizer.length(tm)));
            processing.add(new Integer(i));
        }
    }

    void calcMRScore () {
    //i.s: processing is the set of all candidate sentences to be selected
    //     selected is the set of sentences already selected
    //     tmCentroid dan panjangCentroid sudah diupdate sesuai processing
           //MR(Si)=sim(Si,arrC)-max sim(Si,Sj); Sj in arrS
           //double[] simS=new double[arrS.size()];
        MRStc=new double[processing.size()];
        double simS;
        double[] simC=new double[processing.size()];
        double jumlahKata = 0.0; 
        for (int i=0;i<processing.size();i++) {
            int idxSource=processing.get(i); //index pada source awal
            TreeMap<String, Integer> tm=arrTokenStc.get(idxSource);
            Double panjang=panjangTokenStc.get(idxSource);
            simC[i]=calcStcSimilarity(tm, panjang, tmCentroid, pjgCentroid);
            if (!selected.isEmpty()) {
                double maxSim=0;
                for (int j=0;j<selected.size();j++) {
                    int idxSelected=selected.get(j); //index pada source awal
                    simS=calcStcSimilarity(tm, panjang, arrTokenStc.get(idxSelected), panjangTokenStc.get(idxSelected));
                    if (simS>maxSim && panjang <= 150 && jumlahKata<=150){
                        jumlahKata += panjang;
                        maxSim=simS;
                    }
                }
                MRStc[i]=simC[i]-maxSim;
                System.out.println(simC[i]+"; "+maxSim+"; "+MRStc[i]);
            }
            else {
                if (panjang <= 100){
                    MRStc[i]=simC[i];
                    jumlahKata += panjang;
                }
                System.out.println(simC[i]);
            } //belum ada kalimat yang dipilih masuk S
                
        }
    }

    void calcCentroid () {
    //i.s:  processing, arrTokenStc dan panjangTokenStc terdefinisi
    //f.s:  tmCentroid merupakan centroid processing;
    //      pjgCentroid sudah berisi panjang tmCentroid;
        tmCentroid.clear();
        for (int i=0;i<processing.size();i++) {
            int idxSource=processing.get(i);
            TreeMap<String, Integer> tm=arrTokenStc.get(idxSource);
            Double panjang=panjangTokenStc.get(idxSource);
            for (Map.Entry<String,Integer> entry:tm.entrySet())
            {
                String key1 = entry.getKey();
                int frek1 = entry.getValue().intValue();
                if (tmCentroid.containsKey(key1)) {
                    tmCentroid.put(key1,tmCentroid.get(key1).intValue()+frek1);
                }
                else
                    tmCentroid.put(key1,frek1);
            }
        }
        pjgCentroid=Tokenizer.length(tmCentroid);
    }

    public static double calcStcSimilarity (TreeMap<String,Integer> tm1, double pjg1,  TreeMap<String,Integer> tm2, double pjg2) {

        double stcSimilarity=0;
        int panjang1=tm1.size();
        int panjang2=tm2.size();

        if ((panjang1>0) && (panjang2>0)) {
            if (panjang1<=panjang2) {

                for (Map.Entry<String,Integer> entry:tm1.entrySet())
                {
                    String key1 = entry.getKey();
                    int frek1 = entry.getValue().intValue();
                    if (tm2.containsKey(key1)) {
                        stcSimilarity=stcSimilarity+frek1*tm2.get(key1).intValue();
                    }
                }

            } else { //panjang1>panjang2

                for (Map.Entry<String,Integer> entry:tm2.entrySet())
                {
                    String key2 = entry.getKey();
                    int frek2 = entry.getValue().intValue();
                    if (tm1.containsKey(key2)) {
                        stcSimilarity=stcSimilarity+frek2*tm1.get(key2).intValue();
                    }
                }
            }            
            stcSimilarity=stcSimilarity/(pjg1*pjg2);            
        }
        return stcSimilarity;
    }
    
}
