package by.darya.zdzitavetskaya.notice.ui.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    DatePickerDialog.OnDateSetListener onDateSetListener;

    private static final String ARG_DATE = "date";

    public DatePickerFragment() {
        // Required empty public constructor
    }

    public void setListener(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }

    public static DatePickerFragment newInstance(Calendar date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(args);

        return datePickerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar date = (Calendar) getArguments().getSerializable(ARG_DATE);

        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);
    }
}
