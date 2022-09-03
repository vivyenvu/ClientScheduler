package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class for object type Users.
 */
public class Users {
    private int userID;
    private String username;
    private String password;
    private static ObservableList<Users> allUsers = FXCollections.observableArrayList();

    /**
     * Constructor for Users.
     * @param userID user id
     * @param username user name
     * @param password user password
     */
    public Users(int userID, String username, String password){
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    public static ObservableList<Users> getAllUsers() {
        return allUsers;
    }

    /**
     * @param id the ID set for the user
     */
    public void setUserID(int id){
        userID = id;
    }

    /**
     * @param name the username to set
     */
    public void setUsername(String name) {
        username = name;
    }

    /**
     * @param pw to set for the password
     */
    public void setPassword (String pw) {
        password = pw;
    }

    /**
     * Sets a list of users to be allUsers
     * @param toSet list of Users to be set to allUsers
     */
    public static void setAllUsers(ObservableList<Users> toSet){
        allUsers = toSet;
    }

    /**
     * Provides user id
     * @return user id
     */
    @Override
    public String toString(){
        return Integer.toString(getUserID());
    }
}
