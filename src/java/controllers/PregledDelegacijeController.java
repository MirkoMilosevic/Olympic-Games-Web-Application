
package controllers;

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name="vodja_pregled")
@SessionScoped
public class PregledDelegacijeController {
   
    public class SportPregled{
    
        public int n;
        public String sport;
        public String kategorija;
        
        SportPregled(int nn, String ss, String kk){
        this.n=nn;
        this.sport=ss;
        this.kategorija=kk;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public String getSport() {
            return sport;
        }

        public void setSport(String sport) {
            this.sport = sport;
        }

        public String getKategorija() {
            return kategorija;
        }

        public void setKategorija(String kategorija) {
            this.kategorija = kategorija;
        }
        
        
    }
    
    
    
    private ArrayList<Sportista> mojiSportisti = new ArrayList<>();
    private int brojSportista;
    private ArrayList<SportPregled> sportPregled = new ArrayList<>();

    //Za pregled sportista po disciplinama
    private ArrayList<Sport> sportoviDetaljno = new ArrayList<>();
    private ArrayList<Disciplina> disciplineDetaljno = new ArrayList<>();
    private ArrayList<Sportista> sportistiDetaljno = new ArrayList<>();
    private int izabraniSportDetaljno;
    private int izabranaDisciplinaDetaljno;
    private String izabraniSportNaziv;
    private String izabranaDisciplinaNaziv;

    
    
    public String getIzabraniSportNaziv() {
        return izabraniSportNaziv;
    }

    public void setIzabraniSportNaziv(String izabraniSportNaziv) {
        this.izabraniSportNaziv = izabraniSportNaziv;
    }

    public String getIzabranaDisciplinaNaziv() {
        return izabranaDisciplinaNaziv;
    }

    public void setIzabranaDisciplinaNaziv(String izabranaDisciplinaNaziv) {
        this.izabranaDisciplinaNaziv = izabranaDisciplinaNaziv;
    }
    
    public int getIzabranaDisciplinaDetaljno() {
        return izabranaDisciplinaDetaljno;
    }

    public void setIzabranaDisciplinaDetaljno(int izabranaDisciplinaDetaljno) {
        this.izabranaDisciplinaDetaljno = izabranaDisciplinaDetaljno;
    }

    public int getIzabraniSportDetaljno() {
        return izabraniSportDetaljno;
    }

    public void setIzabraniSportDetaljno(int izabraniSportDetaljno) {
        this.izabraniSportDetaljno = izabraniSportDetaljno;
    }

    public ArrayList<Sport> getSportoviDetaljno() {
        return sportoviDetaljno;
    }

    public void setSportoviDetaljno(ArrayList<Sport> sportoviDetaljno) {
        this.sportoviDetaljno = sportoviDetaljno;
    }

    public ArrayList<Disciplina> getDisciplineDetaljno() {
        return disciplineDetaljno;
    }

    public void setDisciplineDetaljno(ArrayList<Disciplina> disciplineDetaljno) {
        this.disciplineDetaljno = disciplineDetaljno;
    }

    public ArrayList<Sportista> getSportistiDetaljno() {
        return sportistiDetaljno;
    }

    public void setSportistiDetaljno(ArrayList<Sportista> sportistiDetaljno) {
        this.sportistiDetaljno = sportistiDetaljno;
    }
    
    public ArrayList<Sport> getSportovi() {
        return sportoviDetaljno;
    }

    public void setSportovi(ArrayList<Sport> sportovi) {
        this.sportoviDetaljno = sportovi;
    }
    
    public ArrayList<SportPregled> getSportPregled() {
        return sportPregled;
    }

    public void setSportPregled(ArrayList<SportPregled> sportPregled) {
        this.sportPregled = sportPregled;
    }
    
    public int getBrojSportista() {
        return brojSportista;
    }

    public void setBrojSportista(int brojSportista) {
        this.brojSportista = brojSportista;
    }

    public ArrayList<Sportista> getMojiSportisti() {
        return mojiSportisti;
    }

    public void setMojiSportisti(ArrayList<Sportista> mojiSportisti) {
        this.mojiSportisti = mojiSportisti;
    }
    
    public void prebrojSportiste(){
        sportPregled = new ArrayList<>();
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Korisnik temp = (Korisnik)session.getAttribute("korisnik");
        
        try {
            query="SELECT COUNT(*) FROM sportista where nacionalnost='" + temp.getNacionalnost() + "'";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            brojSportista = rs.getInt(1);
            
            query="SELECT COUNT(*),idSport FROM sportista where nacionalnost='" + temp.getNacionalnost() + "' GROUP BY idSport";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            int nn = rs.getInt(1);
            int id = rs.getInt(2);
            query="SELECT naziv, kategorija FROM sport WHERE idSport='" + id + "'";
            ResultSet rs2 = c.createStatement().executeQuery(query);
            rs2.next();
            String ime = rs2.getString(1);
            String kat = rs2.getString(2);
            SportPregled sp = new SportPregled(nn, ime, kat);
            sportPregled.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PregledDelegacijeController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
    }
    
    public String pregledSportova(String nac){
    
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;  
        sportoviDetaljno = new ArrayList<>();
        
        try {
            query="SELECT DISTINCT s.idSport from sportista s where s.nacionalnost='" + nac + "'";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            int idS = rs.getInt(1);
            query="SELECT * FROM sport WHERE idSport='" + idS + "'";
            ResultSet rs2;
            rs2 = c.createStatement().executeQuery(query);
            rs2.next();
            Sport temp = new Sport();
            temp.popuni(rs2);
            sportoviDetaljno.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PregledDelegacijeController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
        
        return "vodja_sportovi";
    }
    
    public String pregledDisciplina(){
    
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;  
        disciplineDetaljno = new ArrayList<>();
         
        try {
            query="SELECT naziv,kategorija FROM sport WHERE idSport='" + izabraniSportDetaljno + "'";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            izabraniSportNaziv = rs.getString(1) + " " + rs.getString(2);
            
            query="SELECT * FROM disciplina WHERE idSport='" + izabraniSportDetaljno + "'";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            Disciplina temp = new Disciplina();
            temp.popuni(rs);
            disciplineDetaljno.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PregledDelegacijeController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
       
        return "vodja_discipline";
    }
    
    public String pregledDetaljno(String nac){
    
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;  
        sportistiDetaljno = new ArrayList<>();
         
        try {
            query="SELECT naziv FROM disciplina WHERE idDisciplina='" + izabranaDisciplinaDetaljno + "'";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            izabranaDisciplinaNaziv = rs.getString(1);
            
            query="SELECT s.* FROM sportista s where s.idSport='" + izabraniSportDetaljno + "' AND s.nacionalnost='" + nac +"' " +
                  "AND EXISTS(SELECT * from sportistadisciplina sp where sp.idSportista=s.idSportista AND sp.idDisciplina='" + izabranaDisciplinaDetaljno + "')"
                    + " ORDER BY s.prezime, s.ime";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            Sportista temp = new Sportista();
            temp.popuni(rs);
            sportistiDetaljno.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PregledDelegacijeController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
       
        return "vodja_detaljno";
    }
    
}
