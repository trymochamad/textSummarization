/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import java.awt.Desktop;
import java.io.*;

/**
 *
 * @author mochamadtry
 */
public class TemplateGenerator {
    public static final String DEST = "resultWithILP.pdf";
    public static String LABEL = "";
    public static String SEPARATOR = " : ";
    public static String CONTENT = "";
    
    public class Rotate extends PdfPageEventHelper {
 
        protected PdfNumber orientation = PdfPage.PORTRAIT;
 
        public void setOrientation(PdfNumber orientation) {
            this.orientation = orientation;
        }
 
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, orientation);
        }
    }
    
    public TemplateGenerator(Notulen not) throws IOException, DocumentException {
        File file = new File(DEST);
        setPdf(DEST, not);
    }
    
    public void generatePDF(Notulen not) throws FileNotFoundException, DocumentException, IOException{
        Document document = new Document(PageSize.A4); 
        PdfWriter writer = PdfWriter.getInstance (document, new FileOutputStream("Ringkasan Berbasis Agenda.pdf")); 
        //RingkasanKhusus ringkasan = new RingkasanKhusus();
        document.open(); 
        //Notulen not = new Notulen();
        Paragraph preface = new Paragraph(); 
        preface.setAlignment(Element.ALIGN_LEFT);
        preface.add (new Paragraph ("DEWAN PERWAKILAN RAKYAT"));
        Image img = Image.getInstance("logo kecil.png");
        img.setAbsolutePosition(250f, 730f);
        document.add(img);
        Paragraph p = new Paragraph();
        document.add(new Paragraph ("\n\n\n\n\n" ));
        p = new Paragraph();
        p.setTabSettings(new TabSettings(150f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("RINGKASAN BERBASIS AGENDA"));
        document.add(p);
        document.add(new Paragraph("TOPIK RAPAT : \n"));
        for (int k = 0; k < not.getAcaraRapat().size(); k++){
            document.add(new Paragraph (not.getAcaraRapat().get(k)+"\n"));
        }
        document.add(new Paragraph ("\n\n" ));
        p.clear();
        p.add(new Chunk("PEMBICARAAN SESUAI AGENDA"));
        document.add(p); 
        int j = 0;
        boolean isFind = false;
        document.add(new Paragraph (not.getInterupsi().keySet().toArray()[j].toString()));
        for (int i = 0; i < not.getPembicara().size(); i++){
            
            while (not.getInterupsi().keySet().toArray()[j] == not.getIsiInterupsi().get(i).get(not.getIsiInterupsi().get(i).size()-1) && i < not.getPembicara().size()-1 ){
                    //acaranya apa, identifikasi utterance termasuk agenda itu apa ngak 
                    document.add(new Paragraph (not.getPembicara().get(i).toString() + "\n"));
                    p.clear();
                    p.add(new Chunk("ISI UCAPAN"));
                    document.add(p);
                    document.add(new Paragraph (not.getInterupsiKlasifikasi().get(i).toString() + "\n"));
                    i++;
            }
            j++; 
            document.add(new Paragraph (not.getInterupsi().keySet().toArray()[j].toString()));
            document.add(new Paragraph (not.getPembicara().get(i).toString() + "\n"));
            document.add(p);
            document.add(new Paragraph (not.getInterupsiKlasifikasi().get(i).toString() + "\n"));
            //j++;
        }
        document.close();
        Desktop.getDesktop().open(new File("Ringkasan Berbasis Agenda.pdf"));
        //String s = not.getIsiInterupsi().get(0).get(0);
        //return document; 
    }
    
    public void generateTemplate (Notulen not) throws FileNotFoundException, DocumentException, IOException{
        Document document = new Document(PageSize.A4); 
        PdfWriter writer = PdfWriter.getInstance (document, new FileOutputStream("Ringkasan Berbasis Agenda MMR.pdf")); 
        //RingkasanKhusus ringkasan = new RingkasanKhusus();
        document.open(); 
        //Notulen not = new Notulen();
        Paragraph preface = new Paragraph(); 
        preface.setAlignment(Element.ALIGN_LEFT);
        preface.add (new Paragraph ("DEWAN PERWAKILAN RAKYAT"));
        Image img = Image.getInstance("logo kecil.png");
        img.setAbsolutePosition(250f, 730f);
        document.add(img);
        Paragraph p = new Paragraph();
        document.add(new Paragraph ("\n\n\n\n\n" ));
        p = new Paragraph();
        p.setTabSettings(new TabSettings(150f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("RINGKASAN BERBASIS AGENDA"));
        document.add(p);
        document.add(new Paragraph("TOPIK RAPAT : \n"));
        for (int k = 0; k < not.getAcaraRapat().size(); k++){
            document.add(new Paragraph (not.getAcaraRapat().get(k)+"\n"));
        }
        document.add(new Paragraph ("\n\n" ));
        p.clear();
        p.add(new Chunk("PEMBICARAAN SESUAI AGENDA"));
        document.add(p); 
        int j = 0;
        //boolean isFind = false;
        document.add(new Paragraph (not.getInterupsi().keySet().toArray()[j].toString()));
        for (int i = 0; i < not.getProsesPembicara().size(); i++){
            
            while (not.getInterupsi().keySet().toArray()[j] == not.getProsesAcara().get(i) && i < not.getProsesPembicara().size()-1 ){
                    //acaranya apa, identifikasi utterance termasuk agenda itu apa ngak 
                    document.add(new Paragraph (not.getProsesPembicara().get(i).toString() + "\n"));
                    p.clear();
                    p.add(new Chunk("ISI UCAPAN"));
                    document.add(p);
                    document.add(new Paragraph (not.getProsesInterupsi().get(i).toString() + "\n"));
                    i++;
            }
            j++; 
            document.add(new Paragraph (not.getInterupsi().keySet().toArray()[j].toString()));
            document.add(new Paragraph (not.getProsesPembicara().get(i).toString() + "\n"));
            document.add(p);
            document.add(new Paragraph (not.getProsesInterupsi().get(i).toString() + "\n"));
            //j++;
        }
        document.close();
        Desktop.getDesktop().open(new File("Ringkasan Berbasis Agenda MMR.pdf"));
        //String s = not.getIsiInterupsi().get(0).get(0);
        
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
        p.add(new Chunk("RINGKASAN RISALAH RAPAT \n", font));
        p.add(new Chunk("BERBASIS AGENDA \n", fontUnderline)); 
        document.add(p);
        p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(new Chunk("\nA. Informasi Umum \n", font));
        document.add(p);
        
        //Bagian Awal
        p = new Paragraph("\n");
        document.add(p);
        table = setIndentationCover("Tanggal Rapat", SEPARATOR, not.getTanggalRapat().get(0));
        document.add(table);
        if (!not.getTahunSidang().isEmpty()){
            table = setIndentationCover("Tahun Sidang", SEPARATOR, not.getTahunSidang().get(0));
            document.add(table);
        }
        
        table = setIndentationCover("Masa Persidangan",SEPARATOR, not.getMasaPersidangan().get(0));
        document.add(table);
        if (!not.getUrutanRapat().isEmpty() || !not.getUrutanRapat().get(0).equals("null")){
            table = setIndentationCover("Rapat Ke", SEPARATOR, not.getUrutanRapat().get(0));
            document.add(table);
        }
        p = new Paragraph();
        
        //Bagian daftar hadir anggota
        List list = new List (List.UNORDERED);
//        p.setAlignment(Element.ALIGN_LEFT);
//        p.add(new Chunk("\nB. Kehadiran Anggota \n", font));
//        document.add(p);
//        if (!not.getDaftarHadirAnggota().isEmpty()){
//            ListItem item; 
//            for (int i = 0; i < not.getDaftarHadirAnggota().size(); i++){
//
//                list.setIndentationLeft(40);
//                item = new ListItem(not.getDaftarHadirAnggota().get(i));
//                item.setAlignment(Element.ALIGN_JUSTIFIED);
//                list.add(item);
//            }
//            document.add(list);
//        }
        
        //bagian agenda rapat 
        p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(new Chunk("\nB. Agenda Rapat \n", font));
        document.add(p);
        list = new List (List.ORDERED);
        if (!not.getAcaraRapat().isEmpty()){
            ListItem item; 
            for (int i = 0; i < not.getAcaraRapat().size(); i++){

                list.setIndentationLeft(40);
                item = new ListItem(not.getAcaraRapat().get(i));
                item.setAlignment(Element.ALIGN_JUSTIFIED);
                list.add(item);
            }
            document.add(list);
        }
        
        //ucapan penting per agenda 
        p = new Paragraph();
        p.setAlignment(Element.ALIGN_LEFT);
        p.add(new Chunk("\nC. Ucapan Penting Per-Agenda Rapat \n", font));
        document.add(p);
        int j = 0; 
        list = new List (List.ORDERED);
        ListItem item; 
        list.setIndentationLeft(25);
        item = new ListItem(not.getInterupsi().keySet().toArray()[j].toString());
        item.setAlignment(Element.ALIGN_JUSTIFIED);
        item.setFont(font);
        list.add(item);
        document.add(list);
        for (int i = 0; i < not.getProsesPembicara().size(); i++){
            
            while (not.getInterupsi().keySet().toArray()[j] == not.getProsesAcara().get(i) && i < not.getProsesPembicara().size()-1 ){
                    //acaranya apa, identifikasi utterance termasuk agenda itu apa ngak 
                    if (!not.getProsesInterupsi().get(i).isEmpty() && not.getProsesInterupsi().get(i).get(0).length() > 3 && !not.getProsesPembicara().get(i).equals("KETUA RAP AT") && !not.getProsesPembicara().get(i).equals("KETUA RAPAT") ){
                        //document.add(new Paragraph (not.getProsesPembicara().get(i).toString() + "\n"));
                        p = new Paragraph();
                        p.setAlignment(Element.ALIGN_LEFT);
                        p.setIndentationLeft(30);
                        p.add(new Chunk(not.getProsesPembicara().get(i).toString(), font));
                        document.add(p);
                        p.clear();
                        p.add(new Chunk("ISI UCAPAN"));
                        document.add(p);
                        for (int k = 0; k < not.getProsesInterupsi().get(i).size(); k++ ){
                            List listIterasi = new List (List.UNORDERED);
                            listIterasi.setIndentationLeft(35);
                            item = new ListItem(not.getProsesInterupsi().get(i).get(k));
                            item.setAlignment(Element.ALIGN_JUSTIFIED);
                            item.setFont(font);
                            listIterasi.add(item);
                            document.add(listIterasi);
                        }
                        //document.add(new Paragraph (not.getProsesInterupsi().get(i).toString() + "\n"));
                    }
                    i++;
            }
            j++; 
            if (j < not.getInterupsi().size()-1){
                list.getLastItem().clear();
                list.setIndentationLeft(25);
                item = new ListItem(not.getInterupsi().keySet().toArray()[j].toString());
                item.setAlignment(Element.ALIGN_JUSTIFIED);
                item.setFont(font);
                
                list.add(item);
                
                document.add(list);
                //document.add(new Paragraph (not.getInterupsi().keySet().toArray()[j].toString()));
            }
            if (!not.getProsesPembicara().get(i).equals("KETUA RAP AT")){
                p = new Paragraph();
                p.setAlignment(Element.ALIGN_LEFT);
                p.setIndentationLeft(30);
                p.add(new Chunk(not.getProsesPembicara().get(i).toString(), font));
                document.add(p);
                //document.add(new Paragraph (not.getProsesPembicara().get(i).toString() + "\n"));
                p.clear();
                p.add(new Chunk("ISI UCAPAN"));
                document.add(p);
                for (int k = 0; k < not.getProsesInterupsi().get(i).size(); k++ ){
                    List listIterasi = new List (List.UNORDERED);
                    listIterasi.setIndentationLeft(35);
                    item = new ListItem(not.getProsesInterupsi().get(i).get(k));
                    item.setAlignment(Element.ALIGN_JUSTIFIED);
                    item.setFont(font);
                    listIterasi.add(item);
                    document.add(listIterasi);
                }
            }
            //document.add(new Paragraph (not.getProsesInterupsi().get(i).toString() + "\n"));
            //j++;
        }
        
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
    // panggil pdf open 
    //Desktop.getDesktop().open(new File("path/to/pdf"));
    
}
