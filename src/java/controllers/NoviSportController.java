
package controllers;

import beans.Sport;
import database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="org_sport")
@SessionScoped
public class NoviSportController {
    
    private String naziv;
    private char kategorija;
    private ArrayList<Sport> sportovi = new ArrayList<>();

    public ArrayList<Sport> getSportovi() {
        return sportovi;
    }

    public void setSportovi(ArrayList<Sport> sportovi) {
        this.sportovi = sportovi;
    }

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
    
    public String dodajSport(){
        String next="";
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        
        try {
            query="SELECT * FROM sport WHERE naziv='" + naziv + "' AND kategorija='" + kategorija + "'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
            context.addMessage(null, new FacesMessage("Neuspesno",  "Sport " + naziv + " " + kategorija + " vec postoji!") );
            return next;
            }
            
            query="SELECT MAX(idSport) FROM sport";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            int id = rs.getInt(1)+1;
  
            query="INSERT INTO `sport`(`idSport`,`naziv`,`kategorija`) VALUES('" + id + "','" + naziv + "','" + kategorija + "')";
            c.createStatement().executeUpdate(query);
            
            sportovi=new ArrayList<>();
            query = "SELECT * FROM sport ORDER BY naziv";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            Sport temp = new Sport();
            temp.popuni(rs);
            sportovi.add(temp);
            }
            
            
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
            session.setAttribute("sportovi", sportovi);
            context.addMessage(null, new FacesMessage("Uspesno",  "Sport " + naziv + " " + kategorija + " je dodat!") );
        
        } catch (SQLException ex) {
            Logger.getLogger(NoviSportController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
        return next;
    }
    
}
