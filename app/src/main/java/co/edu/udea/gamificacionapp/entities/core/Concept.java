package co.edu.udea.gamificacionapp.entities.core;

import java.io.Serializable;

/**
 * Created by oscargallon on 3/06/15.
 */
public class Concept implements Serializable {

    private String name;
    private String description;
    private String objectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
