package com.example.keputrian.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.keputrian.R;
import com.example.keputrian.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists){
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null && inflater != null) {
            view = inflater.inflate(R.layout.activity_list_format, null);
        }
        if (view != null) {
            TextView mulaiMens = view.findViewById(R.id.textMulaiMens);
            TextView selesaiMens = view.findViewById(R.id.textSelesaiMens);
            TextView tanggalMandi = view.findViewById(R.id.textWaktuMandi);
            TextView jamMandi = view.findViewById(R.id.textJamMandi);
            Data data = lists.get(i);
            mulaiMens.setText(data.getTanggal_mulai());
            selesaiMens.setText(data.getTanggal_mulai());
            tanggalMandi.setText(data.getTanggal_mandi());
            jamMandi.setText(data.getJam_mandi());
        }
        return view;
    }
}
