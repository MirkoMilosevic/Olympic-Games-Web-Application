package beans;

public class TakmicenjeView{
        
        public int idTakmicenje;
        public String sport;
        public String disciplina;
        public char kategorija;

        public int getIdTakmicenje() {
            return idTakmicenje;
        }

        public void setIdTakmicenje(int idTakmicenje) {
            this.idTakmicenje = idTakmicenje;
        }

        public String getSport() {
            return sport;
        }

        public void setSport(String sport) {
            this.sport = sport;
        }

        public String getDisciplina() {
            return disciplina;
        }

        public void setDisciplina(String disciplina) {
            this.disciplina = disciplina;
        }

        public char getKategorija() {
            return kategorija;
        }

        public void setKategorija(char kategorija) {
            this.kategorija = kategorija;
        }
        
        
    }