package co.edu.udea.gamificacionapp.factories.impl;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.entities.core.User;
import co.edu.udea.gamificacionapp.factories.interfaces.IUserFactory;

/**
 * Created by oscargallon on 28/07/15.
 */
public class UserFactory implements IUserFactory {

    private final static UserFactory instance = new UserFactory();
    private User loggedUser;


    private UserFactory() {

    }

    public static UserFactory getInstance() {
        return instance;

    }


    @Override
    public void getUSerFromJsonObject(JSONObject jsonObject, Context context) {
        loggedUser = new User();
        try {

            loggedUser.setFullname(jsonObject.getString(context.getString(R.string.fullname_key)));
            loggedUser.setEmail(jsonObject.getString(context.getString(R.string.email_key)));
            loggedUser.setIdNumber(jsonObject.getString(context.getString(R.string.id_number_key)));
            loggedUser.setObjectID(jsonObject.getString(context.getString(R.string.object_id_key)));


        } catch (JSONException e) {
            loggedUser = null;
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}
