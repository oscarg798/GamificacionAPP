package co.edu.udea.gamificacionapp.util;

/**
 * Created by oscargallon on 19/05/15.
 */
public class ErrorMessageHandler {

    public static String getMessageFromCode(String code) {
        String message = "";
        int cod = Integer.parseInt(code);
        switch (cod) {
            case 601: {
                message = "El usuario ingresado no Existe";
                break;
            }
            case 602: {
                message = "La contraseña es Incorrecta";
                break;
            }
            case 603: {
                message = "EL nombre de usuario no Existe";
                break;
            }
            case 604: {
                message = "Informacion duplicada";
                break;
            }
            case 605: {
                message = "Informacion no Encontrada";
                break;
            }
            case 606: {
                message = "Peticion con formato Incorrecto";
                break;
            }
            case 607: {
                message = "Usted ya se encuentra logeado";
                break;
            }
            default: {
                message = "Error desconocido";
                break;
            }
        }

        return message;
    }

}
