package com.example.gravidinhas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnotherActivity extends AppCompatActivity {


    TextView mNameTv, mAgeTv;
    Button mLogoutBtn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_another);

        mNameTv = findViewById(R.id.nameTv);
        mAgeTv = findViewById(R.id.ageTv);
        mLogoutBtn = findViewById(R.id.logout);

        preferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        String name = preferences.getString("NAME", "");
        mNameTv.setText(name);
        int age = preferences.getInt("AGE", 0);
        mAgeTv.setText(""+age);

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(AnotherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public void Tela_Menu(View view){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}