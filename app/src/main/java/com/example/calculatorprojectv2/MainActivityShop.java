package com.example.calculatorprojectv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityShop extends AppCompatActivity implements View.OnClickListener{
    Button buttonTimer, buttonBack;
    public static long timer2=0;//addition to timer
    public static TextView amtGold;
    private Button buttonCoins;
    public static int increaseCoins=1;

    public static int coins2 = 10000;//total coins
    private static int timerAmount =7;//inital cost of the things(for timer)
    private static int goldAmount =7;//inital cost of thing(for gold)






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop);
        amtGold=findViewById(R.id.goldDisplay);
        buttonTimer = (Button) findViewById(R.id.buttonTimer);
        buttonTimer.setOnClickListener(this);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);
        buttonCoins = (Button) findViewById(R.id.buttonCoins);
        buttonCoins.setOnClickListener(this);
        buttonTimer.setText("Cost: " + timerAmount + " COINS");
        buttonTimer.setText("Cost: " + goldAmount + " COINS");
        MainActivityShop.amtGold.setText("Coins: "+(coins2));

    }



    @Override
    public void onClick(View view) {
        System.out.println("hello");
        if(timerAmount < 56)
            System.out.println("hello tow");
            if (coins2 >= timerAmount){
                System.out.println("hello three");
                if (view.getId() == R.id.buttonTimer) {
                    System.out.println("hello four");
                    timer2 += 3000;
                    coins2 =coins2-timerAmount;
                    MainActivityShop.amtGold.setText("Coins: "+(coins2));
                    timerAmount=timerAmount*2;
                    System.out.println("print:timer "+timerAmount);
                    buttonTimer.setText("Cost: " + timerAmount + " COINS");
                }
            }

        if(goldAmount < 56) {
            if (coins2 >= goldAmount) {
                if (view.getId() == R.id.buttonCoins) {
                    increaseCoins += 1;
                    coins2 -= goldAmount;
                    MainActivityShop.amtGold.setText("Coins: "+(coins2));
                    goldAmount *= 2;
                    buttonCoins.setText("Cost: " +goldAmount + " COINS");

                }

            }
        }
        if (view.getId() == R.id.buttonBack) {
            returnBack();
        }
    }
    private void returnBack(){
        Intent i = new Intent(this,MainActivity2.class);
        startActivity(i);
    }
}