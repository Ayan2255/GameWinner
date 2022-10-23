package com.game.gamewinner;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.game.gamewinner.databinding.FragmentCoinBuyBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Coin_buy extends Fragment {

    FragmentCoinBuyBinding binding;

    RadioButton radioButton;
    RadioGroup radioGroup;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    String s="";
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentCoinBuyBinding.inflate(inflater,container,false);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait a momen");
        binding.sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
              int id=   binding.radioGruop.getCheckedRadioButtonId();

              if(id==R.id.bikash_radio){

                  s="Bikash";
              }
              else if(id==R.id.nogod_radiio){

                  s="Nogod";
              }
              else if(id==R.id.roket_radio){

                  s="Roket";
              }

                Date d = new Date();
                String da  = String.valueOf(DateFormat.format("MMMM d, yyyy ", d.getTime()));
                Map<String, Object> user = new HashMap<>();
                user.put("date", da);
                user.put("operator", s);
                user.put("Number",binding.number.getText().toString());
                user.put("Amount",binding.amount.getText().toString());
                user.put("auth",auth.getUid().toString());

                firestore.collection("buy").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.dismiss();;
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        Fragment fragment = new ProfileFragment();
                        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.hone_layout,fragment).addToBackStack(null).commit();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });






            }
        });



        return binding.getRoot();


    }
}