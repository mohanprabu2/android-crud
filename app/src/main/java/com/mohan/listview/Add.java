package com.mohan.listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends Activity {
    Button button;
    DatabaseHelper myDb;
    EditText editName,id,age;
    Button btnAddData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_form);

       // button = (Button) findViewById(R.id.save);
//button.setOnClickListener(new View.OnClickListener() {

          //  @Override
        //    public void onClick(View v) {
          //      AlertDialogCreate();

           // }
       // });

        button = (Button) findViewById(R.id.cancel);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(menuIntent);
            }
        });
  //      setContentView(R.layout.add_form);
        myDb = new DatabaseHelper(this);
        editName = (EditText)findViewById(R.id.name);
        //id = (EditText) findViewById(R.id.editText);
        age = (EditText) findViewById(R.id.ageid);
        btnAddData = (Button)findViewById(R.id.save);
        AddData();
    }

    @Override
    public void onBackPressed() {
        Intent menuIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(menuIntent);
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),age.getText().toString());

                        if(isInserted == true) {
                            Toast.makeText(Add.this, "Data Saved", Toast.LENGTH_LONG).show();
                            Intent menuIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(menuIntent);
                        }else {
                            Toast.makeText(Add.this, "Data not Save", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    //public void AlertDialogCreate () {

      //  new AlertDialog.Builder(Add.this)
        //        .setIcon(R.mipmap.ic_launcher)
          //      .setTitle("Data")
            //    .setMessage("Are You sure, do you want to save data?")
                //.setPositiveButton("OK", null)
                //.setNegativeButton("Cancel", null)
          //      .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            //        @Override
              //      public void onClick(DialogInterface dialog, int which) {
                //        EditText name = (EditText) findViewById(R.id.name);
                  //      Toast.makeText(Add.this,  name.getText() + " Data is saved", Toast.LENGTH_SHORT).show();
                   // }
               // })
          //      .setNegativeButton("No", new DialogInterface.OnClickListener() {
          //          @Override
      //              public void onClick(DialogInterface dialog, int which) {
        //                Toast.makeText(Add.this, "You Clicked on Cancel", Toast.LENGTH_SHORT).show();
            //        }
         //       }).show();
        //}

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
