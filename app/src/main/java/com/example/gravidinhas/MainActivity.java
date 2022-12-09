package com.example.gravidinhas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

    }
    public void tela3m(View view){
        Intent voltar = new Intent(this, ThreeMonthActivity.class);
        startActivity(voltar);
    }
    public void tela6m(View view){
        Intent voltar = new Intent(this, SixMonthActivity.class);
        startActivity(voltar);
    }
    public void tela1y(View view){
        Intent voltar = new Intent(this, OneYearActivity.class);
        startActivity(voltar);
    }
    public void telaInfo(View view){
        Intent info = new Intent(this, InfoActivity.class);
        startActivity(info);
    }
    public void telaLogin(View view){
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }
    public void telaGeoLoc(View view){
        Intent login = new Intent(this, GeoLocationActivity.class);
        startActivity(login);
    }
    public void telasensor(View view){
        Intent login = new Intent(this, SensorLumi.class);
        startActivity(login);
    }
    public void telaCaderno(View view){
        Intent login = new Intent(this, Externo.class);
        startActivity(login);
    }
}