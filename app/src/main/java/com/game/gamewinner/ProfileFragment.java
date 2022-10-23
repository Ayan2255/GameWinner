package com.game.gamewinner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.game.gamewinner.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import Models.Topup_model;
import Models.User_model;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProfileBinding.inflate(inflater,container,false);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        binding.byeCoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Coin_buy();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.hone_layout,fragment).addToBackStack(null).commit();
            }

        });

        binding.setttings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Forgotten_password.class);
                startActivity(intent);
            }
        });

        binding.withrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Withdraw();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.hone_layout,fragment).addToBackStack(null).commit();
            }
        });
        firestore.collection("User").document(auth.getUid().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

               /* String name="";
                String Phone="";
                String emali="";
                String pass="";
                String uid="";
                String pic="";
                int coin=00;
                String sms="";*/
                binding.profileName.setText(documentSnapshot.getString("name"));
                binding.profileEmail.setText(documentSnapshot.getString("emali"));
               binding.profileCoin.setText(documentSnapshot.getString("coin"));

                Picasso.get().load((String) documentSnapshot.getString("pic")).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.profilePic);
               // binding.profileCoin.setText((Integer) documentSnapshot.get("coin"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent=new Intent(getContext(),Login.class);
                startActivity(intent);
            }
        });



        return binding.getRoot();

    }
}