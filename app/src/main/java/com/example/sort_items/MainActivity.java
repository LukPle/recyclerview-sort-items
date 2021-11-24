package com.example.sort_items;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The MainActivity creates a Shopping List using a ArrayList with a RecyclerView.
 * The RecyclerView in combination with an Adapter is the recommended way for creating lists in Android.
 * It is possible to sort the shopping list in ascending alphabetical order or by the quantity.
 *
 * The documentation focuses on the sorting functionality.
 * For Javadoc information about the RecyclerView and Adapter check out the specific project.
 *
 * Layout File for the activity: activity_main.xml
 * Layout File for the menu: menu_sort.xml
 *
 * @author Lukas Plenk
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Item> itemList;

    // The order of items get rearranged by the sorting and this ArrayList contains the original order
    private ArrayList<Item> originalItemList;

    private Adapter adapter;

    // These are the three menu options for sorting the list
    private MenuItem unsorted;
    private MenuItem sorted_alphabetical;
    private MenuItem sorted_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        fillItemList();

        originalItemList = new ArrayList<>(itemList);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new Adapter(itemList);
        recyclerView.setAdapter(adapter);
    }

    private void fillItemList() {

        itemList.add(new Item("Noodles", "1"));
        itemList.add(new Item("Cheese", "1"));
        itemList.add(new Item("Pepper", "2"));
        itemList.add(new Item("Onions", "4"));
        itemList.add(new Item("Carrots", "2"));
        itemList.add(new Item("Tomato", "1"));
        itemList.add(new Item("Fish", "4"));
        itemList.add(new Item("Grill Sauce", "1"));
        itemList.add(new Item("Baguette", "1"));
        itemList.add(new Item("Mushrooms", "6"));
        itemList.add(new Item("Cooking Oil", "1"));
    }

    /**
     * This method has the sorting algorithm in it.
     * The Collections class provides a compare method that can easily compare two items.
     * @param criteria decides if the sorting is done alphabetically by item name or by the quantity of the item.
     */
    private void sortItemList(boolean criteria) {

        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item item, Item t1) {

                // Ascending alphabetical sorting by the name of an item
                if(!criteria) {

                    return item.getItem().toLowerCase().compareTo(t1.getItem().toLowerCase());
                }
                // Ascending sorting by the quantity of an item
                else {

                    int q1 = Integer.parseInt(item.getQuantity());
                    int q2 = Integer.parseInt(t1.getQuantity());

                    return Integer.compare(q1, q2);
                }
            }
        });

        adapter.notifyDataSetChanged();
    }

    /**
     * The onCreateOptionsMenu method handles every step for the creation of the menu.
     * The Layout for the menu gets assigned by a MenuInflater.
     * The unsorted options gets selected by the time of the creation.
     * @param menu is an predefined interface for the menu.
     * @return true for finishing the setup process of the menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sort, menu);

        unsorted =  menu.findItem(R.id.unsorted);
        sorted_alphabetical =  menu.findItem(R.id.sort_alphabetical);
        sorted_quantity =  menu.findItem(R.id.sort_quantity);

        unsorted.setChecked(true);

        return true;
    }

    /**
     * This is a listener that handles click events on the menu options.
     * A switch case decides which action should be executed.
     * When the unsorted options gets checked, the itemList gets all original items again.
     * When the alphabetical option gets checked, the criteria for sorting is false and the list gets sorted.
     * When the quantity option gets checked, the criteria for sorting is true and the list gets sorted.
     * @param item is the clicked MenuItem.
     * @return true for ending the action.
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.unsorted:
                unsorted.setChecked(true);
                itemList.clear();
                itemList.addAll(originalItemList);
                adapter.notifyDataSetChanged();
                return true;

            case R.id.sort_alphabetical:
                sorted_alphabetical.setChecked(true);
                sortItemList(false);
                return true;

            case R.id.sort_quantity:
                sorted_quantity.setChecked(true);
                sortItemList(true);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}