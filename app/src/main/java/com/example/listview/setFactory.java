package com.example.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setFactory extends AppCompatActivity {

    TextView nameText,ageText,desText;
    EditText factName;
    Button addFac;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_factory);

        nameText = findViewById(R.id.fac_name);
        ageText = findViewById(R.id.fac_age);
        desText = findViewById(R.id.fac_des);
        addFac = findViewById(R.id.button);
        factName = findViewById(R.id.fac_n);

        Intent intent = getIntent();
        String id  = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String des = intent.getStringExtra("des");
        String fac_name = factName.getText().toString().trim();
        nameText.setText(name);
        ageText.setText(age);
        desText.setText(des);


        InsertFacotry(id,fac_name,name);


    }

    private void InsertFacotry(String id,String facName , String name) {
        addFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Factory factory = new Factory(name,facName);
                FirebaseDatabase.getInstance().getReference().child("factory").child(id).setValue(factory).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(setFactory.this, "Successfully added", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}