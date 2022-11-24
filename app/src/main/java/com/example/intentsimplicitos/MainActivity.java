package com.example.intentsimplicitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int LLAMADA_TELEFONO = 0;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i = new Intent();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LLAMADA_TELEFONO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "EL USUARIO HA CONCEDIDO EL PERMISO SOLICITADO", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        } else {
            Toast.makeText(this, "EL USUARIO NO HA CONCEDIDO EL PERMISO SOLICITADO", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCLickBtn(View view) {

        //Intent i = new Intent();
        switch (view.getId()) {
            case R.id.btnContactos:
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("content://contacts/people/"));
                startActivity(i);
                break;
            case R.id.btnDial:
                i.setAction(Intent.ACTION_DIAL);
                startActivity(i);
                break;
            case R.id.btnMarcarNumero:
                i.setAction(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:(+34)677157448"));

                //Testear la versión de API
                if (Build.VERSION.SDK_INT >= 23) {
                    //Verificar si el permiso está concedido
                    if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        //Permiso concedido
                        startActivity(i);
                    } else {
                        //Permiso no concedido
                        requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO);
                    }
                } else {
                    startActivity(i);
                }
                break;
            case R.id.btnMarcarNumero2:
                i.setAction(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:(+34)555000000"));

                //Testear directamente el método
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    //Permiso concedido
                    startActivity(i);
                } else {
                    //Permiso no concedido
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, LLAMADA_TELEFONO);
                }
                break;
            case R.id.btnNavegador:
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.edu.xunta.gal/portal"));
                startActivity(i);
                break;
            case R.id.btnMapa:
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:42.25,-8.68"));
                startActivity(i);
                break;
        }

    }
}