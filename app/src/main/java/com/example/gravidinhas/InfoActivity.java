package com.example.gravidinhas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    Button mLogoutBtn;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_info);

        mLogoutBtn = findViewById(R.id.logout);

        preferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        String name = preferences.getString("NAME", "");

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(InfoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void abrirTel(View view ){

        Uri uri = Uri.parse("tel:5511 98888-7777");

        Intent it = new Intent(Intent.ACTION_DIAL, uri);

        startActivity(it);

    }
    public void Tela_Contato(View view) {

        Intent intent = new Intent(getApplicationContext(), ContatosActivity.class);
        startActivity(intent);
    }
    public void Tela_Main(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void telaCaderno(View view){
        Intent login = new Intent(this, Storage.class);
        startActivity(login);
    }
}
