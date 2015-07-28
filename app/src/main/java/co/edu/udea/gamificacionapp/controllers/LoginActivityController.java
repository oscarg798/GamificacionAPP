package co.edu.udea.gamificacionapp.controllers;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;
import co.edu.udea.gamificacionapp.dao.impl.UserDAO;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.presentation.activities.ActivitiesIndexActivity;
import co.edu.udea.gamificacionapp.util.Validation;

/**
 * Created by oscargallon on 28/07/15.
 */
public class LoginActivityController extends AbstractController {


    private String username;
    private String password;

    /**
     * Contructor de la clase
     *
     * @param activity actividad a la cual pertenece el controlador
     */
    public LoginActivityController(Activity activity) {
        super(activity);
    }

    public void logInUser(String username, String password) {

        if (!Validation.validateStringThanCanNotBeEmpty(username)) {
            showAlertDialogWithTwoCustomOnClickListener("Alerta",
                    "El nombre de usuario no puede ser menor a 5 caracteres", null, null, null, null);
            return;
        }

        if (!Validation.validatePassword(password)) {
            showAlertDialogWithTwoCustomOnClickListener("Alerta",
                    "La contraseña no puede ser menor a 5 caracteres", null, null, null, null);
            return;

        }

        this.username = username;
        this.password = password;

        List<CouplePostParam> couplePostParamList = new ArrayList<>();
        CouplePostParam couplePostParam = new CouplePostParam();
        couplePostParam.setKey("idNumber");
        couplePostParam.setParam(username);
        couplePostParamList.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("password");
        couplePostParam.setParam(password);
        couplePostParamList.add(couplePostParam);

        UserDAO userDAO = new UserDAO(this);
        userDAO.logInUser(couplePostParamList);


    }

    public void grantLogInAccess() {
        SharedPreferences.Editor editor = getActivity().getPreferences(0).edit();

        editor.putString("idNumber", username);
        editor.commit();

        editor.putString("password", password);
        editor.commit();

        changeActivityWithExtrasList(ActivitiesIndexActivity.class, null);

    }
}
