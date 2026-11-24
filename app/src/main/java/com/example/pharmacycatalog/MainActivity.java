package com.example.pharmacycatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MedicineBaseAdapter.OnMedicineActionListener {
    private ListView listView;
    private Spinner spinnerCategory;
    private AutoCompleteTextView autoSearch;
    private MedicineBaseAdapter adapter;
    private final List<Medicine> medicines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicines.addAll(DataProvider.getMedicines());
        listView = findViewById(R.id.listView);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        autoSearch = findViewById(R.id.autoSearch);

        adapter = new MedicineBaseAdapter(this, medicines, this);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        listView.setMultiChoiceModeListener(new android.widget.AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Medicine m = adapter.getItem(position);
                if (m != null) m.setSelected(checked);
                adapter.notifyDataSetChanged();
                int count = 0;
                for (Medicine item : adapter.getDisplayList()) if (item.isSelected()) count++;
                mode.setTitle("Обрано: " + count);
            }
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.context_menu, menu);
                return true;
            }
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) { return false; }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    List<Medicine> toRemove = new ArrayList<>();
                    for (Medicine m : adapter.getDisplayList()) if (m.isSelected()) toRemove.add(m);
                    for (Medicine m : toRemove) {
                        medicines.remove(m);
                        adapter.getDisplayList().remove(m);
                    }
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Видалено елементів: " + toRemove.size(), Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                }
                return false;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                for (Medicine item : adapter.getDisplayList()) item.setSelected(false);
                adapter.notifyDataSetChanged();
            }
        });

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);
        spinnerCategory.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) { applyFilter(); }
            @Override public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        String[] titles = medicines.stream().map(Medicine::getTitle).toArray(String[]::new);
        autoSearch.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, titles));
        autoSearch.setOnItemClickListener((parent, view, position, id) -> applyFilter());
        autoSearch.addTextChangedListener(new android.text.TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { applyFilter(); }
            @Override public void afterTextChanged(android.text.Editable s) {}
        });

        findViewById(R.id.btnToRecycler).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RecyclerActivity.class)));
    }

    private void applyFilter() {
        String category = spinnerCategory.getSelectedItem().toString();
        String query = autoSearch.getText().toString();
        adapter.filter(category, query);
    }

    @Override
    public void onBuyClick(Medicine medicine, int position) {
        Toast.makeText(this, "🛒 Додано в кошик: " + medicine.getTitle(), Toast.LENGTH_SHORT).show();
        Snackbar.make(findViewById(android.R.id.content), "🛒 Додано в кошик: " + medicine.getTitle(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckChanged(Medicine medicine, boolean isChecked, int position) {
        Toast.makeText(this, medicine.getTitle() + (isChecked ? " обрано" : " знято"), Toast.LENGTH_SHORT).show();
    }
}