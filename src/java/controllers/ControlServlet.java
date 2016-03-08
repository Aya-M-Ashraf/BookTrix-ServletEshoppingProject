/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DBconnectivity.DBconnection;
import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author ahmed elnazer
 */
public class ControlServlet {

    Connection connection;

    Statement pst;
    ResultSet rs;

    public ControlServlet() throws ClassNotFoundException {
        DBconnection DBConnection = new DBconnection();
        connection = DBConnection.getConnection();

    }

    public String checkLogin(String username, String password) throws SQLException {
    
        
        String sql = "select user_name , role from user where user_name='" + username + "'" + "and password='" + password + "'";
         pst = connection.createStatement();
        rs = pst.executeQuery(sql);
    
        if (rs.next()) {
            String role = rs.getString(2);
                return role;
            }
        else {
            return "wrong";
        }
    }
}
