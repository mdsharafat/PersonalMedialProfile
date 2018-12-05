package com.example.muhammad.personalmedicalprofile;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class IndividualDoctorProfile extends AppCompatActivity {

    private ListView vIndividualTV;
    private MyDatabaseHelper myDatabaseHelper;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_doctor_profile);

        vIndividualTV = findViewById(R.id.individualDoctorLV);
        myDatabaseHelper = new MyDatabaseHelper(this);
        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.showAllData();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            
        }

        /*if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "No Data Available", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listData.add("NAME : "+cursor.getString(1)+"\n"+"DETAILS : "+cursor.getString(2)
                        +"\n"+"APPOINTMENT : "+cursor.getString(3)+"\n"+"EMAIL :"+cursor.getString(4)+"\n"+"PHONE :"+cursor.getString(5));
            }
        }*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.sample_individual_doctor, R.id.individualDoctorTV, listData);
        vIndividualTV.setAdapter(adapter);
    }
}
