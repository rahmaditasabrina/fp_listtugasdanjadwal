package com.amikom.two.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amikom.two.model.Tugas;
import com.amikom.two.room.TugasDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amikom.two.R;
import com.amikom.two.adapter.TugasAdapter;
import com.amikom.two.jadwal.TambahJadwalActivity;
import com.amikom.two.jadwal.TambahTugasActivity;

import java.util.ArrayList;
import java.util.List;

    public class TugasUI extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
        private RecyclerView recyclerView;
        private TugasAdapter TugasAdapter;
        private List<Tugas> list = new ArrayList<>();
        // private JadwalDAO jadwalDAO;
//        private TugasRoom presensiRoom;
        TugasDatabase tugasDatabase;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // jadwalDAO = new JadwalDAO();
            tugasDatabase = TugasDatabase.db(getContext());
            // list = jadwalDAO.selectAll();
            list = tugasDatabase.presensiRoom().selectAll();
            TugasAdapter = new TugasAdapter(getContext(), list, this);
        }


        @SuppressLint("WrongConstant")
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.ui_tugas, container, false);
            recyclerView = view.findViewById(R.id.recycler_jadwal);
            recyclerView.setAdapter(TugasAdapter);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayout.VERTICAL);
            recyclerView.setLayoutManager(llm);
            FloatingActionButton fab = view.findViewById(R.id.fab_jadwal);
            fab.setOnClickListener(this);
            return view;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), TambahTugasActivity.class);
            startActivityForResult(intent, 30);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK) {
                list.clear();
                list.addAll(tugasDatabase.presensiRoom().selectAll());
                TugasAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Tugas tugas = list.get(position);
            Toast.makeText(getContext(), tugas.getMatakuliah(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), TambahJadwalActivity.class);
            intent.putExtra("id", tugas.getId());
            startActivityForResult(intent, 50);
        }
    }

