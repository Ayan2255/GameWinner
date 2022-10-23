package com.game.gamewinner;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.game.gamewinner.Adapters.User_Adapter;
import com.game.gamewinner.databinding.FragmentTopupBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import Models.Topup_model;


public class Topup extends Fragment {

    FragmentTopupBinding binding;
    FirebaseFirestore firestore;
    Context context;
    ArrayList<Topup_model> list;
    User_Adapter adapter;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentTopupBinding.inflate(inflater,container,false);
        firestore=FirebaseFirestore.getInstance();


        list=new ArrayList<>();
        adapter=new User_Adapter(list,getContext());
        binding.topupRecyclerview.setHasFixedSize(true);
        binding.topupRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.topupRecyclerview.setAdapter(adapter);

        receved();


        return binding.getRoot();
    }
    private void receved() {

        firestore.collection("Diamond").orderBy("taka", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for(DocumentChange documentChange : value.getDocumentChanges()){

                    if(documentChange.getType()== DocumentChange.Type.ADDED ){

                        Topup_model mm = documentChange.getDocument().toObject(Topup_model.class);
                        list.add(mm);
                        adapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }
}