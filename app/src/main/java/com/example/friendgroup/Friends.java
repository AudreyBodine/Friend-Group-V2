package com.example.friendgroup;

public class Friends {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Friends (int newId, String newFirstName, String newLastName, String newEmail){
        setID(newId);
        setFirstName(newFirstName);
        setLastName(newLastName);
        setEmail(newEmail);
    }

    private void setEmail(String newEmail) {
        email = newEmail;
    }

    private void setLastName(String newLastName) {
        lastName = newLastName;
    }

    private void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    private void setID(int newId) {
        id = newId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String toString(){
        return id + "; " + firstName + "; " + lastName + "; " + email;
    }

    public int getId() {
        return id;
    }
}
