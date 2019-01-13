package by.darya.zdzitavetskaya.notice.ui.dialog;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.model.receiver.AlarmBroadcastReceiver;
import by.darya.zdzitavetskaya.notice.presentation.noticeDialogPresentation.presenter.NoticeDialogPresenter;

import static android.content.Context.ALARM_SERVICE;

public class NoticeDialog extends MvpAppCompatDialogFragment implements MvpView, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Unbinder unbinder;
    private String title;
    private String description;
    private Calendar calendar = Calendar.getInstance();
    private NoteModel note;

    private DatePickerFragment datePickerFragment;
    private TimePickerFragment timePickerFragment;

    @InjectPresenter
    NoticeDialogPresenter noticeDialogPresenter;

    @BindView(R.id.et_title)
    EditText etTitle;

    @BindView(R.id.et_description)
    EditText etDescription;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.btn_OK)
    Button btnOk;

    @BindView(R.id.btn_cancel)
    Button btnCancel;

    @BindView(R.id.btn_date)
    Button btnDate;

    @BindView(R.id.btn_time)
    Button btnTime;

    @BindView(R.id.ll_task_container)
    LinearLayout llTaskContainer;

    private int selectedItem = -1;

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        btnDate.setText(String.format("%s-%s-%s", String.valueOf(dayOfMonth), String.valueOf(month + 1), String.valueOf(year)));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        btnTime.setText(String.format("%s:%s", String.valueOf(hourOfDay), String.valueOf(minute)));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_custom, container, false);

        unbinder = ButterKnife.bind(this, view);

        setupSpinnerAdapter();

        return view;
    }

    @OnTextChanged(R.id.et_title)
    void onTitleChanged(CharSequence s) {
        title = String.valueOf(s);
    }

    @OnTextChanged(R.id.et_description)
    void onDescriptionChanged(CharSequence s) {
        description = String.valueOf(s);
    }

    @OnClick(R.id.btn_OK)
    void onOkClick() {

        if (TextUtils.isEmpty(title)) {
            etTitle.setError("Title is required");
            return;
        }

        if (description.length() < 6) {
            etDescription.setError("Description must be at least 6 symbols");
            return;
        }

        if (selectedItem == 1 && calendar.getTimeInMillis() > System.currentTimeMillis()) {
            note = new NoteModel(title, description, false, calendar.getTime());
            setupAlarm();
        } else {
            note = new NoteModel(title, description, false, null);
        }

        noticeDialogPresenter.addNoteInDatabase(note);

        unbinder.unbind();
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    void onCancelClick() {
        unbinder.unbind();
        dismiss();
    }

    @OnClick(R.id.btn_date)
    void onDateClick() {
        datePickerFragment = DatePickerFragment.newInstance(calendar);
        datePickerFragment.setListener(this);
        datePickerFragment.show(getFragmentManager(),"datePicker");
    }

    @OnClick(R.id.btn_time)
    void onTimeClick() {
        timePickerFragment = TimePickerFragment.newInstance(calendar);
        timePickerFragment.setListener(this);
        timePickerFragment.show(getFragmentManager(), "timePicker");
    }

    @OnItemSelected(R.id.spinner)
    void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedItem = position;
        if (selectedItem == 1) {
            llTaskContainer.setVisibility(View.VISIBLE);
        } else {
            llTaskContainer.setVisibility(View.GONE);
            calendar = Calendar.getInstance();
        }
    }

    private void setupAlarm() {
        final AlarmManager alarmManager = (AlarmManager) getActivity().getApplicationContext().getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(getContext(), AlarmBroadcastReceiver.class);

        intent.putExtra("task", note.getId());
        int millis = (int)(calendar.getTimeInMillis() - DateUtils.HOUR_IN_MILLIS);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), millis, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmManager != null) {
            int SDK_INT = Build.VERSION.SDK_INT;
            if (SDK_INT < Build.VERSION_CODES.KITKAT) {                alarmManager.set(AlarmManager.RTC_WAKEUP, millis, pendingIntent);
            } else if (SDK_INT < Build.VERSION_CODES.M) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, millis, pendingIntent);
            } else {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, millis, pendingIntent);
            }
        }
    }

    private void setupSpinnerAdapter() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_row, R.id.tv_spinner_item, getActivity().getResources().getStringArray(R.array.items)) {
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, null, parent);
                if (position == selectedItem) {
                    v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                } else {
                    v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                }
                return v;
            }
        };

        spinnerAdapter.setDropDownViewResource(R.layout.spinner_row_dropdown);
        spinner.setAdapter(spinnerAdapter);

        spinner.setSelection(0);
    }
}
