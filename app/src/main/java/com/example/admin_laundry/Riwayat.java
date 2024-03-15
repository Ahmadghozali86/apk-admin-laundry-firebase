package com.example.admin_laundry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Riwayat extends Fragment {

    RecyclerView recyclerView, recyclerView1;
    List<DataPesanan> dataList, dataList1;
    ValueEventListener eventListener, eventListener1;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    double totalBiayaCuciBasah = 0;
    double totalBiayaCuciKering = 0;
    TextView omset;


    public Riwayat(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.riwayat, container, false);

        omset = view.findViewById(R.id.omsett);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView1 = view.findViewById(R.id.recyclerView1);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(requireContext(), 1);
        recyclerView1.setLayoutManager(gridLayoutManager1);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();
        dataList1 = new ArrayList<>();

        MyAdapter adapter = new MyAdapter(requireContext(), dataList);
        recyclerView.setAdapter(adapter);
        MyAdapter adapter1 = new MyAdapter(requireContext(), dataList1);
        recyclerView1.setAdapter(adapter1);

        databaseReference = FirebaseDatabase.getInstance().getReference("Data Pesanan Cuci Kering");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Data Pesanan Cuci Basah");

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();

                totalBiayaCuciKering = 0;
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataPesanan dataPesanan = itemSnapshot.getValue(DataPesanan.class);
                    dataPesanan.setKey(itemSnapshot.getKey());
                    dataList.add(dataPesanan);
                    if (dataPesanan != null) {
                        totalBiayaCuciKering += dataPesanan.getBiaya_Cuci();
                    }
                }

                updateOmsetTextView();
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList1.clear();

                totalBiayaCuciBasah = 0;
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataPesanan dataPesanan = itemSnapshot.getValue(DataPesanan.class);
                    dataPesanan.setKey(itemSnapshot.getKey());
                    dataList1.add(dataPesanan);
                    if (dataPesanan != null) {
                        totalBiayaCuciBasah += dataPesanan.getBiaya_Cuci();
                    }
                }

                updateOmsetTextView();
                adapter1.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        return view;
    }

    private void updateOmsetTextView() {
        Double omsetTotal = totalBiayaCuciBasah + totalBiayaCuciKering;
        omset.setText(String.valueOf(omsetTotal));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (eventListener != null) {
            databaseReference.removeEventListener(eventListener);
        }
        if (eventListener1 != null) {
            databaseReference1.removeEventListener(eventListener1);
        }
    }
}
