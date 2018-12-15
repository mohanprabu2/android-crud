package com.mohan.listview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button button;
    List<String> ids;
    DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);

        myDb = new DatabaseHelper(this);

        Cursor cur = myDb.getAllData();
        System.out.println(cur.getCount());

        if(cur.getCount() == 0) {
            // show message
            Toast.makeText(this,"Nothing found",Toast.LENGTH_LONG).show();
            return;
        }

        List<String> names = new ArrayList<String>();
        ids = new ArrayList<String>();

        StringBuffer buffer = new StringBuffer();
        while (cur.moveToNext()) {
            ids.add(cur.getString(0));
            names.add(cur.getString(1) + " - " + cur.getString(2));
            }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, names);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent myIntent = new Intent(view.getContext(), Update.class);
                myIntent.putExtra("ID_Value", ids.get(position));
                startActivityForResult(myIntent, 0);
            }
        });

    }

    @Override
    public void onBackPressed() {
        //this.getActivity().finish();
        finishAffinity();
        System.exit(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClickAdd(View view) {
        //switch (view.getId()) {
        //  case R.id.button3:
        Intent intent = new Intent(MainActivity.this, Add.class);
        startActivity(intent);
        //    break;
    }



    public void onClickTruncate(View view) {
        myDb.deleteAll();
        finish();
        startActivity(getIntent());
    }

    public void onClickBulkInsert(View view) {

        for(int i = 1; i <= 1000; i ++) {

            myDb.insertData("Mohan" + Integer.toString(i),  Integer.toString(i));

        }

        finish();
        startActivity(getIntent());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
