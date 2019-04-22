package com.example.android.fbrv2019;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PresidentViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView presidentNameView;
    private TextView presidentInfoView;
    private ImageView presidentPhotoView;
    private Context context;


    public PresidentViewHolder(View itemView, final Context context) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        presidentNameView = (TextView) itemView.findViewById(R.id.person_name);
        presidentInfoView = (TextView) itemView.findViewById(R.id.person_info);
        //presidentPhotoView = (ImageView) itemView.findViewById(R.id.person_photo);
        this.context = context;


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, presidentNameView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public TextView getPresidentNameView() {
        return presidentNameView;
    }

    public TextView getPresidentInfoView() {
        return presidentInfoView;
    }

    public ImageView getPresidentPhotoView() {
        return presidentPhotoView;
    }
}
