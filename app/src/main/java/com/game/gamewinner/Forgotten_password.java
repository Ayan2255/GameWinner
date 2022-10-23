package com.game.gamewinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.game.gamewinner.databinding.ActivityForgottenPasswordBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotten_password extends AppCompatActivity {
ActivityForgottenPasswordBinding binding;
FirebaseAuth auth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgottenPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(Forgotten_password.this);
        progressDialog.setMessage("Wait a moment");


        binding.singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(binding.userEmail.getText().toString())){
                    Toast.makeText(Forgotten_password.this, "Please Enter Email...", Toast.LENGTH_SHORT).show();
                }
              else {
                progressDialog.show();;
                auth.sendPasswordResetEmail(binding.userEmail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();;
                        Toast.makeText(Forgotten_password.this, "Check your Email", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Forgotten_password.this,Login.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressDialog.dismiss();
                        Toast.makeText(Forgotten_password.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });}
            }
        });

    }
}