/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan;

import net.sf.javailp.Linear;
import net.sf.javailp.OptType;
import net.sf.javailp.Problem;
import net.sf.javailp.Result;
import net.sf.javailp.Solver;
import net.sf.javailp.SolverFactory;
import net.sf.javailp.SolverFactoryLpSolve;
//import net.sf.javailp.*;
import java.util.List;
import net.sf.javailp.Constraint;
import net.sf.javailp.ResultImpl;
import net.sf.javailp.SolverFactoryGLPK;

/**
 *
 * @author mochamadtry
 */
public class ILP {
     public void selectedWithILP(Notulen not,int sekuens, List<Integer> list){
        SolverFactory factory = new SolverFactoryGLPK(); // use lp_solve
        factory.setParameter(Solver.VERBOSE, 0);
        //factory.setParameter(Solver.TIMEOUT, 100); // set timeout to 100 seconds
        
        int kombinasi = (not.getProsesInterupsi().get(sekuens).size()*(not.getProsesInterupsi().get(sekuens).size()-1))/2;
        int var = kombinasi +not.getProsesInterupsi().get(sekuens).size();
        /**
        * Constructing a Problem:
        * Maximize: 143x+60y
        * Subject to:
        * 120x+210y <= 15000
        * 110x+30y <= 4000
        * x+y <= 75
        *
        * With x,y being integers
        *
        */
        Problem problem = new Problem();
        
        //set objective
        int k = 0; 
        Linear linear = new Linear();
        for (int i = 0 ; i < not.getProsesInterupsi().get(sekuens).size(); i++ ){
            String varName = "x".concat("_").concat(String.valueOf(i));
            linear.add( not.getValueCosine().get(sekuens).get(i), varName );
        }
        
        for (int i = 0 ; i < not.getProsesInterupsi().get(sekuens).size()-1; i++){
            for (int j = i+1; j< not.getProsesInterupsi().get(sekuens).size(); j++){
                String varName = "x".concat("_").concat(String.valueOf(i)).concat("_").concat(String.valueOf(j));
                linear.add(not.getValueCosineKom().get(sekuens).get(k)*-1, varName );
                k++;
            }
        }
        

        problem.setObjective(linear, OptType.MAX);
        
        // Create constraints
        int jumlahConstraints = (3*kombinasi) + 1;
        
        //Constraint 1
        int counter = 0; 
        linear = new Linear();
        int jumlahKata; 
        for (int i = 0; i < not.getProsesInterupsi().get(sekuens).size(); i++){
            jumlahKata = not.getProsesInterupsi().get(sekuens).get(i).split(" ").length;
            String varName = "x".concat("_").concat(String.valueOf(i));
            linear.add(jumlahKata, varName);
        }
        problem.add(new Constraint(String.valueOf(counter), linear, "<=", 100));
        counter++;
        //problem.add(linear, "<=", 200);
        
        for (int i = 0 ; i < not.getProsesInterupsi().get(sekuens).size()-1; i++){
            for (int j = i+1; j< not.getProsesInterupsi().get(sekuens).size(); j++){
                
                //aij-ai <= 0
                linear = new Linear();
                linear.add(-1, "x".concat("_").concat(String.valueOf(i)));
                linear.add(1, "x".concat("_").concat(String.valueOf(i)).concat("_").concat(String.valueOf(j)) );
                problem.add(new Constraint(String.valueOf(counter), linear, "<=", 0));
                counter++;
                
                //aij-aj <= 0
                linear = new Linear();
                linear.add(-1, "x".concat("_").concat(String.valueOf(j)));
                linear.add(1, "x".concat("_").concat(String.valueOf(i)).concat("_").concat(String.valueOf(j)) );
                problem.add(new Constraint(String.valueOf(counter), linear, "<=", 0));
                counter++;
                
                //ai + aj - aij <= 0
                linear = new Linear();
                linear.add(1, "x".concat("_").concat(String.valueOf(i)));
                linear.add(1, "x".concat("_").concat(String.valueOf(j)));
                linear.add(-1, "x".concat("_").concat(String.valueOf(i)).concat("_").concat(String.valueOf(j)) );
                problem.add(new Constraint(String.valueOf(counter), linear, "<=", 1));
                counter++;
                
            }
        }
        // add nilai alfa bernilai 0 dan 1 
        for (int i = 0 ; i < not.getProsesInterupsi().get(sekuens).size(); i++ ){
            linear = new Linear(); 
            String varName = "x".concat("_").concat(String.valueOf(i));
            linear.add(1,varName);
            problem.add(new Constraint(String.valueOf(counter), linear, ">=", 0));
            counter++;
            problem.add(new Constraint(String.valueOf(counter), linear, "<=", 1));
            counter++;
            problem.setVarType(varName, Integer.class);
        }
        
        for (int i = 0 ; i < not.getProsesInterupsi().get(sekuens).size()-1; i++ ){
            for (int j = i+1; j < not.getProsesInterupsi().get(sekuens).size(); j++){
                linear = new Linear(); 
                String varName = "x".concat("_").concat(String.valueOf(i)).concat("_").concat(String.valueOf(j));
                linear.add(1,varName);
                problem.add(new Constraint(String.valueOf(counter), linear, ">=", 0));
                counter++;
                problem.add(new Constraint(String.valueOf(counter), linear, "<=", 1));
                counter++;
                problem.setVarType(varName, Integer.class);
                
            }
            
        }
        
        
        
//        for (int i = 0; i < not.getProsesInterupsi().get(sekuens).size(); i++){
//            problem.setVarType(i, Integer.class);
//        }
        
        System.out.println("problem : " + problem.toString());
        Solver solver = factory.get(); // you should use this solver only once for one problem
        ResultImpl result = (ResultImpl) solver.solve(problem);
        System.out.println(solver);
        for (int i = 0; i < not.getProsesInterupsi().get(sekuens).size(); i++ ){
            System.out.println(result.get("x".concat("_").concat(String.valueOf(i))).intValue());
            if (result.get("x".concat("_").concat(String.valueOf(i))).intValue() == 1){
                System.out.println(i);
                list.add(i);
            }
        }
    }
    
}
