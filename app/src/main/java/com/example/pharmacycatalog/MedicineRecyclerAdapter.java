package com.example.pharmacycatalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class MedicineRecyclerAdapter extends RecyclerView.Adapter<MedicineRecyclerAdapter.VH> {

    private final List<Medicine> items;
    private boolean gridMode = false;

    public MedicineRecyclerAdapter(List<Medicine> items) {
        this.items = items;
    }

    public void setGridMode(boolean gridMode) {
        this.gridMode = gridMode;
    }

    @Override
    public int getItemViewType(int position) {
        return gridMode ? 1 : 0;
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, manufacturer, price;

        VH(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgMedicine);
            title = itemView.findViewById(R.id.textTitle);
            manufacturer = itemView.findViewById(R.id.textManufacturer);
            price = itemView.findViewById(R.id.textPrice);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = (viewType == 1) ? R.layout.item_medicine_grid : R.layout.item_medicine;
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Medicine m = items.get(position);
        holder.title.setText(m.getTitle());
        String info = m.getManufacturer() + " • " + m.getDosage() + (m.isPrescription() ? " [Рецепт]" : "");
        holder.manufacturer.setText(info);
        holder.price.setText(String.format(Locale.getDefault(), "%.2f грн", m.getPrice()));
        holder.img.setImageResource(m.getImageResId());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}