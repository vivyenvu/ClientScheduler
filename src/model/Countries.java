package model;

public class Countries {
    private static String country;
    private int countryID;

    public Countries (int countryID, String country){
        this.countryID = countryID;
        this.country = country;
    }

    public int getCountryID(){
        return countryID;
    }

    public static String getCountry() {
        return country;
    }

    public void setCountryID(int id){
        countryID = id;
    }

    public void setCountry(String name){
        country = name;
    }

    @Override
    public String toString(){
        return country;
    }
}
