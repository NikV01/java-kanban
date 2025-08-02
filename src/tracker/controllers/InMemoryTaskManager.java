package tracker.controllers;

import java.util.*;

import tracker.history.HistoryManager;
import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;
public class InMemoryTaskManager implements TaskManager {
    private final HistoryManager historyManager;
    private int nextId = 1;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private final LinkedList<Task> history = new LinkedList<>();

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }


    private int generateId() {
        return nextId++;
    }

    private void addToHistory(Task task) {
        if (task == null) return;
        history.addLast(task);
        if (history.size() > 10) {
            history.removeFirst();
        }
    }


    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public void addTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void removeAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        epic.updateStatus();
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            Epic existing = epics.get(epic.getId());
            existing.setName(epic.getName());
            existing.setDescription(epic.getDescription());
            existing.updateStatus();
        }
    }

    @Override
    public void removeEpicById(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Subtask sub : epic.getSubtasks()) {
                subtasks.remove(sub.getId());
            }
        }
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void removeAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            epic.updateStatus();
        }
        subtasks.clear();
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public void addSubtask(Subtask subtask) {
        Epic parent = subtask.getParentEpic();
        if (parent == null || !epics.containsKey(parent.getId())) {
            return;
        }
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);
        parent.addSubtask(subtask);
        parent.updateStatus();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            Subtask existing = subtasks.get(subtask.getId());
            existing.setName(subtask.getName());
            existing.setDescription(subtask.getDescription());
            existing.setStatus(subtask.getStatus());
            existing.getParentEpic().updateStatus();
        }
    }

    @Override
    public void removeSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic parent = subtask.getParentEpic();
            parent.getSubtasks().remove(subtask);
            parent.updateStatus();
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public List<Subtask> getSubtasksOfEpic(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null){
            return new ArrayList<>(epic.getSubtasks());
        } else {
            return new ArrayList<>();
        }
    }
}
