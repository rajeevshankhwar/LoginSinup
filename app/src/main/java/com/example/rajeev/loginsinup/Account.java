package com.example.rajeev.loginsinup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.PrivateKey;

public class Account extends AppCompatActivity {

    private Button mlogout;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth=FirebaseAuth.getInstance();
        mlogout=(Button)findViewById(R.id.logout);

        mlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();
                LoginManager.getInstance().logOut();


            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            updateUI(currentUser);
        }

    }

    private void updateUI(FirebaseUser currentUser) {

        Toast.makeText(Account.this, "You're Logged In", Toast.LENGTH_SHORT).show();

       // Intent acc= new Intent(Account.this,FacebookLogin.class);
        //startActivity(acc);
        //finish();
    }

}

