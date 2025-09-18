package com.poutividad.act5;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategory;
    private TextView tvDate, tvTime;
    private EditText etDescription;
    private ListView lvItems;

    private final List<String> items = new ArrayList<>();
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        etDescription = findViewById(R.id.etDescription);
        ImageButton ibPickDate = findViewById(R.id.ibPickDate);
        ImageButton ibPickTime = findViewById(R.id.ibPickTime);
        ImageButton ibAdd = findViewById(R.id.ibAdd);
        ImageButton ibClear = findViewById(R.id.ibClear);
        lvItems = findViewById(R.id.lvItems);

        ArrayAdapter<CharSequence> catAdapter = ArrayAdapter.createFromResource(
                this, R.array.categorias, android.R.layout.simple_spinner_item
        );
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(catAdapter);

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(listAdapter);

        ibPickDate.setOnClickListener(v -> showDatePicker());
        ibPickTime.setOnClickListener(v -> showTimePicker());
        ibAdd.setOnClickListener(v -> addItem());
        ibClear.setOnClickListener(v -> confirmClear());

        lvItems.setOnItemLongClickListener((parent, view, position, id) -> {
            confirmDelete(position);
            return true;
        });
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(this, (view, yy, mm, dd) -> {
            String sY = String.valueOf(yy);
            String sM = String.format("%02d", mm + 1);
            String sD = String.format("%02d", dd);
            tvDate.setText(sY + "-" + sM + "-" + sD);
        }, y, m, d).show();
    }

    private void showTimePicker() {
        Calendar c = Calendar.getInstance();
        int h = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        new TimePickerDialog(this, (view, hh, mm) -> {
            String sH = String.format("%02d", hh);
            String sM = String.format("%02d", mm);
            tvTime.setText(sH + ":" + sM);
        }, h, min, true).show();
    }

    private void addItem() {
        String categoria = (spinnerCategory.getSelectedItem() != null)
                ? spinnerCategory.getSelectedItem().toString()
                : "";
        String fecha = tvDate.getText().toString().trim();
        String hora = tvTime.getText().toString().trim();
        String desc = etDescription.getText().toString().trim();

        if (desc.isEmpty()) {
            etDescription.setError(getString(R.string.cd_agregar));
            return;
        }
        if (fecha.isEmpty() || fecha.equalsIgnoreCase("YYYY-MM-DD")) {
            tvDate.setError(getString(R.string.cd_pick_fecha));
            return;
        }
        if (hora.isEmpty() || hora.equalsIgnoreCase("HH:mm")) {
            tvTime.setError(getString(R.string.cd_pick_hora));
            return;
        }

        String item = categoria + " — " + desc + " (" + fecha + " " + hora + ")";
        items.add(item);
        listAdapter.notifyDataSetChanged();
        etDescription.setText("");
        tvDate.setError(null);
        tvTime.setError(null);
    }

    private void confirmClear() {
        if (items.isEmpty()) return;

        new AlertDialog.Builder(this)
                .setTitle(R.string.cd_limpiar)
                .setMessage("¿Borrar toda la lista?")
                .setPositiveButton("Sí", (d, w) -> {
                    items.clear();
                    listAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void confirmDelete(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar elemento")
                .setMessage("¿Eliminar este elemento?")
                .setPositiveButton("Eliminar", (d, w) -> {
                    items.remove(position);
                    listAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}

