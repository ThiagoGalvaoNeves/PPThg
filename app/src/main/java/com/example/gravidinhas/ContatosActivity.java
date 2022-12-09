package com.example.gravidinhas;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContatosActivity extends AppCompatActivity {

    private static final int RESULT_PICK_CONTACT = 1;
    private static final int RESULT_SELECT_IMAGE = 2;
    TextView txtName, txtNumero;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_contatos);
        txtName = (TextView) findViewById(R.id.txtContatoNome);
        txtNumero = (TextView) findViewById(R.id.txtContatoNum);
        img = (ImageView) findViewById(R.id.imageView1);
    }

    public void selecionarContact(View view) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }
    public void visualizarGaleria(View view) {
        Intent intent =     new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), RESULT_SELECT_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    Cursor cursor = null;
                    try {
                        String numero = null;
                        String nome = null;
                        Uri uri = data.getData();
                        cursor = getContentResolver().query(uri, null, null, null, null);
                        cursor.moveToFirst();
                        int telIndex = cursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER);
                        int nomeIndex = cursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        numero = cursor.getString(telIndex);
                        nome = cursor.getString(nomeIndex);
                        txtName.setText("Nome: ".concat(nome));
                        txtNumero.setText("Telefone : ".concat(numero));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case RESULT_SELECT_IMAGE:
                    Uri imagemSelecionada = data.getData();
                    img.setImageURI(imagemSelecionada);
                    break;

            }
        }
    }
    public void Tela_Main(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}