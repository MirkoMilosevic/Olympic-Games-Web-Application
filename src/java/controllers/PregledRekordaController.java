
package controllers;

import beans.Rekord;
import database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="org_rekordi")
@SessionScoped
public class PregledRekordaController {
    
    private ArrayList<Rekord> rekordi = new ArrayList<>();
    
    private int idRekord;
    
    private Rekord izabraniRekord;

    public Rekord getIzabraniRekord() {
        return izabraniRekord;
    }

    public void setIzabraniRekord(Rekord izabraniRekord) {
        this.izabraniRekord = izabraniRekord;
    }

    public int getIdRekord() {
        return idRekord;
    }

    public void setIdRekord(int idRekord) {
        this.idRekord = idRekord;
    }

    public ArrayList<Rekord> getRekordi() {
        return rekordi;
    }

    public void setRekordi(ArrayList<Rekord> rekordi) {
        this.rekordi = rekordi;
    }
    
    public String pregledRekorda(){
    
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        
        try {
            query="SELECT * FROM rekord";
            rs = c.createStatement().executeQuery(query);
            rekordi=new ArrayList<>();
            while(rs.next()){
            Rekord temp = new Rekord();
            temp.popuni(rs);
            rekordi.add(temp);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PregledRekordaController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
        return "organizator_rekordi";
    }
    
    public void dohvatiRekord(){
    
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        
        try {
            query="SELECT * FROM rekord WHERE idRekord='" + idRekord + "'";
            rs=c.createStatement().executeQuery(query);
            rs.next();
            izabraniRekord = new Rekord();
            izabraniRekord.popuni(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(PregledRekordaController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
    }
    
}
