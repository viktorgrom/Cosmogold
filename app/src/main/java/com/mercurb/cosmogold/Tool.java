package com.mercurb.cosmogold;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

import static com.mercurb.cosmogold.GameView.dWidth;

public class Tool {
    Bitmap toollo[] = new Bitmap[9];

    int toolX, toolY, velocitys, toolFrame;
    int width, height;

    Random random;

    public Tool(Context context){

        toollo[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tool1);
        toollo[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tool2);
        toollo[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tool3);
        toollo[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tool4);
        toollo[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tool5);
        toollo[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tool6);
        toollo[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tool7);
        toollo[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tool8);
        toollo[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tool9);



        width = toollo[1].getWidth();
        height = toollo[1].getHeight();

        width /= 4;
        height /= 4;

        toollo[0] = Bitmap.createScaledBitmap(toollo[0], width, height, false);
        toollo[1] = Bitmap.createScaledBitmap(toollo[1], width, height, false);
        toollo[2] = Bitmap.createScaledBitmap(toollo[2], width, height, false);
        toollo[3] = Bitmap.createScaledBitmap(toollo[3], width, height, false);
        toollo[4] = Bitmap.createScaledBitmap(toollo[4], width, height, false);
        toollo[5] = Bitmap.createScaledBitmap(toollo[5], width, height, false);
        toollo[6] = Bitmap.createScaledBitmap(toollo[6], width, height, false);
        toollo[7] = Bitmap.createScaledBitmap(toollo[7], width, height, false);
        toollo[8] = Bitmap.createScaledBitmap(toollo[8], width, height, false);




        random = new Random();
        toolFrame = 0;
        resetPositionPill();

    }

    public Bitmap getBitmap(){
        return  toollo[toolFrame];
    }

    public int getWidth(){
        return  toollo[0].getWidth();
    }

    public int getHeith(){
        return toollo[0].getHeight();
    }


    public void resetPositionPill() {
        toolX = dWidth + random.nextInt(1300);
        toolY = random.nextInt(800);
        velocitys = 10 + random.nextInt(20);
    }
}

