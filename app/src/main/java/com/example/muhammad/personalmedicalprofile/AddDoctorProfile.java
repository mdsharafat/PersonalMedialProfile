package com.example.muhammad.personalmedicalprofile;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDoctorProfile extends AppCompatActivity {

    private EditText dName;
    private EditText dDetails;
    private EditText dAppointment;
    private EditText dEmail;
    private EditText dPhone;
    private Button dSaveButton;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor_profile);

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        dName = findViewById(R.id.nameDoctorProfileET);
        dDetails = findViewById(R.id.datailsDoctorProfileET);
        dAppointment = findViewById(R.id.appointmentDoctorProfileET);
        dEmail = findViewById(R.id.emailDoctorProfileET);
        dPhone = findViewById(R.id.phoneDoctorProfileET);
        dSaveButton = findViewById(R.id.addDoctorSaveButton);

        dSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String docName = dName.getText().toString();
                String docDetails = dDetails.getText().toString();
                String docAppointment = dAppointment.getText().toString();
                String docEmail = dEmail.getText().toString();
                String docPhone = dPhone.getText().toString();

                long rowId =  myDatabaseHelper.insertData(docName, docDetails, docAppointment, docEmail, docPhone);
                    if (rowId==-1){
                        Toast.makeText(AddDoctorProfile.this, "Error in Inserting Data", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddDoctorProfile.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }


}
