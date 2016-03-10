package DBconnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed Ashraf
 */
public class DBconnection {
     private Connection connection;
    public DBconnection(){
   
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookstore", "root", "root");
        } catch (SQLException ex) {
            ex.printStackTrace();

        } catch (ClassNotFoundException ex) {
             Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public Connection getConnection(){
        return connection;
    }
     
}
