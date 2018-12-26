package by.darya.zdzitavetskaya.notice.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.common.interfaces.UpdateListener;
import by.darya.zdzitavetskaya.notice.model.NoticeModel;
import by.darya.zdzitavetskaya.notice.presentation.currentNoticePresentation.presenter.CurrentNoticePresenter;

public class NoticeDialog {

    private Context context;
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

    @BindView(R.id.btn_OK)
    Button btnOk;

    @BindView(R.id.btn_cancel)
    Button btnCancel;

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
        dialog.dismiss();
    }

    @OnClick(R.id.btn_cancel)
    void onCancelClick() {
        unbinder.unbind();
        dialog.dismiss();
    }

    public void showDialog() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);

        unbinder = ButterKnife.bind(this, dialog);

        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.show();
    }
}
