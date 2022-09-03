package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Pulls Countries information from the database.
 */
public class CountryDaoImpl {

    /**
     * Populates a list of all countries and their information from the database.
     * @return list of all countries
     * @throws SQLException
     */
    public static ObservableList<Countries> getAllCountries() throws SQLException {
        ObservableList<Countries> allCountries = FXCollections.observableArrayList();
        ResultSet result = Query.getRS("select * from countries");
        while(result.next()){
            int countryID = result.getInt("Country_ID");
            String country = result.getString("Country");
            Countries countryResult = new Countries(countryID, country);
            allCountries.add(countryResult);
        }
        return allCountries;
    }
}
