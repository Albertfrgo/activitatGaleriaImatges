package com.example.activitatgaleriaimatges;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 1;
    private ImageView vistaImatge;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button botoObrirCamera = findViewById(R.id.buttonCamera);
        vistaImatge = findViewById(R.id.imageView);

        botoObrirCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
    }

    private void openCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int codiRequerit, int codiResultant, Intent dades) {
        super.onActivityResult(codiRequerit, codiResultant, dades);

        if(codiRequerit == CAMERA_REQUEST_CODE && codiResultant == RESULT_OK){
            Bitmap thumbnail = (Bitmap) dades.getExtras().get("data");
            vistaImatge.setImageBitmap(thumbnail);
        }
    }
}
