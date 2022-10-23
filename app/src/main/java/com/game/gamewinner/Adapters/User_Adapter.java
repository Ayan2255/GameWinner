package com.game.gamewinner.Adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.game.gamewinner.Home;
import com.game.gamewinner.ProfileFragment;
import com.game.gamewinner.R;
import com.game.gamewinner.Singup;
import com.game.gamewinner.Topup;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Models.Topup_model;
import Models.Topup_notification_model;

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.ViewHolder> {

       ArrayList<Topup_model>list;
       Context context;
       FirebaseFirestore firestore;
       FirebaseAuth auth;
       ProgressDialog progressDialog;
    Topup_notification_model model ;
       int abc,def;
       String s;

    public User_Adapter(ArrayList<Topup_model> list, Context context) {
        this.list = list;
       this.context = context;
       String s;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topup_demo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topup_model user= list.get(position);
        holder.diamond.setText(user.getDiamond()+" Diamond");
        holder.taka.setText(user.getTaka()+" Taka");
        holder.name.setText(user.getName().toString());
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

             //   Picasso.get().load((String) documentSnapshot.getString("pic")).placeholder(R.drawable.ic_baseline_account_circle_24).into(binding.profileImage);
                // binding.profileCoin.set
                // Text((Integer) documentSnapshot.get("coin"));
                def= Integer.parseInt(documentSnapshot.getString("coin"));

                abc= Integer.valueOf(user.getTaka().toString());
                int a=def-abc;
                if(def>=abc){


                    // holder.shop.setBackgroundColor(Color.parseColor("#E94560"));
                    holder.shop.setTextColor(Color.parseColor("#367E18"));
                    holder.shop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)

                        {
                            final AlertDialog.Builder alert=new AlertDialog.Builder(context);
                            View mview =LayoutInflater.from(context)
                                    .inflate(R.layout.topup_alartdialoge,null);
                            final EditText editText=mview.findViewById(R.id.ffUId);
                            final Button buttoncn=mview.findViewById(R.id.btn_cn);
                            final  Button buttonok=mview.findViewById(R.id.btn_ok);
                            alert.setView(mview);
                            final AlertDialog alertDialog=alert.create();
                            alertDialog.setCanceledOnTouchOutside(true);


                            buttoncn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //Toast.makeText(context, user.getDiamond()+"--cancel-"+user.getTaka(), Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();

                                }
                            });

                            buttonok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    firestore=FirebaseFirestore.getInstance();
                                    auth=FirebaseAuth.getInstance();
                                    progressDialog=new ProgressDialog(context);
                                    progressDialog.setTitle("Account Create");
                                    progressDialog.setMessage("we hove create your account");
                                    progressDialog.show();;
                                    if(TextUtils.isEmpty(editText.getText().toString())){

                                        Toast.makeText(context, "Please sent UID...", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();

                                    }
                                    else {


                                        String s= String.valueOf(a);
                                        Map<String,String> userMap = new HashMap<>();
                                        userMap.put("coin",s);
                                        firestore.collection("User").document(auth.getUid())
                                                .set(userMap, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Date d = new Date();
                                                        String s  = String.valueOf(DateFormat.format("MMMM d, yyyy ", d.getTime()));


                                                        model=new Topup_notification_model();
                                                        model.setDiamond(user.getDiamond());
                                                        model.setTaka(user.getTaka());
                                                        model.setUid(editText.getText().toString());
                                                        model.setDate(s);



                                                        firestore.collection("Topup_notification").add(model).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {

                                                                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();

                                                                progressDialog.dismiss();
                                                                alertDialog.dismiss();
                                                                Intent intent = new Intent(context, Home.class);
                                                                context.startActivity(intent);
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Toast.makeText(context, "Try again...", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });



                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });


                                    }

                                }
                            });

                            alertDialog.show();
                        }
                    });
                }
                else {

                    holder.shop.setTextColor(Color.parseColor("#E94560"));
                    holder.shop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "Not enough money", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });














    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  ViewHolder extends  RecyclerView.ViewHolder{


        TextView diamond,taka,shop,name;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);


            diamond=itemView.findViewById(R.id.demo_diamond);
            taka =itemView.findViewById(R.id.demo_taka);
            shop=itemView.findViewById(R.id.shop_text);
            name=itemView.findViewById(R.id.topup_name);

        }
    }
}
