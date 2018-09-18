
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sport {
   
    private String naziv;
    private char kategorija;
    private int id;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public char getKategorija() {
        return kategorija;
    }

    public void setKategorija(char kategorija) {
        this.kategorija = kategorija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void popuni(ResultSet rs) throws SQLException{
    naziv=rs.getString("naziv");
    kategorija=rs.getString("kategorija").charAt(0);
    id=rs.getInt("idSport");
    }
    
}
