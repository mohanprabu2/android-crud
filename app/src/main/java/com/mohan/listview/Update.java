package com.mohan.listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.mohan.listview.R.id.delete;
import static com.mohan.listview.R.id.editid;

public class Update extends Activity {
    Button button;
    DatabaseHelper myDb;
    EditText editName, editId, age;
    Button btnDelete;
    Button btnviewUpdate;
    Button  btnviewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_form);
        myDb = new DatabaseHelper(this);
        //button = (Button) findViewById(delete);

       // button.setOnClickListener(new View.OnClickListener() {

         //   @Override
           // public void onClick(View v) {
            //    AlertDialogCreate();

           // }
        //});
        //setContentView(R.layout.update_form);

        button =(Button)findViewById(R.id.cancel);
        button.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick (View v){
                Intent menuIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(menuIntent);
            }
        });
        editName = (EditText)findViewById(R.id.name);
        editId = (EditText)findViewById(R.id.editid);
        age= (EditText)findViewById(R.id.ageid);
        btnDelete= (Button)findViewById(delete);
        btnviewUpdate= (Button)findViewById(R.id.update);
       // btnviewAll = (Button)findViewById(R.id.button_viewAll);

        populateRecord(getIntent().getStringExtra("ID_Value"));

        editId.setInputType(InputType.TYPE_NULL);

        UpdateData();
       // viewAll();
        DeleteData();
    }

    private void populateRecord(String id) {
        Cursor cur = myDb.getData(id);
        cur.moveToFirst();
        editId.setText(cur.getString(cur.getColumnIndex("ID")));
        editName.setText(cur.getString(cur.getColumnIndex("NAME")));
        age.setText(cur.getString(cur.getColumnIndex("AGE")));
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editId.getText().toString());
                        if (deletedRows > 0){
                            Toast.makeText(Update.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        Intent menuIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(menuIntent);}
                        else{
                            Toast.makeText(Update.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        Intent menuIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(menuIntent);
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editId.getText().toString(),editName.getText().toString()
                        ,age.getText().toString());
                        if (isUpdate == true){
                            Toast.makeText(Update.this, "Data Update", Toast.LENGTH_LONG).show();
                          Intent menuIntent = new Intent(getApplicationContext(), MainActivity.class);
                          startActivity(menuIntent);
                    }
                        else

                    {
                        Toast.makeText(Update.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
                }
        );
    }
 //   public void AlertDialogCreate() {

   //     new AlertDialog.Builder(Update.this)
    //            .setIcon(R.mipmap.ic_launcher)
      //          .setTitle("Name")
        //        .setMessage("Do you want to Delete?")
          //      .setPositiveButton("Yes", null)
            //    .setNegativeButton("NO", null)
              //  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
   //                 @Override
     //               public void onClick(DialogInterface dialog, int which) {
       //                 Toast.makeText(Update.this, "You Deleted Name", Toast.LENGTH_SHORT).show();
         //           }
           //     })
        //        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
          //          @Override
            //        public void onClick(DialogInterface dialog, int which) {
              //          Toast.makeText(Update.this, "You Clicked on Cancel", Toast.LENGTH_SHORT).show();
               //     }
               // }).show();
   // }
  //  public void viewAll() {
    //    btnviewAll.setOnClickListener(
      //          new View.OnClickListener() {
         //           @Override
        //            public void onClick(View v) {
          //              Cursor res = myDb.getAllData();
            //            if (res.getCount() == 0) {
                                              // show message
               //             showMessage("Error", "Nothing found");
              //              return;
                 //       }

                   //     StringBuffer buffer = new StringBuffer();
               //         while (res.moveToNext()) {
                 //           buffer.append("Id :"+ res.getString(0)+"\n");
                   //         buffer.append("Name :" + res.getString(1) + "\n");
                     //        buffer.append("Age :"+ res.getString(2)+"\n\n");
                            // buffer.append("Marks :"+ res.getString(3)+"\n\n");
             //                }

                            // Show all data
                       //     showMessage("Data", buffer.toString());
           //             }
                //          }
                         // );
         //                }

                        public void showMessage (String title, String Message){
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setCancelable(true);
                            builder.setTitle(title);
                            builder.setMessage(Message);
                            builder.show();
                        }

}
