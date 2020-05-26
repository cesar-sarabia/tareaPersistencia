package com.example.tareamascotasrecycler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.tareamascotasrecycler.db.ConstructorMascotas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ConstructorMascotas constructorMascotas;

    private  ArrayList<MascotaDataModel> data;
    private  RecyclerView rv_listPet;
    private  RecyclerView.Adapter adapter;

    private TextView tv_favs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar myChildToolBar = (Toolbar) findViewById(R.id.toolBarMain);
        setSupportActionBar(myChildToolBar);


        rv_listPet =(RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        rv_listPet.setLayoutManager(llm);
        initiateData();
        initiateAdapter();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.action_favorite:
                goNextActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goNextActivity(){
        Intent intent =new Intent(this,PetsFavorite.class);
        startActivity(intent);
    }


    private void initiateData(){

        constructorMascotas = new ConstructorMascotas(this);
        data= constructorMascotas.obtenerMascotas();

    }

    private  void initiateAdapter(){
        adapter = new MascotaAdapter(data, this);
        rv_listPet.setAdapter(adapter);
    }


}
