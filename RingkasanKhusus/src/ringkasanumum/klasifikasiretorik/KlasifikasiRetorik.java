/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum.klasifikasiretorik;

import java.io.IOException;
import java.sql.SQLException;
import ringkasanumum.Notulen;

/**
 *
 * @author mochamadtry
 */
public class KlasifikasiRetorik {
    public static void main(String[] args) throws IOException, SQLException, Exception {
        // TODO code application logic here
        Klasifikasi klasifikasi = new Klasifikasi();
        Notulen not = new Notulen();
        FileUpload upload = new FileUpload(); 
        upload.postFile(not);
        klasifikasi.klasifikasi(not);
        
    }
    //G:\Kuliah\8th semester\TA II\tugas-akhir\RingkasanKhusus\Cobapembicara.txt
    
}
