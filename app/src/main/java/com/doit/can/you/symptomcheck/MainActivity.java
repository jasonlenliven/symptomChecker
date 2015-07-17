package com.doit.can.you.symptomcheck;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.doit.can.you.symptomcheck.models.Diagnosis;
import com.doit.can.you.symptomcheck.models.SearchCriteria;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mSearchButton;
    private Button mAddSymptomButton;
    ArrayList<AutoCompleteTextView> symptomsText;
    ListView mDiagnosesLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        loadControls();
    }

    private void loadControls() {
        loadAgeGroups();


        this.symptomsText = new ArrayList<AutoCompleteTextView>();
        //this.symptomsText.add((EditText) this.findViewById(R.id.textView));


        mAddSymptomButton = (Button) findViewById(R.id.add_sympton_button);
        mAddSymptomButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //etPhoneNumber.add(new EditText(ContactEdit.this));
                try {
                    LinearLayout layout = (LinearLayout) findViewById(R.id.symptomLayout);
                    AutoCompleteTextView temp = generateTextView();

                    symptomsText.add(temp);

                    layout.addView(temp);
                } catch (Exception e) {
                    Log.d("None", "Failed to create new edit text");
                }
            }
        });

        mSearchButton = (Button) findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DiagnosesAdapter adapter = new DiagnosesAdapter(MainActivity.this, getSampleDiagnoses());
                mDiagnosesLv.setAdapter(adapter);
                search();
            }
        });

        mDiagnosesLv = (ListView) findViewById(R.id.listView);
        mDiagnosesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent resultActivity = new Intent(MainActivity.this, ResultsActivity.class);
                startActivity(resultActivity);
            }
        });

        loadTextView();
    }

    private void loadAgeGroups() {
        Spinner spinner = (Spinner) findViewById(R.id.age_groups_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age_group_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void search() {
        Spinner mySpinner = (Spinner)findViewById(R.id.age_groups_spinner);
        String text = mySpinner.getSelectedItem().toString();
        String ageGroup = AgeGroupCode.getCode(text);

        RadioGroup radioButtonGroup = (RadioGroup) findViewById(R.id.gender_radios);
        int radioButtonID = (radioButtonGroup).getCheckedRadioButtonId();
        View radioButton = radioButtonGroup.findViewById(radioButtonID);
        int idx = radioButtonGroup.indexOfChild(radioButton);
        String gender = "M";
        if (idx == 1) {
            gender = "F";
        }

        ArrayList<String> symptoms = new ArrayList();
        for(AutoCompleteTextView ev: symptomsText) {
            symptoms.add(ev.getText().toString());
        }

        SearchCriteria sc = new SearchCriteria();
        sc.setAgeGroup(ageGroup);
        sc.setGender(gender);
        sc.setSymptoms(symptoms);

        Gson gson = new Gson();
        String json = gson.toJson(sc);

        Log.i(TAG, json);

        //TODO hit search service
    }

    private static final String[] SAMPLE_SYMPTOMS = new String[] {
            "rash", "nausea", "abdominal pain", "vomiting", "ankle pain", "anxiety"
    };

    private void loadTextView() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.symptomLayout);

        for (int i=1; i<=5;i++) {
            AutoCompleteTextView temp = generateTextView();
            symptomsText.add(temp);

            layout.addView(temp);
        }
    }

    private AutoCompleteTextView generateTextView(){

        // TODO get symptoms list 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SAMPLE_SYMPTOMS);
        AutoCompleteTextView temp = new AutoCompleteTextView(MainActivity.this);
        temp.setHint("symptom");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ActionBar.LayoutParams.FILL_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT, 1);
        temp.setLayoutParams(params);

        temp.setAdapter(adapter);

        return temp;
    }

    private ArrayList<Diagnosis> getSampleDiagnoses() {
        ArrayList<Diagnosis> d = new ArrayList<Diagnosis>();
        d.add(new Diagnosis("miliaria", "Rash, fever", "https://moreinfo.here"));
        d.add(new Diagnosis("Sample Diagnosis 2", "Rash, fever", "https://moreinfo.here"));
        d.add(new Diagnosis("Sample Diagnosis 3", "Rash, fever", "https://moreinfo.here"));
        d.add(new Diagnosis("Sample Diagnosis 4", "Rash, fever", "https://moreinfo.here"));
        d.add(new Diagnosis("Sample Diagnosis 5", "Rash, fever", "https://moreinfo.here"));

        return d;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent resultActivity = new Intent(MainActivity.this, MainActivity.class);
            startActivity(resultActivity);
        }

        return super.onOptionsItemSelected(item);
    }
}
