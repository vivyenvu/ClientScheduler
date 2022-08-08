package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Users {
    private int userID;
    private String username;
    private String password;
    private static ObservableList<Users> allUsers = FXCollections.observableArrayList();

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

    public static void setAllUsers(ObservableList<Users> toSet){
        allUsers = toSet;
    }

    @Override
    public String toString(){
        return Integer.toString(getUserID());
    }
}
