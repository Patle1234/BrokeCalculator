  package com.example.calculatorprojectv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class LevelOneActivity extends AppCompatActivity implements View.OnClickListener{
    TextView display, goalDisplay, buttonClickCounter, constraintDisplay, levelDisplay; //add a TextView for the number that the use has to reach
    Button bOne, bTwo, bThree, bFour, bFive, bSix, bSeven, bEight, bNine, bAdd, bSub, bMulti, bDiv, cButton,bBack;

    private int clickCounter = 0;
    private String displayLabel = "";
    private int level = 0;
    private int numUseOne=0;
    private int clickGoal;
    private double currentGoal;//the wanted number

    public static long remainingTime=60000;
    private CountDownTimer timer;

    public static int coins = 0;
    TextView coinDisplay;

    TextView timerText;
    int time;


    private int numClicksAllowed;//click limit
    private boolean ifMetGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        time=1000;
        timerText=findViewById(R.id.timer);


        bOne = (Button) findViewById(R.id.buttonOne);
        bTwo = (Button) findViewById(R.id.buttonTwo);
        bThree = (Button) findViewById(R.id.buttonThree);
        bFour = (Button) findViewById(R.id.buttonFour);
        bFive = (Button) findViewById(R.id.buttonFive);
        bSix = (Button) findViewById(R.id.buttonSix);
        bSeven = (Button) findViewById(R.id.buttonSeven);
        bEight = (Button) findViewById(R.id.buttonEight);
        bNine = (Button) findViewById(R.id.buttonNine);
        bAdd = (Button) findViewById(R.id.additionButton);
        bSub = (Button) findViewById(R.id.subtractionButton);
        bMulti = (Button) findViewById(R.id.multiplicationButton);
        bDiv = (Button) findViewById(R.id.divisionButton);
        cButton = (Button) findViewById(R.id.calculateButton);
        //^^ The numerical calculator buttons

        bBack=findViewById(R.id.buttonBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });
        bOne.setOnClickListener(this);
        bTwo.setOnClickListener(this);
        bThree.setOnClickListener(this);
        bFour.setOnClickListener(this);
        bFive.setOnClickListener(this);
        bSix.setOnClickListener(this);
        bSeven.setOnClickListener(this);
        bEight.setOnClickListener(this);
        bNine.setOnClickListener(this);
        bAdd.setOnClickListener(this);
        bSub.setOnClickListener(this);
        bMulti.setOnClickListener(this);
        bDiv.setOnClickListener(this);
        cButton.setOnClickListener(this);
        //^^ For the Click Listener for the Button

        display = (TextView) findViewById(R.id.display);
        goalDisplay = (TextView) findViewById((R.id.goalDisplay));
        buttonClickCounter = (TextView) findViewById(R.id.buttonClickCounter);
        constraintDisplay = (TextView) findViewById(R.id.constraintDisplay);
        levelDisplay = (TextView) findViewById(R.id.levelLabel);
        coinDisplay=findViewById(R.id.txtCoins);
        //^^ Sets the Displays
        runTimer(remainingTime);

        run();
    }

    private void returnHome(){
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

    private void run(){
        randomize();
      //  remainingTime =+MainActivityShop.timer2;
        clickCounter = 0;
        goalDisplay.setText("Goal: " + currentGoal);
        constraintDisplay.setText(clickGoal+" Buttons");
        buttonClickCounter.setText("Button Clicks: " + clickCounter);
        level++;
        if(level>1) {
            levelDisplay.setText("Level: " + level);
        }
    }



    private void runTimer(long timeInMillis){//https://stackoverflow.com/questions/11630493/how-to-add-and-remove-time-on-countdowntimer
        if(timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(timeInMillis, 1000) {
            int min=0;
            int sec=0;
            int secTillChange=0;
            @Override
            public void onTick(final long millisUntilFinished) {
                sec=(int)(millisUntilFinished/1000);
                if(sec>59){
                    min=(int)(millisUntilFinished/60000);
                }else{
                    min=0;
                }
                sec=sec%60;
                if(sec<10){
                    timerText.setText(min+":0"+sec);
                }else{
                    timerText.setText(min+":"+sec);
                }

                secTillChange++;
                remainingTime = millisUntilFinished;
                if(timeInMillis<60000 && secTillChange>3){
                    secTillChange=0;
                }
            }

            @Override
            public void onFinish() {
                 System.out.println("coins before: "+MainActivityShop.coins2);
                MainActivityShop.coins2= MainActivityShop.coins2+coins;
                System.out.println("coins after: "+MainActivityShop.coins2);
                coins=0;
                timerText.setText("");
                remainingTime =60000+MainActivityShop.timer2;
                openActivity2();
                System.out.println(remainingTime + " remainingTime");
            }
        }.start();
    }


    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Context context = getApplicationContext();
        CharSequence keystrokeOver = "Too many Button Presses!";
        CharSequence sillyGoose = "I said Addition you silly goose :)";
        CharSequence oneLimit = "Cannot use any more ones";
        int duration = Toast.LENGTH_SHORT;
        Toast achievement= Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

        Toast toast = Toast.makeText(context, keystrokeOver, duration);
        Toast fgd = Toast.makeText(context, sillyGoose, duration);

        Toast toastOnes = Toast.makeText(context, oneLimit, duration);

        Context contextTwo = getApplicationContext();
        CharSequence textOver = "Goal Overshot!";
        CharSequence textUnder = "Goal Undershot!";
        int durationTwo = Toast.LENGTH_SHORT;

        Toast underShot = Toast.makeText(contextTwo, textUnder, durationTwo);
        Toast overShot = Toast.makeText(contextTwo, textOver, durationTwo);

        switch (view.getId()){
            case R.id.buttonOne:
                if(numUseOne<3) {
                    clickCounter++;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                    displayLabel = displayLabel.concat("1");
                    display.setText(displayLabel);
                    numUseOne++;
                    if (clickCounter > clickGoal) {
                        toast.show();
                        displayLabel = "";
                        display.setText(displayLabel);
                        clickCounter = 0;
                        buttonClickCounter.setText("Button Clicks: " + clickCounter);
                    }
                }else{
                    toastOnes.show();
                }

                break;
            case R.id.buttonTwo:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("2");
                display.setText(displayLabel);

                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }
                break;
            case R.id.buttonThree:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("3");
                display.setText(displayLabel);
                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }
                break;
            case R.id.buttonFour:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("4");
                display.setText(displayLabel);
                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }
                break;
            case R.id.buttonFive:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("5");
                display.setText(displayLabel);
                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }
                break;
            case R.id.buttonSix:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("6");
                display.setText(displayLabel);

                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }

                break;
            case R.id.buttonSeven:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("7");
                display.setText(displayLabel);
                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }

                break;
            case R.id.buttonEight:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("8");
                display.setText(displayLabel);
                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }
                break;
            case R.id.buttonNine:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("9");
                display.setText(displayLabel);
                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }

                break;
            case R.id.additionButton:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("+");
                display.setText(displayLabel);
                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }

                break;
            case R.id.subtractionButton:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("-");
                display.setText(displayLabel);
                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }
                break;

            case R.id.multiplicationButton:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("×");
                display.setText(displayLabel);

                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }

                break;

            case R.id.divisionButton:
                clickCounter++;
                buttonClickCounter.setText("Button Clicks: " + clickCounter);
                displayLabel = displayLabel.concat("÷");
                display.setText(displayLabel);

                if(clickCounter>clickGoal){
                    toast.show();
                    displayLabel = "";
                    display.setText(displayLabel);
                    clickCounter = 0;
                    buttonClickCounter.setText("Button Clicks: " + clickCounter);
                }
                break;

            case R.id.calculateButton:

                if(level%5==0){
                    numUseOne=0;
                }
                if(level%3==0){//CHANGE TO 10
                    achievement.setText("completed "+level+" levels");
                    achievement.show();
                    if(MainAchievment.achivementList.size()*3<level){
                        MainAchievment.achivementList.add("completed " + level + " levels");
                    }
                }
                String expEval = display.getText().toString();

                expEval = expEval.replaceAll("×", "*");
                expEval = expEval.replaceAll("÷", "/");

                Expression exp = new Expression(expEval);

                String resultS = String.valueOf(exp.calculate());
                double result = Double.parseDouble(resultS);
                if(result==currentGoal){
                    coins=coins+MainActivityShop.increaseCoins;
                    coinDisplay.setText("Coins Collected: "+coins);
                    run();

                }else{
                    runTimer(remainingTime-3000);
                    if (result > currentGoal){
                        overShot.show();
                    } else {
                        underShot.show();
                    }
                }
                displayLabel = "";
                clickCounter = 0;
                display.setText(displayLabel);
                break;
        }
    }

    private void randomize(){
        if(11>level){
            getRandomNums(0);
        }else if(31>level){
            getRandomNums(1);
        }else{
            getRandomNums(2);
        }
    }


    public void getRandomNums(int difficulty){//creates the goal.
        int numClicks=0;//clicks allowed
        int goal=0;//the goal number
        int randSign=randNum(0,3);//0=plus, 1=minus,2=multiplication,3=division
        int r1=randNum(1,9);
        int r2=randNum(1,9);
        System.out.println("r1: "+r1);
        System.out.println("r2: "+r2);
        System.out.println("randSign:  "+randSign);
        if(difficulty==0){//easy
            switch (randSign) {
                case 0:
                    goal=r1+r2;
                    numClicks=+3;
                    System.out.println("add");
                    break;
                case 1:
                    if(r1>r2){
                        goal=r1-r2;
                    }else if(r2>r1){
                        goal=r2-r1;
                    }else{
                        int x=randNum(2,5);//range you can multiply and still complete in 3 btns
                        goal=(x*r2)-r1;
                    }
                    numClicks=+3;
                    System.out.println("Subtract");
                    break;
                case 2:
                    goal=r1*r2;
                    numClicks=+3;
                    System.out.println("multiply");
                    break;
                case 3:
                    if(r1<r2){
                        goal=r2/r1;
                    }else{
                        goal=r1/r2;
                    }
                    numClicks=+3;
                    System.out.println("Divide");
                    break;
            }

        }else if(difficulty==1){//medium
            int r3=randNum(1,100);
            System.out.println("r3: "+r3);
            switch (randSign) {
                case 0:
                    goal=r1+r2+r3;
                    numClicks=+9;
                    System.out.println("add");
                    break;
                case 1:
                    if(r1>r2&&r2>r3){
                        goal=r1+r2-r2;
                    }else if(r3>r1&&r1>r2){
                        goal=r2+r3-r1;
                    }else if(r2>r3 &&r3>r1){
                        goal=r1+r2-r3;
                    }else{
                        goal=r1+r2+r3;//if two of the vars are the same, then just add
                    }
                    numClicks=+9;
                    System.out.println("Subtract");
                    break;
                case 2:
                    goal=r1*r2+r3;
                    numClicks=+9;
                    System.out.println("multiply");
                    break;
                case 3:
                    if(r1>r2&&r2>r3){
                        goal=(r1+r2)/r2;
                    }else if(r3>r1&&r1>r2){
                        goal=(r1+r2)/r1;
                    }else{
                        goal=(r1+r2)/r3;
                    }
                    numClicks=+9;
                    System.out.println("Divide");
                    break;
            }
        }else if(difficulty==2){//hard
            int r3=randNum(1,100);
            int r4=randNum(1,10);
            
            switch (randSign) {
                case 0:
                    goal=r1+r2+r3+r4;
                    numClicks=+12;
                    System.out.println("add");
                    break;
                case 1:
                    if(r1>r2&&r2>r3){
                        goal=r1+r2-r2;
                    }else if(r3>r1&&r1>r2){
                        goal=r2+r3-r1;

                    }else{
                        goal=r1+r2-r3;
                    }
                    numClicks=+12;
                    System.out.println("Subtract");
                    break;
                case 2:
                    goal=r1*r2+r3*r4;
                    numClicks=+12;
                    System.out.println("multiply");
                    break;
                case 3:
                    if(r1>r2&&r2>r3){
                        goal=(r1+r2)/r2;
                    }else if(r3>r1&&r1>r2){
                        goal=(r1+r2)/r1;
                    }else{
                        goal=(r1+r2)/r3;
                    }
                    numClicks=+12;
                    System.out.println("Divide");;

                    break;
            }
        }
        currentGoal=goal;
        clickGoal=numClicks;
    }


    public int randNum(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
}
