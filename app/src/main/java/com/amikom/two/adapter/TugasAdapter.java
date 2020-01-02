package com.amikom.two.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.amikom.two.R;
import com.amikom.two.model.Tugas;

import java.util.List;

    public class TugasAdapter extends RecyclerView.Adapter<TugasAdapter.ViewHolder> {
        private Context context;
        private List<Tugas> tugas;
        private AdapterView.OnItemClickListener listener;

        public TugasAdapter(Context context, List<Tugas> tugas,
                            AdapterView.OnItemClickListener listener) {
            this.context = context;
            this.tugas = tugas;
            this.listener = listener;
        }

        @NonNull
        @Override
        public TugasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_tugas, viewGroup, false);
            return new TugasAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final TugasAdapter.ViewHolder viewHolder, final int i) {
            final Tugas tugas = this.tugas.get(i);
            viewHolder.tvMatakuliah.setText(tugas.getMatakuliah());
            viewHolder.tvJumlahhadir.setText(tugas.getJumlahhadir());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(null, viewHolder.itemView, i, i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return tugas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tvMatakuliah;
            public TextView tvJumlahhadir;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvMatakuliah = itemView.findViewById(R.id.jadwal_matkul);
                tvJumlahhadir = itemView.findViewById(R.id.jumlah_hadir);
                           }
        }
    }



