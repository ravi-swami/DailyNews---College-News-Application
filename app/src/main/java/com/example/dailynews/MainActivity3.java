package com.example.dailynews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity3 extends AppCompatActivity implements CustomAdapter.ItemClickListener{
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<Model> list;
    CustomAdapter adapter;

    FirebaseFirestore db;
    CollectionReference reference;
    FirebaseDatabase database;

    String date, imageUrl, content, title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

//        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"black\">" +getString(R.string.app_name) + "</font>"));

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity3.this));


        db = FirebaseFirestore.getInstance();
        list = new ArrayList<Model>();

        db.collection("News").orderBy("date", Query.Direction.DESCENDING)
            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                try {
                    for(int i=0; i<task.getResult().size(); i++){
                        title = task.getResult().getDocuments().get(i).getString("title");
                        date = task.getResult().getDocuments().get(i).getString("date");
                        imageUrl = task.getResult().getDocuments().get(i).getString("imageUrl");
                        content = task.getResult().getDocuments().get(i).getString("content");

                        list.add( new Model(title, date, imageUrl, content));
                    }

                    adapter = new CustomAdapter(list, MainActivity3.this, MainActivity3.this);
                    recyclerView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();

                }
                catch (Exception e){
                    Toast.makeText(MainActivity3.this, "No data found", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity3.this, "Something went wrong......", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));




        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                db.collection("News").orderBy("date", Query.Direction.DESCENDING)
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        try {
                            for(int i=0; i<task.getResult().size(); i++){
                                title = task.getResult().getDocuments().get(i).getString("title");
                                date = task.getResult().getDocuments().get(i).getString("date");
                                imageUrl = task.getResult().getDocuments().get(i).getString("imageUrl");
                                content = task.getResult().getDocuments().get(i).getString("content");

                                if (!task.getResult().getDocuments().get(i).getString("title").equalsIgnoreCase(list.get(i).getTitle())){
                                    list.add(i, new Model(title, date, imageUrl, content));
                                }

                            }

                            adapter = new CustomAdapter(list, MainActivity3.this, MainActivity3.this);
//
                            recyclerView.setAdapter(adapter);

                            adapter.notifyDataSetChanged();

                            swipeRefreshLayout.setRefreshing(false);

                            Toast.makeText(MainActivity3.this, "Refreshed", Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity3.this, "No data found", Toast.LENGTH_SHORT).show();
                            swipeRefreshLayout.setRefreshing(false);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity3.this, "Something went wrong......", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });


    }

    @Override
    public void onItemCLick(int position) {
        Intent intent = new Intent(MainActivity3.this, NewsContent.class);
        intent.putExtra("title", list.get(position).getTitle());
        intent.putExtra("date", list.get(position).getDate());
        intent.putExtra("imageUrl", list.get(position).getImageUrl());
        intent.putExtra("content", list.get(position).getContent());
        startActivity(intent);
    }
}