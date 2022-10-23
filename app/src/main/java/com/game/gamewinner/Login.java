package com.game.gamewinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.game.gamewinner.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(Login.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to  your account");

        if(firebaseAuth.getCurrentUser()!=null){

            Intent intent=new Intent(Login.this,Home.class);
          startActivity(intent);
        }
        binding.createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Singup.class);
                startActivity(intent);
            }
        });
        binding.forgettenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Forgotten_password.class);
                startActivity(intent);
            }
        });
        binding.sininBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(binding.lgnUserEmail.getText().toString(),binding.lgnUserPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this, "Done...", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Login.this,Home.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(Login.this, "Please valid email and password...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}