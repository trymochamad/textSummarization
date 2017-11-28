/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;

/**
 *
 * @author mochamadtry
 */
public class Klasifikasi {
    
    String fileName = "G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\data\\run model\\prediksi.csv";
    public void prosesKlasifikasi (Notulen not) throws IOException{
        readFileCSV(not);
        List<List<String>> prosesIsiInterupsi = new ArrayList();
        int j = 0; 
        for (int i = 0; i < not.getSegmentasiAcara().keySet().size(); i++){ //not.getSegmentasiAcara().keySet().size(); i++){
            List<String> penampung = new ArrayList();
            int start = Integer.parseInt(not.getSegmentasiAcara().keySet().toArray()[i].toString());
            
            while (j < not.getIsiInterupsi().get(i).size()-1){
                String line = not.getIsiInterupsi().get(i).get(j);
//                if (not.getKlasifikasi().values().toArray()[start].equals("Statement")){
//                    penampung.add(not.getIsiInterupsi().get(i).get(j)); 
//                }
                if (!not.getKlasifikasi().values().toArray()[start].equals("Other")){
                    penampung.add(not.getIsiInterupsi().get(i).get(j)); 
                }
                else {
                    //System.out.println("kelas Other");
                }
//                penampung.add(not.getIsiInterupsi().get(i).get(j)); 
                start++; 
                j++;
                
            }
            penampung.add(not.getIsiInterupsi().get(i).get(j));
            j = 0;
            prosesIsiInterupsi.add(penampung);
            
        }
        not.setInterupsiKlasifikasi(prosesIsiInterupsi);
    }
    
    public void readFileCSV(Notulen not) throws FileNotFoundException, IOException{
        Map<String, String> map = new LinkedHashMap<>();
        CSVReader reader = new CSVReader(new FileReader(fileName));
        List<String[]> myEntries = reader.readAll();
        myEntries.subList(0, 1).clear();
        for(int i = 0 ; i < myEntries.size(); i++ ){
            map.put(myEntries.get(i)[0], myEntries.get(i)[2]);
        }
        not.setKlasifikasi(map);
        System.out.println(myEntries.get(1)[0]);
    }
    
    public void writeFile(Notulen not) throws IOException {
        List<String[]> result = new ArrayList();
        String line = "";
        List<String> kategoriKlasifikasi = new ArrayList(); 
        BufferedReader input = new BufferedReader(new FileReader("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\hasilKlasifikasi.txt"));
	StringBuilder stringBuilder = new StringBuilder();
        //set text
	while ((line = input.readLine()) != null) {
		stringBuilder.append(line + "\n");
                kategoriKlasifikasi.add(line);
	}
        result.add(new String[] {"ID","Kalimat","Kelas"});
        for (int i = 0; i < kategoriKlasifikasi.size(); i++){
            String[] record = new String[3];
            record[0] = "" + i;
            
            record[1] = not.getSentencesLine().get(i).toString() ;
            record[2] = kategoriKlasifikasi.get(i);
            
            
            result.add(record);
        }
        CSVWriter writer = new CSVWriter(new FileWriter("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\data\\run model\\prediksi.csv"));
        for (int i=0; i<result.size(); i++) {
            writer.writeNext(result.get(i));
        }
        writer.close();
    }
        
    
}
