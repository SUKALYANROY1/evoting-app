package com.example.EVotingSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button Confirm;
    private EditText name;
    private   ProgressBar progressBar;
    private TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress2);
        name = findViewById(R.id.name);
        Confirm = findViewById(R.id.confirm_button);
        progressBar.setVisibility(View.GONE);
        Confirm.setOnClickListener(this::onClick);


        }

    private void onClick(View v) {

        progressBar.setVisibility(View.VISIBLE);

        String voter_ID = name.getText().toString();
        System.out.println(voter_ID);
        ArrayList<String> Voter_ID = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("VoterID");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Voter_ID.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Voter_ID.add(snapshot.getValue().toString());
                }

                if (Voter_ID.contains(voter_ID)) {
                    System.out.println(voter_ID);
                    //progressBar.setVisibility(View.VISIBLE);
                    //System.out.println("Voter Card is present ");
                    Toast.makeText(MainActivity.this, "Voter ID Matched", Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(MainActivity.this, Show_details.class));
                    ArrayList<String> Voter_ID_Details = new ArrayList<>();
                    System.out.println("hlw"+voter_ID);
                    DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child(voter_ID);
                    reference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Voter_ID_Details.clear();
                            for (DataSnapshot snapshots : dataSnapshot.getChildren()) {
                                Voter_ID_Details.add(snapshots.getValue().toString());
                            }
                            progressBar.setVisibility(View.GONE);
                            System.out.println("Name "+Voter_ID_Details.get(3));
                            if(Voter_ID_Details.get(3).equals("0")) {
                                Intent i = new Intent(MainActivity.this, Show_details.class);
                                i.putExtra("Key", Voter_ID_Details);
                                i.putExtra("ID",voter_ID);
                                startActivity(i);
                            }
                            else{
                                t1 = findViewById(R.id.Error);
                                t1.setText("You have all ready voted");
                                //Toast.makeText(MainActivity.this,,Toast.LENGTH_LONG);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } else
                    Toast.makeText(MainActivity.this, "Voter ID is not valid", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    //System.out.println(Voter_ID);
    }
}
