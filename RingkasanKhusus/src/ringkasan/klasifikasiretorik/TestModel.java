/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan.klasifikasiretorik;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static ringkasan.klasifikasiretorik.PraProses.numID;

import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils;
import weka.filters.unsupervised.attribute.Add;

/**
 *
 * @author mochamadtry
 */
public class TestModel {
    public static List<String> predictResult = new ArrayList(); 
    
	public void TestToArff(AksesDatabase da, String basicpath, String id)
			throws FileNotFoundException, SQLException, IOException, Exception {
		ResultSet rs = null;
		rs = da.selectquery("SELECT sequence, length, kalimat.posisi AS kal_posisi, significant, keywordclass "
				+ "FROM ekstraksifitur, kalimat \n" + "WHERE kalimat.id = ekstraksifitur.id_kalimat "
				+ "AND id_notulen = " + id);

		PrintWriter pw = new PrintWriter(new File(basicpath +   File.separator + "req" + File.separator + "test.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("prevLabel");
		sb.append(',');
		sb.append("length");
		sb.append(',');
		sb.append("posisi");
		sb.append(',');
		sb.append("significant");
		sb.append(',');
		sb.append("keywordclass");
		sb.append('\n');

		while (rs.next()) {
			sb.append(rs.getString("sequence"));
			sb.append(',');
			sb.append(rs.getString("length"));
			sb.append(',');
			sb.append(rs.getString("kal_posisi"));
			sb.append(',');
			sb.append(rs.getString("significant"));
			sb.append(',');
			sb.append(rs.getString("keywordclass"));
			sb.append('\n');
		}

		pw.write(sb.toString());
		pw.close();

		CSVLoader loader = new CSVLoader();
		ArffSaver saver = new ArffSaver();

		loader.setSource(new File(basicpath +   File.separator + "req" + File.separator + "test.csv"));
		Instances data = loader.getDataSet();

		Add ad = new Add();
		ad.setAttributeIndex("last");
		ad.setAttributeName("label");
		ad.setNominalLabels("Other,Statement,Request,Opinion,Suggestion");
		ad.setInputFormat(data);

		data = Add.useFilter(data, ad);

		// save ARFF
		saver.setInstances(data);
		saver.setFile(new File(basicpath +   File.separator + "req" + File.separator + "test.arff"));
		saver.writeBatch();

	}

	public void Predict(AksesDatabase da, String basicpath, String id, String fileKlasifikasi) throws Exception {
		/*//Classifier cls = (Classifier) SerializationHelper.read(basicpath + "fix.model");

		// predict instance class values
		Instances originalTrain = new Instances(new BufferedReader(new FileReader(basicpath + "test.arff")));

		// reader.close();
		// setting class attribute
		originalTrain.setClassIndex(originalTrain.numAttributes() - 1);
		
		int j = 0;
		ResultSet rs = da.selectquery("SELECT * FROM kalimat WHERE id_notulen = " + id + " ORDER BY id ASC LIMIT 1");

		while (rs.next()) {
			j = rs.getInt("id");
		}

		for (int i = 0; i < originalTrain.numInstances(); i++) {
			double value = cls.classifyInstance(originalTrain.instance(i));
			String prediction = originalTrain.classAttribute().value((int) value);

			System.out.println("The predicted value of instance " + Integer.toString(i) + ": " + prediction);
			
			da.updatequery("UPDATE kalimat SET id_kelas = '" + prediction + "' WHERE id = " + j++);

		}*/
		
		ConverterUtils.DataSource source1 = new ConverterUtils.DataSource(basicpath +   File.separator + "req" + File.separator + "baseline3_resample.arff");
        Instances train = source1.getDataSet();
        // setting class attribute if the data format does not provide this information
        // For example, the XRFF format saves the class attribute information as well
        if (train.classIndex() == -1)
            train.setClassIndex(train.numAttributes() - 1);

        ConverterUtils.DataSource source2 = new ConverterUtils.DataSource(basicpath +   File.separator + "req" + File.separator + "test.arff");
        Instances test = source2.getDataSet();
        // setting class attribute if the data format does not provide this information
        // For example, the XRFF format saves the class attribute information as well
        if (test.classIndex() == -1)
            test.setClassIndex(train.numAttributes() - 1);

        // model

        IBk ibk = new IBk();
        ibk.buildClassifier(train);

        // this does the trick  
        int j = 0;
		ResultSet rs = da.selectquery("SELECT * FROM kalimat WHERE id_notulen = " + id + " ORDER BY id ASC LIMIT 1");

		while (rs.next()) {
			j = rs.getInt("id");
		}

		for (int i = 0; i < test.numInstances(); i++) {
			double label = ibk.classifyInstance(test.instance(i));
                        test.instance(i).setClassValue(label);
                        String prediction = test.instance(i).stringValue(5);
	        
			da.updatequery("UPDATE kalimat SET id_kelas = '" + prediction + "' WHERE id = " + j++);
                        predictResult.add(prediction); 
                        //System.out.println ("predict : " + prediction);

		}
                System.out.println("ukuran yanng terprediksi: " + predictResult.size());
                try {
                    //Whatever the file path is.
                    File statText = new File(fileKlasifikasi);
//                    File statText = new File("G:\\Kuliah\\8th semester\\TA II\\tugas-akhir\\RingkasanKhusus\\hasilKlasifikasi.txt");
                    FileOutputStream is = new FileOutputStream(statText);
                    OutputStreamWriter osw = new OutputStreamWriter(is);    
                    BufferedWriter w = new BufferedWriter(osw);
                        for (int i = 0; i < numID.size(); i++){
                            //w.write(numID.get(i).toString()+ " ");
                            //w.write(teks.get(i) + " ");
                            w.write(predictResult.get(i));
                            w.newLine();
                        }
                        w.close();
                    
                    
                } catch (IOException e) {
                    System.err.println("Problem writing to the file statsTest.txt");
                    System.err.println(e.getMessage());
                }
        
	}
    
}
