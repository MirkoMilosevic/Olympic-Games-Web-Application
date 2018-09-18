
package controllers;

import beans.Mec;
import database.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name="delegat_rezultat")
@SessionScoped
public class DelegatRezultatController {
    
    private int jedinica;
    
    private String datum;
    private String vreme;
    private String lokacija;
    private int rezultat1;
    private int rezultat2;
    private int rezultat3;
    
    private int autoVreme;

    public int getAutoVreme() {
        return autoVreme;
    }

    public void setAutoVreme(int autoVreme) {
        this.autoVreme = autoVreme;
    }
    
    public int getJedinica() {
        return jedinica;
    }

    public void setJedinica(int jedinica) {
        this.jedinica = jedinica;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public int getRezultat1() {
        return rezultat1;
    }

    public void setRezultat1(int rezultat1) {
        this.rezultat1 = rezultat1;
    }

    public int getRezultat2() {
        return rezultat2;
    }

    public void setRezultat2(int rezultat2) {
        this.rezultat2 = rezultat2;
    }

    public int getRezultat3() {
        return rezultat3;
    }

    public void setRezultat3(int rezultat3) {
        this.rezultat3 = rezultat3;
    }
    
    public void updateMecevi(Connection c, int id) throws SQLException{
    
        String query="SELECT * FROM mec WHERE idTakmicenje='" + id + "' AND zavrseno='0'";
        ResultSet rs;
        ArrayList<Mec> mec=new ArrayList<>();
        
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
            
            mec.add(temp);
        }
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        session.setAttribute("mecevi", mec);
        
    }

    public void proveriRaspored(Mec m){
        
        FacesContext context = FacesContext.getCurrentInstance();
        Connection c = DB.getInstance().getConnection();
        String query;
        ResultSet rs;
        
         try {
         
        query="SELECT * FROM mec WHERE idMec='" + m.getIdMec() + "' AND zakazano='1'";
        rs = c.createStatement().executeQuery(query);
        if(rs.next()){
        context.addMessage(null, new FacesMessage("Mec je vec zakazan!") );
        return;
        }
             
        int format = m.getFormat();
             
        if(format==3 || format==4 || format==5){
        
        query="SELECT * FROM mec WHERE datum='" + m.getDatum() + "' AND vreme='" + m.getVreme() + "' AND lokacija='" + m.getLokacija() + "'";
        rs = c.createStatement().executeQuery(query);
        if(rs.next()){
        context.addMessage(null, new FacesMessage("Vec postoji dogadja zakazan u dato vreme na datom mestu!") );
        return;
        } 
         
        query="UPDATE mec SET datum='" + m.getDatum() + "', vreme='" + m.getVreme() + "', lokacija='" + m.getLokacija() + "', zakazano='1' WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
        c.createStatement().executeUpdate(query);
        context.addMessage(null, new FacesMessage("Dogadjaj je zakazan za " + m.getDatum() + " u " + m.getVreme() + " na " + m.getLokacija()) );
        m.setDisable1(true);
            updateMecevi(c, m.getIdTakmicenje());
            return;
        }
             
        query="SELECT * FROM mec WHERE datum='" + m.getDatum() + "' AND vreme='" + m.getVreme() + "' AND lokacija='" + m.getLokacija() + "'";
        rs = c.createStatement().executeQuery(query);
        if(rs.next()){
        context.addMessage(null, new FacesMessage("Vec postoji dogadja zakazan u dato vreme na datom mestu!") );
        return;
        }
        
        query="UPDATE mec SET datum='" + m.getDatum() + "', vreme='" + m.getVreme() + "', lokacija='" + m.getLokacija() + "', zakazano='1' WHERE idMec='" + m.getIdMec() + "'";
        c.createStatement().executeUpdate(query);
        context.addMessage(null, new FacesMessage("Dogadjaj je zakazan za " + m.getDatum() + " u " + m.getVreme() + " na " + m.getLokacija()) );
        m.setDisable1(true);
        updateMecevi(c, m.getIdTakmicenje());
        
        }catch (SQLException ex) {
                context.addMessage(null, new FacesMessage("Izleteo!") );
                Logger.getLogger(DelegatController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            DB.getInstance().putConnection(c);   
        }
        
    }
    
    public void unesiRezultat(Mec m) throws SQLException{
    
        FacesContext context = FacesContext.getCurrentInstance();
        Connection c = DB.getInstance().getConnection();
        String query;
        ResultSet rs;
        
        try {
        
            query="SELECT * FROM mec WHERE idMec='" + m.getIdMec() + "' AND zakazano='0'";
            rs=c.createStatement().executeQuery(query);
            if(rs.next()){
            context.addMessage(null, new FacesMessage("Mec nije zakazan!") );
            return;
            }
            
        query="SELECT * FROM mec WHERE zavrseno='1' AND idMec='" + m.getIdMec() + "'";
        rs = c.createStatement().executeQuery(query);
        if(rs.next()){
        context.addMessage(null, new FacesMessage("Mec je vec zavrsen!") );
        return;
        }
        
        int pomT = m.getIdTakmicenje();
        query="SELECT idSport FROM takmicenje WHERE idTakmicenje='" + pomT + "'";
        ResultSet rs3 = c.createStatement().executeQuery(query);
        rs3.next();
        int pomS = rs3.getInt(1);
        query="SELECT naziv FROM sport WHERE idSport='" + pomS + "'";
        rs3 = c.createStatement().executeQuery(query);
        rs3.next();
        String sp = rs3.getString(1);
        
        int r1=m.getRezultat1();
        int r2=m.getRezultat2();
        
        if(sp.equals("Tenis")){
        if(r1>2 || r2>2 || (r1+r2<2) || (r1+r2>3)){
         context.addMessage(null, new FacesMessage("Nemoguc rezultat za tenis!") );
         return;   
        }
        }
        
        if(sp.equals("Odbojka")){
        if(r1>3 || r2>3 || (r1+r2<3) || (r1+r2>5)){
         context.addMessage(null, new FacesMessage("Nemoguc rezultat za odbojci!") );
         return;   
        }
        }
        
        if(sp.equals("Stoni tenis")){
        if(r1>4 || r2>4 || (r1+r2<4) || (r1+r2>7)){
         context.addMessage(null, new FacesMessage("Nemoguc rezultat za stoni tenis!") );
         return;   
        }
        }
        
        if(m.getFormat()==1){
        
            if(m.getRezultat1()==m.getRezultat2()){
            context.addMessage(null, new FacesMessage("Neresen rezultat nije moguc!") );
            return;
            }
            
            if(m.getRezultat1()>m.getRezultat2()){
                String rezultat=m.getRezultat1() + ":" + m.getRezultat2();
                query="UPDATE mec SET rezultat='" + rezultat + "', pobednik='1', zavrseno='1' WHERE idMec='" + m.getIdMec() + "'";
                c.createStatement().executeUpdate(query);
                context.addMessage(null, new FacesMessage("Pobednik 1!") );
                //return;
            }
            
            if(m.getRezultat1()<m.getRezultat2()){
                String rezultat=m.getRezultat1() + ":" + m.getRezultat2();
                query="UPDATE mec SET rezultat='" + rezultat + "', pobednik='2', zavrseno='1' WHERE idMec='" + m.getIdMec() + "'";
                c.createStatement().executeUpdate(query);
                context.addMessage(null, new FacesMessage("Pobednik 2!") );
                //return;
            }
            
            query="SELECT * FROM mec WHERE faza='" + m.getFaza() + "' AND idTakmicenje='" + m.getIdTakmicenje() + "' AND zavrseno='0'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
                context.addMessage(null, new FacesMessage("Faza nije zavrsena!") );
                return;
            }
            
            if(m.getFaza().equals("16")){
                String f="8";
                ResultSet mecevi;
                ResultSet ucesnici;
                query="SELECT idMec FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + f + "'";
                mecevi = c.createStatement().executeQuery(query);
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                ucesnici = c.createStatement().executeQuery(query);
                while(mecevi.next()){
                int u1=0 ,u2=0;
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u1 = ucesnici.getInt(1);}
                if(ucesnici.getInt(3)==2){u1 = ucesnici.getInt(2);}
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u2 = ucesnici.getInt(1);}
                if(ucesnici.getInt(3)==2){u2 = ucesnici.getInt(2);}
                
                query="UPDATE mec SET ucesnik1='" + u1 + "', ucesnik2='" + u2 + "' WHERE idMec='" + mecevi.getInt(1) + "'";
                c.createStatement().executeUpdate(query);
                
                }
                context.addMessage(null, new FacesMessage("Pocinje sledeca faza!") );
                return;
            }
            
            if(m.getFaza().equals("8")){
                String f="4";
                ResultSet mecevi;
                ResultSet ucesnici;
                query="SELECT idMec FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + f + "'";
                mecevi = c.createStatement().executeQuery(query);
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                ucesnici = c.createStatement().executeQuery(query);
                while(mecevi.next()){
                int u1=0 ,u2=0;
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u1 = ucesnici.getInt(1);}
                if(ucesnici.getInt(3)==2){u1 = ucesnici.getInt(2);}
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u2 = ucesnici.getInt(1);}
                if(ucesnici.getInt(3)==2){u2 = ucesnici.getInt(2);}
                
                query="UPDATE mec SET ucesnik1='" + u1 + "', ucesnik2='" + u2 + "' WHERE idMec='" + mecevi.getInt(1) + "'";
                c.createStatement().executeUpdate(query);
                }
                context.addMessage(null, new FacesMessage("Pocinje sledeca faza!") );
                return;
            }
            
            if(m.getFaza().equals("4")){
                String f="2";
                ResultSet mecevi;
                ResultSet ucesnici;
                query="SELECT idMec FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + f + "'";
                mecevi = c.createStatement().executeQuery(query);
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                ucesnici = c.createStatement().executeQuery(query);
                while(mecevi.next()){
                int u1=0 ,u2=0;
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u1 = ucesnici.getInt(1);}
                if(ucesnici.getInt(3)==2){u1 = ucesnici.getInt(2);}
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u2 = ucesnici.getInt(1);}
                if(ucesnici.getInt(3)==2){u2 = ucesnici.getInt(2);}
                
                query="UPDATE mec SET ucesnik1='" + u1 + "', ucesnik2='" + u2 + "' WHERE idMec='" + mecevi.getInt(1) + "'";
                c.createStatement().executeUpdate(query);
                }
                context.addMessage(null, new FacesMessage("Pocinje sledeca faza!") );
                return;
            }
            
            if(m.getFaza().equals("2")){
                
                ResultSet ucesnici;
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                ucesnici = c.createStatement().executeQuery(query);
                int u1=0 ,u2=0, u3=0, u4=0;
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u1 = ucesnici.getInt(1); u3 = ucesnici.getInt(2);}
                if(ucesnici.getInt(3)==2){u1 = ucesnici.getInt(2); u3 = ucesnici.getInt(1);}
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u2 = ucesnici.getInt(1); u4 = ucesnici.getInt(2);}
                if(ucesnici.getInt(3)==2){u2 = ucesnici.getInt(2); u4 = ucesnici.getInt(1);}
                query="UPDATE mec SET ucesnik1='" + u1 + "', ucesnik2='" + u2 + "' WHERE faza='1' AND idTakmicenje='" + m.getIdTakmicenje() + "'";
                c.createStatement().executeUpdate(query);
                query="UPDATE mec SET ucesnik1='" + u3 + "', ucesnik2='" + u4 + "' WHERE faza='3' AND idTakmicenje='" + m.getIdTakmicenje() + "'";
                c.createStatement().executeUpdate(query);
                
               context.addMessage(null, new FacesMessage("Pocinje sledeca faza!") );
                return;
            }
            
            if(m.getFaza().equals("3")){
            
                int idT = m.getIdTakmicenje();
                query="SELECT tip FROM takmicenje WHERE idTakmicenje='" + idT + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                int t = rs.getInt(1);
                
                if(t==1){
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                int pobednik = rs.getInt(3);
                        
                if(pobednik==1){
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'bronza')";
                c.createStatement().executeUpdate(query);
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + rs.getInt(1) + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET bronza = bronza + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `bronza`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                }
                else{
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(2) + "', '" + idT + "', 'bronza')";
                c.createStatement().executeUpdate(query);
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + rs.getInt(2) + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET bronza = bronza + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `bronza`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                }
                context.addMessage(null, new FacesMessage("Dodeljena je bronza, individualna") );
                return;
                }
                
                if(t==2){
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                int pobednik = rs.getInt(3);
                        
                if(pobednik==1){
                    
                int treci = rs.getInt(1);
                query="SELECT idSportista FROM sportistaekipa WHERE idEkipa='" + treci + "'";
                rs = c.createStatement().executeQuery(query);
                
                while(rs.next()){   
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'bronza')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + treci + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET bronza = bronza + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `bronza`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                }
                else{
                int treci = rs.getInt(2);
                query="SELECT idSportista FROM sportistaekipa WHERE idEkipa='" + treci + "'";
                rs = c.createStatement().executeQuery(query);
                
                while(rs.next()){   
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'bronza')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + treci + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET bronza = bronza + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `bronza`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                }
                context.addMessage(null, new FacesMessage("Dodeljena je bronza, ekipno") );
                return;
                }
                
            }
            ///////////////////////////////////////////
            if(m.getFaza().equals("1")){
            
                int idT = m.getIdTakmicenje();
                query="SELECT tip FROM takmicenje WHERE idTakmicenje='" + idT + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                int t = rs.getInt(1);
                
                if(t==1){
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                int pobednik = rs.getInt(3);
                        
                if(pobednik==1){
                    int prvi = rs.getInt(1);
                    int drugi = rs.getInt(2);
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + prvi + "', '" + idT + "', 'zlato')";
                c.createStatement().executeUpdate(query);
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + drugi + "', '" + idT + "', 'srebro')";
                c.createStatement().executeUpdate(query);
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + prvi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja1= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja1 + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET zlato = zlato + '1' WHERE nacionalnost='" + zemlja1 + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `zlato`) VALUES ('" + zemlja1 + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + drugi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja2= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja2 + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET srebro = srebro + '1' WHERE nacionalnost='" + zemlja2 + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `srebro`) VALUES ('" + zemlja2 + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                }
                else{
                int prvi = rs.getInt(2);
                    int drugi = rs.getInt(1);
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + prvi + "', '" + idT + "', 'zlato')";
                c.createStatement().executeUpdate(query);
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + drugi + "', '" + idT + "', 'srebro')";
                c.createStatement().executeUpdate(query);
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + prvi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja1= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja1 + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET zlato = zlato + '1' WHERE nacionalnost='" + zemlja1 + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `zlato`) VALUES ('" + zemlja1 + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + drugi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja2= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja2 + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET srebro = srebro + '1' WHERE nacionalnost='" + zemlja2 + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `srebro`) VALUES ('" + zemlja2 + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                }
                context.addMessage(null, new FacesMessage("Zavrseno je finale, individualno") );
                return;
                }
                
                if(t==2){
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                int pobednik = rs.getInt(3);
                        
                if(pobednik==1){
                    int prvi = rs.getInt(1);
                    int drugi = rs.getInt(2);
       
                query="SELECT idSportista FROM sportistaekipa WHERE idEkipa='" + prvi + "'";
                rs = c.createStatement().executeQuery(query);
                
                while(rs.next()){   
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'zlato')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT idSportista FROM sportistaekipa WHERE idEkipa='" + drugi + "'";
                rs = c.createStatement().executeQuery(query);
                
                while(rs.next()){   
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'srebro')";
                c.createStatement().executeUpdate(query);
                }
                
                query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + prvi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET zlato = zlato + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `zlato`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + drugi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja2= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja2 + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET srebro = srebro + '1' WHERE nacionalnost='" + zemlja2 + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `srebro`) VALUES ('" + zemlja2 + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                }
                else{
                
                    int prvi = rs.getInt(2);
                    int drugi = rs.getInt(1);
       
                query="SELECT idSportista FROM sportistaekipa WHERE idEkipa='" + prvi + "'";
                rs = c.createStatement().executeQuery(query);
                
                while(rs.next()){   
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'zlato')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT idSportista FROM sportistaekipa WHERE idEkipa='" + drugi + "'";
                rs = c.createStatement().executeQuery(query);
                
                while(rs.next()){   
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'srebro')";
                c.createStatement().executeUpdate(query);
                }
                
                query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + prvi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET zlato = zlato + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `zlato`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + drugi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja2= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja2 + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET srebro = srebro + '1' WHERE nacionalnost='" + zemlja2 + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `srebro`) VALUES ('" + zemlja2 + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                }
                context.addMessage(null, new FacesMessage("Zavrseno je finale, ekipno!") );
                return;
                }
                
            }
            
            }
            
        
        
        if(m.getFormat()==2){
        
            if(m.getRezultat1()==m.getRezultat2()){
            context.addMessage(null, new FacesMessage("Neresen rezultat nije moguc!") );
            return;
            }
            
            if(m.getRezultat1()>m.getRezultat2()){
                String rezultat=m.getRezultat1() + ":" + m.getRezultat2();
                query="UPDATE mec SET rezultat='" + rezultat + "', pobednik='1', zavrseno='1' WHERE idMec='" + m.getIdMec() + "'";
                c.createStatement().executeUpdate(query);
                context.addMessage(null, new FacesMessage("Pobednik 1!") );
                
                if(m.getFaza().equals("grupaA") || m.getFaza().equals("grupaB")){
                    query="UPDATE ekipabodovi SET brojBodova = brojBodova + '2' WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND idEkipa='" + m.getUcesnik1() + "'";
                    c.createStatement().executeUpdate(query);
                    query="UPDATE ekipabodovi SET brojBodova = brojBodova + '1' WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND idEkipa='" + m.getUcesnik2() + "'";
                    c.createStatement().executeUpdate(query);
                }
                //return;
            }
            
            if(m.getRezultat1()<m.getRezultat2()){
                String rezultat=m.getRezultat1() + ":" + m.getRezultat2();
                query="UPDATE mec SET rezultat='" + rezultat + "', pobednik='2', zavrseno='1' WHERE idMec='" + m.getIdMec() + "'";
                c.createStatement().executeUpdate(query);
                context.addMessage(null, new FacesMessage("Pobednik 2!") );
                
                if(m.getFaza().equals("grupaA") || m.getFaza().equals("grupaB")){
                    query="UPDATE ekipabodovi SET brojBodova = brojBodova + '1' WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND idEkipa='" + m.getUcesnik1() + "'";
                    c.createStatement().executeUpdate(query);
                    query="UPDATE ekipabodovi SET brojBodova = brojBodova + '2' WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND idEkipa='" + m.getUcesnik2() + "'";
                    c.createStatement().executeUpdate(query);
                }
                //return;
            }
            
            query="SELECT * FROM mec WHERE faza LIKE '%grupa%' AND idTakmicenje='" + m.getIdTakmicenje() + "' AND zavrseno='0'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
                context.addMessage(null, new FacesMessage("Faza nije zavrsena!") );
                return;
            }
            
            query="SELECT * FROM mec WHERE faza='" + m.getFaza() + "' AND idTakmicenje='" + m.getIdTakmicenje() + "' AND zavrseno='0'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
                context.addMessage(null, new FacesMessage("Faza nije zavrsena!") );
                return;
            }
            
            if(m.getFaza().equals("grupaA") || m.getFaza().equals("grupaB")){
                String f="4";
                ResultSet mecevi;
                ResultSet ucesniciA;
                ResultSet ucesniciB;
                query="SELECT idMec FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + f + "'";
                mecevi = c.createStatement().executeQuery(query);
                query="SELECT idEkipa FROM ekipabodovi WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND grupa='grupaA' ORDER BY brojBodova DESC LIMIT 4";
                ucesniciA = c.createStatement().executeQuery(query);
                query="SELECT idEkipa FROM ekipabodovi WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND grupa='grupaB' ORDER BY brojBodova DESC LIMIT 4";
                ucesniciB = c.createStatement().executeQuery(query);
                
                ucesniciA.next();
                ucesniciB.next();
                int A1=ucesniciA.getInt(1);
                int B1=ucesniciB.getInt(1);
                ucesniciA.next();
                ucesniciB.next();
                int A2=ucesniciA.getInt(1);
                int B2=ucesniciB.getInt(1);
                ucesniciA.next();
                ucesniciB.next();
                int A3=ucesniciA.getInt(1);
                int B3=ucesniciB.getInt(1);
                ucesniciA.next();
                ucesniciB.next();
                int A4=ucesniciA.getInt(1);
                int B4=ucesniciB.getInt(1);
                
                mecevi.next();
                query="UPDATE mec SET ucesnik1='" + A1 + "', ucesnik2='" + B4 + "' WHERE idMec='" + mecevi.getInt(1) + "'";
                c.createStatement().executeUpdate(query);
                mecevi.next();
                query="UPDATE mec SET ucesnik1='" + B2 + "', ucesnik2='" + A3 + "' WHERE idMec='" + mecevi.getInt(1) + "'";
                c.createStatement().executeUpdate(query);
                mecevi.next();
                query="UPDATE mec SET ucesnik1='" + B1 + "', ucesnik2='" + A4 + "' WHERE idMec='" + mecevi.getInt(1) + "'";
                c.createStatement().executeUpdate(query);
                mecevi.next();
                query="UPDATE mec SET ucesnik1='" + A2 + "', ucesnik2='" + B3 + "' WHERE idMec='" + mecevi.getInt(1) + "'";
                c.createStatement().executeUpdate(query);
                
                context.addMessage(null, new FacesMessage("Pocinje sledeca faza!") );
                return;
            }
            
            
            if(m.getFaza().equals("4")){
                String f="2";
                ResultSet mecevi;
                ResultSet ucesnici;
                query="SELECT idMec FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + f + "'";
                mecevi = c.createStatement().executeQuery(query);
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                ucesnici = c.createStatement().executeQuery(query);
                while(mecevi.next()){
                int u1=0 ,u2=0;
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u1 = ucesnici.getInt(1);}
                if(ucesnici.getInt(3)==2){u1 = ucesnici.getInt(2);}
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u2 = ucesnici.getInt(1);}
                if(ucesnici.getInt(3)==2){u2 = ucesnici.getInt(2);}
                
                query="UPDATE mec SET ucesnik1='" + u1 + "', ucesnik2='" + u2 + "' WHERE idMec='" + mecevi.getInt(1) + "'";
                c.createStatement().executeUpdate(query);
                }
                context.addMessage(null, new FacesMessage("Pocinje sledeca faza!") );
                return;
            }
            
            if(m.getFaza().equals("2")){
                
                ResultSet ucesnici;
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                ucesnici = c.createStatement().executeQuery(query);
                int u1=0 ,u2=0, u3=0, u4=0;
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u1 = ucesnici.getInt(1); u3 = ucesnici.getInt(2);}
                if(ucesnici.getInt(3)==2){u1 = ucesnici.getInt(2); u3 = ucesnici.getInt(1);}
                ucesnici.next();
                if(ucesnici.getInt(3)==1){u2 = ucesnici.getInt(1); u4 = ucesnici.getInt(2);}
                if(ucesnici.getInt(3)==2){u2 = ucesnici.getInt(2); u4 = ucesnici.getInt(1);}
                query="UPDATE mec SET ucesnik1='" + u1 + "', ucesnik2='" + u2 + "' WHERE faza='1' AND idTakmicenje='" + m.getIdTakmicenje() + "'";
                c.createStatement().executeUpdate(query);
                query="UPDATE mec SET ucesnik1='" + u3 + "', ucesnik2='" + u4 + "' WHERE faza='3' AND idTakmicenje='" + m.getIdTakmicenje() + "'";
                c.createStatement().executeUpdate(query);
                
               context.addMessage(null, new FacesMessage("Pocinje sledeca faza!") );
                return;
            }
            
            if(m.getFaza().equals("3")){
            
                int idT = m.getIdTakmicenje();
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                
                int pobednik = rs.getInt(3);
                int treci;        
                if(pobednik==1){   
                treci = rs.getInt(1);
                }
                else{
                treci = rs.getInt(2);
                }
                
                query="SELECT idSportista FROM sportistaekipa WHERE idEkipa='" + treci + "'";
                rs = c.createStatement().executeQuery(query);
                
                while(rs.next()){   
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'bronza')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + treci + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET bronza = bronza + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `bronza`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
               
                context.addMessage(null, new FacesMessage("Dodeljena je bronza, ekipno grupe") );
                return;     
            }
            
            if(m.getFaza().equals("1")){
            
                int idT = m.getIdTakmicenje();
                query="SELECT ucesnik1, ucesnik2, pobednik FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='" + m.getFaza() + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                
                int pobednik = rs.getInt(3);
                int prvi;
                int drugi;
                if(pobednik==1){   
                prvi = rs.getInt(1);
                drugi = rs.getInt(2);
                }
                else{
                prvi = rs.getInt(2);
                drugi = rs.getInt(1);
                }
                
                query="SELECT idSportista FROM sportistaekipa WHERE idEkipa='" + prvi + "'";
                rs = c.createStatement().executeQuery(query);
                
                while(rs.next()){   
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'zlato')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + prvi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET zlato = zlato + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `zlato`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
               
                query="SELECT idSportista FROM sportistaekipa WHERE idEkipa='" + drugi + "'";
                rs = c.createStatement().executeQuery(query);
                
                while(rs.next()){   
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('"
                        + rs.getInt(1) + "', '" + idT + "', 'srebro')";
                c.createStatement().executeUpdate(query);
                }
                query="SELECT nacionalnost FROM ekipa WHERE idEkipa='" + drugi + "'";
                rs = c.createStatement().executeQuery(query);
                rs.next();
                String zemlja2= rs.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja2 + "'";
                rs = c.createStatement().executeQuery(query);
                if(rs.next()){
                query="UPDATE zemljamedalje SET srebro = srebro + '1' WHERE nacionalnost='" + zemlja2 + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `srebro`) VALUES ('" + zemlja2 + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                
                context.addMessage(null, new FacesMessage("Zavrseno je finale, ekipno grupe") );
                return;     
            }
            
        }
        
        
        if(m.getFormat()==3){
        
            String rez;
            int vrednost;
            if(m.getRezultat3()!=0){
                rez=m.getRezultat1() + "," + m.getRezultat2() + "," + m.getRezultat3();
                vrednost = 10000*m.getRezultat1() + 100*m.getRezultat2() + m.getRezultat3();
            }
            else{
                rez=m.getRezultat1() + "," + m.getRezultat2();
                vrednost = 100*m.getRezultat1() + m.getRezultat2();
            }
            
            query="UPDATE mec SET rezultat='" + rez + "', zavrseno='1', pobednik='" + vrednost + "' WHERE idMec='" + m.getIdMec() + "'";
                c.createStatement().executeUpdate(query);
                context.addMessage(null, new FacesMessage("Rezultat je dodat!") );
            
            query="SELECT * FROM mec WHERE faza LIKE '%grupa%' AND idTakmicenje='" + m.getIdTakmicenje() + "' AND zavrseno='0'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
                context.addMessage(null, new FacesMessage("Faza nije zavrsena!") );
                return;
            }
            
            if(m.getFaza().equals("1")){
            
             query="SELECT * FROM mec WHERE faza='" + m.getFaza() + "' AND idTakmicenje='" + m.getIdTakmicenje() + "' AND zavrseno='0'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
                context.addMessage(null, new FacesMessage("Finale nije zavrseno!") );
                return;
            }   
            ResultSet pobednici;  
            if(jedinica==1){
            query="SELECT ucesnik1 FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='1' ORDER by pobednik ASC LIMIT 3";
            pobednici = c.createStatement().executeQuery(query);
            context.addMessage(null, new FacesMessage("Dohvatio pobednike!") );
            }
            
            else{
            query="SELECT ucesnik1 FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='1' ORDER by pobednik DESC LIMIT 3";
            pobednici = c.createStatement().executeQuery(query);
            context.addMessage(null, new FacesMessage("Dohvatio pobednike!") );
            }
            ResultSet rs2;
            String zemlja;
            
            pobednici.next();
            context.addMessage(null, new FacesMessage("Prvo mesto je" + pobednici.getInt(1)) );
            query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('" 
                    + pobednici.getInt(1) + "', '" + m.getIdTakmicenje() + "', 'zlato')";
            c.createStatement().executeUpdate(query);
            context.addMessage(null, new FacesMessage("Unet prvi") );
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + pobednici.getInt(1) + "'";
                rs2 = c.createStatement().executeQuery(query);
                rs2.next();
                context.addMessage(null, new FacesMessage("Dohvacena zemlja prvog") );
                zemlja= rs2.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs2 = c.createStatement().executeQuery(query);
                if(rs2.next()){
                query="UPDATE zemljamedalje SET zlato = zlato + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                context.addMessage(null, new FacesMessage("Update-ovana zemlja prvog") );
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `zlato`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                context.addMessage(null, new FacesMessage("Dodata zemlja prvog") );
                }
            
                pobednici.next();
            query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('" 
                    + pobednici.getInt(1) + "', '" + m.getIdTakmicenje() + "', 'srebro')";
            c.createStatement().executeUpdate(query);
            
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + pobednici.getInt(1) + "'";
                rs2 = c.createStatement().executeQuery(query);
                rs2.next();
                zemlja= rs2.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs2 = c.createStatement().executeQuery(query);
                if(rs2.next()){
                query="UPDATE zemljamedalje SET srebro = srebro + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `srebro`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                
                pobednici.next();
            query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('" 
                    + pobednici.getInt(1) + "', '" + m.getIdTakmicenje() + "', 'bronza')";
            c.createStatement().executeUpdate(query);
            
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + pobednici.getInt(1) + "'";
                rs2 = c.createStatement().executeQuery(query);
                rs2.next();
                zemlja= rs2.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs2 = c.createStatement().executeQuery(query);
                if(rs2.next()){
                query="UPDATE zemljamedalje SET bronza = bronza + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `bronza`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                
                context.addMessage(null, new FacesMessage("Zavrseno finale!") );
                return;
            
            }
            
            if(jedinica==1){
            query="SELECT ucesnik1 FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza LIKE '%grupa%' ORDER by pobednik ASC LIMIT 8";
            ResultSet ucesnici = c.createStatement().executeQuery(query);
            query="SELECT idMec FROM mec WHERE faza='1' AND idTakmicenje='" + m.getIdTakmicenje() + "'";
            ResultSet mecevi = c.createStatement().executeQuery(query);
            while(mecevi.next()){
                ucesnici.next();
            query="UPDATE mec SET ucesnik1='" + ucesnici.getInt(1) + "' WHERE idMec='" + mecevi.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            }
                context.addMessage(null, new FacesMessage("Pocinje finale!") );
                return;
            }
            
            if(jedinica==2){
            query="SELECT ucesnik1 FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza LIKE '%grupa%' ORDER by pobednik DESC LIMIT 8";
            ResultSet ucesnici = c.createStatement().executeQuery(query);
            query="SELECT idMec FROM mec WHERE faza='1' AND idTakmicenje='" + m.getIdTakmicenje() + "'";
            ResultSet mecevi = c.createStatement().executeQuery(query);
            while(mecevi.next()){
                ucesnici.next();
            query="UPDATE mec SET ucesnik1='" + ucesnici.getInt(1) + "' WHERE idMec='" + mecevi.getInt(1) + "'";
            c.createStatement().executeUpdate(query);
            }
                context.addMessage(null, new FacesMessage("Pocinje finale!") );
                return;
            }
            
        }
        
        if(m.getFormat()==4){
        
            String rez;
            int vrednost;
            rez=m.getRezultat1() + "," + m.getRezultat2() + "," + m.getRezultat3();
            vrednost = 10000*m.getRezultat1() + 100*m.getRezultat2() + m.getRezultat3();
            query="UPDATE mec SET rezultat='" + rez + "', zavrseno='1', pobednik='" + vrednost + "' WHERE idMec='" + m.getIdMec() + "'";
            c.createStatement().executeUpdate(query);
            context.addMessage(null, new FacesMessage("Rezultat je dodat!") );
            
            query="SELECT * FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND zavrseno='0'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
                context.addMessage(null, new FacesMessage("Trka nije zavrsena!") );
                return;
            }   
            
            ResultSet pobednici;
            ResultSet rs2;
            String zemlja;
            
            query="SELECT ucesnik1 FROM mec WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza='1' ORDER by pobednik ASC LIMIT 3";
            pobednici = c.createStatement().executeQuery(query);
            
                pobednici.next();
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('" 
                    + pobednici.getInt(1) + "', '" + m.getIdTakmicenje() + "', 'zlato')";
                c.createStatement().executeUpdate(query);
        
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + pobednici.getInt(1) + "'";
                rs2 = c.createStatement().executeQuery(query);
                rs2.next();
                zemlja= rs2.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs2 = c.createStatement().executeQuery(query);
                if(rs2.next()){
                query="UPDATE zemljamedalje SET zlato = zlato + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `zlato`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
            
                pobednici.next();
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('" 
                    + pobednici.getInt(1) + "', '" + m.getIdTakmicenje() + "', 'srebro')";
                c.createStatement().executeUpdate(query);
        
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + pobednici.getInt(1) + "'";
                rs2 = c.createStatement().executeQuery(query);
                rs2.next();
                zemlja= rs2.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs2 = c.createStatement().executeQuery(query);
                if(rs2.next()){
                query="UPDATE zemljamedalje SET srebro = srebro + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `srebro`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                
                pobednici.next();
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('" 
                    + pobednici.getInt(1) + "', '" + m.getIdTakmicenje() + "', 'bronza')";
                c.createStatement().executeUpdate(query);
        
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + pobednici.getInt(1) + "'";
                rs2 = c.createStatement().executeQuery(query);
                rs2.next();
                zemlja= rs2.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs2 = c.createStatement().executeQuery(query);
                if(rs2.next()){
                query="UPDATE zemljamedalje SET bronza = bronza + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `bronza`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                
                context.addMessage(null, new FacesMessage("Zavrseno finale") );
                return;
                
        }
        
        
        if(m.getFormat()==5){
                
            query="UPDATE mec SET rezultat='" + m.getRezultat1() + "', zavrseno='1' WHERE idMec='" + m.getIdMec() + "'";
                c.createStatement().executeUpdate(query);
                context.addMessage(null, new FacesMessage("Rezultat je dodat!") );
            
            query="SELECT * FROM mec WHERE faza LIKE '%grupa%' AND idTakmicenje='" + m.getIdTakmicenje() + "' AND zavrseno='0'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
                context.addMessage(null, new FacesMessage("Faza nije zavrsena!") );
                return;
            }
            
            if(m.getFaza().equals("1")){
            
            query="SELECT * FROM mec WHERE faza='" + m.getFaza() + "' AND idTakmicenje='" + m.getIdTakmicenje() + "' AND zavrseno='0'";
            rs = c.createStatement().executeQuery(query);
            if(rs.next()){
                context.addMessage(null, new FacesMessage("Finale nije zavrseno!") );
                return;
            }
                
            ResultSet pobednici;
            ResultSet rs2;
            String zemlja;
            
                query="SELECT ucesnik1, SUM(rezultat) AS zbir FROM `mec` WHERE idTakmicenje='" 
                    + m.getIdTakmicenje() + "' AND faza='1' GROUP BY ucesnik1 ORDER BY zbir DESC LIMIT 3";
                pobednici = c.createStatement().executeQuery(query);
            
                pobednici.next();
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('" 
                    + pobednici.getInt(1) + "', '" + m.getIdTakmicenje() + "', 'zlato')";
                c.createStatement().executeUpdate(query);
        
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + pobednici.getInt(1) + "'";
                rs2 = c.createStatement().executeQuery(query);
                rs2.next();
                zemlja= rs2.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs2 = c.createStatement().executeQuery(query);
                if(rs2.next()){
                query="UPDATE zemljamedalje SET zlato = zlato + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `zlato`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
            
                pobednici.next();
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('" 
                    + pobednici.getInt(1) + "', '" + m.getIdTakmicenje() + "', 'srebro')";
                c.createStatement().executeUpdate(query);
        
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + pobednici.getInt(1) + "'";
                rs2 = c.createStatement().executeQuery(query);
                rs2.next();
                zemlja= rs2.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs2 = c.createStatement().executeQuery(query);
                if(rs2.next()){
                query="UPDATE zemljamedalje SET srebro = srebro + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `srebro`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                
                pobednici.next();
                query="INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES ('" 
                    + pobednici.getInt(1) + "', '" + m.getIdTakmicenje() + "', 'bronza')";
                c.createStatement().executeUpdate(query);
        
                query="SELECT nacionalnost FROM sportista WHERE idSportista='" + pobednici.getInt(1) + "'";
                rs2 = c.createStatement().executeQuery(query);
                rs2.next();
                zemlja= rs2.getString(1);
                query="SELECT * FROM zemljamedalje WHERE nacionalnost='" + zemlja + "'";
                rs2 = c.createStatement().executeQuery(query);
                if(rs2.next()){
                query="UPDATE zemljamedalje SET bronza = bronza + '1' WHERE nacionalnost='" + zemlja + "'";
                c.createStatement().executeUpdate(query);
                }
                else{
                query="INSERT INTO `zemljamedalje` (`nacionalnost`, `bronza`) VALUES ('" + zemlja + "' , '1')";
                c.createStatement().executeUpdate(query);
                }
                
                context.addMessage(null, new FacesMessage("Zavrseno finale") );
                return;
            
            }
            
            query="SELECT ucesnik1, SUM(rezultat) AS zbir FROM `mec` WHERE idTakmicenje='" + m.getIdTakmicenje() + "' AND faza LIKE '%grupa%' GROUP BY ucesnik1 ORDER BY zbir DESC LIMIT 8";
            ResultSet ucesnici = c.createStatement().executeQuery(query);
            query="SELECT idMec FROM mec WHERE faza='1' AND idTakmicenje='" + m.getIdTakmicenje() + "'";
            ResultSet mecevi = c.createStatement().executeQuery(query);
            while(ucesnici.next()){
                for(int i=0;i<6;i++){
                    mecevi.next();
                    query="UPDATE mec SET ucesnik1='" + ucesnici.getInt(1) + "' WHERE idMec='" + mecevi.getInt(1) + "'";
                    c.createStatement().executeUpdate(query);
                }
            }
                context.addMessage(null, new FacesMessage("Pocinje finale!") );
                return;     
        }
        
        
        
        }catch (SQLException ex) {
                context.addMessage(null, new FacesMessage("Izleteo!") );
                Logger.getLogger(DelegatController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            int id = m.getIdTakmicenje();
            updateMecevi(c, id);
            DB.getInstance().putConnection(c);   
        }
        
    }
    
}
