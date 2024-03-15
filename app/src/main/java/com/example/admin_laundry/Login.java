package com.example.admin_laundry;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Login extends AppCompatActivity {
    SharedPreferences sharedPreferences;


    Uri selectedImageUri;

    ActivityResultLauncher<Intent> imagePickLauncher;
    DataLoginAdmin currentDate;
    ImageView gambar;
    String imageURL;



    EditText usernameEditText, passwordEditText;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);


        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_login);
        gambar = findViewById(R.id.fotoLogin);

        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data != null){
                            selectedImageUri = data.getData();
                            // Set ImageView dengan gambar yang dipilih
                            gambar.setImageURI(selectedImageUri);
                        }
                    }
                });
        gambar.setOnClickListener((v)->{
            ImagePicker.with(this).cropSquare().compress(512).maxResultSize(512,512)
                    .createIntent(new Function1<Intent, Unit>() {
                        @Override
                        public Unit invoke(Intent intent) {
                            imagePickLauncher.launch(intent);
                            return null;
                        }
                    });
        });
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            selectedImageUri = data.getData();
                            gambar.setImageURI(selectedImageUri);
                        }else {
                            Toast.makeText(Login.this, "Tidak Ada Gambar Dipilih", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (selectedImageUri == null) {
                    return;
                }
                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Foto Data Login").child(selectedImageUri.getLastPathSegment());

                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setCancelable(false);
                builder.setView(R.layout.progress_layout);
                AlertDialog dialog = builder.create();
                dialog.show();

                storageReference.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri urlImage = uriTask.getResult();
                        imageURL = urlImage.toString();
                        dialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                    }
                });
                if (username.equals("cahya") && password.equals("cahya")&&selectedImageUri != null) {
                    Intent intent1 = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent1);
                    Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                } else if (username.equals("ghozy") && password.equals("ghozy")&&selectedImageUri != null) {
                    Intent intent1 = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent1);
                    Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                }else if (username.equals("roy") && password.equals("roy")&&selectedImageUri != null) {
                    Intent intent1 = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent1);
                    Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                } else {
                    // Login gagal
                    Toast.makeText(Login.this, "Login gagal", Toast.LENGTH_SHORT).show();
                }

                DataLoginAdmin dataLoginAdmin = new DataLoginAdmin(username, imageURL);

                String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                currentDate = currentDate.replace(".", ""); // Menghapus titik dari format tanggal dan waktu
                currentDate = currentDate.replace(" ", "_"); // Mengganti spasi dengan garis bawah
                currentDate = currentDate.replace(":", "-"); // Mengganti titik dua dengan tanda hubung

                FirebaseDatabase.getInstance().getReference("Data Login User").child(currentDate)
                        .setValue(dataLoginAdmin).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Login.this, "Saved", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}