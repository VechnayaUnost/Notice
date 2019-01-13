package by.darya.zdzitavetskaya.notice.model;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NoteModel extends RealmObject {
    @PrimaryKey
    private String id;

    private String title;

    private String description;

    private boolean isSolved;

    private Date date;

    private Date dateDeadline;

    public NoteModel() {

    }

    public NoteModel(String title, String description, boolean isSolved, Date dateDeadline) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.isSolved = isSolved;
        date = new Date();
        this.dateDeadline = dateDeadline;
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

    public Date getDateDeadline() {
        return dateDeadline;
    }

    public void setDateDeadline(Date dateDeadline) {
        this.dateDeadline = dateDeadline;
    }
}
