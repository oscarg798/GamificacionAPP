package co.edu.udea.gamificacionapp.factories.impl;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.entities.core.Concept;
import co.edu.udea.gamificacionapp.factories.interfaces.IConceptFactory;

/**
 * Created by oscargallon on 3/06/15.
 */
public class ConceptFactory implements IConceptFactory {

    private List<Concept> conceptList;

    private static ConceptFactory ourInstance = new ConceptFactory();

    public static ConceptFactory getInstance() {
        return ourInstance;
    }

    private ConceptFactory() {


    }

    @Override
    public List<Concept> getConceptsFromJsonArray(JSONArray jsonArray, Context context) {
        Concept concept = null;
        JSONObject jsonObject = null;
        conceptList = new ArrayList<Concept>();
        for (int j = 0; j < jsonArray.length(); j++) {
            try {
                jsonObject = jsonArray.getJSONObject(j);
                concept = new Concept();
                concept.setName(jsonObject.getString(context.getString(R.string.name_key)));
                concept.setDescription(jsonObject.getString(context.getString(R.string.description_key)));
                concept.setObjectId(jsonObject.getString(context.getString(R.string.concept_key)));
                conceptList.add(concept);

            } catch (JSONException e) {
                e.printStackTrace();
                jsonObject = null;
                jsonArray = null;
                concept = null;
                return conceptList = null;
            }


        }
        return conceptList;

    }

    @Override
    public Concept getConceptFromJsonObject(JSONObject jsonObject, Context context) {
        Concept concept = null;

        try {
            concept = new Concept();
            concept.setName(jsonObject.getString(context.getString(R.string.name_key)));
            concept.setDescription(jsonObject.getString(context.getString(R.string.description_key)));
            concept.setObjectId(jsonObject.getString(context.getString(R.string.object_id_key)));

        } catch (JSONException e) {
            concept = null;
        }

        return concept;
    }


}
