/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan.klasifikasiretorik;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;

import cc.mallet.fst.SimpleTagger;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.stopwords.Rainbow;
import weka.core.stopwords.StopwordsHandler;
import weka.core.stopwords.WordsFromFile;
import weka.core.tokenizers.AlphabeticTokenizer;
import weka.core.tokenizers.Tokenizer;
import weka.filters.unsupervised.attribute.NominalToString;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author mochamadtry
 */
public class EkstraksiFitur {
    public int Length(String text, int leng) {

        String[] words = text.split("\\s+");

        if (words.length > leng) {
                return 1;
        }
        return 0;
    }
    
    public void RunLength(AksesDatabase da, String id) throws SQLException {
        ResultSet rs = da.selectquery("SELECT * FROM preprocessedkalimat, kalimat "
                        + "WHERE preprocessedkalimat.id_kalimat = kalimat.id " + "AND id_notulen = " + id);
        //System.out.println("result set " + rs.getArray(1).toString());
        while (rs.next()) {
                String text = rs.getString("text");

                da.updatequery("UPDATE ekstraksifitur SET length = " + Length(text, 4) + " WHERE id_kalimat = "
                                + rs.getString("id"));
        }
    }
    
    public void TermToArff(AksesDatabase da, String basicpath, String id)
			throws FileNotFoundException, SQLException, IOException, Exception {

        ResultSet rs = null;
        rs = da.selectquery("SELECT id_kalimat, text, id_kelas AS label FROM preprocessedkalimat, kalimat \n"
                        + "WHERE kalimat.id = preprocessedkalimat.id_kalimat AND id_notulen = " + id);

        PrintWriter pw = new PrintWriter(new File(basicpath +   File.separator + "req" + File.separator+ "significant.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("text");
        sb.append(',');
        sb.append("id_kalimat");
        sb.append(',');
        sb.append("labelclass");
        sb.append('\n');

        while (rs.next()) {
                sb.append("'").append(rs.getString("text")).append("'");
                sb.append(',');
                sb.append(rs.getString("id_kalimat"));
                sb.append(',');
                sb.append(rs.getString("label"));
                sb.append('\n');
        }

        pw.write(sb.toString());
        pw.close();

        CSVLoader loader = new CSVLoader();
        ArffSaver saver = new ArffSaver();

        loader.setSource(new File(basicpath +   File.separator + "req" + File.separator+ "significant.csv"));
        Instances data = loader.getDataSet();

        NominalToString filter1 = new NominalToString();
        filter1.setAttributeIndexes("first");
        filter1.setInputFormat(data);

        data = NominalToString.useFilter(data, filter1);

        StringToWordVector st = new StringToWordVector();
        st.setIDFTransform(true);
        st.setTFTransform(true);
        st.setAttributeIndices("first-last");
        st.setDoNotOperateOnPerClassBasis(true);
        st.setMinTermFreq(1);
        st.setPeriodicPruning(-1.0);

        StopwordsHandler stop = new Rainbow();
        st.setStopwordsHandler(stop);

        Tokenizer token = new AlphabeticTokenizer();

        st.setTokenizer(token);
        st.setWordsToKeep(10);
        st.setInputFormat(data);

        data = StringToWordVector.useFilter(data, st);

        // save ARFF
        saver.setInstances(data);
        saver.setFile(new File(basicpath+   File.separator + "req" + File.separator + "significant.arff"));
        saver.writeBatch();

    }
    
    public void Occurance(AksesDatabase da, String id, WordsFromFile wo, PraProses pr, String basicpath,
			String namafile) throws IOException, SQLException {

        PrintWriter pw = new PrintWriter(new File(basicpath +   File.separator + "req" + File.separator + namafile + ".txt"));
        StringBuilder sb = new StringBuilder();

        if (namafile.equalsIgnoreCase("significant")) {
                BufferedReader reader = new BufferedReader(new FileReader(basicpath +   File.separator + "req" + File.separator + namafile + ".arff"));
                Instances data = new Instances(reader);

                reader.close();
                // setting class attribute
                data.setClassIndex(data.numAttributes() - 1);

                for (int j = 0; j < data.numAttributes(); j++) {
                        if (data.attribute(j).isNumeric() && !data.attribute(j).name().equals("id_kalimat")) {
                                sb.append(data.attribute(j).name());
                                sb.append('\n');
                        }
                }
        } else {
                String[] label = { "Other", "Statement", "Request", "Suggestion", "Opinion" };
                int length = label.length;

                for (int a = 0; a < length; a++) {
                        BufferedReader reader = new BufferedReader(new FileReader(basicpath+   File.separator + "req" + File.separator + label[a] + ".arff"));
                        Instances data = new Instances(reader);

                        reader.close();
                        // setting class attribute
                        data.setClassIndex(data.numAttributes() - 1);

                        for (int j = 0; j < data.numAttributes(); j++) {
                                if (data.attribute(j).isNumeric() && !data.attribute(j).name().equals("id_kalimat")) {
                                        sb.append(data.attribute(j).name());
                                        sb.append('\n');
                                }
                        }
                }
        }

        pw.write(sb.toString());
        pw.close();

        File inputFile = new File(basicpath+   File.separator + "req" + File.separator + namafile + ".txt");
        wo.setStopwords(inputFile);

        ResultSet rs = da.selectquery("SELECT * FROM preprocessedkalimat, kalimat \n"
                        + "WHERE kalimat.id = preprocessedkalimat.id_kalimat AND id_notulen = " + id);

        while (rs.next()) {
                String text = rs.getString("text");

                if (namafile.equalsIgnoreCase("significant")) {
                        da.updatequery("UPDATE ekstraksifitur SET significant = " + pr.StopwordFromFile(text, wo)
                                        + " WHERE id_kalimat = " + rs.getString("id"));
                } else {
                        da.updatequery("UPDATE ekstraksifitur SET keywordclass = " + pr.StopwordFromFile(text, wo)
                                        + " WHERE id_kalimat = " + rs.getString("id"));
                }
        }

    }
    
    public void Significant(AksesDatabase da, PraProses pr, String basicpath, String id)
			throws IOException, SQLException, Exception {
		WordsFromFile wo = new WordsFromFile();

		TermToArff(da, basicpath, id);
		Occurance(da, id, wo, pr, basicpath, "significant");
                System.out.println("pass 2'");
	}

    public void ClassToArff(AksesDatabase da, String basicpath, String id) throws Exception {
            String[] label = { "Other", "Statement", "Request", "Suggestion", "Opinion" };

            int length = label.length;

            for (int a = 0; a < length; a++) {
                    ResultSet rs = null;

                    rs = da.selectquery("SELECT id_kalimat, text, id_kelas AS label FROM preprocessedkalimat, kalimat \n"
                                    + "WHERE kalimat.id = preprocessedkalimat.id_kalimat AND id_kelas = '" + label[a] + "' "
                                    + "AND id_notulen = " + id);

                    PrintWriter pw = new PrintWriter(new File(basicpath+   File.separator + "req" + File.separator + label[a] + ".csv"));

                    StringBuilder sb = new StringBuilder();
                    sb.append("text");
                    sb.append(',');
                    sb.append("id_kalimat");
                    sb.append(',');
                    sb.append("labelclass");
                    sb.append('\n');

                    while (rs.next()) {
                            sb.append("'").append(rs.getString("text")).append("'");
                            sb.append(',');
                            sb.append(rs.getString("id_kalimat"));
                            sb.append(',');
                            sb.append(rs.getString("label"));
                            sb.append('\n');
                    }

                    pw.write(sb.toString());
                    pw.close();

            }

            CSVLoader loader = new CSVLoader();
            ArffSaver saver = new ArffSaver();

            for (int a = 0; a < length; a++) {

                    loader.setSource(new File(basicpath+   File.separator + "req" + File.separator + label[a] + ".csv"));
                    Instances data = loader.getDataSet();

                    NominalToString filter1 = new NominalToString();
                    filter1.setAttributeIndexes("first");
                    filter1.setInputFormat(data);

                    data = NominalToString.useFilter(data, filter1);

                    StringToWordVector st = new StringToWordVector();
                    st.setIDFTransform(true);
                    st.setTFTransform(true);
                    st.setAttributeIndices("first-last");
                    st.setDoNotOperateOnPerClassBasis(true);
                    st.setMinTermFreq(1);
                    st.setPeriodicPruning(-1.0);

                    StopwordsHandler stop = new Rainbow();
                    st.setStopwordsHandler(stop);

                    Tokenizer token = new AlphabeticTokenizer();

                    st.setTokenizer(token);
                    st.setWordsToKeep(10);
                    st.setInputFormat(data);

                    data = StringToWordVector.useFilter(data, st);

                    // save ARFF
                    saver.setInstances(data);
                    saver.setFile(new File(basicpath+   File.separator + "req" + File.separator + label[a] + ".arff"));
                    saver.writeBatch();
            }
    }

    public void Cuephrase(AksesDatabase da, PraProses pr, String basicpath, String id) throws Exception {
            // String[] label = { "Other", "Statement", "Request", "Suggestion",
            // "Opinion" };

            WordsFromFile wo = new WordsFromFile();
            // ClassToArff(da, basicpath, id);

            // int length = label.length;

            // for (int a = 0; a < length; a++) {
            Occurance(da, id, wo, pr, basicpath, "keywordclass");
            // }
    }

    public void DataTest(AksesDatabase da, String id, String basicpath) throws FileNotFoundException, SQLException {
            ResultSet rs = da.selectquery("SELECT length, kalimat.posisi as kal_posisi, significant, keywordclass "
                            + "FROM kalimat, ekstraksifitur WHERE kalimat.id = ekstraksifitur.id_kalimat " + "AND id_notulen = "
                            + id);

            PrintWriter pw = new PrintWriter(new File(basicpath+   File.separator + "req" + File.separator + "datatest.txt"));
            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
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
    }

    @SuppressWarnings("resource")
    public void Sequence(AksesDatabase da, String basicpath, String id) throws Exception {
		/*
		 * String[] arg = { "--train", "true", "--model-file", basicpath +
		 * "trainmodel", basicpath + "datatrain.txt" }; SimpleTagger.main(arg);
		 */

		String[] arg1 = { "--model-file", basicpath+   File.separator + "req" + File.separator + "trainmodel", basicpath +   File.separator + "req" + File.separator+ "datatest.txt" };

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		SimpleTagger.main(arg1);
		// Put things back
		System.out.flush();
		System.setOut(old);
		// Show what happened
		String hasil = baos.toString();

		PrintWriter pw = new PrintWriter(new File(basicpath +   File.separator + "req" + File.separator + "hasil.txt"));
		StringBuilder sb = new StringBuilder();
		sb.append(hasil);
		pw.write(sb.toString());
		pw.close();

		int i = 0;
		ResultSet rs = da.selectquery("SELECT * FROM kalimat WHERE id_notulen = " + id + " ORDER BY id ASC LIMIT 1");

		while (rs.next()) {
			i = rs.getInt("id");
		}

		String line = "";
		BufferedReader input = new BufferedReader(new FileReader(new File(basicpath +   File.separator + "req" + File.separator+ "hasil.txt")));

		while ((line = input.readLine()) != null) {
			da.updatequery("UPDATE ekstraksifitur SET sequence = '" + line.trim() + "' WHERE id_kalimat = " + i++);

		}

	}
    
}
