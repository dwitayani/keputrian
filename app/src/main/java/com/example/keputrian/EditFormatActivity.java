package com.example.keputrian;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keputrian.helper.Helper;

import java.util.Calendar;
import java.util.Locale;

public class EditFormatActivity extends AppCompatActivity {

    private EditText etMulaiMens, etSelesaiMens, etTanggalMandi, etJamMandi;
    private ImageButton btnMulaiMens, btnSelesaiMens, btnTanggalMandi, btnJamMandi;
    private Button btnSimpan;

    private Helper db = new Helper(this);
    private String id, tanggalMulai, tanggalSelesai, tanggalMandi, jamMandi;
    private int jam, menit;
    private int tahun, bulan, tanggal;
    private int tahun2, bulan2, tanggal2;
    private int tahun3, bulan3, tanggal3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_format);

        etMulaiMens = findViewById(R.id.etTanggalMulai);
        etSelesaiMens = findViewById(R.id.etTanggalSelesai);
        etTanggalMandi = findViewById(R.id.etTanggalMandi);
        etJamMandi = findViewById(R.id.etJamMandi);

        btnMulaiMens = findViewById(R.id.BtnDatePickerMulaiMens);
        btnSelesaiMens = findViewById(R.id.BtnDatePickerSelesaiMens);
        btnTanggalMandi = findViewById(R.id.BtnDatePickerMandi);
        btnJamMandi = findViewById(R.id.BtnTimePickerJamMandi);
        btnSimpan = findViewById(R.id.btnSimpanMens);

        //Munculin Dialog Date&Time Picker Dari Image Button//
        btnJamMandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                jam = calendar.get(Calendar.HOUR_OF_DAY);
                menit = calendar.get(Calendar.MINUTE);

                TimePickerDialog dialog;
                dialog = new TimePickerDialog(EditFormatActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        jam = hourOfDay;
                        menit = minute;

                        if (jam <= 12) {
                            etJamMandi.setText(String.format(Locale.getDefault(), "%d:%d am", jam, menit));
                        } else {
                            etJamMandi.setText(String.format(Locale.getDefault(), "%d:%d pm", jam, menit));
                        }
                    }
                }, jam, menit, true);
                dialog.show();
            }
        });

        btnMulaiMens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun = calendar.get(Calendar.YEAR);
                bulan = calendar.get(Calendar.MONTH);
                tanggal = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(EditFormatActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun = year;
                        bulan = month;
                        tanggal = dayOfMonth;

                        etMulaiMens.setText(tanggal + " - " + bulan + " - " + tahun);
                    }
                }, tahun, bulan, tanggal);
                dialog.show();
            }
        });
        btnSelesaiMens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun2 = calendar.get(Calendar.YEAR);
                bulan2 = calendar.get(Calendar.MONTH);
                tanggal2 = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(EditFormatActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun2 = year;
                        bulan2 = month;
                        tanggal2 = dayOfMonth;

                        etSelesaiMens.setText(tanggal2 + " - " + bulan2 + " - " + tahun2);
                    }
                }, tahun2, bulan2, tanggal2);
                dialog.show();
            }
        });

        btnTanggalMandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                tahun3 = calendar.get(Calendar.YEAR);
                bulan3 = calendar.get(Calendar.MONTH);
                tanggal3 = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog;
                dialog = new DatePickerDialog(EditFormatActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tahun3 = year;
                        bulan3 = month;
                        tanggal3 = dayOfMonth;

                        etTanggalMandi.setText(tanggal3 + " - " + bulan3 + " - " + tahun3);
                    }
                }, tahun3, bulan3, tanggal3);
                dialog.show();
            }
        });

        //Memproses Data Base//
        id = getIntent().getStringExtra("id");
        tanggalMulai = getIntent().getStringExtra("TanggalMulai");
        tanggalSelesai = getIntent().getStringExtra("tanggalSelesai");
        tanggalMandi = getIntent().getStringExtra("tanggalMandi");
        jamMandi = getIntent().getStringExtra("jamMandi");

        if (id == null || id.equals("")) {
            setTitle("Tambah Mens");
        } else {
            setTitle("Edit Mens");
            etMulaiMens.setText(tanggalMulai);
            etSelesaiMens.setText(tanggalSelesai);
            etTanggalMandi.setText(tanggalMandi);
            etJamMandi.setText(jamMandi);

        }
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (id == null || id.equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save(){
        if (String.valueOf(etMulaiMens.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan Isi Tanggal Haid kamu", Toast.LENGTH_SHORT).show();
        }else {
            db.insert(etMulaiMens.getText().toString(), etSelesaiMens.getText().toString(), etTanggalMandi.getText().toString(), etJamMandi.getText().toString());
            finish();
        }
    }

    private void edit(){
        if (String.valueOf(etMulaiMens.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Silahkan Isi Tanggal Haid kamu", Toast.LENGTH_SHORT).show();
        }else {
            db.update(Integer.parseInt(id), etMulaiMens.getText().toString(), etSelesaiMens.getText().toString(), etTanggalMandi.getText().toString(), etJamMandi.getText().toString());
            finish();
        }
    }


}

