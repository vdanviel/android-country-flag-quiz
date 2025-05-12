package edu.countryflagquiz.quiz.questions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Set;

import edu.countryflagquiz.MainActivity;
import edu.countryflagquiz.R;

public class Question7 extends AppCompatActivity {

    private RadioButton correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_question7);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.correctAnswer = findViewById(R.id.correct);

    }

    public void answer(View view){

        if (correctAnswer.isChecked()) {

            //ADICIONANDO INFORMAÇÕES AO PLACAR
            SharedPreferences sharedPref = getSharedPreferences("game_progress", Context.MODE_PRIVATE);

            //pegando a lista de pontos atuais...
            Set<String> currentPointsList = sharedPref.getStringSet("points", null);

            currentPointsList.add("7. correct");//adiciona dizendo que a a primeira pergunta foi correta...

            //modificando a lista...
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putStringSet("points", currentPointsList);

            editor.apply();//aplica informações

            //MUDANDO DE TELA
            Intent i = new Intent(getApplicationContext(), Question8.class);

            startActivity(i);

        } else {

            //ADICIONANDO INFORMAÇÕES AO PLACAR
            SharedPreferences sharedPref = getSharedPreferences("game_progress", Context.MODE_PRIVATE);

            //pegando a lista de pontos atuais...
            Set<String> currentPointsList = sharedPref.getStringSet("points", null);

            currentPointsList.add("7. wrong");//adiciona dizendo que a a primeira pergunta foi correta...

            //modificando a lista...
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putStringSet("points", currentPointsList);

            editor.apply();//aplica informações

            //MUDANDO DE TELA
            Intent i = new Intent(getApplicationContext(), Question8.class);

            startActivity(i);

        }

    }

    public void giveUp(View v){

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