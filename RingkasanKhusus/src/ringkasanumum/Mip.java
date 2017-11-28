/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasanumum;
import java.util.List;
import org.gnu.glpk.GLPK;
import org.gnu.glpk.GLPKConstants;
import org.gnu.glpk.GlpkException;
import org.gnu.glpk.SWIGTYPE_p_double;
import org.gnu.glpk.SWIGTYPE_p_int;
import org.gnu.glpk.glp_prob;
import org.gnu.glpk.glp_iocp;

/**
 *
 * @author mochamadtry
 */
public class Mip {

    /**
     * @param args the command line arguments
     */
  
     /**
     * write integer solution
     * @param mip problem
     */
    static void write_mip_solution(glp_prob lp, List<Integer> list) {
        int i;
        int n;
        String name;
        double val;

        name = GLPK.glp_get_obj_name(lp);
        val  = GLPK.glp_mip_obj_val(lp);
        System.out.print("name" + name);
        System.out.print(" = ");
        System.out.println("val" + val);
        n = GLPK.glp_get_num_cols(lp);
        System.out.println("jumlah n " + n );
        for(i=1; i <= n; i++) {
            name = GLPK.glp_get_col_name(lp, i);
            val  = GLPK.glp_mip_col_val(lp, i);
            System.out.print(i);
            System.out.print(" = ");
            System.out.println("val " + val);
            if (val == 1.00){
                list.add(i);
            }
        }
    }
    
   public static void selectedSentencesILP (Notulen not, int sekuens, List<Integer> list){
       // TODO code application logic here
        glp_prob lp;
        glp_iocp iocp;
        SWIGTYPE_p_int ind;
        SWIGTYPE_p_double val;
        int ret;

        try {
            // Create problem
            lp = GLPK.glp_create_prob(); //bikin masalah
            System.out.println("Problem created"); //ga ngaruh
            //GLPK.glp_set_prob_name(lp, "myProblem"); //ga ngaruh

            // Define columns
            
            int kombinasi = (not.getProsesInterupsi().get(sekuens).size()*(not.getProsesInterupsi().get(sekuens).size()-1))/2;
            int var = kombinasi +not.getProsesInterupsi().get(sekuens).size();
            System.out.println ("jumlah baris " + var + " " + not.getProsesInterupsi().get(sekuens).size() + " " + kombinasi );
            GLPK.glp_add_cols(lp, var); //nambah jumlah variable
            for (int i = 1; i <= var; i++){
                //GLPK.glp_set_col_name(lp, i + 1, "x" + (i+1));
                GLPK.glp_set_col_kind(lp, i, GLPKConstants.GLP_IV); // hasil maksimalnya adalah integer
                GLPK.glp_set_col_bnds(lp, i, GLPKConstants.GLP_DB, 0, 1);
            }
           

            // Create constraints
            int jumlahConstraints = (3*kombinasi) + 1;
            
            GLPK.glp_add_rows(lp, jumlahConstraints);
            
            //constraint pertama : jumlah dari seluruh kata dari kalimat 
            
            GLPK.glp_set_row_bnds(lp, 1, GLPKConstants.GLP_UP, 0, 200);

            ind = GLPK.new_intArray(var); // int[] = jumlah variable
            val = GLPK.new_doubleArray(var); 
            int jumlahKata;
            for (int i = 1; i <= var; i++){
                GLPK.intArray_setitem(ind, i, i);
                
                if (i <= not.getProsesInterupsi().get(sekuens).size()){
                    jumlahKata = not.getProsesInterupsi().get(sekuens).get(i-1).split(" ").length;
                    GLPK.doubleArray_setitem(val, i, jumlahKata);
                }
                else {
                    GLPK.doubleArray_setitem(val, i, 0);
                }
                    
            }
            GLPK.glp_set_mat_row(lp, 1, var, ind, val);
//            
            int counter = 2; 
            //constraints kedua 
            
            
            for (int i = 1; i < not.getProsesInterupsi().get(sekuens).size() - 1; i++ ){
                int x = i; 
                for (int j = i + 1; j < not.getProsesInterupsi().get(sekuens).size(); j++){
                    
                    for (int k = 1; k <= var; k++ ){
                        GLPK.doubleArray_setitem(val, k, 0);
                    }
                    
                    GLPK.doubleArray_setitem(val, i, -1);
                    GLPK.doubleArray_setitem(val, x+not.getProsesInterupsi().get(sekuens).size(), 1);
                    GLPK.glp_set_row_bnds(lp, counter, GLPKConstants.GLP_UP, 0, 0);
                    GLPK.glp_set_mat_row(lp, counter, var, ind, val);
                    counter++;
                    x++;
                }
                
            }
//            
            //constraint ketiga
            //GLPK.glp_set_row_bnds(lp, 1, GLPKConstants.GLP_UP, 0, 0);
            //GLPK.glp_set_row_name(lp, 2, "c2");
            //GLPK.glp_set_row_bnds(lp, 2, GLPKConstants.GLP_UP, 0, 5); 
            
            for (int i = 1; i < not.getProsesInterupsi().get(sekuens).size() - 1; i++ ){
                int x = i; 
                for (int j = i + 1; j < not.getProsesInterupsi().get(sekuens).size(); j++){
                    for (int k = 1; k <= var; k++ ){
                        GLPK.doubleArray_setitem(val, k, 0);
                    }
                    GLPK.doubleArray_setitem(val, j, -1);
                    GLPK.doubleArray_setitem(val, x+not.getProsesInterupsi().get(sekuens).size(), 1);
                    GLPK.glp_set_row_bnds(lp, counter, GLPKConstants.GLP_UP, 0, 0);
                    GLPK.glp_set_mat_row(lp, counter, var, ind, val);
                    counter++;
                    x++;
                }
                
            }
//            
            //constraint keempat
            //GLPK.glp_set_row_name(lp, 2, "c2");
            //GLPK.glp_set_row_bnds(lp, 2, GLPKConstants.GLP_UP, 0, 5); 
            
            for (int i = 1; i < not.getProsesInterupsi().get(sekuens).size() - 1; i++ ){
                int x = i; 
                for (int j = i + 1; j < not.getProsesInterupsi().get(sekuens).size(); j++){
                    for (int k = 1; k <= var; k++ ){
                        GLPK.doubleArray_setitem(val, k, 0);
                    }
                    GLPK.doubleArray_setitem(val, i, 1);
                    GLPK.doubleArray_setitem(val, j, 1);
                    GLPK.doubleArray_setitem(val, x+not.getProsesInterupsi().get(sekuens).size(), 1);
                    GLPK.glp_set_row_bnds(lp, counter, GLPKConstants.GLP_UP, 0, 1);
                    GLPK.glp_set_mat_row(lp, counter, var, ind, val);
                    counter++;
                    x++;
                }
                
            }

//            GLPK.delete_doubleArray(val);
//            GLPK.delete_intArray(ind);
            
            // Maximize z =  17 * x1 + 12* x2 //a1*cos12
            // subject to
            //   10 x1 + 7 x2 <= 40
            //      x1 +   x2 <=  5
            // where,
            //   0.0 <= x1  integer
            //   0.0 <= x2  integer

            // Define objective
            int k = 0; 
            GLPK.glp_set_obj_name(lp, "obj");
            GLPK.glp_set_obj_dir(lp, GLPKConstants.GLP_MAX);
            for (int i = 0 ; i < not.getProsesInterupsi().get(sekuens).size(); i++ ){
                GLPK.glp_set_obj_coef(lp, i+1, not.getValueCosine().get(sekuens).get(i));
            }
            
            for (int j = not.getProsesInterupsi().get(sekuens).size()+1; j< var; j++){
                GLPK.glp_set_obj_coef(lp, j, not.getValueCosineKom().get(sekuens).get(k)*-1);
                k++;
            }
            
            GLPK.glp_write_lp(lp, null, "fungsiILP.txt");
            // Solve model
            iocp = new glp_iocp();
            GLPK.glp_init_iocp(iocp);
            iocp.setPresolve(GLPKConstants.GLP_ON);
            ret = GLPK.glp_intopt(lp, iocp);

            // Retrieve solution
            if (ret == 0) {
                write_mip_solution(lp, list);
            } else {
                System.out.println("The problem could not be solved");
            };
            
            System.out.println("lis " + list);

            // free memory
            GLPK.glp_delete_prob(lp);
        } catch (GlpkException ex) {
            ex.printStackTrace();
            ret = 1;
        }
        //System.exit(ret);
   }
}
