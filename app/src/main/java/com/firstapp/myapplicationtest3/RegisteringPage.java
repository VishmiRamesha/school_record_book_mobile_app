package com.firstapp.myapplicationtest3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisteringPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registering_page);

        String UserType = getIntent().getStringExtra("UserType");

        ImageView Back = findViewById(R.id.BackImg);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage(UserType);
            }
        });

        Button Next = findViewById(R.id.Next);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register(UserType);

            }
        });


    }

    public void openLoginPage(String UserType){
        Intent intent1 = new Intent(this, Login.class);
        intent1.putExtra("UserType",UserType);
        startActivity(intent1);
    }

    public void StudentRegister(){


        EditText StudentName = findViewById(R.id.StudentName);
        EditText StudentID = findViewById(R.id.StudentID);
        EditText StudentGrade = findViewById(R.id.StudentGrade);
        EditText StudentContact = findViewById(R.id.StudentContact);
        EditText StudentClass = findViewById(R.id.StudentClass);
        EditText StudentPassword = findViewById(R.id.StudentPassword);

        String SName =StudentName.getText().toString();
        String SId =StudentID.getText().toString();

        String studentGradeStr = StudentGrade.getText().toString(); // Assuming StudentGrade.get() returns a String
        int SGrade = Integer.parseInt(studentGradeStr);

        String SCon =StudentContact.getText().toString();
        String SClass =StudentClass.getText().toString();
        String SPassword =StudentPassword.getText().toString();

        Context context =this;
        DBHelper dbHelper = new DBHelper(context);

        DBModel DBMod =new DBModel(SId,SName,SGrade,SClass,SCon,SPassword);
        if(dbHelper.AddAccount(DBMod)){
            Toast.makeText(RegisteringPage.this,"Your account is registered.",Toast.LENGTH_SHORT).show();
        }
    }

    public void Register(String UserType) {
        FirebaseFirestore db = FirebaseFirestore.getInstance(); // Initialize the Firestore instance

        EditText StudentName = findViewById(R.id.StudentName);
        EditText TeacherID = findViewById(R.id.StudentID);
        EditText StudentGrade = findViewById(R.id.StudentGrade);
        EditText StudentClass = findViewById(R.id.StudentClass);
        EditText StudentContact = findViewById(R.id.StudentContact);
        EditText StudentPassword = findViewById(R.id.StudentPassword);

        String SName = StudentName.getText().toString();
        String SId = TeacherID.getText().toString();
        int SGrade = Integer.parseInt(StudentGrade.getText().toString());
        String SClass = StudentClass.getText().toString();
        String SContact = StudentContact.getText().toString();
        String SPassword = StudentPassword.getText().toString();

        Map<String, Object> StudentProfile = new HashMap<>();
        StudentProfile.put("StudentId", SId);
        StudentProfile.put("StudentName", SName);
        StudentProfile.put("StudentGrade", SGrade);
        StudentProfile.put("StudentClass", SClass);
        StudentProfile.put("StudentContact", SContact);
        StudentProfile.put("StudentPassword", SPassword);

        db.collection("StudentProfile")
                .add(StudentProfile)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegisteringPage.this, "Your account is registered.", Toast.LENGTH_SHORT).show();
                        openLoginPage(UserType);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisteringPage.this, "Your account is not registered.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}