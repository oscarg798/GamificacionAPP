package co.edu.udea.gamificacionapp.presentation.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.QuizActivityController;
import co.edu.udea.gamificacionapp.entities.core.Question;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.presentation.activities.QuizActivity;
import co.edu.udea.gamificacionapp.util.Util;


public class ResponseQuizFragment extends Fragment {


    private LinearLayout linearLayout;
    private HashMap<String, EditText> viewHashMap;
    private QuizActivity quizActivity;
    private List<Question> questionList;
    private View customView;
    private Button btnSave;
    private QuizActivityController quizActivityController;

    public ResponseQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_response_quiz, container, false);
        initComponents(view);
        return view;
    }


    public void initComponents(View view) {
        TextView lblQuestionStatament;
        TextView lblQuestionDescription;
        EditText txtResponse;
        quizActivity = (QuizActivity) getActivity();
        quizActivityController = quizActivity.getQuizActivityController();
        questionList = quizActivity.getPhase().getQuestionList();
        viewHashMap = new HashMap<>();
        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout);
        btnSave = (Button) view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Iterator it = viewHashMap.entrySet().iterator();
                List<List<CouplePostParam>> couplePostParamList = new ArrayList<>();
                List<CouplePostParam> couplePostParamsAux = null;
                CouplePostParam couplePostParam = null;
                while (it.hasNext()) {
                    Map.Entry<String, EditText> entry = (Map.Entry<String, EditText>) it.next();
                    if (entry.getValue().getText() == null || entry.getValue().getText().toString().equals("")) {
                        quizActivityController.showAlertDialogWithTwoCustomOnClickListener("Alerta",
                                "Debe responder todas las preguntas", null, null, null, null);
                        entry.getValue().requestFocus();
                        couplePostParamList = null;
                        couplePostParam = null;
                        couplePostParamsAux = null;
                        it = null;
                        break;
                    }
                    couplePostParamsAux = new ArrayList<CouplePostParam>();
                    couplePostParam = new CouplePostParam();
                    couplePostParam.setKey("reply");
                    couplePostParam.setParam(entry.getValue().getText().toString());
                    couplePostParamsAux.add(couplePostParam);

                    couplePostParam = new CouplePostParam();
                    couplePostParam.setKey("questionID");
                    couplePostParam.setParam(entry.getKey());
                    couplePostParamsAux.add(couplePostParam);
                    couplePostParamList.add(couplePostParamsAux);

                }

                if (couplePostParamList != null) {
                    String jsonArrayString = Util.createJsonArrayString(couplePostParamList);
                    Log.i("JSON", jsonArrayString);

                    quizActivityController.sendRepliesToBackEnd(quizActivity.getPhase().getObjectID(),
                            quizActivity.getPhase().getActivityID(), quizActivity.getPhase().getConcept()
                                    .getObjectId(), jsonArrayString);
                }

            }
        });

        for (Question q : questionList) {
            customView = getActivity().getLayoutInflater().inflate(R.layout.custom_response_quiz_layout, null, false);
            lblQuestionStatament = (TextView) customView.findViewById(R.id.lbl_question_statament);
            lblQuestionDescription = (TextView) customView.findViewById(R.id.lbl_question_description);
            txtResponse = (EditText) customView.findViewById(R.id.txt_response);
            lblQuestionStatament.setText(q.getStatement());
            lblQuestionDescription.setText(q.getDescription());
            viewHashMap.put(q.getObjectID(), txtResponse);
            linearLayout.addView(customView);

        }

    }


}
