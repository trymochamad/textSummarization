/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasankhusus;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import java.awt.Desktop;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
/**
 *
 * @author mochamadtry
 */
public class TemplateGenerator {

    
    public static boolean cekKesimpulanMembacakan (String isiTeks){
        boolean ketemu = false;
            Pattern pattern = Pattern.compile("(.*)(Pidato|pidato)(.*)");
            Matcher matcher = pattern.matcher(isiTeks);
            if (matcher.find())
                ketemu = true;
        return ketemu;
    }
    
    public static boolean cekKesimpulanMenyetujui (String isiTeks){
        boolean ketemu = false;
            Pattern pattern = Pattern.compile("(.*)(Pelantikan|pelantikan|Laporan|laporan|Persetujuan|persetujuan|Pengesahan|pengesahan|Pembicaraan|pembicaraan|RUU|Penetapan|penetapan)(.*)");
            Matcher matcher = pattern.matcher(isiTeks);
            if (matcher.find())
                ketemu = true;
        return ketemu;
    }
    
    public static boolean cekPenggantiKetua (String isiTeks, Notulen not){
        boolean ketemu = false; boolean isFind = false; 
        int j  = 0; 
        List<String> namaPenggantiKetua = new ArrayList();
            Pattern pattern = Pattern.compile("(.*)(Pelantikan Ketua DPR RI)(.*)");
            Matcher matcher = pattern.matcher(isiTeks);
            List<String> daftarPola = Arrays.asList(".*pengganti Ketua DPR RI.*kepada(.*)sesuai dengan ketentuan"); 
            if (matcher.find()){
                ketemu = true;
                while (j<daftarPola.size() && isFind == false){
                    int i = 0; 
                    while (i < not.getBagianIsi().size()-1 && isFind == false){
                        System.out.println("nama pengganti ketua 1");
                        Pattern patternCari = Pattern.compile(daftarPola.get(j));
                        String line = not.getBagianIsi().get(i).toString();
                        Matcher matcherCari = patternCari.matcher(line);
                        if (matcherCari.find()){
                            System.out.println("nama pengganti ketua");
                            isFind = true; 
                            namaPenggantiKetua.add(matcherCari.group(1));
                            not.setDaftarAnggota(namaPenggantiKetua);
                        }
                        i++; 
                    }
                    j++;
                }
            }
        return ketemu;
    }
    
//    public static String cekTahunSidang(){
//       String value = 
//               "<center>Tahun Sidang &emsp; &emsp; : &emsp; &emsp;"
//               + PatternRegex.tahunSidang.get(0) + "</center>"
//               +"</body></html>";
//       return value;
//    }
//    
//    public static String cekMasaSidang(){
//       String value = 
//               "<center>Masa Sidang &emsp; &emsp; : &emsp; &emsp;"
//               + PatternRegex.masaPersidangan.get(0) + "</center>"
//               +"</body></html>";
//       return value;
//    }
//    
//    public static String cekRapatKe(){
//       String value = 
//               "<center>Rapat Ke- &emsp; &emsp; : &emsp; &emsp;"
//               + PatternRegex.rapatKe.get(0) + "</center>"
//               +"</body></html>";
//       return value;
//    }
//    
//    public static String cekJenisRapat(){
//       String value = 
//               "<center>Jenis Rapat &emsp; &emsp; : &emsp; &emsp;"
//               + PatternRegex.jenisRapat.get(0) + "</center>"
//               +"</body></html>";
//       return value;
//    }
//    
//    public static String cekSifatRapat(){
//       String value = 
//               "<center>Sifat Rapat &emsp; &emsp; : &emsp; &emsp;"
//               + PatternRegex.sifatRapat.get(0) + "</center>"
//               +"</body></html>";
//       return value;
//    }
//    
//    public static String cekHariTanggal(){
//       String value = 
//               "<center>Sifat Rapat &emsp; &emsp; : &emsp; &emsp;"
//               + PatternRegex.hariRapat.get(0) + ", " + PatternRegex.tanggalRapat.get(0)+"</center>"
//               +"</body></html>";
//       return value;
//    }

//    public void generatePDF() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
