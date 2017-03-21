package com.example.csaper6.pokequiz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {

    private String[] color;
    private String[] habitat;
    private String[] bodyShape;
    private String[] pokemonFigures;
    private String[] questions;

    private TextView question;
    private Button optA, optB, optC, optD;
    public static final String TAG = MainActivity.class.getSimpleName();

    private int qIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String baseUrl = "http://pokeapi.co/api/v2/";
        new GetPokeData().execute(baseUrl);

        String[] color = new String[]{
                "Black", "White", "Red", "Orange", "Yellow", "Green",
                "Blue", "Indigo", "Violet", "Gray"
        };

        String[] habitat = new String[]{
                "Grassland", "Forest", "Water's-edge", "Sea", "Cave",
                "Mountain", "Rough-terrain", "Urban", "Rare"
        };

        String[] bodyShape = new String[]{
                "Quadruped", "Wings", "Squiggle", "Upright"
        };

        String[] pokemonFigures = new String[]{
                "Bulbasaur", "Charmander", "Squirtle", "Caterpie", "Weedle",
                "Pidgey", "Rattata", "Spearow", "Ekans", "Sandshrew",
                "Nidoran-F", "Nidoran-M", "Vulpix", "Zubat"};

        String[] questions = new String[] {
                "What color is this Pokemon?",
                        "What habitat does this Pokemon live in?",
                        "What shape is this Pokemon?",
                        "What is one ability this Pokemon has?",
                        "Which Pokemon has this ability?",
                        "What does this power do?",
                        "What power does this describe?" };

        question = (TextView) findViewById(R.id.textView_question);
        optA = (Button) findViewById(R.id.button_opt_A);
        optB = (Button) findViewById(R.id.button_opt_B);
        optC = (Button) findViewById(R.id.button_opt_C);
        optD = (Button) findViewById(R.id.button_opt_D);

        qIndex = (int) (Math.random()*6);
        question.setText(questions[qIndex]);

        //TODO: link the question to the answer bank it chooses from
        //if statements?

        if (qIndex == 0) {
            optA.setText(color[(int) Math.random()*10]);
            optB.setText(color[(int) Math.random()*10]);
            optC.setText(color[(int) Math.random()*10]);
            optD.setText(color[(int) Math.random()*10]);
            //TODO: how to update the indices idc
        }
        if (qIndex == 1) {
            optA.setText(habitat[(int) Math.random()*9]);
            optB.setText(habitat[(int) Math.random()*9]);
            optC.setText(habitat[(int) Math.random()*9]);
            optD.setText(habitat[(int) Math.random()*9]);
        }


    }

    private class GetPokeData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                //Build a string just like you did with code in last project
                String jsonString = "";
                //Log the built string and try it out
//1
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;

                    //3 as long as there's more data, keep adding to our string
                    while((line = reader.readLine()) != null) {
                        jsonString += line;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    //TAG is supposed to tell you what class is causing the trouble
                    Log.e(TAG, "onCreate: it crashed because file not found");
                }
                Log.d(TAG, "onCreate: " + jsonString);
                //Create a JSON object from the string
                JSONObject jsonData = null;

                try {
                    jsonData = new JSONObject(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Dive into JSON and access just the name

                if(jsonData != null) {

                    Log.d(TAG, "onCreate: " + jsonData.optJSONArray("results")
                            .optJSONObject(0)
                            .optString("name")); }

                return jsonData.optJSONArray("results").optJSONObject(0).optString("name", "fail");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}


