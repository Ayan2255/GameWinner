package com.game.gamewinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.game.gamewinner.databinding.ActivityHomeBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity {
ActivityHomeBinding binding;
FirebaseFirestore firestore;
    Fragment fragment=null;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.hone_layout,new HomeFragment()).commit();
        bottomMenu();
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

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

                Picasso.get().load((String) documentSnapshot.getString("pic")).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.profileImage);
                // binding.profileCoin.setText((Integer) documentSnapshot.get("coin"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });




        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new ProfileFragment();
                binding.homeText.setTextColor(Color.parseColor("#E94560"));
                getSupportFragmentManager().beginTransaction().replace(R.id.hone_layout,fragment).commit();

            }
        });

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new Sms();
                binding.homeText.setTextColor(Color.parseColor("#E94560"));
                getSupportFragmentManager().beginTransaction().replace(R.id.hone_layout,fragment).commit();
            }
        });


          binding.help.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {


                  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                      if(ContextCompat.checkSelfPermission(Home.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                          ActivityCompat.requestPermissions(Home.this, new String[]{Manifest.permission.CALL_PHONE}, MODE_APPEND);
                      }   else{

                          Intent callIntent = new Intent(Intent.ACTION_CALL);
                          callIntent.setData(Uri.parse("tel:"+"+8801845069241"));

                          startActivity(callIntent);
                      }}

              }
          });


    }

    private void bottomMenu() {
        binding.homeNavigation.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment=null;
                switch (i){
                    case R.id.home:
                        fragment=new HomeFragment();
                        binding.homeText.setTextColor(Color.parseColor("#533483"));
                        break;
                    case R.id.result:
                        fragment=new ResultFragment();
                        binding.homeText.setTextColor(Color.parseColor("#0F3460"));
                        break;
                    case R.id.profile:
                        fragment=new ProfileFragment();
                        binding.homeText.setTextColor(Color.parseColor("#E94560"));
                        break;
                    case R.id.topup:
                        fragment=new Topup();
                        binding.homeText.setTextColor(Color.parseColor("#0F3460"));
                        break;



                }
                getSupportFragmentManager().beginTransaction().replace(R.id.hone_layout,fragment).commit();
            }
        });








    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}