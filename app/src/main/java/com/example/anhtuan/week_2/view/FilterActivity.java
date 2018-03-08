package com.example.anhtuan.week_2.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhtuan.week_2.R;
import com.example.anhtuan.week_2.adapter.RecyclerViewFilterAdapter;
import com.example.anhtuan.week_2.contract.IArticle;
import com.example.anhtuan.week_2.fragments.DatePickerFragment;
import com.example.anhtuan.week_2.presenter.PresenterFilterImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity implements IArticle.IView {

    RecyclerViewFilterAdapter recyclerViewFilterAdapter;
    PresenterFilterImpl presenterFilter;
    GridLayoutManager gridLayoutManager;
    @BindView(R.id.rcv_filter)
    RecyclerView rcvFilter;
    @BindView(R.id.spn_sortOrder)
    Spinner spnSortOrder;
    @BindView(R.id.tv_beginDate)
    TextView tvBeginDate;
    int intSortOrder, curPosition;
    String topic;
    Calendar calendar;
    SharedPreferences sharedPreferences;
    List<String> sortList = new ArrayList<>();
    int year, month, day;

    private void showDate(int year, int month, int day) {
        if (month < 10 || day < 10) {
            tvBeginDate.setText(new StringBuilder().append(year).append(0).append(month).append(0).append(day));
        } else {
            tvBeginDate.setText(new StringBuilder().append(year).append(month).append(day));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
        onCreateSharePreferences();

        onCreateToolBar();
        onCreateSpinner();

        eventDatePickerFragment();
        onRecyclerViewFilter();

        onReadSharePreferences();
    }

    private void eventDatePickerFragment() {
        tvBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });
    }

    private void onCreateSharePreferences() {
        sharedPreferences = getSharedPreferences("dataShare", Context.MODE_PRIVATE);
    }

    @SuppressLint("ApplySharedPref")
    private void onWriteSharePreferences() {
        onCreateSharePreferences();
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("BEGIN_DATE", String.valueOf(tvBeginDate.getText()));
        editor.putInt("SORT_ORDER", intSortOrder);
        editor.putString("SORT", sortList.get(intSortOrder));
        editor.putInt("POSITION_DESK", curPosition);
        editor.putString("TOPIC", topic);
        editor.commit();
    }

    private void onReadSharePreferences() {
        onCreateSharePreferences();
        tvBeginDate.setText(sharedPreferences.getString("BEGIN_DATE", ""));
        if (tvBeginDate.getText().equals("")) {
            onCreateCalendar();
            showDate(year, month + 1, day);
        }
        spnSortOrder.post(new Runnable() {
            @Override
            public void run() {
                spnSortOrder.setSelection(sharedPreferences.getInt("SORT_ORDER", 0));
            }
        });
        recyclerViewFilterAdapter.setCurPos(sharedPreferences.getInt("POSITION_DESK", -1));
        topic = sharedPreferences.getString("TOPIC", "");
    }

    private void onRecyclerViewFilter() {
        presenterFilter = new PresenterFilterImpl(this);
        recyclerViewFilterAdapter = new RecyclerViewFilterAdapter(this, presenterFilter.getImageFilterList());
        gridLayoutManager = new GridLayoutManager(this, 2);
        rcvFilter.setLayoutManager(gridLayoutManager);
        rcvFilter.setAdapter(recyclerViewFilterAdapter);
        presenterFilter.getDataFilter();
        recyclerViewFilterAdapter.setOnItemClickListener(new IArticle.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, boolean selected) {
                if (selected) {
                    curPosition = position;
                    topic = presenterFilter.getImageFilterList().get(position).getImageName();
                }
            }
        });
    }

    private void onCreateCalendar() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private void onCreateSpinner() {
        sortList.add("newest");
        sortList.add("oldest");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_items, sortList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnSortOrder.setAdapter(arrayAdapter);
        spnSortOrder.setOnItemSelectedListener(new SpinnerOnItemSelected());
    }

    private void onCreateToolBar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_icon_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_save:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        onWriteSharePreferences();
        Intent intent = new Intent(FilterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private class SpinnerOnItemSelected implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    intSortOrder = position;
                    break;
                case 1:
                    intSortOrder = position;
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public void showDataSuccess() {
        recyclerViewFilterAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataFail() {
        Toast.makeText(this, "show fail", Toast.LENGTH_SHORT).show();
    }

}
