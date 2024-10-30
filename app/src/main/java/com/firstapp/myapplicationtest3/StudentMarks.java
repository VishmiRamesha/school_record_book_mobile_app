package com.firstapp.myapplicationtest3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Map;

public class StudentMarks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_marks);

        String UserType = getIntent().getStringExtra("UserType"); //taking the user type from previous activity
        String SId = getIntent().getStringExtra("SId"); //taking the kry from previous activity
        String SName = getIntent().getStringExtra("SName"); //taking the kry from previous activity

        ImageView Backbtn = findViewById(R.id.ImgBack);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStudentDashBoard(UserType,SId,SName);
            }
        });

        Spinner Year = findViewById(R.id.Year);

        ArrayAdapter<CharSequence> adapter1 =ArrayAdapter.createFromResource(this,R.array.Year, android.R.layout.simple_dropdown_item_1line);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Year.setAdapter(adapter1);

        Spinner Sem = findViewById(R.id.Term);

        ArrayAdapter<CharSequence> adapter2 =ArrayAdapter.createFromResource(this,R.array.Sem, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Sem.setAdapter(adapter2);


    }

    public void openStudentDashBoard(String UserType,String SId,String SName){
        Intent intent1 = new Intent(this, StudentDashBoard.class);
        intent1.putExtra("UserType", UserType);
        intent1.putExtra("SId", SId); //attaching the user type as variable with intent1
        intent1.putExtra("SName", SName);
        startActivity(intent1);
    }
///////////////////////////////////////
    public void SearchMarks(String UserType,String SId,String SName,String Year,String Sem){
        // Assuming you have a Firestore database instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection(SId+Year+"MarksTable"); // Replace with your collection name

        collectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Map<String, Object> data = document.getData();

                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        String fieldName = entry.getKey(); // Field name
                        Object value = entry.getValue();   // Field value

                        // Do something with the field name and value
                        // For example, print them
                        System.out.println("Field Name: " + fieldName);
                        System.out.println("Field Value: " + value);
                    }
                }
            } else {
                // Handle the query failure here
            }
        });

    }
}