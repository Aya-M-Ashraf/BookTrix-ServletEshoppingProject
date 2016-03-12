package Beans;

import java.io.Serializable;


public class User implements Serializable{
    private String email;
    private String userName;
    private String password;
    private double creditLimit;
    private String job;
    private String address;
    private String profilePicUrl;
    private String role;

    public User() {
    }

    public User(String email, String userName, String password, double creditLimit, String job, String address, String profilePicUrl, String role) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.creditLimit = creditLimit;
        this.job = job;
        this.address = address;
        this.profilePicUrl = profilePicUrl;
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
