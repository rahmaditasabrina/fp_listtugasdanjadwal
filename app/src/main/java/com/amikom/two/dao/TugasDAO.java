package com.amikom.two.dao;


import com.amikom.two.model.Tugas;

import java.util.ArrayList;
import java.util.List;

public class TugasDAO {
    private List<Tugas> list = new ArrayList<>();

    public TugasDAO() {
        list.add(new Tugas("", ""));
        list.add(new Tugas("", ""));


    }

    public void insert(Tugas tugas) {
        list.add(tugas);
    }

    public void update(int id, Tugas tugas) {
        Tugas old = list.get(id);
        ((Tugas) old).setMatakuliah(tugas.getMatakuliah());
        ((Tugas) old).setJumlahhadir(tugas.getJumlahhadir());
       }

    public void delete(int id) {
        list.remove(id);
    }

    public Tugas select(int id) {
        return list.get(id);
    }

    public List<Tugas> selectAll() {
        return list;
    }
}

