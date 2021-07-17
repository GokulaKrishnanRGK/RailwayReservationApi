package model;

public class TicketsModel {
    private int id;
    private int classId;
    private String className;
    private int available;
    private int waiting;        

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getClassId() {
        return classId;
    }
    public void setClassId(int classId) {
        this.classId = classId;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public int getAvailable() {
        return available;
    }
    public void setAvailable(int available) {
        this.available = available;
    }
    public int getWaiting() {
        return waiting;
    }
    public void setWaiting(int waiting) {
        this.waiting = waiting;
    }


}
