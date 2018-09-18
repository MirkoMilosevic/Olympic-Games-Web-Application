
package controllers;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import beans.*;
import database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name="org_ucesnici")
@SessionScoped
public class UcesniciController {
    
    
    private int idTakmicenje;
    private int format;
    private ArrayList<Sportista> ucesnici = new ArrayList<>();
    private ArrayList<Ekipa> ekipe = new ArrayList<>();
    private ArrayList<?> izabraniUcesnici = new ArrayList<>();
    private ArrayList<?> izabraneEkipe = new ArrayList<>();
    
    private boolean prikaziUcesnike = false;
    private boolean prikaziEkipe = false;
    
    

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public ArrayList<?> getIzabraniUcesnici() {
        return izabraniUcesnici;
    }

    public void setIzabraniUcesnici(ArrayList<?> izabraniUcesnici) {
        this.izabraniUcesnici = izabraniUcesnici;
    }

    public ArrayList<?> getIzabraneEkipe() {
        return izabraneEkipe;
    }

    public void setIzabraneEkipe(ArrayList<?> izabraneEkipe) {
        this.izabraneEkipe = izabraneEkipe;
    }
    
    public boolean isPrikaziUcesnike() {
        return prikaziUcesnike;
    }

    public void setPrikaziUcesnike(boolean prikaziUcesnike) {
        this.prikaziUcesnike = prikaziUcesnike;
    }

    public boolean isPrikaziEkipe() {
        return prikaziEkipe;
    }

    public void setPrikaziEkipe(boolean prikaziEkipe) {
        this.prikaziEkipe = prikaziEkipe;
    }
   
    public int getIdTakmicenje() {
        return idTakmicenje;
    }

    public void setIdTakmicenje(int idTakmicenje) {
        this.idTakmicenje = idTakmicenje;
    }

    public ArrayList<Sportista> getUcesnici() {
        return ucesnici;
    }

    public void setUcesnici(ArrayList<Sportista> ucesnici) {
        this.ucesnici = ucesnici;
    }

    public ArrayList<Ekipa> getEkipe() {
        return ekipe;
    }

    public void setEkipe(ArrayList<Ekipa> ekipe) {
        this.ekipe = ekipe;
    }
    
    
    public void ucesniciListener(){
    
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        //FacesContext context = FacesContext.getCurrentInstance();
        ucesnici = new ArrayList<>();
        ekipe = new ArrayList<>();
        prikaziUcesnike=false;
        prikaziEkipe=false;
        
        try {
            query="SELECT tip, idDisciplina, idSport FROM takmicenje WHERE idTakmicenje='" + idTakmicenje + "'";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            int tip = rs.getInt(1);
            int idDis = rs.getInt(2);
            int idSport = rs.getInt(3);
            ResultSet pom;
            
            if(tip==1 && idDis!=0){
                prikaziUcesnike=true;
                query="SELECT idSportista FROM sportistadisciplina WHERE idDisciplina = '" + idDis + "'";
                rs = c.createStatement().executeQuery(query);
                while(rs.next()){
                int idS = rs.getInt(1);
                query="SELECT * FROM sportista WHERE idSportista ='" + idS + "'";
                pom = c.createStatement().executeQuery(query);
                pom.next();
                Sportista s = new Sportista();
                s.popuni(pom);
                ucesnici.add(s);
                }
            }
            else{
                
                prikaziEkipe=true;
                query="SELECT * FROM ekipa WHERE idSport='" + idSport + "'";
                rs = c.createStatement().executeQuery(query);
                while(rs.next()){
                Ekipa e = new Ekipa();
                e.popuni(rs);
                ekipe.add(e);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UcesniciController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }  
    }
    
    public String dodajUcesnike(){
        
        String next="";
        String query;
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext context = FacesContext.getCurrentInstance();
        
     
        
        try {
            
            query="SELECT tip FROM takmicenje WHERE idTakmicenje='" + idTakmicenje + "'";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            int t = rs.getInt(1);
            
            if(t==1){
            int n = izabraniUcesnici.size();
            
            if((n!=32 && n!=16 && n!=8 && n!=4) && format==1){
            context.addMessage(null, new FacesMessage("Neodgovarajuci broj ucesnika za ovaj format!"));
            return "";
            }
            if((n!=12 && n!=8 && n!=4) && format==2){
            context.addMessage(null, new FacesMessage("Neodgovarajuci broj ucesnika za ovaj format!"));
            return "";
            }
            
            for(int i=0; i<n; i++){
            query="INSERT INTO `ucesnik` (`idTakmicenje`,`idUcesnik`) VALUES('"
                    + idTakmicenje + "','" + izabraniUcesnici.get(i) + "')";
            c.createStatement().executeUpdate(query);
            }
            context.addMessage(null, new FacesMessage("Takmicari su uspesno dodati!"));
            //DB.getInstance().putConnection(c);
            if(format==1){formatKup(n,c);}
            if(format==2){formatEkipniGrupe(n,c);}
            if(format==3){formatIndividualniGrupe(n, c);}
            if(format==4){formatJednaTrka(n, c);}
            if(format==5){formatGadjanje(n, c);}
            
            context.addMessage(null, new FacesMessage("Ekipe su uspesno dodate!"));
            }
            else{
            int n = izabraneEkipe.size();
            
            if((n!=32 && n!=16 && n!=8 && n!=4) && format==1){
            context.addMessage(null, new FacesMessage("Neodgovarajuci broj ucesnika za ovaj format!"));
            return "";
            }
            if((n!=12) && format==2){
            context.addMessage(null, new FacesMessage("Neodgovarajuci broj ucesnika za ovaj format!"));
            return "";
            }
            
            for(int i=0; i<n; i++){
            query="INSERT INTO `ucesnik` (`idTakmicenje`,`idUcesnik`) VALUES('"
                    + idTakmicenje + "','" + izabraneEkipe.get(i) + "')";
            c.createStatement().executeUpdate(query);
            }
            context.addMessage(null, new FacesMessage("Ekipe su uspesno dodate!"));
            //DB.getInstance().putConnection(c);
            if(format==1){formatKup(n,c);}
            if(format==2){formatEkipniGrupe(n,c);}
            if(format==3){formatIndividualniGrupe(n, c);}
            if(format==4){formatJednaTrka(n, c);}
            if(format==5){formatGadjanje(n, c);}
            
            context.addMessage(null, new FacesMessage("Mecevi su uspesno dodati!"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(UcesniciController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
       
        return next;
    }
    
    public void formatKup(int n, Connection c) throws SQLException{
    
        String query;
        //Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        FacesContext context = FacesContext.getCurrentInstance();
        
        
    
        try {
        
        if(n==32){
        for(int i=0;i<16;i++){
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','16','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
        }
        for(int i=0;i<8;i++){
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','8','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
        }
        for(int i=0;i<4;i++){
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','4','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
        }
        }
        
        if(n==16){
        for(int i=0;i<8;i++){
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','8','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
        }
        for(int i=0;i<4;i++){
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','4','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
        }
        }
        
        if(n==8){    
        for(int i=0;i<4;i++){
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','4','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
        }
        }
        
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','2','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
        
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','2','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
        
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','3','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
        
        query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                +  idTakmicenje + "','1','" + format + "', '0')"; 
        c.createStatement().executeUpdate(query);
       
        }catch (SQLException ex) {
            Logger.getLogger(UcesniciController.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage("Izleteo"));
        }finally{
        DB.getInstance().putConnection(c);
       } 
    }
    
    
    public void formatEkipniGrupe(int n, Connection c){
    
        String query;
        //Connection con = DB.getInstance().getConnection();
        //FacesContext context = FacesContext.getCurrentInstance();
        //context.addMessage(null, new FacesMessage("Usao sam u ekipniGrupe"));
        
        try {
            
            if(n==12){
            String grupa = "grupaA";
            for(int i=0;i<15;i++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','" + grupa + "','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
            }
            
            grupa = "grupaB";
            for(int i=0;i<15;i++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','" + grupa + "','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
            }
            }
            
            if(n==12 || n==8){
            for(int i=0;i<4;i++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','4','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
          }
            }
            
            query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','2','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
        
            query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','2','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
        
            query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','3','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
        
            query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','1','" + format + "', '0')";
                c.createStatement().executeUpdate(query);  
           
        } catch (SQLException ex) {
                Logger.getLogger(UcesniciController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
        DB.getInstance().putConnection(c);
        }
    }
   
    public void formatIndividualniGrupe(int n, Connection c){
    
        String query;
       
        try {
            if(n>8){
            int cnt = (int)Math.ceil((float)n/8);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Broj grupa je " + cnt));
            
            for(int i=0;i<(cnt-1);i++){
                String grupa="grupa" + (i+1);
                for(int j=0;j<8;j++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','" + grupa + "','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
                }
            }
            
            int rest = 8-(cnt*8-n);
            String poslednja="grupa" + cnt;
            for(int k =0;k<rest;k++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','" + poslednja + "','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
            }      
            
            for(int i=0;i<8;i++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','1','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
            }
            }
            else{
            for(int i=0;i<n;i++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','1','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
            }
            } 
           
        } catch (SQLException ex) {
            Logger.getLogger(UcesniciController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }  
    }
    
    public void formatJednaTrka(int n,Connection c){
    
       
           try {
               for(int i=0;i<n;i++){
               String query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                       + idTakmicenje + "','1','" + format + "', '0')";
               c.createStatement().executeUpdate(query);
               }
           } catch (SQLException ex) {
               Logger.getLogger(UcesniciController.class.getName()).log(Level.SEVERE, null, ex);
           }finally{
           DB.getInstance().putConnection(c);
           } 
    }
   
    public void formatGadjanje(int n, Connection c){
    
        String query;
       
        try {
            if(n>8){
            int cnt = (int)Math.ceil((float)n/8);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Broj grupa je " + cnt));
            
            for(int i=0;i<(cnt-1);i++){
                String grupa="grupa" + (i+1);
                for(int j=0;j<48;j++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','" + grupa + "','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
                }
            }
            
            int rest = 8-(cnt*8-n);
            String poslednja="grupa" + cnt;
            for(int k =0;k<(rest*6);k++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','" + poslednja + "','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
            }      
            
            for(int i=0;i<48;i++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','1','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
            }
            }
            else{
            for(int i=0;i<(n*6);i++){
                query="INSERT INTO `mec` (`idTakmicenje`, `faza`, `format`, `zavrseno`) VALUES ('"
                        + idTakmicenje + "','1','" + format + "', '0')";
                c.createStatement().executeUpdate(query);
            }
            } 
           
        } catch (SQLException ex) {
            Logger.getLogger(UcesniciController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
    }
    
}
