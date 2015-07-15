package co.edu.udea.gamificacionapp.factories.interfaces;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import co.edu.udea.gamificacionapp.entities.core.Activity;

/**
 * Created by oscargallon on 3/06/15.
 */
public interface IActivityFactory {

    List<Activity> getActivitiesFromJsonObject(JSONObject jsonObject, Context context);
}
