package com.example.vehiclesharing.model;

public interface IUser {
    public void setCredits(float credits);
    public float getCredits();
    public String getEmail();
    public void setEmail(String email);
    public String getPassword();
    public void setPassword(String password);
    public String getUserType();
    public void setUserType(String userType);
    public String getFirst_name();
    public void setFirst_name(String first_name);
    public String getLast_name();
    public void setLast_name(String last_name);
    public int getId();
    public void setId(int id);
}
