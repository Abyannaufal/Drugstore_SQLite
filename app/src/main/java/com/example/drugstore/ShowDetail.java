package com.example.drugstore;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowDetail extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    ImageView back_Phase;
    TextView text1, text2, text3, text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        dbHelper = new DatabaseHelper(this);
        text1 = (TextView) findViewById(R.id.showId);
        text2 = (TextView) findViewById(R.id.showName);
        text3 = (TextView) findViewById(R.id.showPrice);
        text4 = (TextView) findViewById(R.id.showPack);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
        back_Phase = (ImageView) findViewById(R.id.backPhase);
        back_Phase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }
}