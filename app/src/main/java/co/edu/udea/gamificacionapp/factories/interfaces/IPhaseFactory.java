package co.edu.udea.gamificacionapp.factories.interfaces;

import android.content.Context;

import org.json.JSONArray;

import java.util.List;

import co.edu.udea.gamificacionapp.entities.core.Phase;

/**
 * Created by oscargallon on 14/07/15.
 */
public interface IPhaseFactory {

    List<Phase> getPhasesFromJsonArray(JSONArray jsonArray,Context context);


}
