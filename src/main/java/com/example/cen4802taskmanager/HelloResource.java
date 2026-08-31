package com.example.cen4802taskmanager;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/tasks")
public class HelloResource {

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

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getTasks(@QueryParam("complete") Integer taskId) {

        if (taskId != null) {
            for (Task task : tasks) {
                if (task.getId() == taskId) {
                    task.setCompleted(true);
                    break;
                }
            }
        }

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
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
        html.append("<h1>Business Task Manager</h1>");
        html.append("<p>Manage, monitor, and track tasks across the organization.</p>");
        html.append("</div>");

        html.append("<div class='container'>");

        html.append("<h2>Current Tasks</h2>");

        for (Task task : tasks) {

            html.append("<div class='task'>");

            html.append("<h2>");
            html.append(task.getId());
            html.append(". ");
            html.append(task.getTitle());
            html.append("</h2>");

            html.append("<p class='description'>");
            html.append(task.getDescription());
            html.append("</p>");

            html.append("<p class='status'>");
            html.append("Status: ");

            if (task.isCompleted()) {
                html.append("Completed");
            } else {
                html.append("Pending");
            }

            html.append("</p>");

            if (!task.isCompleted()) {
                html.append("<a class='button' href='?complete=");
                html.append(task.getId());
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