package com.example.rajeev.loginsinup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity
{

    EditText username,password,txtStatus;
    TextView register;
    Button login;

    LoginButton login_button;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.username);

        password=(EditText) findViewById(R.id.pass);

        txtStatus=(EditText)findViewById(R.id.abc);
        login=(Button)findViewById(R.id.login);
        register=(TextView) findViewById(R.id.restr);



        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                final String name = username.getText().toString();
                final String pass = password.getText().toString();


                Toast.makeText(MainActivity.this, name +  pass  + "Clicked", Toast.LENGTH_LONG).show();



            }
        });




                register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(MainActivity.this, Registration.class);

                        startActivity(intent);
                    }
                });






        initializeControls();
        loginWithFB();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }

    private void initializeControls()
    {
        callbackManager = CallbackManager.Factory.create();
        login_button =(LoginButton) findViewById(R.id.login_button);
    }

    private void loginWithFB()
    {

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                txtStatus.setText("Login Success\n"+loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                txtStatus.setText(("Login Cancelled"));
            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("Login Error: "+error.getMessage());
            }
        });
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
