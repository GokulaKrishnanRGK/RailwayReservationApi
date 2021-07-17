package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userdetails")
public class RegisterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "userid")
    protected String userid;

    @Column(name = "name")
    protected String name;

    @Column(name = "phoneno")
    protected String phoneno;

    @Column(name = "dob")
    protected String dob;

    public RegisterModel() {
    }

    public RegisterModel(String userid, String name, String phoneno, String dob) {
        super();
        this.userid = userid;
        this.name = name;
        this.phoneno = phoneno;
        this.dob = dob;
    }

    public RegisterModel(int id, String userid, String name, String phoneno, String dob) {
        super();
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.phoneno = phoneno;
        this.dob = dob;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    
}