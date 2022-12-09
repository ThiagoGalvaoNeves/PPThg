package com.example.gravidinhas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class OneYearActivity extends AppCompatActivity {
    private ImageView open_tutorial1y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_one_year);

        open_tutorial1y = (ImageView) findViewById(R.id.img_tutorialyear);

        open_tutorial1y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=00F4kGnoeW8")));
            }
        });
    }
    public void Tela_Main(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}