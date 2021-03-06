/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasankhusus;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author mochamadtry
 */
public class UIDesktop extends javax.swing.JFrame {

    /**
     * Creates new form UIDesktop
     */
    Notulen not  = new Notulen();
    public UIDesktop() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFileName = new javax.swing.JTextField();
        buttonFileName = new javax.swing.JButton();
        proses = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textFileName.setEditable(false);
        textFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFileNameActionPerformed(evt);
            }
        });

        buttonFileName.setText("...");
        buttonFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFileNameActionPerformed(evt);
            }
        });

        proses.setText("Proses");
        proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prosesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(textFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(buttonFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(proses)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonFileName)
                        .addComponent(proses))
                    .addContainerGap(139, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFileNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFileNameActionPerformed

    private void buttonFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFileNameActionPerformed
        final JFileChooser fileDialog = new JFileChooser();
        int returnVal = fileDialog.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = fileDialog.getSelectedFile().getPath();
            textFileName.setText(fileName);
            not.setPath(textFileName.getText());
            System.out.println(not.getPath());
        }
    }//GEN-LAST:event_buttonFileNameActionPerformed

    private void prosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prosesActionPerformed
//        Notulen not  = new Notulen();
//        //File f = new File(nameOfFile);
        IOFile file = new IOFile();
        try {
            file.loadFile(not);
        } catch (IOException ex) {
            Logger.getLogger(UIDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println(not.getSentences()); //jadinya ga di lowercase dulu 
//        System.out.println(not.getSentencesLine());
        PatternRegex pr = new PatternRegex();
        pr.regexTahunSidang(not);
        pr.regexMasaSidang(not);
        pr.regexUrutanRapat(not);
        pr.regexJenisRapat(not);
        pr.regexSifatRapat(not);
        pr.regexHariRapat(not);
        pr.regexTanggalRapat(not);
        pr.regexRapatDibuka(not);
        pr.regexRapatDitutup(not);
        pr.regexTempatRapat(not);
        pr.regexKotaRapat(not);
        pr.regexKetuaRapat(not);
        pr.regexPendampingKetua(not);
        try {
            pr.regexSekretarisRapat(not);
        } catch (IOException ex) {
            Logger.getLogger(UIDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
        pr.regexPendampingSekretaris(not);
        pr.regexAnggotaHadir(not);
        pr.regexSepucukSuratMasuk(not);
        pr.regexPucukSuratMasuk(not);
        pr.regexPenggantiAntarWaktu(not);
        pr.regexBiroPersidangan(not);
        try {
            pr.regexAcara(not);
        } catch (IOException ex) {
            Logger.getLogger(UIDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pr.regexAcaraRapat(not);
        } catch (IOException ex) {
            Logger.getLogger(UIDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
        pr.regexKesimpulan(not);
        System.out.println("a" + not.getTahunSidang());
        System.out.println("b" + not.getMasaSidang());
        System.out.println("c" + not.getUrutanRapat());
        System.out.println("d" + not.getJenisRapat());
        System.out.println("e" + not.getSifatRapat());
        System.out.println("f" + not.getHariRapat());
        System.out.println("g" + not.getTanggalRapat());
        System.out.println("h" + not.getRapatDibuka());
        System.out.println("i" + not.getRapatDitutup());
        System.out.println("j" + not.getTempatRapat());
        System.out.println("k" + not.getKotaRapat());
        System.out.println("l" + not.getKetuaRapat());
        System.out.println("m" + not.getPendampingKetua());
        System.out.println("n" + not.getSekretarisRapat());
        System.out.println("o" + not.getPendampingSekretaris());
        System.out.println("p" + not.getAnggotaHadir());
        System.out.println("q" + not.getPucukSuratMasuk());
        System.out.println("q1" + not.getSepucukSuratMasuk());
        System.out.println("r" + not.getPenggantiAntarWaktu());
        System.out.println("r1" + not.getPenggantiAntarWaktuTambahan());
        System.out.println("s" + not.getBiroPersidangan());
        System.out.println("t"+ not.getAcara());
        System.out.println("u"+ not.getAcaraRapat());
        System.out.println("w"+ not.getKesimpulan());
        try {
            file.writeResultSystem(not);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UIDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            CreatePDF PDF = new CreatePDF(not);
        } catch (IOException ex) {
            Logger.getLogger(UIDesktop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(UIDesktop.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }//GEN-LAST:event_prosesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIDesktop.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UIDesktop().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonFileName;
    private javax.swing.JButton proses;
    private javax.swing.JTextField textFileName;
    // End of variables declaration//GEN-END:variables
}
