package com.amikom.two.jadwal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amikom.two.R;
import com.amikom.two.model.Tugas;
import com.amikom.two.room.TugasDatabase;
import com.amikom.two.room.TugasRoom;

@SuppressLint("Registered")
public class TambahTugasActivity extends AppCompatActivity implements View.OnClickListener {
        EditText edtMatakuliah;
        EditText edtJenistugas;
        EditText edtKeterangan;
        EditText edtTanggalPengumpulan;
        EditText edtJamPengumpulan;
        Button btnTambah;
        Button btnHapus;
        TugasRoom tugasRoom;
        int id;
        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tambah_tugas);

            edtMatakuliah = findViewById(R.id.list_matakuliah);
            edtJenistugas = findViewById(R.id.list_jenistugas);
            edtKeterangan = findViewById(R.id.list_keterangan);
            edtTanggalPengumpulan = findViewById(R.id.list_tglkumpul);
            edtJamPengumpulan = findViewById(R.id.list_jamkumpul);

            btnTambah = findViewById(R.id.tugas_tambah);
            btnTambah.setOnClickListener(this);
            btnHapus = findViewById(R.id.tugas_hapus);
            tugasRoom = TugasDatabase.db(this).tugasRoom();

            id = getIntent().getIntExtra("id", 0);
            if (id != 0) {
                Tugas tugas = tugasRoom.select(id);
                edtMatakuliah.setText(tugas.getMatakuliah());
                edtJenistugas.setText(tugas.getJenistugas());
                edtKeterangan.setText(tugas.getKeterangan());
                edtTanggalPengumpulan.setText(tugas.getTanggalpengumpulan());
                edtJamPengumpulan.setText(tugas.getJampengumpulan());
                btnTambah.setText("Update Tugas");
                btnHapus.setVisibility(View.VISIBLE);
                btnHapus.setOnClickListener(v -> {
                    Tugas tugas1 = tugasRoom.select(id);
                    tugasRoom.delete(tugas1);
                    Intent result = new Intent();
                    setResult(Activity.RESULT_OK, result);
                    finish();
                });
            }
        }

        @Override
        public void onClick(View v) {
            Intent result = new Intent();
            Tugas tugas = new Tugas("","","","","");
            if (id != 0) {
                tugas = tugasRoom.select(id);
            }
            tugas.setMatakuliah(edtMatakuliah.getText().toString());
            tugas.setJenistugas(edtJenistugas.getText().toString());
            tugas.setKeterangan(edtKeterangan.getText().toString());
            tugas.setTanggalpengumpulan(edtTanggalPengumpulan.getText().toString());
            tugas.setJampengumpulan(edtJamPengumpulan.getText().toString());
            if (id != 0) {
                tugasRoom.update(tugas);
            } else {
                tugasRoom.insert(tugas);
            }
            setResult(Activity.RESULT_OK, result);
            finish();
        }
    }

