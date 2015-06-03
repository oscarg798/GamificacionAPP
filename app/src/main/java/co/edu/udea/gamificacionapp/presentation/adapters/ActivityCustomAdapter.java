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
import co.edu.udea.gamificacionapp.presentation.utils.ActivitiesCustomAdapterViewHolder;

/**
 * Created by oscargallon on 3/06/15.
 */
public class ActivityCustomAdapter extends BaseAdapter{

    private Context context;
    private List<Activity> activityList;

    public ActivityCustomAdapter(Context context, List<Activity> activityList) {
        this.context = context;
        this.activityList = activityList;
    }

    @Override
    public int getCount() {
        return this.activityList.size();
    }

    @Override
    public Activity getItem(int i) {
        return this.activityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.activityList.indexOf(getItem(i));
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(android.app.Activity.LAYOUT_INFLATER_SERVICE);
        ActivitiesCustomAdapterViewHolder holder;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.activities_custom_layout, null);

            holder = new ActivitiesCustomAdapterViewHolder();

            holder.setTxtActivitiesName((TextView)convertView.findViewById(R.id.txt_activity_name));
            convertView.setTag(holder);
        } else {
            holder = (ActivitiesCustomAdapterViewHolder) convertView.getTag();

        }

        Activity activity = getItem(i);
        holder.getTxtActivitiesName().setText(activity.getName());


        return convertView;

    }
}
