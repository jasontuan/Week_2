package com.example.anhtuan.week_2.view;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.anhtuan.week_2.R;
import com.example.anhtuan.week_2.fragments.FilterDialogFragment;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity {

    @BindView(R.id.tv_beginDate)
    TextView tvBeginDate;
    @BindView(R.id.tv_sortOrder)
    TextView tvSortOrder;
    Calendar calendar;
    int year, month, day;

    private void showDate(int year, int month, int day) {
        tvBeginDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        tvSortOrder.setText("Newest");

        showDate(year, month + 1, day);

        tvBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tvSortOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FilterDialogFragment filterDialogFragment = FilterDialogFragment.newIntFragment();
                filterDialogFragment.show(fragmentManager, null);
            }
        });

    }

}
