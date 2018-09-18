
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Zemlja {
 
    private String nacionalnost;
    private int zlato;
    private int srebro;
    private int bronza;
    
    private int ukupno;
    private int brojSportista;
    private int rang;
    
    public void popuni(ResultSet rs) throws SQLException{
    
        nacionalnost=rs.getString("nacionalnost");
        zlato=rs.getInt("zlato");
        srebro=rs.getInt("srebro");
        bronza=rs.getInt("bronza");
    }

    public String getNacionalnost() {
        return nacionalnost;
    }

    public void setNacionalnost(String nacionalnost) {
        this.nacionalnost = nacionalnost;
    }

    public int getZlato() {
        return zlato;
    }

    public void setZlato(int zlato) {
        this.zlato = zlato;
    }

    public int getSrebro() {
        return srebro;
    }

    public void setSrebro(int srebro) {
        this.srebro = srebro;
    }

    public int getBronza() {
        return bronza;
    }

    public void setBronza(int bronza) {
        this.bronza = bronza;
    }

    public int getUkupno() {
        return ukupno;
    }

    public void setUkupno(int ukupno) {
        this.ukupno = ukupno;
    }

    public int getBrojSportista() {
        return brojSportista;
    }

    public void setBrojSportista(int brojSportista) {
        this.brojSportista = brojSportista;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }
    
    
}
