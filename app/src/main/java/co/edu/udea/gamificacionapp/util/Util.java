package co.edu.udea.gamificacionapp.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;

/**
 * Created by oscargallon on 22/05/15.
 */
public class Util {


    public static String organizePostServicesParametres(List<CouplePostParam> couplePostParams) {

        if (couplePostParams == null)
            return null;

        String data = "";
        try {
            for (int i = 0; i < couplePostParams.size(); i++) {
                CouplePostParam couplePostParam = couplePostParams.get(i);
                if (i > 0)
                    data += "&";

                data += URLEncoder.encode(couplePostParam.getKey(), "UTF-8")
                        + "=" + URLEncoder.encode(couplePostParam.getParam(), "UTF-8");

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return data=null;
        }

        if(data.equals(""))
            return data= null;

        return  data;


    }

}
