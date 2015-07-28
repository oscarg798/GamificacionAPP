package co.edu.udea.gamificacionapp.factories.interfaces;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import co.edu.udea.gamificacionapp.entities.core.Concept;

/**
 * Created by oscargallon on 3/06/15.
 */
public interface IConceptFactory {

    List<Concept> getConceptsFromJsonArray(JSONArray jsonArray, Context context);

    Concept getConceptFromJsonObject(JSONObject jsonObject, Context context);
}
