package com.numa.cardmax.cardmaxu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;


public class CrearPublicacion extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private FirebaseDatabase mDatabase;
    private DatabaseReference refDb;
    ObjetoMuro muro_publicaciones;
    private EditText edit_titulo;
    private EditText edit_contenido;
    private String imagen_publicacion;
    private Long nombre_imagen;
    private VideoView video;
    private String titulo_publicacion;
    private String contenido_publicacion;
    private Calendar c = Calendar.getInstance();
    private String fecha_publicacion;
    private String video_publicacion;
    private int comentarios_publicacion = 0;
    private int likes_publicacion = 0;
    private int dislikes_publicacion = 0;
    private ProgressBar barra ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_publicacion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




        video = (VideoView)findViewById(R.id.videoView);
        edit_titulo = (EditText) findViewById(R.id.titulo_publicacion);
        edit_contenido = (EditText) findViewById(R.id.contenido_publicacion);
        Button galeria = (Button) findViewById(R.id.btn_galeria);
        Button foto = (Button) findViewById(R.id.btn_camara);
        mDatabase = FirebaseDatabase.getInstance();
        refDb = mDatabase.getReference();
        nombre_imagen = System.currentTimeMillis();
        c.set(Calendar.DATE, 0);
        fecha_publicacion = c.getTime().toString();




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

        final ImageView miniatura = findViewById(R.id.contenedor_imagen);
        Button aceptar = findViewById(R.id.btn_aceptar);


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            miniatura.setImageBitmap(imageBitmap);


            aceptar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final String id = refDb.push().getKey();
                    barra = (ProgressBar)findViewById(R.id.progressBar);
                    barra.setProgress(0);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
                    byte[] byteArray = stream.toByteArray();

                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    // Create a storage reference from our app
                    final StorageReference storageRef = storage.getReference();
                    // Create a reference to "mountains.jpg"
                    StorageReference mountainsRef = storageRef.child("images/"+nombre_imagen);
                    // Create a reference to 'images/mountains.jpg'
                    StorageReference mountainImagesRef = storageRef.child("images/"+nombre_imagen);
                    Toast.makeText(CrearPublicacion.this, mountainImagesRef.getDownloadUrl().toString(),Toast.LENGTH_LONG );
                    // While the file names are the same, the references point to different files
                    mountainsRef.getName().equals(mountainImagesRef.getName());    // true
                    mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

                    UploadTask uploadTask;
                    uploadTask= mountainsRef.putBytes(byteArray);

                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                            // ...
                            storageRef.child("images/"+nombre_imagen).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // Got the download URL for 'users/me/profile.png'
                                    imagen_publicacion =uri.toString();
                                    titulo_publicacion = edit_titulo.getText().toString();
                                    contenido_publicacion = edit_contenido.getText().toString();

                                    muro_publicaciones = new ObjetoMuro(titulo_publicacion,contenido_publicacion,
                                            fecha_publicacion,imagen_publicacion,video_publicacion,
                                            comentarios_publicacion,likes_publicacion,dislikes_publicacion);
                                    refDb.child("muro_publicaciones").child(id).setValue(muro_publicaciones);
                                    }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                    System.out.println("ERROR"+exception.getMessage());
                                }
                            });

                            Toast.makeText(CrearPublicacion.this,"Publicado",Toast.LENGTH_LONG);
                            Intent aceptarx;
                            aceptarx = new Intent(CrearPublicacion.this, MainActivity.class);
                            startActivity(aceptarx);
                        }
                    });
                }
            });
        }

        final Uri path = data.getData();
        if(path != null){
           String buscar = path.toString();
           System.out.println(buscar);
           int buscando = buscar.indexOf("video");
            if(buscando != -1) {
                video.setVisibility(View.VISIBLE);
                video.setVideoURI(path);
                video.start();
            }else{
                video.setVisibility(View.INVISIBLE);
                miniatura.setImageURI(path);

            }
            aceptar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    // Create a storage reference from our app
                    final StorageReference storageRef = storage.getReference();
                    // Create a reference to "mountains.jpg"
                    final String id = refDb.push().getKey();
                    UploadTask uploadTask ;
                    final StorageReference riversRef = storageRef.child("images/"+nombre_imagen);
                    uploadTask = riversRef.putFile(path);
                    // Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                            // ...
                            storageRef.child("images/"+nombre_imagen).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {


                                    imagen_publicacion =uri.toString();
                                    titulo_publicacion = edit_titulo.getText().toString();
                                    contenido_publicacion = edit_contenido.getText().toString();

                                    muro_publicaciones = new ObjetoMuro(titulo_publicacion,contenido_publicacion,
                                            fecha_publicacion,imagen_publicacion,video_publicacion,
                                            comentarios_publicacion,likes_publicacion,dislikes_publicacion);
                                    refDb.child("muro_publicaciones").child(id).setValue(muro_publicaciones);
                                }
                            });

                            Toast.makeText(CrearPublicacion.this, "Publicado", Toast.LENGTH_SHORT).show();
                        }
                    });
                  Intent aceptarx;
                    aceptarx = new Intent(CrearPublicacion.this, MainActivity.class);
                    startActivity(aceptarx);
                }
            });
        }

    }




}
