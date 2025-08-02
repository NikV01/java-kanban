package tracker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @Test
    void subtasksWithSameIdShouldBeEqual() {
        Epic epic1 = new Epic(0, "Переезд ", "Организовать переезд");
        Subtask subtask1 = new Subtask(0, "Упаковать вещи", "Разобрать и упаковать мебель", TaskStatus.NEW, epic1);
        Subtask subtask2 = new Subtask(0, "Заказать транспорт", "Нанять машину для перевозки", TaskStatus.NEW, epic1);

        assertEquals(subtask1, subtask2);
    }

    @Test
    void subtaskCannotBeItsOwnEpic() {
        Epic epic1 = new Epic(0, "Переезд ", "Организовать переезд");
        Subtask subtask1 = new Subtask(0, "Упаковать вещи", "Разобрать и упаковать мебель", TaskStatus.NEW, epic1);
        assertNotEquals(epic1, subtask1);
        assertSame(epic1, subtask1.getParentEpic());
        assertNotEquals(subtask1, subtask1.getParentEpic());
    }
}