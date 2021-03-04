package com.mercurb.cosmogold;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

import static com.mercurb.cosmogold.GameView.dWidth;


public class Spaceship1 {

    Bitmap space1[] = new Bitmap[5];

    int space1X, space1Y,  spaceship1Frame;
    public int velocity;
    int width, height;

    Random random;

    public Spaceship1(Context context){

        space1[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.space1);
        space1[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.space2);
        space1[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.space3);
        space1[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.space4);
        space1[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.space5);


        width = space1[1].getWidth();
        height = space1[1].getHeight();

        width /= 4;
        height /= 4;

        space1[0] = Bitmap.createScaledBitmap(space1[0], width, height, false);
        space1[1] = Bitmap.createScaledBitmap(space1[1], width, height, false);
        space1[2] = Bitmap.createScaledBitmap(space1[2], width, height, false);
        space1[3] = Bitmap.createScaledBitmap(space1[3], width, height, false);
        space1[4] = Bitmap.createScaledBitmap(space1[4], width, height, false);



        random = new Random();
        spaceship1Frame = 0;
        resetPosition();

    }

    public Bitmap getBitmap(){
        return  space1[spaceship1Frame];
    }

    public int getWidth(){
        return  space1[0].getWidth();
    }

    public int getHeith(){
        return space1[0].getHeight();
    }


    public void resetPosition() {
        space1X = dWidth + random.nextInt(1300);
        space1Y = random.nextInt(700);
        velocity = 6 + random.nextInt(11);
    }
}
