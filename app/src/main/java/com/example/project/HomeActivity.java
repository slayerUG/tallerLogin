package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public boolean onCreateOptionsMenu(Menu menu)  {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case R.id.configuracion:
                Toast.makeText(this, "SELECIONO CONFIGURACION", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contacto:
                Toast.makeText(this, "SELECIONO CONTACTO", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.idioma:
                Toast.makeText(this, "SELECIONO IDIOMA", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.acerca:
                Toast.makeText(this, "SELECIONO Acerca", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.salir:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
            default:
        }
        return super.onOptionsItemSelected(item);

    }

}