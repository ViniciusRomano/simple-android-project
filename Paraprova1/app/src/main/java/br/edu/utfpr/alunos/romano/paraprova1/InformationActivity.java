package br.edu.utfpr.alunos.romano.paraprova1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    public void back(View view){
        finish();
    }
}
