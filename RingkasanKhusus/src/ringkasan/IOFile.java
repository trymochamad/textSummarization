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
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.xml.sax.SAXException;

/**
 *
 * @author mochamadtry
 */
public class IOFile {
    
    public void loadFile(Notulen not, String fileName, String fileOutput) throws IOException, SAXException, TikaException {
//    public void loadFile(Notulen not) throws IOException, SAXException, TikaException {

        List<String> kalimat = new ArrayList();
//        kalimat = parseToPlainTextChunks("m0002-0_edit.xlsx");
        kalimat = parseToPlainTextChunks(fileName);

        not.setSentencesLine(kalimat);
        try {
            //Whatever the file path is.
            File statText = new File(fileOutput);//"G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\hasilKalimat.txt"); //file yang akan digunakan berisi kalimat klasifikasi 
//            File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\hasilKalimat.txt");//"G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\hasilKalimat.txt"); //file yang akan digunakan berisi kalimat klasifikasi 
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            try (BufferedWriter w = new BufferedWriter(osw)) {
                for (int i = 0; i < kalimat.size(); i++) {
                    w.write(kalimat.get(i));
                    w.newLine();
                }
            }       
        } catch (IOException e) {
                System.err.println("Problem writing to the file statsTest.txt");
        }
    }
    public static List<String> parseToPlainTextChunks(String filename) throws IOException, SAXException, TikaException, org.xml.sax.SAXException {
        final List<String> chunks = new ArrayList<>();
        chunks.add("");
        ContentHandlerDecorator handler = new ContentHandlerDecorator() {
            @Override
            public void characters(char[] ch, int start, int length) {
                String lastChunk = chunks.get(chunks.size() - 1);
                String thisStr = new String(ch, start, length);

                if (lastChunk.length() + length > 10) {
                    chunks.add(thisStr);
                } else {
                    chunks.set(chunks.size() - 1, lastChunk + thisStr);
                }
            }
        };

        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        String str=filename;
        try (InputStream stream = Ringkasan.class.getResourceAsStream(str)) {
            parser.parse(stream, handler, metadata);
            return chunks; 
        }
    }
    
    
}
