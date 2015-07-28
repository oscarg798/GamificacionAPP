package co.edu.udea.gamificacionapp.presentation.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.QuizActivityController;
import co.edu.udea.gamificacionapp.entities.core.Phase;
import co.edu.udea.gamificacionapp.presentation.fragments.ResponseQuizFragment;

public class QuizActivity extends ActionBarActivity {

    private Fragment fragment;
    private Phase phase;
    private FragmentManager fragmentManager;
    private QuizActivityController quizActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initComponents();
    }

    public void initComponents() {
        quizActivityController = new QuizActivityController(this);
        if (getIntent().getExtras() != null) {
            phase = (Phase) getIntent().getExtras().getSerializable(getString(R.string.phase_key));
            switch (phase.getPhaseType()) {
                case "0": {
                    this.fragment = new ResponseQuizFragment();
                    break;
                }
            }
            changeFragment(fragment);

        }

    }


    public void changeFragment(Fragment fragment) {
        this.fragment = fragment;

        if (this.fragmentManager == null)
            this.fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public QuizActivityController getQuizActivityController() {
        return quizActivityController;
    }

    public void setQuizActivityController(QuizActivityController quizActivityController) {
        this.quizActivityController = quizActivityController;
    }
}
