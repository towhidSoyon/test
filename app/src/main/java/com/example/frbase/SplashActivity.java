package com.example.frbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference mRef = firebaseDatabase.getReference("User_Info");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if(currentUser != null){
            mRef.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        goToMain();
                    }
                    else{
                        goToLogin();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SplashActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    goToLogin();
                }
            });
            goToMain();

        }
        else{
            goToLogin();
        }
    }

    private void goToMain() {
        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToLogin() {
        Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}