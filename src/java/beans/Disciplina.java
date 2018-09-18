package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Disciplina {
   
    private String naziv;
    private String vrsta;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }
    
    public void popuni(ResultSet rs) throws SQLException{
    naziv=rs.getString("naziv");
    vrsta=rs.getString("vrsta");
    id=rs.getInt("idDisciplina");
    }
    
}
