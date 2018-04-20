package com.example.snowk.exercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Getting events will be here soon but we don't ahve google calendar access yet but wehn we do wei will be good.
        ArrayList<Event> listofevents = new ArrayList<Event>();
        //Generator of Exercise Master List
        InputStream is = (InputStream) getResources().openRawResource(R.raw.exerciselist);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";
        ExerciseMasterList masterlists = new ExerciseMasterList();
        try {
            while ((line = reader.readLine()) != null) {
                //Split line by ","
                String[] fields = line.split(",");
                Exercise exercise = new Exercise(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), Integer.parseInt(fields[4]));
                masterlists.addexercise(exercise);
            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading data from file on line " + line);
        }

        //notification code
        Button buttonExercise = findViewById(R.id.dateButton);
        buttonExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationpopup();
            }
        });
        //Cosntructing A Day
        Date d = new Date();
        String date = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());
        int dateinteger = Integer.parseInt(date);

        Day day = new Day(dateinteger, listofevents, masterlists);


    }


    public void scheduleexercises(){

    }

    //code for connecting
    public void notificationpopup(){
        Intent noti = new Intent(this, notificationActivity.class);
        startActivity(noti);
    }


    }





