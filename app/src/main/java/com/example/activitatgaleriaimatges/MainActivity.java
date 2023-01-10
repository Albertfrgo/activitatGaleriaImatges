package com.example.activitatgaleriaimatges;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static int RC_PHOTO_PICKER = 0;
    ActivityResultLauncher<Intent> activityResultLauncherCamera;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    ImageView vistaImatge;
    Button botoObrirGaleria;
    Button botoObrirCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botoObrirGaleria = findViewById(R.id.buttonGallery);
        botoObrirCamera = findViewById(R.id.buttonCamera);
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Uri URI=data.getData();
                            vistaImatge=findViewById(R.id.imageView);
                            vistaImatge.setImageURI(URI);
                        }
                    }
                });

        activityResultLauncherCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Bundle extBundle = data.getExtras();
                            Bitmap imageBitmap =(Bitmap) extBundle.get("data");
                            vistaImatge.setImageBitmap(imageBitmap);
                        }
                    }
                }
        );

        botoObrirGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSomeActivityForResult(null);
            }
        });

        botoObrirCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obrirCamera();
            }
        });

    }

    public void openSomeActivityForResult(View view) {
        /*Ens porta a la galeria*/
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        someActivityResultLauncher.launch(intent);
    }

    public void obrirCamera(){
        /*Ens porta a la camera*/
        Intent intent=new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        someActivityResultLauncher.launch(intent);
    }
}

