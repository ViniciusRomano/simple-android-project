package br.edu.utfpr.alunos.romano.paraprova1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class FirstActivity extends AppCompatActivity {

    public static final String NAME    = "NOME";

    private EditText editTextname;
    private CheckBox checkBoxToast;

    public String nameOption = "";
    public Boolean toastOption = Boolean.FALSE;

    private Spinner spinnerRole;
    private Spinner spinnerChamp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] paths;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        paths = getResources().getStringArray(R.array.role_array);


        editTextname   = (EditText)   findViewById(R.id.editTextName);
        checkBoxToast  = (CheckBox) findViewById(R.id.checkBoxToast);

        // spinner

        spinnerRole = (Spinner) findViewById(R.id.spinnerRole);
        spinnerChamp = (Spinner) findViewById(R.id.spinnerChamp);

        // overriding an anonymous class
        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fillModelsByLabel(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // preferences
        readPreferences();
    }

    // ENVIO DE PARAMETROS PARA OUTRA PAGINA

    public void send(View view){

        if(checkBoxToast.isChecked()){
            String output;
            output = editTextname.getText().toString() + spinnerChamp.getSelectedItem().toString();
            //Toast.makeText(this, editTextname.getText().toString(), LENGTH_LONG).show();
            Toast.makeText(this, output,Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(NAME,
                editTextname.getText().toString());
        startActivityForResult(intent, SecondActivity.ASK);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data){
        super.onActivityResult(requestCode,
                resultCode,
                data);

        if (requestCode == SecondActivity.ASK &&
                resultCode  == Activity.RESULT_OK){

            Bundle bundle = data.getExtras();

            String fullName = bundle.getString(SecondActivity.FULL_NAME);

            Toast.makeText(this,
                    "Nome Completo: " + fullName,
                    Toast.LENGTH_SHORT).show();
        }

    }


    // MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.itemHelp:
                Intent intent = new Intent(this, InformationActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // PREFERENCIAS

    public void readPreferences(){
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.data_preference), Context.MODE_PRIVATE);

        nameOption = sharedPref.getString(getString(R.string.name_preference), nameOption);
        toastOption = sharedPref.getBoolean(getString(R.string.toast_preference), toastOption);

        editTextname.setText(nameOption);
        checkBoxToast.setChecked(toastOption);


    }

    public void savePreferences(String nameValue, boolean toastValue){
        SharedPreferences sharedPref =  getSharedPreferences(getString(R.string.data_preference), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(getString(R.string.name_preference), nameValue);
        editor.putBoolean(getString(R.string.toast_preference), toastValue);

        editor.commit();

        nameOption = nameValue;
        toastOption = toastValue;

    }

    @Override
    protected void onStop() {
        savePreferences(editTextname.getText().toString(),checkBoxToast.isChecked());
        super.onStop();
    }


    // SPINNER LOAD

    public void fillModelsByLabel(String label) {
        ArrayList<String> labelChamps = new ArrayList<String>();

        switch (label) {
            case "Mid":
                for (String s : getResources().getStringArray(R.array.mid_array)) {
                    labelChamps.add(s);
                }
                break;
            case "Jungle":
                for (String s : getResources().getStringArray(R.array.jungle_array)) {
                    labelChamps.add(s);
                }
                break;
            default:
                break;
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, labelChamps);

        spinnerChamp.setAdapter(adapter);
    }
}
