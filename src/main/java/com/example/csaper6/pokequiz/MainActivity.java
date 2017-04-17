package com.example.csaper6.pokequiz;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private String[] color;
    private String[] habitat;
    private String[] bodyShape;
    private String[] pokemonFigures;
    private String[] powers;
    private String[] questions;
    private String[] answerChoices = new String[4];


    private TextView questionTextView;
    private Button optA, optB, optC, optD, next;
    private String correctAnswerUrl, correctAnswer, pokemon;
    public static final String TAG = MainActivity.class.getSimpleName();

    private int questionIndex, speciesIndex, value;

    Animation anim1, anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        questionIndex = 0;
        speciesIndex = 0;
        setArrays();
        generateRandomAnswers();
        fillArrayWithUrls();


        setAnswerChoices();

        questionTextView.setText("What color is this Pokemon?");


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAnswerChoices();
                generateRandomAnswers();
            }
        });

        optA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (optA.getText() == correctAnswer) {
                    Toast.makeText(MainActivity.this, "You're correct!", Toast.LENGTH_SHORT).show();
                    optA.setBackgroundColor(Color.GREEN);
                    next.startAnimation(anim2);
                } else {
                    Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                    optA.setBackgroundColor(Color.RED);
                }
            }

        });

        optB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optB.getText() == correctAnswer) {
                    Toast.makeText(MainActivity.this, "You're correct!", Toast.LENGTH_SHORT).show();
                    optB.setBackgroundColor(Color.GREEN);
                    next.startAnimation(anim2);
                } else {
                    Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                    optB.setBackgroundColor(Color.RED);
                }

            }
        });
        optC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optC.getText() == correctAnswer) {
                    Toast.makeText(MainActivity.this, "You're correct!", Toast.LENGTH_SHORT).show();
                    optC.setBackgroundColor(Color.GREEN);
                    next.startAnimation(anim2);
                } else {
                    Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                    optC.setBackgroundColor(Color.RED);
                }

            }
        });
        optD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optD.getText() == correctAnswer) {
                    Toast.makeText(MainActivity.this, "You're correct!", Toast.LENGTH_SHORT).show();
                    optD.setBackgroundColor(Color.GREEN);
                    next.startAnimation(anim2);
                } else {
                    Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                    optD.setBackgroundColor(Color.RED);
                }

            }
        });
    }


    private void setArrays() {
        color = new String[]{
                "Black", "White", "Red", "Orange", "Yellow", "Green",
                "Blue", "Indigo", "Violet", "Gray"
        };

        habitat = new String[]{
                "Grassland", "Forest", "Water's-edge", "Sea", "Cave",
                "Mountain", "Rough-terrain", "Urban", "Rare"
        };

        bodyShape = new String[]{
                "Quadruped", "Wings", "Squiggle", "Upright"
        };

        pokemonFigures = new String[]{
                "Bulbasaur", "Charmander", "Squirtle", "Caterpie", "Weedle",
                "Pidgey", "Rattata", "Spearow", "Ekans", "Sandshrew",
                "Nidoran-F", "Nidoran-M", "Vulpix", "Zubat"};

        powers = new String[]{
                "Stench", "Fire",
        };

        questions = new String[]{
                "What color is ",
                "What habitat does this Pokemon live in?",
                "What shape is this Pokemon?",
                "What is one ability this Pokemon has?",
                "Which Pokemon is this color?",
                "Which Pokemon has this ability?",
                "What does this power do?",
                "What power does this describe?"};
    }


    private void setAnswerChoices() {
        optA.setText(answerChoices[0]);
        optB.setText(answerChoices[1]);
        optC.setText(answerChoices[2]);
        optD.setText(answerChoices[3]);


    }

    private void wireWidgets() {
        questionTextView = (TextView) findViewById(R.id.textView_question);
        optA = (Button) findViewById(R.id.button_opt_A);
        optB = (Button) findViewById(R.id.button_opt_B);
        optC = (Button) findViewById(R.id.button_opt_C);
        optD = (Button) findViewById(R.id.button_opt_D);
        next = (Button) findViewById(R.id.button_next);
        anim1 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.rotate);
    }


    private void fillArrayWithUrls() {
        switch (questionIndex) {
            case 0:
                value = (int) (Math.random() * 4);
                String baseUrl = "";
                if (value == 1) {
                    baseUrl = "https://pokeapi.co/api/v2/generation/1/";

                }
                if (value == 2) {
                    baseUrl = "https://pokeapi.co/api/v2/generation/2/";

                }
                if (value == 3) {
                    baseUrl = "https://pokeapi.co/api/v2/generation/3/";

                }
                if (value == 4) {
                    baseUrl = "https://pokeapi.co/api/v2/generation/4/";

                }
                new SwPersonSearch().execute(baseUrl);
                break;
            case 1:
                baseUrl = "https://pokeapi.co/api/v2/generation/2/";
                new SwPersonSearch().execute(baseUrl);
                break;
        }
    }


    private void generateRandomAnswers() {
        switch (questionIndex) {
            case 0:
                for (int i = 0; i < 4; i++) {
                    int x = (int) (Math.random() * color.length);
                    Log.d(x + "", "FAIL");
                    answerChoices[i] = color[x];
                    questionIndex++;
                }
                break;
            case 1:
                for (int i = 0; i < 4; i++) {
                    int x = (int) (Math.random() * habitat.length);
                    answerChoices[i] = habitat[x];
                    questionIndex++;
                }
                break;
            case 2:
                for (int i = 0; i < 4; i++) {
                    int x = (int) (Math.random() * bodyShape.length);
                    answerChoices[i] = bodyShape[x];
                    questionIndex++;
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    int x = (int) (Math.random() * powers.length);
                    answerChoices[i] = powers[x];
                    questionIndex++;
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    int x = (int) (Math.random() * pokemonFigures.length + 1);
                    answerChoices[i] = pokemonFigures[x];
                    questionIndex++;
                }
                break;
            default:
                for (int i = 0; i < 4; i++) {
                    int x = (int) (Math.random() * color.length + 1);
                    answerChoices[i] = color[x];
                    questionIndex++;
                }
                break;
        }
    }

    private class SwPersonSearch extends AsyncTask<String, Void, String> {
        String jsonString = "";

        @Override
        protected String doInBackground(String... urls) {
            //make a new URL object
            try {
                URL url = new URL(urls[0]);

                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                //build a string just like you did in the code from "Parsing Local JSON"
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonString += line;

                    //return "Something";
                }

                //Log the built string and try it out

            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONObject jsonData = null;
            try {
                jsonData = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (jsonData != null) {
                speciesIndex = (int) Math.random() * jsonData.optJSONArray("pokemon_species").length() + 1;
                correctAnswerUrl = jsonData.optJSONArray("pokemon_species").optJSONObject(speciesIndex).optString("url");

                Log.d(TAG, "onCreate: " + jsonData.optJSONArray("results").optJSONObject(0).optString("name", "FAIL"));
                pokemon = jsonData.optJSONArray("pokemon_species").optJSONObject(speciesIndex).optString("name", "FAIL");
                setCorrectAnswer(correctAnswerUrl);
            }


            return pokemon;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, s);
            if (s != null) {
                String x = questions[questionIndex] + s + "?";
                questionTextView.setText(x);
            }
        }
    }


    private class SwPersonSearch1 extends AsyncTask<String, Void, String> {
        String jsonString = "";

        @Override
        protected String doInBackground(String... urls) {
            //make a new URL object
            try {
                URL url = new URL(urls[0]);

                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                //build a string just like you did in the code from "Parsing Local JSON"
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonString += line;

                    //return "Something";
                }

                //Log the built string and try it out

            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONObject jsonData = null;
            try {
                jsonData = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (jsonData != null) {
                correctAnswer = jsonData.optJSONObject("color").optString("name");
            }
            return correctAnswer;
        }
    }

    private void setCorrectAnswer(String correctAnswerUrl) {
        new SwPersonSearch1().execute(correctAnswerUrl);
        int answerIndex = (int) Math.random() * 4;
        if (answerIndex == 0) {
            optA.setText(correctAnswer);
        }
        if (answerIndex == 1) {
            optB.setText(correctAnswer);
        }
        if (answerIndex == 2) {
            optC.setText(correctAnswer);
        }
        if (answerIndex == 3) {
            optD.setText(correctAnswer);
        }

    }

}
