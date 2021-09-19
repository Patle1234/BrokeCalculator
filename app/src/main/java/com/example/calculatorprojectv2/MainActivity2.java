package com.example.calculatorprojectv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button buttonPlay , buttonShop,buttonAchivement;
    private boolean ifPlay=false;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

        buttonShop = (Button) findViewById(R.id.buttonShop);
        buttonShop.setOnClickListener(this);

        buttonAchivement=findViewById(R.id.activity_achivement);
        buttonAchivement.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonPlay) {
            openLevelOne();
        }

        if(view.getId() == R.id.buttonShop) {
            openShop();
        }
        if(view.getId()==R.id.activity_achivement){
            openAchievment();
        }
    }

    public void openLevelOne() {
        ifPlay=true;
        Intent intent = new Intent(this,LevelOneActivity.class);
        startActivity(intent);
    }

    public void openShop() {

            Intent intent = new Intent(this, MainActivityShop.class);
            startActivity(intent);
    }

    public void openAchievment() {
        System.out.println("ifPlay: "+ifPlay);

            Intent intent = new Intent(this, MainAchievment.class);
            startActivity(intent);

    }


}