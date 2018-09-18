
package controllers;

import controllers.LoginController;
import beans.Disciplina;
import beans.Korisnik;
import beans.Sport;
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

@ManagedBean(name="vodja")
@SessionScoped
public class NoviSportistaController {
    
    private int sport;
    private ArrayList<Disciplina> dis;
    private ArrayList<?> discipline = new ArrayList<>();
    private Sportista sportista = new Sportista();
    
    private ArrayList<Sportista> mojiSportisti = new ArrayList<>();
    
    private String poruka;
    private boolean prikazi=false;


    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
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

    public Sportista getSportista() {
        return sportista;
    }

    public void setSportista(Sportista sportista) {
        this.sportista = sportista;
    }

    public ArrayList<?> getDiscipline() {
        return discipline;
    }

    public void setDiscipline(ArrayList<String> discipline) {
        this.discipline = discipline;
    }

    public ArrayList<Disciplina> getDis() {
        return dis;
    }

    public void setDis(ArrayList<Disciplina> dis) {
        this.dis = dis;
    }

    public ArrayList<Sportista> getMojiSportisti() {
        return mojiSportisti;
    }

    public void setMojiSportisti(ArrayList<Sportista> mojiSportisti) {
        this.mojiSportisti = mojiSportisti;
    }
    
    
    public void disciplineListener() throws SQLException{
        
        dis=new ArrayList<>();
        
        String query="SELECT d.* from disciplina d, takmicenje t "
                + "WHERE d.idDisciplina=t.idDisciplina and d.idSport=t.idSport and d.idSport='" + sport + "' and t.idTakmicenje not in "
                + "(SELECT m.idTakmicenje from mec m) "
                + "UNION "
                + "SELECT d.* from disciplina d where d.idDisciplina not in "
                + "(SELECT t.idDisciplina from takmicenje t where t.idSport='" + sport + "') "
                + "and d.idSport='" + sport + "' order by naziv";
        

        //String query = "SELECT * FROM disciplina WHERE idSport = '" + sport + "' AND idDisciplina NOT IN "
          //      + "(SELECT idDisciplina FROM takmicenje WHERE idTakmicenje IN (SELECT idTakmicenje FROM mec)) ORDER BY naziv";
        Connection c = DB.getInstance().getConnection();
        ResultSet rs = c.createStatement().executeQuery(query);
        while(rs.next()){
        Disciplina temp = new Disciplina();
        temp.popuni(rs);
        dis.add(temp);}
        DB.getInstance().putConnection(c);
    
    }
    
    public String dodajSportistu(){
        prikazi=false;
        String next="";
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        
        try {
            query = "SELECT MAX(idSportista) FROM sportista";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            int id = rs.getInt(1) + 1;
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            Korisnik temp = (Korisnik)session.getAttribute("korisnik");
            query = "INSERT INTO `sportista` (`idSportista`, `idSport`, `ime`, `prezime`, `nacionalnost`) VALUES('" + id + "','" + sport + "','" + sportista.getIme() + "','" + sportista.getPrezime() + "','" + temp.getNacionalnost() + "')";
            c.createStatement().executeUpdate(query);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Uspesno",  "Timu se pridruzio " + sportista.getIme() + " " + sportista.getPrezime()) );
            
            for(int i=0;i<discipline.size();i++){
                query="INSERT INTO `sportistadisciplina` (`idSportista`, `idDisciplina`) VALUES('" + id + "','" + discipline.get(i) + "')";
                c.createStatement().executeUpdate(query);
            }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            DB.getInstance().putConnection(c);
            }
        return next;
    }
   
   
}
