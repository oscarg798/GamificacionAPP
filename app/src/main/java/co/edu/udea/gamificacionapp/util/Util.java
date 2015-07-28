package co.edu.udea.gamificacionapp.util;


import org.json.JSONArray;

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
            return data = null;
        }

        if (data.equals(""))
            return data = null;

        return data;


    }

    public static String createJsonArrayString(List<List<CouplePostParam>> couplePostParamList) {
        String jsonArrayString = null;
        for (List<CouplePostParam> couplePostParamList1 : couplePostParamList) {
            if (jsonArrayString == null)
                jsonArrayString = "[" + createJsonObjectString(couplePostParamList1);
            else
                jsonArrayString += "," + createJsonObjectString(couplePostParamList1);

        }
        if (jsonArrayString != null)
            jsonArrayString += "]";

        return jsonArrayString;

    }

    public static String createJsonObjectString(List<CouplePostParam> couplePostParamList) {
        String jsonObjectString = null;
        for (CouplePostParam couplePostParam : couplePostParamList) {
            if (jsonObjectString == null)
                jsonObjectString = "{\"" + couplePostParam.getKey() + "\":\"" + couplePostParam.getParam().replace("\n", " ").replace("\r", " ") + "\"";

            else
                jsonObjectString += ",\"" + couplePostParam.getKey().replace("\n", " ").replace("\r", " ") + "\":\"" + couplePostParam.getParam() + "\"";
        }

        if (jsonObjectString != null)
            jsonObjectString += "}";

        return jsonObjectString;
    }

}
