//package com.example.keputrian;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Adapter;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//
//import com.example.keputrian.helper.Helper;
//import com.example.keputrian.model.Data;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class FormatMensActivity extends AppCompatActivity {
//
//    ListView listView;
//    AlertDialog.Builder dialog;
//    List<Data> lists = new ArrayList<>();
//    Adapter adapter;
//
//    Helper db = new Helper(this);
//    Button btnAdd;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_format_mens);
//
//
//        btnAdd = findViewById(R.id.buttonTambah);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FormatMensActivity.this, EditFormatActivity.class);
//                startActivity(intent);
//            }
//        });
//        listView = findViewById(R.id.list_item);
//        adapter = new Adapter(FormatMensActivity.this, lists);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
//                final String id = lists.get(i).getId();
//                final String mulaiMens = lists.get(i).getTanggal_mulai();
//                final String selesaiMens = lists.get(i).getTanggal_selesai();
//                final String tanggalMandi = lists.get(i).getTanggal_mandi();
//                final String jamMandi = lists.get(i).getJam_mandi();
//                final CharSequence[] dialogItem = {"Edit", "Hapus"};
//                dialog = new AlertDialog.Builder(FormatMensActivity.this);
//                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        switch (i) {
//                            case 0:
//                                // Use getIntent() to get the intent
//                                Intent intent = new Intent(FormatMensActivity.this, EditFormatActivity.class);
//                                intent.putExtra("id", id);
//                                intent.putExtra("mulaiMens", mulaiMens);
//                                intent.putExtra("selesaiMens", selesaiMens);
//                                intent.putExtra("tanggalMandi", tanggalMandi);
//                                intent.putExtra("jamMandi", jamMandi);
//                                startActivity(intent);
//                                break;
//                            case 1:
//                                db.delete(Integer.parseInt(id));
//                                lists.clear();
//                                getData();
//                                break;
//                        }
//                    }
//                }).show();
//
//                return false;
//            }
//        });
//        getData();
//    }
//
//    private void getData(){
//        ArrayList<HashMap<String, String>> rows = db.getAll();
//        for (int i = 0; i<rows.size(); i++){
//            String id = rows.get(i).get("id");
//            String mulaiMens = rows.get(i).get("mulaiMens");
//            String selesaiMens = rows.get(i).get("selesaiMens");
//            String tanggalMandi = rows.get(i).get("tanggalMandi");
//            String jamMandi = rows.get(i).get("jamMandi");
//
//            Data data = new Data();
//            data.setId(id);
//            data.setTanggal_mulai(mulaiMens);
//            data.setTanggal_selesai(selesaiMens);
//            data.setTanggal_mandi(tanggalMandi);
//            data.setJam_mandi(jamMandi);
//            lists.add(data);
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        lists.clear();
//        getData();
//    }
//}

package com.example.keputrian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.keputrian.helper.Helper;
import com.example.keputrian.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormatMensActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    ArrayAdapter<Data> adapter; // Menggunakan ArrayAdapter dengan tipe Data

    Helper db;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_format_mens);

        db = new Helper(this);

        btnAdd = findViewById(R.id.buttonTambah);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormatMensActivity.this, EditFormatActivity.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.list_item);
        adapter = new ArrayAdapter<>(FormatMensActivity.this, android.R.layout.simple_list_item_1, lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                final String id = lists.get(i).getId();
                final String mulaiMens = lists.get(i).getTanggal_mulai();
                final String selesaiMens = lists.get(i).getTanggal_selesai();
                final String tanggalMandi = lists.get(i).getTanggal_mandi();
                final String jamMandi = lists.get(i).getJam_mandi();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(FormatMensActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(FormatMensActivity.this, EditFormatActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("mulaiMens", mulaiMens);
                                intent.putExtra("selesaiMens", selesaiMens);
                                intent.putExtra("tanggalMandi", tanggalMandi);
                                intent.putExtra("jamMandi", jamMandi);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(id));
                                lists.clear();
                                getData();
                                break;
                        }
                    }
                }).show();

                return false;
            }
        });
        getData();
    }

    private void getData(){
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i<rows.size(); i++){
            String id = rows.get(i).get("id");
            String mulaiMens = rows.get(i).get("mulaiMens");
            String selesaiMens = rows.get(i).get("selesaiMens");
            String tanggalMandi = rows.get(i).get("tanggalMandi");
            String jamMandi = rows.get(i).get("jamMandi");

            Data data = new Data();
            data.setId(id);
            data.setTanggal_mulai(mulaiMens);
            data.setTanggal_selesai(selesaiMens);
            data.setTanggal_mandi(tanggalMandi);
            data.setJam_mandi(jamMandi);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}

