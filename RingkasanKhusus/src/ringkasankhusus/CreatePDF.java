/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasankhusus;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import java.awt.Desktop;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.transform.Rotate;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfName;
import java.io.OutputStream;

/**
 *
 * @author mochamadtry
 */
public final class CreatePDF {
    public static final String DEST = "resultAgenda.pdf";
    public static final String LAMPIRAN = "lampiran.pdf";
    public static String LABEL = "";
    public static String SEPARATOR = " : ";
    public static String CONTENT = "";
    public static boolean isKesimpulan = false; 
    
    public class Rotate extends PdfPageEventHelper {
 
        protected PdfNumber orientation = PdfPage.LANDSCAPE;
 
        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }
 
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, orientation);
        }
    }
    
    public class RotateEvent extends PdfPageEventHelper {
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, PdfPage.SEASCAPE);
        }
    }
    
    public CreatePDF(Notulen not) throws IOException, DocumentException {
        File file = new File(DEST);
        setPdf(DEST, not);
        setPdfLampiran(LAMPIRAN, not);
    }
    
    public void setPdf(String dest, Notulen not) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Paragraph p = new Paragraph();
        Font font = new Font(Font.getFamily("TIMES_ROMAN"), 12 , Font.BOLD);
        Font fontUnderline = new Font(Font.getFamily("TIMES_ROMAN"), 12 , Font.UNDERLINE|Font.BOLD);
        Image img = Image.getInstance("logo kecil.png");
        PdfPTable table;// = new PdfPTable(3);
        img.setAbsolutePosition(250f, 730f);
        document.add(img);
        document.add(new Paragraph ("\n\n\n\n\n"));
        p.setAlignment(Element.ALIGN_CENTER);
        p.add(new Chunk("DEWAN PERWAKILAN RAKYAT \n", font));
        p.add(new Chunk("REPUBLIK INDONESIA \n", fontUnderline)); 
        document.add(p);
        
        p = new Paragraph();
        document.add(new Paragraph ("\n"));
        p.setAlignment(Element.ALIGN_CENTER);
        p.add(new Chunk("POKOK-POKOK PEMBICARAAN \n RAPAT PARIPURNA DEWAN PERWAKILAN RAKYAT\n REPUBLIK INDONESIA", font));
        document.add(p);
        
        //Bagian Awal
        p = new Paragraph("\n");
        document.add(p);
        table = setIndentationCover("Tahun Sidang", SEPARATOR, not.getTahunSidang().get(0));
        document.add(table);
        table = setIndentationCover("Masa Persidangan",SEPARATOR, not.getMasaSidang().get(0));
        document.add(table);
        table = setIndentationCover("Rapat Ke", SEPARATOR, not.getUrutanRapat().get(0));
        document.add(table);
        table = setIndentationCover("Jenis Rapat",SEPARATOR, not.getJenisRapat().get(0));
        document.add(table);
        table = setIndentationCover("Sifat Rapat",SEPARATOR, not.getSifatRapat().get(0));
        document.add(table);
        table = setIndentationCover("Hari, Tanggal", SEPARATOR, not.getHariRapat().get(0) + ", " + not.getTanggalRapat().get(0));
        document.add(table);
        table = setIndentationCover("Waktu", SEPARATOR, "Pukul " + not.getRapatDibuka().get(0)+" WIB s.d. Selesai");
        document.add(table);
        table = setIndentationCover("Tempat", SEPARATOR, not.getTempatRapat().get(0) + " " + not.getTempatRapat().get(1) + " " + not.getTempatRapat().get(2));
        document.add(table);
        table = setIndentationCover("Ketua Rapat", SEPARATOR, not.getKetuaRapat().get(0) + " " + SEPARATOR + " " + not.getKetuaRapat().get(1));
        document.add(table);
        table = setIndentationCover("", "", "Didampingi");
        document.add(table);
        if (!not.getPendampingKetua().isEmpty()){
            List list = new List (List.ORDERED);
            ListItem item; 
            for (int i = 0; i < not.getPendampingKetua().size(); i++){
                BaseFont bf = BaseFont.createFont();
                float indentation = bf.getWidthPoint(0, 5);
                list.setIndentationLeft(200-indentation);
                item = new ListItem(not.getPendampingKetua().get(i) + " : " + not.getPendampingKetua().get(i+1));
                item.setAlignment(Element.ALIGN_JUSTIFIED);
                list.add(item);
//                table = setIndentationCover("", "", i+1 + ". " + not.getPendampingKetua().get(i) + " : " + not.getPendampingKetua().get(i+1));
//                document.add(table);
                i++;
            }
            document.add(list);
        }
        
        if (!not.getAcaraRapat().isEmpty()||!not.getAcara().isEmpty()){
            if (not.getAcaraRapat().size() == 1){ //Acara cuman 1 
                table = setIndentationCover("Acara", SEPARATOR, not.getAcaraRapat().get(0));
                document.add(table);
            }
            
            else if (not.getAcaraRapat().size() > 1){
                table = setIndentationCover("Acara", SEPARATOR, "");
                document.add(table);
                List list = new List (List.ORDERED);
                ListItem item; 
                for (int i = 0; i < not.getAcaraRapat().size(); i++){
                    BaseFont bf = BaseFont.createFont();
                    float indentation = bf.getWidthPoint(0, 5);
                    list.setIndentationLeft(200-indentation);
                    item = new ListItem(not.getAcaraRapat().get(i));
                    item.setAlignment(Element.ALIGN_JUSTIFIED);
                    list.add(item);
                }
                document.add(list);
            }
            else if (not.getAcara().size() == 1){ //Acara cuman 1 
                table = setIndentationCover("Acara", SEPARATOR, not.getAcara().get(0));
                document.add(table);
            }
            
            else{
                table = setIndentationCover("Acara", SEPARATOR, "");
                document.add(table);
                List list = new List (List.ORDERED);
                ListItem item; 
                for (int i = 0; i < not.getAcara().size(); i++){
                    BaseFont bf = BaseFont.createFont();
                    float indentation = bf.getWidthPoint(0, 5);
                    list.setIndentationLeft(200-indentation);
                    item = new ListItem(not.getAcara().get(i));
                    item.setAlignment(Element.ALIGN_JUSTIFIED);
                    list.add(item);
                }
                document.add(list);
            }
        }
        
        table = setIndentationCover("Sekretaris Rapat", SEPARATOR, not.getSekretarisRapat().get(0) + " " + SEPARATOR + " " + not.getSekretarisRapat().get(1));
        document.add(table);
        table = setIndentationCover("", "", "Didampingi");
        document.add(table);
        
        if (!not.getPendampingSekretaris().isEmpty()){
            List list = new List (List.ORDERED);
            ListItem item; 
            for (int i = 0; i < not.getPendampingSekretaris().size() ; i++){// not.getPendampingSekretaris().size(); i++){
                BaseFont bf = BaseFont.createFont();
                float indentation = bf.getWidthPoint(0, 5);
                list.setIndentationLeft(200-indentation);
                item = new ListItem(not.getPendampingSekretaris().get(i) + " : " + not.getPendampingSekretaris().get(i+1));
                item.setAlignment(Element.ALIGN_JUSTIFIED);
                list.add(item);
//                table = setIndentationCover("", "", i+1 + ". " + not.getPendampingKetua().get(i) + " : " + not.getPendampingKetua().get(i+1));
//                document.add(table);
                i++;
            }
            document.add(list);
        }
        
        table = setIndentationCover("Hadir Anggota", SEPARATOR, not.getAnggotaHadir().get(0) + " Dari " +  not.getAnggotaHadir().get(1) + " orang Anggota");
        document.add(table);
        
        //Create new page
        document.newPage();
        
        //Start Bagian Isi 
        table = setIndentationCover("A. Pendahuluan", "", "");
        document.add(table);
        p = new Paragraph("\n");
        document.add(p);
        List list = new List (List.ORDERED); //list untuk setiap elemen di pendahuluan
        
        if (!not.getKetuaRapat().isEmpty()){
            //isi untuk pendahuluan
            
            ListItem item; 
            
            BaseFont bf = BaseFont.createFont();
            //float indentation = bf.getWidthPoint(0, 5);
            list.setIndentationLeft(70);
            item = new ListItem("Rapat dibuka Pukul " + not.getRapatDibuka().get(0)+" WIB dipimpin Oleh Bapak "+ not.getKetuaRapat().get(0)+", "+ not.getKetuaRapat().get(1)+ ", dihadiri oleh " + not.getAnggotaHadir().get(0) + " orang dari " + not.getAnggotaHadir().get(1)+ " Orang Anggota DPR RI.");
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            list.add(item);
            document.add(list);
        }
        
        if (!not.getAcaraRapat().isEmpty() || !not.getAcara().isEmpty()){
            ListItem item; 
            list.setIndentationLeft(70);
            item = new ListItem("Rapat Paripurna menyetujui acara Rapat pada hari ini adalah: ");
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            list.getFirstItem().clear();
            list.add(item);
            document.add((list));
            if (!not.getAcaraRapat().isEmpty()){
                List listAcara = new List (List.ORDERED);
                for (int i = 0; i < not.getAcaraRapat().size(); i++){
                    
                    listAcara.setIndentationLeft(85);
                    item = new ListItem(not.getAcaraRapat().get(i));
                    item.setAlignment(Element.ALIGN_JUSTIFIED);
                    listAcara.add(item);
                }
                document.add(listAcara);
            }
            
            else if (!not.getAcara().isEmpty()){
               List listAcara = new List (List.ORDERED);
                for (int i = 0; i < not.getAcara().size(); i++){
                    
                    listAcara.setIndentationLeft(85);
                    item = new ListItem(not.getAcara().get(i));
                    item.setAlignment(Element.ALIGN_JUSTIFIED);
                    listAcara.add(item);
                }
                document.add(listAcara); 
                
            }
           //System.out.println(list.getLastItem());
        }
        
        if (!not.getPenggantiAntarWaktu().isEmpty()){
            ListItem item; 
            list.getLastItem().clear();
            list.setIndentationLeft(70);
            item = new ListItem("Rapat Paripurna didahului dengan Pelantikan Anggota Pengganti Antar Waktu " +
                   "DPR RI yaitu :");
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            
            
            list.add(item);
            document.add(list);
            if (!not.getPenggantiAntarWaktu().isEmpty()){
                List listAcara = new List (List.ORDERED);
                for (int i = 0; i < not.getPenggantiAntarWaktu().size(); i++){
                    
                    listAcara.setIndentationLeft(85);
                    item = new ListItem(not.getPenggantiAntarWaktu().get(i));
                    item.setAlignment(Element.ALIGN_JUSTIFIED);
                    listAcara.add(item);
                }
                document.add(listAcara);
            }
        }
        
        if (!not.getPucukSuratMasuk().isEmpty()|| !not.getSepucukSuratMasuk().isEmpty()){
            
            //kalo diterima sepucuk surat
            if (!not.getSepucukSuratMasuk().isEmpty()){
                ListItem item; 
                list.getLastItem().clear();
                list.setIndentationLeft(70);
                item = new ListItem("Pimpinan Rapat membacakan sepucuk surat masuk yaitu " + not.getSepucukSuratMasuk().get(0));
                item.setAlignment(Element.ALIGN_JUSTIFIED);


                list.add(item);
                document.add(list);
            }
            
            //kalo lebih dari satu surat 
            else{
                String numSurat = Rule.intToString(not.getPucukSuratMasuk().size()-1);
                String num = Rule.intToInt(not.getPucukSuratMasuk().size()-1);
                ListItem item; 
                list.getLastItem().clear();
                list.setIndentationLeft(70);
                item = new ListItem("Pimpinan Rapat membacakan " + num + " (" + numSurat + ") pucuk surat masuk yaitu");
                item.setAlignment(Element.ALIGN_JUSTIFIED);


                list.add(item);
                document.add(list);
                if (!not.getPucukSuratMasuk().isEmpty()){
                    List listAcara = new List (List.ORDERED);
                    for (int i = 0; i < not.getPucukSuratMasuk().size()-1; i++){

                        listAcara.setIndentationLeft(85);
                        item = new ListItem(not.getPucukSuratMasuk().get(i));
                        item.setAlignment(Element.ALIGN_JUSTIFIED);
                        listAcara.add(item);
                    }
                    item = new ListItem(not.getPucukSuratMasuk().get(not.getPucukSuratMasuk().size()-1));
                    item.setIndentationLeft(80);
                    item.setAlignment(Element.ALIGN_JUSTIFIED);

                    document.add(listAcara);
                    document.add(item);
                }
            }
        }
        
        p = new Paragraph("\n");
        document.add(p);
        p = new Paragraph();
        p.setTabSettings(new TabSettings(56f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("B. Keputusan dan Kesimpulan"));
        document.add(p);
        p = new Paragraph("\n");
        document.add(p);
        int iterator;
        if (!not.getKesimpulan().isEmpty()){
            ListItem item; 
            List listAcara = new List (List.ORDERED);
            listAcara.setIndentationLeft(85);
            
            //identifikasi kesimpulan pertama ada PAW atau ngak 
            item = new ListItem(not.getKesimpulan().get(0));
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            listAcara.add(item);
            List listPAW = new List (List.ORDERED);
            if (!not.getPenggantiAntarWaktu().isEmpty() && !isKesimpulan ){

                for (int j = 0; j < not.getPenggantiAntarWaktu().size(); j++){

                    listPAW.setIndentationLeft(100);
                    item = new ListItem(not.getPenggantiAntarWaktu().get(j));
                    item.setAlignment(Element.ALIGN_JUSTIFIED);
                    listPAW.add(item);
                }
            }
            document.add(listAcara);
            if (!listPAW.isEmpty()){
              document.add(listPAW);
            }
            listAcara.getFirstItem().clear();
            
            //sisa acara
            for (int i = 1; i < not.getKesimpulan().size(); i++){

                listAcara.setIndentationLeft(85);
                item = new ListItem(not.getKesimpulan().get(i));
                item.setAlignment(Element.ALIGN_JUSTIFIED);
                listAcara.add(item);
            
            }
            document.add(listAcara);
            
            
        }
        
        //bagian rapat ditutup
        p = new Paragraph("\n");
        document.add(p);
        table = setIndentationCover("C. Penutup", "", "");
        document.add(table);
        if (!not.getRapatDitutup().isEmpty()){
            ListItem item; 
            
            item = new ListItem("Rapat ditutup Pukul " + not.getRapatDitutup().get(0) + " WIB");
            item.setIndentationLeft(70);
            item.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(item);
        }
        
        //bagian tanda tangan 
        p = new Paragraph("\n");
        document.add(p);
        p = new Paragraph();
        p.setAlignment(Element.ALIGN_RIGHT);
        p.add(new Chunk(not.getKotaRapat().get(0) + ", " + not.getTanggalRapat().get(0) + 
                "\n KETUA RAPAT\n\n\n " + not.getKetuaRapat().get(0), font)); 
        document.add(p);
        
        //Bagian Lampiran 
        //Create new page
       
//        Rotate event = new Rotate();
//        writer.setPageEvent(event);
//        event.setOrientation(PdfPage.LANDSCAPE);
//        document.newPage();
//        document.add(new Chunk("A simple page in landscape orientation"));
//        p.add(new Chunk(not.getKotaRapat().get(0) + ", " + not.getTanggalRapat().get(0) + 
//                "\n KETUA RAPAT\n\n\n " + not.getKetuaRapat().get(0), font)); 
//        document.add(p);
//        writer.setPageEvent(new RotateEvent());
//        document.newPage();
//        p = new Paragraph();
//        p.setAlignment(Element.ALIGN_CENTER);
//        p.add(new Chunk("Interupsi Dalam Rapat Paripurna ke-" + not.getUrutanRapat().get(0)+"\n Masa Persidangan " + not.getMasaSidang().get(0) + " Tahun Sidang " + not.getTahunSidang().get(0) + 
//                "\n " + not.getHariRapat().get(0) + ", " + not.getTanggalRapat().get(0) + "\n ", font)); 
//        document.add(p);
//        table = addTableLampiran (not);
//        document.add(table);
        
//        writer.setPageEvent(new RotateEvent());
        
//        document.add(new Chunk("A simple page in landscape orientation"));
        
        
        
//        p = new Paragraph();
//        p.setAlignment(Element.ALIGN_RIGHT);
//        p.add(new Chunk(not.getKotaRapat().get(0) + ", " + not.getTanggalRapat().get(0) + 
//                "\n KETUA RAPAT\n\n " + not.getKetuaRapat().get(0), font)); 
//        document.add(p);
        
        document.close();
        Desktop.getDesktop().open(new File(dest));
        
    }
    
    public void setPdfLampiran(String dest, Notulen not) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Paragraph p = new Paragraph();
        Font font = new Font(Font.getFamily("TIMES_ROMAN"), 12 , Font.BOLD);
        Font fontUnderline = new Font(Font.getFamily("TIMES_ROMAN"), 12 , Font.UNDERLINE|Font.BOLD);
//        Image img = Image.getInstance("logo kecil.png");
        PdfPTable table;// = new PdfPTable(3);
//        img.setAbsolutePosition(250f, 730f);
//        document.add(img);
//        document.add(new Paragraph ("\n\n\n\n\n"));
//        p.setAlignment(Element.ALIGN_CENTER);
//        p.add(new Chunk("DEWAN PERWAKILAN RAKYAT \n", font));
//        p.add(new Chunk("REPUBLIK INDONESIA \n", fontUnderline)); 
//        document.add(p);
        
//        p = new Paragraph();
//        document.add(new Paragraph ("\n"));
//        p.setAlignment(Element.ALIGN_CENTER);
//        p.add(new Chunk("POKOK-POKOK PEMBICARAAN \n RAPAT PARIPURNA DEWAN PERWAKILAN RAKYAT\n REPUBLIK INDONESIA", font));
//        document.add(p);
        
        //Bagian Awal
//        p = new Paragraph("\n");
//        document.add(p);
//        table = setIndentationCover("Tahun Sidang", SEPARATOR, not.getTahunSidang().get(0));
//        document.add(table);
//        table = setIndentationCover("Masa Persidangan",SEPARATOR, not.getMasaSidang().get(0));
//        document.add(table);
//        table = setIndentationCover("Rapat Ke", SEPARATOR, not.getUrutanRapat().get(0));
//        document.add(table);
//        table = setIndentationCover("Jenis Rapat",SEPARATOR, not.getJenisRapat().get(0));
//        document.add(table);
//        table = setIndentationCover("Sifat Rapat",SEPARATOR, not.getSifatRapat().get(0));
//        document.add(table);
//        table = setIndentationCover("Hari, Tanggal", SEPARATOR, not.getHariRapat().get(0) + ", " + not.getTanggalRapat().get(0));
//        document.add(table);
//        table = setIndentationCover("Waktu", SEPARATOR, "Pukul " + not.getRapatDibuka().get(0)+" WIB s.d. Selesai");
//        document.add(table);
//        table = setIndentationCover("Tempat", SEPARATOR, not.getTempatRapat().get(0) + " " + not.getTempatRapat().get(1) + " " + not.getTempatRapat().get(2));
//        document.add(table);
//        table = setIndentationCover("Ketua Rapat", SEPARATOR, not.getKetuaRapat().get(0) + " " + SEPARATOR + " " + not.getKetuaRapat().get(1));
//        document.add(table);
//        table = setIndentationCover("", "", "Didampingi");
//        document.add(table);
//        if (!not.getPendampingKetua().isEmpty()){
//            List list = new List (List.ORDERED);
//            ListItem item; 
//            for (int i = 0; i < not.getPendampingKetua().size(); i++){
//                BaseFont bf = BaseFont.createFont();
//                float indentation = bf.getWidthPoint(0, 5);
//                list.setIndentationLeft(200-indentation);
//                item = new ListItem(not.getPendampingKetua().get(i) + " : " + not.getPendampingKetua().get(i+1));
//                item.setAlignment(Element.ALIGN_JUSTIFIED);
//                list.add(item);
////                table = setIndentationCover("", "", i+1 + ". " + not.getPendampingKetua().get(i) + " : " + not.getPendampingKetua().get(i+1));
////                document.add(table);
//                i++;
//            }
//            document.add(list);
//        }
//        
//        if (!not.getAcaraRapat().isEmpty()||!not.getAcara().isEmpty()){
//            if (not.getAcaraRapat().size() == 1){ //Acara cuman 1 
//                table = setIndentationCover("Acara", SEPARATOR, not.getAcaraRapat().get(0));
//                document.add(table);
//            }
//            
//            else if (not.getAcaraRapat().size() > 1){
//                table = setIndentationCover("Acara", SEPARATOR, "");
//                document.add(table);
//                List list = new List (List.ORDERED);
//                ListItem item; 
//                for (int i = 0; i < not.getAcaraRapat().size(); i++){
//                    BaseFont bf = BaseFont.createFont();
//                    float indentation = bf.getWidthPoint(0, 5);
//                    list.setIndentationLeft(200-indentation);
//                    item = new ListItem(not.getAcaraRapat().get(i));
//                    item.setAlignment(Element.ALIGN_JUSTIFIED);
//                    list.add(item);
//                }
//                document.add(list);
//            }
//            else if (not.getAcara().size() == 1){ //Acara cuman 1 
//                table = setIndentationCover("Acara", SEPARATOR, not.getAcara().get(0));
//                document.add(table);
//            }
//            
//            else{
//                table = setIndentationCover("Acara", SEPARATOR, "");
//                document.add(table);
//                List list = new List (List.ORDERED);
//                ListItem item; 
//                for (int i = 0; i < not.getAcara().size(); i++){
//                    BaseFont bf = BaseFont.createFont();
//                    float indentation = bf.getWidthPoint(0, 5);
//                    list.setIndentationLeft(200-indentation);
//                    item = new ListItem(not.getAcara().get(i));
//                    item.setAlignment(Element.ALIGN_JUSTIFIED);
//                    list.add(item);
//                }
//                document.add(list);
//            }
//        }
//        
//        table = setIndentationCover("Sekretaris Rapat", SEPARATOR, not.getSekretarisRapat().get(0) + " " + SEPARATOR + " " + not.getSekretarisRapat().get(1));
//        document.add(table);
//        table = setIndentationCover("", "", "Didampingi");
//        document.add(table);
//        
//        if (!not.getPendampingSekretaris().isEmpty()){
//            List list = new List (List.ORDERED);
//            ListItem item; 
//            for (int i = 0; i < not.getPendampingSekretaris().size() ; i++){// not.getPendampingSekretaris().size(); i++){
//                BaseFont bf = BaseFont.createFont();
//                float indentation = bf.getWidthPoint(0, 5);
//                list.setIndentationLeft(200-indentation);
//                item = new ListItem(not.getPendampingSekretaris().get(i) + " : " + not.getPendampingSekretaris().get(i+1));
//                item.setAlignment(Element.ALIGN_JUSTIFIED);
//                list.add(item);
////                table = setIndentationCover("", "", i+1 + ". " + not.getPendampingKetua().get(i) + " : " + not.getPendampingKetua().get(i+1));
////                document.add(table);
//                i++;
//            }
//            document.add(list);
//        }
//        
//        table = setIndentationCover("Hadir Anggota", SEPARATOR, not.getAnggotaHadir().get(0) + " Dari " +  not.getAnggotaHadir().get(1) + " orang Anggota");
//        document.add(table);
//        
//        //Create new page
//        document.newPage();
//        
//        //Start Bagian Isi 
//        table = setIndentationCover("A. Pendahuluan", "", "");
//        document.add(table);
//        p = new Paragraph("\n");
//        document.add(p);
//        List list = new List (List.ORDERED); //list untuk setiap elemen di pendahuluan
//        
//        if (!not.getKetuaRapat().isEmpty()){
//            //isi untuk pendahuluan
//            
//            ListItem item; 
//            
//            BaseFont bf = BaseFont.createFont();
//            //float indentation = bf.getWidthPoint(0, 5);
//            list.setIndentationLeft(70);
//            item = new ListItem("Rapat dibuka Pukul " + not.getRapatDibuka().get(0)+" WIB dipimpin Oleh Bapak "+ not.getKetuaRapat().get(0)+", "+ not.getKetuaRapat().get(1)+ ", dihadiri oleh " + not.getAnggotaHadir().get(0) + " orang dari " + not.getAnggotaHadir().get(1)+ " Orang Anggota DPR RI.");
//            item.setAlignment(Element.ALIGN_JUSTIFIED);
//            list.add(item);
//            document.add(list);
//        }
//        
//        if (!not.getAcaraRapat().isEmpty() || !not.getAcara().isEmpty()){
//            ListItem item; 
//            list.setIndentationLeft(70);
//            item = new ListItem("Rapat Paripurna menyetujui acara Rapat pada hari ini adalah: ");
//            item.setAlignment(Element.ALIGN_JUSTIFIED);
//            list.getFirstItem().clear();
//            list.add(item);
//            document.add((list));
//            if (!not.getAcaraRapat().isEmpty()){
//                List listAcara = new List (List.ORDERED);
//                for (int i = 0; i < not.getAcaraRapat().size(); i++){
//                    
//                    listAcara.setIndentationLeft(85);
//                    item = new ListItem(not.getAcaraRapat().get(i));
//                    item.setAlignment(Element.ALIGN_JUSTIFIED);
//                    listAcara.add(item);
//                }
//                document.add(listAcara);
//            }
//            
//            else if (!not.getAcara().isEmpty()){
//               List listAcara = new List (List.ORDERED);
//                for (int i = 0; i < not.getAcara().size(); i++){
//                    
//                    listAcara.setIndentationLeft(85);
//                    item = new ListItem(not.getAcara().get(i));
//                    item.setAlignment(Element.ALIGN_JUSTIFIED);
//                    listAcara.add(item);
//                }
//                document.add(listAcara); 
//                
//            }
//           //System.out.println(list.getLastItem());
//        }
//        
//        if (!not.getPenggantiAntarWaktu().isEmpty()){
//            ListItem item; 
//            list.getLastItem().clear();
//            list.setIndentationLeft(70);
//            item = new ListItem("Rapat Paripurna didahului dengan Pelantikan Anggota Pengganti Antar Waktu " +
//                   "DPR RI yaitu :");
//            item.setAlignment(Element.ALIGN_JUSTIFIED);
//            
//            
//            list.add(item);
//            document.add(list);
//            if (!not.getPenggantiAntarWaktu().isEmpty()){
//                List listAcara = new List (List.ORDERED);
//                for (int i = 0; i < not.getPenggantiAntarWaktu().size(); i++){
//                    
//                    listAcara.setIndentationLeft(85);
//                    item = new ListItem(not.getPenggantiAntarWaktu().get(i));
//                    item.setAlignment(Element.ALIGN_JUSTIFIED);
//                    listAcara.add(item);
//                }
//                document.add(listAcara);
//            }
//        }
//        
//        if (!not.getPucukSuratMasuk().isEmpty()|| !not.getSepucukSuratMasuk().isEmpty()){
//            
//            //kalo diterima sepucuk surat
//            if (!not.getSepucukSuratMasuk().isEmpty()){
//                ListItem item; 
//                list.getLastItem().clear();
//                list.setIndentationLeft(70);
//                item = new ListItem("Pimpinan Rapat membacakan sepucuk surat masuk yaitu " + not.getSepucukSuratMasuk().get(0));
//                item.setAlignment(Element.ALIGN_JUSTIFIED);
//
//
//                list.add(item);
//                document.add(list);
//            }
//            
//            //kalo lebih dari satu surat 
//            else{
//                String numSurat = Rule.intToString(not.getPucukSuratMasuk().size()-1);
//                String num = Rule.intToInt(not.getPucukSuratMasuk().size()-1);
//                ListItem item; 
//                list.getLastItem().clear();
//                list.setIndentationLeft(70);
//                item = new ListItem("Pimpinan Rapat membacakan " + num + " (" + numSurat + ") pucuk surat masuk yaitu");
//                item.setAlignment(Element.ALIGN_JUSTIFIED);
//
//
//                list.add(item);
//                document.add(list);
//                if (!not.getPucukSuratMasuk().isEmpty()){
//                    List listAcara = new List (List.ORDERED);
//                    for (int i = 0; i < not.getPucukSuratMasuk().size()-1; i++){
//
//                        listAcara.setIndentationLeft(85);
//                        item = new ListItem(not.getPucukSuratMasuk().get(i));
//                        item.setAlignment(Element.ALIGN_JUSTIFIED);
//                        listAcara.add(item);
//                    }
//                    item = new ListItem(not.getPucukSuratMasuk().get(not.getPucukSuratMasuk().size()-1));
//                    item.setIndentationLeft(80);
//                    item.setAlignment(Element.ALIGN_JUSTIFIED);
//
//                    document.add(listAcara);
//                    document.add(item);
//                }
//            }
//        }
//        
//        p = new Paragraph("\n");
//        document.add(p);
//        p = new Paragraph();
//        p.setTabSettings(new TabSettings(56f));
//        p.add(Chunk.TABBING);
//        p.add(new Chunk("B. Keputusan dan Kesimpulan"));
//        document.add(p);
//        p = new Paragraph("\n");
//        document.add(p);
//        int iterator;
//        if (!not.getKesimpulan().isEmpty()){
//            ListItem item; 
//            List listAcara = new List (List.ORDERED);
//            listAcara.setIndentationLeft(85);
//            
//            //identifikasi kesimpulan pertama ada PAW atau ngak 
//            item = new ListItem(not.getKesimpulan().get(0));
//            item.setAlignment(Element.ALIGN_JUSTIFIED);
//            listAcara.add(item);
//            List listPAW = new List (List.ORDERED);
//            if (!not.getPenggantiAntarWaktu().isEmpty() && !isKesimpulan ){
//
//                for (int j = 0; j < not.getPenggantiAntarWaktu().size(); j++){
//
//                    listPAW.setIndentationLeft(100);
//                    item = new ListItem(not.getPenggantiAntarWaktu().get(j));
//                    item.setAlignment(Element.ALIGN_JUSTIFIED);
//                    listPAW.add(item);
//                }
//            }
//            document.add(listAcara);
//            if (!listPAW.isEmpty()){
//              document.add(listPAW);
//            }
//            listAcara.getFirstItem().clear();
//            
//            //sisa acara
//            for (int i = 1; i < not.getKesimpulan().size(); i++){
//
//                listAcara.setIndentationLeft(85);
//                item = new ListItem(not.getKesimpulan().get(i));
//                item.setAlignment(Element.ALIGN_JUSTIFIED);
//                listAcara.add(item);
//            
//            }
//            document.add(listAcara);
//            
//            
//        }
//        
//        //bagian rapat ditutup
//        p = new Paragraph("\n");
//        document.add(p);
//        table = setIndentationCover("C. Penutup", "", "");
//        document.add(table);
//        if (!not.getRapatDitutup().isEmpty()){
//            ListItem item; 
//            
//            item = new ListItem("Rapat ditutup Pukul " + not.getRapatDitutup().get(0) + " WIB");
//            item.setIndentationLeft(70);
//            item.setAlignment(Element.ALIGN_JUSTIFIED);
//            document.add(item);
//        }
//        
//        //bagian tanda tangan 
//        p = new Paragraph("\n");
//        document.add(p);
//        p = new Paragraph();
//        p.setAlignment(Element.ALIGN_RIGHT);
//        p.add(new Chunk(not.getKotaRapat().get(0) + ", " + not.getTanggalRapat().get(0) + 
//                "\n KETUA RAPAT\n\n\n " + not.getKetuaRapat().get(0), font)); 
//        document.add(p);
        
        //Bagian Lampiran 
        //Create new page
       
//        Rotate event = new Rotate();
//        writer.setPageEvent(event);
//        event.setOrientation(PdfPage.LANDSCAPE);
//        document.newPage();
//        document.add(new Chunk("A simple page in landscape orientation"));
//        p.add(new Chunk(not.getKotaRapat().get(0) + ", " + not.getTanggalRapat().get(0) + 
//                "\n KETUA RAPAT\n\n\n " + not.getKetuaRapat().get(0), font)); 
//        document.add(p);
        writer.setPageEvent(new RotateEvent());
        document.newPage();
        p = new Paragraph();
        p.setAlignment(Element.ALIGN_CENTER);
        p.add(new Chunk("Interupsi Dalam Rapat Paripurna ke-" + not.getUrutanRapat().get(0)+"\n Masa Persidangan " + not.getMasaSidang().get(0) + " Tahun Sidang " + not.getTahunSidang().get(0) + 
                "\n " + not.getHariRapat().get(0) + ", " + not.getTanggalRapat().get(0) + "\n ", font)); 
        document.add(p);
        table = addTableLampiran (not);
        document.add(table);
        
//        writer.setPageEvent(new RotateEvent());
        
//        document.add(new Chunk("A simple page in landscape orientation"));
        
        
        
//        p = new Paragraph();
//        p.setAlignment(Element.ALIGN_RIGHT);
//        p.add(new Chunk(not.getKotaRapat().get(0) + ", " + not.getTanggalRapat().get(0) + 
//                "\n KETUA RAPAT\n\n " + not.getKetuaRapat().get(0), font)); 
//        document.add(p);
        
        document.close();
        Desktop.getDesktop().open(new File(dest));
        
    }
    
    
    public PdfPTable setIndentationCover(String label, String separator, String content) throws DocumentException, IOException{
        BaseFont bf = BaseFont.createFont();
        LABEL =  label;
        //SEPARATOR = separator;
        CONTENT = content;
        float indentation = bf.getWidthPoint(0, 5);
        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.setTotalWidth(new float[]{indentation + 200, indentation + 40 , 440 - indentation});
        //table.setKeepTogether(true);
        //table.setLockedWidth(true);
        table.addCell(LABEL);
        table.addCell(separator);
        table.addCell(CONTENT);
        
        return table;
        //document.add(table);
    }
    
    public List setListNumber(String text){
        List list = new List(List.ORDERED);
        ListItem item = new ListItem(text);
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        list.add(item);
        return list;
    }
    
    public PdfPTable addTableLampiran (Notulen not) throws DocumentException, IOException{
        BaseFont bf = BaseFont.createFont();
        float indentation = bf.getWidthPoint(0, 5);
        // table with 2 columns:
        PdfPTable table = new PdfPTable(3);
        // header row:
        table.addCell("Nama");
        table.addCell("Pernyataan");
        table.addCell("Tanggapan Ketua");
        table.setHeaderRows(1);
        // many data rows:
        for (int i = 0; i < not.getPembicara().size(); i++) {
            if(not.getIsiInterupsi().get(i).size() > 0){
                if (not.getPembicara().get(i).equals("KETUA RAPAT:")){
                    table.addCell("");
                    table.addCell("");
                    table.addCell(not.getIsiInterupsi().get(i).toString());
                }
                else{
                    table.addCell(not.getPembicara().get(i).toString());
                    table.addCell(not.getIsiInterupsi().get(i).toString());
                    table.addCell("Terima kasih");
                }
            }
            
        }
        System.out.println("isi interupsi orang ke 1 " + not.getIsiInterupsi().get(0).size());
        //document.add(table);
        return table;
        
    }
    
    
}

