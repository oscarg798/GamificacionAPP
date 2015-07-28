package co.edu.udea.gamificacionapp.entities.core;

import java.io.Serializable;

/**
 * Created by oscargallon on 28/07/15.
 */
public class User implements Serializable {

    private String fullname;
    private String email;
    private String idNumber;
    private String objectID;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
}
