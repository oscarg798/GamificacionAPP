package co.edu.udea.gamificacionapp.services.http;

import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import co.edu.udea.gamificacionapp.dao.abstracts.AbstractDao;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.util.Util;
import co.edu.udea.gamificacionapp.util.Validation;


/**
 * Created by oscargallon on 4/05/15.
 */
public class PostServices extends AsyncTask<String, String, Boolean> {


    /**
     * En este string se alamacenara la respuesta del servidor;
     */
    private String stringRes = null;

    /**
     * Mensaje cuando ocurra un erro en el formato de la url
     */
    private final String URL_ERROR_FORMAT = "La url a la  que esta intentando acceder es incorrecta";

    /**
     * Mensaje cuando ocuraa un error en la conexion
     */
    private final String CONNECTION_ERROR = "Error al intentar contactar el Servidor";

    /**
     * Mensaje cuando ocurra un error desconocido
     */
    private final String UNKNOW_ERROR = "Error desconocido Intente mas Tarde";

    /**
     * Variabe para almacenar el error que ocurra
     */
    private String ERROR_MESSAGE;


    /**
     * Lista de parametros a enviar en la peticion
     */
    private static List<CouplePostParam> paramsList;

    /**
     * Dao que invoca esta clase
     */
    private static AbstractDao abstractDao;

    /**
     * Intancia del servicio
     */
    public static PostServices instance;

    private PostServices() {

    }

    public static PostServices getInsance(AbstractDao mAbstractDao, List<CouplePostParam> mParamsList) {
        if (instance == null)
            instance = new PostServices();

        abstractDao = mAbstractDao;
        paramsList = mParamsList;

        return instance;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        //Cojemos la url a la que se realizara la peticion
        String urlString = strings[0];
        Log.i("Entre", "me registrare");

        //Se evalua que la url sea valida
        if (!Validation.validateURl(urlString)) {
            ERROR_MESSAGE = URL_ERROR_FORMAT;
            return false;

        }
        try {


            /**
             * Damos formato a los parametros para enviarlos por
             * la url al backend
             */
            String data = Util.organizePostServicesParametres(paramsList);

            /**
             * Si hay datos que enviar los  añadimso a la url
             */
            if (data != null)
                urlString += "?" + data;

            /**
             * Creamos un nuevo objeto de tipo url
             */
            URL url = new URL(urlString);

            /**
             * Creamos un nuevo objeto de tipo http con la url de destino
             */
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            /**
             * Añadimos un tiempo de conexion maximo
             */
            httpURLConnection.setConnectTimeout(20000);

            /**
             * Indicamos que es post ??
             */
            httpURLConnection.setDoOutput(true);

            /**
             * Le decimos que no hay iteraccion con el usuario
             */
            httpURLConnection.setAllowUserInteraction(false);

            /**
             * Añadimos tiempo maximo de lectura
             */
            httpURLConnection.setReadTimeout(10000);

            /**
             * LE decimos que el tipo de conexion es POST
             */
            httpURLConnection.setRequestMethod("POST");

            /**
             * Le indicamos que el contenido es de tipo json
             */
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            /**
             * nos conectamos
             */
            httpURLConnection.connect();

            /**
             * Obtenemos el status de la conexion
             */
            int status = httpURLConnection.getResponseCode();

            switch (status) {

                /**
                 * Si el status es 200 o 201 que indican que everything salio bien procesamos la respuesta
                 */
                case 200:
                case 201: {
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    stringRes = sb.toString();
                    break;
                }
                default: {
                    /**
                     * De lo contrario mostramos mensaje de error
                     */
                    ERROR_MESSAGE = CONNECTION_ERROR;
                    return false;
                }
            }


        } catch (Exception ex) {
            Log.i("Debug", "error: " + ex.getMessage(), ex);
            ERROR_MESSAGE = UNKNOW_ERROR + "; " + ex;
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean)
            getAbstractDao().proccessRestFulResponse(stringRes);
        else
            getAbstractDao().showErrorMessage("Error", ERROR_MESSAGE);

    }

    public AbstractDao getAbstractDao() {
        return abstractDao;
    }

    public void setAbstractDao(AbstractDao abstractDao) {
        this.abstractDao = abstractDao;
    }
}
