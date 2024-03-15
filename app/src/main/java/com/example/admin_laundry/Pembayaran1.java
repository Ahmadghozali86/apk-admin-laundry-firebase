package com.example.admin_laundry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Pembayaran1 extends AppCompatActivity {

    private EditText namapelanggan, beratpakaian, hargabasah, saldopembelian, saldokembalian;
    private Spinner namaadmin;
    Double ketentuanHargaBasah = 4500.0 ;
    Double totalBerat, totalHarga, totalPembelian,totalPengembalian;
    Button hitungbiaya, hitungpengembalian, kirim, clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran1);


        namapelanggan = findViewById(R.id.namaPelanggan);
        beratpakaian = findViewById(R.id.beratPakaian);
        hargabasah= findViewById(R.id.hargaBasah);
        saldopembelian = findViewById(R.id.saloPembelian);
        saldokembalian = findViewById(R.id.saloPengembalian);

        namaadmin =findViewById(R.id.namaAdmin);

        hitungbiaya = findViewById(R.id.hitungBiayaBasah);
        hitungpengembalian = findViewById(R.id.hitungPengembalian);
        kirim = findViewById(R.id.kirim);
        clear = findViewById(R.id.clear);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.nama_admin, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        namaadmin.setAdapter(adapter1);

        namaadmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String admin = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hapus();
            }
        });
        hitungbiaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Biaya();

            }
        });

        hitungpengembalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kembalian();
            }
        });
        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KirimPesanan();

            }
        });
    }
    public void Biaya(){

        totalBerat = Double.parseDouble(beratpakaian.getText().toString());

        totalHarga = ketentuanHargaBasah*totalBerat;
        hargabasah.setText(String.valueOf(totalHarga));

    }

    public  void Kembalian(){
        totalPembelian = Double.parseDouble(saldopembelian.getText().toString());

        totalPengembalian = totalPembelian - totalHarga;
        saldokembalian.setText(String.valueOf(totalPengembalian));
    }
    public void KirimPesanan(){
        String namaku = namapelanggan.getText().toString();
        String adminku = namaadmin.getSelectedItem().toString();
        Double beratku = totalBerat;
        Double biayaku = totalHarga;
        Double pembelianku = Double.parseDouble(saldopembelian.getText().toString());
        Double kembalianku = totalPengembalian;

        DataPesanan dataPesanan = new DataPesanan(namaku, adminku, beratku,biayaku, pembelianku,kembalianku);

        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        currentDate = currentDate.replace(".", "");
        currentDate = currentDate.replace(" ", "_");
        currentDate = currentDate.replace(":", "-");
        FirebaseDatabase.getInstance().getReference("Data Pesanan Cuci Basah").child(currentDate)
                .setValue(dataPesanan).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Pembayaran1.this, "Saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Pembayaran1.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
    private void Hapus() {
        namapelanggan.getText().clear();
        beratpakaian.getText().clear();
        hargabasah.getText().clear();
        saldopembelian.getText().clear();
        saldokembalian.getText().clear();
        namaadmin.setSelection(0);

        Toast.makeText(this, "Berhasil di Hapus", Toast.LENGTH_SHORT).show();
    }
}