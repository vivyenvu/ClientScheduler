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

    public int getDivisionID() {
        return divisionID;
    }

    public String getDivision() {
        return division;
    }

    public int getCountryIDFK(){
        return countryIDFK;
    }

    public void setDivisionID(int id) {
        divisionID = id;
    }
    public void setDivision(String div){
        division = div;
    }
    public void setCountryIDFK(int id) {
        countryIDFK = id;
    }
}
