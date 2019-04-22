package com.example.android.fbrv2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    private FirebaseAuth auth;

    DatabaseReference userReference;
    private FirebaseAuth.AuthStateListener authListener;

    RecyclerView recyclerView;
    PresidentAdapter presidentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        //Basics needed for RV as always
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    userReference = database.getReference(user.getUid());
                    presidentAdapter = new PresidentAdapter(userReference.child("presidents"), MainActivity.this);
                    recyclerView.setAdapter(presidentAdapter);
                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        };



    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override

    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

    public void addPresident(View view) {
        President newPrez;
        int num = (int) (Math.random() * 3);
        if (num == 0)
            newPrez = new President ("Bill Clinton", "1993-2001");
        else if (num == 1)
            newPrez= new President("George W. Bush", "2001-2009");
        else
            newPrez = new President("Barack Obama", "2009-2017");
        userReference.child("presidents").push().setValue(newPrez);
        //presidents.add(newPrez);
        recyclerView.setAdapter(presidentAdapter);

    }
}
