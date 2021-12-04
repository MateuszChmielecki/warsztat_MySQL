package pl.coderslab.entity;

import static pl.coderslab.entity.UserDao.hashPassword;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;

    public User (String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = hashPassword(password);
    }

    public  User(int id, String userName, String email, String password){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = hashPassword(password);
    }


    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    @Override
    public String toString() {
        return "Id: " + id + " User name: " + userName + " Email: " + email + " Password: " +password;
    }

}