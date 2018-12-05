package com.example.muhammad.personalmedicalprofile;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewMedicalHistory extends AppCompatActivity {

    private ListView vListView;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_history);

        vListView = findViewById(R.id.viewMedicalLV);
        myDatabaseHelper = new MyDatabaseHelper(this);

        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        loadData();
    }


    public void loadData(){
        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.showAllMedicalData();

        if (cursor.getCount()==0){
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listData.add("ID : "+cursor.getString(0)+"\n"+"NAME : "+cursor.getString(1)+"\n"+"DETAILS : "+cursor.getString(2)+"\n"+"DATE :"+cursor.getString(3));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample_view_medical_history, R.id.sampleMedicalTV, listData);
        vListView.setAdapter(adapter);

    }
}
