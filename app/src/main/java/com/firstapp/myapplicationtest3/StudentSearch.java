package com.firstapp.myapplicationtest3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StudentSearch extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);

        String UserType = getIntent().getStringExtra("UserType");

        ImageView Backbtn = findViewById(R.id.ImgBack);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTeacherDashBoard(UserType);
            }
        });


    }
    public void openTeacherDashBoard(String UserType){
        Intent intent1 = new Intent(this, TeacherDashBoard.class);
        intent1.putExtra("UserType",UserType);
        startActivity(intent1);
    }
}