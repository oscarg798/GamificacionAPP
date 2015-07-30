package co.edu.udea.gamificacionapp.presentation.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.entities.core.Activity;

public class ShowActivityInfoActivity extends ActionBarActivity {

    private TextView txtIdNumber;
    private TextView txtActivityName;
    private TextView txtActivityDescription;
    private TextView txtStartDate;
    private TextView txtEndDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_activity_info);
        initComponents();
    }

    public void initComponents() {
        getSupportActionBar().setTitle("Información Actividad");

        txtIdNumber = (TextView) findViewById(R.id.txt_id_number);
        txtActivityName = (TextView) findViewById(R.id.txt_activity_name);
        txtActivityDescription = (TextView) findViewById(R.id.txt_activity_description);
        txtStartDate = (TextView) findViewById(R.id.txt_start_date);
        txtEndDate = (TextView) findViewById(R.id.txt_end_date);

        Activity activity = (Activity) getIntent().getExtras().getSerializable("activity");
        txtIdNumber.setText(activity.getIdNumber());
        txtActivityName.setText(activity.getName());
        txtActivityDescription.setText(activity.getDescription());
        txtStartDate.setText(activity.getStartDate());
        txtEndDate.setText(activity.getEndDate());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_activity_info, menu);
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
