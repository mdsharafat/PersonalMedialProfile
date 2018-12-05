package com.example.muhammad.personalmedicalprofile;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDoctorProfile extends AppCompatActivity {


    MyDatabaseHelper myDatabaseHelper;

    private EditText uId;
    private EditText uName;
    private EditText uDetails;
    private EditText uAppointment;
    private EditText uEmail;
    private EditText uPhone;
    private Button uSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctor_profile);

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        uId = findViewById(R.id.idUpdateDoctorET);
        uName = findViewById(R.id.nameUpdateDoctorET);
        uDetails = findViewById(R.id.datailsUpdateDoctorET);
        uAppointment = findViewById(R.id.appointmentUpdateDoctorET);
        uEmail = findViewById(R.id.emailUpdateDoctorET);
        uPhone = findViewById(R.id.phoneUpdateDoctorET);
        uSaveButton = findViewById(R.id.updateDoctorButton);


        uSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String docId = uId.getText().toString();
                String docName = uName.getText().toString();
                String docDetails = uDetails.getText().toString();
                String docAppointment = uAppointment.getText().toString();
                String docEmail = uEmail.getText().toString();
                String docPhone = uPhone.getText().toString();

                Boolean isUpdated = myDatabaseHelper.updateData(docId, docName, docDetails, docAppointment, docEmail, docPhone);
                if (isUpdated == true){
                    Toast.makeText(UpdateDoctorProfile.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UpdateDoctorProfile.this, "Error in Updating Data", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
