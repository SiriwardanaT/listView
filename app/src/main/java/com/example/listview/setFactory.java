package com.example.listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

    TextView productView , PnameText , qunText, desText,pricesText;
    Button addcart;
    Button add;
    Button remo;
    private static int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_factory);

        productView = findViewById(R.id.productView);
        PnameText = findViewById(R.id.name);
        qunText = findViewById(R.id.qun);
        desText = findViewById(R.id.description);
        addcart = findViewById(R.id.addCart);
        add = findViewById(R.id.AddButton);
        remo = findViewById(R.id.remButton);
        pricesText = findViewById(R.id.price);


        //get values from main activity
        Intent intent = getIntent();
        String id =intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String description = intent.getStringExtra("des");

        //set values
        productView.setText(name);
        PnameText.setText(name);
        pricesText.setText(price);
        desText.setText(description);

        int pri = Integer.parseInt(price);

        cartHadler(pri);





    }

    private void cartHadler(int price){

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                qunText.setText(String.valueOf(count));
                remo.setEnabled(true);


            }
        });
        remo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count ==0){

                    remo.setEnabled(false);


                }
                else {
                    count--;
                    qunText.setText(String.valueOf(count));

                }
            }
        });



    }





}
