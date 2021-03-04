package com.mercurb.cosmogold;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    Bitmap space_bg;
    Rect rect;

    static int dWidth, dHeight;
    int width_ice, height_ice, width_target, height_target, width_heart, height_heart;

    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS = 30;

    ArrayList<Spaceship1> spaceship1;
    ArrayList<Spaceship2> ghost2;
    ArrayList<Tool> tools;

    Bitmap iceball, targ;
    private Bitmap life[] = new Bitmap[9];
    float ballX, ballY;
    float sX, sY;
    float fX, fY;
    float dX, dY;
    float tempX, tempY;

    Paint borderPaint, scorePaint, lifePaint;

    int score = 0;
    int life_counter = 10;
    Context context;

    Random random;

    boolean gameState = true;


    public GameView(Context context) {
        super(context);
        space_bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg_cosmo);

        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;

        //dWidth = dWidth/ 6;
        //dHeight = dHeight / 6;
        rect = new Rect(0, 0 , dWidth, dHeight);


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();

            }
        };

        random = new Random();



        spaceship1 = new ArrayList<>();
        ghost2 = new ArrayList<>();
        tools = new ArrayList<>();

        for (int i = 0; i<2; i++){
            Spaceship1 money_1 = new Spaceship1(context);
            spaceship1.add(money_1);
            Spaceship2 money_2 = new Spaceship2(context);
            ghost2.add(money_2);
            Tool pillom = new Tool(context);
            tools.add(pillom);

        }


        Tool pillom = new Tool(context);
        tools.add(pillom);




        iceball = BitmapFactory.decodeResource(getResources(), R.drawable.iceball);
        targ = BitmapFactory.decodeResource(getResources(), R.drawable.targetos);

        width_ice = iceball.getWidth();
        height_ice = iceball.getHeight();
        width_target = targ.getWidth();
        height_target = targ.getHeight();

        width_ice /= 5;
        height_ice /=5;
        width_target /= 5;
        height_target /= 5;

        iceball = Bitmap.createScaledBitmap(iceball, width_ice, height_ice, false);
        targ = Bitmap.createScaledBitmap(targ, width_ice, height_ice, false);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearto_on);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.hearto_off);

        width_heart = life[0].getWidth();
        height_heart = life[1].getHeight();

        width_heart /= 4;
        height_heart /= 4;

        life[0] = Bitmap.createScaledBitmap(life[0], width_heart, height_heart, false);
        life[1] = Bitmap.createScaledBitmap(life[1], width_heart, height_heart, false);

        ballX = ballY = 0;
        sX = sY = fX= fY = 0;
        dX = dY = 0;
        tempX = tempY = 0;

        scorePaint = new Paint();
        scorePaint.setTextSize(50);
        scorePaint.setColor(Color.WHITE);



        borderPaint = new Paint();
        borderPaint.setColor(Color.WHITE);
        borderPaint.setStrokeWidth(5);



        this.context = context;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (life_counter < 1){
            gameState = false;
            Intent intent = new Intent(context, GameOver.class);
            intent.putExtra("score", score);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
        canvas.drawBitmap(space_bg,null, rect, null);

        for (int i = 0; i< spaceship1.size(); i++){
            canvas.drawBitmap(spaceship1.get(i).getBitmap(), spaceship1.get(i).space1X, spaceship1.get(i).space1Y, null);

            spaceship1.get(i).spaceship1Frame++;
            if (spaceship1.get(i).spaceship1Frame >4){
                spaceship1.get(i).spaceship1Frame = 0;
            }
            spaceship1.get(i).space1X -= spaceship1.get(i).velocity;
            if (spaceship1.get(i).space1X < -spaceship1.get(i).getWidth()){
                spaceship1.get(i).resetPosition();
                life_counter--;
            }

            canvas.drawBitmap(ghost2.get(i).getBitmap(), ghost2.get(i).space2X, ghost2.get(i).space2Y, null);

            ghost2.get(i).space2Frame++;
            if (ghost2.get(i).space2Frame >4){
                ghost2.get(i).space2Frame = 0;
            }
            ghost2.get(i).space2X -= ghost2.get(i).velocity;
            if (ghost2.get(i).space2X < -ghost2.get(i).getWidth()){
                ghost2.get(i).resetPosition2();
                life_counter--;
            }

            canvas.drawBitmap(tools.get(i).getBitmap(), tools.get(i).toolX, tools.get(i).toolY, null);

            tools.get(i).toolFrame++;
            if (tools.get(i).toolFrame >8){
                tools.get(i).toolFrame = 0;
            }
            tools.get(i).toolX -= tools.get(i).velocitys;

            if (score > 30){
                tools.get(i).toolX -= tools.get(i).velocitys +3;
                ghost2.get(i).space2X -= ghost2.get(i).velocity+3;
                spaceship1.get(i).space1X -= spaceship1.get(i).velocity+3;
            }
            if( score > 80){
                tools.get(i).toolX -= tools.get(i).velocitys +6;
                ghost2.get(i).space2X -= ghost2.get(i).velocity+6;
                spaceship1.get(i).space1X -= spaceship1.get(i).velocity+6;
            }
            if( score > 120){
                tools.get(i).toolX -= tools.get(i).velocitys +10;
                ghost2.get(i).space2X -= ghost2.get(i).velocity+10;
                spaceship1.get(i).space1X -= spaceship1.get(i).velocity+10;
            }
            if( score > 150){
                tools.get(i).toolX -= tools.get(i).velocitys +15;
                ghost2.get(i).space2X -= ghost2.get(i).velocity+15;
                spaceship1.get(i).space1X -= spaceship1.get(i).velocity+15;
            }


            if (tools.get(i).toolX < -tools.get(i).getWidth()){
                tools.get(i).resetPositionPill();

            }

            if (life_counter >10){
                life_counter = 10;
            }

            if (ballX <= (spaceship1.get(i).space1X + spaceship1.get(i).getWidth())
                    && ballX + iceball.getWidth() >= spaceship1.get(i).space1X
                    && ballY <= (spaceship1.get(i).space1Y + spaceship1.get(i).getHeith())
                    && ballY >= spaceship1.get(i).space1Y){
                spaceship1.get(i).resetPosition();
                score++;
            }

            if (ballX <= (ghost2.get(i).space2X + ghost2.get(i).getWidth())
                    && ballX + iceball.getWidth() >= ghost2.get(i).space2X
                    && ballY <= (ghost2.get(i).space2Y + ghost2.get(i).getHeith())
                    && ballY >= ghost2.get(i).space2Y){
                ghost2.get(i).resetPosition2();
                score++;
            }

            if (ballX <= (tools.get(i).toolX + tools.get(i).getWidth())
                    && ballX + iceball.getWidth() >= tools.get(i).toolX
                    && ballY <= (tools.get(i).toolY + tools.get(i).getHeith())
                    && ballY >= tools.get(i).toolY){
                tools.get(i).resetPositionPill();
                life_counter++;

            }

        }
        if (sX > 0 && sY > dHeight * .75f){
            canvas.drawBitmap(targ, sX - targ.getWidth()/2, sY - targ.getHeight()/2, null);
        }
        if ((Math.abs(fX - sX) > 0 || Math.abs(fY - sY) > 0) && fY > 0 && fY > dHeight *.75f){
            canvas.drawBitmap(targ, fX - targ.getWidth()/2, fY - targ.getHeight()/2, null);
        }
        if ((Math.abs(dX) > 10 || Math.abs(dY) > 10) && sY > dHeight * .75f && fY > dHeight * .75f){
            ballX = fX - iceball.getWidth()/2 - tempX;
            ballY = fY - iceball.getHeight()/2 - tempY;
            canvas.drawBitmap(iceball, ballX, ballY, null);
            tempX += dX;
            tempY += dY;

        }

        canvas.drawLine(0, dHeight * .75f, dWidth, dHeight * .75f, borderPaint);
        canvas.drawText("SCORE: " + score, 50, 100, scorePaint);


        for (int i=0; i<10; i++){
            int x = (int) (380 + life[0].getWidth()  *i);
            int y = 30;

            if (i < life_counter){
                canvas.drawBitmap(life[0], x, y, null);
            }
            else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }

        if (gameState)
            handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = dY = fX = fY = tempX = tempY = 0;
                sX = event.getX();
                sY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                fX = event.getX();
                fY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                dX = event.getX();
                dY = event.getY();
                ballX = event.getX();
                ballY = event.getX();
                dX = fX - sX;
                dY = fY - sY;
                break;
        }
        return true;
    }
}
