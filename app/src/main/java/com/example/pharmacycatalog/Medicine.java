package com.example.pharmacycatalog;

import java.io.Serializable;

public class Medicine implements Serializable {
    private final int id;
    private final String title;
    private final String manufacturer;
    private final String dosage;
    private final double price;
    private final boolean prescription;
    private final int imageResId;
    private boolean isSelected;

    public Medicine(int id, String title, String manufacturer, String dosage,
                    double price, boolean prescription, int imageResId) {
        this.id = id;
        this.title = title;
        this.manufacturer = manufacturer;
        this.dosage = dosage;
        this.price = price;
        this.prescription = prescription;
        this.imageResId = imageResId;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getManufacturer() { return manufacturer; }
    public String getDosage() { return dosage; }
    public double getPrice() { return price; }
    public boolean isPrescription() { return prescription; }
    public int getImageResId() { return imageResId; }
    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }
}