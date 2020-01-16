package com.example.casediary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_form extends AppCompatActivity {

    private Button btnregi;
    private ProgressBar progressbar;
    private EditText txtname,txtmail,txtpass,txtcpass;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        btnregi=(Button)findViewById(R.id.btnregi);
        txtpass=(EditText)findViewById(R.id.txtpass);
        txtcpass=(EditText)findViewById(R.id.txtcpass);
        txtmail=(EditText)findViewById(R.id.txtmail);
        txtname=(EditText)findViewById(R.id.txtname);
        progressbar=(ProgressBar)findViewById(R.id.progressbar);

        firebaseAuth= FirebaseAuth.getInstance();


        btnregi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= txtname.getText().toString().trim();
                String email= txtmail.getText().toString().trim();
                String password= txtpass.getText().toString().trim();
                String Cpassword= txtcpass.getText().toString().trim();


                if (TextUtils.isEmpty(name)){

                    Toast.makeText(Signup_form.this,"Please Enter Name",Toast.LENGTH_LONG).show();
                    return;

                }
                if (TextUtils.isEmpty(email)){

                    Toast.makeText(Signup_form.this,"Please Enter Email",Toast.LENGTH_LONG).show();
                    return;

                }
                if (TextUtils.isEmpty(password)){

                    Toast.makeText(Signup_form.this,"Please Enter password",Toast.LENGTH_LONG).show();
                    return;

                }
                if (TextUtils.isEmpty(Cpassword)){

                    Toast.makeText(Signup_form.this,"Please Enter Confirm Password",Toast.LENGTH_LONG).show();
                    return;

                }


                if (password.length()<4){
                    Toast.makeText(Signup_form.this,"Password is too short",Toast.LENGTH_SHORT).show();
                }

                progressbar.setVisibility(View.VISIBLE);

                if (password.equals(Cpassword)){


                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup_form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressbar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        startActivity(new Intent(getApplicationContext(),Login.class));
                                        Toast.makeText(Signup_form.this,"Registration Succes",Toast.LENGTH_LONG).show();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Signup_form.this,"Simmiler Email availble. Try With New Email",Toast.LENGTH_LONG).show();

                                    }

                                    // ...
                                }
                            });



                }
            }
        });
    }
}
