package co.edu.udea.gamificacionapp.factories.impl;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.entities.core.Activity;
import co.edu.udea.gamificacionapp.entities.core.Concept;
import co.edu.udea.gamificacionapp.factories.interfaces.IActivityFactory;

/**
 * Created by oscargallon on 3/06/15.
 */
public class ActivityFactory implements IActivityFactory {

    private List<Activity> activityList;
    public static ActivityFactory instance;

    private ActivityFactory() {

    }

    public static ActivityFactory getInstance() {
        if (instance == null)
            instance = new ActivityFactory();
        return instance;
    }

    @Override
    public List<Activity> getActivitiesFromJsonObject(JSONObject jsonObject, Context context) {
        activityList = new ArrayList<Activity>();
        JSONObject activityJsonObject = null;
        JSONObject conceptJsonObject = null;
        JSONArray activitiesIDJsonArray = null;
        JSONObject jsonObjectAux = null;
        JSONArray conceptsJsonArray;
        Concept concept = null;
        Activity activity = null;
        List<Concept> conceptList = null;
        String ID = null;
        activitiesIDJsonArray = jsonObject.names();

        if (activitiesIDJsonArray == null)
            return null;

        for (int i = 0; i < activitiesIDJsonArray.length(); i++) {
            try {
                ID = activitiesIDJsonArray.get(i).toString();
                jsonObjectAux = jsonObject.getJSONObject(ID);
                activityJsonObject = jsonObjectAux.getJSONObject("activity");
                activity = new Activity();
                activity.setIdNumber(activityJsonObject.getString(context.getString(R.string.id_number_key)));
                activity.setName(activityJsonObject.getString(context.getString(R.string.name_key)));
                activity.setStartDate(activityJsonObject.getString(context.getString(R.string.startDate_key)));
                activity.setEndDate(activityJsonObject.getString(context.getString(R.string.endDate_key)));
                activity.setDescription(activityJsonObject.getString(context.getString(R.string.description_key)));
                activity.setObjectId(activityJsonObject.getString(context.getString(R.string.object_id_key)));
                conceptList = new ArrayList<Concept>();
                conceptsJsonArray = jsonObjectAux.getJSONArray("concepts");
                for (int j = 0; j < conceptsJsonArray.length(); j++) {
                    conceptJsonObject = conceptsJsonArray.getJSONObject(j);
                    concept = new Concept();
                    concept.setName(conceptJsonObject.getString(context.getString(R.string.name_key)));
                    concept.setDescription(conceptJsonObject.getString(context.getString(R.string.description_key)));
                    concept.setObjectId(conceptJsonObject.getString(context.getString(R.string.object_id_key)));
                    conceptList.add(concept);
                }
                activity.setConceptList(conceptList);
                activityList.add(activity);


            } catch (JSONException e) {
                e.printStackTrace();
                activityList = null;
                jsonObject = null;
                concept = null;
                activity = null;
                conceptList = null;
                return activityList = null;
            }
        }
        return activityList;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }
}
