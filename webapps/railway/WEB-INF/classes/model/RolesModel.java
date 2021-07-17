package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RolesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "userid")
    protected String userid;

    @Column(name = "rolename")
    protected String rolename;    

    public RolesModel() {
    }

    public RolesModel(String userid, String rolename) {
        super();
        this.userid = userid;
        this.rolename = rolename;        
    }

    public RolesModel(int id, String userid, String rolename) {
        super();
        this.id = id;
        this.userid = userid;
        this.rolename = rolename;        
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

    public String Rolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}