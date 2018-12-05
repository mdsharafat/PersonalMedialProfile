package com.example.muhammad.personalmedicalprofile;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDoctorProfile extends AppCompatActivity {

    private ListView vListView;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor_profile);



        vListView = findViewById(R.id.viewDoctorLV);
        myDatabaseHelper = new MyDatabaseHelper(this);

        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        loadData();
    }

    public void loadData(){
        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.showAllData();

        if (cursor.getCount()==0){
            Toast.makeText(this, "No Data Available", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listData.add("ID : "+cursor.getString(0)+"\n"+"NAME : "+cursor.getString(1)+"\n"+"DETAILS : "+cursor.getString(2)
                        +"\n"+"APPOINTMENT : "+cursor.getString(3)+"\n"+"EMAIL :"+cursor.getString(4)+"\n"+"PHONE :"+cursor.getString(5));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample_view_doctor_profile, R.id.sampleDoctorTV, listData);
        vListView.setAdapter(adapter);

    }

}
