package com.game.gamewinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.game.gamewinner.databinding.ActivitySingupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import Models.User_model;

public class Singup extends AppCompatActivity {
  ActivitySingupBinding binding;
  FirebaseFirestore firestore;
  FirebaseAuth auth;
    ProgressDialog progressDialog;
  StorageReference storageReference;
    User_model model;
    Uri u,ui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySingupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(Singup.this);
        progressDialog.setTitle("Account Create");
        progressDialog.setMessage("we hove create your account");

        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });
        binding.singUpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                      progressDialog.show();
                      if(TextUtils.isEmpty(binding.userName.getText().toString()) ||
                        TextUtils.isEmpty(binding.userEmail.getText().toString())||
                        TextUtils.isEmpty(binding.userPassword1.getText().toString())||
                        TextUtils.isEmpty(binding.userPassword2.getText().toString())||
                        TextUtils.isEmpty(binding.userPhone.getText().toString())||
                      u==null)

                {
                          progressDialog.dismiss();
                          Toast.makeText(Singup.this, "Please fill up requrmen ...", Toast.LENGTH_SHORT).show();

                }
                      else if(!binding.userPassword1.getText().toString().equals(binding.userPassword2.getText().toString())){
                          progressDialog.dismiss();
                          Toast.makeText(Singup.this, "Please set same password ...", Toast.LENGTH_SHORT).show();

                      }
                      else {

                             auth.createUserWithEmailAndPassword(binding.userEmail.getText().toString(),binding.userPassword1.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {

                                     if(task.isSuccessful()){


                                     storageReference=FirebaseStorage.getInstance().getReference().child("user_profile_Pic").child(auth.getUid());

                                     storageReference.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                         @Override
                                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                             storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                 @Override
                                                 public void onSuccess(Uri uri) {




                                                     model=new User_model(binding.userName.getText().toString(),binding.userPhone.getText().toString(),
                                                             binding.userEmail.getText().toString(),binding.userPassword1.getText().toString(),
                                                             auth.getUid().toString(),uri.toString(),"ss","00");



                                                     firestore.collection("User").document(auth.getUid().toString()).set(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                         @Override
                                                         public void onSuccess(Void unused) {
                                                             Toast.makeText(Singup.this, "Done...", Toast.LENGTH_SHORT).show();
                                                             Intent intent=new Intent(Singup.this,Home.class);
                                                             startActivity(intent);
                                                         }
                                                     }).addOnFailureListener(new OnFailureListener() {
                                                         @Override
                                                         public void onFailure(@NonNull Exception e) {
                                                             Toast.makeText(Singup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                         }
                                                     });
                                                 }
                                             }).addOnFailureListener(new OnFailureListener() {
                                                 @Override
                                                 public void onFailure(@NonNull Exception e) {

                                                 }
                                             });

                                         }
                                     }).addOnFailureListener(new OnFailureListener() {
                                         @Override
                                         public void onFailure(@NonNull Exception e) {

                                         }
                                     });









                                       }
                                     else {
                                         progressDialog.dismiss();
                                         Toast.makeText(Singup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Toast.makeText(Singup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                 }
                             });
                      }

            }
        });









    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData()!=null){
            u=data.getData();
            binding.profileImage.setImageURI(u);


        }
    }
}