/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;

/**
 *
 * @author mochamadtry
 */
public class Klasifikasi {
//    String fileName = "G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\data\\run model\\prediksi.csv";
    public void prosesKlasifikasi (Notulen not, String fileName) throws IOException{
        readFileCSV(not, fileName);
        List<String> filterKlasifikasi = new ArrayList(); 
        for (int i = 0; i < not.getSentencesLine().size(); i++){
            System.out.println(not.getKlasifikasi().values().toArray()[i]);
            if (!not.getKlasifikasi().values().toArray()[i].equals("Other")){
                String line = not.getSentencesLine().get(i).toString();
                filterKlasifikasi.add(not.getSentencesLine().get(i).toString());
            }
        }
        System.out.println("filter " + filterKlasifikasi.size());
        not.setFilterKlasifikasi(filterKlasifikasi);
    }
    
    public void readFileCSV(Notulen not, String fileName) throws FileNotFoundException, IOException{
        Map<String, String> map = new LinkedHashMap<>();
        CSVReader reader = new CSVReader(new FileReader(fileName));
        List<String[]> myEntries = reader.readAll();
        myEntries.subList(0, 1).clear();
        for(int i = 0 ; i < myEntries.size(); i++ ){
            map.put(myEntries.get(i)[0], myEntries.get(i)[2]);
        }
        not.setKlasifikasi(map);
//        System.out.println(myEntries.get(1)[0]);
//        System.out.println(not.getKlasifikasi());
    }
    
    public void writeFile(Notulen not, String fileNameInput, String fileNameOutput) throws IOException {
        System.out.println("pass 1 ");
        List<String[]> result = new ArrayList();
        String line = "";
        List<String> kategoriKlasifikasi = new ArrayList(); 
//        BufferedReader input = new BufferedReader(new FileReader("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\hasilKlasifikasi.txt"));
        BufferedReader input = new BufferedReader(new FileReader(fileNameInput));
        System.out.println("pass 1 ");
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
        System.out.println("pass 1 ");
//        CSVWriter writer = new CSVWriter(new FileWriter("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\data\\run model\\prediksi.csv"));
                CSVWriter writer = new CSVWriter(new FileWriter(fileNameOutput));
        
        for (int i=0; i<result.size(); i++) {
            writer.writeNext(result.get(i));
        }
        writer.close();
        System.out.println("pass 2 ");
    }
    
}
