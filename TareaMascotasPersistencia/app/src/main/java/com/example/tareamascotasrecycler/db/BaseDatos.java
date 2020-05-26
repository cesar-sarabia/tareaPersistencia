package com.example.tareamascotasrecycler.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tareamascotasrecycler.MascotaDataModel;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;
    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota="CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA + "("+
                ConstantesBaseDatos.TABLE_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_MASCOTA_NOMBRE + " TEXT, "+
                ConstantesBaseDatos.TABLE_MASCOTA_IMAGEN + " INTEGER "+
                ")";

        String queryCrearTablaLikes = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES + "("+
                ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA + " INTEGER, "+
                ConstantesBaseDatos.TABLE_MASCOTA_LIKES_CANTIDAD + " INTEGER, "+
                " FOREIGN KEY (" +ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA + " )"+
                " REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTA +"("+ConstantesBaseDatos.TABLE_MASCOTA_ID + ")"+
                ")";
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES);
        onCreate(db);
    }


    public ArrayList<MascotaDataModel> obtenerMascotasAll(){
        ArrayList<MascotaDataModel> mascotaData = new ArrayList<>();
        String query =" Select * from " + ConstantesBaseDatos.TABLE_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros =  db.rawQuery(query, null);

        while(registros.moveToNext()){
            MascotaDataModel mascota = new MascotaDataModel();
            mascota.setID(registros.getInt(0));
            mascota.setName(registros.getString(1));
            mascota.setImage(registros.getInt(2));
            mascota.setFavs(obtenerLikesMascota( registros.getInt(0) ));
            mascotaData.add(mascota);
        }

        db.close();
        return mascotaData;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA, null, contentValues);
        db.close();
    }

    public void insertarMascotaLike(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTA_LIKES, null, contentValues);
        db.close();
    }
    public int obtenerLikesMascota(int mascotaID){
        int  cantidadLikes=0;

        String query= "Select count("+ConstantesBaseDatos.TABLE_MASCOTA_LIKES_CANTIDAD +")"+
                " from "+ ConstantesBaseDatos.TABLE_MASCOTA_LIKES +
                " where " + ConstantesBaseDatos.TABLE_MASCOTA_LIKES_ID_MASCOTA  +" = " +mascotaID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registro= db.rawQuery(query, null);

        if(registro.moveToNext()){

            cantidadLikes= registro.getInt(0);
        }
        db.close();

        return cantidadLikes;
    }
    public MascotaDataModel obtenerMascota(int id){
        MascotaDataModel mascota=null;
        String query="Select * from " + ConstantesBaseDatos.TABLE_MASCOTA + " where "+
                ConstantesBaseDatos.TABLE_MASCOTA_ID +"="+ id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registro = db.rawQuery(query, null);

        if (registro.moveToNext()){
            mascota = new MascotaDataModel();
            mascota.setID(registro.getInt(0));
            mascota.setName(registro.getString(1));
            mascota.setImage(registro.getInt(2));
        }
        db.close();
        return mascota;
    }

    public ArrayList<MascotaDataModel> obtenerMascotasFavoritas(){

        ArrayList<MascotaDataModel> mascotaDataFavs= new ArrayList<>();
        ArrayList<MascotaDataModel> mascotaData= new ArrayList<>();


        mascotaDataFavs=obtenerMascotasAll();

        for (MascotaDataModel mascotaDatum : mascotaDataFavs) {
         if ( mascotaDatum.getFavs() >0 ){
             mascotaData.add(mascotaDatum);
         }
        }


        return mascotaData;
    }
}
