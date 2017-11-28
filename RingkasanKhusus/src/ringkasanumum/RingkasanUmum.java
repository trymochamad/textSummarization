/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum;

import com.itextpdf.text.DocumentException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ringkasanumum.klasifikasiretorik.*;

/**
 *
 * @author mochamadtry
 */
public class RingkasanUmum {
    public static List<String> text_ = new ArrayList();
    public static List<String> sentences = new ArrayList();
    public static List<String> agendaRapat = new ArrayList();
    public static Map<String, Integer> map = new HashMap<>();
    public static Map<String, List<String>> mapAgenda = new HashMap<>();
  
    /**
     * @param args the command line arguments
     * @throws com.itextpdf.text.DocumentException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws DocumentException, IOException, SQLException, Exception {
        // TODO code application logic here
        List<String> kalimat = new ArrayList(); 
        kalimat.add("Adakah.Menindaklanjuti penugasan tersebut");
        kalimat.add("RAPAT DIBUKA PUKUL 16.20 WIB");
        Praproses.addSpaceSentences(kalimat);
        System.out.println(10%3);
//        Scanner scan = new Scanner(System.in);
//        String nameOfFile= scan.nextLine();
//        Notulen not  = new Notulen();
//        RegexGenerator rg = new RegexGenerator();
//        File f = new File(nameOfFile);
//        Klasifikasi predict = new Klasifikasi();
//        Praproses pp = new Praproses();
//        IOFile file = new IOFile(); 
//        SelectedSentences ss = new SelectedSentences();
//        ringkasanumum.klasifikasiretorik.Klasifikasi klasifikasi = new ringkasanumum.klasifikasiretorik.Klasifikasi();
//        ringkasanumum.klasifikasiretorik.FileUpload upload = new ringkasanumum.klasifikasiretorik.FileUpload(); 
        
        
//        System.out.println("file " + f);
//        file.loadFile(not);
//        upload.postFile(not);
//        klasifikasi.klasifikasi(not);
//        predict.writeFile(not);
//        rg.regexTanggalRapat(not);
//        rg.regexTahunSidang(not);
//        rg.regexMasaPersidangan(not);
//        rg.regexUrutanRapat(not);
//        rg.regexDaftarHadirAnggota(not);
//        agendaRapat = rg.regexAcara(not);
//        map = rg.partSegmentasi(not);
//        mapAgenda = rg.regexInterupsi(not);
//        predict.prosesKlasifikasi(not);
        
//        List<Integer> list = new ArrayList();
//        ss.sentencesJoin(not);
//        ss.calcCosineSim(not);
//        ss.calcCS(not);
//        ss.rankedSentencesCosine(not, 2);
//        ss.rankedJavaILP(not, list);
//        ss.rankedSentencesMMR(not);
//        
//        
//        System.out.println(not.getTanggalRapat());
//        System.out.println(not.getTahunSidang());
//        System.out.println(not.getMasaPersidangan());
//        System.out.println(not.getUrutanRapat());
//        System.out.println(not.getDaftarHadirAnggota());
//        System.out.println(not.getJumlahHadirAnggota());
//        System.out.println(not.getAcaraRapat().size());
//        System.out.println(not.getInterupsi());
//        System.out.println(not.getSegmentasiAcara());
//        System.out.println(not.getPembicara());
//        System.out.println(not.getSegmentasiAcara().get(not.getSegmentasiAcara().keySet().toArray()[2]));
//        System.out.println(not.getIsiInterupsi());
//        
//        TemplateGenerator template = new TemplateGenerator(not);
//        try {
//            //Whatever the file path is.
//            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\pembicara.txt");
//            FileOutputStream is = new FileOutputStream(statText);
//            OutputStreamWriter osw = new OutputStreamWriter(is);    
//            try (BufferedWriter w = new BufferedWriter(osw)) {
//                for (int i = 0; i < not.getInterupsiKlasifikasi().size(); i++){
//                    w.write(not.getPembicara().get(i).toString());
//                    w.newLine();
//                }
//            }
//                    
//                    
//        } catch (IOException e) {
//                System.err.println("Problem writing to the file statsTest.txt");
//        }
        //data\txt\dpr_old (1).txt
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\dpr (1).pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merged_RAPAT 17 MS III 24 Januari 2017.pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merge_RAPAT 23 MS III 7 April 2015.pdf ; acaranya cuman satu 
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merge_RAPAT 24  MS III 14 April 2015.pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merge_RAPAT 35 MS IV 1 Juli 2015.pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merge_RAPAT 26 MS III 21 April 2015.pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merge_RAPAT 27 MS III 24 April 2015.pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merge_RAPAT 34 MS IV 25 Juni 2015.pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merge_RAPAT 31 MS IV 28 Mei 2015 PPP.pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\dpr (10).pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merged_persipar-Risalah-Rapat-Risalah-Resmi-Rapat-Paripurna-ke-16-1455776246.pdf
        //G:\Kuliah\7th Semester\Tugas Akhir\Data Anotasi\Data\perisalah rapat\korpus yang dipakai\pdf\merged_RAPAT 17 MS III 24 Januari 2017.pdf
    }
    
}
