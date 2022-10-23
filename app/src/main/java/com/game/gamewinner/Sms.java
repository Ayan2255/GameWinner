package com.game.gamewinner;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.game.gamewinner.Adapters.Sms_Adapter;
import com.game.gamewinner.Adapters.User_Adapter;
import com.game.gamewinner.databinding.FragmentSmsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import Models.Sms_model;
import Models.Topup_model;


public class Sms extends Fragment {

 FragmentSmsBinding binding;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    Context context;
    ArrayList<Sms_model> list;
    Sms_Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSmsBinding.inflate(inflater,container,false);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        list=new ArrayList<>();
        adapter=new Sms_Adapter(list,getContext());
        binding.smsR.setHasFixedSize(true);
        binding.smsR.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.smsR.setAdapter(adapter);
        progressDialog=new ProgressDialog(getContext());

        binding.DltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                firestore.collection("SMS").document(auth.getUid().toString()).collection("s").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                            firestore.collection("SMS").document(auth.getUid().toString()).collection("s").document(queryDocumentSnapshot.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Try again", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        receved();
        return binding.getRoot();
    }

    private void receved() {

        firestore.collection("SMS").document(auth.getUid().toString()).collection("s").orderBy("name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for(DocumentChange documentChange : value.getDocumentChanges()){

                    if(documentChange.getType()== DocumentChange.Type.ADDED ){

                        Sms_model mm = documentChange.getDocument().toObject(Sms_model.class);
                        list.add(mm);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }
}