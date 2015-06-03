package co.edu.udea.gamificacionapp.presentation.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.ActivitiesIndexController;
import co.edu.udea.gamificacionapp.factories.impl.ActivityFactory;
import co.edu.udea.gamificacionapp.presentation.adapters.ActivityCustomAdapter;


public class ActivitiesIndexActivity extends ActionBarActivity {

    private ActivityCustomAdapter activityCustomAdapter;
    private ListView lvActivities;
    private ActivitiesIndexController activitiesIndexController;

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
