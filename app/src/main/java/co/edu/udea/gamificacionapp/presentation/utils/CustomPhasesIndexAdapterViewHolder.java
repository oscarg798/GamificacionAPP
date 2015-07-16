package co.edu.udea.gamificacionapp.presentation.utils;

import android.widget.TextView;

import co.edu.udea.gamificacionapp.presentation.utils.abstracts.ViewHolder;

/**
 * Created by oscargallon on 15/07/15.
 */
public class CustomPhasesIndexAdapterViewHolder extends ViewHolder {

    private TextView txtPhaseName;
    private TextView txtPhaseDescription;
    private TextView txtPhaseIdentifier;

    public TextView getTxtPhaseName() {
        return txtPhaseName;
    }

    public void setTxtPhaseName(TextView txtPhaseName) {
        this.txtPhaseName = txtPhaseName;
    }

    public TextView getTxtPhaseDescription() {
        return txtPhaseDescription;
    }

    public void setTxtPhaseDescription(TextView txtPhaseDescription) {
        this.txtPhaseDescription = txtPhaseDescription;
    }

    public TextView getTxtPhaseIdentifier() {
        return txtPhaseIdentifier;
    }

    public void setTxtPhaseIdentifier(TextView txtPhaseIdentifier) {
        this.txtPhaseIdentifier = txtPhaseIdentifier;
    }
}
