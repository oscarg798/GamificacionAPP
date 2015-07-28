package co.edu.udea.gamificacionapp.controllers;

import android.app.Activity;

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
public class SignUpController extends AbstractController {
    /**
     * Contructor de la clase
     *
     * @param activity actividad a la cual pertenece el controlador
     */
    public SignUpController(Activity activity) {
        super(activity);
    }

    public void signUpUser(String fullname, String email, String IDNumber, String password,
                           String passwordConfirmation) {

        if (!Validation.validateStringThanCanNotBeEmpty(fullname)) {
            showAlertDialogWithTwoCustomOnClickListener("Alerta",
                    "El nombre debe de contener al menos 4 caracteres", null, null, null, null);
            return;

        }

        if (!Validation.validateEmail(email)) {
            showAlertDialogWithTwoCustomOnClickListener("Alerta",
                    "Debe ingresar un email correcto", null, null, null, null);
            return;
        }

        if (!Validation.validateStringWithNumbers(IDNumber)) {
            showAlertDialogWithTwoCustomOnClickListener("Alerta",
                    "La cedula solo puede contener numeros", null, null, null, null);
            return;
        }

        if (!Validation.validatePassword(password)) {
            showAlertDialogWithTwoCustomOnClickListener("Alerta",
                    "La contraseña debe tener al menos 5 caracteres", null, null, null, null);
            return;
        }

        if (!Validation.validatePassword(passwordConfirmation)) {
            showAlertDialogWithTwoCustomOnClickListener("Alerta",
                    "La contraseña debe tener al menos 5 caracteres", null, null, null, null);
            return;
        }

        if (!Validation.compareTwoStrings(password, passwordConfirmation)) {
            showAlertDialogWithTwoCustomOnClickListener("Alerta",
                    "La contraseña y la confirmación no son iguales", null, null, null, null);
            return;
        }

        List<CouplePostParam> couplePostParamList = new ArrayList<>();

        CouplePostParam couplePostParam = new CouplePostParam();
        couplePostParam.setKey("fullname");
        couplePostParam.setParam(fullname);
        couplePostParamList.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("email");
        couplePostParam.setParam(email);
        couplePostParamList.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("idNumber");
        couplePostParam.setParam(IDNumber);
        couplePostParamList.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("password");
        couplePostParam.setParam(password);
        couplePostParamList.add(couplePostParam);

        couplePostParam = new CouplePostParam();
        couplePostParam.setKey("passwordConfirmation");
        couplePostParam.setParam(passwordConfirmation);
        couplePostParamList.add(couplePostParam);

        UserDAO userDAO = new UserDAO(this);
        userDAO.signUpUser(couplePostParamList);

    }

    public void grantSignUpAccess() {
        changeActivityWithExtrasList(ActivitiesIndexActivity.class, null);
    }
}
