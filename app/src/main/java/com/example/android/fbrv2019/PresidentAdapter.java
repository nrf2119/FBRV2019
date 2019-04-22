package com.example.android.fbrv2019;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class PresidentAdapter extends RecyclerView.Adapter<PresidentViewHolder> {
    private List<President> presidents;
    private Context context;

   //local data constructor
    public PresidentAdapter(List<President> presidents, Context context) {
        this.presidents = presidents;
        this.context = context;
    }

    //FB data constructor
    public PresidentAdapter(DatabaseReference presidentsRef, Context context) {
        this.context = context;
        presidents = new ArrayList<>();
        presidentsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                President p = dataSnapshot.getValue(President.class);
                presidents.add(p);
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                President p = dataSnapshot.getValue(President.class);
                presidents.remove(p);
                notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public PresidentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_president, parent, false);
        return new PresidentViewHolder(view, context);
    }


    @Override
    public void onBindViewHolder(PresidentViewHolder holder, int position) {
        President president = presidents.get(position);
        holder.getPresidentNameView().setText(president.getName());
        holder.getPresidentInfoView().setText(president.getInfo());
        //holder.getPresidentPhotoView().setImageResource(president.getPhotoId());
    }

    @Override
    public int getItemCount() {
        return presidents.size();
    }
}
