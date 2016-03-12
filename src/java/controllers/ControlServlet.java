package controllers;

import DBconnectivity.ManipulateDB;


public class ControlServlet {
    ManipulateDB manipulateDB;
    public ControlServlet(){
            manipulateDB= new ManipulateDB();  
    }

    public String checkLogin(String username, String password){
        String userRole = manipulateDB.selectRoleFromUser(username, password);
        return userRole;
    }
    public boolean doesUserNameExist(String userName){
        return manipulateDB.checkUserNameExistence(userName);
    }
    
    public boolean doesEmailExist(String email){
        return manipulateDB.checkEmailExistence(email);
    }
}
