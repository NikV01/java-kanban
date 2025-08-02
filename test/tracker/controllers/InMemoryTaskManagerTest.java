package tracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.model.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private TaskManager manager;

    @BeforeEach
    void setup() {
        manager = Managers.getDefault();
    }

    @Test
    void shouldAddAndRetrieveDifferentTaskTypesById() {
        Task t = new Task(0, "Прогуляться", "Выйти на улицу", TaskStatus.NEW);
        Epic e = new Epic(0, "Переезд", "Организовать переезд");
        Subtask s = new Subtask(0, "Упаковать вещи", "Разобрать и упаковать мебель", TaskStatus.NEW, e);

        manager.addTask(t);
        manager.addEpic(e);
        manager.addSubtask(s);

        assertEquals(t, manager.getTaskById(t.getId()));
        assertEquals(e, manager.getEpicById(e.getId()));
        assertEquals(s, manager.getSubtaskById(s.getId()));
    }

    @Test
    void idCollisionBetweenManualAndAutoShouldNotConflict() {
        Task t1 = new Task(999, "Manual", "desc", TaskStatus.NEW);
        manager.addTask(t1);
        Task t2 = new Task(0, "Auto", "desc", TaskStatus.NEW);
        manager.addTask(t2);

        assertNotEquals(t1.getId(), t2.getId());
    }

    @Test
    void taskShouldRemainUnchangedAfterAdd() {
        Task original = new Task(0, "Попить", "Выпить воды", TaskStatus.NEW);
        manager.addTask(original);
        Task stored = manager.getTaskById(original.getId());

        assertNotNull(stored);
        assertEquals(original.getName(), stored.getName());
        assertEquals(original.getDescription(), stored.getDescription());
        assertEquals(original.getStatus(), stored.getStatus());
    }
}
