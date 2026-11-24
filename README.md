# 💊 Лабораторна робота №6: Адаптери та списки в Android

**Назва проєкту:** PharmacyCatalog (Каталог Інтернет-Аптеки)  
**Автор:** [Твоє ПІБ]  
**Група:** [Твоя Група]  

---

## 📌 Опис проєкту
**PharmacyCatalog** — це мобільний додаток для Android, розроблений мовою **Java** з використанням класичних **Views & XML** та принципами **Material Design 3**. Додаток реалізує каталог ліків з можливістю фільтрації за категоріями, автодоповненням пошуку, вибору елементів у кошик, контекстним меню вилучення та динамічною сіткою (RecyclerView Grid / List).

---

## 🚀 Реалізований функціонал

1. **Модель даних та DataProvider (`Medicine`, `DataProvider`)**:
   - Розроблено клас `Medicine` (ідентифікатор, назва, категорія/виробник, дозування, ціна, прапорець рецептурності, іконка).
   - Клас `DataProvider` містить набір із 12 реалістичних медикаментів, розподілених за 4 категоріями (*Антибіотики*, *Анальгетики*, *Вітаміни*, *Протизастудні*).

2. **ListView та Кастомний BaseAdapter (`MedicineBaseAdapter`)**:
   - Реалізовано паттерн **ViewHolder** для оптимізації рендерингу списку.
   - Використано `android:descendantFocusability="blocksDescendants"` та відключено фокусування дочірніх кнопок, щоб рядок списку залишався клікабельним.
   - Інтегровано обробку натискань на кнопку *"В кошик"* із відображенням `Toast` та `Snackbar`.
   - Фільтрація списку в реальному часі за категоріями (`Spinner`) та текстовим запитом (`AutoCompleteTextView`).

3. **Множинний вибір та Contextual Action Bar (CAB)**:
   - Підключено `ListView.CHOICE_MODE_MULTIPLE_MODAL` із слухачем `MultiChoiceModeListener`.
   - Довге натискання відкриває верхню контекстну панель із лічильником обраних елементів та функцією видалення.

4. **RecyclerView з динамічною сіткою (`RecyclerActivity`, `MedicineRecyclerAdapter`)**:
   - Підтримка двох режимів відображення: **Список (LinearLayoutManager)** та **Сітка 2 колонки (GridLayoutManager)**.
   - Динамічне переключення макетів (`item_medicine.xml` та `item_medicine_grid.xml`) через перевизначення методу `getItemViewType()`.

5. **Сучасний дизайн у стилі Material Design 3**:
   - Використано `MaterialCardView` із заокругленими кутами (`12dp`/`14dp`), теньми та витонченими рамками.
   - Контрастна кольорова гама: темний текст (`#0F172A`), акцентна зелена ціна (`#059669`) та адаптивні кнопки.

---

## 🛠 Технологічний стек

- **Мова програмування:** Java 11
- **Платформа:** Android SDK (Min SDK: 24, Target SDK: 37)
- **UI Toolkit:** Android Views, XML Layouts, Material Design Components (`com.google.android.material`)
- **Списки:** `ListView`, `BaseAdapter`, `RecyclerView`, `LinearLayoutManager`, `GridLayoutManager`
- **Компоненти UI:** `Spinner`, `AutoCompleteTextView`, `MaterialCardView`, `MaterialButton`, `Snackbar`, `ActionMode`

---

## 🛠 Як запустити проєкт

1. **Клонувати репозиторій:**
   ```bash
   git clone https://github.com/your-username/PharmacyCatalog.git
   ```
2. **Відкрити в Android Studio:**
   - Оберіть **File -> Open...** та виберіть папку проєкту `PharmacyCatalog`.
   - Зачекайте завершення Gradle Sync.

3. **Запустити додаток:**
   - Запустіть проєкт на Android-емуляторі або підключеному фізичному пристрої (Shift + F10 або кнопка **Run 'app'**).

---
