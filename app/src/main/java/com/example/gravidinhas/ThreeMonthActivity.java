package com.example.gravidinhas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ThreeMonthActivity extends AppCompatActivity {
    private ImageView open_tutorial3m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_three_month);

        open_tutorial3m = (ImageView) findViewById(R.id.img_tutorialthree);

        open_tutorial3m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=e5gD0O6k2r8")));
            }
        });
    }
    public void Tela_Main(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}