package co.edu.udea.gamificacionapp.factories.impl;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.entities.core.Concept;
import co.edu.udea.gamificacionapp.entities.core.Phase;
import co.edu.udea.gamificacionapp.entities.core.Question;
import co.edu.udea.gamificacionapp.factories.interfaces.IPhaseFactory;

/**
 * Created by oscargallon on 15/07/15.
 */
public class PhaseFactory implements IPhaseFactory {

    private static PhaseFactory instance;

    private List<Phase> phaseList;

    private PhaseFactory() {
    }

    public static PhaseFactory getInstance() {
        if (instance == null)
            instance = new PhaseFactory();

        return instance;
    }

    @Override
    public List<Phase> getPhasesFromJsonArray(JSONArray jsonArray, Context context) {
        phaseList = new ArrayList<Phase>();
        JSONObject phaseJsonObject = null;
        Phase phase = null;
        JSONArray questionJsonArray = null;
        List<Question> questionList = null;
        JSONObject conceptJsonObject = null;

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                phaseJsonObject = jsonArray.getJSONObject(i);
                phase = new Phase();

                phase.setName(phaseJsonObject.getString(context.getString(R.string.name_key)));
                phase.setDescription(phaseJsonObject.getString(context.getString(R.string.description_key)));
                phase.setActivityID(phaseJsonObject.getString(context.getString(R.string.phase_activity_id_key)));
                phase.setStartDate(phaseJsonObject.getString(context.getString(R.string.startDate_key)));
                phase.setEndDate(phaseJsonObject.getString(context.getString(R.string.endDate_key)));
                phase.setObjectID(phaseJsonObject.getString(context.getString(R.string.object_id_key)));
                phase.setPhaseType(phaseJsonObject.getString(context.getString(R.string.phase_type_key)));
                phase.setIdentifier(phaseJsonObject.getString(context.getString(R.string.phase_identifier_key)));
                questionJsonArray = phaseJsonObject.getJSONArray(context.getString(R.string.phase_quesions_key));
                questionList = QuestionFactory.getInstance().getQuestionsFromJsonArray(questionJsonArray, context);

                if (questionList == null) {
                    phaseList = null;
                    phaseJsonObject = null;
                    phase = null;
                    questionJsonArray = null;
                    questionList = null;
                    break;

                }
                phase.setQuestionList(questionList);

                conceptJsonObject = phaseJsonObject.getJSONObject(context.getString(R.string.concept_key));
                Concept concept = ConceptFactory.getInstance().getConceptFromJsonObject(conceptJsonObject, context);
                if(concept==null){
                    phaseList = null;
                    phaseJsonObject = null;
                    phase = null;
                    questionJsonArray = null;
                    questionList = null;
                    break;

                }
                phase.setConcept(concept);

                phaseList.add(phase);


            } catch (JSONException e) {
                e.printStackTrace();
                phaseList = null;
                phaseJsonObject = null;
                phase = null;
                questionJsonArray = null;
                questionList = null;
            }
        }


        return phaseList;
    }

    public List<Phase> getPhaseList() {
        return phaseList;
    }

    public void setPhaseList(List<Phase> phaseList) {
        this.phaseList = phaseList;
    }
}
