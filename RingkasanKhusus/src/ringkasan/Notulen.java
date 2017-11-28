/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan;

import java.util.List;
import java.util.Map;

/**
 *
 * @author mochamadtry
 */
public class Notulen {
    private String file;
    private String fileKlasifikasi; //Inisiasi file berisi kalimat yang akan diklasifikasikan
    private String fileHasilKlasifikasi; //Berisi hasil klasifikasi dari kumpulan kalimat yang ada di file klasifikasi
    private String path; 
    private String basicPath;
    private String text;
    private String id;
    private List sentences; 
    private List sentencesLine; 
    private List kalimat; 
    private List kalimatKlasifikasi;
    private List tanggalRapat; 
    private List tahunSidang; 
    private List masaPersidangan; 
    private List urutanRapat; 
    private List daftarHadirAnggota; 
    private List jumlahHadirAnggota; 
    private Integer jumlahInterupsi; 
    private List<String> acaraRapat; 
    private Map map;
    private Map segmentasiAcara;
    private List<String> pembicara; 
    private List<List<String>> isiInterupsi;
    private Map prediksiKlasifikasi; 
    private List<List<String>> interupsiKlasifikasi;
    private List<String> prosesPembicara; 
    private List<List<String>> prosesInterupsi; 
    private List<String> prosesAcara;
    private List<String> filterKlasifikasi;
    private List<List<Double>> valueCosine; 
    private List<List<Double>> valueCosineKom; 
    
    private List bagianAwal; //Berisi kalimat bagian cover  
    private List<String> bagianIsi; //Berisi kalimat bagian isi, setelah cover 
    

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
        
        public Integer getJumlahInterupsi() {
		return jumlahInterupsi;
	}

	public void setJumlahInterupsi(Integer jumlahInterupsi) {
		this.jumlahInterupsi = jumlahInterupsi;
	}
        
        public String getFileKlasifikasi() {
		return fileKlasifikasi;
	}

	public void setFileKlasifikasi(String fileKlasifikasi) {
		this.fileKlasifikasi = fileKlasifikasi;
	}
        
        public String getFileHasilKlasifikasi() {
		return fileHasilKlasifikasi;
	}

	public void setFileHasilKlasifikasi(String fileHasilKlasifikasi) {
		this.fileHasilKlasifikasi = fileHasilKlasifikasi;
	}
        
        public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
        
        public String getBasicPath() {
		return basicPath;
	}

	public void setBasicPath(String basicPath) {
		this.basicPath = basicPath;
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
        
        public List getKalimat(){
            return kalimat; 
        }
        
        public void setKalimat (List kalimat){
            this.kalimat = kalimat;
        }
        
        public List getKalimatKlasifikasi(){
            return kalimatKlasifikasi; 
        }
        
        public void setKalimatKlasifikasi (List kalimatKlasifikasi){
            this.kalimatKlasifikasi = kalimatKlasifikasi;
        }
        
        public List getFilterKlasifikasi(){
            return filterKlasifikasi; 
        }
        
        public void setFilterKlasifikasi (List filterKlasifikasi){
            this.filterKlasifikasi = filterKlasifikasi;
        }
        
        public List<String> getTanggalRapat(){
            return tanggalRapat; 
        }
        
        public void setTanggalRapat (List<String> tanggalRapat){
            this.tanggalRapat = tanggalRapat;
        }
        
        public List<String> getTahunSidang(){
            return tahunSidang; 
        }
        
        public void setTahunSidang (List<String> tahunSidang){
            this.tahunSidang = tahunSidang;
        }
        
        public List<String> getMasaPersidangan(){
            return masaPersidangan; 
        }
        
        public void setMasaPersidangan (List<String> masaPersidangan){
            this.masaPersidangan = masaPersidangan;
        }
        
        public List<String> getUrutanRapat(){
            return urutanRapat; 
        }
        
        public void setUrutanRapat (List<String> urutanRapat){
            this.urutanRapat = urutanRapat;
        }
        
        public List<String> getDaftarHadirAnggota(){
            return daftarHadirAnggota; 
        }
        
        public void setDaftarHadirAnggota (List<String> daftarHadirAnggota){
            this.daftarHadirAnggota = daftarHadirAnggota;
        }
        
        public List<String> getJumlahHadirAnggota(){
            return jumlahHadirAnggota; 
        }
        
        public void setJumlahHadirAnggota (List<String> jumlahHadirAnggota){
            this.jumlahHadirAnggota = jumlahHadirAnggota;
        }
        
        public List<String> getAcaraRapat(){
            return acaraRapat; 
        }
        
        public void setAcaraRapat (List<String> acaraRapat){
            this.acaraRapat = acaraRapat;
        }
        
        public Map getInterupsi(){ //acara ke n dimulai dari baris n sampai baris ke m
            return map;
        }
        
        public void setInterupsi (Map map){
            this.map = map;
        }
        
        public Map getSegmentasiAcara(){ //daftar utterance dan awal kalimat yang akan diklasifikasikan 
            return segmentasiAcara;
        }
        
        public void setSegmentasiAcara (Map segmentasiAcara){
            this.segmentasiAcara = segmentasiAcara;
        }
        
        public List getPembicara(){//daftar pembicara
            return pembicara; 
        }
        
        public void setPembicara (List pembicara){
            this.pembicara = pembicara;
        }
        
        public List<List<String>> getIsiInterupsi(){ //interupsi per pembicara
            return isiInterupsi; 
        }
        
        public void setIsiInterupsi (List<List<String>> isiInterupsi){
            this.isiInterupsi = isiInterupsi;
        }
        
        public Map getKlasifikasi(){
            return prediksiKlasifikasi; 
        }
        
        public void setKlasifikasi(Map prediksiKlasifikasi){
            this.prediksiKlasifikasi = prediksiKlasifikasi; 
        }
        
        public List<List<String>> getInterupsiKlasifikasi(){
            return interupsiKlasifikasi; 
        }
        
        public void setInterupsiKlasifikasi(List<List<String>> interupsiKlasifikasi){
            this.interupsiKlasifikasi = interupsiKlasifikasi;
        }
        
        public List getProsesPembicara(){//daftar pembicara
            return prosesPembicara; 
        }
        
        public void setProsesPembicara (List prosesPembicara){
            this.prosesPembicara = prosesPembicara;
        }
        
        public List<List<String>> getProsesInterupsi(){ //interupsi per pembicara
            return prosesInterupsi; 
        }
        
        public void setProsesInterupsi (List<List<String>> prosesInterupsi){
            this.prosesInterupsi = prosesInterupsi;
        }
        
        public List getProsesAcara(){//daftar pembicara
            return prosesAcara; 
        }
        
        public void setProsesAcara (List prosesAcara){
            this.prosesAcara = prosesAcara;
        }
        
        public List<List<Double>> getValueCosine(){
            return valueCosine; 
        }
        
        public void setValueCosine(List<List<Double>> valueCosine){
            this.valueCosine = valueCosine;
        }
        
        public List<List<Double>> getValueCosineKom(){
            return valueCosineKom; 
        }
        
        public void setValueCosineKom(List<List<Double>> valueCosineKom){
            this.valueCosineKom = valueCosineKom;
        }
        
         public List getBagianAwal(){
            return bagianAwal; 
        }
        
        public void setBagianAwal (List bagianAwal){
            this.bagianAwal = bagianAwal;
        }
        
        public List<String> getBagianIsi(){
            return bagianIsi; 
        }
        
        public void setBagianIsi (List<String> bagianIsi){
            this.bagianIsi = bagianIsi;
        }
    
}
