package com.auth.main.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  private String name;
  
  private String pass;
  
  private long join_date;
  
  public User () {}
  
  public User(String name, String pass, long join_date) {
    this.name = name;
    this.pass = pass;
    this.join_date = join_date;
  }

  public Integer getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }

  public String getPass(){
    return this.pass;
  }
  public void setPass(String pass) {this.pass = pass;}

  public long getJoinDate() {
    return this.join_date;
  }
}
