package pe.edu.utp.corpgenprojectburningencounters.entities;

import java.util.Map;

/**
 * Created by root on 10/30/17.
 */

public class User {

    private String email ;
    private boolean online ;
    private Map<String , Boolean> contact ;
    public final static boolean ONLINE = true ;
    public final static boolean OFFLINE = false ;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Map<String, Boolean> getContact() {
        return contact;
    }

    public void setContact(Map<String, Boolean> contact) {
        this.contact = contact;
    }
}
