
package controllers;

import beans.Disciplina;
import beans.Sport;
import beans.Sportista;
import beans.Zemlja;
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

@ManagedBean(name="neregistrovani")
@SessionScoped
public class NeregistrovaniController {
  
    private ArrayList<Zemlja> zemlje = new ArrayList<>();
    
    private String izabranoIme;
    private String izabranoPrezime;
    private String izabranaZemlja;
    private int izabraniSport;
    private int izabranaDisciplina=0;
    private String izabraniPol;
    private boolean osvajacMedalje=false;
    
    private String testQuery;
    
    private ArrayList<Disciplina> discipline = new ArrayList<>();
    private ArrayList<Sport> sportovi = new ArrayList<>();  
    private ArrayList<Sportista> sportisti = new ArrayList<>();

    public ArrayList<Sportista> getSportisti() {
        return sportisti;
    }

    public void setSportisti(ArrayList<Sportista> sportisti) {
        this.sportisti = sportisti;
    }

    public ArrayList<Sport> getSportovi() {
        return sportovi;
    }

    public void setSportovi(ArrayList<Sport> sportovi) {
        this.sportovi = sportovi;
    }

    public String getIzabranoPrezime() {
        return izabranoPrezime;
    }

    public String getTestQuery() {
        return testQuery;
    }

    public void setTestQuery(String testQuery) {
        this.testQuery = testQuery;
    }

    public void setIzabranoPrezime(String izabranoPrezime) {
        this.izabranoPrezime = izabranoPrezime;
    }

    public String getIzabranoIme() {
        return izabranoIme;
    }

    public void setIzabranoIme(String izabranoIme) {
        this.izabranoIme = izabranoIme;
    }

    public String getIzabranaZemlja() {
        return izabranaZemlja;
    }

    public void setIzabranaZemlja(String izabranaZemlja) {
        this.izabranaZemlja = izabranaZemlja;
    }

    public int getIzabraniSport() {
        return izabraniSport;
    }

    public void setIzabraniSport(int izabraniSport) {
        this.izabraniSport = izabraniSport;
    }

    public int getIzabranaDisciplina() {
        return izabranaDisciplina;
    }

    public void setIzabranaDisciplina(int izabranaDisciplina) {
        this.izabranaDisciplina = izabranaDisciplina;
    }
    
  
    public String getIzabraniPol() {
        return izabraniPol;
    }

    public void setIzabraniPol(String izabraniPol) {
        this.izabraniPol = izabraniPol;
    }

    public boolean getOsvajacMedalje() {
        return osvajacMedalje;
    }

    public void setOsvajacMedalje(boolean osvajacMedalje) {
        this.osvajacMedalje = osvajacMedalje;
    }

    public ArrayList<Disciplina> getDiscipline() {
        return discipline;
    }

    public void setDiscipline(ArrayList<Disciplina> discipline) {
        this.discipline = discipline;
    }
    
    public ArrayList<Zemlja> getZemlje() {
        return zemlje;
    }

    public void setZemlje(ArrayList<Zemlja> zemlje) {
        this.zemlje = zemlje;
    }
    
    
    
  public String stranaPregled(){
      
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext context = FacesContext.getCurrentInstance();
        zemlje=new ArrayList<>();
        
        try {
            
            query="SELECT * from zemljamedalje order by zlato DESC, srebro DESC, bronza DESC";
            rs = c.createStatement().executeQuery(query);
            int rang = 1;
            while(rs.next()){
            Zemlja temp = new Zemlja();
            temp.popuni(rs);
            temp.setRang(rang);
            rang++;
            int z,s,b;
            z=rs.getInt("zlato");
            s=rs.getInt("srebro");
            b=rs.getInt("bronza");
            temp.setUkupno(z+s+b);
            query="SELECT COUNT(*) from sportista WHERE nacionalnost='" + temp.getNacionalnost() + "'";
            ResultSet rs2 = c.createStatement().executeQuery(query);
            rs2.next();
            temp.setBrojSportista(rs2.getInt(1));
            zemlje.add(temp);
            }
            
            query="SELECT DISTINCT nacionalnost from korisnik WHERE tip='3' AND nacionalnost NOT IN (SELECT nacionalnost FROM zemljamedalje)";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            Zemlja temp = new Zemlja();
            temp.setNacionalnost(rs.getString(1));
            temp.setZlato(0);
            temp.setSrebro(0);
            temp.setBronza(0);
            temp.setUkupno(0);
            temp.setRang(0);
            query="SELECT COUNT(*) from sportista WHERE nacionalnost='" + temp.getNacionalnost() + "'";
            ResultSet rs2 = c.createStatement().executeQuery(query);
            rs2.next();
            temp.setBrojSportista(rs2.getInt(1));
            zemlje.add(temp);
            }
           
            query="SELECT * FROM sport";
            rs = c.createStatement().executeQuery(query);
            sportovi=new ArrayList<>();
            while(rs.next()){
            Sport temp = new Sport();
            temp.popuni(rs);
            sportovi.add(temp);
            }
            
        } catch (SQLException ex) {
            context.addMessage(null, new FacesMessage("Izleteo!"));
            Logger.getLogger(NeregistrovaniController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
   
        return "neregistrovani_home";   
      
  }  
   
  public String pregledSportista(){
  
        String query;
        boolean veza=false;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        
        query="SELECT * FROM sportista";
        
        if(!izabranoIme.isEmpty()){
            if(!veza){
            query+=" WHERE ime='" + izabranoIme + "'";
            veza=true;
            }
            else{
            query+=" AND ime='" + izabranoIme + "'";
            }
        }
        
        if(!izabranoPrezime.isEmpty()){
            if(!veza){
            query+=" WHERE prezime='" + izabranoPrezime + "'";
            veza=true;
            }
            else{
            query+=" AND prezime='" + izabranoPrezime + "'";
            }
        }
        
        if(!izabranaZemlja.equals("prazno")){
            if(!veza){
            query+=" WHERE nacionalnost='" + izabranaZemlja + "'";
            veza=true;
            }
            else{
            query+=" AND nacionalnost='" + izabranaZemlja + "'";
            }
        }
        
        if(!(izabraniSport==0)){
            if(!veza){
            query+=" WHERE idSport='" + izabraniSport + "'";
            veza=true;
            }
            else{
            query+=" AND idSport='" + izabraniSport + "'";
            }
        }
        
        if(!(izabranaDisciplina==0)){
            if(!veza){
            query+=" WHERE idSportista IN (SELECT idSportista FROM sportistadisciplina WHERE idDisciplina='" + izabranaDisciplina + "')";
            veza=true;
            }
            else{
            query+=" AND idSportista IN (SELECT idSportista FROM sportistadisciplina WHERE idDisciplina='" + izabranaDisciplina + "')";
            }
        }
        
        if(osvajacMedalje){
            if(!veza){
            query+=" WHERE idSportista IN (SELECT idSportista FROM sportistamedalja)";
            veza=true;
            }
            else{
            query+=" AND idSportista IN (SELECT idSportista FROM sportistamedalja)";
            }
        }
        
        try {
            rs = c.createStatement().executeQuery(query);
            sportisti= new ArrayList<>();
            while(rs.next()){
            Sportista temp = new Sportista();
            temp.popuni(rs);
            sportisti.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NeregistrovaniController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
         DB.getInstance().putConnection(c);
        }
        
      return "neregistrovani_sportisti";
  }
  
  public void updateDiscipline(){
        
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        discipline = new ArrayList<>();
        
        try {
            query="SELECT * FROM disciplina WHERE idSport='" + izabraniSport + "'";
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            Disciplina temp = new Disciplina();
            temp.popuni(rs);
            discipline.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NeregistrovaniController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
  }
  
}
