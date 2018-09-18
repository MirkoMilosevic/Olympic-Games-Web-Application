
package beans;

import database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Takmicenje {
 
    private int idTakmicenje;
    private int idSport;
    private int idDisciplina;
    private String datumOd;
    private String datumDo;
    private String lokacija;
    private int idDelegat;
    private int brojUcesnika;
    private int tip;

     /*private String nazivS;
     private String nazivD;
     private String nazivK;

    public String getNazivS() {
        return nazivS;
    }

    public void setNazivS(String nazivS) {
        this.nazivS = nazivS;
    }

    public String getNazivD() {
        return nazivD;
    }

    public void setNazivD(String nazivD) {
        this.nazivD = nazivD;
    }

    public String getNazivK() {
        return nazivK;
    }

    public void setNazivK(String nazivK) {
        this.nazivK = nazivK;
    }
     */
    
    public int getIdTakmicenje() {
        return idTakmicenje;
    }

    public void setIdTakmicenje(int idTakmicenje) {
        this.idTakmicenje = idTakmicenje;
    }

    public int getIdSport() {
        return idSport;
    }

    public void setIdSport(int idSport) {
        this.idSport = idSport;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getIdDelegat() {
        return idDelegat;
    }

    public void setIdDelegat(int idDelegat) {
        this.idDelegat = idDelegat;
    }

    public int getBrojUcesnika() {
        return brojUcesnika;
    }

    public void setBrojUcesnika(int brojUcesnika) {
        this.brojUcesnika = brojUcesnika;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }
    
    public void popuni(ResultSet rs) throws SQLException{
    
        Connection c =DB.getInstance().getConnection();
        
        idTakmicenje=rs.getInt("idTakmicenje");
        idSport=rs.getInt("idSport");
        idDisciplina=rs.getInt("idDisciplina");
        datumOd=rs.getString("datumOd");
        datumDo=rs.getString("datumDo");
        lokacija=rs.getString("lokacija");
        idDelegat=rs.getInt("idDelegat");
        brojUcesnika=rs.getInt("brojUcesnika");
        tip=rs.getInt("tip");
    
    /*String query="SELECT naziv FROM sport WHERE idSport='" + idSport + "'";
    ResultSet rezultat;
    rezultat = c.createStatement().executeQuery(query);
    rs.next();
    nazivS = rs.getString(1);
    String query2="SELECT naziv FROM disciplina WHERE idDisciplina='" + idDisciplina + "'";
    */    
}
    
}
