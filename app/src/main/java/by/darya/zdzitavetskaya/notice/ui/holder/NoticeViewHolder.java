package by.darya.zdzitavetskaya.notice.ui.holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.darya.zdzitavetskaya.notice.R;
import by.darya.zdzitavetskaya.notice.common.interfaces.Listener;
import by.darya.zdzitavetskaya.notice.model.view.NoticeViewModel;

public class NoticeViewHolder extends BaseViewHolder<NoticeViewModel> implements View.OnClickListener{

    private String id;
    private Listener listener;

    @BindView(R.id.tv_item_note_title)
    TextView tvItemNoteTitle;

    @BindView(R.id.tv_item_note_description)
    TextView tvItemNoteDescription;

    @BindView(R.id.tv_item_note_date)
    TextView tvItemNoteDate;

    @BindView(R.id.tv_item_note_deadline)
    TextView tvItemNoteDeadline;

    @BindView(R.id.cb_item_note_status)
    CheckBox cbItemNoteStatus;

    public NoticeViewHolder(Listener listener, View itemView) {
        super(itemView);
        this.listener = listener;
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
        if (noticeViewModel.isSolved()) {
            cbItemNoteStatus.setClickable(false);
        }
        cbItemNoteStatus.setChecked(noticeViewModel.isSolved());
    }

    @Override
    public void unbindViewHolder() {
        tvItemNoteTitle.setText(null);
        tvItemNoteDescription.setText(null);
        tvItemNoteDate.setText(null);
        tvItemNoteDeadline.setText(null);
    }

    @OnClick(R.id.cb_item_note_status)
    void addToSolved() {
        listener.addToSolved(id, getAdapterPosition());
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(id);
    }
}
