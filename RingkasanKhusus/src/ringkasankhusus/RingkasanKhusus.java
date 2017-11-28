/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasankhusus;
import java.io.IOException;
import java.util.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author mochamadtry
 */
public class RingkasanKhusus {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException, IOException {
        // TODO code application logic here
        //IOFile.convertPDF();
        //TemplateGenerator.createPDF();
        //IOFile.makeHTMLFile();
        //Scanner scan = new Scanner(System.in);
        //String nameOfFile= scan.nextLine();
        Notulen not  = new Notulen();
        //File f = new File(nameOfFile);
        IOFile file = new IOFile();
        file.loadFile(not);
//        System.out.println(not.getSentences()); //jadinya ga di lowercase dulu 
//        System.out.println(not.getSentencesLine());
        PatternRegex pr = new PatternRegex();
        pr.regexTahunSidang(not);
        pr.regexMasaSidang(not);
        pr.regexUrutanRapat(not);
        pr.regexJenisRapat(not);
        pr.regexSifatRapat(not);
        pr.regexHariRapat(not);
        pr.regexTanggalRapat(not);
        pr.regexRapatDibuka(not);
        pr.regexRapatDitutup(not);
        pr.regexTempatRapat(not);
        pr.regexKotaRapat(not);
        pr.regexKetuaRapat(not);
        pr.regexPendampingKetua(not);
        pr.regexSekretarisRapat(not);
        pr.regexPendampingSekretaris(not);
        pr.regexAnggotaHadir(not);
        pr.regexSepucukSuratMasuk(not);
        pr.regexPucukSuratMasuk(not);
        pr.regexPenggantiAntarWaktu(not);
        pr.regexBiroPersidangan(not);
        pr.regexAcara(not);
        pr.regexAcaraRapat(not);
        pr.regexKesimpulan(not);
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
        System.out.println("q" + not.getPucukSuratMasuk());
        System.out.println("q1" + not.getSepucukSuratMasuk());
        System.out.println("r" + not.getPenggantiAntarWaktu());
        System.out.println("r1" + not.getPenggantiAntarWaktuTambahan());
        System.out.println("s" + not.getBiroPersidangan());
        System.out.println("t"+ not.getAcara());
        System.out.println("u"+ not.getAcaraRapat());
        System.out.println("w"+ not.getKesimpulan());
        file.writeResultSystem(not);
        CreatePDF PDF = new CreatePDF(not);
        /*G:\Kuliah\8th semester\TA II\tugas-akhir\RingkasanKhusus\data\'unstructured\16.txt*/
    }   // G:\\Kuliah\\7th Semester\\Tugas Akhir\\Data Anotasi\\Filter\\Format Unstructured\\merge_persipar-Risalah-Rapat-Risalah-Resmi-Rapat-Paripurna-ke-16-1455776246.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 17 MS II 27 Januari 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 23 MS III 7 April 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 22 MS III 23 Maret 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 21 MS II 18 Februari 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 24  MS III 14 April 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 14 MS II 12 Januari 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 15 MS II 15 Januari 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 16 MS II 20 Januari 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 34 MS IV 25 Juni 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 33 MS IV 23 Juni 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 35 MS IV 1 Juli 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 31 MS IV 28 Mei 2015 PPP.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 26 MS III 21 April 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 27 MS III 24 April 2015.pdf
    //C:\Users\Public\Documents\ScanDoc\merge_RAPAT 17 MS III 24 Januari 2017.pdf
    
}
