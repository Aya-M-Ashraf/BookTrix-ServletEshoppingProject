/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DBconnectivity.ManipulateDB;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlServlet {
    ManipulateDB manipulateDB;
    public ControlServlet(){
            manipulateDB= new ManipulateDB();  
    }

    public String checkLogin(String username, String password){
        String userRole = manipulateDB.selectRoleFromUser(username, password);
        return userRole;
    }
}
