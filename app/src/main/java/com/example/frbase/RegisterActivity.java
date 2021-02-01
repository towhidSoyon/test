package com.example.frbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailText, passText;
    private Button signUpButton;
    private TextView textView;
    private ProgressBar progressBar;


    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findSection();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailText.getText().toString();
                String pass=passText.getText().toString();


                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
                    if(pass.length() >= 6){
                        userSignUp(email,pass);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Password should at least SIX Character", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Fill all the field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void userSignUp(String email, String pass) {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser currentUser=firebaseAuth.getCurrentUser();
                    currentUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            firebaseAuth.signOut();
                            Toast.makeText(RegisterActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this, ""+task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findSection() {

        emailText=findViewById(R.id.emailId);
        passText=findViewById(R.id.passwordId);
        textView=findViewById(R.id.alreadyAccountId);
        signUpButton=findViewById(R.id.signUpId);
        progressBar=findViewById(R.id.progressBarId);
        progressBar.setVisibility(View.GONE);
    }
}