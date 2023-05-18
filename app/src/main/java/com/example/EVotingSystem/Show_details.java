package com.example.EVotingSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.EVotingSystem.suk.AndroidSuk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Show_details extends AppCompatActivity {
    private TextView Aadhar;
    private TextView Gender;
    private TextView Phone_no;
    private TextView Name;
    private String OTP;
    EditText OTPInput;
    Button SubmitBtx;
    ProgressBar progressBar;
    TextView resendotpTextView;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    Long timeoutSeconds=60L;
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken ResendingToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_details);
        ArrayList<String> Voter_ID_Details = new ArrayList<>();
        Voter_ID_Details.clear();
        Voter_ID_Details = getIntent().getStringArrayListExtra("Key");
        System.out.println("Second activity : "+Voter_ID_Details);
        Aadhar = findViewById(R.id.AadharText);
        String AadharLast = Voter_ID_Details.get(0).substring(Voter_ID_Details.get(0).length()-4);
        String Aadharno = "XXXXXXXX"+AadharLast;
        Aadhar.setText(Aadharno);
        Gender = findViewById(R.id.GenderText);
        Gender.setText(Voter_ID_Details.get(1));
        Name = findViewById(R.id.nameText);
        Name.setText(Voter_ID_Details.get(2));
        Phone_no=findViewById(R.id.PhoneText);
        String PHLast = Voter_ID_Details.get(4).substring(Voter_ID_Details.get(4).length()-4);
        String Phoneno = "XXXXXX"+PHLast;
        Phone_no.setText(Phoneno);

        OTPInput=findViewById(R.id.OTP);
        SubmitBtx=findViewById(R.id.Submit);

        resendotpTextView=findViewById(R.id.Resend);

        final ProgressBar progressBar = findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + Voter_ID_Details.get(4),
                60,
                TimeUnit.SECONDS,
                Show_details.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(backendotp, forceResendingToken);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Show_details.this, "OTP send Successfully", Toast.LENGTH_SHORT).show();
                        System.out.println("OTP : "+backendotp);
                        OTP = backendotp;
                    }
                });

        SubmitBtx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(OTP!=null){
                    progressBar.setVisibility(View.VISIBLE);
                    String InOTP = OTPInput.getText().toString();
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            OTP,InOTP
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        progressBar.setVisibility(View.GONE);
                                        System.out.println("Verification Successfull");
                                        Toast.makeText(Show_details.this,"Verification Successfull",Toast.LENGTH_LONG).show();
                                        String ID = getIntent().getStringExtra("ID");
                                        //System.out.println(ID);
                                        Intent intent = new Intent(Show_details.this,Voting.class);
                                        intent.putExtra("ID",ID);
                                        startActivity(intent);
                                    }
                                    else{
                                        progressBar.setVisibility(View.GONE);
                                        System.out.println("Wrong OTP");
                                        Toast.makeText(Show_details.this,"Wrong OTP",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(Show_details.this, "Verification failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}