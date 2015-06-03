package co.edu.udea.gamificacionapp.dao.impl;

import org.json.JSONArray;
import org.json.JSONException;
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
import co.edu.udea.gamificacionapp.util.ErrorMessageHandler;

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

        PostServices postServices = new PostServices(this, couplePostParamList);
        postServices.execute(getAbstractController().getActivity()
                .getString(R.string.base_url) + getAbstractController().getActivity()
                .getString(R.string.activities_index_url));
    }

    @Override
    public void proccessRestFulResponse(String stringResponse) {
        super.proccessRestFulResponse(stringResponse);
        getAbstractController().dismissProgressDialog();

        JSONArray jsonArrayResponse = null;
        JSONObject jsonObjectResponse = null;
        /**
         * Obtenemos el primer caracter de la respuesta
         */
        Character c = stringResponse.charAt(0);
        /**
         * Verificamos si es un corchete o una llave para saber si esta
         * es un arreglo o un simple objeto
         */
        if (c.toString().equals("[")) {
            try {
                jsonArrayResponse = new JSONArray(stringResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (c.toString().equals("{")) {
            try {
                jsonObjectResponse = new JSONObject(stringResponse);
                jsonArrayResponse = jsonObjectResponse.getJSONArray(getAbstractController().
                        getActivity().getResources().getString(R.string.result_key));

                processJsonArrayResponse(jsonArrayResponse);

            } catch (JSONException e) {
                e.printStackTrace();
                try {
                    String message = jsonObjectResponse.getString(getAbstractController().getActivity()
                            .getResources().getString(R.string.message_key));

                    getAbstractController().showAlertDialogWithTwoCustomOnClickListener(
                            getAbstractController().getActivity().getResources().getString(R.string.alert_default_title),
                            ErrorMessageHandler.getMessageFromCode(message), null, null, null, null);

                } catch (JSONException e1) {
                    e1.printStackTrace();
                    getAbstractController().showAlertDialogWithTwoCustomOnClickListener(
                            getAbstractController().getActivity().getResources()
                                    .getString(R.string.alert_default_title),
                            getAbstractController().getActivity().getResources()
                                    .getString(R.string.error_default_message), null, null,
                            null, null);
                }
            }

        } else {
            showErrorMessage(getAbstractController().getActivity()
                            .getResources().getString(R.string.alert_default_title),
                    getAbstractController().getActivity()
                            .getResources().getString(R.string.error_default_message));
        }
    }

    @Override
    public void processJsonArrayResponse(JSONArray jsonArray) {
        super.processJsonArrayResponse(jsonArray);
        List<Activity> activityList = ActivityFactory.getInstance().getActivitiesFromJsonArray(jsonArray,
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
