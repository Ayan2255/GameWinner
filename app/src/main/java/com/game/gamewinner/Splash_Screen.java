package com.game.gamewinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.game.gamewinner.databinding.ActivitySplashScreenBinding;

public class Splash_Screen extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Thread thread=new Thread(){
            public  void  run(){

                try {
                    sleep(6000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {


                   // Intent intent=new Intent(Splash_Screen.this,Home.class);
            Intent intent=new Intent(Splash_Screen.this,Login.class);
         //  Intent intent=new Intent(Splash_Screen.this,Singup.class);


                    startActivity(intent);
                }

            }
        };thread.start();
    }
}