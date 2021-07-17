package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seats")
public class SeatsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "seatNo")
    protected int seatNo;

    @Column(name = "classId")
    protected int classId;    

    @Column(name = "isAvailable")
    protected int isAvailable;

    public SeatsModel(int seatNo, int classId, int isAvailable) {
        this.seatNo = seatNo;
        this.classId = classId;
        this.isAvailable = isAvailable;
    }

    public SeatsModel(int id, int seatNo, int classId, int isAvailable) {
        this.id = id;
        this.seatNo = seatNo;
        this.classId = classId;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }    
    
}