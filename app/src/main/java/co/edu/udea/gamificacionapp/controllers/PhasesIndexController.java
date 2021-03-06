package co.edu.udea.gamificacionapp.controllers;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;
import co.edu.udea.gamificacionapp.dao.impl.PhaseDAO;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.factories.impl.UserFactory;
import co.edu.udea.gamificacionapp.presentation.activities.PhasesIndexActivity;

/**
 * Created by oscargallon on 15/07/15.
 */
public class PhasesIndexController extends AbstractController {

    private int hasBeenCalled= 0;
    /**
     * Contructor de la clase
     *
     * @param activity actividad a la cual pertenece el controlador
     */
    public PhasesIndexController(Activity activity) {
        super(activity);
    }

    public void getPhasesFromBackEnd(String activityID, String conceptID) {
        hasBeenCalled +=1;
        List<CouplePostParam> couplePostParams = new ArrayList<CouplePostParam>();
        CouplePostParam couplePostParam = new CouplePostParam();
        couplePostParam.setKey("activity");
        couplePostParam.setParam(activityID);
        couplePostParams.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("loggedUser");
        couplePostParam.setParam(UserFactory.getInstance().getLoggedUser().getObjectID());
        couplePostParams.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("concept");
        couplePostParam.setParam(conceptID);
        couplePostParams.add(couplePostParam);

        PhaseDAO phaseDAO = new PhaseDAO(this);
        phaseDAO.getPhasesFromBackEnd(couplePostParams);

    }

    public void fillLvPhases() {
        PhasesIndexActivity phasesIndexActivity = (PhasesIndexActivity) getActivity();
        phasesIndexActivity.fillLvPhases();
    }

    public int isHasBeenCalled() {
        return hasBeenCalled;
    }

    public void setHasBeenCalled(int hasBeenCalled) {
        this.hasBeenCalled = hasBeenCalled;
    }
}
