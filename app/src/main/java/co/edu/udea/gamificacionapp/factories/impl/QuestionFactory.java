package co.edu.udea.gamificacionapp.factories.impl;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.entities.core.Question;
import co.edu.udea.gamificacionapp.factories.interfaces.IQuestionFactory;

/**
 * Created by oscargallon on 15/07/15.
 */
public class QuestionFactory implements IQuestionFactory {
    private static QuestionFactory ourInstance = new QuestionFactory();

    public static QuestionFactory getInstance() {
        return ourInstance;
    }

    private QuestionFactory() {
    }

    @Override
    public List<Question> getQuestionsFromJsonArray(JSONArray jsonArray, Context context) {
        List<Question> questionList =  new ArrayList<>();
        Question question = null;
        JSONObject questionJsonObject = null;

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                questionJsonObject = jsonArray.getJSONObject(i);
                question = new Question();
                question.setObjectID(questionJsonObject.getString(context.getString(R.string.object_id_key)));
                question.setStatement(questionJsonObject.getString(context.getString(R.string.question_statement_key)));
                question.setDescription(questionJsonObject.getString(context.getString(R.string.description_key)));
                question.setPhaseID(questionJsonObject.getString(context.getString(R.string.question_phase_id_key)));
                questionList.add(question);

            } catch (JSONException e) {
                questionList = null;
                question = null;
                questionJsonObject = null;
                e.printStackTrace();
            }

        }

        return questionList;
    }
}
