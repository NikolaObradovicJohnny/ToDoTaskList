package eu.execom.todolistgrouptwo.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * Model representing a task.
 */
public class Task {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private String title;

    @DatabaseField(canBeNull = false)
    private String description;

//    @DatabaseField(columnName = "user", canBeNull = false, foreign = true)
//    private User user;

    @DatabaseField(canBeNull = false,dataType = DataType.BOOLEAN)
    private boolean finished;

    public Task() {
    }

    public Task(String title, String description) {
//        this.user = user;
        this.title = title;
        this.description = description;
    }

    public Task(String title, String description, boolean finished) {
        this.title = title;
        this.description = description;
        this.finished = finished;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
