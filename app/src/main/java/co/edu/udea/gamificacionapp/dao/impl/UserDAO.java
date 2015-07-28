package co.edu.udea.gamificacionapp.dao.impl;

import org.json.JSONObject;

import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.LoginActivityController;
import co.edu.udea.gamificacionapp.controllers.SignUpController;
import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;
import co.edu.udea.gamificacionapp.dao.abstracts.AbstractDao;
import co.edu.udea.gamificacionapp.dao.interfaces.IUserDAO;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.factories.impl.UserFactory;
import co.edu.udea.gamificacionapp.presentation.activities.LoginActivity;
import co.edu.udea.gamificacionapp.presentation.activities.SignUpActivity;
import co.edu.udea.gamificacionapp.services.http.PostServices;

/**
 * Created by oscargallon on 28/07/15.
 */
public class UserDAO extends AbstractDao implements IUserDAO {

    public UserDAO(AbstractController abstractController) {
        super(abstractController);
    }


    @Override
    public void logInUser(List<CouplePostParam> couplePostParamList) {
        getAbstractController().showProgressDialog("alerta", "espere por favor");

        PostServices postServices = new PostServices(this, couplePostParamList);
        postServices.execute(getAbstractController().getActivity()
                .getString(R.string.base_url) + getAbstractController().getActivity()
                .getString(R.string.log_in_url));
    }

    @Override
    public void signUpUser(List<CouplePostParam> couplePostParamList) {
        getAbstractController().showProgressDialog("alerta", "espere por favor");

        PostServices postServices = new PostServices(this, couplePostParamList);
        postServices.execute(getAbstractController().getActivity()
                .getString(R.string.base_url) + getAbstractController().getActivity()
                .getString(R.string.sign_up__url));
    }

    @Override
    public void processJsonObjectResponse(JSONObject jsonObject) {
        super.processJsonObjectResponse(jsonObject);
        UserFactory.getInstance().getUSerFromJsonObject(jsonObject,
                getAbstractController().getActivity().getApplicationContext());
        if (UserFactory.getInstance().getLoggedUser() != null) {
            if (getAbstractController() instanceof SignUpController) {
                ((SignUpController) getAbstractController()).grantSignUpAccess();
            }
            else  if(getAbstractController() instanceof LoginActivityController)
                ((LoginActivityController) getAbstractController()).grantLogInAccess();
        } else {
            showErrorMessage("Error", "No se puedo registrar el usuario");
        }

    }
}
