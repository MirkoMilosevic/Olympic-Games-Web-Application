
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Ekipa {
 
    private int idEkipa;
    private int idSport;
    private String nacionalnost;
    private int brojClanova;

    public int getIdEkipa() {
        return idEkipa;
    }

    public void setIdEkipa(int idEkipa) {
        this.idEkipa = idEkipa;
    }

    public int getIdSport() {
        return idSport;
    }

    public void setIdSport(int idSport) {
        this.idSport = idSport;
    }

    public String getNacionalnost() {
        return nacionalnost;
    }

    public void setNacionalnost(String nacionalnost) {
        this.nacionalnost = nacionalnost;
    }

    public int getBrojClanova() {
        return brojClanova;
    }

    public void setBrojClanova(int brojClanova) {
        this.brojClanova = brojClanova;
    }
    
    public void popuni(ResultSet rs) throws SQLException{
    idEkipa=rs.getInt("idEkipa");
    idSport=rs.getInt("idSport");
    nacionalnost=rs.getString("nacionalnost");
    brojClanova=rs.getInt("brojClanova");
    }
    
}
