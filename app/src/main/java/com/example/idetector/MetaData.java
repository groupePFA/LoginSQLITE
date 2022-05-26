package com.example.idetector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idetector.adapters.MyAdapter;
import com.example.idetector.beans.imagemap;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MetaData extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton floatingActionButton_metadonnee;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    Button  annotateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_data);
        getSupportActionBar().hide();
        annotateButton = findViewById(R.id.annotateButton);



        annotateButton.setOnClickListener((View.OnClickListener) this);

        //Firebase data to recycleView
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<imagemap> options =
                new FirebaseRecyclerOptions.Builder<imagemap>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("images"),
                                imagemap.class)
                        .build();
        myAdapter = new MyAdapter(options);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onClick(View v) {
        if(v== annotateButton) {
            Intent it=new Intent(this,ImageActivity.class);
            startActivity(it);
            finish();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}