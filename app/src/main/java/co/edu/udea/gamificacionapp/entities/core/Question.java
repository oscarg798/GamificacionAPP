package co.edu.udea.gamificacionapp.entities.core;

import java.io.Serializable;

/**
 * Created by oscargallon on 15/07/15.
 */
public class Question implements Serializable {

    private String statement;
    private String description;
    private String phaseID;
    private String objectID;

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhaseID() {
        return phaseID;
    }

    public void setPhaseID(String phaseID) {
        this.phaseID = phaseID;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
}
