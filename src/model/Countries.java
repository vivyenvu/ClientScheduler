package model;

/**
 * Class for object type Countries.
 */
public class Countries {
    private String country;
    private int countryID;

    /**
     * Constructor for Countries object
     * @param countryID country id
     * @param country name of country
     */
    public Countries (int countryID, String country){
        this.countryID = countryID;
        this.country = country;
    }

    /**
     * Gets country id
     * @return country id
     */
    public int getCountryID(){
        return countryID;
    }

    /**
     * Gets country name
     * @return country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country id
     * @param id country id
     */
    public void setCountryID(int id){
        countryID = id;
    }

    /**
     * Sets country name
     * @param name country name
     */
    public void setCountry(String name){
        country = name;
    }

    /**
     * Provides string name of country
     * @return name of country
     */
    @Override
    public String toString(){
        return getCountry();
    }
}
