package model;

public class Appointments {
    private int apptID;
    private String title;
    private String desc;
    private String location;
    private String type;
    private String start; //CHANGE TO TYPE DATETIME
    private String end; //CHANGE TO TYPE DATETIME
    private int customerIDFK;
    private int userIDFK;
    private int contactIDFK;

    public Appointments (int apptID, String title, String desc, String location, String type, String start, String end,
                         int customerIDFK, int userIDFK, int contactIDFK){
        this.apptID = apptID;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerIDFK = customerIDFK;
        this.userIDFK = userIDFK;
        this.contactIDFK = contactIDFK;
    }
}
