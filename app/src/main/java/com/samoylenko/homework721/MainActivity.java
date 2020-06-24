package com.samoylenko.homework721;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        Button button = findViewById(R.id.button_search);
        final EditText editText = findViewById(R.id.editText_geo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geo = editText.getText().toString();
                if (!geo.isEmpty()) {
                    toIntent(getGeoType(geo));
                }
            }
        });

    }

    public void toIntent(String geo) {
        Uri uri = Uri.parse(geo);
        Intent openGeo = new Intent(Intent.ACTION_VIEW, uri);
        if (openGeo.resolveActivity(getPackageManager()) != null) {
            startActivity(openGeo);
        } else {
            Toast.makeText(this, "Не найдено приложение для открытия"
                    , Toast.LENGTH_LONG)
                    .show();
        }

    }

    public String getGeoType(String geo) {
        char c = geo.charAt(0);
        if (Character.isLetter(c)) {
            return "geo:?q=" + geo;
        } else {
            return "geo:" + geo;
        }
    }
}
