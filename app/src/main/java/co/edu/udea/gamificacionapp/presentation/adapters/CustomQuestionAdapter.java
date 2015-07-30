package co.edu.udea.gamificacionapp.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import co.edu.udea.gamificacionapp.presentation.utils.CustomQuestionViewHolder;
import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.entities.core.Question;


/**
 * Created by oscargallon on 30/07/15.
 */
public class CustomQuestionAdapter extends BaseAdapter {

    private List<Question> questionList;
    private Context context;

    public CustomQuestionAdapter(List<Question> questionList, Context context) {
        this.questionList = questionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.questionList.size();
    }

    @Override
    public Question getItem(int i) {
        return this.questionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.questionList.indexOf(getItem(i));
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(android.app.Activity.LAYOUT_INFLATER_SERVICE);
        CustomQuestionViewHolder holder;
        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.custom_question_layout, null);

            holder = new CustomQuestionViewHolder();

            holder.setTxtQuestionStatament((TextView) convertView.findViewById(R.id.textView2));
            convertView.setTag(holder);
        } else {
            holder = (CustomQuestionViewHolder) convertView.getTag();

        }

        Question question = getItem(i);
        holder.getTxtQuestionStatament().setText(question.getStatement());


        return convertView;
    }
}
