package com.example.admin_laundry;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class Layanan extends Fragment {
    TextView logout;


    private SharedPreferences sharedPreferences;

    public Layanan(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layanan, container, false);

        CardView cardViewbasah = view.findViewById(R.id.cardbasah);
        CardView cardViewkering = view.findViewById(R.id.cardkering);

        logout = view.findViewById(R.id.tvlogout);

        sharedPreferences = requireContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);



        TextView informasiKering = view.findViewById(R.id.informasiKering);
        TextView informasiBasah = view.findViewById(R.id.informasiBasah);

        ImageView basah = view.findViewById(R.id.addBasah);
        ImageView kering = view.findViewById(R.id.addKering);


        informasiKering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), informasiKering.class);
                startActivity(intent);
            }
        });
        informasiBasah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), infromasiBasah.class);
                startActivity(intent);
            }
        });
        basah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Pembayaran1.class);
                startActivity(intent);
            }
        });
        kering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Pembayaran.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("isLoggedIn");
                editor.apply();

                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}