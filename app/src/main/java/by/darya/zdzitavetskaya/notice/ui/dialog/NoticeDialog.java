package by.darya.zdzitavetskaya.notice.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.common.interfaces.UpdateListener;
import by.darya.zdzitavetskaya.notice.model.NoticeModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter.CurrentNoticePresenter;

public class NoticeDialog {

    private final Context context;
    private UpdateListener updateListener;
    private Dialog dialog;
    private Unbinder unbinder;
    private String title;
    private String description;

    private CurrentNoticePresenter currentNoticePresenter;

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

    @BindView(R.id.ll_task_container)
    LinearLayout llTaskContainer;

    private int selectedItem = -1;

    public NoticeDialog(Context context, UpdateListener updateListener) {
        this.context = context;
        this.updateListener = updateListener;
        currentNoticePresenter = new CurrentNoticePresenter();
    }

    @OnTextChanged(R.id.et_title)
    void onTitleChanged(CharSequence s, int start, int before, int count) {
        title = String.valueOf(s);
    }

    @OnTextChanged(R.id.et_description)
    void onDescriptionChanged(CharSequence s, int start, int before, int count) {
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

        NoticeModel note = new NoticeModel(title, description, false);

        currentNoticePresenter.addNoteInDatabase(note);
        updateListener.update(title, description);
        unbinder.unbind();
        currentNoticePresenter = null;
        dialog.dismiss();
    }

    @OnClick(R.id.btn_cancel)
    void onCancelClick() {
        unbinder.unbind();
        currentNoticePresenter = null;
        dialog.dismiss();
    }

    @OnItemSelected(R.id.spinner)
    void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedItem = position;
        if (selectedItem == 1) {
            llTaskContainer.setVisibility(View.VISIBLE);
        } else {
            llTaskContainer.setVisibility(View.GONE);
        }
    }

    public void showDialog() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);

        unbinder = ButterKnife.bind(this, dialog);

        setupSpinnerAdapter();

        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void setupSpinnerAdapter() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context,
                R.layout.spinner_row, R.id.tv_spinner_item, context.getResources().getStringArray(R.array.items)) {
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
