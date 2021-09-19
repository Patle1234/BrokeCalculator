package com.example.calculatorprojectv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainAchievment extends AppCompatActivity {
    public static ListView list;
    private Button btnReturn;
    public static boolean ifSetAdapter=false;

   //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEM
   public static ArrayList<String> achivementList=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    public static ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivement);
        btnReturn=findViewById(R.id.return_btn);
        list=findViewById(R.id.list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, achivementList);
        list.setAdapter(adapter);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnBack();
            }
        });
    }

    private void returnBack(){
        Intent i = new Intent(this,MainActivity2.class);
        startActivity(i);
    }
}
