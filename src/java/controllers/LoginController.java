
package controllers;

import beans.Disciplina;
import beans.Korisnik;
import beans.Sport;
import beans.Takmicenje;
import beans.TakmicenjeView;
import database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="login")
@SessionScoped
public class LoginController {
 
    private String password;
    private String username;
    private Korisnik korisnik;
    private String poruka;
    private boolean prikazi=false;
    
    public ArrayList<Sport> sportovi=new ArrayList<>();
    public ArrayList<Disciplina> discipline=new ArrayList<>();
    public ArrayList<Korisnik> delegati = new ArrayList<>();
    public ArrayList<Takmicenje> takmicenja = new ArrayList<>();
    public ArrayList<TakmicenjeView> takmicenjaView = new ArrayList<>();
    
    
    //Za delegata
    public ArrayList<Takmicenje> mojaTakmicenja = new ArrayList<>();
    public ArrayList<TakmicenjeView> mojaTakmicenjaView = new ArrayList<>();

    public ArrayList<TakmicenjeView> getMojaTakmicenjaView() {
        return mojaTakmicenjaView;
    }

    public void setMojaTakmicenjaView(ArrayList<TakmicenjeView> mojaTakmicenjaView) {
        this.mojaTakmicenjaView = mojaTakmicenjaView;
    }

    public ArrayList<Takmicenje> getMojaTakmicenja() {
        return mojaTakmicenja;
    }

    public void setMojaTakmicenja(ArrayList<Takmicenje> mojaTakmicenja) {
        this.mojaTakmicenja = mojaTakmicenja;
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

    public ArrayList<Korisnik> getDelegati() {
        return delegati;
    }

    public void setDelegati(ArrayList<Korisnik> delegati) {
        this.delegati = delegati;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
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

    public ArrayList<Sport> getSportovi() {
        return sportovi;
    }

    public void setSportovi(ArrayList<Sport> sportovi) {
        this.sportovi = sportovi;
    }

    public ArrayList<Disciplina> getDiscipline() {
        return discipline;
    }

    public void setDiscipline(ArrayList<Disciplina> discipline) {
        this.discipline = discipline;
    }
    
    
    
    public String login(){
    
        prikazi=false;
        String next="";
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        
        try {
            query = "SELECT * FROM korisnik WHERE username = '" + username + "'";
            rs = c.createStatement().executeQuery(query);
            
            if(rs.next()){
            korisnik = new Korisnik();
            korisnik.popuni(rs);
            }
            else{
            context.addMessage(null, new FacesMessage("Korisnikcko ime ne postoji!"));
            next = "";
            }
            
            if(korisnik!=null){
                if(password.equals(korisnik.getPassword())){
                    if(korisnik.getAktivan()==0){
                    context.addMessage(null, new FacesMessage("Nalog Vam jos uvek nije aktiviran!"));
                    next = "";
                    }
                    else{
                    if(korisnik.getTip()==1) {
                        next = "organizator_home";
                        
                        sportovi = new ArrayList<>();
                        query="SELECT * FROM sport ORDER BY naziv";
                        rs = c.createStatement().executeQuery(query);
                        while(rs.next()){
                        Sport temp = new Sport();
                        temp.popuni(rs);
                        sportovi.add(temp);
                        }
                        
                        delegati = new ArrayList<>();
                        query="SELECT * FROM korisnik WHERE tip = '2' ORDER BY ime,prezime";
                        rs = c.createStatement().executeQuery(query);
                        while(rs.next()){
                        Korisnik temp = new Korisnik();
                        temp.popuni(rs);
                        delegati.add(temp);
                        }
                        
                        takmicenja = new ArrayList<>();
                        takmicenjaView = new ArrayList<>();
                        query="SELECT * FROM takmicenje";
                        rs = c.createStatement().executeQuery(query);
                        
                        while(rs.next()){    
                        ResultSet rs2;
                        Takmicenje temp = new Takmicenje();
                        temp.popuni(rs);
                        
                        
                        int sport = temp.getIdSport();
                        int disciplina = temp.getIdDisciplina();
                        int takmicenje = temp.getIdTakmicenje();
                        takmicenja.add(temp);
                        TakmicenjeView novo = new TakmicenjeView();
                        
                        query="SELECT naziv,kategorija FROM sport WHERE idSport='" + sport + "'";
                        rs2 = c.createStatement().executeQuery(query);
                        rs2.next();
                        String nazivSport = rs2.getString(1);
                        char kategorijaSport = rs2.getString(2).charAt(0);
                        novo.setSport(nazivSport);
                        novo.setKategorija(kategorijaSport);
                        
                        if(disciplina!=0){
                        query="SELECT naziv,kategorija FROM disciplina WHERE idDisciplina='" + disciplina + "'";
                        rs2 = c.createStatement().executeQuery(query);
                        rs2.next();
                        String nazivDisciplina = rs2.getString(1);
                        novo.setDisciplina(nazivDisciplina);
                        }
                        novo.setIdTakmicenje(takmicenje);
                        takmicenjaView.add(novo);
                        
                        }
                        
                    }
                        
                    
                    if(korisnik.getTip()==2) {
                        
                        next = "delegat_home";
                        
                        mojaTakmicenja = new ArrayList<>();
                        mojaTakmicenjaView = new ArrayList<>();
                        query = "SELECT * FROM takmicenje WHERE idDelegat = '" + korisnik.getId() + 
                                "' AND idTakmicenje IN (SELECT idTakmicenje FROM ucesnik)";
                        rs=c.createStatement().executeQuery(query);
                        while(rs.next()){
                            
                            ResultSet rs2;
                            
                        Takmicenje temp = new Takmicenje();
                        temp.popuni(rs);
                        mojaTakmicenja.add(temp);
                        
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
                        mojaTakmicenjaView.add(novo);
                        }
                        
                        }
                    
                    if(korisnik.getTip()==3){
                        next = "vodja_home";
                        
                        sportovi=new ArrayList<>();
                        query = "SELECT * FROM sport ORDER BY naziv";
                        rs = c.createStatement().executeQuery(query);
                        while(rs.next()){
                        Sport temp = new Sport();
                        temp.popuni(rs);
                        sportovi.add(temp);
                        }
                        
                        /*discipline=new ArrayList<>();
                        query = "SELECT * FROM disciplina ORDER BY naziv";
                        rs = c.createStatement().executeQuery(query);
                        while(rs.next()){
                        Disciplina temp = new Disciplina();
                        temp.popuni(rs);
                        discipline.add(temp);
                        }
                        */
                    }
                    session.setAttribute("takmicenjaView", takmicenjaView);
                    session.setAttribute("mojaTakmicenjaView", mojaTakmicenjaView);
                    session.setAttribute("korisnik", korisnik);
                    session.setAttribute("sportovi", sportovi);
                    session.setAttribute("delegati123", delegati);
                    session.setAttribute("takmicenja", takmicenja);
                    
                    }
                }
                else{
                context.addMessage(null, new FacesMessage("Uneta lozinka je pogresna!"));
                password = "";
                next = "";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
        return next;
    }
    
    public String logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        return "index";
    }
    
}
