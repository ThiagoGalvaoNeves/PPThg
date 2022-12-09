package com.example.gravidinhas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gravidinhas.banco.Usuario;
import com.example.gravidinhas.banco.UsuarioDAO;

public class CadastroActivity extends AppCompatActivity {

    Button btnConcluir;
    EditText txtNome, txtLogin, txtSenha;
    String UsuarioNome, UsuarioLogin, UsuarioSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro);

        btnConcluir = findViewById(R.id.btnCadastrar);
        txtNome = findViewById(R.id.editTextNome);
        txtLogin = findViewById(R.id.editTextEmail);
        txtSenha = findViewById(R.id.editTextSenha);

        Intent intent = getIntent();

        btnConcluir.setOnClickListener(v -> {
            UsuarioNome = txtNome.getText().toString();
            UsuarioLogin = txtLogin.getText().toString();
            UsuarioSenha = txtSenha.getText().toString();

            Usuario usuario = new Usuario(UsuarioLogin, UsuarioSenha, UsuarioNome);

            UsuarioDAO usuarioDAO = new UsuarioDAO(CadastroActivity.this);

            try {
                usuarioDAO.cadastrarUsuario(usuario);
                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}