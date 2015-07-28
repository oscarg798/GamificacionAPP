package co.edu.udea.gamificacionapp.controllers;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;
import co.edu.udea.gamificacionapp.dao.impl.PhaseDAO;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.factories.impl.UserFactory;

/**
 * Created by oscargallon on 28/07/15.
 */
public class QuizActivityController extends AbstractController {
    /**
     * Contructor de la clase
     *
     * @param activity actividad a la cual pertenece el controlador
     */
    public QuizActivityController(Activity activity) {
        super(activity);
    }

    public void sendRepliesToBackEnd(String phaseID, String activityID, String conceptID
            , String jsonArrayReplies) {

        List<CouplePostParam> couplePostParams = new ArrayList<>();


        CouplePostParam couplePostParam = new CouplePostParam();
        couplePostParam.setKey("phase");
        couplePostParam.setParam(phaseID);
        couplePostParams.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("loggedUser");
        couplePostParam.setParam(UserFactory.getInstance().getLoggedUser().getObjectID());
        couplePostParams.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("activity");
        couplePostParam.setParam(activityID);
        couplePostParams.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("concept");
        couplePostParam.setParam(conceptID);
        couplePostParams.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("replies");
        couplePostParam.setParam(jsonArrayReplies);
        couplePostParams.add(couplePostParam);

        PhaseDAO phaseDAO = new PhaseDAO(this);
        phaseDAO.sendPhaseReplies(couplePostParams);

    }
}
