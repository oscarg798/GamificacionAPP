package co.edu.udea.gamificacionapp.presentation.activities;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.ActivitiesIndexController;
import co.edu.udea.gamificacionapp.entities.core.Activity;
import co.edu.udea.gamificacionapp.entities.core.Concept;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.factories.impl.ActivityFactory;
import co.edu.udea.gamificacionapp.presentation.adapters.ActivityCustomAdapter;


public class ActivitiesIndexActivity extends ActionBarActivity {

    private ActivityCustomAdapter activityCustomAdapter;
    private ListView lvActivities;
    private ActivitiesIndexController activitiesIndexController;
    private int selectedActivityIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_index);
        initComponents();
    }

    public void initComponents() {
        activitiesIndexController = new ActivitiesIndexController(this);
        lvActivities = (ListView) findViewById(R.id.lv_activities);
        activitiesIndexController.getActivities();

    }

    public void showActivitiesList() {

        activityCustomAdapter = new ActivityCustomAdapter(getApplicationContext(),
                ActivityFactory.getInstance().getActivityList());
        lvActivities.setAdapter(activityCustomAdapter);


        lvActivities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedActivityIndex = i;
                Activity activity = (Activity) adapterView.getAdapter().getItem(i);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.custom_select_dialog_single_choice);


                for (Concept c : activity.getConceptList()) {
                    arrayAdapter.add(c.getName());
                }

                DialogInterface.OnClickListener arrayAdapterOnClickLister
                        = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Concept c = ActivityFactory.getInstance().getActivityList().get(selectedActivityIndex)
                                .getConceptList().get(i);

                        String conceptID = c.getObjectId();
                        String activityID = ActivityFactory.getInstance().getActivityList()
                                .get(selectedActivityIndex).getObjectId();

                        List<CouplePostParam> couplePostParams = new ArrayList<>();
                        CouplePostParam couplePostParam = new CouplePostParam();
                        couplePostParam.setKey("activityID");
                        couplePostParam.setParam(activityID);
                        couplePostParams.add(couplePostParam);

                        couplePostParam = new CouplePostParam();
                        couplePostParam.setKey("conceptID");
                        couplePostParam.setParam(conceptID);
                        couplePostParams.add(couplePostParam);

                        activitiesIndexController.changeActivityWithExtrasList(PhasesIndexActivity.class,
                                couplePostParams);
                    }
                };


                activitiesIndexController.showAlertDialogWithListView("Seleccione un Concepto",
                        "Cancelar", arrayAdapter, arrayAdapterOnClickLister);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activities_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
