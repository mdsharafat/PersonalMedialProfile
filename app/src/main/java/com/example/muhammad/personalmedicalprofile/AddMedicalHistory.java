package com.example.muhammad.personalmedicalprofile;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class AddMedicalHistory extends AppCompatActivity {

    private EditText mDoctorName;
    private EditText mDoctorDetails;
    private EditText mDate;
    private Button mMedicalButton;


    MyDatabaseHelper myDatabaseHelper;

    //Medical History
    DatePickerDialog datePickerDialog;
    private EditText mDatePicker;

    //Camera
    private ImageView mImageView;
    private Button mCameraButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int MY_CAMERA_REQUEST_CODE = 2;
    private String mPhotoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical_history);

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        mDoctorName = findViewById(R.id.nameDoctorMedicalET);
        mDoctorDetails = findViewById(R.id.datailsDoctorMedicalET);
        mDate = findViewById(R.id.dateMedicalET);
        mMedicalButton = findViewById(R.id.addMedicalSaveButton);

        mMedicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String docName = mDoctorName.getText().toString();
                String docDetails = mDoctorDetails.getText().toString();
                String date = mDate.getText().toString();

                long rowId =  myDatabaseHelper.addData(docName, docDetails, date);
                if (rowId==-1){
                    Toast.makeText(AddMedicalHistory.this, "Error in Inserting Data", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddMedicalHistory.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }



            }
        });

        mImageView = findViewById(R.id.medicalImageView);
        mCameraButton = findViewById(R.id.cameraButton);

        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent ();
            }
        });
        getCameraPermission();

        mDatePicker = findViewById(R.id.dateMedicalET);
        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddMedicalHistory.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        mDatePicker.setText(day+"-"+(month+1)+"-"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK ) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            mPhotoData = encodeToBase64 (imageBitmap,Bitmap.CompressFormat.JPEG, 50);
            System.out.println(mPhotoData);
            mImageView.setImageBitmap(imageBitmap);
        }
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void getCameraPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
        }
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }


    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

}
