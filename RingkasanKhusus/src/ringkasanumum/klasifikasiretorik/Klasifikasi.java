/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum.klasifikasiretorik;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import cc.mallet.fst.SimpleTagger;
import ringkasanumum.Notulen;
import java.sql.SQLException;
/**
 *
 * @author mochamadtry
 */
public class Klasifikasi {
    public Klasifikasi() {
        //super();
        // TODO Auto-generated constructor stub
    }
    
    public void klasifikasi(Notulen not) throws SQLException, Exception{
        //Notulen not = new Notulen();
        EkstraksiFitur ef = new EkstraksiFitur();
        AksesDatabase da = new AksesDatabase();
        Praproses pr = new Praproses();
        TestModel tm = new TestModel();
        //Tampilkan ta = new Tampilkan();
        String id = not.getId();
        System.out.println("id " + id);
        String basicpath = not.getBasicPath();
        System.out.println("basicpath " + basicpath);


//		try {
			ef.RunLength(da, id);
                        System.out.println("pass 1");
			ef.Significant(da, pr, basicpath, id);
                        System.out.println("pass 2");
			ef.Cuephrase(da, pr, basicpath, id);
                        System.out.println("pass 3");
//			
			ef.DataTest(da, id, basicpath);
                        System.out.println("pass 4");
			ef.Sequence(da, basicpath, id);
                        System.out.println("pass 5");
			
			tm.TestToArff(da, basicpath, id);
			tm.Predict(da, basicpath, id);
			
			

//			request.setAttribute("hasil", ta.tampil(da, id));
//		} catch (Exception ex) {
//			request.setAttribute("hasil", "There was an error: " + ex.getMessage());
//		}

//		RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
//
//		dis.forward(request, response);
//	
    }
    
}
