package com.doit.can.you.symptomcheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.doit.can.you.symptomcheck.models.Diagnosis;
import com.doit.can.you.symptomcheck.models.Symptom;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class DiagnosesAdapter extends ArrayAdapter<Diagnosis> {

    private  final Context mContext;
    private final List<Diagnosis> mDiagnoses;

    public DiagnosesAdapter(Context context, List<Diagnosis> diagnoses) {
        super(context, R.layout.diagnosis_row, diagnoses);
        this.mContext = context;
        this.mDiagnoses = diagnoses;
    }

    @Override
    public int getCount() {
        return mDiagnoses.size();
    }

    @Override
    public Diagnosis getItem(int i) {
        return mDiagnoses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Diagnosis diagnosis = mDiagnoses.get(position);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.diagnosis_row, viewGroup, false);
        TextView tvCountyName = (TextView) rowView.findViewById(R.id.tvDiagnosisName);
        tvCountyName.setText(diagnosis.diagName);

        TextView tvDistance = (TextView) rowView.findViewById(R.id.tvSymptoms);
        String sym = "";
        for(String s: diagnosis.symptomsMatched) {
            if (sym != "") {
                sym += ", ";
            }
            String val = MainActivity.SYMPTOMS_REV.get(s);
            if (val == null || val == "") {
                val = s;
            }
            sym += val;
        }
        tvDistance.setText(sym);

        return rowView;
    };
}