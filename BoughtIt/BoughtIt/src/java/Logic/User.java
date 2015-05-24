/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

/**
 *
 * @author Tal
 */
public class User {
    String password ;
    String firstName ;
    String lastName ;
    String userName ;
    String country ;
    java.sql.Date birthDay ;

    public User(String password, String firstName, String lastName, String userName, String country, java.sql.Date birthDay) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.country = country;
        this.birthDay = birthDay;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBirthDay(java.sql.Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getCountry() {
        return country;
    }

    public java.sql.Date getBirthDay() {
        return birthDay;
    }
    
    
}
