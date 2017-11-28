
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author mochamadtry
 */
class Ringkasan {
    
    static IOFile file = new IOFile(); 
    static Notulen notulen = new Notulen();
    static ringkasan.klasifikasiretorik.FileUpload upload = new ringkasan.klasifikasiretorik.FileUpload(); 
    static ringkasan.klasifikasiretorik.Klasifikasi klasifikasi = new ringkasan.klasifikasiretorik.Klasifikasi();
    static Klasifikasi predict = new Klasifikasi(); 
    static SelectedSentences selectedSentences = new SelectedSentences();
    
    public static void main(String[] args) throws IOException, SAXException, TikaException, org.xml.sax.SAXException, SQLException, Exception
    {
//          file.loadFile(notulen, "m0002-0_edit.xlsx", "hasilKalimat.txt");
//          upload.postFile(notulen, "hasilKalimat.txt");
          file.loadFile(notulen, args[0], args[1]);
          upload.postFile(notulen, args[1]);
//          file.loadFile(notulen);
//          upload.postFile(notulen);
          klasifikasi.klasifikasi(notulen);
//          predict.writeFile(notulen, "hasilKlasifikasi.txt", "prediksi.csv");
//          predict.prosesKlasifikasi(notulen, "prediksi.csv");
          System.out.println("hahahaha"); 
          predict.writeFile(notulen, args[2], args[3]);
          System.out.println("hahahaha"); 
          predict.prosesKlasifikasi(notulen, args[3]);
          selectedSentences.rankedSentencesMMR(notulen);
          for (int i = 0; i < notulen.getFilterKlasifikasi().size(); i++){
            System.out.println(notulen.getFilterKlasifikasi().get(i));
          }
          System.out.println(notulen.getFilterKlasifikasi().size());
    }
    
}