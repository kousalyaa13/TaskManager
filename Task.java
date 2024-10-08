import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private String description;
    private boolean isComplete;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isComplete = false;
    }

    // Getters and setters
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

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return (isComplete ? "[X] " : "[ ] ") + title + ": " + description;
    }
}
