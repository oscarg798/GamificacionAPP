package co.edu.udea.gamificacionapp.factories.interfaces;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by oscargallon on 28/07/15.
 */
public interface IUserFactory {

    void getUSerFromJsonObject(JSONObject jsonObject, Context context);

}
