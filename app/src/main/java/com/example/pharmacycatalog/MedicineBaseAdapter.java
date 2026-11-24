package com.example.pharmacycatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MedicineBaseAdapter extends BaseAdapter {

    public interface OnMedicineActionListener {
        void onBuyClick(Medicine medicine, int position);
        void onCheckChanged(Medicine medicine, boolean isChecked, int position);
    }

    private final List<Medicine> originalList;
    private final List<Medicine> displayList;
    private final LayoutInflater inflater;
    private final OnMedicineActionListener listener;

    public MedicineBaseAdapter(Context context, List<Medicine> list, OnMedicineActionListener listener) {
        this.originalList = list;
        this.displayList = new ArrayList<>(list);
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    public void filter(String category, String query) {
        displayList.clear();
        String q = query == null ? "" : query.toLowerCase(Locale.ROOT).trim();
        for (Medicine m : originalList) {
            boolean catOk = category == null || category.equals("Всі") || m.getManufacturer().equals(category);
            boolean queryOk = q.isEmpty() || m.getTitle().toLowerCase(Locale.ROOT).contains(q) || m.getManufacturer().toLowerCase(Locale.ROOT).contains(q);
            if (catOk && queryOk) displayList.add(m);
        }
        notifyDataSetChanged();
    }

    public List<Medicine> getOriginalList() { return originalList; }
    public List<Medicine> getDisplayList() { return displayList; }

    @Override public int getCount() { return displayList.size(); }
    @Override public Medicine getItem(int position) { return displayList.get(position); }
    @Override public long getItemId(int position) { return displayList.get(position).getId(); }

    static class ViewHolder {
        ImageView imgMedicine;
        TextView textTitle, textManufacturer, textPrice;
        Button btnAction;
        CheckBox checkSelect;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_medicine, parent, false);
            holder = new ViewHolder();
            holder.imgMedicine = convertView.findViewById(R.id.imgMedicine);
            holder.textTitle = convertView.findViewById(R.id.textTitle);
            holder.textManufacturer = convertView.findViewById(R.id.textManufacturer);
            holder.textPrice = convertView.findViewById(R.id.textPrice);
            holder.btnAction = convertView.findViewById(R.id.btnAction);
            holder.checkSelect = convertView.findViewById(R.id.checkSelect);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Medicine medicine = getItem(position);
        holder.textTitle.setText(medicine.getTitle());
        String info = medicine.getManufacturer() + " • " + medicine.getDosage() + (medicine.isPrescription() ? " [Рецепт]" : "");
        holder.textManufacturer.setText(info);
        holder.textPrice.setText(String.format(Locale.getDefault(), "%.2f грн", medicine.getPrice()));
        holder.imgMedicine.setImageResource(medicine.getImageResId());

        holder.checkSelect.setOnCheckedChangeListener(null);
        holder.checkSelect.setChecked(medicine.isSelected());
        holder.checkSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            medicine.setSelected(isChecked);
            if (listener != null) listener.onCheckChanged(medicine, isChecked, position);
        });

        holder.btnAction.setOnClickListener(v -> {
            if (listener != null) listener.onBuyClick(medicine, position);
        });

        return convertView;
    }
}