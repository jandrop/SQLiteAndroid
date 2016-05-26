package es.atrapandocucarachas.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import es.atrapandocucarachas.sqlite.data.DataBaseHelper;
import es.atrapandocucarachas.sqlite.model.Person;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private ArrayAdapter<Person> mAdapter;
    private DataBaseHelper mDbHelper;
    private ArrayList<Person> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    private void setup() {
        mDbHelper = new DataBaseHelper(this);
        ListView mListView = (ListView) findViewById(R.id.list);
        assert mListView != null;
        mListView.setOnItemLongClickListener(this);
        mListView.setOnItemClickListener(this);
        mList = new ArrayList<>(mDbHelper.list());
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Person p = (Person) parent.getItemAtPosition(position);
        mDbHelper.delete(p.getId());
        mAdapter.remove(p);
        mAdapter.notifyDataSetChanged();
        return true;
    }

    public void add(View view) {
        Person p = new Person("John", "Doe", 54, "675666777", "13 Rue del Percebe");
        p.setId(mDbHelper.insert(p));
        mAdapter.add(p);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Person p = (Person) parent.getItemAtPosition(position);
        p.setName("Peter");
        p.setSurname("Parker");
        p.setAge(21);
        p.setPhone("555666777");
        p.setAddress("No idea");
        mDbHelper.update(p.getId(),p);
        mList.set(position,p);
        mAdapter.notifyDataSetChanged();
    }
}
