package br.edu.utfpr.alunos.romano.paraprova1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static br.edu.utfpr.alunos.romano.paraprova1.R.id.editTextFullName;

public class SecondActivity extends AppCompatActivity {
    public static final String FULL_NAME = "NOME_COMPLETO";
    public static final int ASK = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        String  name   = bundle.getString(FirstActivity.NAME);

        EditText fullname = (EditText) findViewById(editTextFullName);

        fullname.setText(name);

    }

    public void back(View View){

        Intent intent = new Intent();

        EditText fullname = (EditText) findViewById(editTextFullName);

        intent.putExtra(FULL_NAME,fullname.getText().toString());

        setResult(Activity.RESULT_OK, intent);

        finish();
    }
}
