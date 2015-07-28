package co.edu.udea.gamificacionapp.entities.utils;

import java.io.Serializable;

/**
 * Created by oscargallon on 18/05/15.
 */
public class CouplePostParam {

    private String key;
    private String param;
    private Serializable objectParam;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Serializable getObjectParam() {
        return objectParam;
    }

    public void setObjectParam(Serializable objectParam) {
        this.objectParam = objectParam;
    }
}
