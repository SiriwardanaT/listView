package com.example.listview;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference ref;
    Button button;
    TextView textView;
    ListView customers;
    EditText names,ages,dess;
    TextView noItem;

    ProgressBar progressBar;
    ArrayList<Customer> cus = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button1);
        names = findViewById(R.id.Cus_Name);
        ages = findViewById(R.id.age);
        dess = findViewById(R.id.des);
        noItem = findViewById(R.id.textView4);
        progressBar = findViewById(R.id.progressBar);
        customers = findViewById(R.id.listView1);




        readValue();
        insertValue();


    }

    private void insertValue() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref =FirebaseDatabase.getInstance().getReference("customer");

                String id= ref.push().getKey();
                Customer customer = new Customer(id ,names.getText().toString().trim(),ages.getText().toString().trim(),dess.getText().toString().trim());
                ref.child(id).setValue(customer);

                clear();



            }
        });
    }

    private void clear() {
        names.setText("");
        ages.setText("");
        dess.setText("");

    }

    private void readValue(){

                progressBar.setVisibility(View.VISIBLE);
                ref = FirebaseDatabase.getInstance().getReference().child("customer");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChildren()) {
                            cus.clear();
                            for(DataSnapshot d : snapshot.getChildren()) {
                                 Customer c = d.getValue(Customer.class);
                                 cus.add(c);

                           }
                            ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(MainActivity.this,cus);
                            customers.setAdapter(itemArrayAdapter);

                            customers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(MainActivity.this,setFactory.class);
                                    System.out.println(cus.get(position).getId());
                                    intent.putExtra("id",cus.get(position).getId());
                                    intent.putExtra("name",cus.get(position).getName());
                                    intent.putExtra("price",cus.get(position).getAge());
                                    intent.putExtra("des",cus.get(position).getDescription());
                                    startActivity(intent);

                                }
                            });
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            noItem.setVisibility(View.VISIBLE);
                            noItem.setText("No Customers");
                            noItem.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });









    }
}