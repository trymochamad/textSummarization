/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasankhusus;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author mochamadtry
 */
public class IOFile {
    protected String namaFile; 
    public PraProses pp; 
    public Rule rule;
    
    public IOFile (){
        pp = new PraProses(); 
        rule = new Rule();
    }
    
    public IOFile(String file){ //konstruktor untuk rule
        namaFile = file; 
    }
    
    public List loadFile() throws FileNotFoundException, IOException{ //untuk rule
        String line = ""; 
        String text = "";
        List textNotulen = new ArrayList(); 
        // Open the file.        
	BufferedReader input = new BufferedReader(new FileReader(namaFile));
	StringBuilder stringBuilder = new StringBuilder();
        while ((line = input.readLine()) != null) {
            stringBuilder.append(line + "\n");
            textNotulen.add(line);
	}        
	text = stringBuilder.toString();
        return textNotulen;
    }
    
    public void loadFile(Notulen not) throws IOException {
        //PraProses pp = new PraProses();
        List<String> text_ = new ArrayList();
        List<String> penampung = new ArrayList();
        String line = "";
	String text = convertPDF(not);
        String file = not.getFile();
	// Open the file.
	BufferedReader input = new BufferedReader(new FileReader(file));
	StringBuilder stringBuilder = new StringBuilder();
        //set text
	while ((line = input.readLine()) != null) {
		stringBuilder.append(line + "\n");
                text_.add(line);
	}
	text = stringBuilder.toString();
	not.setText(text);
//        penampung = text_;
//        Rule.processPembicara(penampung);
//        not.setSentencesLine(penampung);
        List teks = new ArrayList();
        for (int i = 0 ; i < text_.size(); i++) {
            String s1 = text_.get(i);
            if (s1.matches(".*[a-zA-Z]+.*") && s1.length() > 2) {
                   teks.add(s1);
            }
        }
//        for(int j=0; j< teks.size(); j++) {
//            String formalized = pp.preprocessTeksAwal(teks.get(j).toString());
//            teks.set(j, formalized);
//        }
        not.setSentences(teks);
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\Cobapembicara.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int i = 0; i < not.getSentences().size(); i++){
                    w.write(not.getSentences().get(i).toString());
                    w.newLine();
                }
            }       
        } catch (IOException e) {
                System.err.println("Problem writing to the file statsTest.txt");
        }
    }
    
    public void writeResultSystem (Notulen not) throws FileNotFoundException{ // make file for data test from system
         try {
            //Whatever the file path is.
            File statText = new File("result.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            BufferedWriter w = new BufferedWriter(osw);
            if(!not.getJenisRapat().isEmpty()){
                w.write(not.getJenisRapat().get(0));
                w.newLine();
            }
            
            if(!not.getTahunSidang().isEmpty()){
                w.write(not.getTahunSidang().get(0));
                w.newLine();
            }
            
            if(!not.getMasaSidang().isEmpty()){
                w.write(not.getMasaSidang().get(0));
                w.newLine();
            }
            
            if(!not.getUrutanRapat().isEmpty()){
                w.write(not.getUrutanRapat().get(0));
                w.newLine();
            }
            
            if(!not.getJenisRapat().isEmpty()){
                w.write(not.getJenisRapat().get(0));
                w.newLine();
            }
            
            if(!not.getSifatRapat().isEmpty()){
                w.write(not.getSifatRapat().get(0));
                w.newLine();
            }
            
            if(!not.getHariRapat().isEmpty()){
                w.write(not.getHariRapat().get(0));
                w.newLine();
            }
            
            if(!not.getTanggalRapat().isEmpty()){
                w.write(not.getTanggalRapat().get(0));
                w.newLine();
            }
            
            if(!not.getRapatDibuka().isEmpty()){
                w.write(not.getRapatDibuka().get(0));
                w.newLine();
            }
            
            if(!not.getTempatRapat().isEmpty()){
                
                 for (int i = 0; i < not.getTempatRapat().size(); i++){
                    w.write(not.getTempatRapat().get(i));
                    //w.newLine();
                }
                 w.newLine();
            }
            
            if(!not.getKotaRapat().isEmpty()){
                w.write(not.getKotaRapat().get(0));
                w.newLine();
            }
            
            if(!not.getKetuaRapat().isEmpty()){
                w.write(not.getKetuaRapat().get(0));
                w.newLine();
            }
            
            if(!not.getKetuaRapat().isEmpty()){
                w.write(not.getKetuaRapat().get(1));
                w.newLine();
            }
            
//            if(!not.getKetuaRapat().isEmpty()){
//                for (int i = 0; i < not.getKetuaRapat().size(); i++){
//                    w.write(not.getKetuaRapat().get(i));
//                    //w.newLine();
//                }
//            }
//            w.newLine();
            if(!not.getPendampingKetua().isEmpty()){
                for (int i = 0; i < not.getPendampingKetua().size(); i++){
                    w.write(not.getPendampingKetua().get(i));
                    //w.newLine();
                }
            }
            
            w.newLine();
            if(!not.getAcaraRapat().isEmpty()){
                for (int i = 0; i < not.getAcaraRapat().size(); i++){
                    w.write(not.getAcaraRapat().get(i));
                }
            }
            else if(not.getAcaraRapat().isEmpty()){
                    for (int i = 0; i < not.getAcara().size(); i++){
                        w.write(not.getAcara().get(i));
                    }
            }
            
            w.newLine();
            
            if(!not.getSekretarisRapat().isEmpty()){
                w.write(not.getSekretarisRapat().get(0));
                w.newLine();
            }
            
            if(!not.getSekretarisRapat().isEmpty()){
                w.write(not.getSekretarisRapat().get(1));
                w.newLine();
            }
//            if(!PatternRegex.sekretarisRapat.isEmpty()){
//                for (int i = 0; i < PatternRegex.sekretarisRapat.size(); i++){
//                    w.write(PatternRegex.sekretarisRapat.get(i));
//                    //w.newLine();
//                }
//            }
            if(!not.getPendampingSekretaris().isEmpty()){
                for (int i = 0; i < not.getPendampingSekretaris().size(); i++){
                    w.write(not.getPendampingSekretaris().get(i));
                    //w.newLine();
                }
            }
            
            if(!not.getAnggotaHadir().isEmpty()){
                for (int i = 0; i < not.getAnggotaHadir().size(); i++){
                    w.write(not.getAnggotaHadir().get(i));
                    w.newLine();
                }
            }
            
            if(!not.getRapatDibuka().isEmpty()){
                w.write(not.getRapatDibuka().get(0));
                w.newLine();
            }
            
            if(!not.getKetuaRapat().isEmpty()){
                w.write(not.getKetuaRapat().get(0));
                w.newLine();
            }
            
            if(!not.getKetuaRapat().isEmpty()){
                w.write(not.getKetuaRapat().get(1));
                w.newLine();
            }
            
            if(!not.getAnggotaHadir().isEmpty()){
                for (int i = 0; i < not.getAnggotaHadir().size(); i++){
                    w.write(not.getAnggotaHadir().get(i));
                    w.newLine();
                }
            }
            
            if(!not.getJenisRapat().isEmpty()){
                w.write(not.getJenisRapat().get(0));
                w.newLine();
            }
            
            if(!not.getAcaraRapat().isEmpty()){
                for (int i = 0; i < not.getAcaraRapat().size(); i++){
                    w.write(not.getAcaraRapat().get(i));
                }
            }
            else if(not.getAcaraRapat().isEmpty()){
                    for (int i = 0; i < not.getAcara().size(); i++){
                        w.write(not.getAcara().get(i));
                    }
            }
            
            w.newLine();
            if(!not.getJenisRapat().isEmpty()){
                w.write(not.getJenisRapat().get(0));
                w.newLine();
            }
            if(!not.getPenggantiAntarWaktu().isEmpty()){
                for (int i = 0; i < not.getPenggantiAntarWaktu().size(); i++){
                    w.write(not.getPenggantiAntarWaktu().get(i));
                }
            }
//            else if (!not.getPenggantiAntarWaktuTambahan().isEmpty()){
//                for (int i = 0; i < not.getPenggantiAntarWaktuTambahan().size(); i++){
//                    w.write(not.getPenggantiAntarWaktuTambahan().get(i));
//                }
//            }
            
            w.newLine();
            
            
            
            if(!not.getPucukSuratMasuk().isEmpty()){
                String numSurat = Rule.intToString(not.getPucukSuratMasuk().size());
                String num = Rule.intToInt(not.getPucukSuratMasuk().size());
                //int y = Integer.parseInt(not.getPucukSuratMasuk().size());
                w.write(num);
                w.newLine();
                w.write(numSurat);
                w.newLine();
                for (int i = 0; i < not.getPucukSuratMasuk().size(); i++){
                    w.write(not.getPucukSuratMasuk().get(i));
                }
            }
//            if(!not.getSepucukSuratMasuk().isEmpty()){
//               for (int i = 0; i < not.getSepucukSuratMasuk().size(); i++){
//                    w.write(not.getSepucukSuratMasuk().get(i));
//                } 
//            }
            
            w.newLine();
            
            if((not.getKesimpulan().size()>0)){
                for (int i = 0; i < not.getKesimpulan().size(); i++){
                    w.write(not.getKesimpulan().get(i));
                }
            }
            w.newLine();
            
            if(!not.getRapatDitutup().isEmpty())
                w.write(not.getRapatDitutup().get(0));
            w.newLine();
            
            if(!not.getKotaRapat().isEmpty()){
                w.write(not.getKotaRapat().get(0));
                w.newLine();
            }
            
            if(!not.getTanggalRapat().isEmpty()){
                w.write(not.getTanggalRapat().get(0));
                w.newLine();
            }
            
            if(!not.getKetuaRapat().isEmpty()){
                w.write(not.getKetuaRapat().get(0));
                w.newLine();
            }
            
            if(!not.getBiroPersidangan().isEmpty()){
                w.write(not.getBiroPersidangan().get(0));
                w.newLine();
            }
            
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.txt");
        }
    }
    
    public static String convertPDF(Notulen not){
        String text_ = null;
        try {
		// extract text using library
                //G:\\Kuliah\\7th Semester\\Tugas Akhir\\Data Anotasi\\Filter\\Format Unstructured\\merge_persipar-Risalah-Rapat-Risalah-Resmi-Rapat-Paripurna-ke-16-1455776246.pdf
//                Scanner scan = new Scanner(System.in);
//                String nameOfFile= scan.nextLine();
//                File f = new File(nameOfFile);
//                String fileName = f.getName();
//                String path = f.getAbsolutePath();
//                not.setPath(path);
                //System.out.println(path);
		PDDocument doc = PDDocument.load(new File(not.getPath())); // put path to your input pdf file here
                //doc.removePage(3);
                //doc.removePage(4);
                //doc.removePage(1);
                //doc.getDocumentInformation();
                //System.out.println(doc.getDocumentInformation());
		String text =  new PDFTextStripper().getText(doc);
                text_ = text;
                //System.out.println("Text in PDF: " + text );
		    
		// write the content to text file
		PrintWriter pw; // put path to your output text file here
                pw = new PrintWriter(new FileWriter("G:\\Kuliah\\7th Semester\\Tugas Akhir\\Data Anotasi\\Filter\\result.txt"));
		pw.println();
                pw.write(text);
                
                not.setFile("G:\\Kuliah\\7th Semester\\Tugas Akhir\\Data Anotasi\\Filter\\result.txt");
		pw.close();  
        } catch (IOException e) {
            e.printStackTrace();
	}
        
        return text_; 
    }
    
    
    public void segmentasiTeks (Notulen not){
        List<String> valueBagianIsi = new ArrayList();
        List<String> valueBagianAwal = new ArrayList();
        List<String> valueIsi = new ArrayList();
        List<String> daftarPola = Arrays.asList(".*Pertama-tama marilah kita mengucapkan puji.*",".*Pertama-tama marilah kita bersyukur dan memanjatkan puji.*|.*Pertama-tama marilah kita panjatkan puji dan syukur.*");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while(i<not.getSentences().size() - 1 && isFind == false ){
            Pattern pattern = Pattern.compile(daftarPola.get(0));
            String teks = not.getSentences().get(i).toString();
            Matcher matcher = pattern.matcher(teks);

            if (!matcher.find()) {
                valueBagianAwal.add(teks); 
            }
            else {
                isFind = false;
                j = i; 
                
            }
            ++i;
        }  
        System.out.println ("nilai j " + j); 
        
        while (j < not.getSentences().size() - 1 ){
            String teks = not.getSentences().get(j).toString();
            valueBagianIsi.add(teks);
            j++; 
            
        }
        PraProses.processPembicara(valueBagianIsi);
        
        if (!valueBagianIsi.isEmpty()){
            
            String str = valueBagianIsi.stream().collect(Collectors.joining());
            valueIsi = pp.sentencesSpliter(str);
        }
        
        if (!isFind) {
            System.out.println("tidak ditemukan");
        } 
        
        not.setBagianAwal(valueBagianAwal);
        not.setBagianIsi(valueIsi);
        
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\segmentasiIsi.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int k = 0; k < valueIsi.size(); k++){
                    w.write(valueIsi.get(k));
                    w.newLine();
                }
            }
                    
                    
        } catch (IOException e) {
                System.err.println("Problem writing to the file segmentasiIsi.txt");
        }
    }
    
    
}
