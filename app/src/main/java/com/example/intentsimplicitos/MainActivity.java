package com.example.intentsimplicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCLickBtn(View view) {

        Intent i = new Intent();
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
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:(+34)677157448"));
                startActivity(i);
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