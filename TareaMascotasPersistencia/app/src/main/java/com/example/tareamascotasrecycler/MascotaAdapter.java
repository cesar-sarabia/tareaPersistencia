package com.example.tareamascotasrecycler;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tareamascotasrecycler.db.BaseDatos;
import com.example.tareamascotasrecycler.db.ConstructorMascotas;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {
    ArrayList<MascotaDataModel> mascotas;
    Activity activity;

    public MascotaAdapter(ArrayList<MascotaDataModel> mascotas , Activity activity){
        this.mascotas=mascotas;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_view_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder holder, int position) {
        final MascotaDataModel mascota = mascotas.get(position);
        holder.imgPet.setImageResource(mascota.getImage());
        holder.tvNamePet.setText(mascota.getName());
        holder.tvfavsPet.setText(String.valueOf(mascota.getFavs()+"Likes"));



        holder.tvVotePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "like",Toast.LENGTH_SHORT).show();
                ConstructorMascotas constructorMascotas= new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);
                holder.tvfavsPet.setText( String.valueOf( constructorMascotas.obtenerLikesMascota(mascota.getID()) ));
            }
        });




    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends  RecyclerView.ViewHolder{
        private ImageView imgPet;
        private TextView tvNamePet;
        private TextView tvfavsPet;
        private ImageButton imbGoFavs;
        private  ImageButton tvVotePet;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPet=(ImageView) itemView.findViewById(R.id.imvPet);
            tvNamePet = (TextView) itemView.findViewById(R.id.tvNamePet);
            tvfavsPet = (TextView) itemView.findViewById(R.id.tvTotalFavs);
            tvVotePet =(ImageButton) itemView.findViewById(R.id.tvVote);
            //imbGoFavs = (ImageButton) itemView.findViewById(R.id.toolBar);
        }
    }

}
