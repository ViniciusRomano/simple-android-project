package br.edu.utfpr.alunos.romano.atividade2;

/**
 * Created by romano on 8/12/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    private TextView textCategories, textSubCategories, textName, textAge, textCourses, textHasCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        loadElViews();
        loadSentData();
    }

    /**
     * Load elements views
     */
    public void loadElViews() {
        textName = (TextView) findViewById(R.id.textViewShowName);
        textAge = (TextView) findViewById(R.id.textViewShowAge);
        textCourses = (TextView) findViewById(R.id.textViewShowCourse);
        textHasCar = (TextView) findViewById(R.id.textViewShowCar);
        textCategories = (TextView) findViewById(R.id.textViewShowCat);
        textSubCategories = (TextView) findViewById(R.id.textViewShowSubCat);
    }

    /**
     * Load sent data through intent bundle extras
     */
    public void loadSentData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        textName.setText(bundle.getString(MainActivity.NAME_FIELD));
        textAge.setText(bundle.getString(MainActivity.AGE_FIELD));
        textCourses.setText(bundle.getString(MainActivity.COURSES_FIELD));
        if(bundle.getBoolean(MainActivity.CAR_FIELD)) textHasCar.setText("Tem Carro");
        else textHasCar.setText("Não tem Carro");
        textCategories.setText(bundle.getString(MainActivity.CATEGORIES_FIELD));
        textSubCategories.setText(bundle.getString(MainActivity.SUBCATEGORIES_FIELD));
    }

    /**
     * Finish activity and return to the previous one
     */
    public void close(View view) {
        finish();
    }
}
