package com.example.gizmo.solarsystem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SolarSystem extends View {
    MyTask myTask = new MyTask();
    Sun sun = new Sun(100, Color.YELLOW, 300, 250/2);
    ArrayList<Planet> planets = new ArrayList<>();
    public SolarSystem(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
    }
    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        sun.setX(canvas.getWidth() / 2); sun.setY(canvas.getHeight() / 2);
        //canvas.drawColor(Color.LTGRAY);
        sun.Draw(canvas);
        for (Planet p : planets)
        {
            Paint paint = new Paint();
            paint.setColor(p.color);
            canvas.drawCircle(p.getX(), p.getY(), p.radius, paint);
        }
    }
    public void addPlanet(float r, float d)
    {
        Random random = new Random();

        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        planets.add(new Planet(r, d, (float)0.1, (float) random.nextFloat()*6.28f, color, sun));
        invalidate();
    }
    public void reset()
    {
        planets.clear();
        invalidate();
    }
    class MyTask extends AsyncTask<Void, Void, Void>
    {
        int seconds = 0;
        @Override
        protected Void doInBackground(Void... params) {
            seconds = 0; // добавить задержку
            while (seconds < 1000) {
                // ждем 1 секунду
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {}
                seconds++;
                publishProgress(); // вызов этой функции обеспечит вызом onProgressUpdate
            }
            return null;
        }
        protected void onProgressUpdate(Void... progress)
        {
            for (Planet p : planets)
            {
                p.angle += p.avelocity;

            }
            invalidate();
        }

    }
    public void startMotion()
    {

        myTask.execute();
    }
}
