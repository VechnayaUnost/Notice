package by.darya.zdzitavetskaya.notice.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NoticeModel extends RealmObject {
    @PrimaryKey
    private String id;

    private String title;

    private String description;

    private boolean isSolved;

    private Date date;

    public NoticeModel() {

    }

    public NoticeModel(String title, String description, boolean isSolved) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.isSolved = isSolved;
        date = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        this.isSolved = solved;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
