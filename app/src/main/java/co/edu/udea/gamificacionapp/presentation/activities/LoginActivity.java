package co.edu.udea.gamificacionapp.presentation.activities;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.LoginActivityController;

public class LoginActivity extends ActionBarActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogIn;
    private Button btnSignUp;
    private LoginActivityController loginActivityController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();
    }

    public void initComponents() {
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        btnLogIn = (Button) findViewById(R.id.btn_log_in);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        loginActivityController = new LoginActivityController(this);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivityController.changeActivityWithExtrasList(SignUpActivity.class, null);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivityController.logInUser(txtUsername.getText().toString(),
                        txtPassword.getText().toString());
            }
        });

        SharedPreferences editor = getPreferences(0);
        String username = editor.getString("idNumber", null);
        String password = editor.getString("password", null);

        if (username != null && password != null) {
            txtUsername.setText(username);
            txtPassword.setText(password);
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
