package com.example.gravidinhas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gravidinhas.banco.Usuario;
import com.example.gravidinhas.banco.UsuarioDAO;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private static final String FILE_NAME = "usuarioLogado.json";
    private EditText userLogin, userSenha;
    private Button btnLogin;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    private TextView btnCadastro;
    CheckBox mRemember;
    SharedPreferences preferences;
    boolean isRemembered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);


        userLogin = findViewById(R.id.txtEmailLogin);
        userSenha = findViewById(R.id.txtSenhaLogin);
        btnLogin = findViewById(R.id.btnentrar);
        btnCadastro = findViewById(R.id.btncadastro);
        mRemember = findViewById(R.id.checkBox);

        preferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        isRemembered = preferences.getBoolean("CHECKBOX", false);

        if(isRemembered){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String name = userLogin.getText().toString();
                String senha = userSenha.getText().toString();
                boolean checked = mRemember.isChecked();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("NAME", name);
                editor.putString("SENHA", senha);
                editor.putBoolean("CHECKBOX", checked);
                editor.apply();

                Toast.makeText(LoginActivity.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();

                finish();
            }

        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastro = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(cadastro);
            }
        });

        btnLogin.setOnClickListener(v ->{
            String login = String.valueOf(userLogin.getText());
            String senha = String.valueOf(userSenha.getText());

            validarCampos();

            usuarioDAO = new UsuarioDAO(getApplicationContext());

            if (usuarioDAO.verificarLogin(login, senha)){
                Usuario usuario = usuarioDAO.buscarUsuario(login);

                Gson gson = new Gson();
                String json = gson.toJson(usuario);
                gravarDados(json);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(LoginActivity.this, "Usuário ou senha não correspondem", Toast.LENGTH_SHORT).show();
            }
        });
    }



    // ARMAZENAR DADOS
    private void gravarDados(String json) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(json.getBytes());
            Toast.makeText(this, "Usuário logado.", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // VALIDAR CAMPOS
    private void validarCampos(){
        boolean verificacao = false;

        String email = userLogin.getText().toString();
        String senha = userSenha.getText().toString();

        if (verificacao = campoNulo(email)) {
            userLogin.requestFocus();
            Toast.makeText(this, "Preencha o campo e-mail.", Toast.LENGTH_SHORT).show();
        } else if (verificacao = campoNulo(senha)) {
            userSenha.requestFocus();
            Toast.makeText(this, "Preencha o campo senha.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean campoNulo (String campo){
        boolean verificacao = (TextUtils.isEmpty(campo) || campo.trim().isEmpty());
        return verificacao;
    }

    // SAVED INSTANCE
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String email = userLogin.getText().toString();
        outState.putString("Email", email);
    }

    public void back(View view){
        Intent voltar = new Intent(this, MainActivity.class);
        startActivity(voltar);
    }
    public void cadastrotela(View view){
        Intent voltar = new Intent(this, CadastroActivity.class);
        startActivity(voltar);
    }
}