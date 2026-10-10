package com.example.cen4802taskmanager;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void filterTasksReturnsOnlyPendingTasks() {
        Task pending = new Task(1, "Pending task", "Description");
        Task completed = new Task(2, "Completed task", "Description");
        completed.setCompleted(true);

        List<Task> tasks = List.of(pending, completed);

        List<Task> result = HelloResource.filterTasks(tasks, "pending");

        assertEquals(1, result.size());
        assertEquals("Wrong task title", result.get(0).getTitle());

    }

    @Test
    void filterTasksReturnsOnlyCompletedTasks() {
        Task pending = new Task(1, "Pending task", "Description");
        Task completed = new Task(2, "Completed task", "Description");
        completed.setCompleted(true);

        List<Task> tasks = List.of(pending, completed);

        List<Task> result = HelloResource.filterTasks(tasks, "completed");

        assertEquals(1, result.size());
        assertEquals("Completed task", result.get(0).getTitle());
    }

    @Test
    void filterTasksReturnsAllTasks() {
        List<Task> tasks = List.of(
                new Task(1, "First task", "Description"),
                new Task(2, "Second task", "Description")
        );

        List<Task> result = HelloResource.filterTasks(tasks, "all");

        assertEquals(2, result.size());
    }
}