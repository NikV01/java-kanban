package tracker.model;

public class Subtask extends Task {
    private final Epic parentEpic;

    public Subtask(int id, String name, String description, TaskStatus status, Epic parentEpic) {
        super(id, name, description, status);
        this.parentEpic = parentEpic;
        parentEpic.addSubtask(this);
    }


    public Epic getParentEpic() {
        return parentEpic;
    }

    @Override
    public void setStatus(TaskStatus status) {
        super.setStatus(status);
        parentEpic.updateStatus();
    }

    @Override
    public String toString() {
        return "tracker.model.Subtask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", parentEpicId=" + parentEpic.getId() +
                '}';
    }

}
