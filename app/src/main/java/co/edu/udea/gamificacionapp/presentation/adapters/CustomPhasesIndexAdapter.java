package co.edu.udea.gamificacionapp.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.entities.core.Activity;
import co.edu.udea.gamificacionapp.entities.core.Phase;
import co.edu.udea.gamificacionapp.presentation.utils.ActivitiesCustomAdapterViewHolder;
import co.edu.udea.gamificacionapp.presentation.utils.CustomPhasesIndexAdapterViewHolder;

/**
 * Created by oscargallon on 15/07/15.
 */
public class CustomPhasesIndexAdapter extends BaseAdapter {

    private List<Phase> phaseList;
    private Context context;

    public CustomPhasesIndexAdapter(List<Phase> phaseList, Context context) {
        this.phaseList = phaseList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.phaseList.size();
    }

    @Override
    public Phase getItem(int i) {
        return this.phaseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.phaseList.indexOf(getItem(i));
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(android.app.Activity.LAYOUT_INFLATER_SERVICE);
        CustomPhasesIndexAdapterViewHolder holder;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.custom_phases_index_layout, null);

            holder = new CustomPhasesIndexAdapterViewHolder();

            holder.setTxtPhaseName((TextView) convertView.findViewById(R.id.txt_phase_name));
            holder.setTxtPhaseDescription((TextView) convertView.findViewById(R.id.txt_phase_description));
            holder.setTxtPhaseIdentifier((TextView) convertView.findViewById(R.id.txt_phase_identifier));
            convertView.setTag(holder);
        } else {
            holder = (CustomPhasesIndexAdapterViewHolder) convertView.getTag();

        }

        Phase phase = getItem(i);
        holder.getTxtPhaseName().setText(phase.getName());
        holder.getTxtPhaseDescription().setText(phase.getDescription());
        holder.getTxtPhaseIdentifier().setText(phase.getIdentifier());


        return convertView;
    }
}
