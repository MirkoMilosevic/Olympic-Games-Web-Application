package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    private static DB instance = null;
    private static final int MAX_CON = 5;
    private Connection[] cons = new Connection[MAX_CON];
    private int free, first, last;

    private DB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < MAX_CON; i++) {
                cons[i] = DriverManager.getConnection("jdbc:mysql://localhost:3306/oi2016", "root", "");
            }
            free = MAX_CON;
            first = last = 0;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection tmp = null;
        if (free == 0) {
            return tmp;
        }
        free--;
        tmp = cons[first];
        first = (first + 1) % MAX_CON;
        return tmp;
    }

    public synchronized void putConnection(Connection c) {
        if (free == MAX_CON || c == null) {
            return;
        }
        free++;
        cons[last] = c;
        last = (last + 1) % MAX_CON;
    }
}
