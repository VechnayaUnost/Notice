package by.darya.zdzitavetskaya.notice.ui.holder;

import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.darya.zdzitavetskaya.notice.MainActivity;
import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.model.view.NoticeViewModel;
import by.darya.zdzitavetskaya.notice.ui.dialog.NoticeDialog;

public class NoticeViewHolder extends BaseViewHolder<NoticeViewModel> implements View.OnClickListener{

    private String id;

    @Inject
    MainActivity mainActivity;

    @BindView(R.id.tv_item_note_title)
    TextView tvItemNoteTitle;

    @BindView(R.id.tv_item_note_description)
    TextView tvItemNoteDescription;

    @BindView(R.id.tv_item_note_date)
    TextView tvItemNoteDate;

    @BindView(R.id.tv_item_note_deadline)
    TextView tvItemNoteDeadline;

    public NoticeViewHolder(View itemView) {
        super(itemView);
        MainActivity.getMainActivityComponent().inject(this);
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(NoticeViewModel noticeViewModel) {
        id = noticeViewModel.getId();
        tvItemNoteTitle.setText(noticeViewModel.getTitle());
        tvItemNoteDescription.setText(noticeViewModel.getDescription());
        tvItemNoteDate.setText(noticeViewModel.getDate());
        tvItemNoteDeadline.setText(noticeViewModel.getDateDeadline());
    }

    @Override
    public void unbindViewHolder() {
        tvItemNoteTitle.setText(null);
        tvItemNoteDescription.setText(null);
        tvItemNoteDate.setText(null);
        tvItemNoteDeadline.setText(null);
    }

    @Override
    public void onClick(View v) {
        NoticeDialog noticeDialog = NoticeDialog.newInstance(id);
        noticeDialog.show(mainActivity.getSupportFragmentManager(), "dialog");
    }
}
