package com.game.gamewinner;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.game.gamewinner.Adapters.Event_Adapter;
import com.game.gamewinner.Adapters.Slider_Adapter;
import com.game.gamewinner.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;

import Models.Event_model;


public class HomeFragment extends Fragment {


     FragmentHomeBinding binding;
    FirebaseFirestore firestore;
    Context context;
    ArrayList<Event_model>list;
    Event_Adapter adapter2;
     int [] images={
             R.drawable.aa,
             R.drawable.cc,
             R.drawable.ee,
             R.drawable.bb,
             R.drawable.ccccccccc,
             R.drawable.gg,
             R.drawable.freefire,
             R.drawable.ff,
             R.drawable.hh,
     };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentHomeBinding.inflate(inflater, container, false);


        Slider_Adapter adapter=new Slider_Adapter(images,getActivity());

        binding.imageSlider.setSliderAdapter(adapter);
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        binding.imageSlider.startAutoCycle();

        firestore=FirebaseFirestore.getInstance();


        list=new ArrayList<>();
        adapter2=new Event_Adapter(list,getContext());
        binding.eventRecycelerview.setHasFixedSize(true);
        binding.eventRecycelerview.setLayoutManager(new GridLayoutManager(context,2));
        binding.eventRecycelerview.setAdapter(adapter2);
        receved();



        return binding.getRoot();

    }

    private void receved() {

        firestore.collection("Event").orderBy("e_date", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for(DocumentChange documentChange : value.getDocumentChanges()){

                    if(documentChange.getType()== DocumentChange.Type.ADDED ){

                        Event_model  mm = documentChange.getDocument().toObject(Event_model.class);
                        list.add(mm);
                        adapter2.notifyDataSetChanged();
                    }
                }
            }
        });
    }


}