package br.edu.utfpr.alunos.romano.atividade2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private EditText name, age;
    private Spinner categories, subCategories;
    private RadioGroup radioGroup;
    private CheckBox checkBoxCar;
    private ConstraintLayout constraintLayout;
    private int option = Color.BLUE;

    public static final String NAME_FIELD = "name", AGE_FIELD = "age", CATEGORIES_FIELD = "categories",
            SUBCATEGORIES_FIELD = "subcategories", COURSES_FIELD = "courses", CAR_FIELD = "car";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextName);
        age = (EditText) findViewById(R.id.editTextAge);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        checkBoxCar = (CheckBox) findViewById(R.id.checkBoxCar);
        categories = (Spinner) findViewById(R.id.spinnerCat);
        subCategories = (Spinner) findViewById(R.id.spinnerSub);

        constraintLayout = (ConstraintLayout) findViewById(R.id.main_layout);
        readColorPreference();
        changeBackgroundColor();

        categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubCategories(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void clearFields(View view) {
        name.setText(null);
        age.setText(null);
        categories.setSelection(0);
        subCategories.setSelection(0);
        radioGroup.clearCheck();
        checkBoxCar.setChecked(false);
        Toast.makeText(this, "Os campos foram limpos!", Toast.LENGTH_SHORT).show();
    }
    public void selectedSubCategories(String category) {
        ArrayList<String> subcategories = new ArrayList<String>();

        switch (category) {
            case "Aluno":
                for (String s : getResources().getStringArray(R.array.student_array)) {
                    subcategories.add(s);
                }
                break;
            case "Professor":
                for (String s : getResources().getStringArray(R.array.professor_array)) {
                    subcategories.add(s);
                }
                break;
            case "Diretor":
                for (String s : getResources().getStringArray(R.array.director_array)) {
                    subcategories.add(s);
                }
                break;
            default:
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subcategories);
        subCategories.setAdapter(adapter);
    }

    public String getRadioGroupButtonSelectedText() {
        View rdButtonCourseView = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        int rdButtonCourseId = radioGroup.indexOfChild(rdButtonCourseView);
        RadioButton rdButtonSelected = (RadioButton) radioGroup.getChildAt(rdButtonCourseId);
        return (String) rdButtonSelected.getText();
    }

    public void submitData(View view) {

        Intent intent = new Intent(this, ShowActivity.class);
        intent.putExtra(NAME_FIELD, name.getText().toString());
        intent.putExtra(AGE_FIELD, age.getText().toString());
        intent.putExtra(CATEGORIES_FIELD, categories.getSelectedItem().toString());
        intent.putExtra(SUBCATEGORIES_FIELD, subCategories.getSelectedItem().toString());
        intent.putExtra(COURSES_FIELD, getRadioGroupButtonSelectedText());
        intent.putExtra(CAR_FIELD, checkBoxCar.isChecked());
        startActivity(intent);
    }

    private void changeBackgroundColor() {
        constraintLayout.setBackgroundColor(option);
    }

    private void readColorPreference() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.color_preferences), Context.MODE_PRIVATE);
        option = sharedPref.getInt(getString(R.string.color_background), option);
    }

    private void saveColorPreference(int newValue) {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.color_preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.color_background), newValue);
        editor.commit();
        option = newValue;
        changeBackgroundColor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch(option) {
            case Color.BLUE:
                menu.getItem(0).setChecked(true);
                return true;
            case Color.RED:
                menu.getItem(1).setChecked(true);
                return true;
            case Color.YELLOW:
                menu.getItem(2).setChecked(true);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch(item.getItemId()) {
            case R.id.redItem:
                saveColorPreference(Color.RED);
                return true;
            case R.id.greenItem:
                saveColorPreference(Color.GREEN);
                return true;
            case R.id.blueItem:
                saveColorPreference(Color.BLUE);
                return true;
            case R.id.helpItem:
                Toast.makeText(this, "HAAAAAAAA", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
