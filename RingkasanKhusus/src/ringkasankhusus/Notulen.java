/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasankhusus;

import java.util.List;
import java.util.Map;

/**
 *
 * @author mochamadtry
 */
public class Notulen {
    private String file;
    private String text;
    private String id;
    private String path; 
    private List sentences; 
    private List sentencesLine; 
    private List bagianAwal; //Berisi kalimat bagian cover  
    private List bagianIsi; //Berisi kalimat bagian isi, setelah cover 
    
    //identitas 
    private List<String> tahunSidang; 
    private List<String> masaSidang;
    private List<String> urutanRapat;
    private List<String> jenisRapat;
    private List<String> sifatRapat; 
    private List<String> hariRapat;
    private List<String> tanggalRapat;
    private List<String> rapatDibuka; //waktu rapat dibuka, pukul berapa
    private List<String> rapatDitutup; //waktu rapat ditutup, pukul berapa
    private List<String> tempatRapat; 
    private List<String> kotaRapat; 
    private List<String> acara; 
    private List<String> acaraRapat;
    private List<String> ketuaRapat;
    private List<String> pendampingKetua;
    private List<String> sekretarisRapat; 
    private List<String> pendampingSekretaris;
    private List<String> anggotaHadir; 
    private List<String> pucukSuratMasuk; 
    private List<String> sepucukSuratMasuk;
    private List<String> penggantiAntarWaktu; 
    private List<String> penggantiAntarWaktuTambahan; //kasus khusus, tidak disebutkan secara implisit PAW
    private List<String> kesimpulan; 
    private List<String> biroPersidangan; 
    private List<String> prosesPembicara; 
    private List<String> daftarAnggota; 
    
    

    private Map map;
    private Map segmentasiAcara;
    private List<String> pembicara; 
    private List<List<String>> isiInterupsi;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
        
        public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
        
        public List getSentences(){
            return sentences; 
        }
        
        public void setSentences (List sentences){
            this.sentences = sentences;
        }
        
        public List getSentencesLine(){
            return sentencesLine; 
        }
        
        public void setSentencesLine (List sentencesLine){
            this.sentencesLine = sentencesLine;
        }
        
        public List getBagianAwal(){
            return bagianAwal; 
        }
        
        public void setBagianAwal (List bagianAwal){
            this.bagianAwal = bagianAwal;
        }
        
        public List getBagianIsi(){
            return bagianIsi; 
        }
        
        public void setBagianIsi (List bagianIsi){
            this.bagianIsi = bagianIsi;
        }
        
        //setter getter slot 
        public List<String> getTahunSidang(){
            return tahunSidang; 
        }
        
        public void setTahunSidang (List<String> tahunSidang){
            this.tahunSidang = tahunSidang; 
        }
        
        public List<String> getMasaSidang(){
            return masaSidang; 
        }
        
        public void setMasaSidang (List<String> masaSidang){
            this.masaSidang = masaSidang; 
        }
        
        public List<String> getUrutanRapat(){ //rapat ke-
            return urutanRapat; 
        }
        
        public void setUrutanRapat (List<String> urutanRapat){
            this.urutanRapat = urutanRapat; 
        }
        
        public List<String> getJenisRapat(){
            return jenisRapat; 
        }
        
        public void setJenisRapat (List<String> jenisRapat){
            this.jenisRapat = jenisRapat; 
        }
        
        public List<String> getSifatRapat(){
            return sifatRapat; 
        }
        
        public void setSifatRapat (List<String> sifatRapat){
            this.sifatRapat = sifatRapat; 
        }
        
        public List<String> getHariRapat(){
            return hariRapat; 
        }
        
        public void setHariRapat (List<String> hariRapat){
            this.hariRapat = hariRapat; 
        }
        
        public List<String> getTanggalRapat(){
            return tanggalRapat; 
        }
        
        public void setTanggalRapat (List<String> tanggalRapat){
            this.tanggalRapat = tanggalRapat; 
        }
        
        public List<String> getRapatDibuka(){
            return rapatDibuka; 
        }
        
        public void setRapatDibuka (List<String> rapatDibuka){
            this.rapatDibuka = rapatDibuka; 
        }
        
        public List<String> getRapatDitutup(){
            return rapatDitutup; 
        }
        
        public void setRapatDitutup (List<String> rapatDitutup){
            this.rapatDitutup  = rapatDitutup; 
        }
        
        public List<String> getTempatRapat(){
            return tempatRapat; 
        }
        
        public void setTempatRapat (List<String> tempatRapat){
            this.tempatRapat  = tempatRapat; 
        }
        
        public List<String> getKotaRapat(){
            return kotaRapat; 
        }
        
        public void setKotaRapat (List<String> kotaRapat){
            this.kotaRapat  = kotaRapat; 
        }
        
        public List<String> getAcara(){
            return acara; 
        }
        
        public void setAcara(List<String> acara){
            this.acara = acara;
        }
                
        
        
        public void setAcaraRapat (List<String> acaraRapat){
            this.acaraRapat = acaraRapat;
            System.out.println("lala" + this.acaraRapat);
        }
        
        public List<String> getAcaraRapat(){
            return acaraRapat; 
        }
        
        public void setKetuaRapat (List<String> ketuaRapat){
            this.ketuaRapat  = ketuaRapat; 
        }
                
        public List<String> getKetuaRapat(){
            return ketuaRapat; 
        }
        
        public void setPendampingKetua (List<String> pendampingKetua){
            this.pendampingKetua  = pendampingKetua; 
        }
                
        public List<String> getPendampingKetua(){
            return pendampingKetua; 
        }
        
        public void setSekretarisRapat (List<String> sekretarisRapat){
            this.sekretarisRapat  = sekretarisRapat; 
        }
                
        public List<String> getSekretarisRapat(){
            return sekretarisRapat; 
        }
        
        public void setPendampingSekretaris (List<String> pendampingSekretaris){
            this.pendampingSekretaris  = pendampingSekretaris; 
        }
                
        public List<String> getPendampingSekretaris(){
            return pendampingSekretaris; 
        }
        
        public void setAnggotaHadir (List<String> anggotaHadir){
            this.anggotaHadir  = anggotaHadir; 
        }
                
        public List<String> getAnggotaHadir(){
            return anggotaHadir; 
        }
        
        public void setPucukSuratMasuk (List<String> pucukSuratMasuk){
            this.pucukSuratMasuk  = pucukSuratMasuk; 
        }
                
        public List<String> getPucukSuratMasuk(){
            return pucukSuratMasuk; 
        }
        
        public void setSepucukSuratMasuk (List<String> sepucukSuratMasuk){
            this.sepucukSuratMasuk  = sepucukSuratMasuk; 
        }
                
        public List<String> getSepucukSuratMasuk(){
            return sepucukSuratMasuk; 
        }
        
        public void setPenggantiAntarWaktu (List<String> penggantiAntarWaktu){
            this.penggantiAntarWaktu  = penggantiAntarWaktu; 
        }
                
        public List<String> getPenggantiAntarWaktu(){
            return penggantiAntarWaktu; 
        }
        
        public void setPenggantiAntarWaktuTambahan (List<String> penggantiAntarWaktuTambahan){
            this.penggantiAntarWaktuTambahan  = penggantiAntarWaktuTambahan; 
        }
                
        public List<String> getPenggantiAntarWaktuTambahan(){
            return penggantiAntarWaktuTambahan; 
        }
        
        public void setKesimpulan(List<String> kesimpulan){
            this.kesimpulan  = kesimpulan; 
        }
                
        public List<String> getKesimpulan(){
            return kesimpulan; 
        }
        
        public void setBiroPersidangan (List<String> biroPersidangan){
            this.biroPersidangan  = biroPersidangan; 
        }
                
        public List<String> getBiroPersidangan(){
            return biroPersidangan; 
        }
        
        public Map getInterupsi(){
            return map;
        }
        
        public void setInterupsi (Map map){
            this.map = map;
        }
        
        public Map getSegmentasiAcara(){
            return segmentasiAcara;
        }
        
        public void setSegmentasiAcara (Map segmentasiAcara){
            this.segmentasiAcara = segmentasiAcara;
        }
        
        public List getPembicara(){
            return pembicara; 
        }
        
        public void setPembicara (List pembicara){
            this.pembicara = pembicara;
        }
        
        public List<List<String>> getIsiInterupsi(){
            return isiInterupsi; 
        }
        
        public void setIsiInterupsi (List<List<String>> isiInterupsi){
            this.isiInterupsi = isiInterupsi;
        }
        
        public List getDaftarAnggota(){
            return daftarAnggota; 
        }
        
        public void setDaftarAnggota (List daftarAnggota){
            this.daftarAnggota = daftarAnggota;
        }
    
}
