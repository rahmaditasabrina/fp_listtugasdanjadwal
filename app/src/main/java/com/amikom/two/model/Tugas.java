package com.amikom.two.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tugas {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "matakuliah")
    public String matakuliah;

    @ColumnInfo(name = "jumlahhadir")
    public String jumlahhadir;


    public Tugas() {

    }

    public Tugas(String matakuliah, String jumlahhadir) {
        this.matakuliah = matakuliah;
        this.jumlahhadir = jumlahhadir;

    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(String matakuliah) {
        this.matakuliah = matakuliah;
    }

    public String getJumlahhadir() {
        return jumlahhadir;
    }

    public void setJumlahhadir(String jumlahhadir) {
        this.jumlahhadir = jumlahhadir;
    }
}
