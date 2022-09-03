package model;

/**
 * Class of object type FirstClassDivisions.
 */
public class FirstClassDivisions {
    private int divisionID;
    private String division;
    private int countryIDFK;

    /**
     * Constructor for FirstClassDivisions.
     * @param divisionID division id
     * @param division division name
     * @param countryIDFK associated country id
     */
    public FirstClassDivisions (int divisionID, String division, int countryIDFK) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryIDFK = countryIDFK;
    }

    /**
     * Gets division id
     * @return division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Gets division name
     * @return division name
     */
    public String getDivision() {
        return division;
    }

    /**
     * Gets country id
     * @return country id
     */
    public int getCountryIDFK(){
        return countryIDFK;
    }

    /**
     * Sets division id
     * @param id division id
     */
    public void setDivisionID(int id) {
        divisionID = id;
    }

    /**
     * Sets division name
     * @param div division name
     */
    public void setDivision(String div){
        division = div;
    }

    /**
     * Sets country id
     * @param id country id
     */
    public void setCountryIDFK(int id) {
        countryIDFK = id;
    }
}
