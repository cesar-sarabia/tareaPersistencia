package com.example.tareamascotasrecycler.db;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.example.tareamascotasrecycler.MascotaDataModel;
import com.example.tareamascotasrecycler.R;

import java.util.ArrayList;

public class ConstructorMascotas {
    private Context context;
    private static  final int VOTO=1;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<MascotaDataModel> obtenerMascotas(){
        BaseDatos db = new BaseDatos(context);
        //Toast.makeText(context, "est:" + context.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        insertarContacto(db);
            //return db.obtenerMascotasFavoritas();
        return db.obtenerMascotasAll();


    }

    public void insertarContacto(BaseDatos db){
        //db.insertarMascota(data());

        ContentValues contentValues = new ContentValues();
        String [] nameArray={"Canelo","Bastian","siblins","duli","dino","happy hipo","Hari","crazy","nemo desarrollado"};
        Integer [] drawableArray={
                R.drawable.pet_cro, R.drawable.pet_dra, R.drawable.pet_cat,
                R.drawable.pet_caterpillar, R.drawable.pet_dino, R.drawable.pet_happy_hipo,
                R.drawable.pet_lion, R.drawable.pet_parrot, R.drawable.pet_shark};

        for (int i = 0; i< nameArray.length; i++){
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE, nameArray[i] );
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN, drawableArray[i] );
            db.insertarMascota(contentValues);
        }


    }

    public void darLikeMascota(MascotaDataModel mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues= new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA, mascota.getID());
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTA_LIKES_CANTIDAD, VOTO);
        db.insertarMascotaLike(contentValues);
    }

    public int obtenerLikesMascota(int mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<MascotaDataModel> obtenerSoloFavoritos(){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerMascotasFavoritas();
    }

}
