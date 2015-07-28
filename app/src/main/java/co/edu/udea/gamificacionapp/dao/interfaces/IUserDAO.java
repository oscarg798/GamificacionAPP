package co.edu.udea.gamificacionapp.dao.interfaces;

import java.util.List;

import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;

/**
 * Created by oscargallon on 28/07/15.
 */
public interface IUserDAO {


    void logInUser(List<CouplePostParam> couplePostParamList);

    void signUpUser(List<CouplePostParam> couplePostParamList);
}
