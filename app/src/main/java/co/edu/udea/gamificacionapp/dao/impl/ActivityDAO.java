package co.edu.udea.gamificacionapp.dao.impl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.ActivitiesIndexController;
import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;
import co.edu.udea.gamificacionapp.dao.abstracts.AbstractDao;
import co.edu.udea.gamificacionapp.dao.interfaces.IActivityDAO;
import co.edu.udea.gamificacionapp.entities.core.Activity;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.factories.impl.ActivityFactory;
import co.edu.udea.gamificacionapp.services.http.PostServices;

/**
 * Created by oscargallon on 3/06/15.
 */
public class ActivityDAO extends AbstractDao implements IActivityDAO {
    public ActivityDAO(AbstractController abstractController) {
        super(abstractController);
    }

    @Override
    public void getActivities(List<CouplePostParam> couplePostParamList) {
        getAbstractController().showProgressDialog("alerta", "espere por favor");

        PostServices postServices = PostServices.getInsance(this, couplePostParamList);
        postServices.execute(getAbstractController().getActivity()
                .getString(R.string.base_url) + getAbstractController().getActivity()
                .getString(R.string.activities_index_url));
    }

    @Override
    public void proccessRestFulResponse(String stringResponse) {
        super.proccessRestFulResponse(stringResponse);

    }

    @Override
    public void processJsonObjectResponse(JSONObject jsonObject) {
        super.processJsonObjectResponse(jsonObject);
        List<Activity> activityList = ActivityFactory.getInstance().getActivitiesFromJsonObject(jsonObject,
                getAbstractController().getActivity().getApplicationContext());

        if (activityList == null) {
            showErrorMessage("error", "error en factory");
            return;
        }
        activityList = null;
        if (getAbstractController() instanceof ActivitiesIndexController) {
            ActivitiesIndexController activitiesIndexController = (ActivitiesIndexController) getAbstractController();
            activitiesIndexController.showActivities();
        }
    }


    @Override
    public void showErrorMessage(String title, String message) {
        super.showErrorMessage(title, message);
    }
}
