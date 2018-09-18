
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mec {
  
    private int idMec;
    private int idTakmicenje;
    private String faza;
    private int ucesnik1;
    private int ucesnik2;
    private String datum;
    private String vreme;
    private String lokacija;
    private String rezultat;
    
    private int rezultat1;
    private int rezultat2;
    private int rezultat3;
    
    private int pobednik;
    private int zavrseno;
    private int format;
    private int zakazano;
    
    private String nazivUcesnika1;
    private String nazivUcesnika2;
    
    private boolean disable1=false;
    private boolean disable2=false;
    
    
    public void popuni(ResultSet rs) throws SQLException{
    idMec=rs.getInt("idMec");
    idTakmicenje=rs.getInt("idTakmicenje");
    faza=rs.getString("faza");
    ucesnik1=rs.getInt("ucesnik1");
    ucesnik2=rs.getInt("ucesnik2");
    datum=rs.getString("datum");
    vreme=rs.getString("vreme");
    lokacija=rs.getString("lokacija");
    rezultat=rs.getString("rezultat");
    pobednik=rs.getInt("pobednik");
    zavrseno=rs.getInt("zavrseno");
    format=rs.getInt("format");
    zakazano = rs.getInt("zakazano");
    }

    public int getIdMec() {
        return idMec;
    }

    public void setIdMec(int idMec) {
        this.idMec = idMec;
    }

    public int getIdTakmicenje() {
        return idTakmicenje;
    }

    public void setIdTakmicenje(int idTakmicenje) {
        this.idTakmicenje = idTakmicenje;
    }

    public String getFaza() {
        return faza;
    }

    public void setFaza(String faza) {
        this.faza = faza;
    }

    public int getUcesnik1() {
        return ucesnik1;
    }

    public void setUcesnik1(int ucesnik1) {
        this.ucesnik1 = ucesnik1;
    }

    public int getUcesnik2() {
        return ucesnik2;
    }

    public void setUcesnik2(int ucesnik2) {
        this.ucesnik2 = ucesnik2;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public int getPobednik() {
        return pobednik;
    }

    public void setPobednik(int pobednik) {
        this.pobednik = pobednik;
    }

    public int getZavrseno() {
        return zavrseno;
    }

    public void setZavrseno(int zavrseno) {
        this.zavrseno = zavrseno;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    
    
    public String getNazivUcesnika1() {
        return nazivUcesnika1;
    }

    public void setNazivUcesnika1(String nazivUcesnika1) {
        this.nazivUcesnika1 = nazivUcesnika1;
    }

    public String getNazivUcesnika2() {
        return nazivUcesnika2;
    }

    public void setNazivUcesnika2(String nazivUcesnika2) {
        this.nazivUcesnika2 = nazivUcesnika2;
    }

    public int getRezultat1() {
        return rezultat1;
    }

    public void setRezultat1(int rezultat1) {
        this.rezultat1 = rezultat1;
    }

    public int getRezultat2() {
        return rezultat2;
    }

    public void setRezultat2(int rezultat2) {
        this.rezultat2 = rezultat2;
    }

    public int getRezultat3() {
        return rezultat3;
    }

    public void setRezultat3(int rezultat3) {
        this.rezultat3 = rezultat3;
    }

    public boolean isDisable1() {
        return disable1;
    }

    public void setDisable1(boolean disable1) {
        this.disable1 = disable1;
    }

    public boolean isDisable2() {
        return disable2;
    }

    public void setDisable2(boolean disable2) {
        this.disable2 = disable2;
    }

    
    
  
    
}
