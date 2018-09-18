
package controllers;

import beans.Mec;
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

@ManagedBean(name="delegat")
@SessionScoped
public class DelegatController {
    
    private int izabranoTakmicenje;
    private int izabraniFormatRezultata;
    private ArrayList<Mec> mecevi=new ArrayList<>();

    public int getIzabranoTakmicenje() {
        return izabranoTakmicenje;
    }

    public void setIzabranoTakmicenje(int izabranoTakmicenje) {
        this.izabranoTakmicenje = izabranoTakmicenje;
    }

    public int getIzabraniFormatRezultata() {
        return izabraniFormatRezultata;
    }

    public void setIzabraniFormatRezultata(int izabraniFormatRezultata) {
        this.izabraniFormatRezultata = izabraniFormatRezultata;
    }

    public ArrayList<Mec> getMecevi() {
        return mecevi;
    }

    public void setMecevi(ArrayList<Mec> mecevi) {
        this.mecevi = mecevi;
    }
    
    public void razvrstajPoGrupama(){
    
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
           
        try {
            
            String query="SELECT idUcesnik FROM ucesnik WHERE idTakmicenje='" + izabranoTakmicenje + "' ORDER BY RAND()";
            rs = c.createStatement().executeQuery(query);
            ArrayList<Integer> ucesnici = new ArrayList<>();
            
            while(rs.next()){
            ucesnici.add(rs.getInt(1));
            }
            
            int cnt = ucesnici.size();
            
            query="SELECT format FROM mec WHERE idTakmicenje='" + izabranoTakmicenje + "'";
            rs = c.createStatement().executeQuery(query);
            rs.next();
            
            int form = rs.getInt(1);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Broj ucesnika je " + cnt + " , a format je " + form) );
            
            if(form==1){razvrstajFormat1(cnt, c, ucesnici);}
            if(form==2){razvrstajFormat2(cnt, c, ucesnici);}
            if(form==3){razvrstajFormat3(cnt, c, ucesnici);}
            if(form==4){razvrstajFormat4(cnt, c, ucesnici);}
            if(form==5){razvrstajFormat5(cnt, c, ucesnici);}
        
        } catch (SQLException ex) {
            Logger.getLogger(DelegatController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        }
        
    }
    
    public void razvrstajFormat1(int n, Connection c, ArrayList<Integer> niz){
    
        String query;
        FacesContext context = FacesContext.getCurrentInstance();
        ResultSet rs1;
        
        try {
            
            if(n==32){
                query="SELECT idMec FROM mec WHERE faza='16' AND idTakmicenje='" + izabranoTakmicenje + "'";
                rs1=c.createStatement().executeQuery(query);
                for(int i=0;i<16;i++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(2*i) + "', ucesnik2='" + niz.get(2*i+1) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                }  
            }
            
            if(n==16){
                query="SELECT idMec FROM mec WHERE faza='8' AND idTakmicenje='" + izabranoTakmicenje + "'";
                rs1=c.createStatement().executeQuery(query);
                for(int i=0;i<8;i++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(2*i) + "', ucesnik2='" + niz.get(2*i+1) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                }  
            }
            
            
            if(n==8){
                query="SELECT idMec FROM mec WHERE faza='4' AND idTakmicenje='" + izabranoTakmicenje + "'";
                rs1=c.createStatement().executeQuery(query);
                for(int i=0;i<4;i++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(2*i) + "', ucesnik2='" + niz.get(2*i+1) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                }  
            }
            
            if(n==4){
                query="SELECT idMec FROM mec WHERE faza='2' AND idTakmicenje='" + izabranoTakmicenje + "'";
                rs1=c.createStatement().executeQuery(query);
                for(int i=0;i<2;i++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(2*i) + "', ucesnik2='" + niz.get(2*i+1) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                }  
            }
            
        } catch (SQLException ex) {
                context.addMessage(null, new FacesMessage("Izleteo!") );
                Logger.getLogger(DelegatController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            DB.getInstance().putConnection(c);   
        }
    }
    
    
    
    
    
    public void razvrstajFormat2(int n, Connection c, ArrayList<Integer> niz){
    
        String query;
        FacesContext context = FacesContext.getCurrentInstance();
        ResultSet rs1;
        
        try {
        if(n==12){
        
            query="SELECT idMec FROM mec WHERE faza='grupaA' AND idTakmicenje='" + izabranoTakmicenje + "'";
            rs1=c.createStatement().executeQuery(query); 
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(0) + "', ucesnik2='" + niz.get(1) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(2) + "', ucesnik2='" + niz.get(3) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(4) + "', ucesnik2='" + niz.get(5) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(0) + "', ucesnik2='" + niz.get(2) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(3) + "', ucesnik2='" + niz.get(4) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(1) + "', ucesnik2='" + niz.get(5) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(0) + "', ucesnik2='" + niz.get(3) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(1) + "', ucesnik2='" + niz.get(4) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(2) + "', ucesnik2='" + niz.get(5) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(0) + "', ucesnik2='" + niz.get(4) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(1) + "', ucesnik2='" + niz.get(2) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(3) + "', ucesnik2='" + niz.get(5) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(0) + "', ucesnik2='" + niz.get(5) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(1) + "', ucesnik2='" + niz.get(3) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(2) + "', ucesnik2='" + niz.get(4) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
            //Druga grupa
            query="SELECT idMec FROM mec WHERE faza='grupaB' AND idTakmicenje='" + izabranoTakmicenje + "'";
            rs1=c.createStatement().executeQuery(query); 
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(6) + "', ucesnik2='" + niz.get(7) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(8) + "', ucesnik2='" + niz.get(9) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(10) + "', ucesnik2='" + niz.get(11) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(6) + "', ucesnik2='" + niz.get(8) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(9) + "', ucesnik2='" + niz.get(10) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(7) + "', ucesnik2='" + niz.get(11) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(6) + "', ucesnik2='" + niz.get(9) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(7) + "', ucesnik2='" + niz.get(10) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(8) + "', ucesnik2='" + niz.get(11) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(6) + "', ucesnik2='" + niz.get(10) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(7) + "', ucesnik2='" + niz.get(8) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(9) + "', ucesnik2='" + niz.get(11) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(6) + "', ucesnik2='" + niz.get(11) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(7) + "', ucesnik2='" + niz.get(9) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(8) + "', ucesnik2='" + niz.get(10) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query); 
            
           
            for(int i=0;i<6;i++){
            query="INSERT INTO `ekipabodovi` (`idTakmicenje`, `idEkipa`, `grupa`, `brojBodova`) VALUES ('" 
                    + izabranoTakmicenje + "', '" + niz.get(i) + "', 'grupaA', '0')";
            c.createStatement().executeUpdate(query);
            }
            for(int i=0;i<6;i++){
            query="INSERT INTO `ekipabodovi` (`idTakmicenje`, `idEkipa`, `grupa`, `brojBodova`) VALUES ('" 
                    + izabranoTakmicenje + "', '" + niz.get(i+6) + "', 'grupaB', '0')";
            c.createStatement().executeUpdate(query);
            }
            
        }
        
        if(n==8){
        
            query="SELECT idMec FROM mec WHERE faza='4' AND idTakmicenje='" + izabranoTakmicenje + "'";
            rs1=c.createStatement().executeQuery(query); 
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(0) + "', ucesnik2='" + niz.get(1) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(2) + "', ucesnik2='" + niz.get(3) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(4) + "', ucesnik2='" + niz.get(5) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(6) + "', ucesnik2='" + niz.get(7) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            
        }
        
        if(n==4){
        
            query="SELECT idMec FROM mec WHERE faza='2' AND idTakmicenje='" + izabranoTakmicenje + "'";
            rs1=c.createStatement().executeQuery(query); 
            
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(0) + "', ucesnik2='" + niz.get(1) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            rs1.next();
            query="UPDATE mec SET ucesnik1='" + niz.get(2) + "', ucesnik2='" + niz.get(3) + "' WHERE idMec='" + rs1.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
        
        }
        
        } catch (SQLException ex) {
                context.addMessage(null, new FacesMessage("Izleteo!") );
                Logger.getLogger(DelegatController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            DB.getInstance().putConnection(c);   
        }
        
    }    
    
    
    public void razvrstajFormat3(int n, Connection c, ArrayList<Integer> niz){
    
        String query;
        ResultSet rs1;
        FacesContext context = FacesContext.getCurrentInstance();
       
        try {
            
            if(n>8){
            int cnt = (int)Math.ceil((float)n/8);
            context.addMessage(null, new FacesMessage("Broj grupa je " + cnt));
            
            int x=0;
            for(int i=0;i<(cnt-1);i++){
                String grupa="grupa" + (i+1);
                query="SELECT idMec FROM mec WHERE faza='" + grupa + "' AND idTakmicenje='" + izabranoTakmicenje + "'";
                rs1=c.createStatement().executeQuery(query);
                for(int j=0;j<8;j++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(x) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                    x++;
                }
            }
            
            int rest = 8-(cnt*8-n);
            String poslednja="grupa" + cnt;
            String grupa=poslednja;
            query="SELECT idMec FROM mec WHERE faza='" + grupa + "' AND idTakmicenje='" + izabranoTakmicenje + "'";
            rs1=c.createStatement().executeQuery(query);
            for(int k =0;k<rest;k++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(x) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                    x++;
                }      
            
            }
            else{
                
                query="SELECT idMec FROM mec WHERE faza='1' AND idTakmicenje='" + izabranoTakmicenje + "'";
                rs1=c.createStatement().executeQuery(query);
                
                for(int i=0;i<n;i++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(i) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
            }
            } 
           
        } catch (SQLException ex) {
            context.addMessage(null, new FacesMessage("Izleteo!") );
            Logger.getLogger(UcesniciController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        
        }
    
    }
    
    
    public void razvrstajFormat4(int n, Connection c, ArrayList<Integer> niz){
    
        String query;
        ResultSet rs1;
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
            query="SELECT idMec FROM mec WHERE faza='1' AND idTakmicenje='" + izabranoTakmicenje + "'";
            rs1=c.createStatement().executeQuery(query);
                
            for(int i=0;i<n;i++){
                rs1.next();
                query="UPDATE mec SET ucesnik1='" + niz.get(i) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                c.createStatement().executeUpdate(query);
            }
        } catch (SQLException ex) {
            context.addMessage(null, new FacesMessage("Izleteo!") );
            Logger.getLogger(DelegatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void razvrstajFormat5(int n, Connection c, ArrayList<Integer> niz){
    
        String query;
        ResultSet rs1;
        FacesContext context = FacesContext.getCurrentInstance();
       
        try {
            
            if(n>8){
            int cnt = (int)Math.ceil((float)n/8);
            context.addMessage(null, new FacesMessage("Broj grupa je " + cnt));
            
            int x=0;
            for(int i=0;i<(cnt-1);i++){
                String grupa="grupa" + (i+1);
                query="SELECT idMec FROM mec WHERE faza='" + grupa + "' AND idTakmicenje='" + izabranoTakmicenje + "'";
                rs1=c.createStatement().executeQuery(query);
                for(int j=0;j<8;j++){
                    for(int k=0;k<6;k++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(x) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                    }
                    x++;
                }
            }
            
            int rest = 8-(cnt*8-n);
            String poslednja="grupa" + cnt;
            String grupa=poslednja;
            query="SELECT idMec FROM mec WHERE faza='" + grupa + "' AND idTakmicenje='" + izabranoTakmicenje + "'";
            rs1=c.createStatement().executeQuery(query);
            for(int l=0;l<rest;l++){
                for(int m=0;m<6;m++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(x) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                }
                    x++;
                }      
            
            }
            else{
                
                query="SELECT idMec FROM mec WHERE faza='1' AND idTakmicenje='" + izabranoTakmicenje + "'";
                rs1=c.createStatement().executeQuery(query);
                
                for(int i=0;i<n;i++){
                    for(int j=0;j<6;j++){
                    rs1.next();
                    query="UPDATE mec SET ucesnik1='" + niz.get(i) + "' WHERE idMec='" + rs1.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                    }
                }
            } 
           
        } catch (SQLException ex) {
            context.addMessage(null, new FacesMessage("Izleteo!") );
            Logger.getLogger(UcesniciController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        DB.getInstance().putConnection(c);
        
        } 
        
    }
    
    public String novaStrana(){
    
        FacesContext context = FacesContext.getCurrentInstance();
        Connection c = DB.getInstance().getConnection();
        ResultSet rs;
        String query="SELECT * FROM mec WHERE idTakmicenje='" + izabranoTakmicenje + "' AND zavrseno='0'";
        String next="";
        try {
            String p;
            mecevi = new ArrayList<>();
            rs = c.createStatement().executeQuery(query);
            while(rs.next()){
            Mec temp = new Mec();
            temp.popuni(rs);
            
            int idT = temp.getIdTakmicenje();
            query="SELECT tip FROM takmicenje WHERE idTakmicenje='" + idT + "'";
            ResultSet rs2;
            rs2=c.createStatement().executeQuery(query);
            rs2.next();
            int t = rs2.getInt(1);
            
            if(temp.getUcesnik1()!=0 || temp.getUcesnik2()!=0){
            
            if(t==1){
                //context.addMessage(null, new FacesMessage("Tip1 pre") );
            
                query="SELECT ime, prezime FROM sportista WHERE idSportista='" + temp.getUcesnik1() + "'";
                rs2=c.createStatement().executeQuery(query);
                rs2.next();
                temp.setNazivUcesnika1(rs2.getString(1) + " " + rs2.getString(2));
                
                if(temp.getUcesnik2()!=0){
                query="SELECT ime, prezime FROM sportista WHERE idSportista='" + temp.getUcesnik2() + "'";
                rs2=c.createStatement().executeQuery(query);
                rs2.next();
                temp.setNazivUcesnika2(rs2.getString(1) + " " + rs2.getString(2));
                }
            //context.addMessage(null, new FacesMessage("Tip1 posle") );
            }
            if(t==2){
              //  context.addMessage(null, new FacesMessage("Tip2 pre") );
            query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + temp.getUcesnik1() + "'";
            rs2=c.createStatement().executeQuery(query);
            rs2.next();
            temp.setNazivUcesnika1(rs2.getString(1));
            query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + temp.getUcesnik2() + "'";
            rs2=c.createStatement().executeQuery(query);
            rs2.next();
            temp.setNazivUcesnika2(rs2.getString(1));
            //context.addMessage(null, new FacesMessage("Tip2 posle") );
            }
            }
            
            mecevi.add(temp);
            }
            
            if(izabraniFormatRezultata==1){next="delegat_form1";}
            if(izabraniFormatRezultata==2){next="delegat_form2";}
            if(izabraniFormatRezultata==3){next="delegat_form3";}
            if(izabraniFormatRezultata==4){next="delegat_form4";}
            
            HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
            session.setAttribute("mecevi", mecevi);
        
        } catch (SQLException ex) {
            context.addMessage(null, new FacesMessage("Izleteo!") );
            Logger.getLogger(DelegatController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            DB.getInstance().putConnection(c);
        }
        
        return next;
    }
    
}
