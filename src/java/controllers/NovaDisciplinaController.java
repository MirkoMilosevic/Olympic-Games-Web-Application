
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

@ManagedBean(name="org_disciplina")
@SessionScoped
public class NovaDisciplinaController {
    
    private int sport;
    private String naziv;
    private String vrsta;

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
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
    
    public String dodajDisciplinu(){
        String next="";
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            query="SELECT * FROM disciplina WHERE naziv='" + naziv + "' AND idSport='" + sport + "'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
            context.addMessage(null, new FacesMessage("Neuspesno",  "Disciplina " + naziv + " vec postoji!") );
            return next;
            }
            
            query="SELECT MAX(idDisciplina) FROM disciplina";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            int id = rs.getInt(1)+1;
            
            query="INSERT INTO `disciplina`(`idDisciplina`,`idSport`,`naziv`,`vrsta`) VALUES('" + id + "','" + sport + "','" + naziv + "','" + vrsta + "')";
            c.createStatement().executeUpdate(query);
            
            /*sportovi=new ArrayList<>();
            query = "SELECT * FROM sport ORDER BY naziv";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            Sport temp = new Sport();
            temp.popuni(rs);
            sportovi.add(temp);
            }
            */
            
            context.addMessage(null, new FacesMessage("Uspesno",  "Disciplina " + naziv + " je dodat!") );
        
        } catch (SQLException ex) {
            Logger.getLogger(NoviSportController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
        return next;
    }
    
}
