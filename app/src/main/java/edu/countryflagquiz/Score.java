package edu.countryflagquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Set;

public class Score extends AppCompatActivity {

    private TextView txtScore, txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.txtScore = findViewById(R.id.txtScore);
        this.txtName = findViewById(R.id.textName);

        populateScoreScreen();

    }

    private void populateScoreScreen(){

        SharedPreferences sharedPref = getSharedPreferences("game_progress", Context.MODE_PRIVATE);
        Set<String> currentPointsList = sharedPref.getStringSet("points", null);

        Integer quantCorrectAnswers = 0;

        if (currentPointsList != null){
            quantCorrectAnswers = (int) currentPointsList.stream().filter(point -> point.contains("correct")).count();
        }

        this.txtScore.setText(quantCorrectAnswers.toString());
        this.txtName.setText(sharedPref.getString("name", "N/A"));

    }

    public void restart(View view){
        //removendo todas as informações antes de sair
        SharedPreferences sharedPref = getSharedPreferences("game_progress",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", null);
        editor.putStringSet("points", null);
        editor.apply();

        //mudando de tela...
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);//resetando toda a cadeia de acrivities
        startActivity(i);
    }
}