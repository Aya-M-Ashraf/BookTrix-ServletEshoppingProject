package DBconnectivity;

import java.sql.Connection;

/**
 *
 * @author Ahmed Ashraf
 */
public class ManipulateDB {
    Connection connection;

    public ManipulateDB() throws ClassNotFoundException {
        DBconnection DBConnection = new DBconnection();
        connection = DBConnection.getConnection();
        
        
    }
    
    
}
