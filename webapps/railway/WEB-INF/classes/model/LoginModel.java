package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class LoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "userid")
    protected String userid;

    @Column(name = "password")
    protected String password;    

    public LoginModel() {
    }

    public LoginModel(String userid, String password) {
        super();
        this.userid = userid;
        this.password = password;        
    }

    public LoginModel(int id, String userid, String password) {
        super();
        this.id = id;
        this.userid = userid;
        this.password = password;        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}