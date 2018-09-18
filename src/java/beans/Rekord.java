
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Rekord {
  
    private int idRekord;
    private int godina;
    private String mesto;
    private String disciplina;
    private String takmicar;
    private String nacionalnost;
    private String vrednost;
    
    public void popuni(ResultSet rs) throws SQLException{
    
        idRekord=rs.getInt("idRekord");
        godina=rs.getInt("godina");
        mesto=rs.getString("mesto");
        disciplina=rs.getString("disciplina");
        takmicar=rs.getString("takmicar");
        nacionalnost=rs.getString("nacionalnost");
        vrednost=rs.getString("vrednost");
    }

    public int getIdRekord() {
        return idRekord;
    }

    public void setIdRekord(int idRekord) {
        this.idRekord = idRekord;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTakmicar() {
        return takmicar;
    }

    public void setTakmicar(String takmicar) {
        this.takmicar = takmicar;
    }

    public String getNacionalnost() {
        return nacionalnost;
    }

    public void setNacionalnost(String nacionalnost) {
        this.nacionalnost = nacionalnost;
    }

    public String getVrednost() {
        return vrednost;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }
    
    
}
