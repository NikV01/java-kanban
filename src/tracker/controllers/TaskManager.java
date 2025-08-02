package tracker.controllers;

import tracker.model.Epic;
import tracker.model.Subtask;
import tracker.model.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTasks();


    void removeAllTasks();

    Task getTaskById(int id);

    void addTask(Task task);

    void updateTask(Task task);

    void removeTaskById(int id);

    List<Epic> getAllEpics();

    void removeAllEpics();

    Epic getEpicById(int id);

    void addEpic(Epic epic);

    void updateEpic(Epic epic);

    void removeEpicById(int id);

    List<Subtask> getAllSubtasks();

    void removeAllSubtasks();

    Subtask getSubtaskById(int id);

    void addSubtask(Subtask subtask);

    void updateSubtask(Subtask subtask);

    void removeSubtaskById(int id);

    List<Subtask> getSubtasksOfEpic(int epicId);

    List<Task> getHistory();

}
