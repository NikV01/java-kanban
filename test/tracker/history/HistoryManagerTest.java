package tracker.history;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import tracker.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {

    HistoryManager history;

    @BeforeEach
    void setup() {
        history = new InMemoryHistoryManager();
    }

    @Test
    void shouldStoreAndPreserveTaskVersions() {
        Task task = new Task(1, "orig", "desc", TaskStatus.NEW);
        history.add(task);
        task.setName("changed");
        history.add(task);

        List<Task> historyList = history.getHistory();

        assertEquals(2, historyList.size());
        assertEquals("changed", historyList.get(1).getName());
    }

    @Test
    void historyShouldNotExceedLimit() {
        for (int i = 0; i < 15; i++) {
            Task t = new Task(i, "Task" + i, "D", TaskStatus.NEW);
            history.add(t);
        }

        List<Task> h = history.getHistory();
        assertEquals(10, h.size());
        assertEquals("Task5", h.get(0).getName());
    }
}