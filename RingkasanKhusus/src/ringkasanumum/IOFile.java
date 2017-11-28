/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author mochamadtry
 */
public class IOFile {
    
    public Praproses pp; 
    
    public IOFile (){
        pp = new Praproses(); 
    }
    
    public void loadFile(Notulen not, File file) throws IOException {
        List<String> text_ = new ArrayList();
        List<String> kalimat = new ArrayList(); 
        List<String> texs = new ArrayList(); 
        String path = file.getAbsolutePath();
        String line = "";
	//String text = "";
	//not.setFile(file.getPath());
        String fileName = file.getName();
        not.setFile(fileName);
        
        String text = convertPDF(not);
        String files = not.getFile();

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
        not.setSentences(text_); //dapetin sentences perline
        
        for (int k = 0 ; k < text_.size(); k++){
            texs.add(text_.get(k));
        }
        Praproses.processPembicara(texs);
        kalimat = pp.sentencesSpliter(text);
        //set sentences diambil kalimat split per titik
        //String[] sentences = text.split("\\r?\\n ");
        //System.out.println(sentences.length);
        
//        List teks = new ArrayList();
//        for (int i = 0 ; i < text_.size(); i++) {
//            String s1 = text_.get(i);//.toString();//.replaceAll("[^\\w\\s]", "").replaceAll("[\\t\\n\\r]", " ");
//            //String s1 = s.replaceAll("[^\\w\\s]", "").replaceAll("[\\t\\n\\r]", " ");//.toLowerCase();
//            if (s1.matches(".*[a-zA-Z]+.*") && s1.length() > 2) {
//                   teks.add(s1);
//            }
//        }
        
        List teks = new ArrayList();
        for (int i = 0 ; i < kalimat.size(); i++) {
            String s1 = kalimat.get(i);//.toString();//.replaceAll("[^\\w\\s]", "").replaceAll("[\\t\\n\\r]", " ");
            //String s1 = s.replaceAll("[^\\w\\s]", "").replaceAll("[\\t\\n\\r]", " ");//.toLowerCase();
            if (s1.matches(".*[a-zA-Z]+.*") && s1.length() > 2) {
                   teks.add(s1);
            }
        }
        not.setSentencesLine(teks); //sentences per kalimat 
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\kalimat.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int i = 0; i < teks.size(); i++){
                    w.write(teks.get(i).toString());
                    w.newLine();
                }
            }       
        } catch (IOException e) {
                System.err.println("Problem writing to the file kalimat.txt");
        }
    }
    
    public void loadFile(Notulen not) throws IOException {
        //PraProses pp = new PraProses();
        List<String> text_ = new ArrayList();
        String penampung = "";
        List<String> kalimat = new ArrayList();
        List<String> kalimatPP = new ArrayList();
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
	//not.setText(text);
        not.setKalimat(text_);
//        penampung = text_;
//        not.setSentencesLine(penampung);
        segmentasiTeks(not,text_);
//        Praproses.processPembicara(text_);//komentar
//        Praproses.addSpaceSentences(text_); //komentar
        Praproses.processPembicara(not.getBagianIsi());
        Praproses.deleteDot(not.getBagianIsi());
        
        Praproses.addSpaceSentences(not.getBagianIsi());
//        for (int k = 0; k<not.getKalimat().size(); k++){
//            String kal = not.getKalimat().get(k).toString();
//            penampung = pp.praprocessSentences(kal);
//            kalimatPP.add(penampung);
//        }
//        text_.clear();
//        for (int j = 0; j < kalimatPP.size(); j++){
//            text_.add(kalimatPP.get(j));
//        }
//        String str = text_.stream().collect(Collectors.joining()); //komentar
        String str = not.getBagianIsi().stream().collect(Collectors.joining());
        kalimat = pp.sentencesSpliter(str);
        //Praproses.deletePageNumber(kalimat);
        String[] sentences = str.split("(?<=[.!?])\\s* ");
        List teks = new ArrayList();
//        for (String sentence : sentences) {
//            String s1 = sentence;//.replaceAll("[^\\w\\s]", "").replaceAll("[\\t\\n\\r]", " ");
//            if (s1.matches(".*[a-zA-Z]+.*") && s1.length() > 2) {
//                
//                   teks.add(s1);
//            }
//        }
//        List teks = new ArrayList();
        for (int i = 0 ; i < kalimat.size(); i++) {
            String s1 = kalimat.get(i);
            if (s1.matches(".*[a-zA-Z]+.*") && s1.length() > 2) {
                
                   teks.add(s1);
            }
        }
        not.setSentencesLine(teks); 
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\hasilKalimat.txt"); //file yang akan digunakan berisi kalimat klasifikasi 
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int i = 0; i < teks.size(); i++) {
                    w.write(teks.get(i).toString());
                    w.newLine();
                }
            }       
        } catch (IOException e) {
                System.err.println("Problem writing to the file statsTest.txt");
        }
    }
        
    public List readFile(Notulen not, String teks) throws IOException{ //tidak dipakai 
        Scanner scan = new Scanner(System.in); 
        List text_ = new ArrayList();
        String nameOfFile= scan.nextLine();
        File file = new File(nameOfFile);
        String path = file.getAbsolutePath();
        loadFile(not,file);
        return text_;
                
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
		PDDocument doc = PDDocument.load(new File(not.getPath())); // put path to your input pdf file here
                
		String text =  new PDFTextStripper().getText(doc);
                text_ = text;
                //System.out.println("Text in PDF: " + text );
		    
		// write the content to text file
		PrintWriter pw; // put path to your output text file here
                pw = new PrintWriter(new FileWriter("G:\\Kuliah\\7th Semester\\Tugas Akhir\\Data Anotasi\\Filter\\resultUmum.txt"));
		pw.println();
                pw.write(text);
                
                not.setFile("G:\\Kuliah\\7th Semester\\Tugas Akhir\\Data Anotasi\\Filter\\resultUmum.txt");
		pw.close();  
        } catch (IOException e) {
            e.printStackTrace();
	}
        
        return text_; 
    }
    
    public void segmentasiTeks (Notulen not, List<String> text){
        List<String> valueBagianIsi = new ArrayList();
        List<String> valueBagianAwal = new ArrayList();
        List<String> daftarPola = Arrays.asList(".*Selamat siang dan salam sejahtera buat kita semua.*|.*Alhamdulillah kita diberikan kesehatan dan kekuatan oleh Allah SWT.*|.*Pertama-tama, marilah kita mengucapkan rasa syukur.*|.*Pertama-tama tentunya marilah kita.*|.*Baik, kita mulai.*|.*Pertama-tama marilah kita mengucapkan puji.*|.*Pertama, marilah kita panjatkan puji.*|.*Pertama-tama marilah kita bersyukur dan memanjatkan puji.*|.*Pertama-tama marilah kita panjatkan puji dan syukur.*|.*Pertama-tama marilah kita memanjatkan puji dan syukur kehadirat Allah.*|.*Pertama-tama marilah kita memanjatkan puji dan syukur.*|.*Pertama-tama seperti biasanya kita memanjatkan puji syukur.*");
        boolean isFind = false; 
        int i = 0; int j = 0; 
        while(i<text.size() - 1 && isFind == false ){
            Pattern pattern = Pattern.compile(daftarPola.get(0));
            String teks = text.get(i);
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
        
        while (j < text.size() - 1 ){
            String teks = text.get(j);
            valueBagianIsi.add(teks);
            j++; 
            
        }
        
        if (!valueBagianIsi.isEmpty()){
            
            //String str = valueBagianIsi.stream().collect(Collectors.joining());
            //valueIsi = pp.sentencesSpliter(str);
            System.out.println("bagian isi ada");
        }
        
        if (!isFind) {
            System.out.println("bagian isi tidak ditemukan");
        } 
        
        not.setBagianAwal(valueBagianAwal);
        not.setBagianIsi(valueBagianIsi);
        
        try {
            //Whatever the file path is.
            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\segmentasiIsi.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int k = 0; k < valueBagianIsi.size(); k++){
                    w.write(valueBagianIsi.get(k));
                    w.newLine();
                }
            }
                    
                    
        } catch (IOException e) {
                System.err.println("Problem writing to the file segmentasiIsi.txt");
        }
    }
}
