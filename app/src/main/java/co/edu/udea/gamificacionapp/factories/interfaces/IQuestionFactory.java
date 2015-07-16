package co.edu.udea.gamificacionapp.factories.interfaces;

import android.content.Context;

import org.json.JSONArray;

import java.util.List;

import co.edu.udea.gamificacionapp.entities.core.Question;

/**
 * Created by oscargallon on 15/07/15.
 */
public interface IQuestionFactory {

    List<Question> getQuestionsFromJsonArray(JSONArray jsonArray, Context context);
}
