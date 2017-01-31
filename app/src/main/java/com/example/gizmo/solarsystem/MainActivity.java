package com.example.gizmo.solarsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etRadius, etDistance;
    SolarSystem solarsystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etDistance = (EditText) findViewById(R.id.distance);
        etRadius = (EditText) findViewById(R.id.radius);
        solarsystem = (SolarSystem) findViewById(R.id.view);
    }

    public void onAddPlanetClick(View v)
    {
        float radius = Float.parseFloat(etRadius.getText().toString());
        float distance = Float.parseFloat(etDistance.getText().toString());
        solarsystem.addPlanet(radius,distance);

    }
    public void onResetClick(View v)
    {
        solarsystem.reset();
        solarsystem.myTask.cancel(true);
    }
    public void onMotionClick(View v)
    {
        solarsystem.startMotion();
    }

}
