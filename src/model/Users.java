package model;

public class Users {
    private int userID;
    private String username;
    private String password;

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

    @Override
    public String toString(){
        return Integer.toString(getUserID());
    }
}
