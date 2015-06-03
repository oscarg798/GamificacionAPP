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
    public List<Activity> getActivitiesFromJsonArray(JSONArray jsonArray, Context context) {
        activityList = new ArrayList<Activity>();
        JSONObject jsonObject = null;
        JSONObject conceptJsonObject = null;
        JSONArray conceptArray = null;
        Concept concept = null;
        Activity activity = null;
        List<Concept> conceptList = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
                activity = new Activity();
                activity.setIdNumber(jsonObject.getInt(context.getString(R.string.id_number_key)));
                activity.setName(jsonObject.getString(context.getString(R.string.name_key)));
                activity.setStartDate(jsonObject.getString(context.getString(R.string.startDate_key)));
                activity.setEndDate(jsonObject.getString(context.getString(R.string.endDate_key)));
                activity.setDescription(jsonObject.getString(context.getString(R.string.description_key)));
                activity.setObjectId(jsonObject.getString(context.getString(R.string.object_id_key)));
                conceptArray = jsonObject.getJSONArray(context.getString(R.string.activity_has_concept_key));

                conceptList = new ArrayList<Concept>();
                for (int j = 0; j < conceptArray.length(); j++) {
                    conceptJsonObject = conceptArray.getJSONObject(i);

                    concept = new Concept();
                   // concept.setName(conceptJsonObject.getString(context.getString(R.string.name_key)));
                  //  concept.setDescription(conceptJsonObject.getString(context.getString(R.string.description_key)));
                    concept.setObjectId(conceptJsonObject.getString(context.getString(R.string.concept_key)));
                    conceptList.add(concept);

                }

                activity.setConceptList(conceptList);
                activityList.add(activity);


            } catch (JSONException e) {
                e.printStackTrace();
                activityList = null;
                jsonObject = null;
                conceptJsonObject = null;
                conceptArray = null;
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
