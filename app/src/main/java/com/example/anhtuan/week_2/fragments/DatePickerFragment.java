package com.example.anhtuan.week_2.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.anhtuan.week_2.R;

import java.util.Arrays;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    Calendar calendar;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView tvBeginDate = (TextView) getActivity().findViewById(R.id.tv_beginDate);
        tvBeginDate.setText(new StringBuilder().append(dayOfMonth).append("/").append(month).append("/").append(year));
    }
}
