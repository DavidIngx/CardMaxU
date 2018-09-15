package com.numa.cardmax.cardmaxu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.support.design.widget.BottomNavigationView;
import android.widget.VideoView;
import android.support.v7.widget.Toolbar;
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
    private ImageButton cerrar;
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
    private Button clear;
    private ImageButton foto, addvideo;
    private ImageView miniatura;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_publicar, menu);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_publicacion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar actionbarx = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(actionbarx);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        addvideo = (ImageButton)findViewById(R.id.btn_video_add);
        clear =(Button)findViewById(R.id.btn_clear);
        cerrar = (ImageButton)findViewById(R.id.close);
        video = (VideoView)findViewById(R.id.videoView);
        edit_titulo = (EditText) findViewById(R.id.titulo_publicacion);
        edit_contenido = (EditText) findViewById(R.id.contenido_publicacion);
        ImageButton galeria = (ImageButton) findViewById(R.id.btn_galeria);
        foto = (ImageButton) findViewById(R.id.btn_foto);
        mDatabase = FirebaseDatabase.getInstance();
        refDb = mDatabase.getReference();
        nombre_imagen = System.currentTimeMillis();
        c.set(Calendar.DATE, 0);
        fecha_publicacion = c.getTime().toString();
        miniatura = findViewById(R.id.contenedor_imagen);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear.setVisibility(View.INVISIBLE);
                miniatura.setImageBitmap(null);
                miniatura.setImageURI(null);
                video.setVisibility(View.INVISIBLE);
                video.stopPlayback();
                video.setVideoURI(null);           }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


        addvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("video/*");
                startActivityForResult(galleryIntent.createChooser(galleryIntent,"Seleccione un Video"),10);

            }
        });

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


        Button aceptar = findViewById(R.id.btn_publicar);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            miniatura.setImageBitmap(imageBitmap);
            miniatura.setVisibility(View.VISIBLE);
            clear.setVisibility(View.VISIBLE);
            aceptar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final String id = refDb.push().getKey();


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

                        }
                    });

                    Toast.makeText(CrearPublicacion.this,"Publicado",Toast.LENGTH_LONG).show();
                    Intent aceptarx;
                    aceptarx = new Intent(CrearPublicacion.this, MainActivity.class);
                    startActivity(aceptarx);
                }
              });
        }

        final Uri path = data.getData();
        if(path != null){
            clear.setVisibility(View.VISIBLE);
           String buscar = path.toString();
           final int buscando = buscar.indexOf("video");
            if(buscando != -1) {
                miniatura.setVisibility(View.INVISIBLE);
                video.setVisibility(View.VISIBLE);
                video.setVideoURI(path);
                video.setMediaController(new MediaController(this));
                video.start();
            }else{
                video.setVisibility(View.INVISIBLE);
                miniatura.setVisibility(View.VISIBLE);
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
                    final StorageReference riversRef;

                    if(buscando != -1) {
                        riversRef = storageRef.child("video/"+nombre_imagen);

                    }else{
                        riversRef = storageRef.child("images/"+nombre_imagen);

                    }



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

                            if(buscando != -1) {
                                storageRef.child("video/"+nombre_imagen).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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

                            }else{



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


                            }



                        }
                    });
                    Toast.makeText(CrearPublicacion.this, "Publicado", Toast.LENGTH_SHORT).show();
                    Intent aceptarx;
                    aceptarx = new Intent(CrearPublicacion.this, MainActivity.class);
                    startActivity(aceptarx);

                }
            });
        }
    }




}
