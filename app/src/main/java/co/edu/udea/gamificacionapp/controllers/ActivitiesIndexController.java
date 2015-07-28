package co.edu.udea.gamificacionapp.controllers;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;
import co.edu.udea.gamificacionapp.dao.impl.ActivityDAO;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.factories.impl.UserFactory;
import co.edu.udea.gamificacionapp.presentation.activities.ActivitiesIndexActivity;

/**
 * Created by oscargallon on 3/06/15.
 */
public class ActivitiesIndexController  extends AbstractController{
    /**
     * Contructor de la clase
     *
     * @param activity actividad a la cual pertenece el controlador
     */
    public ActivitiesIndexController(Activity activity) {
        super(activity);
    }

    public void getActivities(){
        ActivityDAO activityDAO = new ActivityDAO(this);
        List<CouplePostParam> couplePostParams = new ArrayList<>();
        CouplePostParam couplePostParam = new CouplePostParam();
        couplePostParam.setKey("loggedUser");
        couplePostParam.setParam(UserFactory.getInstance().getLoggedUser().getObjectID());
        couplePostParams.add(couplePostParam);
        activityDAO.getActivities(couplePostParams);
    }

    public void showActivities(){
        ActivitiesIndexActivity activitiesIndexActivity = (ActivitiesIndexActivity) getActivity();
        activitiesIndexActivity.showActivitiesList();
    }




}
