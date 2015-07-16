package co.edu.udea.gamificacionapp.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.PhasesIndexController;
import co.edu.udea.gamificacionapp.factories.impl.PhaseFactory;
import co.edu.udea.gamificacionapp.presentation.adapters.CustomPhasesIndexAdapter;

public class PhasesIndexActivity extends AppCompatActivity {

    private PhasesIndexController phasesIndexController;

    private ListView lvPhases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phases_index);
        initComponents();
    }

    public void initComponents() {
        phasesIndexController = new PhasesIndexController(this);
        lvPhases = (ListView) findViewById(R.id.lv_phases);
        String activityID = getIntent().getExtras().getString("activityID");
        String conceptID = getIntent().getExtras().getString("conceptID");

        getSupportActionBar().setTitle("Lista de Fases");

        phasesIndexController.getPhasesFromBackEnd(activityID, conceptID);

    }

    public void fillLvPhases() {
        CustomPhasesIndexAdapter customPhasesIndexAdapter =
                new CustomPhasesIndexAdapter(PhaseFactory.getInstance().getPhaseList(),
                        getApplicationContext());

        lvPhases.setAdapter(customPhasesIndexAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phases_index, menu);
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
