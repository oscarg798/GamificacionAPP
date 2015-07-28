package co.edu.udea.gamificacionapp.controllers.abstracts;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;


/**
 * Created by oscargallon on 8/04/15.
 */
public abstract class AbstractController {

    /**
     * Actividad a a la cual pertenece el controlador
     */
    private Activity activity;

    /**
     * Barra de progreso
     */
    private ProgressDialog progressDialog;

    /**
     * Fragmento al cual pertenece el controlador
     */
    private Fragment fragment;


    /**
     * Contructor de la clase
     *
     * @param activity actividad a la cual pertenece el controlador
     */
    public AbstractController(Activity activity) {
        super();
        this.activity = activity;
    }

    /**
     * Metodo que muestra un dialogo de progreso
     *
     * @param title   titulo del dialogo
     * @param message mensaje del dialogo
     */
    public void showProgressDialog(String title, String message) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();

    }

    /**
     * Metodo que oculta un dialogo de progreso
     */
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    /**
     * Metodo que cambia de actividad
     *
     * @param destinyClass        clase da la actividad de destino
     * @param couplePostParamList parametros a enviar
     */
    public void changeActivityWithExtrasList(Class<?> destinyClass, List<CouplePostParam> couplePostParamList) {
        Intent i = new Intent(getActivity(), destinyClass);
        if (couplePostParamList != null) {
            for (CouplePostParam couplePostParam : couplePostParamList) {
                if (couplePostParam.getParam() != null)
                    i.putExtra(couplePostParam.getKey(), couplePostParam.getParam());
                else if (couplePostParam.getObjectParam() != null)
                    i.putExtra(couplePostParam.getKey(), couplePostParam.getObjectParam());

            }
        }
        getActivity().startActivity(i);
    }

    public void showAlertDialogWithListView(String alertTitle, final String negativeButtonTitle,
                                            final ArrayAdapter<String> arrayAdapter,
                                            final DialogInterface.OnClickListener
                                                    arrayAdapterOnclickListener) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                this.getActivity());
        builderSingle.setTitle(alertTitle);

        builderSingle.setNegativeButton(negativeButtonTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter,
                arrayAdapterOnclickListener);

        AlertDialog dialog = builderSingle.show();
        dialog.show();

    }


    /**
     * Metodo que muestra un dialog de texto
     *
     * @param title                         titulo del dialogo
     * @param message                       mensaje del dialogo
     * @param onCLickListenerPositiveButton accion del boton 1
     * @param onCLickListenerNegativeButton accion del boton 2
     * @param positiveButtonTitle           titulo boton 1
     * @param negativeButtonTitle           titulo boton 2
     */
    public void showAlertDialogWithTwoCustomOnClickListener(String title, String message,
                                                            DialogInterface.OnClickListener onCLickListenerPositiveButton,
                                                            DialogInterface.OnClickListener onCLickListenerNegativeButton,
                                                            String positiveButtonTitle, String negativeButtonTitle) {
        /**
         * Creamos el dialogo
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        /**
         * Le asignamos el titulo
         */
        builder.setTitle(title);

        /**
         * Le asignamos el mensaje
         */
        builder.setMessage(message);

        /**
         * Verificamos si tenemos boton positivo
         */
        if (onCLickListenerPositiveButton != null)
            builder.setPositiveButton(positiveButtonTitle,
                    onCLickListenerPositiveButton);

        /**
         * Verificamos si tenemos boton negativo
         */
        if (onCLickListenerNegativeButton != null)
            builder.setNegativeButton(negativeButtonTitle, onCLickListenerNegativeButton);

        /**
         * Verificamos si nose mando ningun metodo de boton para asignar uno por defecto que no
         * hace nada
         */
        if (onCLickListenerNegativeButton == null && onCLickListenerPositiveButton == null) {

            builder.setPositiveButton(getActivity().getResources().getString(R.string.acept_label),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
        }

        /**
         * Mostramos el dialogo
         */
        AlertDialog dialog = builder.show();
        dialog.show();

    }


    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }


}