package com.example.gravidinhas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Vizualizar extends AppCompatActivity {

    TextView txtVizualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_vizualizar);
        txtVizualizar = (TextView) findViewById(R.id.textVizualizar);
    }

    public void showPublicData(View view) {

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        File file = new File(folder, "comentario.txt");
        String data = getdata(file);
        if (data != null) {
            txtVizualizar.setText(data);
        } else {
            txtVizualizar.setText("Anotações do caderno");
        }
    }


    private String getdata(File myfile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myfile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void back(View view){
        Intent voltar = new Intent(this, Externo.class);
        startActivity(voltar);
    }
}