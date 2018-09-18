package controllers;

import beans.Disciplina;
import beans.Korisnik;
import beans.Sportista;
import beans.Takmicenje;
import beans.TakmicenjeView;
import database.DB;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
//import java.text.*;
//import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;

import controllers.LoginController;

@ManagedBean(name="org_takmicenje")
@SessionScoped
public class NovoTakmicenjeController {
   
    private int idSport;
    private int idDisciplina;
    private String datumOd;
    private String datumDo;
    private String lokacija;
    private int idDelegat;
    
    private ArrayList<Sportista> sportisti = new ArrayList<>();
    private ArrayList<Disciplina> discipline;
    private ArrayList<Takmicenje> takmicenja;
    private ArrayList<TakmicenjeView> takmicenjaView;
    
    private ArrayList<?> ucesnici = new ArrayList<>();
    private int izabranoTakmicenje;

    public int getIzabranoTakmicenje() {
        return izabranoTakmicenje;
    }

    public void setIzabranoTakmicenje(int izabranoTakmicenje) {
        this.izabranoTakmicenje = izabranoTakmicenje;
    }

    public ArrayList<?> getUcesnici() {
        return ucesnici;
    }

    public void setUcesnici(ArrayList<?> ucesnici) {
        this.ucesnici = ucesnici;
    }

    public ArrayList<TakmicenjeView> getTakmicenjaView() {
        return takmicenjaView;
    }

    public void setTakmicenjaView(ArrayList<TakmicenjeView> takmicenjaView) {
        this.takmicenjaView = takmicenjaView;
    }

    public ArrayList<Takmicenje> getTakmicenja() {
        return takmicenja;
    }

    public void setTakmicenja(ArrayList<Takmicenje> takmicenja) {
        this.takmicenja = takmicenja;
    }

    public ArrayList<Disciplina> getDiscipline() {
        return discipline;
    }

    public void setDiscipline(ArrayList<Disciplina> discipline) {
        this.discipline = discipline;
    }

    public int getIdSport() {
        return idSport;
    }

    public void setIdSport(int idSport) {
        this.idSport = idSport;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public String getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(String datumDo) {
        this.datumDo = datumDo;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getIdDelegat() {
        return idDelegat;
    }

    public void setIdDelegat(int idDelegat) {
        this.idDelegat = idDelegat;
    }

    public ArrayList<Sportista> getSportisti() {
        return sportisti;
    }

    public void setSportisti(ArrayList<Sportista> sportisti) {
        this.sportisti = sportisti;
    }
    
    public void disciplineListener() throws SQLException{
        
        
        discipline=new ArrayList<>();
        String query = "SELECT * FROM disciplina WHERE idSport = '" + idSport + "' ORDER BY naziv";
        Connection c = DB.getInstance().getConnection();
        ResultSet rs = c.createStatement().executeQuery(query);
        while(rs.next()){
        Disciplina temp = new Disciplina();
        temp.popuni(rs);
        discipline.add(temp);}
        DB.getInstance().putConnection(c);
    }
    
    public String dodajTakmicenje(){
        String next="";
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext context = FacesContext.getCurrentInstance();
        int tipTakmicenja;
        takmicenja = new ArrayList<>();
        takmicenjaView = new ArrayList<>();
        try {
            if(idDisciplina!=0){
                query="SELECT * FROM takmicenje WHERE idSport='" + idSport + "' AND idDisciplina='" + idDisciplina + "'";
            }
            else{
                query="SELECT * FROM takmicenje WHERE idSport='" + idSport + "'";
            }
            
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
            context.addMessage(null, new FacesMessage("Neuspesno",  "Takmicenje za vec postoji!") );
            return next;
            }
            
            query="SELECT * FROM korisnik WHERE idKorisnik='" + idDelegat + "' AND aktivan='0'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
            context.addMessage(null, new FacesMessage("Izabrani delegat nije aktiviran!") );
            return next;
            }
            
            query="SELECT COUNT(*) AS suma FROM takmicenje Where idDelegat='" + idDelegat + "'";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            if(count>2){
            context.addMessage(null, new FacesMessage("Izabrani delegat je vec zaduzen za 3 takmicenja!") );
            return next;
            }
            
            query="SELECT MAX(idTakmicenje) FROM takmicenje";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            int id = rs.getInt(1)+1;
            
            if(idDisciplina!=0){
                
            query="SELECT vrsta FROM disciplina WHERE idDisciplina='" + idDisciplina + "'";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            String vrsta = rs.getString(1);
            if(vrsta.equals("individualni")){
            query="INSERT INTO `takmicenje`(`idTakmicenje`,`idDisciplina`,`idSport`,`datumOd`,`datumDo`,`lokacija`,`idDelegat`,`tip`)"
                    + " VALUES('" + id + "','" + idDisciplina + "','" + idSport + "','" + datumOd + "','" + datumDo + "','" 
                    + lokacija + "','" + idDelegat + "','" + 1 + "')";
            tipTakmicenja=1;
            }
            else{
            query="INSERT INTO `takmicenje`(`idTakmicenje`,`idDisciplina`,`idSport`,`datumOd`,`datumDo`,`lokacija`,`idDelegat`,`tip`)"
                    + " VALUES('" + id + "','" + idDisciplina + "','" + idSport + "','" + datumOd + "','" + datumDo + "','" 
                    + lokacija + "','" + idDelegat + "','" + 2 + "')";
            tipTakmicenja=2;
            }    
            c.createStatement().executeUpdate(query);
            
            }
            else{
            query="INSERT INTO `takmicenje`(`idTakmicenje`,`idSport`,`datumOd`,`datumDo`,`lokacija`,`idDelegat`,`tip`)"
                    + " VALUES('" + id + "','" + idSport + "','" + datumOd + "','" + datumDo + "','" 
                    + lokacija + "','" + idDelegat + "','" + 2 + "')";
            c.createStatement().executeUpdate(query);
            tipTakmicenja=2;
            }
            
            context.addMessage(null, new FacesMessage("Uspesno",  "Takmicenje je dodato!") );
        
            query="SELECT * FROM takmicenje WHERE idTakmicenje NOT IN (SELECT idTakmicenje FROM ucesnik)";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
                
                ResultSet rs2;
                
            Takmicenje temp = new Takmicenje();
            temp.popuni(rs);
            takmicenja.add(temp);
            TakmicenjeView novo = new TakmicenjeView();
            int sport = temp.getIdSport();
            int disciplina = temp.getIdDisciplina();
            int takmicenje = temp.getIdTakmicenje();
            query="SELECT naziv,kategorija FROM sport WHERE idSport='" + sport + "'";
            rs2 = c.createStatement().executeQuery(query);
            rs2.next();
            String nazivSport = rs2.getString(1);
            char kategorijaSport = rs2.getString(2).charAt(0);
            novo.setSport(nazivSport);
            novo.setKategorija(kategorijaSport);
            
            if(disciplina!=0){
            query="SELECT naziv FROM disciplina WHERE idDisciplina='" + disciplina + "'";
            rs2 = c.createStatement().executeQuery(query);
            rs2.next();
            String nazivDisciplina = rs2.getString(1);
            novo.setDisciplina(nazivDisciplina);
            }
            novo.setIdTakmicenje(takmicenje);
            takmicenjaView.add(novo);
            }
            
            HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
            session.setAttribute("takmicenja", takmicenja);
            session.setAttribute("takmicenjaView", takmicenjaView);
            context.addMessage(null, new FacesMessage("Uspesno",  "Lista takmicenja dodata!") );
        } catch (SQLException ex) {
            Logger.getLogger(NoviSportController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
        return next;
 
    }
    
    public String novaStrana() throws SQLException{
        String next="organizator_ucesnici";
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext context = FacesContext.getCurrentInstance();
        int tipTakmicenja;
        takmicenja = new ArrayList<>();
        takmicenjaView = new ArrayList<>();
        try {
            query="SELECT * FROM takmicenje WHERE idTakmicenje NOT IN (SELECT idTakmicenje FROM ucesnik)";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
                
                ResultSet rs2;
                
            Takmicenje temp = new Takmicenje();
            temp.popuni(rs);
            takmicenja.add(temp);
            TakmicenjeView novo = new TakmicenjeView();
            int sport = temp.getIdSport();
            int disciplina = temp.getIdDisciplina();
            int takmicenje = temp.getIdTakmicenje();
            query="SELECT naziv,kategorija FROM sport WHERE idSport='" + sport + "'";
            rs2 = c.createStatement().executeQuery(query);
            rs2.next();
            String nazivSport = rs2.getString(1);
            char kategorijaSport = rs2.getString(2).charAt(0);
            novo.setSport(nazivSport);
            novo.setKategorija(kategorijaSport);
            
            if(disciplina!=0){
            query="SELECT naziv FROM disciplina WHERE idDisciplina='" + disciplina + "'";
            rs2 = c.createStatement().executeQuery(query);
            rs2.next();
            String nazivDisciplina = rs2.getString(1);
            novo.setDisciplina(nazivDisciplina);
            }
            novo.setIdTakmicenje(takmicenje);
            takmicenjaView.add(novo);
            }
            
            HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
            session.setAttribute("takmicenja", takmicenja);
            session.setAttribute("takmicenjaView", takmicenjaView);
            } catch (SQLException ex) {
            Logger.getLogger(NoviSportController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
        return next;
    }
    
}