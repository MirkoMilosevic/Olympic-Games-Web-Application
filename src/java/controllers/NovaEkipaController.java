
package controllers;

import beans.Disciplina;
import beans.Korisnik;
import beans.Sportista;
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

@ManagedBean(name="ekipa")
@SessionScoped
public class NovaEkipaController {
    
    private String poruka;
    private boolean prikazi=false;
    
    private int sport;
    private ArrayList<Sportista> pom;
    private ArrayList<?> sportisti = new ArrayList<>();

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public ArrayList<Sportista> getPom() {
        return pom;
    }

    public void setPom(ArrayList<Sportista> pom) {
        this.pom = pom;
    }

    public ArrayList<?> getSportisti() {
        return sportisti;
    }

    public void setSportisti(ArrayList<?> sportisti) {
        this.sportisti = sportisti;
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
   
    
    
    
    public void igraciListener() throws SQLException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Korisnik k = (Korisnik)session.getAttribute("korisnik");
        pom=new ArrayList<>();
        //String query = "SELECT * FROM sportista";
                    String query="SELECT * FROM `sportista` "
                              + "WHERE "
                            + "(idSport NOT IN (SELECT idSport FROM disciplina WHERE vrsta='individualni') "
                          + "OR idSport IN (SELECT idSport from disciplina WHERE vrsta='ekipni')) "
                    + "AND idSport='" + sport + "'"
                    + "AND nacionalnost='" + k.getNacionalnost() + "'"
                    + "AND idSportista NOT IN (SELECT DISTINCT idSportista FROM sportistaekipa)";
        Connection c = DB.getInstance().getConnection();
        ResultSet rs = c.createStatement().executeQuery(query);
        while(rs.next())
        {
        Sportista temp = new Sportista();
        temp.popuni(rs);
        pom.add(temp);
        }
        DB.getInstance().putConnection(c);
    }
    
    public String napraviTim(){
        
        String next="";
        poruka="";
        prikazi=false;
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        try {
            query="SELECT idTakmicenje FROM takmicenje WHERE idSport='" + sport + "'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
            int idT = rs.getInt(1);
            query="SELECT * FROM mec WHERE idTakmicenje='" + idT + "'";
            rs=c.createStatement().executeQuery(query);
            if(rs.next()){
            facesContext.addMessage(null, new FacesMessage("Neuspesno", "Takmicenje iz ovog sporta je vec pocelo"));
            return "";
            }
            }
            
            query="SELECT naziv FROM sport WHERE idSport='" + sport + "'";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            String naziv = rs.getString(1);
            int n=sportisti.size();
            
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            Korisnik k = (Korisnik)session.getAttribute("korisnik");
            
            query="SELECT * FROM ekipa WHERE nacionalnost='" + k.getNacionalnost() + "' AND idSport='" + sport + "'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
                facesContext.addMessage(null, new FacesMessage("Neuspesno", "Ekipa iz ovog sporta je vec dodata!"));
                return next;
            }
            
            if(naziv.equals("Kosarka") && (n<5 || n>12))
            {
                facesContext.addMessage(null, new FacesMessage("Neuspesno", "Nedozvoljen broj igraca za ovaj sport"));
                return next;
            }
            
            if(naziv.equals("Odbojka") && (n<6 || n>12))
            {
                facesContext.addMessage(null, new FacesMessage("Neuspesno", "Nedozvoljen broj igraca za ovaj sport"));
                return next;
            }
            
            if(naziv.equals("Vaterpolo") && (n<6 || n>13))
            {
                facesContext.addMessage(null, new FacesMessage("Neuspesno", "Nedozvoljen broj igraca za ovaj sport"));
                return next;
            }
            
            if((naziv.equals("Tenis") || naziv.equals("Stoni tenis")) && n!=2)
            {
                facesContext.addMessage(null, new FacesMessage("Neuspesno", "Nedozvoljen broj igraca za ovaj sport"));
                return next;
            }
            
            query="SELECT MAX(idEkipa) FROM ekipa";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            int id = rs.getInt(1) + 1;
            
            query="INSERT INTO `ekipa` VALUES ('"
                    +id + "','" + sport + "','" + k.getNacionalnost() + "','" + n + "')";
            c.createStatement().executeUpdate(query);
            
            for(int i=0; i<n; i++){
                query="INSERT INTO `sportistaekipa` (`idSportista`, `idEkipa`) VALUES('" + sportisti.get(i) + "','" + id + "')";
                c.createStatement().executeUpdate(query);
                
            }
            
            facesContext.addMessage(null, new FacesMessage("Uspesno", "Ekipa je dodata"));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NovaEkipaController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
    return next;
    }
    
}

