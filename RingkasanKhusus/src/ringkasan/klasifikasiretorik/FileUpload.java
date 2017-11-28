/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan.klasifikasiretorik;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ringkasan.Notulen;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author mochamadtry
 */
public class FileUpload {
    public void postFile (Notulen not, String nameOfFile) throws IOException, SQLException{
//        public void postFile (Notulen not) throws IOException, SQLException{

            //Notulen not = new Notulen();
            AksesDatabase da = new AksesDatabase();
            PraProses pr = new PraProses();
//            String nameOfFile= "G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\hasilKalimat.txt";//scan.nextLine();
//            System.out.println(nameOfFile);
            File f = new File(nameOfFile);
            System.out.println("WOY 1 " + f.getAbsolutePath() + "haha " + f.getParent());
            loadFile(not, f);
            splitFile(not, da, not.getText(), not.getFileKlasifikasi(), pr);
        }
        
	public void loadFile(Notulen not, File file) throws IOException {
                List<String> kalimat = new ArrayList(); 
		String line = "";
		String text = "";
                String basicPath = "";
                boolean match; 
//		not.setFileKlasifikasi(file.getPath()); lama
                not.setFileKlasifikasi(file.getAbsolutePath());
                basicPath = file.getAbsolutePath();
                if (match = basicPath.matches(".*(hasilKalimat).*")) 
                {
                    System.out.println("WOY 2 a");
                    basicPath = basicPath.replaceAll("\\W(hasil)(.*)", "");
                 }
//                not.setBasicPath(file.getParent()); lama
                not.setBasicPath(basicPath);
                System.out.println("WOY 1 " + not.getFileKlasifikasi());
                System.out.println("WOY 2 " + not.getBasicPath());
                System.out.println("WOY 2 " + basicPath);

		// Open the file.
		BufferedReader input = new BufferedReader(new FileReader(file));
		StringBuilder stringBuilder = new StringBuilder();

		while ((line = input.readLine()) != null) {
			stringBuilder.append(line + "\n");
                        kalimat.add(line);
		}
		text = stringBuilder.toString();
		not.setText(text);
                not.setKalimatKlasifikasi(kalimat);
	}

	public void splitFile(Notulen not, AksesDatabase da, String text, String filename, PraProses pr)
			throws SQLException, FileNotFoundException {
		int i = 1;
		String id = null;

		da.updatequery("INSERT INTO notulen " + "VALUES (null, '" + ringkasanumum.klasifikasiretorik.StringHelper.escapeSQL(filename) + "')");

		ResultSet rs = da.selectquery("SELECT * FROM notulen ORDER BY id DESC LIMIT 1");

		while (rs.next()) {
			id = rs.getString("id");
		}

		not.setId(id);
//                String[] sentences = text.split("\\r?\\n");
//		//String[] sentences = text.split("(?<=[.!?])\\s* ");
//                System.out.println("Ini kalimat "+ sentences.length);
//                int splitSentences = 0; 
//		for (String s : sentences) {
//
//			String s1 = s.replaceAll("[^\\w\\s]", "").replaceAll("[\\t\\n\\r]", " ");//.toLowerCase();
//			if (s1.matches(".*[a-zA-Z]+.*") && s1.length() > 2) {
//				da.updatequery("INSERT INTO kalimat (id, id_notulen, rawtext, posisi)" + "VALUES (null, " + id + ", '"
//						+ klasifikasinotulen.StringHelper.escapeSQL(s) + "', " + i++ + ")");
//                            splitSentences++; 
//			}
//		}
                for (int j =0 ; j < not.getKalimatKlasifikasi().size(); j++){
                    String s = not.getKalimatKlasifikasi().get(j).toString(); 
                    da.updatequery("INSERT INTO kalimat (id, id_notulen, rawtext, posisi)" + "VALUES (null, " + id + ", '"
						+ ringkasanumum.klasifikasiretorik.StringHelper.escapeSQL(s) + "', " + i++ + ")");
                    
                }
//                System.out.println ("split" + splitSentences);
		rs = da.selectquery("SELECT * FROM kalimat WHERE id_notulen = " + id);
		i = 1;
		while (rs.next()) {
			da.updatequery("INSERT INTO ekstraksifitur (id, id_kalimat, posisi) " + "VALUES (" + rs.getString("id")
					+ ", " + rs.getString("id") + ", " + rs.getString("posisi") + ")");
			i++;
		}
                System.out.println("masuk 1 ");
		pr.PreprocessText(da, not.getBasicPath(), id);
                System.out.println("masuk 2 ");
	}
    
}
