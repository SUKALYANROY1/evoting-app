package com.example.EVotingSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Voting extends AppCompatActivity {
    private Button BJP,CPIM,tmc,cong,aap,nota;
    private int flag = 0;
    //String party = "bjp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        BJP = findViewById(R.id.bjp);
        CPIM = findViewById(R.id.cpi);
        tmc = findViewById(R.id.TMC);
        cong = findViewById(R.id.CONG);
        aap = findViewById(R.id.AAP);
        nota = findViewById(R.id.NOTA);
        //HashMap Party = new HashMap();
        String ID = getIntent().getStringExtra("ID");
        DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference().child(ID);

        final String[] party = {null};

        HashMap hashMap = new HashMap();

        BJP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                party[0] = "BJP";
                hashMap.put("Party", party[0]);

                databaseReference3.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Voting.this,Exit.class);
                            startActivity(intent);
                            Toast.makeText(Voting.this,"Votting Successfull", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(Voting.this,"Votting Unsuccessfull", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        tmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                party[0] = "TMC";
                hashMap.put("Party", party[0]);

                databaseReference3.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Voting.this,Exit.class);
                            startActivity(intent);
                            Toast.makeText(Voting.this,"Votting Successfull", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(Voting.this,"Votting Unsuccessfull", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        CPIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                party[0] = "CPI(M)";
                hashMap.put("Party", party[0]);

                databaseReference3.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Voting.this,Exit.class);
                            startActivity(intent);
                            Toast.makeText(Voting.this,"Votting Successfull", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(Voting.this,"Votting Unsuccessfull", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        aap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                party[0] = "AAP";
                hashMap.put("Party", party[0]);

                databaseReference3.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Voting.this,Exit.class);
                            startActivity(intent);
                            Toast.makeText(Voting.this,"Votting Successfull", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(Voting.this,"Votting Unsuccessfull", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                party[0] = "CONG";
                hashMap.put("Party", party[0]);

                databaseReference3.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Voting.this,Exit.class);
                            startActivity(intent);
                            Toast.makeText(Voting.this,"Votting Successfull", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(Voting.this,"Votting Unsuccessfull", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                party[0] = "NOTA";
                hashMap.put("Party", party[0]);

                databaseReference3.updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Voting.this,Exit.class);
                            startActivity(intent);
                            Toast.makeText(Voting.this,"Votting Successfull", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(Voting.this,"Votting Unsuccessfull", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });



    }
}