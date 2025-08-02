package tracker.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    void epicsWithSameIdShouldBeEqual() {
        Epic e1 = new Epic(1, "Переезд ", "Организовать переезд");
        Epic e2 = new Epic(1, "Заварить чай", "Сделать горячий чай");

        assertEquals(e1, e2);
    }

    @Test
    void epicsWithDifferentIdShouldNotBeEqual() {
        Epic e1 = new Epic(1, "Переезд ", "Организовать переезд");
        Epic e2 = new Epic(2, "Заварить чай", "Сделать горячий чай");

        assertNotEquals(e1, e2);
    }

}