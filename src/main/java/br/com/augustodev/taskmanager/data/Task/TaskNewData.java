package br.com.augustodev.taskmanager.data.Task;

import java.sql.Date;

import jakarta.validation.constraints.Size;

public record TaskNewData(
        String name,
        String description,
        String status,
        @Size(min= 0, max= 5) Integer priority,
        Date startDate,
        Date endDate) {

}
