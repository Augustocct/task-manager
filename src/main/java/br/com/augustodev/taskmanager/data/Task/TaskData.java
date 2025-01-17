package br.com.augustodev.taskmanager.data.Task;

import java.util.Date;

public record TaskData(
                String name,
                String description,
                String status,
                Integer priority,
                Date startDate,
                Date endDate) {
}
