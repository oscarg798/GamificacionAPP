package co.edu.udea.gamificacionapp.dao.interfaces;

import java.util.List;

import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;

/**
 * Created by oscargallon on 3/06/15.
 */
public interface IActivityDAO {

    void getActivities(List<CouplePostParam> couplePostParamList);
}
