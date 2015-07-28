package co.edu.udea.gamificacionapp.dao.impl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.PhasesIndexController;
import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;
import co.edu.udea.gamificacionapp.dao.abstracts.AbstractDao;
import co.edu.udea.gamificacionapp.dao.interfaces.IPhaseDAO;
import co.edu.udea.gamificacionapp.entities.core.Phase;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.factories.impl.PhaseFactory;
import co.edu.udea.gamificacionapp.services.http.PostServices;

/**
 * Created by oscargallon on 15/07/15.
 */
public class PhaseDAO extends AbstractDao implements IPhaseDAO {


    public PhaseDAO(AbstractController abstractController) {
        super(abstractController);
    }

    @Override
    public void getPhasesFromBackEnd(List<CouplePostParam> couplePostParamList) {
        getAbstractController().showProgressDialog("alerta", "espere por favor");
        PostServices postServices = new PostServices(this, couplePostParamList);
        postServices.execute(getAbstractController().getActivity()
                .getString(R.string.base_url) + getAbstractController().getActivity()
                .getString(R.string.phases_index_url));


    }

    @Override
    public void sendPhaseReplies(List<CouplePostParam> couplePostParamList) {
        getAbstractController().showProgressDialog("Alerta", "espere por favor");
        PostServices postServices = new PostServices(this, couplePostParamList);
        postServices.execute(getAbstractController().getActivity()
                .getString(R.string.base_url) + getAbstractController().getActivity()
                .getString(R.string.create_phase_replies_url));

    }

    @Override
    public void processJsonArrayResponse(JSONArray jsonArray) {
        super.processJsonArrayResponse(jsonArray);
        List<Phase> phaseList = PhaseFactory.getInstance().getPhasesFromJsonArray(jsonArray,
                getAbstractController().getActivity().getApplicationContext());
        if (phaseList == null) {
            showErrorMessage("error", "error en factory");
            return;
        }

        phaseList = null;

        if (getAbstractController() instanceof PhasesIndexController) {
            PhasesIndexController phasesIndexController = (PhasesIndexController) getAbstractController();
            phasesIndexController.fillLvPhases();


        }

    }

    @Override
    public void processJsonObjectResponse(JSONObject jsonObject) {
        super.processJsonObjectResponse(jsonObject);
    }
}
