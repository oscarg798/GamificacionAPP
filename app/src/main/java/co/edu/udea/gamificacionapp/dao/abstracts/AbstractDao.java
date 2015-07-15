package co.edu.udea.gamificacionapp.dao.abstracts;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;
import co.edu.udea.gamificacionapp.util.ErrorMessageHandler;


/**
 * Created by oscargallon on 9/04/15.
 */

public abstract class AbstractDao {

    private AbstractController abstractController;

    protected AbstractDao(AbstractController abstractController) {
        this.abstractController = abstractController;
    }

    public AbstractController getAbstractController() {
        return abstractController;
    }

    public void setAbstractController(AbstractController abstractController) {
        this.abstractController = abstractController;
    }


    public void proccessRestFulResponse(String stringResponse) {
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
                    jsonObjectResponse = jsonObjectResponse.getJSONObject(getAbstractController().
                            getActivity().getResources().getString(R.string.result_key));
                    processJsonObjectResponse(jsonObjectResponse);

                } catch (JSONException e2) {
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
            }

        } else {
            showErrorMessage(getAbstractController().getActivity()
                            .getResources().getString(R.string.alert_default_title),
                    getAbstractController().getActivity()
                            .getResources().getString(R.string.error_default_message));
        }

    }

    public void processJsonObjectResponse(JSONObject jsonObject) {

    }

    public void processJsonArrayResponse(JSONArray jsonArray) {

    }

    public void showErrorMessage(String title, String message) {

        try{
            getAbstractController().dismissProgressDialog();
        }catch (Exception e){

        }
        if (title == null && message == null)
            getAbstractController().showAlertDialogWithTwoCustomOnClickListener("Error",
                    "Error", null, null, null, null);
        else
            getAbstractController().showAlertDialogWithTwoCustomOnClickListener(title,
                    message, null, null, null, null);
    }

}