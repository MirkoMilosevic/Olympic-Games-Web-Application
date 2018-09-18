
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Korisnik {
    
    private String username;
    private String password;
    private String ime;
    private String prezime;
    private String nacionalnost;
    private String email;
    private int tip;
    private int aktivan;
    private int id;
    
    
    
    public void popuni(ResultSet rs) throws SQLException{
    username=rs.getString("username");
    password=rs.getString("password");
    ime=rs.getString("ime");
    prezime=rs.getString("prezime");
    nacionalnost=rs.getString("nacionalnost");
    email=rs.getString("email");
    tip=rs.getInt("tip");
    aktivan=rs.getInt("aktivan");
    id=rs.getInt("idKorisnik");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getAktivan() {
        return aktivan;
    }

    public void setAktivan(int aktivan) {
        this.aktivan = aktivan;
    }
    
    
    
}
