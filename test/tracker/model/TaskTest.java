package tracker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TaskTest {

    @Test
    void tasksWithSameIdShouldBeEqual() {
        Task task1 = new Task(0, "Прогуляться", "Выйти на улицу", TaskStatus.NEW);
        Task task2 = new Task(0, "Попить", "Выпить воды", TaskStatus.NEW);
        assertEquals(task1, task2);
    }
    @Test
    void epicCannotContainItselfAsSubtask() {
        Epic epic1 = new Epic(0, "Переезд ", "Организовать переезд");
        Subtask subtask1 = new Subtask(0, "Упаковать вещи", "Разобрать и упаковать мебель", TaskStatus.NEW, epic1);
        assertNotSame(epic1, subtask1);
        assertTrue(epic1.getSubtasks().contains(subtask1));
        assertNotEquals(epic1, subtask1);
    }
}