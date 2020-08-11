package com.example.drugstore;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowAdd extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dataHelper;
    ImageView add_Button, back_Phase;
    EditText text1, text2, text3, text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_add);

        dataHelper = new DatabaseHelper(this);
        text1 = (EditText) findViewById(R.id.editId);
        text2 = (EditText) findViewById(R.id.editName);
        text3 = (EditText) findViewById(R.id.editPrice);
        text4 = (EditText) findViewById(R.id.editPack);
        back_Phase = (ImageView) findViewById(R.id.backPhase);
        add_Button = (ImageView) findViewById(R.id.addButton);


        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dataHelper.getWritableDatabase();
                db.execSQL("INSERT INTO drug(id, name, price, pack) VALUES('" +
                        text1.getText().toString() + "','" + text2.getText().toString() + "','" +
                        text3.getText().toString() + "','" + text4.getText().toString() + "')" );
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                MainActivity.main_Page.RefreshList();
                finish();
            }
        });
        back_Phase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //TODO Auto-generated method stub
                finish();
            }
        });
    }
}