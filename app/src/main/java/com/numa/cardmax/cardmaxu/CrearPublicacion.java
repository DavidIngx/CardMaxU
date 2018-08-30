package com.numa.cardmax.cardmaxu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class CrearPublicacion extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_publicacion);

        Button galeria = findViewById(R.id.btn_galeria);
        Button foto = findViewById(R.id.btn_camara);


        galeria.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/");
                startActivityForResult(galleryIntent.createChooser(galleryIntent,"Seleccione la foto"),10);

            }
        });
        foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }


            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        ImageView miniatura = findViewById(R.id.contenedor_imagen);
        Button aceptar = findViewById(R.id.btn_aceptar);


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            miniatura.setImageBitmap(imageBitmap);


            aceptar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();


                    Intent anotherIntent = new Intent(CrearPublicacion.this, MainActivity.class);
                    anotherIntent.putExtra("image", byteArray);
                    startActivity(anotherIntent);

                }
            });







        }

        final Uri path = data.getData();
        if(path != null){

            miniatura.setImageURI(path);


            aceptar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent aceptarx;
                    aceptarx = new Intent(CrearPublicacion.this, MainActivity.class);
                    aceptarx.putExtra("foto", path.toString());
                    startActivity(aceptarx);
                }
            });




        }

    }

}
