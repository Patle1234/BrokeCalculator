package com.example.calculatorprojectv2;

import android.widget.Button;
import android.widget.ImageView;

public class PowerUp {
    private int cost;
    private Button button;
    private int addTime=0;

    public  PowerUp(Button button) {
        this.button = button;
    }


    public int getTime() {


        return addTime;

    }

    public void addTime() {
        addTime+=100;

    }


    public Button getButton() {
        return button;

    }



}
