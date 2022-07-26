package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDaoImpl {
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
