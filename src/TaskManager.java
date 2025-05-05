import java.util.*;

public class TaskManager {
    private int nextId = 1;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    private int generateId() {
        return nextId++;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void addTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void removeAllEpics() {
        for (Epic epic : epics.values()) {
            for (Subtask sub : epic.getSubtasks()) {
                subtasks.remove(sub.getId());
            }
        }
        epics.clear();
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public void addEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        epic.updateStatus();
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            Epic existing = epics.get(epic.getId());
            existing.setName(epic.getName());
            existing.setDescription(epic.getDescription());
            existing.updateStatus();
        }
    }

    public void removeEpicById(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Subtask sub : epic.getSubtasks()) {
                subtasks.remove(sub.getId());
            }
        }
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void removeAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            epic.updateStatus();
        }
        subtasks.clear();
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public void addSubtask(Subtask subtask) {
        Epic parent = subtask.getParentEpic();
        if (parent == null || !epics.containsKey(parent.getId())) {
            System.out.println("Нельзя добавить подзадачу без подходящего эпика.");
            return;
        }
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);
        parent.addSubtask(subtask);
        parent.updateStatus();
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            Subtask existing = subtasks.get(subtask.getId());
            existing.setName(subtask.getName());
            existing.setDescription(subtask.getDescription());
            existing.setStatus(subtask.getStatus());
            existing.getParentEpic().updateStatus();
        }
    }

    public void removeSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic parent = subtask.getParentEpic();
            parent.getSubtasks().remove(subtask);
            parent.updateStatus();
        }
    }

    public List<Subtask> getSubtasksOfEpic(int epicId) {
        Epic epic = epics.get(epicId);
       if (epic != null){
           return new ArrayList<>(epic.getSubtasks());
       } else {
           return new ArrayList<>();
       }
    }
}
