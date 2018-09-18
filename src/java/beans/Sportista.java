
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sportista {
  
    private int id;
    private String ime;
    private String prezime;
    //private String sport;
    private String nacionalnost;
    private char pol;
    private int idSport;
    
    
    
    public void popuni(ResultSet rs) throws SQLException{
    ime=rs.getString("ime");
    prezime=rs.getString("prezime");
    nacionalnost=rs.getString("nacionalnost");
    //pol=rs.getString(pol).charAt(0);
    idSport=rs.getInt("idSport");
    id=rs.getInt("idSportista");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getNacionalnost() {
        return nacionalnost;
    }

    public void setNacionalnost(String nacionalnost) {
        this.nacionalnost = nacionalnost;
    }

    public int getIdSport() {
        return idSport;
    }

    public void setIdSport(int idSport) {
        this.idSport = idSport;
    }

    public char getPol() {
        return pol;
    }

    public void setPol(char pol) {
        this.pol = pol;
    }
    
    
    
}
