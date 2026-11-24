package com.example.pharmacycatalog;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MedicineRecyclerAdapter adapter;
    private boolean isGrid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = findViewById(R.id.recyclerView);
        Button btnToggle = findViewById(R.id.btnToggleLayout);

        List<Medicine> data = DataProvider.getMedicines();
        adapter = new MedicineRecyclerAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnToggle.setOnClickListener(v -> {
            isGrid = !isGrid;
            if (isGrid) {
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                adapter.setGridMode(true);
            } else {
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter.setGridMode(false);
            }
            adapter.notifyDataSetChanged();
        });
    }
}