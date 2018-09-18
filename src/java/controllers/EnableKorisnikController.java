
package controllers;

import beans.Korisnik;
import database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="org_korisnici")
@SessionScoped
public class EnableKorisnikController {

    private ArrayList<Korisnik> neregistrovani = new ArrayList<>();

    public ArrayList<Korisnik> getNeregistrovani() {
        return neregistrovani;
    }

    public void setNeregistrovani(ArrayList<Korisnik> neregistrovani) {
        this.neregistrovani = neregistrovani;
    }
    
    
    
public String neregistrovaniStrana(){
        
            Connection c = DB.getInstance().getConnection();
            ResultSet rs;
            FacesContext context = FacesContext.getCurrentInstance();
            neregistrovani = new ArrayList<>();
    
        try {
            String query = "SELECT * FROM Korisnik WHERE aktivan='0'";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            Korisnik k = new Korisnik();
            k.popuni(rs);
            neregistrovani.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnableKorisnikController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
    return "organizator_korisnici";
}
  
public void aktivirajKorisnika(int id){
            Connection c = DB.getInstance().getConnection();
            ResultSet rs;
            String query;
           
        try {
            query="UPDATE korisnik SET aktivan='1' WHERE idKorisnik='" + id + "'";
            c.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(EnableKorisnikController.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
        DB.getInstance().putConnection(c);
        }
}

}
