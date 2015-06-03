package co.edu.udea.gamificacionapp.dao.abstracts;



import org.json.JSONArray;
import org.json.JSONObject;

import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;


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


    public void proccessRestFulResponse(String stringResp) {

    }

    public void processJsonObjectResponse(JSONObject jsonObject) {

    }

    public void processJsonArrayResponse(JSONArray jsonArray) {

    }

    public void showErrorMessage(String title, String message) {
        if (title == null && message == null)
            getAbstractController().showAlertDialogWithTwoCustomOnClickListener("Error",
                    "Error", null, null, null, null);
        else
            getAbstractController().showAlertDialogWithTwoCustomOnClickListener(title,
                    message, null, null, null, null);
    }

}