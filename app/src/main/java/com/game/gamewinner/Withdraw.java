package com.game.gamewinner;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.game.gamewinner.databinding.FragmentWithdrawBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Models.Withdraw_model;

public class Withdraw extends Fragment {

     FragmentWithdrawBinding binding;
     FirebaseFirestore firestore;
     FirebaseAuth auth;
    String oparator="",date="";
    ProgressDialog progressDialog;

    int userCoin=0,withdrowCoin=0;
    Withdraw_model model;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentWithdrawBinding.inflate(inflater,container,false);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();


        binding.wdSentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("User").document(auth.getUid().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        userCoin= Integer.parseInt(documentSnapshot.getString("coin"));
                        withdrowCoin=Integer.valueOf(binding.wdAmount.getText().toString());

                        if(userCoin>=withdrowCoin){

                            progressDialog=new ProgressDialog(getContext());
                            progressDialog.setMessage("Please wait a momen");
                                    progressDialog.show();
                                    int id=   binding.radioGruop.getCheckedRadioButtonId();

                                    if(id==R.id.bikash_radio){

                                        oparator="Bikash";
                                    }
                                    else if(id==R.id.nogod_radiio){

                                        oparator="Nogod";
                                    }
                                    else if(id==R.id.roket_radio){

                                        oparator="Roket";
                                    }

                                    Date d = new Date();
                                    date  = String.valueOf(DateFormat.format("MMMM d, yyyy ", d.getTime()));

                            String s= String.valueOf(userCoin-withdrowCoin);
                            Map<String,String> userMap = new HashMap<>();
                            userMap.put("coin",s);

                            firestore.collection("User").document(auth.getUid())
                                    .set(userMap, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {


                                            firestore.collection("User").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {

                                                    model=new Withdraw_model();

                                                    model.setNamw(documentSnapshot.getString("name"));
                                                    model.setPic(documentSnapshot.getString("pic"));
                                                    model.setUid(auth.getUid());
                                                    model.setDate(date);
                                                    model.setOperator(oparator);
                                                    model.setTaka(binding.wdAmount.getText().toString());
                                                    model.setNumber(binding.wdNumber.getText().toString());
                                                    firestore.collection("withwdraw").add(model).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                        @Override
                                                        public void onSuccess(DocumentReference documentReference) {
                                                             progressDialog.dismiss();
                                                            Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();


                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(getContext(), "Try again...", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                  progressDialog.dismiss();
                                                }
                                            });



                                        }
                                    });

                        }
                        else {
                            Toast.makeText(getContext(), "Not Ef manuy", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        return binding.getRoot();

    }
}