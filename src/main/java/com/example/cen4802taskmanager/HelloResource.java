package com.example.cen4802taskmanager;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/tasks")
public class HelloResource {
    private static final Logger logger =
            Logger.getLogger(HelloResource.class.getName());

    private static final List<Task> tasks = new ArrayList<>();

    static {
        tasks.add(new Task(
                1,
                "Review Ambulance Design",
                "Review the latest ambulance design specifications before production."
        ));

        tasks.add(new Task(
                2,
                "Order Manufacturing Materials",
                "Verify that aluminum, electrical components, medical equipment, and other required materials are available."
        ));

        tasks.add(new Task(
                3,
                "Inspect Electrical System",
                "Perform a quality inspection of the ambulance electrical and emergency systems."
        ));

        tasks.add(new Task(
                4,
                "Schedule Quality Control",
                "Schedule the final quality control inspection before the vehicle is delivered."
        ));
    }

    static List<Task> filterTasks(List<Task> taskList, String status) {
        String selectedStatus = status == null
                ? "all"
                : status.toLowerCase();

        if (!selectedStatus.equals("all")
                && !selectedStatus.equals("pending")
                && !selectedStatus.equals("completed")) {

            Logger.getLogger(HelloResource.class.getName()).warning(
                    "Invalid task status filter received: " + selectedStatus
                            + ". Defaulting to all tasks."
            );

            selectedStatus = "all";
        }

        final String filter = selectedStatus;

        return taskList.stream()
                .filter(task -> filter.equals("all")
                        || (filter.equals("completed") && task.isCompleted())
                        || (filter.equals("pending") && !task.isCompleted()))
                .collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getTasks(
            @QueryParam("complete") Integer taskId,
            @QueryParam("status") String status) {
        logger.info("Task page requested. Status filter: "
                + (status == null ? "all" : status)
                + ", completion request ID: " + taskId);
        logger.fine("Detailed diagnostic: getTasks() was called.");

        if (taskId != null) {
            boolean taskFound = false;

            for (Task task : tasks) {
                if (task.getId() == taskId) {
                    if (!task.isCompleted()) {
                        task.setCompleted(true);
                        logger.info("Task completed successfully. ID: "
                                + task.getId() + ", title: " + task.getTitle());
                    } else {
                        logger.warning("Task was already completed. ID: " + taskId);
                    }

                    taskFound = true;
                    break;
                }
            }

            if (!taskFound) {
                logger.severe("Task completion failed because the task was not found. ID: "
                        + taskId);
            }
        }

        String filter = status == null ? "all" : status.toLowerCase();
        List<Task> filteredTasks = filterTasks(tasks, status);
        logger.info("Task filter applied: " + filter
                + ". Matching tasks: " + filteredTasks.size());

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<title>Business Task Manager</title>");

        html.append("<style>");

        html.append("body {");
        html.append("font-family: Arial, sans-serif;");
        html.append("margin: 0;");
        html.append("background-color: #f4f6f8;");
        html.append("color: #333;");
        html.append("}");

        html.append(".header {");
        html.append("background-color: #1f2937;");
        html.append("color: white;");
        html.append("padding: 25px 40px;");
        html.append("}");

        html.append(".header h1 {");
        html.append("margin: 0;");
        html.append("}");

        html.append(".header p {");
        html.append("margin: 8px 0 0;");
        html.append("color: #d1d5db;");
        html.append("}");

        html.append(".container {");
        html.append("max-width: 1000px;");
        html.append("margin: 30px auto;");
        html.append("padding: 0 20px;");
        html.append("}");

        html.append(".filters {");
        html.append("margin: 20px 0;");
        html.append("}");

        html.append(".filters a {");
        html.append("display: inline-block;");
        html.append("margin: 0 8px 8px 0;");
        html.append("padding: 10px 14px;");
        html.append("background-color: #e5e7eb;");
        html.append("color: #1f2937;");
        html.append("text-decoration: none;");
        html.append("border-radius: 5px;");
        html.append("}");

        html.append(".filters a:hover {");
        html.append("background-color: #d1d5db;");
        html.append("}");

        html.append(".task {");
        html.append("background-color: white;");
        html.append("padding: 20px;");
        html.append("margin-bottom: 15px;");
        html.append("border-radius: 8px;");
        html.append("box-shadow: 0 2px 5px rgba(0,0,0,0.08);");
        html.append("}");

        html.append(".task h2 {");
        html.append("margin-top: 0;");
        html.append("}");

        html.append(".description {");
        html.append("color: #555;");
        html.append("}");

        html.append(".status {");
        html.append("font-weight: bold;");
        html.append("}");

        html.append(".button {");
        html.append("display: inline-block;");
        html.append("padding: 8px 14px;");
        html.append("margin-top: 8px;");
        html.append("background-color: #2563eb;");
        html.append("color: white;");
        html.append("text-decoration: none;");
        html.append("border-radius: 5px;");
        html.append("}");

        html.append("</style>");
        html.append("</head>");

        html.append("<body>");

        html.append("<div class='header'>");
        html.append("<h1>Business Task Manager - Docker Updated Version</h1>");
        html.append("<p>Manage, monitor, and track tasks across the organization.</p>");
        html.append("</div>");

        html.append("<div class='container'>");
        html.append("<h2>Current Tasks (");
        html.append(filteredTasks.size());
        html.append(")</h2>");

        html.append("<div class='filters'>");
        html.append("<a href='?status=all'>All Tasks</a>");
        html.append("<a href='?status=pending'>Pending Tasks</a>");
        html.append("<a href='?status=completed'>Completed Tasks</a>");
        html.append("</div>");

        if (filteredTasks.isEmpty()) {
            html.append("<p>No tasks found for this filter.</p>");
        }

        for (Task task : filteredTasks) {
            html.append("<div class='task'>");

            html.append("<h2>");
            html.append(task.getId());
            html.append(". ");
            html.append(task.getTitle());
            html.append("</h2>");

            html.append("<p class='description'>");
            html.append(task.getDescription());
            html.append("</p>");

            html.append("<p class='status'>Status: ");

            if (task.isCompleted()) {
                html.append("Completed");
            } else {
                html.append("Pending");
            }

            html.append("</p>");

            if (!task.isCompleted()) {
                html.append("<a class='button' href='?complete=");
                html.append(task.getId());
                html.append("&amp;status=");
                html.append(filter);
                html.append("'>Mark as Completed</a>");
            }

            html.append("</div>");
        }

        html.append("</div>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
