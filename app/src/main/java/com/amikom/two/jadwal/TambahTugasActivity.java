package com.amikom.two.jadwal;

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

public class TambahTugasActivity extends AppCompatActivity implements View.OnClickListener {
        EditText edtMatakuliah;
        EditText edtJumlahhadir;
        Button btnTambah;
        Button btnHapus;
        TugasRoom tugasRoom;
        int id;
        TugasDatabase tugasDatabase;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tambah_tugas);
            tugasDatabase = TugasDatabase.db(this);
            edtMatakuliah = findViewById(R.id.list_matakuliah);
            edtJumlahhadir = findViewById(R.id.list_jumlahhadir);
            btnTambah = findViewById(R.id.presensi_tambah);
            btnTambah.setOnClickListener(this);
            btnHapus = findViewById(R.id.presensi_hapus);

            tugasRoom = TugasDatabase.db(this).presensiRoom();
            id = getIntent().getIntExtra("id", 0);
            if (id != 0) {
                final Tugas tugas = tugasDatabase.presensiRoom().select(id);
                edtMatakuliah.setText(tugas.getMatakuliah());
                edtJumlahhadir.setText(tugas.getJumlahhadir());
                btnTambah.setText("Update Jadwal");
                btnHapus.setVisibility(View.VISIBLE);
                btnHapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tugas tugas = tugasDatabase.presensiRoom().select(id);
                        tugasRoom.delete(tugas);
                        Intent result = new Intent();
                        setResult(Activity.RESULT_OK, result);
                        finish();
                    }
                });
            }
        }

        @Override
        public void onClick(View v) {
            Intent result = new Intent();
            Tugas tugas = new Tugas();
            if (id != 0) {
                tugas = tugasDatabase.presensiRoom().select(id);
            }
            tugas.setMatakuliah(edtMatakuliah.getText().toString());
            tugas.setJumlahhadir(edtJumlahhadir.getText().toString());
            if (id != 0) {
                tugasRoom.update(tugas);
            } else {
                tugasRoom.insert(tugas);
            }
            setResult(Activity.RESULT_OK, result);
            finish();
        }
    }

