package co.edu.udea.gamificacionapp.presentation.utils;

import android.widget.TextView;

import co.edu.udea.gamificacionapp.presentation.utils.abstracts.ViewHolder;

/**
 * Created by oscargallon on 3/06/15.
 */
public class ActivitiesCustomAdapterViewHolder extends ViewHolder {

    private TextView txtActivitiesName;

    public TextView getTxtActivitiesName() {
        return txtActivitiesName;
    }

    public void setTxtActivitiesName(TextView txtActivitiesName) {
        this.txtActivitiesName = txtActivitiesName;
    }
}
