package co.edu.udea.gamificacionapp.presentation.utils;

import android.widget.TextView;

import org.w3c.dom.Text;

import co.edu.udea.gamificacionapp.presentation.utils.abstracts.ViewHolder;

/**
 * Created by oscargallon on 30/07/15.
 */
public class CustomQuestionViewHolder extends ViewHolder {

    private TextView txtQuestionStatament;

    public TextView getTxtQuestionStatament() {
        return txtQuestionStatament;
    }

    public void setTxtQuestionStatament(TextView txtQuestionStatament) {
        this.txtQuestionStatament = txtQuestionStatament;
    }
}
