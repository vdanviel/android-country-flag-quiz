package edu.countryflagquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.countryflagquiz.quiz.questions.Question1;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private Button btnBegin, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ///////////////

        this.edtName = findViewById(R.id.edtName);
        this.btnBegin = findViewById(R.id.btnStart);
        this.btnExit = findViewById(R.id.btnExit);

        ///////////////

        this.edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean enableButton = s.toString().trim().length() > 0;
                btnBegin.setEnabled(enableButton);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //////

    }

    public void start(View view){

        //adicionando o nome do usuario
        SharedPreferences sharedPref = getSharedPreferences("game_progress",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", this.edtName.getText().toString());
        editor.apply();

        //mudando de tela...
        Intent i = new Intent(getApplicationContext(), Question1.class);

        startActivity(i);
        
    }

    public void exit(View v){

        //removendo todas as informações antes de sair
        SharedPreferences sharedPref = getSharedPreferences("game_progress",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", null);
        editor.putStringSet("points", null);
        editor.apply();

        finish();
    }

}