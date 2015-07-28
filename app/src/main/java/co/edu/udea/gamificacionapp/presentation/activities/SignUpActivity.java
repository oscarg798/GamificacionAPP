package co.edu.udea.gamificacionapp.presentation.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.SignUpController;

public class SignUpActivity extends ActionBarActivity {

    private EditText txtFullname;
    private EditText txtEmail;
    private EditText txtIDNumber;
    private EditText txtpassword;
    private EditText txtpasswordConfirmation;
    private Button btnSignUp;
    private SignUpController signUpController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initComponents();
    }


    public void initComponents() {
        signUpController = new SignUpController(this);
        txtFullname = (EditText) findViewById(R.id.txt_fullname);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtIDNumber = (EditText) findViewById(R.id.txt_id_number);
        txtpassword = (EditText) findViewById(R.id.txt_password);
        txtpasswordConfirmation = (EditText) findViewById(R.id.txt_password_confirmation);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpController.signUpUser(txtFullname.getText().toString(),
                        txtEmail.getText().toString(), txtIDNumber.getText().toString(),
                        txtpassword.getText().toString(), txtpasswordConfirmation.getText().toString());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
