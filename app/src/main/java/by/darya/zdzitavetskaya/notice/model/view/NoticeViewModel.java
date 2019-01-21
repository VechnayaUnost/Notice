package by.darya.zdzitavetskaya.notice.model.view;

import android.view.View;

import by.darya.zdzitavetskaya.notice.common.utility.Utility;
import by.darya.zdzitavetskaya.notice.model.NoteModel;
import by.darya.zdzitavetskaya.notice.ui.holder.BaseViewHolder;
import by.darya.zdzitavetskaya.notice.ui.holder.NoticeViewHolder;

public class NoticeViewModel extends BaseViewModel {

    private final String id;
    private final String title;
    private final String description;
    private final String date;
    private final String dateDeadline;

    public NoticeViewModel(NoteModel note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.description = note.getDescription();
        this.date = Utility.getFormatDate(note.getDate());
        this.dateDeadline = Utility.getFormatDate(note.getDateDeadline());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.CurrentNotice;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NoticeViewHolder(view);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getDateDeadline() {
        return dateDeadline;
    }
}
