package com.example.drugstore;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowUpdate extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dataHelper;
    ImageView ton1, ton2;
    EditText text1, text2, text3, text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_update);

        dataHelper = new DatabaseHelper(this);
        text1 = (EditText) findViewById(R.id.editId);
        text2 = (EditText) findViewById(R.id.editName);
        text3 = (EditText) findViewById(R.id.editPack);
        text4 = (EditText) findViewById(R.id.editPrice);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM drug WHERE name = '" +
                getIntent().getStringExtra("name") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
        }
        ton1 = (ImageView) findViewById(R.id.button1);
        ton2 = (ImageView) findViewById(R.id.button2);
        // daftarkan even onClick pada btnSimpan

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("UPDATE drug SET name='"+
                        text2.getText().toString() +"', price='" +
                        text3.getText().toString()+"', pack='"+
                        text4.getText().toString() +"'WHERE id='" +
                        text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.main_Page.RefreshList();
                finish();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}