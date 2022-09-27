package com.example.birch;

/**
 * <User Account Information Model Class>
 * Last Updated: 09/26/2022 by Hyeonjun An (Daniel An)
 * Currently : Testing Purpose.
 */
public class UserAccount {
    private String idToken; // Firebase UID (Unique Token Info)
    private String emailID;
    private String password;

    public UserAccount() {
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
