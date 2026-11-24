package com.example.pharmacycatalog;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    public static List<Medicine> getMedicines() {
        List<Medicine> list = new ArrayList<>();
        // Антибіотики (рецептурні)
        list.add(new Medicine(1, "Амоксицилін", "Антибіотики", "500 мг, 20 табл.", 135.50, true, android.R.drawable.ic_menu_gallery));
        list.add(new Medicine(2, "Азитроміцин", "Антибіотики", "500 мг, 3 табл.", 220.00, true, android.R.drawable.ic_menu_gallery));
        list.add(new Medicine(3, "Цефтріаксон", "Антибіотики", "1000 мг, 1 фл.", 185.00, true, android.R.drawable.ic_menu_gallery));

        // Анальгетики (безрецептурні)
        list.add(new Medicine(4, "Парацетамол", "Анальгетики", "500 мг, 10 табл.", 38.50, false, android.R.drawable.ic_menu_gallery));
        list.add(new Medicine(5, "Ібупрофен", "Анальгетики", "400 мг, 20 табл.", 72.00, false, android.R.drawable.ic_menu_gallery));
        list.add(new Medicine(6, "Дексалгін", "Анальгетики", "25 мг, 10 табл.", 160.00, false, android.R.drawable.ic_menu_gallery));

        // Вітаміни (безрецептурні)
        list.add(new Medicine(7, "Вітамін C", "Вітаміни", "500 мг, 20 табл.", 65.00, false, android.R.drawable.ic_menu_gallery));
        list.add(new Medicine(8, "Вітамін D3", "Вітаміни", "2000 МО, 60 капс.", 195.00, false, android.R.drawable.ic_menu_gallery));
        list.add(new Medicine(9, "Магне-B6", "Вітаміни", "100 мг, 50 табл.", 240.00, false, android.R.drawable.ic_menu_gallery));

        // Протизастудні (безрецептурні)
        list.add(new Medicine(10, "ТераФлю", "Протизастудні", "10 саше", 280.00, false, android.R.drawable.ic_menu_gallery));
        list.add(new Medicine(11, "Фармацитрон", "Протизастудні", "10 саше", 265.00, false, android.R.drawable.ic_menu_gallery));
        list.add(new Medicine(12, "Грипго", "Протизастудні", "10 табл.", 95.00, false, android.R.drawable.ic_menu_gallery));

        return list;
    }
}