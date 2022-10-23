package com.game.gamewinner.Adapters;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import com.game.gamewinner.Home;
import com.game.gamewinner.R;
import com.game.gamewinner.Sms;
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

import Models.Event_model;
import Models.Event_notification;
import Models.Topup_model;
import Models.Topup_notification_model;


public class Event_Adapter extends RecyclerView.Adapter<Event_Adapter.Holder> {
    ArrayList<Event_model>list;
    Context context;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    int abc,def;
    Event_notification event_notification;
    String s;
    public Event_Adapter(ArrayList<Event_model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.event_demo,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
     Event_model model=list.get(position);
     holder.name.setText(model.getE_name());
     holder.date.setText(model.getE_date());
     holder.join_coin.setText(model.getE_join_coin()+" Coin\nJoin");
     holder.prize_coin.setText(model.getE_prize_coin()+" Coin\nPrizze");
     holder.time.setText(model.getE_time().toString());
     Picasso.get().load((String) model.getE_pic()).placeholder(R.drawable.ic_baseline_account_circle_24).into(holder.imageView);
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

                abc= Integer.valueOf((model.getE_join_coin().toString()));
                int a=def-abc;
                if(def>=abc){


                    // holder.shop.setBackgroundColor(Color.parseColor("#E94560"));
                    holder.join_coin.setTextColor(Color.parseColor("#367E18"));
                    holder.layout.setOnClickListener(new View.OnClickListener() {
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
                                    Date d = new Date();
                                    String da  = String.valueOf(DateFormat.format("MMMM d, yyyy ", d.getTime()));
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


                                                        event_notification=new Event_notification();
                                                        event_notification.setAuth_uid(auth.getUid().toString());
                                                        event_notification.setUid(editText.getText().toString());
                                                        event_notification.setDate(model.getE_date().toString());
                                                        event_notification.setName(model.getE_name().toString());
                                                        event_notification.setTime(model.getE_time().toString());
                                                        event_notification.setCurrent_date(da.toString());




                                                        firestore.collection("Event_notification").add(event_notification).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

                    holder.join_coin.setTextColor(Color.parseColor("#E94560"));
                    holder.layout.setOnClickListener(new View.OnClickListener() {
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

    public class Holder extends  RecyclerView.ViewHolder{

TextView name,join_coin,prize_coin,date,time;
ImageView imageView;
View layout;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.event_name);
            join_coin=itemView.findViewById(R.id.event_join_coin);
            prize_coin=itemView.findViewById(R.id.event_prize_coin);
            date=itemView.findViewById(R.id.event_date);
            imageView=itemView.findViewById(R.id.event_pic);
            layout=itemView.findViewById(R.id.event_demo_layout);
            time=itemView.findViewById(R.id.event_time);
        }
    }
}
