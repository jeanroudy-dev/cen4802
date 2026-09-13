package com.example.cen4802taskmanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getId() {
        Task task = new Task(
                1,
                "Review ambulance design",
                "Review the latest ambulance design specifications."
        );

        assertEquals(1, task.getId());
    }

    @Test
    void getTitle() {
        Task task = new Task(
                2,
                "Order manufacturing materials",
                "Verify required materials."
        );

        assertEquals("Order manufacturing materials", task.getTitle());
    }

    @Test
    void getDescription() {
        Task task = new Task(
                3,
                "Inspect electrical system",
                "Perform an electrical inspection."
        );

        assertEquals(
                "Perform an electrical inspection.",
                task.getDescription()
        );
    }

    @Test
    void isCompleted() {
        Task task = new Task(
                4,
                "Schedule quality control",
                "Schedule the final quality control inspection."
        );

        assertFalse(task.isCompleted());
    }

    @Test
    void setCompleted() {
        Task task = new Task(
                5,
                "Complete production review",
                "Review production requirements."
        );

        task.setCompleted(true);

        assertTrue(task.isCompleted());
    }

}