package com.example.casediary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private Button btnlogin,btncreate;
    private EditText txtlogmail,txtlogpass;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin=(Button)findViewById(R.id.btnlogin);
        btncreate=(Button)findViewById(R.id.btncreate);

        txtlogmail=(EditText) findViewById(R.id.txtlogmail);
        txtlogpass=(EditText)findViewById(R.id.txtlogpass);
        firebaseAuth=FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtlogmail.getText().toString().trim();
                String password = txtlogpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){

                    Toast.makeText(Login.this,"Please Enter Email",Toast.LENGTH_LONG).show();
                    return;

                }
                if (TextUtils.isEmpty(password)){

                    Toast.makeText(Login.this,"Please Enter password",Toast.LENGTH_LONG).show();
                    return;

                }

                if (password.length()<4){
                    Toast.makeText(Login.this,"Password is too short",Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = firebaseAuth.getCurrentUser();

                                    // Sign in success, update UI with the signed-in user's information
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                    Toast.makeText(Login.this,"Success log in " +user.getEmail(),Toast.LENGTH_LONG).show();


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Login failed or User Not available!",
                                            Toast.LENGTH_LONG).show();

                                }

                            }
                        });

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // Name, email address, and profile photo Url
                    String name = user.getDisplayName();
                    String Email = user.getEmail();


                    // Check if user's email is verified
                    boolean emailVerified = user.isEmailVerified();

                    // The user's ID, unique to the Firebase project. Do NOT use this value to
                    // authenticate with your backend server, if you have one. Use
                    // FirebaseUser.getIdToken() instead.
                    String uid = user.getUid();
                }




            }
        });

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Signup_form.class));
            }
        });

    }
}
