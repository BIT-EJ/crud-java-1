package com.auth.main.dtos;

public class RegisterUserDto {
    private String user;
    private String pass;
    private String rpass;

    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getRpass() {
        return rpass;
    }
    public void setRpass(String rpass) {
        this.rpass = rpass;
    }
}