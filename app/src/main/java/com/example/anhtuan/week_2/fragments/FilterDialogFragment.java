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

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterDialogFragment extends DialogFragment {

    @BindView(R.id.tv_old)
    TextView tvOld;
    @BindView(R.id.tv_new)
    TextView tvNew;

    public static FilterDialogFragment newIntFragment() {
        FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
        return filterDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentdialog, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        tvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        tvOld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setItems(listSort, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                ((FilterDialogFragment) getActivity()). do
//            }
//        });
//        return builder.create();
//    }

//
//    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            showDate(year, month + 1, dayOfMonth);
//        }
//    };
}
