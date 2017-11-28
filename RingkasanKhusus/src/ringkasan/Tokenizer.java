/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author mochamadtry
 */
public class Tokenizer {
     public static TreeMap<String,Integer> tokenize(String sentence) {
        TreeMap<String,Integer> result=new TreeMap<String, Integer>();
        StringTokenizer st = new StringTokenizer(sentence);
//        while (st.hasMoreTokens()) {
//            System.out.println(st.nextToken());
//        }
//        PTBTokenizer ptbt = new PTBTokenizer(new StringReader(sentence),new CoreLabelTokenFactory(), "");
        //System.out.println(ptbt.toString());
        while (st.hasMoreTokens()) {
            String sWord=st.nextToken();//label.value().toLowerCase();
            Integer itemp=result.get(sWord);
            if (itemp==null) result.put(sWord,new Integer(1));
            else result.put(sWord,itemp+1);
        }
        return result;
    }

    public static double length(TreeMap<String,Integer> tm) {
        int result=0;
        for (Map.Entry<String,Integer> entry:tm.entrySet())
        {
            String key1 = entry.getKey();
            int frek1 = entry.getValue().intValue();
            result=result+frek1*frek1;
        }
        return Math.sqrt(result);
    }
    
}
