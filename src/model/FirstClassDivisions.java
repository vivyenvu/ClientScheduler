package model;

public class FirstClassDivisions {
    private int divisionID;
    private String division;
    private int countryIDFK;

    public FirstClassDivisions (int divisionID, String division, int countryIDFK) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryIDFK = countryIDFK;
    }
}
