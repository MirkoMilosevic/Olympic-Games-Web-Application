
package controllers;

import beans.Korisnik;
import database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="register")
@SessionScoped
public class RegisterController {
    
    private String username;
    private String password;
    private String check_password;
    private String ime;
    private String prezime;
    private String nacionalnost;
    private String email;
    private int tip;
    
    private String poruka="";
    private boolean prikazi=false;

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

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public boolean isPrikazi() {
        return prikazi;
    }

    public void setPrikazi(boolean prikazi) {
        this.prikazi = prikazi;
    }

    public String getCheck_password() {
        return check_password;
    }

    public void setCheck_password(String check_password) {
        this.check_password = check_password;
    }
    
    public boolean provera(){
     
       FacesContext context = FacesContext.getCurrentInstance();
        
       if(password.length()<8 && password!=null){
       context.addMessage(null, new FacesMessage("Lozinka je kraca od 8 karaktera!"));
       return false;
       }
       
       if(password.length()>12){
       context.addMessage(null, new FacesMessage("Lozinka je duza od 12 karaktera!"));
       return false;
       }
        
    if(password!=null && password.equals(check_password)){
        int mala=0, velika=0, broj=0, spec=0;
        Character c = (Character) password.charAt(0);
        if(!Character.isLetter(c)){context.addMessage(null, new FacesMessage("Lozinka mora poceti slovom!")); return false;}
        for(int i=0; i<password.length(); i++){
        if(i<password.length()-3 && password.charAt(i)==password.charAt(i+1) && password.charAt(i)==password.charAt(i+2) && password.charAt(i)==password.charAt(i+3)){
            context.addMessage(null, new FacesMessage("Lozinka ne sme imati 4 ista uzastopna znaka!"));
            return false;
        }
        c = (Character) password.charAt(i);
        if(Character.isLetter(c)){
            if(Character.isUpperCase(c)){velika++;}
            else{mala++;}
        }else if(Character.isDigit(c)){broj++;}
        else{spec++;}
        }
        if(mala>2 && velika>0 && broj>1 && spec>1) return true;
        else{
        context.addMessage(null, new FacesMessage("Nisu ispunjena pravila za sigurnu lozinku!"));
        return false;
        }
        }
       context.addMessage(null, new FacesMessage("Potvrdjena lozinka se razlikuje od unete!"));
       return false;
    }
    
    public String register(){
        String next="";
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
            query="SELECT * FROM korisnik WHERE username='" + username + "'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
            context.addMessage(null, new FacesMessage("Korisnicko ime vec postoji, izaberite drugo!"));
            return "";
            }
            
            if(provera()){
            query = "SELECT MAX(idKorisnik) FROM korisnik";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            int id = rs.getInt(1) + 1;
            query = "INSERT INTO `korisnik` (`idKorisnik`, `username`, `password`, `ime`, `prezime`, `nacionalnost`, `email`, `tip`, `aktivan`) VALUES('" + id + "','" + username + "','" + password + "','" + ime + "','" + prezime + "','" + nacionalnost + "','" + email + "','" + tip + "','0')";
            c.createStatement().executeUpdate(query);
            context.addMessage(null, new FacesMessage("Uspesno ste se registrovali!"));
            }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            DB.getInstance().putConnection(c);
            }
        return next;
    }
   
   public String promeni(int id){
       
        String next="";
        String query;
        Connection c = DB.getInstance().getConnection();
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
            if(provera()){
            query = "UPDATE `korisnik` SET `password` = '" + password + "' WHERE `idKorisnik` = '" + id + "'";
            c.createStatement().executeUpdate(query);
            context.addMessage(null, new FacesMessage("Uspesno promenjena lozinka!"));
            }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            DB.getInstance().putConnection(c);
            }
        return next;
   }
 
    public String nazad(int tip){

        String next="";
       
        if(tip==1){next="organizator_home";}
        if(tip==2){next="delegat_home";}
        if(tip==3){next="vodja_home";}
        
        return next;
    }
   
}
