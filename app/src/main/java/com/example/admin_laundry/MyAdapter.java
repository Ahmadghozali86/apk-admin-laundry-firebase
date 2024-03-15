package com.example.admin_laundry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataPesanan> dataList;

    public MyAdapter(Context context, List<DataPesanan> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.namaPelanggan.setText(dataList.get(position).getNama_Pelanggan());
        holder.namaAdmin.setText(dataList.get(position).getNama_Admin());
        holder.beratPakaian.setText(String.valueOf(dataList.get(position).getBeratKg()));
        holder.biayaTotal.setText(String.valueOf(dataList.get(position).getBiaya_Cuci()));
        holder.saldoPembelian.setText(String.valueOf(dataList.get(position).getSaldo_Pembelian()));
        holder.saldoPengembalian.setText(String.valueOf(dataList.get(position).getSaldo_Kembalian()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();}

    public void searchDataList(ArrayList<DataPesanan> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView namaAdmin, namaPelanggan, beratPakaian, biayaTotal, saldoPembelian, saldoPengembalian;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {

        super(itemView);

        recCard = itemView.findViewById(R.id.recCard);
        namaAdmin = itemView.findViewById(R.id.namaAdmin);
        namaPelanggan = itemView.findViewById(R.id.namaPelanggan);
        beratPakaian = itemView.findViewById(R.id.beratPakaian);
        biayaTotal = itemView.findViewById(R.id.hargatotal);
        saldoPembelian = itemView.findViewById(R.id.saloPembelian);
        saldoPengembalian = itemView.findViewById(R.id.saloPengembalian);

    }
}
