package co.edu.udea.gamificacionapp.dao.interfaces;

import java.util.List;

import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;

/**
 * Created by oscargallon on 15/07/15.
 */
public interface IPhaseDAO  {

     void getPhasesFromBackEnd(List<CouplePostParam> couplePostParamList);

     void sendPhaseReplies(List<CouplePostParam> couplePostParamList);


}
