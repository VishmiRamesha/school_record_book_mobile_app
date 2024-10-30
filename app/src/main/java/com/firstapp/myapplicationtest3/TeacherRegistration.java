package com.firstapp.myapplicationtest3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class TeacherRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);

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
                login(UserType);

            }
        });
    }

    public void openLoginPage(String UserType){
        Intent intent1 = new Intent(this, Login.class);
        intent1.putExtra("UserType",UserType);
        startActivity(intent1);
    }

    public void login(String UserType) {
        FirebaseFirestore db = FirebaseFirestore.getInstance(); // Initialize the Firestore instance

        EditText TeacherName = findViewById(R.id.TeacherName);
        EditText TeacherID = findViewById(R.id.TeacherID);
        EditText TeacherNIC = findViewById(R.id.TeacherNIC);
        EditText TeacherContact = findViewById(R.id.TeacherContact);
        EditText TeacherPassword = findViewById(R.id.TeacherPassword);

        String TName = TeacherName.getText().toString();
        String TId = TeacherID.getText().toString();
        String TNIC = TeacherNIC.getText().toString();
        String TContact = TeacherContact.getText().toString();
        String TPassword = TeacherPassword.getText().toString();

        Map<String, Object> TeacherProfile = new HashMap<>();
        TeacherProfile.put("TeacherId", TId);
        TeacherProfile.put("TeacherName", TName);
        TeacherProfile.put("TeacherNIC", TNIC);
        TeacherProfile.put("TeacherContact", TContact);
        TeacherProfile.put("TeacherPassword", TPassword);

        db.collection("TeacherProfile")
                .add(TeacherProfile)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(TeacherRegistration.this, "Your account is registered.", Toast.LENGTH_SHORT).show();
                        openLoginPage(UserType);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TeacherRegistration.this, "Your account is not registered.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}