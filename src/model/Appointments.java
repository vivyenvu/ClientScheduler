package model;

import java.time.LocalDateTime;

public class Appointments {
    private int apptID;
    private String title;
    private String desc;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerIDFK;
    private int userIDFK;
    private int contactIDFK;

    public Appointments (int apptID, String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end,
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
