package com.example.sredmond.todolist;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.TreeMap;

import stanford.androidlib.SimpleActivity;

public class MainActivity extends SimpleActivity {

//    public enum ItemPriority {
//        LOW, MEDIUM, HIGH
//    }
//
//    private static TreeMap<String, ItemPriority> priorities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findListView(R.id.todos);
        list.setOnItemClickListener(this);
        list.setOnItemLongClickListener(this);
    }

    public void addItem(View view) {
        EditText addItemText = findEditText(R.id.addItemText);
        String newItem = addItemText.getText().toString();
        ListView todolist = findListView(R.id.todos);
        ArrayList<String> items = listGetItems(todolist);
        if (!items.contains(newItem)) {
            items.add(newItem);
        } else {
            toast("To Do Item " + newItem + " already present");
        }
        listSetItems(todolist, items);
    }

    @Override
    public void onItemClick(ListView list, int index) {

    }

    @Override
    public boolean onItemLongClick(ListView list, int index) {
        ListView todolist = findListView(R.id.todos);
        ArrayList<String> items = listGetItems(todolist);
        items.remove(index);
        listSetItems(todolist, items);
        return true; // True => we have consumed the event, shouldn't go to onItemClick
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<String> todos = listGetItems(findListView(R.id.todos));
        outState.putStringArrayList("todos", todos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        ArrayList<String> todos = inState.getStringArrayList("todos");
        listSetItems(findListView(R.id.todos), todos);
    }
}
