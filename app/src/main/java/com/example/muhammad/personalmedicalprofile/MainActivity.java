package com.example.muhammad.personalmedicalprofile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Home Page Button
    private Button mAddDoctorButton;
    private Button mViewDoctorButton;
    private Button mUpdateDoctorButton;
    private Button mAddMedicalButton;
    private Button mViewMedicalButton;



    //Database
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddDoctorButton = findViewById(R.id.goToAddDoctorButton);
        mViewDoctorButton = findViewById(R.id.goToViewDoctorButton);
        mUpdateDoctorButton = findViewById(R.id.goToUpdateDoctorButton);
        mAddMedicalButton = findViewById(R.id.goToAddMedicalButton);
        mViewMedicalButton = findViewById(R.id.goToViewMedicalButton);



        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        mAddDoctorButton.setOnClickListener(this);
        mViewDoctorButton.setOnClickListener(this);
        mUpdateDoctorButton.setOnClickListener(this);
        mAddMedicalButton.setOnClickListener(this);
        mViewMedicalButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.goToAddDoctorButton){
            Intent intent = new Intent(MainActivity.this, AddDoctorProfile.class);
            startActivity(intent);
        }else if (view.getId() == R.id.goToViewDoctorButton){
            Intent intent = new Intent(MainActivity.this, ViewDoctorProfile.class);
            startActivity(intent);
        }else if (view.getId() == R.id.goToUpdateDoctorButton){
            Intent intent = new Intent(MainActivity.this, UpdateDoctorProfile.class);
            startActivity(intent);
        }else if (view.getId() == R.id.goToAddMedicalButton){
            Intent intent = new Intent(MainActivity.this, AddMedicalHistory.class);
            startActivity(intent);
        }else if (view.getId() == R.id.goToViewMedicalButton){
            Intent intent = new Intent(MainActivity.this, ViewMedicalHistory.class);
            startActivity(intent);
        }
    }
}
