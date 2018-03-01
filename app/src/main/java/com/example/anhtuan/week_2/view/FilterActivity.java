package com.example.anhtuan.week_2.view;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.anhtuan.week_2.R;
import com.example.anhtuan.week_2.fragments.DatePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {

    @BindView(R.id.spn_sortOrder)
    Spinner spnSortOrder;
    @BindView(R.id.tv_beginDate)
    TextView tvBeginDate;

    Calendar calendar;

    List<String> sortList = new ArrayList<>();
    int year, month, day;

    private void showDate(int year, int month, int day) {
        tvBeginDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        sortList.add("Newest");
        sortList.add("Oldest");

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year, month + 1, day);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_items, sortList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnSortOrder.setAdapter(arrayAdapter);
        spnSortOrder.setOnItemSelectedListener(new SpinnerOnItemSelected());

        tvBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
               dialogFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });
    }

    private class SpinnerOnItemSelected implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
