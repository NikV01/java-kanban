package tracker.controllers;

import org.junit.jupiter.api.Test;
import tracker.history.HistoryManager;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void shouldReturnInitializedTaskManager() {
        TaskManager tm = Managers.getDefault();
        assertNotNull(tm);
    }

    @Test
    void shouldReturnInitializedHistoryManager() {
        HistoryManager hm = Managers.getDefaultHistory();
        assertNotNull(hm);
    }
}