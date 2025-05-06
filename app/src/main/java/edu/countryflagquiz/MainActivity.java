package edu.countryflagquiz;

import android.content.Intent;
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
                btnBegin.setEnabled(count > 0);//se a quantidade de caracteres for maior que 0 ele da true e libera o butão, se não ele tranca o butão de novo
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void start(View v){

        Intent i = new Intent(getApplicationContext(), Question1.class);

        startActivity(i);

    }

    public void exit(View v){
        finish();
    }

}