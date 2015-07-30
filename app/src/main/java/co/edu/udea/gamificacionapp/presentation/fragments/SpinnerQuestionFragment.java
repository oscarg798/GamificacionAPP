package co.edu.udea.gamificacionapp.presentation.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.QuizActivityController;
import co.edu.udea.gamificacionapp.entities.core.Phase;
import co.edu.udea.gamificacionapp.entities.core.Question;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.presentation.activities.QuizActivity;
import co.edu.udea.gamificacionapp.presentation.adapters.CustomQuestionAdapter;
import co.edu.udea.gamificacionapp.util.Util;


public class SpinnerQuestionFragment extends Fragment {


    private Spinner spFirstQuestionsToSelect;
    private Spinner spSecondQuestionToSelect;
    private EditText txtReplyFirstQuestion;
    private EditText txtReplySecondQuestion;
    private Button btnSave;
    private String firstQuestionIDSelection;
    private String secondQuestionIDSelection;

    public SpinnerQuestionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spinner_question, container, false);
        initComponents(view);
        return view;
    }

    public void initComponents(View view) {

        btnSave = (Button) view.findViewById(R.id.btn_save);

        txtReplyFirstQuestion = (EditText) view.findViewById(R.id.txt_reply_first_question);
        txtReplySecondQuestion = (EditText) view.findViewById(R.id.txt_reply_second_question);

        spFirstQuestionsToSelect = (Spinner) view.findViewById(R.id.sp_first_question_to_select);
        spSecondQuestionToSelect = (Spinner) view.findViewById(R.id.sp_second_question_to_select);

        List<Question> questionList = ((QuizActivity) getActivity()).getPhase().getQuestionList();
        CustomQuestionAdapter adapter = new CustomQuestionAdapter(questionList, getActivity().getApplicationContext());


        spFirstQuestionsToSelect.setAdapter(adapter);
        spFirstQuestionsToSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                firstQuestionIDSelection = ((Question) adapterView.getAdapter().getItem(i)).getObjectID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spSecondQuestionToSelect.setAdapter(adapter);
        spSecondQuestionToSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                secondQuestionIDSelection = ((Question) adapterView.getAdapter().getItem(i)).getObjectID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           String firstReply = txtReplyFirstQuestion.getText().toString();
                                           String secondReply = txtReplySecondQuestion.getText().toString();
                                           if (firstReply.equals("") || secondReply.equals("")) {
                                               ((QuizActivityController) ((QuizActivity) getActivity()).getQuizActivityController())
                                                       .showAlertDialogWithTwoCustomOnClickListener("Alerta", "Debe responder" +
                                                               "todas las preguntas", null, null, null, null);
                                               return;
                                           }
                                           List<List<CouplePostParam>> couplePostParamList = new ArrayList<>();
                                           List<CouplePostParam> couplePostParamsAux = null;
                                           CouplePostParam couplePostParam = null;

                                           couplePostParamsAux = new ArrayList<>();
                                           couplePostParam = new CouplePostParam();
                                           couplePostParam.setKey("reply");
                                           couplePostParam.setParam(firstReply);
                                           couplePostParamsAux.add(couplePostParam);

                                           couplePostParam = new CouplePostParam();
                                           couplePostParam.setKey("questionID");
                                           couplePostParam.setParam(firstQuestionIDSelection);
                                           couplePostParamsAux.add(couplePostParam);

                                           couplePostParamList.add(couplePostParamsAux);

                                           couplePostParamsAux = new ArrayList<>();
                                           couplePostParam = new CouplePostParam();
                                           couplePostParam.setKey("reply");
                                           couplePostParam.setParam(secondReply);
                                           couplePostParamsAux.add(couplePostParam);

                                           couplePostParam = new CouplePostParam();
                                           couplePostParam.setKey("questionID");
                                           couplePostParam.setParam(secondQuestionIDSelection);
                                           couplePostParamsAux.add(couplePostParam);
                                           couplePostParamList.add(couplePostParamsAux);

                                           if (couplePostParamList != null) {
                                               String jsonArrayString = Util.createJsonArrayString(couplePostParamList);
                                               Log.i("JSON", jsonArrayString);
                                               ((QuizActivityController) ((QuizActivity) getActivity()).getQuizActivityController())
                                                       .sendRepliesToBackEnd(((QuizActivity) getActivity()).getPhase().getObjectID(),
                                                               ((QuizActivity) getActivity()).getPhase().getActivityID(),
                                                               ((QuizActivity) getActivity()).getPhase().getConcept().getObjectId(),
                                                               jsonArrayString, ((QuizActivity) getActivity()).getPhase()
                                                                       .getPhaseType());
                                           }


                                       }
                                   }

        );

    }


}
