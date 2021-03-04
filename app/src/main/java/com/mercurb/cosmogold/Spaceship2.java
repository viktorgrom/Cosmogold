package com.mercurb.cosmogold;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

import static com.mercurb.cosmogold.GameView.dWidth;

public class Spaceship2 extends Spaceship1 {

    Bitmap[] space2 = new Bitmap[5];

    int space2X, space2Y, velocity, space2Frame;
    int width, height;
    Random random;

    public Spaceship2(Context context) {
        super(context);

        space2[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaces1_1);
        space2[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaces1_2);
        space2[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaces1_3);
        space2[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaces1_4);
        space2[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaces1_5);


        width = space2[1].getWidth();
        height = space2[1].getHeight();

        width /= 4;
        height /= 4;

        space2[0] = Bitmap.createScaledBitmap(space2[0], width, height, false);
        space2[1] = Bitmap.createScaledBitmap(space2[1], width, height, false);
        space2[2] = Bitmap.createScaledBitmap(space2[2], width, height, false);
        space2[3] = Bitmap.createScaledBitmap(space2[3], width, height, false);
        space2[4] = Bitmap.createScaledBitmap(space2[4], width, height, false);



        random = new Random();

        space2Frame = 0;
        resetPosition2();

    }

    public Bitmap getBitmap() {
        return space2[space2Frame];
    }

    public int getWidth() {
        return space2[0].getWidth();
    }

    public int getHeith() {
        return space2[0].getHeight();
    }


    public void resetPosition2() {
        space2X = dWidth + random.nextInt(1300);
        space2Y = random.nextInt(800);
        velocity = 5 + random.nextInt(12);
    }
}
